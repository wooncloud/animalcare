package com.pet.care;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.care.dto.SurveyDto;
import com.pet.care.model.service.survey.ISurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISurveyService iService;
	
	@RequestMapping(value="/adminSurveyList.do", method = RequestMethod.GET)
	public String adminSurveyList(Model model) {
		logger.info("SurveyController : adminSurveyList 설문 폼 리스트 조회");
		List<SurveyDto> list = iService.adminSurveyList();
		model.addAttribute("adminSurveyList",list);
		return "/survey/adminSurveyList";
	}
	
	@RequestMapping(value="/surveyForm.do", method = RequestMethod.GET)
	public String surveyForm() {
		logger.info("SurveyController : surveyForm 설문 폼 작성 페이지");
		return "/survey/surveyForm";
	}
	
	@RequestMapping(value="/insertSurveyForm.do", method = RequestMethod.POST)
	public String insertSurveyForm(SurveyDto sDto) {
		logger.info("SurveyController : insertsurveyForm 설문 폼 등록 - {}");

		return "/survey/insertSurveyForm";
	}
}