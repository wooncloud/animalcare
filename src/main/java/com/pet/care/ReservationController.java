package com.pet.care;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pet.care.comm.JsonUtil;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.ReservationDto;
import com.pet.care.model.service.hospital.IHospitalInfoService;
import com.pet.care.model.service.hospital.IHospitalScheduleService;
import com.pet.care.model.service.reservation.IReservationService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private IReservationService rService;
	
	@Autowired
	private IHospitalScheduleService hService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
//	@RequestMapping(value = "/calendar.do", method = RequestMethod.GET)
//	public String Calendar(Model model) {
//
//		Map<String, Object> rMap = new HashMap<String, Object>();
//		rMap.put("hospital_seq",3);
//		
//		Map<String, Object>hMap = new HashMap<String, Object>();
//		hMap.put("hospital_seq", 3);
//		
//		List<ReservationDto>rLists= rService.hospitalReserveList(rMap);
//		List<HospitalScheduleDto> hList = hService.monthSchedule(hMap);
//		
//		
//		JSONArray jarr = JsonUtil.CalenderJson(hList, rLists);
//		
//		model.addAttribute("jarr", jarr.toJSONString());
//		
//		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-="+jarr.toJSONString());
//		
//		return "reservation/calendar";
//	}
	
	/*
	 * 달력 이동
	 */
	@RequestMapping(value = "/moveCalendar.do", method = RequestMethod.GET)
	public String moveCalendar() {
		return "reservation/calendar";
	}
	
	/*
	 * 달력에 일정
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar.do", method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String Calendar(String hospital_seq, Model model) {
		logger.info("ReservationController Calendar 달력 {} ",hospital_seq );
		System.out.println(hospital_seq);
		
		//세션
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("hospital_seq",hospital_seq);
		
		Map<String, Object>hMap = new HashMap<String, Object>();
		hMap.put("hospital_seq", hospital_seq);
		
		List<ReservationDto>rLists= rService.hospitalReserveList(rMap);
		List<HospitalScheduleDto> hList = hService.monthSchedule(hMap);
		
		JSONArray jarr = JsonUtil.CalenderJson(hList, rLists);
		
		JSONObject result = new JSONObject();
		result.put("result",jarr);
		
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-="+result.toJSONString());
		
		return result.toString();
		
	}
	
	/*
	 * 예약 디비 입력 insert  놀고 있는거
	 */
	@RequestMapping(value = "/insertReservation.do", method = RequestMethod.POST)
	public String insertReservation(Model model, ReservationDto rDto, @RequestParam Map<String, Object> map, String paynum) {
		logger.info("ReservationController insertReservation 양식 입력 {} =========================", rDto );
		System.out.println("+++++++++++++++++++++++++++++++++="+rDto);
		System.out.println("------------------------------"+map);
		rDto.setHospital_seq(3);
		 map.get("name");
		 map.get("pet_name");
		 map.get("reservetype");
		 map.get("user_email");
		 map.get("phone");
		 System.out.println("+++++++++++++++++두번째++++++++++++++++="+rDto);
		 System.out.println("================================"+paynum);
		rService.insertReserve(rDto);
		return "redirect:/reservation/userReserveList.do";
		
	}
	
	/*
	 * 결제 없이 입력 테스트
	 */
