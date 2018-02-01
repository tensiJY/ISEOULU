package com.seoul.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.seoul.Util.FileUtil;

@Controller
public class CommonController {

	
	@RequestMapping("/Common/ImageUploadForm")
	public ModelAndView imageUploadForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Common/ImageUploadForm");
		return mv;
	}
	
		@RequestMapping("/Common/ImageUploadProc")
	public ModelAndView imageUploadProc(@RequestParam("files") MultipartFile files, HttpSession session) {
		String path = session.getServletContext().getRealPath("seupload");
		String	name = files.getOriginalFilename();
		
		String saveName = FileUtil.upload(files, name, path);
		ModelAndView		mv = new ModelAndView();
		mv.addObject("PATH", path);
		mv.addObject("NAME", saveName);
		mv.setViewName("Common/ImageUploadProc");
		return mv;
		
	}
}











