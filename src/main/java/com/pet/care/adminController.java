package com.pet.care;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.comm.Util;
import com.pet.care.dto.OperatorDto;
import com.pet.care.dto.SurveyDto;
import com.pet.care.model.service.survey.ISurveyService;
import com.pet.care.model.service.user.IUserService;

@Controller
@RequestMapping("/admin")
public class adminController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserService userService;
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private JavaMailSender mailSender;

	// 관리자 콘솔
	@RequestMapping(value = "/adminConsole.do", method = RequestMethod.GET)
	public String adminConsole(Model model) {
		logger.info("[adminConsole] : 관리자 콘솔");

		// 가입대기중인 병원관계자 리스트
		List<OperatorDto> waitOpers = userService.grantWaitList();
		model.addAttribute("operList", waitOpers);
		
		List<SurveyDto> list = surveyService.adminSurveyList();
		model.addAttribute("adminSurveyList",list);

		return "admin/adminConsole";
	}
	
	// 병원관계자 권한
	@RequestMapping(value = "/adminGrant.do", method = RequestMethod.GET)
	public String adminGrant(Model model) {
		logger.info("[adminGrant] : 병원관계자 권한");
		
		// 가입대기중인 병원관계자 리스트
		List<OperatorDto> waitOpers = userService.grantWaitList();
		model.addAttribute("operList", waitOpers);
		
		return "admin/adminGrant";
	}

	// 권한 부여
	@RequestMapping(value = "/grantOper.do", method = RequestMethod.POST)
	@ResponseBody
	public String grantOper(@RequestParam Map<String, Object> param) {
		boolean isc = false;
		isc = userService.grantOper(param);

		if (isc) {
			String flag = (String) param.get("approvalflag");
			String addr = (String) param.get("email");
			grantOperMail(flag, addr);
			
			return "success";
		} else {
			return "error";
		}
	}
	
	// 
	private void grantOperMail(String flag, String addr) {
		String subject;
		StringBuffer content = new StringBuffer();
		
		if(flag.equals("Y")) {
			subject = "[PET CARE] 계정이 승인 되었습니다.";
			content.append("안녕하세요. PET CARE 관리자 입니다.<br>");
			content.append("고객님의 계정이 승인 되었습니다.<br>");
			content.append("PET CARE 서비스를 찾아 주셔서 감사합니다.<br>");
			content.append("- PET CARE 관리자 -");
		} else {
			subject = "[PET CARE] 계정이 거부 되었습니다.";
			content.append("안녕하세요. PET CARE 관리자 입니다.<br>");
			content.append("고객님의 계정이 거부 되었습니다.<br>");
			content.append("입력하신 계정 정보를 검토중에 부적절한 내용이 발견 되었습니다.<br>");
			content.append("자세한 사항은 관리자에게 문의해 주세요.<br>");
			content.append("감사합니다.<br>");
			content.append("- PET CARE 관리자 -");
		}
		
		Util.EmailSend(mailSender, addr, subject, content.toString());
	}

}
