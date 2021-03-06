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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.comm.JsonUtil;
import com.pet.care.comm.PageUtil;
import com.pet.care.comm.Util;
import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.MedicalRecodeDto;
import com.pet.care.dto.MedicalRecodeJoinDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.PageDto;
import com.pet.care.dto.PetTypeDto;
import com.pet.care.model.service.code.ICodeService;
import com.pet.care.model.service.hospital.IHospitalInfoService;
import com.pet.care.model.service.hospital.IHospitalScheduleService;
import com.pet.care.model.service.hospital.IMedicalRecodeService;


@Controller
@RequestMapping("/hospital")
public class HospitalInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private IHospitalInfoService hiService;
	
	@Autowired 
	private IHospitalScheduleService hsService;
	
	@Autowired
	private IMedicalRecodeService mrService;
	
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
		
		String opentime = (String)param.get("opentime");
		
		hiDto.setName((String)param.get("name"));
		hiDto.setOperator_email((String)param.get("email"));
		hiDto.setTel((String)param.get("tel"));
		hiDto.setAddress1((String)param.get("address1"));
		hiDto.setAddress2((String)param.get("address2"));
		hiDto.setContent((String)param.get("insertContent"));
		hiDto.setEmergency((String)param.get("emergencyRadio"));
		// 운영시간 textarea에서 엔터입력시 DB에 <br>로 입력되도록
		hiDto.setOpentime(opentime.replace("\r\n","<br>"));
		
