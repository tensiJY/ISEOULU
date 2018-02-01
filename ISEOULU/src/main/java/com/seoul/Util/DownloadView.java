package com.seoul.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
		@Override
	public void renderMergedOutputModel(Map model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			File		file = (File) model.get("downloadFile");
			setContentType("application/download; UTF-8");
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		String userAgent = request.getHeader("User-Agent");
		 boolean ie = userAgent.indexOf("MSIE") > -1;
		 String		fileName = file.getName();
		 if(ie == true) {
			 fileName = URLEncoder.encode(file.getName(), "UTF-8");
		 }
		 else {
			 System.out.println(fileName);
			 fileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");
			 System.out.println(fileName);
		 }
		
		 response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		 response.setHeader("Content-Transfer-Encoding", "binary");
		 OutputStream out = response.getOutputStream();
		 FileInputStream	fin = new FileInputStream(file);
		 try {
			 FileCopyUtils.copy(fin, out);
		 }
		 catch(Exception e) {
			 System.out.println("다운로드 에러 = " + e);
		 }
		 finally {
			 try {
				 fin.close();
				 out.close();
			 }
			 catch(Exception e) {}
		 }
	}
	
}






