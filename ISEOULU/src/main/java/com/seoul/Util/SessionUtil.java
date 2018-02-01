package com.seoul.Util;

import javax.servlet.http.HttpSession;

/*
 * 	�� Ŭ������ ���ǿ� ���õ� ������ ó���ϱ� ���� ��ƿ��Ƽ Ŭ�����̴�.
 */
public class SessionUtil {
	/*
	 * 	�α��� ���θ� �˷��ִ� �Լ�
	 */
	public static boolean isLogin(HttpSession session) {
		//	����
		//		���ǿ� UID��� Ű���� �����Ͱ� �ִ��� Ȯ���ؼ� �� ����� �˷��ش�.
		String	id = (String) session.getAttribute("UID");

		boolean	isNull = StringUtil.isNull(id);
		//	���̸� true, ���� �ƴϸ� false ��ȯ�ϹǷ�...
		
		//	�� �Լ��� �α����� ������ true, �α����� �������� false�� �����Ѵ�.
		return !isNull;
	}
	
	/*
	 * 	�α��� id�� �˾Ƴ��� �Լ�
	 */
	public static String getLoginId(HttpSession session) {
		return (String) session.getAttribute("UID");
	}
}









