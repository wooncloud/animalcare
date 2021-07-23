package com.pet.care;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.care.dto.OperatorDto;
import com.pet.care.model.service.user.IUserService;

@Controller
@RequestMapping("/admin")
public class adminController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserService userService;
	
	// 로그인 화면
	@RequestMapping(value = "/adminConsole.do", method = RequestMethod.GET)
	public String adminConsole(Model model) {
		logger.info("[adminConsole] : 관리자 콘솔");
		
		// 가입대기중인 병원관계자 리스트
		List<OperatorDto> waitOpers = userService.grantWaitList();
		model.addAttribute("operList", waitOpers);
		
		return "admin/adminConsole";
	}
	
}
