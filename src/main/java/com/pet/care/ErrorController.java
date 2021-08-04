package com.pet.care;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping(value = "/error403.do", method = RequestMethod.GET)
	public String error403(Model model) {
		model.addAttribute("code", "403");
		model.addAttribute("text", "세션이 만료되었습니다.<br>다시 로그인 하세요.");
		return "error/error";
	}

	@RequestMapping(value = "/error404.do", method = RequestMethod.GET)
	public String error404(Model model) {
		model.addAttribute("code", "404");
		model.addAttribute("text", "페이지를 찾을 수 없습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/error500.do", method = RequestMethod.GET)
	public String error500(Model model) {
		model.addAttribute("code", "500");
		model.addAttribute("text", "서버에 문제가 발생했습니다.<br>관리자에게 문의하세요.");
		return "error/error";
	}

	@RequestMapping(value = "/error.do", method = RequestMethod.GET)
	public String error(Model model) {
		return "error/error";
	}

}
