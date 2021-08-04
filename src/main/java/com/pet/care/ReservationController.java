package com.pet.care;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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

import com.pet.care.comm.CalendarDate;
import com.pet.care.comm.JsonUtil;
import com.pet.care.comm.PageUtil;
import com.pet.care.comm.Util;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.PageDto;
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
	
	/*
	 * 달력 이동
	 */
	@RequestMapping(value = "/moveCalendar.do", method = RequestMethod.GET)
	public String moveCalendar(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		logger.info("ReservationController Calendar 달력 이동 {} ", map);
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String usertype = mDto.getUsertype();
		String user_email = mDto.getEmail();
		
		//사용자
		map.put("user_email", user_email);
		model.addAttribute("searchInfo",map);
		List<String>petLists = rService.getUserPet(map);
	    //반려 동물 목록
	    model.addAttribute("userPet",petLists);
	    
	    
	    //병원 관계자
	    int hospital_seq = hService.findSeq(user_email);
	    model.addAttribute("hospital_seq",hospital_seq);
//	    HospitalJoinDto hDto = hiService.detailHospital(hospital_seq);
//	    model.addAttribute("hospital_info",hDto);
	    
		if(usertype.equals("ROLE_USER")) {			
			return "reservation/userCalendar";
		}
			return "reservation/operCalendar";
		
	}

	/*
	 * 달력에 일정 2021.07.26
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
		System.out.println("병원번호======================"+ hospital_seq);
		
		Map<String, Object>hMap = new HashMap<String, Object>();
		hMap.put("hospital_seq", hospital_seq);
		
		List<ReservationDto>rLists= rService.hospitalCalendarList(rMap);
		List<HospitalScheduleDto> hList = hService.monthSchedule(hMap);
		
		JSONArray jarr = JsonUtil.CalenderJson(hList, rLists);
		
		JSONObject result = new JSONObject();
		result.put("result",jarr.toString());
		
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-="+result.toJSONString());
		
		return result.toString();
	}
	
	/*
	 * 예약 디비 입력 insert
	 */
	@RequestMapping(value = "/insertReservation.do", method = RequestMethod.POST)
	public String insertReservation(Model model, ReservationDto rDto, @RequestParam Map<String, Object> map, String paynum,HttpSession session) {
		logger.info("ReservationController insertReservation 양식 입력 {} ", rDto );


		boolean isc = rService.insertReserve(rDto);
		if(isc) {			
			return "redirect:/reservation/userReserveList.do";
		}
			return "redirect:/error/error.do";
	}
	
