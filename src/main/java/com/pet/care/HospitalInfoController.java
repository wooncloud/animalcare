package com.pet.care;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.PageDto;
import com.pet.care.dto.PetTypeDto;
import com.pet.care.model.service.code.ICodeService;
import com.pet.care.model.service.hospital.IHospitalInfoService;
import com.pet.care.model.service.hospital.IHospitalScheduleService;


@Controller
@RequestMapping("/hospital")
public class HospitalInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private IHospitalInfoService hiService;
	
	@Autowired 
	private IHospitalScheduleService hsService;
	
	@Autowired
	private ICodeService codeService;
	
	@RequestMapping(value = "PetCare/home.do", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/insertHospitalPage.do", method = RequestMethod.GET)
	public String insertHospitalPage(Model model) {
		logger.info("[insertHospital] : 병원 정보 등록 페이지 이동 요청  ");
		
		//진료항목 리스트
		List<CodeDto> petlist =hiService.petTypeList();
		
		model.addAttribute("petlist", petlist);
		
		return "hospital/insertHospital";
	}
	
	@RequestMapping(value = "/insertHospital.do", method = RequestMethod.POST)
	public void insertHospital(@RequestParam Map<String, Object> param, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		logger.info("[insertHospital] - {} : 병원 정보 입력 요청 ", param);
		HospitalInfoDto hiDto = new HospitalInfoDto();
		
		hiDto.setName((String)param.get("name"));
		hiDto.setOperator_email((String)param.get("email"));
		hiDto.setTel((String)param.get("tel"));
		hiDto.setAddress1((String)param.get("address1"));
		hiDto.setAddress2((String)param.get("address2"));
		hiDto.setContent((String)param.get("insertContent"));
		hiDto.setEmergency((String)param.get("emergencyRadio"));
		hiDto.setOpentime((String)param.get("opentime"));
		
//		먼저 병원정보를 입력 후 성공한다면 진료항목을 입력
		boolean isc = hiService.insertHospital(hiDto);
		if (isc) {		
			PetTypeDto ptDto = new PetTypeDto();
			
			// 선택한 진료항목을 배열에 담음
			String[] hiddenValue = req.getParameterValues("hiddenValue"); 		
//			System.out.println("길이확인중@@@@@@@@ :"+hiddenValue.length);
			
			// 배열에 담은 진료항목을 길이만큼 돌려서 입력
			for (int i = 0; i < hiddenValue.length; i++) {
				String hv = hiddenValue[i];
//				System.out.println(hiddenValue[i]);
				
				ptDto.setPettype(hv);

				boolean isc2 = hiService.insertPetType(ptDto);
				if(isc2) {
					Util.PrintWriterMsg(resp, "병원정보 등록이 모두 완료 되었습니다.", "PetCare/home.do");
				}else {
					Util.PrintWriterMsg(resp, "진료항목 등록에 문제가 발생했습니다.", "PetCare/home.do");
				}		
			}
						
		} else {
			Util.PrintWriterMsg(resp, "병원정보 등록에 문제가 발생했습니다.", "PetCare/home.do");
		}
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
		logger.info("[detailHospital.] - {} :  사용자 병원정보 상세보기 페이지로 이동", seq);
		
		HospitalJoinDto dto = hiService.detailHospital(seq);
		model.addAttribute("dto", dto);
		
		List<CodeDto> pet = codeService.categoryCodeSelect("PET");
		JSONArray petJson = JsonUtil.CommonCodeJson(pet);
		
		model.addAttribute("pet", pet);
		model.addAttribute("petJson", petJson);
		
		return "hospital/detailHospital";
	}
	
	@RequestMapping(value = "/insertOrDetail.do", method = RequestMethod.GET)
	public String insertOrDetail(HttpSession session, HttpServletResponse resp){
		logger.info("[insertOrDetail] :  병원관리자 병원정보 클릭시 이동페이지 판단");
		
		MemberDto mDto = (MemberDto) session.getAttribute("member");
		String Email = mDto.getEmail();
		
		// hSeq 가 존재하지않으면 0이 반환됨
		int hSeq = hsService.findSeq(Email);
		if(hSeq>0) {
//			return "redirect:./login.do";
			return "redirect:./operDetailHospital.do"; 
		}else {
			return "redirect:./insertHospitalPage.do"; 
		}
		
	}
	
	@RequestMapping(value = "/operDetailHospital.do", method = RequestMethod.GET)
	public String operDetailHospital(Model model, HttpSession session) {
		logger.info("[detailHospital.] - {} :  병원관리자 병원정보 상세보기 페이지로 이동", model);
		
		MemberDto mDto = (MemberDto) session.getAttribute("member");
		String oEmail = mDto.getEmail();
		
		HospitalJoinDto dto = hiService.detailHospitalOper(oEmail);
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
	
	@RequestMapping(value = "/modifyHospitalPage.do", method = RequestMethod.GET)
	public String modifyHospitalPage(String seq, Model model, HttpSession session) {
		logger.info("[modifyHospitalPage] : 병원 정보 수정 페이지 이동 요청 : ");
		
		HospitalJoinDto hjDto = hiService.detailHospital(Integer.parseInt(seq));
		//병원 seq를 세션에 저장
		session.setAttribute("seq", Integer.parseInt(seq));
		
		//진료항목 리스트
		List<CodeDto> petlist =hiService.petTypeList();
		List<CodeDto> pet = codeService.categoryCodeSelect("PET");
		JSONArray petJson = JsonUtil.CommonCodeJson(pet);
		
		model.addAttribute("pet", pet);
		model.addAttribute("petJson", petJson);
		model.addAttribute("hjdto", hjDto);
		model.addAttribute("petlist", petlist);
		
		return "hospital/modifyHospital";
	}
	
	@RequestMapping(value = "/modifyHospital.do", method = RequestMethod.POST)
	public void modifyHospital(@RequestParam Map<String, Object> param, HttpSession session, HttpServletResponse resp, HttpServletRequest req) throws IOException {

		HospitalInfoDto hiDto = new HospitalInfoDto();
		
		//페이지 이동에서 세션에 저장시킨 병원 seq 를 가져옴
		int seq = (int) session.getAttribute("seq");

		hiDto.setName((String)param.get("name"));
		hiDto.setTel((String)param.get("tel"));
		hiDto.setAddress1((String)param.get("address1"));
		hiDto.setAddress2((String)param.get("address2"));
		hiDto.setContent((String)param.get("modifyContents"));
		hiDto.setEmergency((String)param.get("emergencyRadio"));
		hiDto.setOpentime((String)param.get("opentime"));
		hiDto.setSeq(seq);
		
//		먼저 병원정보를 수정 후 성공한다면 진료항목을 수정
		boolean isc = hiService.modifyHospital(hiDto);
		if (isc) {		
			PetTypeDto ptDto = new PetTypeDto();
			
			ptDto.setHospital_seq(seq);
			
			//선택한 진료항목 입력전 기존 진료항목 삭제
			boolean isc2 = hiService.deletePetType(ptDto);
			if(isc2) {
				
				// 선택한 진료항목을 배열에 담음
				String[] hiddenValue = req.getParameterValues("hiddenValue"); 		
//				System.out.println("길이확인중@@@@@@@@ :"+hiddenValue.length);
				
				// 배열에 담은 진료항목을 길이만큼 돌려서 입력
				for (int i = 0; i < hiddenValue.length; i++) {
					String hv = hiddenValue[i];
//					System.out.println(hiddenValue[i]);				
					ptDto.setPettype(hv);

					boolean isc3 = hiService.modifyPetType(ptDto);
					if(isc3) {
						Util.PrintWriterMsg(resp, "병원정보 수정이 모두 완료 되었습니다.", "PetCare/home.do");
					}else {
						Util.PrintWriterMsg(resp, "새 진료항목 입력에 문제가 발생했습니다.", "PetCare/home.do");
					}		
				}			
			}else {
				Util.PrintWriterMsg(resp, "기존 진료항목 삭제에 문제가 발생했습니다.", "PetCare/home.do");
			}								
		} else {
			Util.PrintWriterMsg(resp, "병원정보 수정에 문제가 발생했습니다.", "PetCare/home.do");
		}
	}
	
	@RequestMapping(value = "/insertSchedulePage.do", method = RequestMethod.GET)
	public String insertHospitalSchedule() {
		logger.info("[insertHospitalSchedule] : 병원 일정 등록 페이지 이동 요청 : ");
		
		return "hospital/insertHospitalSchedule";
	}
	
	@RequestMapping(value = "/insertSchedule.do", method = RequestMethod.POST)
	public void insertSchedule(@RequestParam Map<String, Object> param, HttpSession session, HttpServletResponse resp) throws ParseException, IOException {
		logger.info("[insertSchedule] - {} : 병원 일정 등록 요청 : ", param);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		HospitalScheduleDto hsDto = new HospitalScheduleDto();
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String email = mDto.getEmail();
		
		int hSeq = hsService.findSeq(email);
		
		// 에디터에 이미지 삽입시 4000 훅넘어서 에러뜸
		hsDto.setHospital_seq(hSeq);
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
	
	@RequestMapping(value = "/detailSchedulePage.do", method = RequestMethod.GET)
	public String detailSchedulePage(Model model) {
		logger.info("[detailSchedulePage] : 병원 일정 상세보기 페이지 이동 요청 : ");
			
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		HospitalScheduleDto dto = hsService.detailSchedule(61);
		model.addAttribute("dto", dto);
		
		return "hospital/detailHospitalSchedule";
	}
	
	@RequestMapping(value = "/modifySchedulePage.do", method = RequestMethod.GET)
	public String modifySchedulePage(Model model) {
		logger.info("[modifySchedulePage] : 병원 일정 수정하기 페이지 이동 요청 : ");
		
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		HospitalScheduleDto dto = hsService.detailSchedule(61);
		model.addAttribute("dto", dto);
		
		return "hospital/modifyHospitalSchedule";
	}
	
	@RequestMapping(value = "/modifySchedule.do", method = RequestMethod.POST)
	public void modifySchedule(@RequestParam Map<String, Object> param, HttpSession session, HttpServletResponse resp) throws ParseException, IOException {
		logger.info("[modifySchedule] - {} : 병원 일정 수정 요청 : ", param);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		HospitalScheduleDto hsDto = new HospitalScheduleDto();
		
//		MemberDto mDto = (MemberDto) session.getAttribute("member");
		
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@

		hsDto.setSeq(61);
		hsDto.setTitle((String)param.get("scheduleName"));
		hsDto.setSchedule(dateFormat.parse((String)param.get("scheduleDate")));
		hsDto.setCheck((String)param.get("reservationChk"));
		hsDto.setContent((String)param.get("modifyScheduleContent"));
		
		boolean isc = hsService.modifySchedule(hsDto);
		if (isc) {
			Util.PrintWriterMsg(resp, "일정 수정이 완료 되었습니다.", "PetCare/home.do");
		} else {
			Util.PrintWriterMsg(resp, "일정 수정에 문제가 발생했습니다.", "PetCare/home.do");
		}
		
	}
	
	@RequestMapping(value = "/deleteHospitalSchedule.do", method = RequestMethod.GET)
	public void deleteHospitalSchedule(HttpServletResponse resp) throws IOException {
		logger.info("[deleteHospitalSchedule] - {} : 병원 일정 삭제 요청 : ");
		
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		//@@@@@@@@캘린더에서 글 seq 가져올거임 병합 후 수정 필요 @@@@@@@@@@
		boolean isc = hsService.deleteSchedule(61);
		if(isc) {
			Util.PrintWriterMsg(resp, "삭제가 완료 되었습니다.", "PetCare/home.do");
		}else {
			Util.PrintWriterMsg(resp, "오류가 발생했습니다. 다시 시도해주세요.", "PetCare/home.do");	
		}

	}
	
}
