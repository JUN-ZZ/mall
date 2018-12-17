package com.mall.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码问题
		//文件内容是不参与编解码的，除非在流中处理编解码
		//在过滤器中只是把map的键值对进行了编解码
		//乱码问题，fileUpload拿到的数据流与tomcat里的不相干，所以设置setContentType()不起作用
		
		ServletContext sc = request.getServletContext();
		//临时文件夹的路径,用作缓冲区
		String tempPath = sc.getRealPath("/temp");//相对于webContent目录下
		//服务器保存上传文件的路径
		String uploadPath = sc.getRealPath("/upload");
		
		
		DiskFileItemFactory factory =
				new DiskFileItemFactory(1024*5, new File(tempPath));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		if(!fileUpload.isMultipartContent(request)){
			throw new RuntimeException("请使用正确的文件上传表单");
		}
		
		//解决文件名乱码问题
		fileUpload.setHeaderEncoding("utf-8");
		
		//设置一个上传文件的大小
		fileUpload.setFileSizeMax(1024*1024);//单位为一字节//1mb
		//设置一次上传请求中所有文件的总大小上限
		fileUpload.setSizeMax(1024*1024*5);//5mb
		try {
			//实际解析本次文件上传的内容，
			//将每个表单项封装成一个对象
			List<FileItem> list = fileUpload.parseRequest(request);
			if(list!=null){
				for(FileItem item:list){
					//普通表单项
					if(item.isFormField()){
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						System.out.println("key="+name+",value="+value);
					}else{
						//文件名
						String filename = item.getName();
						if(filename.contains("\\")){
							//ie浏览器的bug
							filename = filename.substring(filename.lastIndexOf("\\")+1);
						}
						//解决文件名重复问题
						filename = UUID.randomUUID()+"_"+filename;
						//解决单个文件保存过多问题
						//使用文件名的hashcode的16进制表示
						String hexStr = Integer.toHexString(filename.hashCode());
						//不足8位补零
						while(hexStr.length()<8){
							hexStr = "0"+hexStr;
						}
						//生成中间目录
						String midPath = "/";
						for(int i=0;i<hexStr.length();i++){
							midPath+=hexStr.charAt(i)+"/";
						}
						 //最终保存文件的绝对路径
						String finalPath = sc.getRealPath("/upload"+midPath);
						//在硬盘上实际创建的多级目录
						new File(finalPath).mkdirs();
						
						//文件上传项，将文件保存在服务器上
						InputStream is = item.getInputStream();
						//创建一个将文件写入到服务器上传文件的输出流
						FileOutputStream fos = new FileOutputStream(finalPath+"/"+filename);
						byte[] array = new byte[1024];
						int len = is.read(array);
						while(len!=-1){
							fos.write(array,0,len);
							len=is.read(array);
						}
						fos.close();
						is.close();
						//删除临时文件
						item.delete();
					}
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().write("文件上传成功...");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
