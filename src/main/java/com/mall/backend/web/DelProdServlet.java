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
		//����serviceִ��ɾ���߼�
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		//����pidɾ����Ʒ
		boolean flag = service.deleteProdByPid(pid);
		if(flag==true){
			//ɾ���ɹ����ض����г�ȫ����Ʒҳ��
			response.getWriter().write("��Ʒɾ���ɹ�");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/backend/ListProdServlet");
			return ;
		}
		//���ɹ�����ת����ԭ����ҳ�棬����Я��ԭ������Ʒ��Ϣ
		List<Prod> list = service.listAllProd();
		request.setAttribute("list", list);
		request.setAttribute("errMsg", "ɾ����Ʒʧ��");
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