//	/*
//	 * 결제 컨트롤러로
//	 */
//	@RequestMapping(value="sendReservation.do", method=RequestMethod.POST)
//	public String sendReservation(ReservationDto dto, Model model) {
//		logger.info("ReservationController insertReservation 양식 입력값 {}  ",dto );
//		
//		dto.setReservetime("09001100");
//		dto.setHospital_seq(3);
//		System.out.println(dto);
//		
//		boolean isc = rService.insertReserve(dto);
//		
//		if(isc) {
//			
//		}
//		model.addAttribute("rDto",dto);
//		
//		return "reservation/index";
//	}

	
	/*
	 * 사용자 예약 목록 조회
	 */
	@RequestMapping(value = "/userReserveList.do", method = RequestMethod.GET)
	public String userReserveList(HttpSession session, Model model, @RequestParam Map<String, Object>map) {
		
		logger.info("ReservationController userReserveList 사용자 예약 목록 조회 {}", map, session );
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String email = mDto.getEmail();
		Map<String, Object>uMap = new HashMap<String, Object>();
		uMap.put("user_email", email);
		
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)map.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0; 
		
		
		allPageCnt = rService.userReserveListCount(uMap);
		
		//PageDto 셋팅
		PageUtil.reserveDefaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		map.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		map.put("last", page.getPage() * page.getCountList());
		map.put("user_email", email);

		List<ReservationDto> lists = rService.userReserveList(map);
		
		model.addAttribute("lists",lists);
		model.addAttribute("page",page);
	
		return "reservation/userReserveList";
		
	}
	
	/*
	 * 사용자 반려 상세 조회
	 */
	@RequestMapping(value = "/userRejectDetail.do", method = RequestMethod.GET)
	public String userRejectDetail(Model model, HttpSession session) {
		
		logger.info("ReservationController userRejectDetail 사용자 반려 상세 조회 " );
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		
		//user_email은 세션에서 가져와야함
		//seq는 예약번호 request
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", mDto.getEmail());
		map.put("seq", "103"); // 반려이상하다 번호

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
	public String todayReserveList(Model model, HttpSession session) {
		logger.info("ReservationController todayReserveList 오늘의 예약 조회 " );
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String user_email = mDto.getEmail();
		
		int seq = hService.findSeq(user_email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospital_seq",seq);
		
		
		List<ReservationDto> lists = rService.todayReserveList(map);
		model.addAttribute("tlists",lists);
		
		
		return "reservation/hospitalReserveList";
		
	}
	
	/*
	 * 병원 예약 목록 조회 2021.07.26
	 */
	@RequestMapping(value = "/hospitalReserveList.do", method = RequestMethod.GET)
	public String hospitalReserveList(@RequestParam Map<String, Object>hMap, Model model , HttpSession session) {
		logger.info("ReservationController hospitalReserveList 병원 예약 목록 조회 " );
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String user_email = mDto.getEmail();
		
		int seq = hService.findSeq(user_email);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospital_seq",seq);

		//페이징
				PageDto page = new PageDto();
				String strIdx = (String)hMap.get("page");
				if(strIdx == null) {
					strIdx = "1";
				}
				
				int idx = Integer.parseInt(strIdx);
				int allPageCnt = 0;
				
				
				allPageCnt = rService.hospitalReserveListCount(map);
				
				//PageDto 셋팅
				PageUtil.reserveDefaultPagingSetting(page, allPageCnt);
				
				page.setPage(idx);
				page.setStartPage(idx);
				page.setEndPage(page.getCountPage());
				
				map.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
				map.put("last", page.getPage() * page.getCountList());

	
		List<ReservationDto>lists = rService.hospitalReserveList(map);
		
		model.addAttribute("lists",lists);
		model.addAttribute("page",page);
		
		return "reservation/hospitalReserveList";
	}
	
	/*
	 * 병원 예약 미처리 목록 조회 2021.07.26
	 */
	@RequestMapping(value = "/hospitalStandReserveList.do", method=RequestMethod.GET)
	public String hospitalStandReserveList(@RequestParam Map<String, Object>hMap, Model model, HttpSession session) {
		logger.info("ReservationController hospitalStandReserveList 병원 예약 미처리 목록 조회 {}", hMap);
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String user_email = mDto.getEmail();
		
		int seq = hService.findSeq(user_email);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospital_seq",seq);
		//페이징
		PageDto page = new PageDto();
		String strIdx = (String)hMap.get("page");
		if(strIdx == null) {
			strIdx = "1";
		}
		
		int idx = Integer.parseInt(strIdx);
		int allPageCnt = 0;
		
		
		allPageCnt = rService.hospitalStandReserveListCount(map);
		
		//PageDto 셋팅
		PageUtil.reserveDefaultPagingSetting(page, allPageCnt);
		
		page.setPage(idx);
		page.setStartPage(idx);
		page.setEndPage(page.getCountPage());
		
		map.put("first", page.getPage() * page.getCountList() - (page.getCountList() - 1));
		map.put("last", page.getPage() * page.getCountList());

		List<ReservationDto>lists = rService.hospitalStandReserveList(map);
		
		model.addAttribute("slists",lists);
		model.addAttribute("page",page);
		
		return "reservation/hospitalReserveList";
	}
	
	
	/*
	 * 병원 예약 상세 조회 완료
	 */
	@RequestMapping(value = "/hospitalReserveDetail.do", method = RequestMethod.GET)
	public String hospitalReserveDetail(@RequestParam Map<String,Object>map, HttpSession session, Model model) {
		logger.info("ReservationController hospitalReserveDetail 병원 예약 상세 조회 완료 {}", map );
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String user_email = mDto.getEmail();
		int hospital_seq = hService.findSeq(user_email);

		map.put("hospital_seq", hospital_seq);
		
		ReservationDto rDto = rService.hospitalReserveDetail(map);
	
		System.out.println(rDto);
		model.addAttribute("hospitalReserveDetail", rDto);
		
		return "reservation/hospitalReserveDetail";
	}
	/*
	 * 예약 수정 
	 */
	@RequestMapping(value = "/modifyReserve.do", method=RequestMethod.POST)
	public String modifyReserve(@RequestParam Map<String,Object>map, Model model) {
		logger.info("ReservationController modifyReserve 병원 예약 수정 {}", map );
		//예약 seq , 병원변호 seq
		String seq = (String)map.get("seq");
		String reservedate = (String)map.get("reservedate");
		String reservetime = (String)map.get("reservetime");

		Map<String, Object>rMap = new HashMap<String, Object>();
		rMap.put("seq", seq);
		rMap.put("reservedate", reservedate);
		rMap.put("reservetime", reservetime);
		
		boolean isc = rService.modifyReserve(map);
		 
		model.addAttribute("seq", map.get("seq"));
		if(isc) {
			 return "redirect:/reservation/hospitalReserveDetail.do";
		 }
		
		return "redirect:/error/error.do";
	}
	
	/*
	 * 선택일 예약 목록 조회
	 */
	@RequestMapping(value = "/selectdayReserveList.do", method = RequestMethod.GET)
	public String selectdayReserveList(@RequestParam Map<String, Object>map, Model model, HttpSession session) {
		logger.info("ReservationController selectdayReserveList 선택일 예약 목록 조회 {}", map );
		
	      String reservedate = (String)map.get("reservedate");
	      String hospital_seq = (String)map.get("hospital_seq");
//	      MemberDto mDto =  (MemberDto)session.getAttribute("member");
//	      String user_email = mDto.getEmail();
	      
	      
	      Map<String, Object>rMap = new HashMap<String, Object>();
	      rMap.put("hospital_seq",hospital_seq);
	      rMap.put("reservedate",reservedate);
//	      map.put("user_email",user_email);
	      
//	      List<String>petLists = rService.getUserPet(map);

	      //반려 동물 목록
//	      model.addAttribute("userPet",petLists);
	      
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
	// 이게 맞음 20210731 04:28 am
	@RequestMapping(value = "/cancelReservation.do", method = RequestMethod.GET)
	public String cancelReservation(@RequestParam Map<String, Object> map, Model model) {
		logger.info("ReservationController cancelReservation 예약 취소 결제 보내기{}", map );
		//세션에 병원 seq 있으면 병원 관계자 결제 컨트롤러
		// 아니면 사용자 결제 컨트롤러
		
		String seq = (String)map.get("seq");
		String status =(String) map.get("status");
	
		model.addAttribute("seq", seq);
		model.addAttribute("status", status);
		
		//status도 보내주세요~~
		if(status.equals("S")) {
			//status 받아서 대기이면 환불 결제 컨트롤러
			return "redirect:/payment/userCancelPayRefund.do";//"대기 상태 취소 결제 컨트롤러";
		} else {
			//status 받아서 확정이면 환불X 결제 컨트롤러
			return "redirect:/payment/cancelPay.do";//"확정 상태 취소 결제 컨트롤러";
		}	
	}
	
	
	// 이게 맞음 20210731 04:28 am
	@RequestMapping(value="/cancelReserve.do", method=RequestMethod.GET)
	public String cancelReserve(@RequestParam Map<String, Object> map, Model model, HttpSession session) {
		logger.info("ReservationController cancelReserve 예약 취소하기{}", map);
		boolean isc = rService.cancelReserve(map);
		
		String seq = (String)map.get("seq");
		model.addAttribute("seq", seq);
		
		MemberDto mDto =  (MemberDto)session.getAttribute("member");
	    String userType = mDto.getUsertype();	
	    
		if(isc) {
			if(userType.equals("ROLE_USER")) {
				return "redirect:/reservation/userReserveDetail.do";
			}else if(userType.equals("ROLE_OPER")){
				return "redirect:/reservation/hospitalReserveDetail.do";
			}else {
				return "redirect:/error/error.do";
			}
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	
	/*
	 * 병원 관계자 예약 확정
	 */
	@RequestMapping(value = "/acceptReserve.do", method = RequestMethod.GET)
	public String acceptReserve(@RequestParam Map<String, Object>map) {
		logger.info("ReservationController acceptReserve 병원 관계자 예약 확정 {}", map );
		
		String seq = (String)map.get("seq");
		boolean isc = rService.acceptReserve(map);
//		ReservationDto rDto = rService.userAcceptDetail(map);
		
		if(isc) {
//			Util.sendReservation(rDto);
			return "redirect:/reservation/hospitalReserveDetail.do?seq="+seq;
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	/*
	 * 병원 관계자 예약 반려 commnet update
	 */
	// 이게 맞음 20210731 04:28 am
	@RequestMapping(value = "/rejectCommnetReserve.do", method = RequestMethod.POST)
	public String rejectReserve(@RequestParam Map<String,Object>map, Model model) {
		logger.info("ReservationController rejectCommnetReserve 예약 반려 코멘트 업데이트 {}", map );
		String seq = (String)map.get("seq");
		String status =(String) map.get("status");
		
		boolean isc = rService.rejectCommnetReserve(map);
		
		model.addAttribute("seq",seq);
		model.addAttribute("status",status);
		
		if(isc) {
			return "redirect:/payment/operCancelPayRefund.do";
		}else {
			return "redirect:/reservation/hospitalReserveDetail.do";
		}
	}
	
	
	//반려 status update
	@RequestMapping(value = "/rejectStatusReserve.do",method = RequestMethod.GET)
	public String rejectStatusReserve(@RequestParam Map<String, Object>map, Model model) {
		logger.info("ReservationController rejectStatusReserve 예약 반려 상태 업데이트  {}", map );
		
		String seq = (String) map.get("seq");
		boolean isc = rService.rejectStatusReserve(map);
		
		
		model.addAttribute("seq",seq);
		
		if(isc) {
			
			return "redirect:/reservation/hospitalReserveDetail.do";
			
		}else {
			
			return "redirect:/error/error.do";
		}		
	}


	
	@RequestMapping(value = "/getSunday.do", method = RequestMethod.GET)
	public String getSunday(@RequestParam Map<String,Object>map, String date, HttpServletResponse resp, Model model, HttpSession session) throws ParseException, IOException {
		logger.info("ReservationController getSunday 일요일 확인  {}",map );
		CalendarDate cal = new CalendarDate();
		boolean isc = cal.CalSunday(date);

		
		String msg = "일요일은 휴무입니다.";
		String redirectUri = "./moveCalendar.do?hospital_seq="+(map.get("hospital_seq")+"&hospital_name="+map.get("hospital_name)"));
		
		model.addAttribute("searchInfo",map);
		if(isc) {
			Util.PrintWriterMsg(resp, msg ,redirectUri);
		}	
//			model.addAttribute("reservedate",date);
			return "redirect:/reservation/moveCalendar.do";
//			return "redirect:/reservation/selectdayReserveList.do";
		
	}
	
	
	@RequestMapping(value = "/checkReservation.do", method = RequestMethod.GET)
	@ResponseBody
	public String checkReservation(@RequestParam Map<String,Object> map, String hospital_seq) {
		logger.info("ReservationController checkReservation 예약 중복 확인   {}",map );
		map.put("hospital_seq", hospital_seq);
		
		System.out.println("12312312312312312312"+map);
		boolean isc = rService.checkReservation(map);
		
		String resultIsc = String.valueOf(isc);
		
		return resultIsc;
	}
}
	