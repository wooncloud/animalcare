package com.pet.care;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.comm.JsonUtil;
import com.pet.care.comm.PageUtil;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.PageDto;
import com.pet.care.dto.SurveyDto;
import com.pet.care.dto.SurveyResultDto;
import com.pet.care.model.service.survey.ISurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISurveyService iService;
	
	@RequestMapping(value="/adminSurveyList.do", method = RequestMethod.GET)
	public String adminSurveyList(@RequestParam Map<String, Object> map, Model model) {
		logger.info("SurveyController : adminSurveyList 설문 폼 리스트 조회");
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)map.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		//볼 수 있는 글의 총 갯수
		allPageCnt = iService.adminSurveyListCount();
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		map.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		map.put("last", page.getPage() * page.getCountList());
		
		
		List<SurveyDto> list = iService.adminSurveyList(map);
		model.addAttribute("adminSurveyList",list);
		model.addAttribute("page", page);

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
	
	@RequestMapping(value="/userSurveyDetail.do", method=RequestMethod.GET)
	public String userSurveyDetail(Model model) {
		logger.info("SurveyController : userSurveyDetail (사용자) 설문 폼 상세 페이지");
		SurveyDto sDto = (SurveyDto)iService.userSurveyDetail();
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
	public String userSurveySubmit(@RequestParam Map<String, Object> map, HttpSession session) {
		logger.info("SurveyController : userSurveySubmit (사용자) 설문 폼 제출 - {}", map);

		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String responser = mDto.getEmail().split("@")[0];
		map.put("responser", responser);
				
		boolean isc = iService.userSurveySubmit(map);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="/userSurveyList.do", method = RequestMethod.GET)
	public String userSurveyList(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		logger.info("SurveyController : userSurveyList 설문 폼 리스트 조회");
	
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String responser = mDto.getEmail().split("@")[0];
		map.put("responser", responser);
				
		logger.info("SurveyController : ongoingSurvey (사용자) 진행중인 설문 폼 리스트 - {}", map);
		logger.info("SurveyController : outOfDateSurvey (사용자) 날짜 지난 설문 폼 리스트 - {}", map);
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)map.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		//볼 수 있는 글의 총 갯수
		allPageCnt = iService.ongoingSurveyCount(map);
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		map.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		map.put("last", page.getPage() * page.getCountList());
		
		//페이징
		PageDto page2 = new PageDto();

		String strIdx2 = (String)map.get("page");
		if(strIdx2 == null) {
			strIdx2 = "1";
		}
		
		int idx2 = Integer.parseInt(strIdx2);
		int allPageCnt2 = 0;
		
		//볼 수 있는 글의 총 갯수
		allPageCnt2 = iService.outOfDateSurveyCount(map);
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page2, allPageCnt2);
		
		page2.setPage(idx2);
		page2.setStartPage(idx2);
		page2.setEndPage(page2.getCountPage());
		
		map.put("first2", page2.getPage() * page2.getCountList() - (page2.getCountList() - 1));
		map.put("last2", page2.getPage() * page2.getCountList());
		
		List<SurveyDto> list = iService.ongoingSurvey(map);
		List<SurveyDto> list2 = iService.outOfDateSurvey(map);
		model.addAttribute("userDoSurveyList",list);
		model.addAttribute("userEndSurveyList",list2);
		model.addAttribute("page", page);
		model.addAttribute("page2", page2);


		
		return "/survey/userSurveyList";
	}
	
	
	@RequestMapping(value="/surveyResultList.do", method=RequestMethod.GET)
	public String surveyResultList(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		logger.info("SurveyController : surveyResultList 설문 결과 리스트 - {}", map);
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)map.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		//볼 수 있는 글의 총 갯수
		allPageCnt = iService.surveyResultListCount();
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		map.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		map.put("last", page.getPage() * page.getCountList());
		
		List<SurveyDto> surveyResultList = iService.surveyResultList(map);
	
		model.addAttribute("surveyResultList",surveyResultList);
		model.addAttribute("page", page);
		
		return "/survey/surveyResultList";
	}
	
	@RequestMapping(value="/surveyResultDetail.do", method=RequestMethod.GET)
	public String surveyResultDetail(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		String seq = (String)map.get("seq");
		map.put("survey_seq", seq);
		SurveyResultDto answers = null;
		List<SurveyResultDto> surveyResultDetail = iService.surveyResultDetail(map);
		for (int i = 0; i < surveyResultDetail.size(); i++) {
			answers = surveyResultDetail.get(i);
		}
//		JsonUtil util = new JsonUtil();
//		JSONObject answer =  util.stringToJson(String.valueOf(surveyResultDetail));
//		System.out.println(answer);
		model.addAttribute("surveyResultDetail",surveyResultDetail);
		model.addAttribute("resultAnswer",answers);
		return "/survey/surveyResultDetail";
	}
	
	
	
	
	
	
}