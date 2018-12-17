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
		//��������
		//�ļ������ǲ���������ģ����������д�������
		//�ڹ�������ֻ�ǰ�map�ļ�ֵ�Խ����˱����
		//�������⣬fileUpload�õ�����������tomcat��Ĳ���ɣ���������setContentType()��������
		
		ServletContext sc = request.getServletContext();
		//��ʱ�ļ��е�·��,����������
		String tempPath = sc.getRealPath("/temp");//�����webContentĿ¼��
		//�����������ϴ��ļ���·��
		String uploadPath = sc.getRealPath("/upload");
		
		
		DiskFileItemFactory factory =
				new DiskFileItemFactory(1024*5, new File(tempPath));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		if(!fileUpload.isMultipartContent(request)){
			throw new RuntimeException("��ʹ����ȷ���ļ��ϴ���");
		}
		
		//����ļ�����������
		fileUpload.setHeaderEncoding("utf-8");
		
		//����һ���ϴ��ļ��Ĵ�С
		fileUpload.setFileSizeMax(1024*1024);//��λΪһ�ֽ�//1mb
		//����һ���ϴ������������ļ����ܴ�С����
		fileUpload.setSizeMax(1024*1024*5);//5mb
		try {
			//ʵ�ʽ��������ļ��ϴ������ݣ�
			//��ÿ�������װ��һ������
			List<FileItem> list = fileUpload.parseRequest(request);
			if(list!=null){
				for(FileItem item:list){
					//��ͨ����
					if(item.isFormField()){
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						System.out.println("key="+name+",value="+value);
					}else{
						//�ļ���
						String filename = item.getName();
						if(filename.contains("\\")){
							//ie�������bug
							filename = filename.substring(filename.lastIndexOf("\\")+1);
						}
						//����ļ����ظ�����
						filename = UUID.randomUUID()+"_"+filename;
						//��������ļ������������
						//ʹ���ļ�����hashcode��16���Ʊ�ʾ
						String hexStr = Integer.toHexString(filename.hashCode());
						//����8λ����
						while(hexStr.length()<8){
							hexStr = "0"+hexStr;
						}
						//�����м�Ŀ¼
						String midPath = "/";
						for(int i=0;i<hexStr.length();i++){
							midPath+=hexStr.charAt(i)+"/";
						}
						 //���ձ����ļ��ľ���·��
						String finalPath = sc.getRealPath("/upload"+midPath);
						//��Ӳ����ʵ�ʴ����Ķ༶Ŀ¼
						new File(finalPath).mkdirs();
						
						//�ļ��ϴ�����ļ������ڷ�������
						InputStream is = item.getInputStream();
						//����һ�����ļ�д�뵽�������ϴ��ļ��������
						FileOutputStream fos = new FileOutputStream(finalPath+"/"+filename);
						byte[] array = new byte[1024];
						int len = is.read(array);
						while(len!=-1){
							fos.write(array,0,len);
							len=is.read(array);
						}
						fos.close();
						is.close();
						//ɾ����ʱ�ļ�
						item.delete();
					}
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().write("�ļ��ϴ��ɹ�...");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
