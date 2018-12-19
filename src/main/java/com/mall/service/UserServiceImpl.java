package com.mall.service;

import com.mall.dao.UserDao;
import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.util.BaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("userService")
public class UserServiceImpl implements UserService {
	
//	private UserDao dao =
//	BaseFactory.getFactory().getInstance(UserDao.class);
//

	@Autowired
	private UserDao userDao ;

	@Override
	public boolean hasUsername(String username) throws MsgException {
		// TODO Auto-generated method stub
		return userDao.getUserByUsername(username);
	}

	@Override
	public boolean registUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}

	@Override
	public User login(String username, String password) throws MsgException {
		// TODO Auto-generated method stub
		return userDao.getUserByUAP(username, password);
	}

	
}
