package com.t271.mapper;

import com.t271.entity.Role;
import com.t271.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    //根据用户名和密码查询用户信息
    public User findUserByCodeAndPwd(@Param("code")String code,@Param("pwd") String pwd);
    //统计数据条数
    public Integer countUserByPage(HashMap<String, Object> hashMap);
    //查询指定页码数据
    public List<User> getUserByPage(HashMap<String, Object> hashMap);
    //新增用户
    public Integer insertUser(User user);
    //根据id获取用户信息
    public User getUserById(Integer id);
    //修改用户
    public  Integer updateUser(User user);
    //根据usercode查询用户信息
    public User getUserByUserCode(String userCode);
}
