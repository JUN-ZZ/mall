package com.mall.service;

import com.mall.domain.User;
import com.mall.exception.MsgException;

public interface UserService {
	/**
	 * ������֤�û����Ƿ����
	 * @param username
	 * @return
	 * @throws MsgException
	 */
	boolean hasUsername(String username) throws MsgException;
	/**
	 * ����ע���û��ķ���
	 * @param user
	 * @return
	 */
	boolean registUser(User user);

	/**
	 * ������¼�ķ���
	 * @param username
	 * @param password
	 * @return
	 * @throws MsgException
	 */
	User login(String username, String password) throws MsgException;
	
	
}
