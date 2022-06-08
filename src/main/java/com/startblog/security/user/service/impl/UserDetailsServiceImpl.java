package com.startblog.security.user.service.impl;

import com.startblog.security.user.service.PermissionInfoService;
import com.startblog.security.user.service.RoleInfoService;
import com.startblog.security.user.service.UserInfoService;
import com.startblog.sys.role.bean.Role;
import com.startblog.sys.user.bean.User;
import com.startblog.sys.user.enums.UserStatus;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月06日
 */
public class UserDetailsServiceImpl implements UserDetailsService, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("userInfoService")
    private UserInfoService userInfoService;
    @Autowired
    @Qualifier("permissionInfoService")
    private PermissionInfoService permissionInfoService;
    @Autowired
    @Qualifier("roleInfoService")
    private RoleInfoService roleInfoService;
    
    private String rolePrefix = "ROLE_";
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userInfoService, "userInfoService is required!");
        Assert.notNull(permissionInfoService, "permissionInfoService is required!");
        Assert.notNull(roleInfoService, "roleInfoService is required!");
    
    }
    
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Assert.hasText(account, "user account is required!");
        User user = userInfoService.getUserByAccount(account);
        if(user == null){
            throw new UsernameNotFoundException("user " + account + " not exist");
        }
        Set<GrantedAuthority> dbAuthsSet = new HashSet();
        dbAuthsSet.addAll(loadUserAuthorities(user));
        List<GrantedAuthority> dbAuths = new ArrayList<>();
        dbAuths.addAll(loadUserAuthorities(user));
        if (dbAuths.size() == 0) {
            this.logger.debug("User '{}' has no authorities and will be treated as 'not found'", account);
            throw new UsernameNotFoundException("User " + account + " has no GrantedAuthority");
        } else {
            return createUserDetails(account, user, dbAuths);
        }
    }
    protected UserDetails createUserDetails(String username, User user, List<GrantedAuthority> combinedAuthorities) {
        String account = user.getAccount();
        String password = user.getPassWord();
        int status = user.getStatus();
        boolean enabled = user.getEnabled() == 1;
        boolean nonExpired = UserStatus.ACCOUNT_EXPIRED.getIndex() == status;
        boolean nonLocked = UserStatus.LOCKED.getIndex() == status;
        boolean credentialsNonExpired = UserStatus.CREDENTIALS_EXPIRED.getIndex() == status;
        return new org.springframework.security.core.userdetails.User(account,
                password, enabled, nonExpired, credentialsNonExpired, nonLocked, combinedAuthorities);
    }
    protected void addCustomAuthorities(User user, List<GrantedAuthority> authorities) {
    }
    protected List<GrantedAuthority> loadUserAuthorities(User user) {
        Role[] roles = roleInfoService.getAssignedRoles(user);
        List<GrantedAuthority> list = new ArrayList(roles.length);
    
        for (Role role : roles) {
            String roleName = rolePrefix + role.getRoleCode();
            list.add(new SimpleGrantedAuthority(roleName));
        }
        return list;
    }
}
