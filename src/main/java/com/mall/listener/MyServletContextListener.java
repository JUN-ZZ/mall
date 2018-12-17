package com.mall.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    	//通过事件对象，获取事件源的引用
    	ServletContext sc = sce.getServletContext();
    	//获取当前web应用映射的虚拟路径
    	String path = sc.getContextPath();
    	//将虚拟路径添加到ServletContext作用域当中
    	sc.setAttribute("app", path);
    	System.out.println(sc.getAttribute("app"));
    	
    }
	
}
