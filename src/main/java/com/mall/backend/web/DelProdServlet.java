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
 * Servlet implementation class DelProdServlet
 */
@WebServlet("/DelProdServlet")
public class DelProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pidStr = request.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		//根据service执行删除逻辑
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		//根据pid删除商品
		boolean flag = service.deleteProdByPid(pid);
		if(flag==true){
			//删除成功则重定向列出全部商品页面
			response.getWriter().write("商品删除成功");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/backend/ListProdServlet");
			return ;
		}
		//不成功，则转发回原来的页面，并且携带原来的商品信息
		List<Prod> list = service.listAllProd();
		request.setAttribute("list", list);
		request.setAttribute("errMsg", "删除商品失败");
		request.getRequestDispatcher("").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
