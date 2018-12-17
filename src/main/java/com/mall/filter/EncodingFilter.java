package com.mall.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter {
	@Override
	public void destroy() {

	}
	private String encode = null;
	
	//���й����������
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//����Ӧ������
		response.setContentType("text/html;charset="+encode);
		//������������
		//װ����ģʽ
		MyRequest req = new MyRequest((HttpServletRequest)request);
		//����
		//�������װ���߶��󣬺���ʹ�õĶ��Ǹö���
		chain.doFilter(req, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// ͨ��config�����ȡServletContext���󣬽�����ȡweb.xml�����õĳ�ʼ������
		this.encode = filterConfig.getServletContext().getInitParameter("encode");
	}
	
	/**
	 * HttpServletRequestWrapper 
	 * public class HttpServletRequestWrapper extends ServletRequestWrapper implements HttpServletRequest 
	 * @author jun
	 *��request����װ����ģʽ
	 *ServletRequestWrapper ��request���а���
	 *HttpServletRequestWrapper ʵ��HttpServletRequest�ӿ� ������صĲ���
	 *
	 */
	class MyRequest extends HttpServletRequestWrapper {
		//������ʶ�Ƿ���й������
		private boolean hasEncode = false;
		
		//û��Ĭ�ϵĹ�����
		//���Լ̳е�ʱ��Ҫ��Ӧ�ĸ��๹����
		public MyRequest(HttpServletRequest request) {
			super(request);
		}
		
		@Override
		public String getParameter(String name) {
			String[] array = this.getParameterMap().get(name);
			return array==null?null:array[0];
		}
		@Override
		public Map<String, String[]> getParameterMap() {
			//request ����������map<String,String[]> ����ʽ���д�Ŷ�����
			//�Ը�map�����е�����value�����ֶ������
			Map<String,String[]> map = this.getRequest().getParameterMap();
			//�ж��Ƿ���б����
			if(hasEncode==false){
				for(Entry<String,String[]> entry:map.entrySet()){
					String[] values = entry.getValue();
					if(values!=null){
						for(int i=0;i<values.length;i++){
							//��ȡ��һ��������ֵ
							String value = values[i];
							try {
								value = new String(value.getBytes("iso8859-1"),encode);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//							�������֮���ֵ���´���map��
							values[i] = value;
						}
					}
				}
				hasEncode = true;
			}
			return map;
		}
		
		@Override
		public String[] getParameterValues(String name) {
			return this.getParameterMap().get(name);
		}
	}
	
	
}
