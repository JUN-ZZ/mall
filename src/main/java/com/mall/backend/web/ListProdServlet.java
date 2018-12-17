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
		//调用Service对应的方法，获取商品数据
		ProdService service = 
				BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list = service.listAllProd();
		//将商品数据添加到request作用域中
		request.setAttribute("list", list);
		
		//将请求转发给相应的页面
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
