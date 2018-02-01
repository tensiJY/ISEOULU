package com.seoul.Util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/*
 * 	�� Ŭ������ ���� ���ε�� �߻��ϴ� ���� ������ ó���ϱ� ���� �Լ��� ������
 * 	��ƿ��Ƽ Ŭ�����̴�.
 */
public class FileUtil {
	/*
	 * 	���� ���ε带 ������ �Լ�
	 */
	public static String upload(MultipartFile oriFile, String name, String path) {
		//	���ε带 �ǽ��� �� �̸��� �ٲ� �� �����Ƿ�
		//	�̸��� �ٲ� ����� �� �̸��� �޾ƾ� ��Ʈ�ѷ��� ����� �� �ִ�.
		
		//	oriFile			�ӽ� ����� ÷�� ������ ������ ����ϰ� �ִ� Ŭ����
		//	name, path	�ӽ� ������ ������ ��ġ�� ������ ������ �̸��� ����� ����
		
		//	���縦 �ϱ� ���� ���� ������ �̸��� �ߺ��Ǹ� �̸��� �ٲپ ���縦
		//	�ؾ��� ���̴�.
		name = renameFile(name, path);
		
		//	1.	������ ������ File Ŭ������ �����.
		File		copyFile = new File(path + "\\" + name);
		//	2.	���縦 �õ��Ѵ�.
		try {
			oriFile.transferTo(copyFile);
		}
		catch(Exception e) {
			System.out.println("���� ���ε� ���� = " + e);
		}
		return name;
	}
	/*
	 * 	���ε� �� �� ������ �̸��� �ߺ��Ǵ� ��� ó���� �Լ�
	 * 	�� ���� ���ε��� ������ �̸��� �̹� �����ϸ� �̸��� �����ؼ� ������ ��������
	 * 	�̸��� ������ �� ����
	 */
	public static String renameFile(String name, String path) {
		//	��ȯ��			����� ���� �̸�
		//	�Ķ����		������ ������ �̸��� ���� ���
		
		//	��Ģ		�̸��� �ߺ��Ǹ� �̸� �ڿ� _��ȣ�� �̿��ؼ� �̸��� �����ϵ��� �Ѵ�.
		//				��>		hong.txt		hong_1.txt�� �ٲ� �����̴�.
		int		count = 0;			//	�ڿ� ���� ��ȣ�� �����ϱ� ���� ����
		String	oriName = name;
		File		file = new File(path + "\\" + oriName);
		while(file.exists()) {
			//	�� ������ �̸��� �ٲپ �˷��־�� �Ѵ�.
			//	���	1	.�� �̿��ؼ� ���� ����� ���� ������ �и��Ѵ�.
			//			hong.txt		hong		txt		�и��Ѵ�.
			int	pos = name.lastIndexOf(".");
			String	first = name.substring(0, pos);
			String	last = name.substring(pos + 1);
			
			//			2.	�� ����� _��ȣ�� �ٿ��� �ٽ� �� �����Ѵ�.
			count++;		//	1
			first = first + "_" + count;			//	hong_1
			oriName = first + "." + last;			//	hong_1.txt
			
			//	�ٵ�
			//	�� �̸��� ���� �� �ִ�.
			//	�׷��Ƿ� �ٽ� �˻��ϵ��� ����.
			file = new File(path + "\\" + oriName);
		}
		return oriName;
	}
}
