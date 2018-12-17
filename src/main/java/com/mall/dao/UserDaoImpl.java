package com.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	
	@Override
	public boolean getUserByUsername(String username) {
		Connection con = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select username from user where username=? ");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(con, ps, rs);
		}
		return false;
	}

	@Override
	public boolean insertUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String nickname = user.getNickname();
		String email = user.getEmail();
		String sql = "insert into user values(null,?,?,?,?) ";
		Connection con = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, nickname);
			ps.setString(4, email);
			rs = ps.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(con, ps, null);
		}
		return false;
	}

	@Override
	public User getUserByUAP(String username, String password) throws MsgException {
		User user = null;
		String sql = "select id,username,password,nickname,email from user where username=? and password=? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JDBCUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(con, ps, rs);
		}
		return user;
	}

	
	
}
