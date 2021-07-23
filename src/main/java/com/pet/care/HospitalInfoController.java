package com.pet.care;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pet.care.comm.JsonUtil;
import com.pet.care.comm.PageUtil;
import com.pet.care.comm.Util;
import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.PageDto;
import com.pet.care.model.service.code.ICodeService;
import com.pet.care.model.service.hospital.IHospitalInfoService;
import com.pet.care.model.service.hospital.IHospitalScheduleService;


@Controller
@RequestMapping("/hospital")
public class HospitalInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private IHospitalInfoService hiService;
	
	@Autowired IHospitalScheduleService hsService;
	
	@Autowired
	private ICodeService codeService;
	
	@RequestMapping(value = "PetCare/home.do", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/insertHospital.do", method = RequestMethod.GET)
	public String insertHospital(Model model) {
		logger.info("[insertHospital] : 병원 정보 등록 페이지 이동 요청 : ");
		
		//진료항목 리스트
		List<CodeDto> petlist =hiService.petTypeList();
		
		model.addAttribute("petlist", petlist);
		
		return "hospital/insertHospital";
	}
	
	@RequestMapping(value = "/searchHospital.do", method = RequestMethod.GET)
	public String searchHospital(@RequestParam Map<String, Object> param, Model model) {
		logger.info("[searchHospital] - {} :  병원찾기 페이지로 이동", model);
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)param.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		allPageCnt = hiService.hospitalCount(param);
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		param.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		param.put("last", page.getPage() * page.getCountList());
		
		//볼수 있는 글의 총 갯수
		List<HospitalJoinDto> lists = hiService.hospitalList(param);
		
		//진료항목 리스트
		List<CodeDto> petlist =hiService.petTypeList();
		
		// 서울 25개구 리스트
		List<CodeDto> loc = codeService.categoryCodeSelect("LOC");
		JSONArray locJson = JsonUtil.CommonCodeJson(loc);
		
		model.addAttribute("lists", lists);
		model.addAttribute("page", page);
		model.addAttribute("petlist", petlist);
		model.addAttribute("loc", loc);
		model.addAttribute("locJson", locJson);
		
		return "hospital/searchHospital";
	}
	
	@RequestMapping(value = "/detailHospital.do", method = RequestMethod.GET)
	public String detailHospital(int seq, Model model) {
		logger.info("[detailHospital.] - {} :  병원정보 상세보기 페이지로 이동", seq);
		
		HospitalJoinDto dto = hiService.detailHospital(seq);
		model.addAttribute("dto", dto);
		
		List<CodeDto> pet = codeService.categoryCodeSelect("PET");
		JSONArray petJson = JsonUtil.CommonCodeJson(pet);
		
		model.addAttribute("pet", pet);
		model.addAttribute("petJson", petJson);
		
		return "hospital/detailHospital";
	}
	
	@RequestMapping(value = "/deleteHospital.do", method = RequestMethod.GET)
	public void deleteHospital(int seq, HttpServletResponse resp) throws IOException {
		logger.info("[deleteHospital] - {} : 병원 정보 삭제 요청 : ", seq);
		boolean isc = hiService.deleteHospital(seq);
		if(isc) {
			Util.PrintWriterMsg(resp, "삭제가 완료 되었습니다.", "PetCare/home.do");
		}else {
			Util.PrintWriterMsg(resp, "오류가 발생했습니다. 다시 시도해주세요.", "PetCare/home.do");	
		}

	}
	
	@RequestMapping(value = "/modifyHospital.do", method = RequestMethod.GET)
	public String modifyHospital(Model model) {
		logger.info("[modifyHospital] : 병원 정보 수정 페이지 이동 요청 : ");
		
		//진료항목 리스트
		List<CodeDto> petlist =hiService.petTypeList();
		
		model.addAttribute("petlist", petlist);
		
		return "hospital/modifyHospital";
	}
	
	@RequestMapping(value = "/insertHospitalSchedule.do", method = RequestMethod.GET)
	public String insertHospitalSchedule(Model model) {
		logger.info("[insertHospitalSchedule] : 병원 정보 등록 페이지 이동 요청 : ");
		
		return "hospital/insertHospitalSchedule";
	}
	
	@RequestMapping(value = "/insertSchedule.do", method = RequestMethod.POST)
	public void insertSchedule(@RequestParam Map<String, Object> param, HttpServletResponse resp) throws ParseException, IOException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		HospitalScheduleDto hsDto = new HospitalScheduleDto();
		
		//나중에 병원 seq 어디서 가져올지 확인 후 수정 필요 // 에디터에 이미지 삽입시 4000 훅넘어서 에러뜸
		hsDto.setHospital_seq(5);
		hsDto.setTitle((String)param.get("scheduleName"));
		hsDto.setSchedule(dateFormat.parse((String)param.get("scheduleDate")));
		hsDto.setCheck((String)param.get("reservationChk"));
		hsDto.setContent((String)param.get("scheduleContent"));
		
		boolean isc = hsService.insertSchedule(hsDto);
		if (isc) {
			Util.PrintWriterMsg(resp, "일정 등록이 완료 되었습니다.", "PetCare/home.do");
		} else {
			Util.PrintWriterMsg(resp, "일정 등록에 문제가 발생했습니다.", "PetCare/home.do");
		}
		
	}
	
	
	
	
	
	
	
}