//	@RequestMapping(value = "/insertReservation.do", method = RequestMethod.GET)
//	public String insertReservation(Model model, HttpSession session) {
//		logger.info("ReservationController insertReservation 양식 입력  " );
//		Map<String, Object> map = new HashMap<String, Object>();
//		//세션에서 ? 
//		map.put("user_email", "user01@gmail.com");
//		
//		//이건 반려동물 정보 가져오기 위한 것
//		List<String>lists = rService.getUserPet(map);
//
//		model.addAttribute("userPet",lists);
//		
//		return "redirect:/reservation/userReserveList.do";
//		
//	}
	
	/*
	 * 결제 컨트롤러로
	 */
	@RequestMapping(value="sendReservation.do", method=RequestMethod.POST)
	public String sendReservation(ReservationDto dto, Model model) {
		logger.info("ReservationController insertReservation 양식 입력값 {}  ",dto );
		
		dto.setReservetime("09001100");
		dto.setHospital_seq(3);
		System.out.println(dto);
		
		boolean isc = rService.insertReserve(dto);
		
		if(isc) {
			
		}
		model.addAttribute("rDto",dto);
		
		return "reservation/index";
	}
	
	/*
	 * 사용자 예약 목록 조회
	 */
	@RequestMapping(value = "/userReserveList.do", method = RequestMethod.GET)
	public String userReserveList(HttpSession session, Model model) {
		
		logger.info("ReservationController userReserveList 사용자 예약 목록 조회  " );
		//user_email은 세션에서 가져와야함
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
	
		String email = mDto.getEmail();
		
		map.put("user_email", email);
		
		List<ReservationDto> lists = rService.userReserveList(map);
		
		model.addAttribute("lists",lists);
	
		return "reservation/userReserveList";
		
	}
	
	/*
	 * 사용자 반려 상세 조회
	 */
	@RequestMapping(value = "/userRejectDetail.do", method = RequestMethod.GET)
	public String userRejectDetail(Model model) {
		
		logger.info("ReservationController userRejectDetail 사용자 반려 상세 조회 " );
		//user_email은 세션에서 가져와야함
		//seq는 예약번호 request
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", "user01@gmail.com");
		map.put("seq", "103");
		ReservationDto rDto = rService.userRejectDetail(map);
		

		
		model.addAttribute("rejectDto",rDto);
		
		return "reservation/userRejectDetail";
		
	}
	
	
	/*
	 * 사용자 예약 상세 조회
	 */
	@RequestMapping(value = "/userReserveDetail.do", method = RequestMethod.GET)
	public String userReserveDetail(Model model, @RequestParam Map<String, Object> map, HttpSession session) {
		
		logger.info("ReservationController userRejectDetail 사용자 예약 상세 조회 " );
		//user_email은 세션에서 가져와야함
		//seq는 예약번호 request
		 MemberDto mDto =(MemberDto)session.getAttribute("member");
		 String user_email = mDto.getEmail();
		String seq = (String) map.get("seq");
		System.out.println(seq);
		
		Map<String, Object> uMap = new HashMap<String, Object>();
		uMap.put("user_email", user_email);
		uMap.put("seq", seq);
		ReservationDto rDto = rService.userReserveDetail(uMap);
		
		model.addAttribute("reserveDto",rDto);
		
		return "reservation/userReserveDetail";
	}
	
	
	/*
	 * 오늘의 예약 조회
	 */
	@RequestMapping(value = "/todayReserveList.do", method = RequestMethod.GET)
	public String todayReserveList(Model model) {
		logger.info("ReservationController todayReserveList 오늘의 예약 조회 " );
		//seq는 병원번호 session
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq",4);
		
		List<ReservationDto> lists = rService.todayReserveList(map);
		model.addAttribute("todayLists",lists);
		
		
		return "reservation/todayReserveList";
		
	}
	
	/*
	 * 병원 예약 목록 조회
	 */
	@RequestMapping(value = "/hospitalReserveList.do", method = RequestMethod.GET)
	public String hospitalReserveList(Model model) {
		logger.info("ReservationController hospitalReserveList 병원 예약 목록 조회 " );
		//seq 병원번호 session
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("hospital_seq", 3);
		
		List<ReservationDto>lists = rService.hospitalReserveList(map);
		
		model.addAttribute("lists",lists);
		
		return "reservation/hospitalReserveList";
	}
	
	/*
	 * 병원 예약 미처리 목록 조회
	 */
	@RequestMapping(value = "/hospitalStandReserveList.do", method=RequestMethod.GET)
	public String hospitalStandReserveList(Model model) {
		logger.info("ReservationController hospitalStandReserveList 병원 예약 미처리 목록 조회 {}");
		Map<String, Object>map = new HashMap<String, Object>();
		//병원 seq 임의 입력 session?에서 가져오던가 해야함
		map.put("hospital_seq",3);
		List<ReservationDto>lists = rService.hospitalStandReserveList(map);
		
		model.addAttribute("lists",lists);
		
		return "reservation/hospitalReserveList";
	}
	
	//1번 문자
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/hospitalStandReserveList.do", method=RequestMethod.GET, produces = "application/text; charset=UTF-8")
//	@ResponseBody
//	public String hospitalStandReserveList(Model model) {
//		logger.info("ReservationController hospitalStandReserveList 병원 예약 미처리 목록 조회 {}");
//		Map<String, Object>map = new HashMap<String, Object>();
//		map.put("hospital_seq",3);
//		List<ReservationDto>lists = rService.hospitalStandReserveList(map);
//		
//		JSONObject jObj = new JSONObject();
//		jObj.put("lists", lists);
//		
//		System.out.println(jObj.toJSONString());
//		
//		return jObj.toJSONString();
//	}
	
	/*
	 * 병원 예약 상세 조회 완료
	 */
	@RequestMapping(value = "/hospitalReserveDetail.do", method = RequestMethod.GET)
	public String hospitalReserveDetail(@RequestParam Map<String,Object>map, Model model) {
		logger.info("ReservationController hospitalReserveDetail 병원 예약 상세 조회 완료 " );
		String seq = (String)map.get("seq");
		System.out.println("seq======================"+seq);
		Map<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("seq", seq);
		hMap.put("hospital_seq", 3);
		
		ReservationDto rDto = rService.hospitalReserveDetail(hMap);
	
		System.out.println(rDto);
		model.addAttribute("hospitalReserveDetail", rDto);
		
		return "reservation/hospitalReserveDetail";
	}
	/*
	 * 예약 수정 
	 */
	@RequestMapping(value = "/modifyReserve.do", method=RequestMethod.POST)
	public String modifyReserve() {
		//예약 seq , 병원변호 seq
//		
//		 boolean isc = service.modifyReserve(null);
//		 
//		 if(isc) {
//			 
//		 }
//		
		return "reservation/modifyReserve";
	}
	
	/*
	 * 선택일 예약 목록 조회 
	 */
	@RequestMapping(value = "/selectdayReserveList.do", method = RequestMethod.GET)
	public String selectdayReserveList(@RequestParam Map<String, Object>map, Model model, HttpSession session) {
		logger.info("ReservationController selectdayReserveList 선택일 예약 목록 조회 {}", map );
		
	      String reservedate = (String)map.get("reservedate");

	      MemberDto mDto =  (MemberDto)session.getAttribute("member");
	      String user_email = mDto.getEmail();
	      
	      
	      Map<String, Object>rMap = new HashMap<String, Object>();
	      rMap.put("hospital_seq",3);
	      rMap.put("reservedate",reservedate);
	      map.put("user_email",user_email);
	      List<String>petLists = rService.getUserPet(map);

	      //반려 동물 목록
	      model.addAttribute("userPet",petLists);
	      
	      List<ReservationDto> lists = rService.selectdayReserveList(rMap);
	      model.addAttribute("reservedate", reservedate);
	      model.addAttribute("todayReserve", lists);
	      //가져갈방법????????
	      return "reservation/selectdayReserveList";

	}

	/*
	 * 결제 컨트롤러 -> 예약 취소(쿼리실행)
	 * 사용자 취소
	 */
	@RequestMapping(value = "/cancelReserve.do", method = RequestMethod.GET)
	public String cancelReservation(@RequestParam Map<String, Object> map, Model model) {
		logger.info("ReservationController selectdayReserveList 예약 취소 결제 보내기{}", map );
		//세션에 병원 seq 있으면 병원 관계자 결제 컨트롤러
		// 아니면 사용자 결제 컨트롤러
		
		String seq = (String)map.get("seq");
		String status =(String) map.get("status");
	
		model.addAttribute("seq", seq);
		model.addAttribute("status", status);
		
		//status도 보내주세요~~
		if(status == "S") {
			//status 받아서 대기이면 환불 결제 컨트롤러
//			rService.cancelReserve(map);
			return "대기 상태 취소 결제 컨트롤러";
		} else {
			//status 받아서 확정이면 환불X 결제 컨트롤러
//			rService.cancelReserve(map);
			return "확정 상태 취소 결제 컨트롤러";
		}	
	}
	
	/*
	 * 병원 관계자 예약 확정
	 */
	@RequestMapping(value = "/acceptReserve.do", method = RequestMethod.GET)
	public String acceptReserve(@RequestParam Map<String, Object>map) {
		logger.info("ReservationController acceptReserve 병원 관계자 예약 확정 {}", map );
		String seq = (String)map.get("seq");
		
		rService.acceptReserve(map);
		
		return "redirect:/reservation/hospitalReserveDetail.do?seq="+seq;
	}
	
	/*
	 * 병원 관계자 예약 반려
	 */
	@RequestMapping(value = "/rejectReserve.do", method = RequestMethod.POST)
	public String rejectReserve(@RequestParam Map<String,Object>map, Model model) {
		logger.info("ReservationController acceptReserve 예약 확정  {}", map );
		String seq = (String)map.get("seq");
		boolean isc = rService.rejectReserve(map);
		model.addAttribute("seq",seq);
		if(isc) {
			return "redirect:/reservation/hospitalStandReserveList.do";
		}else {
			return "reservation/index";
		}
		
	}
	

	/*
	 * 오늘의 예약 목록 조회 (병원 관계자 페이지에서 볼거)
	 */
//	@RequestMapping(value = "/todayReserveList.do", method = RequestMethod.GET)
//	public String selectdayReserveList(Model model) {
//	
//		
//		Map<String, Object>rMap = new HashMap<String, Object>();
//		rMap.put("hospital_seq",3);
//		
//		List<ReservationDto> lists = rService.todayReserveList(rMap);
//		
//		model.addAttribute("todayReserve", lists);
//		
//		return "reservation/insertReservation";
//	}
}
	