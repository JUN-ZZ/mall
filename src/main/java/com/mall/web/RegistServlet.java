package com.mall.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.domain.User;
import com.mall.service.UserService;
import com.mall.util.BaseFactory;
import com.mall.util.JDBCUtils;
import com.mall.util.MD5Utils;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//获取servletContext里的初始值
//		ServletContext sc = getServletContext();
//		String encode = sc.getInitParameter("encode");
//		//处理请求乱码
//		request.setCharacterEncoding(encode);
//		//处理响应乱码
//		response.setContentType("text/html;charset=utf-8");
//		
		
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String valistr = request.getParameter("valistr");
		//进行MD5加密
		if(password!=null){
			password = MD5Utils.md5(password);
			password2= MD5Utils.md5(password2);
		}
		
		//注释说明，直接转到前端js验证了
/*		//表单非空验证
		//验证用户名不为空
		if(username==null||"".equals(username.trim())){
			request.setAttribute("usernameError","用户名不能为空");
		}
		if(password==null||"".equals(password.trim())){
			request.setAttribute("passwordError","密码不能为空");
		}
		if(password2==null||"".equals(password2.trim())){
			request.setAttribute("passwordError2","确认密码不能为空");
		}
		if(nickname==null||"".equals(nickname.trim())){
			request.setAttribute("nicknameError","昵称不能为空");
		}
		if(email==null||"".equals(email.trim())){
			request.setAttribute("emailError","邮箱不能为空");
		}
		if(valistr==null||"".equals(valistr.trim())){
			request.setAttribute("valistrError","验证码不能为空");
		}
		if(request.getAttribute("usernameError")!=null||
			request.getAttribute("passwordError")!=null||
			request.getAttribute("passwordError2")!=null||
			request.getAttribute("nicknameError")!=null||
			request.getAttribute("emailError")!=null||
			request.getAttribute("valistrError")!=null
		){
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}*/
		
/*		//验证用户名是否存在
		//查询数据库中是否有相同用户名存在
		Connection con = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select username from user where username=? ");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()){
				request.setAttribute("usernameError","用户名已存在");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs = null;
			ps = null;
			JDBCUtils.close(con);
		}*/
		//验证两次密码是否一致
/*		if(!password.equals(password2)){
			request.setAttribute("passwordError2","确认密码不正确");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}*/	
		//格式邮箱验证admim@jun.com
/*		String regex = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(regex)){
			request.setAttribute("emailError","邮箱格式不正确");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}*/
		
		//进行验证码是否正确进行会话级别的验证
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("verifyCode");
		if(!code.equals(valistr)){
			request.setAttribute("errMsg", "验证码错误");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
		
		//将用户保存到数据库中
		User user = new User();
		user.setId(0);
		user.setUsername(username);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setPassword(password);
		UserService userService = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = userService.registUser(user);
		if(flag){
			response.getWriter().write(
			"<h1 style='text-align:center;color:red'>"
					+ "恭喜您，注册成功，3秒自动跳转首页</h1>");
			response.setHeader("refresh", "3;url="
			+request.getContextPath()+"/index.jsp");
			return;
		}
		
		
//		//将用户保存到数据库中
//		Connection con = JDBCUtils.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			String insertSql = "insert into user values(null,?,?,?,?) ";
//			ps = con.prepareStatement(insertSql);
//			ps.setString(1, username);
//			ps.setString(2, password);
//			ps.setString(3, nickname);
//			ps.setString(4, email);
//			int i = ps.executeUpdate();
//			if(i>0){
//				response.getWriter().write(
//				"<h1 style='text-align:center;color:red'>"
//						+ "恭喜您，注册成功，3秒自动跳转首页</h1>");
//				response.setHeader("refresh", "3;url="
//				+request.getContextPath()+"/index.jsp");
//				return;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			ps = null;
//			JDBCUtils.close(con,ps,rs);
//		}
//		
		
		//出现异常的情况
		request.setAttribute("errMsg", "服务器出现异常，请稍后重试");
		request.getRequestDispatcher("regist.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
