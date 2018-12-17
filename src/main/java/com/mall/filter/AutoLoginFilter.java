package com.mall.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.service.UserService;
import com.mall.util.BaseFactory;
import com.mall.util.JDBCUtils;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */


public class AutoLoginFilter implements Filter {
	
	
    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*ӵ���Զ���¼cookieֱ���ض��򵽵�¼��֤��  */
		HttpServletRequest request1 = (HttpServletRequest)request;
		//��һ�η��ʣ��Ự      false�Ļ�����ʾû�лỰ�Ļ��ͻ᷵��null���еĻ����Ƿ��ص�ǰ�Ự��session
		HttpSession session = request1.getSession(false);
		if(session==null||session.getAttribute("user")==null){
			Cookie[] cs = request1.getCookies();
			String username = null;
			String password = null;
			//�ж��Ƿ����Զ���¼cookie
			if(cs!=null){
				for(Cookie c:cs){
					if("autologin".equals(c.getName())){
						/* ���ﲻ���ض���, */
						String s = URLDecoder.decode(c.getValue(), "utf-8");
						username = s.split(";")[0];	
						password = s.split(";")[1];
						break;
					}
				}
			}
			//�����ж��û��������Ƿ���ȷ
			if(username!=null&&password!=null){	
				UserService userService = BaseFactory.getFactory().getInstance(UserService.class);
				User user = null;
				try {
					user = userService.login(username, password);
					if(user!=null){
						request1.getSession().setAttribute("user", user);
					}
				} catch (MsgException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
/*				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try{
					con = JDBCUtils.getConnection();
					ps = con.prepareStatement("select username from user where username=? and password=? ");
					ps.setString(1, username);		
					ps.setString(2, password);
					rs = ps.executeQuery();
					while(rs.next()){
						request1.getSession().setAttribute("username", username);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					JDBCUtils.close(con, ps, rs);
				}*/
				
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
