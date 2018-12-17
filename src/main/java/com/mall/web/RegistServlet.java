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
//		//��ȡservletContext��ĳ�ʼֵ
//		ServletContext sc = getServletContext();
//		String encode = sc.getInitParameter("encode");
//		//������������
//		request.setCharacterEncoding(encode);
//		//������Ӧ����
//		response.setContentType("text/html;charset=utf-8");
//		
		
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String valistr = request.getParameter("valistr");
		//����MD5����
		if(password!=null){
			password = MD5Utils.md5(password);
			password2= MD5Utils.md5(password2);
		}
		
		//ע��˵����ֱ��ת��ǰ��js��֤��
/*		//���ǿ���֤
		//��֤�û�����Ϊ��
		if(username==null||"".equals(username.trim())){
			request.setAttribute("usernameError","�û�������Ϊ��");
		}
		if(password==null||"".equals(password.trim())){
			request.setAttribute("passwordError","���벻��Ϊ��");
		}
		if(password2==null||"".equals(password2.trim())){
			request.setAttribute("passwordError2","ȷ�����벻��Ϊ��");
		}
		if(nickname==null||"".equals(nickname.trim())){
			request.setAttribute("nicknameError","�ǳƲ���Ϊ��");
		}
		if(email==null||"".equals(email.trim())){
			request.setAttribute("emailError","���䲻��Ϊ��");
		}
		if(valistr==null||"".equals(valistr.trim())){
			request.setAttribute("valistrError","��֤�벻��Ϊ��");
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
		
/*		//��֤�û����Ƿ����
		//��ѯ���ݿ����Ƿ�����ͬ�û�������
		Connection con = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select username from user where username=? ");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()){
				request.setAttribute("usernameError","�û����Ѵ���");
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
		//��֤���������Ƿ�һ��
/*		if(!password.equals(password2)){
			request.setAttribute("passwordError2","ȷ�����벻��ȷ");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}*/	
		//��ʽ������֤admim@jun.com
/*		String regex = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(regex)){
			request.setAttribute("emailError","�����ʽ����ȷ");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}*/
		
		//������֤���Ƿ���ȷ���лỰ�������֤
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("verifyCode");
		if(!code.equals(valistr)){
			request.setAttribute("errMsg", "��֤�����");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
		
		//���û����浽���ݿ���
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
					+ "��ϲ����ע��ɹ���3���Զ���ת��ҳ</h1>");
			response.setHeader("refresh", "3;url="
			+request.getContextPath()+"/index.jsp");
			return;
		}
		
		
//		//���û����浽���ݿ���
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
//						+ "��ϲ����ע��ɹ���3���Զ���ת��ҳ</h1>");
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
		
		//�����쳣�����
		request.setAttribute("errMsg", "�����������쳣�����Ժ�����");
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
