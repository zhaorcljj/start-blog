-- 创建表空间和用户
create database startblog character set utf8mb4 collate utf8mb4_0900_as_cs;
create user 'startblog'@'%' identified by 'startblog';
GRANT ALL PRIVILEGES ON startblog.* TO 'startblog'@'%'WITH GRANT OPTION;
