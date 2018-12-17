package com.mall.util;

public class WebUtils {
	
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		return false;
	}
}
