package com.pet.care;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.UserDto;
import com.pet.care.model.service.user.IUserService;

@Controller
@RequestMapping("/login")
public class UserController {
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm() {
		return "login/login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> param) {
		String email = param.get("email");
		String pw = param.get("password");
		
		System.out.println(email);
		System.out.println(pw);
		
		return "login/login";
	}

	// 로그아웃
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		Object obj = session.getAttribute("member");
		if (obj != null) {
			session.removeAttribute("member");
		}

		logger.info("[logout] : 로그아웃 요청" + obj);

		return "redirect:/home.do";
	}

	// 회원가입 동의 화면
	@RequestMapping(value = "/signupAgree.do", method = RequestMethod.GET)
	public String signupAgree() {
		return "login/signupAgree";
	}

	// 회원 종류 선택 화면
	@RequestMapping(value = "/signupSelect.do", method = RequestMethod.GET)
	public String signupSelect() {
		return "login/signupSelect";
	}

	// 사용자 회원 가입 화면
	@RequestMapping(value = "/signupUserForm.do", method = RequestMethod.GET)
	public String signupUserForm() {
		return "login/signupUser";
	}
	
	@RequestMapping(value = "/emailDupl.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean emailDuplCheck(String email) {
		boolean result = false;
		
		result = userService.emailDuplCheck(email);
		
		return result;
	}
	
	@RequestMapping(value = "/signupUser.do", method = RequestMethod.POST)
	public String signupUser(UserDto param) {
		logger.info("[signupUser] : 회원가입 - {}", param);
		boolean isc = userService.insertUser(param);
		
		if(isc) {
			return "redirect:/home.do";
		}
		else {
			return "redirect:error/error500.do";
		}
	}

	// 병원관계자 회원 가입 화면
	@RequestMapping(value = "/signupOperForm.do", method = RequestMethod.GET)
	public String signupOperForm() {
		return "login/signupOper";
	}
}