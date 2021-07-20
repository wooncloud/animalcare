package com.pet.care;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.care.dto.ReservationDto;
import com.pet.care.model.service.reservation.IReservationService;

@Controller
public class ReservationController {

	@Autowired
	private IReservationService service;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 사용자 예약 목록 조회
	 */
	@RequestMapping(value = "/userReserveList.do", method = RequestMethod.GET)
	public String userReserveList(HttpSession session, Model model) {
		
		logger.info("ReservationController userReserveList 사용자 예약 목록 조회  " );
		//user_email은 세션에서 가져와야함
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", "user01@gmail.com");
		List<ReservationDto> lists = service.userReserveList(map);
		
		model.addAttribute("lists",lists);
	
		return "reservation/index";
		
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
		ReservationDto rDto = service.userRejectDetail(map);
		
		model.addAttribute("rejectDto",rDto);
		
		return "reservation/userRejectDetail";
		
	}
	
	
	/*
	 * 사용자 예약 상세 조회
	 */
	@RequestMapping(value = "/userReserveDetail.do", method = RequestMethod.GET)
	public String userReserveDetail(Model model) {
		
		logger.info("ReservationController userRejectDetail 사용자 예약 상세 조회 " );
		//user_email은 세션에서 가져와야함
		//seq는 예약번호 request
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", "user01@gmail.com");
		map.put("seq", 102);
		ReservationDto rDto = service.userReserveDetail(map);
		
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
		
		List<ReservationDto> lists = service.todayReserveList(map);
		model.addAttribute("todayLists",lists);
		
		
		return "reservation/todayReserveList";
		
	}
	
	@RequestMapping(value = "/hospitalReserveList.do", method = RequestMethod.GET)
	public String hospitalReserveList(Model model) {
		//seq 병원번호 session
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("seq", 3);
		
		List<ReservationDto>lists = service.hospitalReserveList(map);
		
		model.addAttribute("lists",lists);
		return "reservation/hospitalReserveList";
	}
}
