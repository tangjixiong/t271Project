package com.t271.service;

import com.t271.entity.User;
import com.t271.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface UserService {
    //登录功能
    public User login(String code, String pwd);
    //分页查询功能
    public Page pagationUser(HashMap<String,Object> hash);
    //新增用户
    public Integer insertUser(User user);
    //根据id获取用户信息
    public User getUserById(Integer id);
    //修改用户
    public  Integer updateUser(User user);
    //根据usercode查询用户信息
    public User getUserByUserCode(String userCode);
}
