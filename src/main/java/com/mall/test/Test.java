package com.mall.test;

import java.util.UUID;

public class Test {

	public static void main(String[] args) {
		
		for(int i=0;i<5;i++){
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid);
		}
		
		String str = "b2d24ad4b6e2c585cc02bf21419db0db";
		System.out.println(str.length());
		
		
	}
	
}
