package com.seoul.Util;
/*
 * 	문자열 처리를 할 때 필요한 기능을 알려주는 유틸리티 클래스
 */
public class StringUtil {
	//	예>		\r\n  -->  <br>
	//			문자열이 긴 경우 적당히 줄이고 "..."을 써야되는 경우
	
	/*
	 * 	스트링에 데이터가 존재하는지 여부를 알려주는 기능을 담당할 함수
	 */
	public static boolean isNull(String data) {
		if(data == null || data.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String change(String data){
		String temp = data;	
		
		int check1 = data.indexOf("(");
		int check2 = data.indexOf("[");
		
		if(check1== -1 && check2== -1){
			
			
		}else if(check1 != -1){
			
			temp = data.substring(0, check1);
			
			
		}else if(check2 != -1){
			
			temp = data.substring(0, check2);
			
		}
		
		return temp;
	}
	
} 
