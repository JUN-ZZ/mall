package com.mall.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 用来解耦的工厂类
 * 实现web层和Service层，以及service层和dao层的解耦
 * web层需要一个Service层的实例时，不再直接创建对象，而是
 * 从工厂类获取该实例，service层需要dao层的实例时，也是通过
 * 工厂类获取该实例
 * 符合单例模式
 */
public class BaseFactory {
	
	private Properties prop = new Properties();
	
	//私有构造器
	private BaseFactory(){
		//获取类加载器
		ClassLoader loader = this.getClass().getClassLoader();
		//获取配置文件的绝对路径
		String path = loader.getResource("config.properties").getPath();
		try {
			//读取配置文件
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	//单例模式
	private static BaseFactory factory = new BaseFactory();
	public static BaseFactory getFactory(){
		return factory;
	}
	
	public <T> T getInstance(Class<T> c){
		//获取接口的实际名称
		String name = c.getSimpleName();
		String value = prop.getProperty(name);
		try {
			//将配置文件的指向类加载到内存当中
			Class cz = Class.forName(value);
			//基于反射创建该类
			return (T) cz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
