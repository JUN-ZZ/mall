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
	
	//进行过滤器编解码
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//处理应答乱码
		response.setContentType("text/html;charset="+encode);
		//处理请求乱码
		//装饰者模式
		MyRequest req = new MyRequest((HttpServletRequest)request);
		//放行
		//传入的是装饰者对象，后续使用的都是该对象
		chain.doFilter(req, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// 通过config对象获取ServletContext对象，进而获取web.xml中配置的初始化参数
		this.encode = filterConfig.getServletContext().getInitParameter("encode");
	}
	
	/**
	 * HttpServletRequestWrapper 
	 * public class HttpServletRequestWrapper extends ServletRequestWrapper implements HttpServletRequest 
	 * @author jun
	 *对request进行装饰者模式
	 *ServletRequestWrapper 对request进行包裹
	 *HttpServletRequestWrapper 实现HttpServletRequest接口 进行相关的操作
	 *
	 */
	class MyRequest extends HttpServletRequestWrapper {
		//用来标识是否进行过编解码
		private boolean hasEncode = false;
		
		//没有默认的构造器
		//所以继承的时候要对应的父类构造器
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
			//request 请求中是以map<String,String[]> 的形式进行存放东西的
			//对该map集合中的所有value进行手动编解码
			Map<String,String[]> map = this.getRequest().getParameterMap();
			//判断是否进行编解码
			if(hasEncode==false){
				for(Entry<String,String[]> entry:map.entrySet()){
					String[] values = entry.getValue();
					if(values!=null){
						for(int i=0;i<values.length;i++){
							//获取第一个参数的值
							String value = values[i];
							try {
								value = new String(value.getBytes("iso8859-1"),encode);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//							将编解码之后的值重新存入map中
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
