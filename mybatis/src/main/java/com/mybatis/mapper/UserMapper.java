package com.mybatis.mapper;

import com.mybatis.domain.User;

import java.util.List;

/**
 * @author jun
 * @date 2018/12/21
 */
public interface UserMapper {

    List<User> queryUserAll();

    String queryUsernameByAge(int age);

}
