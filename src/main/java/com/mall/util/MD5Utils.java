package com.mall.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Utils {
	
	public static String md5(String plainText){
		//保存密文的字节数据
		byte[] secretBytes = null;
		try{
			//MD5加密算法，在对任意长度的明文加密后，
			//都输出定长的数据，128位二进制长度，该算法是单向的，消息摘要算出的是哈希值
			//字节长度是16 
			secretBytes = 
		MessageDigest.getInstance("md5").digest(plainText.getBytes());
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException("没有MD5加密算法");
		}
		//将128位数据，转化位32位数据
		String md5code = new BigInteger(1,secretBytes).toString(16);
		//如果密文不足32位定长，在字符串前加0，补足32位
		for(int i = 0;i<32-md5code.length();i++){
			md5code = "0"+md5code;
		}
		return md5code;
		
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		byte[] bs = {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0};
//		byte[] md5code= MessageDigest.getInstance("md5").digest(bs);
//		System.out.println(md5code.length);
//		for(int i=0;i<md5code.length;i++){
//			System.out.print(Integer.toBinaryString(i)+",");
//		}
//		System.out.println(Arrays.toString(md5code));
//		String code = new BigInteger(1,md5code).toString(16);
//		System.out.println(code);
//		System.out.println(code.length());
//	}
//	
//	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		//返回的是16长度的字节数组
//		byte[] bs1 = MessageDigest.getInstance("md5").digest("1234".getBytes());
//		byte[] bs2 = MessageDigest.getInstance("md5").digest("1234".getBytes());
//		System.out.println(Arrays.toString(bs1));
//		System.out.println(Arrays.toString(bs2));
//		
//		
//	}
//	
	
}
