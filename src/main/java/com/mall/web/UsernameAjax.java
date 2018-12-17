package com.mall.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.exception.MsgException;
import com.mall.service.UserService;
import com.mall.util.BaseFactory;
import com.mall.util.JDBCUtils;


public class UsernameAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		//text/html;charset=utf-8
//		response.setContentType("text/html;charset=utf-8");
		
		
		String username = request.getParameter("username");
		UserService userService = BaseFactory.getFactory().getInstance(UserService.class);
		try {
			boolean flag = userService.hasUsername(username);
			if(flag){
				response.getWriter().write("用户名已存在");
			}
//			else{
//				response.getWriter().write("用户名可以使用");
//			}
		} catch (MsgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			con = JDBCUtils.getConnection();
//			ps = con.prepareStatement("select username from user where username=? ");
//			ps.setString(1, username);
//			rs = ps.executeQuery();
//			System.out.println(username);
//			System.out.println("ajaxx");
//			while(rs.next()){
//				response.getWriter().write("用户名已存在");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			JDBCUtils.close(con,ps,rs);
//		}
//		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
