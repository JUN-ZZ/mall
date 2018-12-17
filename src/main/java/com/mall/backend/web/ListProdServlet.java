package com.mall.backend.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.domain.Prod;
import com.mall.service.ProdService;
import com.mall.util.BaseFactory;

/**
 * Servlet implementation class ListProdServlet
 */
@WebServlet("/backend/ListProdServlet")
public class ListProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����Service��Ӧ�ķ�������ȡ��Ʒ����
		ProdService service = 
				BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list = service.listAllProd();
		//����Ʒ������ӵ�request��������
		request.setAttribute("list", list);
		
		//������ת������Ӧ��ҳ��
		request.getRequestDispatcher("/backend/manageProdList.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
