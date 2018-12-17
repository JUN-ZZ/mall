package com.mall.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Utils {
	
	public static String md5(String plainText){
		//�������ĵ��ֽ�����
		byte[] secretBytes = null;
		try{
			//MD5�����㷨���ڶ����ⳤ�ȵ����ļ��ܺ�
			//��������������ݣ�128λ�����Ƴ��ȣ����㷨�ǵ���ģ���ϢժҪ������ǹ�ϣֵ
			//�ֽڳ�����16 
			secretBytes = 
		MessageDigest.getInstance("md5").digest(plainText.getBytes());
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException("û��MD5�����㷨");
		}
		//��128λ���ݣ�ת��λ32λ����
		String md5code = new BigInteger(1,secretBytes).toString(16);
		//������Ĳ���32λ���������ַ���ǰ��0������32λ
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
//		//���ص���16���ȵ��ֽ�����
//		byte[] bs1 = MessageDigest.getInstance("md5").digest("1234".getBytes());
//		byte[] bs2 = MessageDigest.getInstance("md5").digest("1234".getBytes());
//		System.out.println(Arrays.toString(bs1));
//		System.out.println(Arrays.toString(bs2));
//		
//		
//	}
//	
	
}
