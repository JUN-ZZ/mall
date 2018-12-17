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
		HttpSession session = request.getSession();//����֤��ŵ�session��
		OutputStream out = response.getOutputStream();
		verifyCode.drawImage(out);//�Ȼ���֤�����text�ı�
		String code = verifyCode.getCode();
		session.setAttribute("verifyCode",code);
		System.out.println("��֤��"+code);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
