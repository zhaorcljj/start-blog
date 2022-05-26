package com.startblog.mvc;

import com.startblog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
@RestController("loginController")
//@RequestMapping("${url.rest.prefix.v1}/usermanage")
@RequestMapping("/api/usermanage")
public class LoginController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(
            @RequestParam(value = "account", name = "账号", required = true) String account,
            @RequestParam(value = "passWord", name = "密码", required = true) String passWord){
        com.startblog.security.user.User user = new com.startblog.security.user.User();
        user.setAccount(account);
        user.setPassWord(passWord);
        boolean flag = userService.login(user);
        return flag;
    }
    
    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String register(){
        
        return "success";
    }
}
