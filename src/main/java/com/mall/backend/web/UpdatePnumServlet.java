package com.mall.backend.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.service.ProdService;
import com.mall.util.BaseFactory;

/**
 * Servlet implementation class UpdatePnumServlet
 */
@WebServlet("/UpdatePnumServlet")
public class UpdatePnumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ajax");
		String pnumStr = request.getParameter("pnum");
		String pidStr = request.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		int pnum = Integer.parseInt(pnumStr);
		//执行逻辑
		
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag = service.updatePnum(pid, pnum);
		if(flag==true){
			response.getWriter().write("商品数量修改成功");
		}else{
			response.getWriter().write("商品数量修改失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
