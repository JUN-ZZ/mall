package com.mall.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * ��������Ĺ�����
 * ʵ��web���Service�㣬�Լ�service���dao��Ľ���
 * web����Ҫһ��Service���ʵ��ʱ������ֱ�Ӵ������󣬶���
 * �ӹ������ȡ��ʵ����service����Ҫdao���ʵ��ʱ��Ҳ��ͨ��
 * �������ȡ��ʵ��
 * ���ϵ���ģʽ
 */
public class BaseFactory {
	
	private Properties prop = new Properties();
	
	//˽�й�����
	private BaseFactory(){
		//��ȡ�������
		ClassLoader loader = this.getClass().getClassLoader();
		//��ȡ�����ļ��ľ���·��
		String path = loader.getResource("config.properties").getPath();
		try {
			//��ȡ�����ļ�
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	//����ģʽ
	private static BaseFactory factory = new BaseFactory();
	public static BaseFactory getFactory(){
		return factory;
	}
	
	public <T> T getInstance(Class<T> c){
		//��ȡ�ӿڵ�ʵ������
		String name = c.getSimpleName();
		String value = prop.getProperty(name);
		try {
			//�������ļ���ָ������ص��ڴ浱��
			Class cz = Class.forName(value);
			//���ڷ��䴴������
			return (T) cz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
