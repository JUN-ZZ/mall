package com.mall.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.UserDao;
import com.mall.dao.UserDaoImpl;
import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.service.UserService;
import com.mall.util.BaseFactory;
import com.mall.util.JDBCUtils;
import com.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService ;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String remname = request.getParameter("remname");
		String autologin = request.getParameter("autologin");
		if(password!=null){
			password = MD5Utils.md5(password);
		}
		
		//�ж���û�м�ס�û������еĻ������û���cookie		
		if(remname!=null&&"true".equals(remname)){
			Cookie c1 = new Cookie("remname",URLEncoder.encode(username, "utf-8"));
			c1.setPath(request.getContextPath()+"/");
			c1.setMaxAge(60*60*24*7);//7��
			response.addCookie(c1);
		}else{
			Cookie c1 = new Cookie("remname","");
			c1.setPath(request.getContextPath()+"/");	
			c1.setMaxAge(0);
			response.addCookie(c1);
		}
		
		//�ж���û�м�ס�Զ���¼,�еĻ������Զ���¼cookie
		if(autologin!=null&&"true".equals(autologin)){
			Cookie c2 = new Cookie("autologin",URLEncoder.encode(username+";"+password, "utf-8"));
			c2.setPath(request.getContextPath()+"/");
			c2.setMaxAge(60*60*24*30);//30��
			response.addCookie(c2);
		}else{
			Cookie c2 = new Cookie("autologin","");
			c2.setPath(request.getContextPath()+"/");
			c2.setMaxAge(0);//0��ɾ��֮ǰ��cookie
			response.addCookie(c2);
		}
		
		//mvc �Ĳ�ܹ�ʵ���߼�
		UserService userService = BaseFactory.getFactory().getInstance(UserService.class);

		User user = null;
		try {
			user = userService.login(username, password);
			if(user!=null){
				request.getSession().setAttribute("user", user);
				response.getWriter().write("<h1 style='text-algin:center;color:red'>��¼�ɹ���3��󷵻���ҳ!!!</h1>");
				response.setHeader("Refresh", "3;url="+request.getContextPath()+"/index.jsp");
				return;
			}
			//����ѯ���û��˵��û�е�¼Ȩ��
			request.setAttribute("error", "�û�����������������");
			request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request, response);
		} catch (MsgException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		������ʹ��dao�������֤��Ϊ�˽���ͣ�����service����н����
//		UserDao userDao = new UserDaoImpl();
//		User user = null;
//		try {
//			user = userDao.getUserByUAP(username, password);
//			if(user!=null){
//				request.getSession().setAttribute("user", user);
//				response.getWriter().write("<h1 style='text-algin:center;color:red'>��¼�ɹ���3��󷵻���ҳ!!!</h1>");
//				response.setHeader("Refresh", "3;url=/index.jsp");
//				return;
//			}
//			//����ѯ���û��˵��û�е�¼Ȩ��
//			request.setAttribute("error", "�û�����������������");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
//			
//		} catch (MsgException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
	}
	
	//���е�¼��֤
	private void verifyUser(HttpServletRequest request,
			HttpServletResponse response,String username,String password){
		//���е�¼��֤
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JDBCUtils.getConnection();
			ps = con.prepareStatement("select username from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				//�Ự
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				response.getWriter().write("<h1 style='text-algin:center;color:red'>��¼�ɹ���3��󷵻���ҳ!!!</h1>");
				response.setHeader("Refresh", "3;url=/index.jsp");
				return ;
			}
			//����ѯ���û��˵��û�е�¼Ȩ��
			request.setAttribute("error", "�û�����������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(con,ps,rs);
		}
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
