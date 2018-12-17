package com.mall.backend.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mall.domain.Prod;
import com.mall.service.ProdService;
import com.mall.util.BaseFactory;

/**
 * Servlet implementation class AddProdServlet
 */
@WebServlet("/AddProdServlet")
public class AddProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		
		String temp = "/WEB-INF/temp";
		String upload = "WEB-INF/upload";
		String tempPath = sc.getRealPath(temp);
		String imgurl = null;//用来存放服务器上的url
		
		//1.接收请求参数
		DiskFileItemFactory factory =
		new DiskFileItemFactory(1024,new File(tempPath));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		if(!fileUpload.isMultipartContent(request)){
			throw new RuntimeException("请使用正确的文件上传表单");
		}
		//设置单个文件大小上限
		fileUpload.setFileSizeMax(1024*1024);//1m
		//总文件大小
		fileUpload.setSizeMax(1024*1024*5);//5m
		
		//处理文件名乱码问题
		fileUpload.setHeaderEncoding(encode);
		//临时存放请求参数的map集合
		Map<String,String> paramMap = new HashMap<String,String>();
		//实际解析请求参数
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			if(list!=null){
				for(FileItem item:list){
					if(item.isFormField()){//普通表单项
						System.out.println("key="+item.getFieldName()+",value="+item.getString(encode));
						paramMap.put(item.getFieldName(), item.getString(encode));
					}else{//文件上传项
						//获取文件名
						String fileName = item.getName();
						//ieBUG
						if(fileName.contains("\\")){
							fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
						}
						//文件名重复问题
						fileName = UUID.randomUUID()+"_"+fileName;
						//单个文件夹过多问题，利用文件名的hashcode的16进制表示，生成多级目录
						String hexStr = Integer.toHexString(fileName.hashCode());
						while(hexStr.length()<8){
							hexStr = "0"+hexStr;
						}
						//生成多级目录
						String midPath = "/";
						for(int i=0;i<hexStr.length();i++){
							midPath += hexStr.charAt(i)+"/";
						}
						imgurl = upload+midPath+fileName;
						String finalPath = sc.getRealPath(upload+midPath);
						new File(finalPath).mkdirs();
						//将文件写入到服务器当中
						InputStream is = item.getInputStream();//图片的输入流
						FileOutputStream fos = new FileOutputStream(finalPath+"/"+fileName);
						byte[] bs = new byte[100];
						int len = is.read(bs);
						while(len!=-1){
							fos.write(bs,0,len);
							len = is.read(bs);
						}
						//关闭流
						is.close();
						fos.close();
						//删除临时文件
						item.delete();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.表单验证（略）
		//3.执行逻辑
		ProdService prodService = BaseFactory.getFactory().getInstance(ProdService.class);
		Prod prod = new Prod();
		prod.setName(paramMap.get("name"));
		prod.setPrice(Double.parseDouble(paramMap.get("price")));
		prod.setCname(paramMap.get("cname"));
		prod.setPnum(Integer.parseInt(paramMap.get("pnum")));
		prod.setImgurl(imgurl);
		prod.setDescription(paramMap.get("description"));
		// 调用ProdService的方法，添加商品信息到数据库
		boolean flag = prodService.addPord(prod);
		//4.根据结果执行返回视图
		if(flag==true){
			response.getWriter().write("商品添加成功");
			response.setHeader("refresh", "3;url="+request.getContextPath()
			+"/backend/_right.jsp");
		}else{
			request.setAttribute("errMsg", "商品添加失败，请稍后重试");
			request.getRequestDispatcher("/backend/manageAddProd.jsp").forward(request,response);
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
