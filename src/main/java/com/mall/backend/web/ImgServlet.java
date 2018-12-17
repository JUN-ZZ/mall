package com.mall.backend.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数imgurl
		String imgurl = request.getParameter("imgurl");
		//表单验证
		//执行逻辑
		String realPath = this.getServletContext().getRealPath(imgurl);
		FileInputStream fis = null;
		try{
			//创建文件输入流，读取图片内容
			fis = new FileInputStream(realPath);
			//获取向response缓冲区写入内容输出流对象
			ServletOutputStream sos = response.getOutputStream();
			byte[] array = new byte[100];
			int len = fis.read(array);
			while(len!=-1){
				sos.write(array, 0, len);
				len = fis.read(array);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally{
				fis=null;
			}
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
