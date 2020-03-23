package com.pang.stethoscope.service.impl;


import com.pang.stethoscope.mapper.UserMapper;
import com.pang.stethoscope.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userDao;

    @Override
    public List<Map<String, Object>> findUserList() {
        return userDao.findUserList();
    }


}
