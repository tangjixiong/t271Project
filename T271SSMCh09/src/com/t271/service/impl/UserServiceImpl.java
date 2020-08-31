package com.t271.service.impl;

import com.t271.entity.User;
import com.t271.mapper.UserMapper;
import com.t271.service.UserService;
import com.t271.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("userService")
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String code, String pwd) {
        User user=userMapper.findUserByCodeAndPwd(code,pwd);
        return user;
    }

    @Override
    public Page pagationUser(HashMap<String, Object> hashMap) {
        //1、获取数据总条数
        Integer totalCount=userMapper.countUserByPage(hashMap);
        //2、创建page对象
        Page page=new Page(hashMap.get("curpage"),hashMap.get("pagesize"),totalCount);
        hashMap.put("startrow",page.getStartrow());
        hashMap.put("pagesize",page.getPageSize());
        //3、查询指定页码数据
        List<User> list=userMapper.getUserByPage(hashMap);
        //4、把数据放入page对象
        page.setList(list);
        return page;
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUserByUserCode(String userCode) {
        return userMapper.getUserByUserCode(userCode);
    }
}
