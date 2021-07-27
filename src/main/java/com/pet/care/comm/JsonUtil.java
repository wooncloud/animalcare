package com.pet.care.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.ReservationDto;

public class JsonUtil {

	/**
	 * 공통코드 리스트를 JsonArray로 변환하는 메서드입니다.
	 * 
	 * @param codeList : List로 담긴 코드데이터들
	 * @return List를 JsonArray 형태로 반환함.
	 * @author WOO SEONG HO
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray CommonCodeJson(List<CodeDto> codeList) {
		JSONArray ja = new JSONArray();
		for (CodeDto code : codeList) {
			JSONObject j = new JSONObject();
			j.put("id", code.getCodeid());
			j.put("type", code.getCodetype());
			j.put("name", code.getCodename());
			
			ja.add(j);
		}
		
		return ja;
	}
	
	/**
	 * 
	 * 병원일정과 예약정보를 받아서 Toast Calender에 사용할 수 있는 Json data로 변경하는 메서드
	 * 
	 * @param HospitalScheduleList : 월간 병원일정 리스트
	 * @param reservationList : 월간 예약 리스트
	 * @return 월간 병원일정 및 예약 리스트를 Json으로 병합한 데이터
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray CalenderJson(List<HospitalScheduleDto> HospitalScheduleList, List<ReservationDto> reservationList) {
		JSONArray ja = new JSONArray();
		//병원 스케줄 하고 예약 리스트하고 집어넣으면 JSON으로 뽑아
		for (ReservationDto r : reservationList) {
			JSONObject j = new JSONObject();
			j.put("id", String.valueOf(r.getSeq()));
			j.put("calendarId", "예약완료");
			j.put("title", r.getReservetype()); // 제목 어떻게 할지 정해야 함.
			j.put("start",r.getReservedate()+"T"+ r.getReservetime().substring(0,2)+":"+r.getReservetime().substring(2,4)+":00");
//			SimpleDateFormat rReserve = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
//			String reserve = rReserve.format(r.getReservedate()+r.getReservetime());
//			j.put("start", reserve);
			j.put("end", r.getReservedate()+"T"+ r.getReservetime().substring(4,6)+":"+r.getReservetime().substring(6,8)+":00");
			// 예약 상태에 따른 다른 색깔
			j.put("category", "time");
			j.put("color", "#ffffff");
			j.put("bgColor", "#28B463");
			j.put("dragBgColor", "#28B463");
			j.put("borderColor", "#28B463");
			
			ja.add(j);
		}
		
		for (HospitalScheduleDto s : HospitalScheduleList) {
			JSONObject j = new JSONObject();
			j.put("id", String.valueOf(s.getSeq()));
			j.put("calendarId", "병원일정");
			j.put("title", s.getTitle());
//			SimpleDateFormat sSchedule = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			String schedule = sSchedule.format(s.getSchedule());
//			System.out.println("스케줄"+schedule);
//			j.put("start", schedule);
			j.put("category", "time");
			j.put("color", "#ffffff");
			j.put("bgColor", "#A569BD");
			j.put("dragBgColor", "#A569BD");
			j.put("borderColor", "#A569BD");
			
			ja.add(j);
		}
		
		return ja;
	}
}
