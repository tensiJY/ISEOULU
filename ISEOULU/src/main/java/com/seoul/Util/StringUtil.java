package com.seoul.Util;
/*
 * 	���ڿ� ó���� �� �� �ʿ��� ����� �˷��ִ� ��ƿ��Ƽ Ŭ����
 */
public class StringUtil {
	//	��>		\r\n  -->  <br>
	//			���ڿ��� �� ��� ������ ���̰� "..."�� ��ߵǴ� ���
	
	/*
	 * 	��Ʈ���� �����Ͱ� �����ϴ��� ���θ� �˷��ִ� ����� ����� �Լ�
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