//		먼저 병원정보를 입력 후 성공한다면 진료항목을 입력
		boolean isc = hiService.insertHospital(hiDto);
		if (isc) {		
			PetTypeDto ptDto = new PetTypeDto();
			
			// 선택한 진료항목을 배열에 담음
			String[] hiddenValue = req.getParameterValues("hiddenValue"); 		
//			System.out.println("길이확인중 :"+hiddenValue.length);
			
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
	
	@RequestMapping(value = "/searchHospitalPage.do", method = RequestMethod.GET)
	public String searchHospitalPage(@RequestParam Map<String, Object> param, Model model) {
		logger.info("[searchHospitalPage] - {} :  병원찾기 페이지로 이동", model);
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)param.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		//볼수 있는 글의 총 갯수
		allPageCnt = hiService.hospitalCount(param);
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		param.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		param.put("last", page.getPage() * page.getCountList());
		
		//볼수 있는 병원 리스트
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
	
	@RequestMapping(value = "/searchHospital.do", method = RequestMethod.POST)
	public String searchHospital(@RequestParam Map<String, Object> param, Model model, HttpServletRequest req) {
		logger.info("[searchHospital] - {} :  병원찾기", model);

		//선택한 지역 확인
		String[] address1 = req.getParameterValues("hiddenLoc");
		param.put("address1", address1);
//		for (int i = 0; i < address1.length; i++) {
//			System.out.println(address1[i]+"선택한 지역 확인");
//		}
		
		//선택한 진료항목 확인  
		String[] pettype = req.getParameterValues("hiddenValue");
		param.put("pettype", pettype);
//		for (int i = 0; i < pettype.length; i++) {
//			System.out.println(pettype[i]+"선택한 진료항목 확인");
//		}
		
		// 응급실 유무 선택 확인 
		String emergency = req.getParameter("flexRadioDefault");				
		param.put("emergency", emergency);
//		System.out.println(emergency+"선택한 응급실 유무 값 확인");
		
		//페이징 
				PageDto page = new PageDto();
				String strIdx = (String)param.get("page");
				if(strIdx == null) {
					strIdx = "1";
				}
				
				int idx = Integer.parseInt(strIdx);
				int allPageCnt = 0;
				
				//볼수 있는 글의 총 갯수
				allPageCnt = hiService.searchCount(param);
				
				//PageDto 셋팅
				PageUtil.defaultPagingSetting(page, allPageCnt);
				
				page.setPage(idx);
				page.setStartPage(idx);
				page.setEndPage(page.getCountPage());
				
				param.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
				param.put("last", page.getPage() * page.getCountList());
				
				//볼수 있는 병원 리스트
				List<HospitalJoinDto> lists = hiService.searchHospital(param);
				
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
		
		// // 로그인한 병원의 seq  존재하지않으면 0이 반환됨
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
		logger.info("[modifyHospital] - {} : 병원 정보 수정 요청 : ", param);
		
		HospitalInfoDto hiDto = new HospitalInfoDto();
		
		//페이지 이동에서 세션에 저장시킨 병원 seq 를 가져옴
		int seq = (int) session.getAttribute("seq");
		
		String opentime = (String)param.get("opentime");

		hiDto.setName((String)param.get("name"));
		hiDto.setTel((String)param.get("tel"));
		hiDto.setAddress1((String)param.get("address1"));
		hiDto.setAddress2((String)param.get("address2"));
		hiDto.setContent((String)param.get("modifyContents"));
		hiDto.setEmergency((String)param.get("emergencyRadio"));
//		hiDto.setOpentime((String)param.get("opentime"));
		hiDto.setOpentime(opentime.replace("\r\n","<br>"));
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
				
				// 배열에 담은 진료항목을 길이만큼 돌려서 입력
				for (int i = 0; i < hiddenValue.length; i++) {
					String hv = hiddenValue[i];
			
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
	public String detailSchedulePage(@RequestParam Map<String, Object> param, Model model) {
		logger.info("[detailSchedulePage] : 병원 일정 상세보기 페이지 이동 요청 : ");
		
		//캘린더의 seq를 받아옴
		String sequence = (String)param.get("seq");
		int seq = Integer.parseInt(sequence); 
		
		HospitalScheduleDto dto = hsService.detailSchedule(seq);
		model.addAttribute("dto", dto);
		
		return "hospital/detailHospitalSchedule";
	}
	
	@RequestMapping(value = "/modifySchedulePage.do", method = RequestMethod.GET)
	public String modifySchedulePage(int seq, Model model) {
		logger.info("[modifySchedulePage] : 병원 일정 수정하기 페이지 이동 요청 : ");
		
		HospitalScheduleDto dto = hsService.detailSchedule(seq);
		model.addAttribute("dto", dto);
		
		return "hospital/modifyHospitalSchedule";
	}
	
	@RequestMapping(value = "/modifySchedule.do", method = RequestMethod.POST)
	public void modifySchedule(@RequestParam Map<String, Object> param, int seq, HttpSession session, HttpServletResponse resp) throws ParseException, IOException {
		logger.info("[modifySchedule] - {} : 병원 일정 수정 요청 : ", param);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		HospitalScheduleDto hsDto = new HospitalScheduleDto();
		hsDto.setSeq(seq);

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
	public void deleteHospitalSchedule(int seq, HttpServletResponse resp) throws IOException {
		logger.info("[deleteHospitalSchedule] - {} : 병원 일정 삭제 요청 : ");
		
		boolean isc = hsService.deleteSchedule(seq);
		if(isc) {
			Util.PrintWriterMsg(resp, "삭제가 완료 되었습니다.", "PetCare/home.do");
		}else {
			Util.PrintWriterMsg(resp, "오류가 발생했습니다. 다시 시도해주세요.", "PetCare/home.do");	
		}

	}
	
	@RequestMapping(value = "/recodeList.do", method = RequestMethod.GET)
	public String recodeList(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		logger.info("[recodeList] - {} :  병원 진료기록 리스트 페이지로 이동", model);
		
		MemberDto mDto = (MemberDto) session.getAttribute("member");
		String Email = mDto.getEmail();
		
		// // 로그인한 병원의 seq  존재하지않으면 0이 반환됨
		int hSeq = hsService.findSeq(Email);
		
		// 병원 등록이 되어있지 않다면 진료내역도 있을 수 없으므로 홈으로 보냄
		if(hSeq<1) {
			return "redirect:./PetCare/home.do"; 
		}
		
		//진료기록을 볼 병원의 seq 입력
		param.put("hospital_seq", hSeq);
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)param.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		//볼수 있는 글의 총 갯수
		allPageCnt = mrService.recodeCount(param);
		
		//PageDto 셋팅
		PageUtil.defaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		param.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		param.put("last", page.getPage() * page.getCountList());
		
		//볼수 있는 진료기록 리스트
		List<MedicalRecodeJoinDto> lists = mrService.recodeList(param);
			
		model.addAttribute("page", page);
		model.addAttribute("lists", lists);
		
		return "hospital/medicalRecodeList";
	}
	
	@RequestMapping(value = "/detailRecodeHospital.do", method = RequestMethod.GET)
	public String detailRecodeHospital(int seq, Model model) {
		logger.info("[detailRecodeHospital] - {} :  병원관계자 진료기록 상세보기 페이지로 이동", seq);
		
		MedicalRecodeJoinDto dto = mrService.detailRecode(seq);
		model.addAttribute("dto", dto);
		
		return "hospital/detailMedicalRecode";
	}	
	
	@RequestMapping(value = "/insertRecodePage.do", method = RequestMethod.GET)
	public String insertRecodePage(String seq, Model model) {
		logger.info("[insertRecodePage] - {} :  병원관계자 진료기록 입력하기 페이지로 이동", model);
		// System.out.println(seq+"확인");

		int sequence = Integer.parseInt(seq);
		HospitalJoinDto dto = hiService.insertsBasicData(sequence);
		model.addAttribute("dto", dto); 
		
		return "hospital/insertMedicalRecode";	
	}
	
	@RequestMapping(value = "/insertMedicalRecode.do", method = RequestMethod.POST)
	public void insertMedicalRecode(@RequestParam Map<String, Object> param, HttpSession session, HttpServletResponse resp) throws IOException {
		logger.info("[insertMedicalRecode] - {} :  병원관계자 진료기록 입력 요청", param);
		
		MedicalRecodeDto mrDto = new MedicalRecodeDto();
		
		MemberDto mDto = (MemberDto) session.getAttribute("member");
		String Email = mDto.getEmail();
		
		// 로그인한 병원의 seq  존재하지않으면 0이 반환됨
		int hSeq = hsService.findSeq(Email);
		
		mrDto.setHospital_seq(hSeq);
		mrDto.setPet_id((String)param.get("petId"));
		mrDto.setSymptom((String)param.get("symptom"));
		mrDto.setTreatment((String)param.get("insertTreatmentContent"));
		mrDto.setPrescription((String)param.get("insertPrescriptionContent"));
		
		boolean isc = mrService.insertRecode(mrDto);
		if(isc) {
			Util.PrintWriterMsg(resp, "입력이 완료 되었습니다.", "./recodeList.do");
		}else {
			Util.PrintWriterMsg(resp, "오류가 발생했습니다. 다시 시도해주세요.", "PetCare/home.do");	
		}
		
	}
	
	@RequestMapping(value = "/modifyRecodeHospital.do", method = RequestMethod.GET)
	public String modifyRecodeHospital(int seq, Model model) {
		logger.info("[modifyRecodeHospital] - {} :  병원관계자 진료기록 수정하기 페이지로 이동", seq);
		
		MedicalRecodeJoinDto dto = mrService.detailRecode(seq);
		model.addAttribute("dto", dto);
		
		return "hospital/modifyMedicalRecode";	
	}
	
	@RequestMapping(value = "/modifyRecode.do", method = RequestMethod.POST)
	public void modifyRecode(@RequestParam Map<String, Object> param,HttpServletResponse resp) throws IOException {
		logger.info("[modifyRecode] - {} :  병원관계자 진료기록 수정 요청");
		
		MedicalRecodeDto mrDto = new MedicalRecodeDto();
		
		String seq = (String) param.get("hiddenSeq");
		mrDto.setSeq(Integer.parseInt(seq));
		mrDto.setTreatment((String)param.get("modifyTreatmentContent"));
		mrDto.setPrescription((String)param.get("modifyPrescriptionContent"));
		
		boolean isc = mrService.modifyRecode(mrDto);
		if(isc) {
			Util.PrintWriterMsg(resp, "수정이 완료 되었습니다.", "./recodeList.do");
		}else {
			Util.PrintWriterMsg(resp, "오류가 발생했습니다. 다시 시도해주세요.", "PetCare/home.do");	
		}
		
	}
	
}
