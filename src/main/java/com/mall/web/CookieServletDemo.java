package com.mall.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServletDemo
 */
@WebServlet("/CookieServletDemo")
public class CookieServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡservletContext��ĳ�ʼֵ
		ServletContext sc = getServletContext();
		String encode = sc.getInitParameter("encode");
		//������������
		request.setCharacterEncoding(encode);
		//������Ӧ����
		response.setContentType("text/html;charset=utf-8");
		
		request.getInputStream();
		
		Cookie[] cs = request.getCookies();
		Cookie cookie = null;
		if(cs!=null){			
			for(Cookie c:cs){
				if("time".equals(c.getName())){
					cookie = c;
				}
			}
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time =sdf.format(date); 
		Cookie cc = new Cookie("time",time);
		response.addCookie(cc);
		if(cookie==null){
			response.getWriter().write("���ǵ�һ�η��ʵġ�������");
			return;
		}
		response.getWriter().write(time);
		
		
/*		//��Ϣͷ����cookie
		String lastTime = request.getHeader("cookie");
		if(lastTime==null){
			response.setHeader("set-cookie", new Date().toLocaleString());
			response.getWriter().write("���ǵ�һ�η��ʡ���");
			return;
		}
		String time =  new Date().toLocaleString();
		response.setHeader("set-cookie",time);
		response.getWriter().write("�ϴη���ʱ�䣺"+lastTime+"��ǰʱ�䣺"+time);
		*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
