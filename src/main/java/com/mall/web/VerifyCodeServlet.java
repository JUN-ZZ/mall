package com.mall.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.util.VerifyCode;

/**
 * Servlet implementation class VerifyCodeServlet
 */

public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
		
		VerifyCode verifyCode = new VerifyCode();
		HttpSession session = request.getSession();//把验证码放到session中
		OutputStream out = response.getOutputStream();
		verifyCode.drawImage(out);//先画验证码才有text文本
		String code = verifyCode.getCode();
		session.setAttribute("verifyCode",code);
		System.out.println("验证码"+code);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
