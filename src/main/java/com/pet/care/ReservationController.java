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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.comm.JsonUtil;
import com.pet.care.dto.HospitalScheduleDto;
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar.do", method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String Calendar(String hospital_seq) {
		logger.info("ReservationController Calendar 달력 {} ",hospital_seq );
		System.out.println(hospital_seq);
		
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
	 *  예약 신청 테스트
	 */
	@RequestMapping(value = "/insertReservation.do", method = RequestMethod.GET)
	public String insertReservation(Model model) {
		logger.info("ReservationController insertReservation 양식 입력  " );
		Map<String, Object> map = new HashMap<String, Object>();
		//세션에서 ? 
		map.put("user_email", "user01@gmail.com");
		
		//이건 반려동물 정보 가져오기 위한 것
		List<String>lists = rService.getUserPet(map);

		model.addAttribute("userPet",lists);
		
		return "reservation/insertReservation";
		
	}
	
	/*
	 * 결제 컨트롤러로? 보내야 함
	 */
	@RequestMapping(value="sendReservation.do", method=RequestMethod.POST)
	public String sendReservation(ReservationDto dto, Model model) {
		logger.info("ReservationController insertReservation 양식 입력값 {}  ",dto );
		
		dto.setReservetime("09001100");
		
		System.out.println(dto);
		
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
		map.put("user_email", "user01@gmail.com");
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
	public String userReserveDetail(Model model, @RequestParam Map<String, Object> map) {
		
		logger.info("ReservationController userRejectDetail 사용자 예약 상세 조회 " );
		//user_email은 세션에서 가져와야함
		//seq는 예약번호 request
		String seq = (String) map.get("seq");
		System.out.println(seq);
		
		Map<String, Object> uMap = new HashMap<String, Object>();
		uMap.put("user_email", "user01@gmail.com");
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
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("seq", 3);
		
		List<ReservationDto>lists = rService.hospitalReserveList(map);
		
		model.addAttribute("lists",lists);
		return "reservation/hospitalReserveList";
	}
	
	/*
	 * 병원 예약 상세 조회 완료
	 */
	@RequestMapping(value = "/hospitalReserveDetail.do", method = RequestMethod.GET)
	public String hospitalReserveDetail(@RequestParam Map<String,Object>map, Model model) {
		String seq = (String)map.get("seq");
		System.out.println("seq======================"+seq);
		Map<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("seq", seq);
		hMap.put("hospital_seq", 3);
		
		ReservationDto rDto = rService.hospitalReserveDetail(hMap);
	
		System.out.println(rDto);
		model.addAttribute("detail", rDto);
		
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
	public String selectdayReserveList(@RequestParam Map<String, Object>map, Model model) {
		
		String reservedate = (String)map.get("reservedate");
		
		Map<String, Object>rMap = new HashMap<String, Object>();
		rMap.put("hospital_seq",3);
		rMap.put("reservedate",reservedate);
		map.put("user_email","user01@gmail.com");
		List<String>petLists = rService.getUserPet(map);

		//반려 동물 목록
		model.addAttribute("userPet",petLists);
		
		List<ReservationDto> lists = rService.selectdayReserveList(rMap);
		
		model.addAttribute("todayReserve", lists);
		
		return "reservation/selectdayReserveList";
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
	