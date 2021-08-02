package com.pet.care;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.CodeDto;
import com.pet.care.model.service.code.ICodeService;
import com.pet.care.model.service.report.IReportService;

@Controller
@RequestMapping("/report")
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICodeService codeService;
	@Autowired
	private IReportService reportService; 
	
	@RequestMapping(value = "/insertForm.do", method = RequestMethod.GET)
	public String insertForm(@RequestParam Map<String, Object> param, Model model) {
		logger.info("insertForm - {}", param);
		// 신고자 email, 대상 email, 진료기록 일자

		// 신고자가 누구냐에 따라 가져오는 코드가 달라짐
		List<CodeDto> codes = codeService.categoryCodeSelect("RPU");

		model.addAttribute("info", param);
		model.addAttribute("codes", codes);

		return "report/insertForm";
	}
}
