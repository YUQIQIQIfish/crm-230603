package com.crm.settings.service.Impl;

import com.crm.settings.mapper.UserMapper;
import com.crm.settings.pojo.User;
import com.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServieImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }
}
