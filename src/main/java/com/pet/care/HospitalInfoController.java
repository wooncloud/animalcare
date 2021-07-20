package com.pet.care;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pet.care.comm.PageUtil;
import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.PageDto;
import com.pet.care.model.service.code.ICodeService;
import com.pet.care.model.service.hospital.IHospitalInfoService;


@Controller
@RequestMapping("/hospital")
public class HospitalInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private IHospitalInfoService hiService;
	
	@Autowired
	private ICodeService codeService;
	
	@RequestMapping(value = "searchHospital.do", method = RequestMethod.GET)
	public String searchHospital(@RequestParam Map<String, Object> param, Model model) {
		logger.info("[searchHospital] :  병원찾기 페이지로 이동");
		
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
		
		model.addAttribute("lists", lists);
		model.addAttribute("page", page);
		
		return "hospital/searchHospital";
	}
}
