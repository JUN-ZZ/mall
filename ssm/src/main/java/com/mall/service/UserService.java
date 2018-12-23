package com.mall.service;

import com.mall.dao.UserMapper;
import com.mall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jun
 * @date 2018/12/23
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper = null;

//    public List<User> listAllUser(){
////        return userMapper.queryUserAll();
//    }

    public String queryUserNameById(Integer id){
        return userMapper.queryUsernameByUid();
    }



}
