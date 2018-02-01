package com.seoul.Controller;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.seoul.Service.NaverLoginBO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	//�α��� ù ȭ�� ��û �޼ҵ�
	@RequestMapping(value = "Login/Login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("���̹�:" + naverAuthUrl);
		
		//���̹� 
		model.addAttribute("url", naverAuthUrl);

		/* ������ ���� URL�� View�� ���� */
		return "Login/Login";
	}

	//���̹� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "Login/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //�α��� ����� ������ �о�´�.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);

        /* ���̹� �α��� ���� ������ View ȣ�� */
		int startPos = apiResult.indexOf("email")+8;
		int endPos = apiResult.indexOf("@");
		String loginId = apiResult.substring(startPos, endPos);
		session.setAttribute("UID", loginId);
		return "/Login/callback";
		
	}
		@RequestMapping ("/Login/Logout")
		public ModelAndView Logout(HttpSession session){
			ModelAndView	mv = new ModelAndView();
			session.removeAttribute("UID");
			RedirectView	rv = new RedirectView("../Main/Main.do");
			mv.setView(rv);
			return mv;
			
		}
	
}
