package com.mall.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

	private static ThreadLocal<Connection> t1 = 
			new ThreadLocal<Connection>();//操作事务的连接对象
	
	/**
	 * 开启事务的方法
	 */
	public static void startTrans(){
		//获取一个新的连接对象
		Connection conn = JDBCUtils.getConnection();
		t1.set(conn);//将Conn存入当前线程对象的ThreadLocalMap集合中
		try {
			//基于连接对象开启事务
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commitTrans(){
		//基于t1这个工具类对象，从当前线程对象ThreadLocalMap集合中获取之前的conn对象
		//key--t1,value--Conn
		Connection conn = t1.get();
		try {
			//基于该连接对象提交事务
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
				//从当前线程对象的map集合中删除连接对象
				t1.remove();
			}
		}
	}
	
	
}
