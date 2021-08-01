package com.pet.care.comm;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.ReservationDto;

public class JsonUtil {

	/**
	 * 문자열을 JSON 객체로 변환해 줍니다.<br>
	 * org.json.simple을 이용한 메서드 입니다.
	 * 
	 * @param str : 변환하고자 하는 json 형식의 문자열
	 * @return org.json.simple.JSONObject 형식의 Json 객체
	 */
	public static JSONObject stringToJson(String str) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;

		try {
			jsonObj = (JSONObject) parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jsonObj;
	}
	
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
			j.put("id", r.getSeq());
			j.put("calendarId", "예약");
			j.put("title", r.getReservetype()); // 제목 어떻게 할지 정해야 함.
			j.put("start", r.getReservetime());
			
			// 예약 상태에 따른 다른 색깔
			j.put("category", "task");
			j.put("color", "#ffffff");
			j.put("bgColor", "#28B463");
			j.put("dragBgColor", "#28B463");
			j.put("borderColor", "#28B463");
			
			ja.add(j);
		}
		
		for (HospitalScheduleDto s : HospitalScheduleList) {
			JSONObject j = new JSONObject();
			j.put("id", s.getSeq());
			j.put("calendarId", "병원일정");
			j.put("name", s.getTitle());
			j.put("start", s.getSchedule());
			
			j.put("category", "task");
			j.put("color", "#ffffff");
			j.put("bgColor", "#A569BD");
			j.put("dragBgColor", "#A569BD");
			j.put("borderColor", "#A569BD");
			
			ja.add(j);
		}
		
		return ja;
	}
}
