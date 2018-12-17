package com.mall.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	private static ComboPooledDataSource dataSource = null;
	
	static{
		dataSource = new ComboPooledDataSource();
	}
	
	public static Connection getConnection(){
		Connection con = null;
		try {
		con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con,PreparedStatement ps,ResultSet rs){
		if(con!=null){			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				con = null;
			}
		}
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ps = null;
			}
		}

	}
	
	public static void main(String[] args) {
		Connection con = getConnection();
		Connection con1 = getConnection();
		Connection con2 = getConnection();
		Connection con3 = getConnection();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(con1,null,null);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
