package com.pet.care;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.MemberDto;
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
//		return "/survey/surveyForm2";
		return "/survey/surveyForm";
	}
	
	@RequestMapping(value="/surveyDetail.do", method=RequestMethod.GET)
	public String surveyDetail(@RequestParam Map<String, Object> map, Model model) {
		logger.info("SurveyController : surveyDetail 설문 폼 상세 페이지", map);
		SurveyDto sDto = (SurveyDto)iService.surveyDetail(map);
		model.addAttribute("detail",sDto);
		return "/survey/surveyDetail";
	}
	
	@RequestMapping(value="/insertSurveyForm.do", method = RequestMethod.POST)
	@ResponseBody
	public String insertSurveyForm(@RequestParam Map<String, Object> map, Model model) {
		logger.info("SurveyController : insertsurveyForm 설문 폼 등록 - {}", map);
		
		boolean isc = iService.insertSurveyForm(map);
		
		System.out.println("=============================="+map.get("seq"));
		int seq = (int)map.get("seq");
		if(isc) {
			return String.valueOf(seq);
		}else {
			return "redirect:/error/error.do";
		}
	} 
	
	@RequestMapping(value="/updateDateForm.do", method=RequestMethod.POST)
	public String updateDateForm(@RequestParam Map<String, Object> map, HttpServletRequest request) {
		logger.info("SurveyController : updateDateForm 설문 폼 배포기간 설정 - {}", map);
		String seq = request.getParameter("seq");
		map.put("seq", seq);
		boolean isc = iService.updateDateForm(map);
		if(isc) {
			return "redirect:/survey/adminSurveyList.do";
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	@RequestMapping(value="/delflagForm.do", method=RequestMethod.POST)
	public String delflagForm(String[] chkVal) {
		logger.info("SurveyController : delflagForm 설문 폼 다중 삭제 - {}", Arrays.toString(chkVal));
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", chkVal);
		int n = iService.delflagForm(map);
		logger.info("SurveyController 설문 폼 다중 삭제 : {}", n);
		return "redirect:/survey/adminSurveyList.do";
	}
	
	
	@RequestMapping(value="/compareStartDate.do",method=RequestMethod.GET)
	@ResponseBody
	public String compareStartDate(@RequestParam Map<String, Object> map) {
		logger.info("SurveyController : compareStartDate 설문 시작일 설정 - {}", map);
		boolean isc = iService.compareStartDate(map);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="/compareEndDate.do",method=RequestMethod.GET)
	@ResponseBody
	public String compareEndDate(@RequestParam Map<String, Object> map) {
		logger.info("SurveyController : compareStartDate 설문 마감일 설정 - {}", map);
		boolean isc = iService.compareEndDate(map);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="/userSurveySubmit.do", method = RequestMethod.POST)
	@ResponseBody
	public String userSurveySubmit(@RequestParam Map<String, Object> map,HttpSession session) {
		logger.info("SurveyController : userSurveySubmit (사용자) 설문 폼 제출 - {}", map);

		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String responser = mDto.getEmail().split("@")[0];
		map.put("responser", responser);
				
		boolean isc = iService.userSurveySubmit(map);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="/userSurveyList.do", method = RequestMethod.GET)
	public String userSurveyList(Model model) {
		logger.info("SurveyController : userSurveyList 설문 폼 리스트 조회");
//		List<SurveyDto> list = iService.adminSurveyList();
//		model.addAttribute("userSurveyList",list);
		return "/survey/userSurveyList";
	}
	
//	public String insertResponser(@RequestParam Map<String, Object> map, HttpSession session) {
//		logger.info("SurveyController : insertResponser 사용자 설문 폼 작성 {}", map);
//		MemberDto mDto = (MemberDto)session.getAttribute("member");
//		String responser = mDto.getEmail().split("@")[0];
//		System.out.println("responser 확인 =============="+responser);
//		int n = iService.checkEmptyResponser(map);
//		if(n>0) {
//			
//		}
//		
//		
//		
//		return "";
//	}
}