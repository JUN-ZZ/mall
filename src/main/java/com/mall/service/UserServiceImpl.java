package com.mall.service;

import com.mall.dao.UserDao;
import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.util.BaseFactory;

public class UserServiceImpl implements UserService {
	
	private UserDao dao =
	BaseFactory.getFactory().getInstance(UserDao.class);
	
	
	@Override
	public boolean hasUsername(String username) throws MsgException {
		// TODO Auto-generated method stub
		return dao.getUserByUsername(username);
	}

	@Override
	public boolean registUser(User user) {
		// TODO Auto-generated method stub
		return dao.insertUser(user);
	}

	@Override
	public User login(String username, String password) throws MsgException {
		// TODO Auto-generated method stub
		return dao.getUserByUAP(username, password);
	}

	
}
