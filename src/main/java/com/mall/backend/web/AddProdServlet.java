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
		String imgurl = null;//������ŷ������ϵ�url
		
		//1.�����������
		DiskFileItemFactory factory =
		new DiskFileItemFactory(1024,new File(tempPath));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		if(!fileUpload.isMultipartContent(request)){
			throw new RuntimeException("��ʹ����ȷ���ļ��ϴ���");
		}
		//���õ����ļ���С����
		fileUpload.setFileSizeMax(1024*1024);//1m
		//���ļ���С
		fileUpload.setSizeMax(1024*1024*5);//5m
		
		//�����ļ�����������
		fileUpload.setHeaderEncoding(encode);
		//��ʱ������������map����
		Map<String,String> paramMap = new HashMap<String,String>();
		//ʵ�ʽ����������
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			if(list!=null){
				for(FileItem item:list){
					if(item.isFormField()){//��ͨ����
						System.out.println("key="+item.getFieldName()+",value="+item.getString(encode));
						paramMap.put(item.getFieldName(), item.getString(encode));
					}else{//�ļ��ϴ���
						//��ȡ�ļ���
						String fileName = item.getName();
						//ieBUG
						if(fileName.contains("\\")){
							fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
						}
						//�ļ����ظ�����
						fileName = UUID.randomUUID()+"_"+fileName;
						//�����ļ��й������⣬�����ļ�����hashcode��16���Ʊ�ʾ�����ɶ༶Ŀ¼
						String hexStr = Integer.toHexString(fileName.hashCode());
						while(hexStr.length()<8){
							hexStr = "0"+hexStr;
						}
						//���ɶ༶Ŀ¼
						String midPath = "/";
						for(int i=0;i<hexStr.length();i++){
							midPath += hexStr.charAt(i)+"/";
						}
						imgurl = upload+midPath+fileName;
						String finalPath = sc.getRealPath(upload+midPath);
						new File(finalPath).mkdirs();
						//���ļ�д�뵽����������
						InputStream is = item.getInputStream();//ͼƬ��������
						FileOutputStream fos = new FileOutputStream(finalPath+"/"+fileName);
						byte[] bs = new byte[100];
						int len = is.read(bs);
						while(len!=-1){
							fos.write(bs,0,len);
							len = is.read(bs);
						}
						//�ر���
						is.close();
						fos.close();
						//ɾ����ʱ�ļ�
						item.delete();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.����֤���ԣ�
		//3.ִ���߼�
		ProdService prodService = BaseFactory.getFactory().getInstance(ProdService.class);
		Prod prod = new Prod();
		prod.setName(paramMap.get("name"));
		prod.setPrice(Double.parseDouble(paramMap.get("price")));
		prod.setCname(paramMap.get("cname"));
		prod.setPnum(Integer.parseInt(paramMap.get("pnum")));
		prod.setImgurl(imgurl);
		prod.setDescription(paramMap.get("description"));
		// ����ProdService�ķ����������Ʒ��Ϣ�����ݿ�
		boolean flag = prodService.addPord(prod);
		//4.���ݽ��ִ�з�����ͼ
		if(flag==true){
			response.getWriter().write("��Ʒ��ӳɹ�");
			response.setHeader("refresh", "3;url="+request.getContextPath()
			+"/backend/_right.jsp");
		}else{
			request.setAttribute("errMsg", "��Ʒ���ʧ�ܣ����Ժ�����");
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
