package com.mall.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

	private static ThreadLocal<Connection> t1 = 
			new ThreadLocal<Connection>();//������������Ӷ���
	
	/**
	 * ��������ķ���
	 */
	public static void startTrans(){
		//��ȡһ���µ����Ӷ���
		Connection conn = JDBCUtils.getConnection();
		t1.set(conn);//��Conn���뵱ǰ�̶߳����ThreadLocalMap������
		try {
			//�������Ӷ���������
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commitTrans(){
		//����t1�����������󣬴ӵ�ǰ�̶߳���ThreadLocalMap�����л�ȡ֮ǰ��conn����
		//key--t1,value--Conn
		Connection conn = t1.get();
		try {
			//���ڸ����Ӷ����ύ����
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void rollBack(){
		try {
			t1.get().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConn(){
		return t1.get();
	}
	
	
	public static void closeConn(){
		Connection conn = t1.get();
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn = null;
				//�ӵ�ǰ�̶߳����map������ɾ�����Ӷ���
				t1.remove();
			}
		}
	}
	
	
}
