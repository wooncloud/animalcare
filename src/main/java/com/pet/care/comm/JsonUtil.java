package com.pet.care.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.dto.ReservationDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

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
	 * 
	 * @author WOO SEONG HO
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray CalenderJson(List<HospitalScheduleDto> HospitalScheduleList, List<ReservationDto> reservationList) {
		JSONArray ja = new JSONArray();
		//병원 스케줄 하고 예약 리스트하고 집어넣으면 JSON으로 뽑아
		for (ReservationDto r : reservationList) {
			JSONObject j = new JSONObject();
			j.put("id", String.valueOf(r.getSeq()));
			j.put("calendarId", "예약완료");
			j.put("title", r.getReservetype()+"/"+r.getReservetime().substring(0,2)+":"+r.getReservetime().substring(2,4)); // 제목 어떻게 할지 정해야 함.
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
	
	/**
	 * jwt 토큰을 decode해 줍니다.
	 * @param jwt
	 * @return decode된 header, payload
	 * 
	 * @author WOO SEONG HO
	 */
	public static Map<String, String> decodeJWT(String jwt) {
		String[] chunks = jwt.split("\\.");
    	Base64.Decoder decoder = Base64.getDecoder();

    	String header = new String(decoder.decode(chunks[0]));
    	String payload = new String(decoder.decode(chunks[1]));
    	
    	Map<String, String> result = new HashMap<String, String>();
    	result.put("header", header);
    	result.put("payload", payload);
    	
    	return result;
	}
	
	
	/**
	 * jwt 토큰 검증 
	 * @param jwt 토큰
	 * @return claimMap
	 * @throws UnsupportedEncodingException
	 * 
	 * @author WOO SEONG HO
	 */
    public static Map<String, Object> verifyJWT(String jwt, String key) {
    	Map<String, Object> claimMap = null;
    	
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            claimMap = claims;

            //Date expiration = claims.get("exp", Date.class);
            //String data = claims.get("data", String.class);
            
        } catch (ExpiredJwtException e) {
        	// 토큰이 만료되었을 경우
            e.printStackTrace();
        } catch (Exception e) {
        	// 그외 에러났을 경우
            e.printStackTrace();
        }
        
        return claimMap;
    } 
}
