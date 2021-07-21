package com.pet.care;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pet.care.dto.UserDto;
import com.pet.care.model.service.user.IUserService;

@Controller
@RequestMapping("/login")
public class UserController {
	
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
	public String logout() {
		return "home";
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
	
	@RequestMapping(value = "/signupUser.do", method = RequestMethod.POST)
	public String signupUser(UserDto param) {
		System.out.println(param);
		
		return "redirect:/home.do";
	}

	// 병원관계자 회원 가입 화면
	@RequestMapping(value = "/signupOperForm.do", method = RequestMethod.GET)
	public String signupOperForm() {
		return "login/signupOper";
	}
}