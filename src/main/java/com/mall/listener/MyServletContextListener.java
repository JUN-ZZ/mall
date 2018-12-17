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
    	//ͨ���¼����󣬻�ȡ�¼�Դ������
    	ServletContext sc = sce.getServletContext();
    	//��ȡ��ǰwebӦ��ӳ�������·��
    	String path = sc.getContextPath();
    	//������·����ӵ�ServletContext��������
    	sc.setAttribute("app", path);
    	System.out.println(sc.getAttribute("app"));
    	
    }
	
}
