package com.mall.dao;

import com.mall.domain.User;
import com.mall.exception.MsgException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

	/**
	 * �����û�����ѯ�û��Ƿ����
	 * @param username
	 * @return true���� false ������
	 */
	boolean getUserByUsername(String username);
	
	/**
	 * 
	 * @param user
	 * @return true�ɹ�  false���ɹ�
	 */
	boolean insertUser(User user);
	
	/**
	 * �����û����������ѯ�û����ݵķ���
	 * @param username
	 * @param password
	 * @return 
	 */
	User getUserByUAP(String username, String password) throws MsgException;
	
	
}
