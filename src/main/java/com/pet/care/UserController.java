package com.pet.care;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value = "login/loginPage.do", method = RequestMethod.GET)
	public String loginPage() {
		return "login/login";
	}
	
}
