package com.pet.care.model.service.reservation;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.ReservationDto;

public interface IReservationService {

	//사용자 예약신청
	public boolean insertReserve(ReservationDto rDto);
	
	//사용자 예약 신청 목록 전체 조회//
	public List<ReservationDto> userReserveList(Map<String, Object>map);
	
	//사용자 예약 신청 내용 상세 조회(대기, 완료, 취소)//
	public ReservationDto userReserveDetail(Map<String, Object>map);
	
	//사용자 예약 반려 상세 조회//
	public ReservationDto userRejectDetail(Map<String, Object>map);

	//병원 예약 신청 목록 전체 조회//
	public List<ReservationDto> hospitalReserveList(Map<String, Object>map);
	
	//병원 예약 상세 조회 
	public ReservationDto hospitalReserveDetail(Map<String, Object>map);
	
	//병원 관계자 예약 신청 완료 처리//
	public boolean acceptReserve(Map<String, Object>map);
	
	//병원 관계자 예약 신청 반려 처리//
	public boolean rejectReserve(Map<String, Object>map);
	
	//사용자 대기 예약 취소
	public boolean cancelReserve(Map<String, Object>map);

	//병원 관계자 예약 수정
	public boolean modifyReserve(Map<String, Object>map);
	
	//미처리 예약 목록 보기
	public List<ReservationDto> hospitalStandReserveList(Map<String, Object>map);
	
	//오늘의 예약 목록 보기
	public List<ReservationDto> todayReserveList(Map<String, Object>map);
	
	//사용자 반려 동물 목록
	public List<String> getUserPet(Map<String, Object>map);
	
	//병원 해당일 목록 예약 보기
	public List<ReservationDto> selectdayReserveList(Map<String, Object>map);
	
	//사용자 예약 목록 페이징 
	public int userReserveListCount(Map<String, Object>map);
	
	//병원 관계자 예약 처리 목록 페이징
	public int hospitalReserveListCount(Map<String, Object>map);
	
	//병원 관계자 예약 미처리 목록 페이징
	public int hospitalStandReserveListCount(Map<String, Object>map);
	
	//병원 예약 리스트 달력
	public List<ReservationDto> hospitalCalendarList(Map<String, Object>map);
	
	//예약 중복 검사
	public boolean checkReservation(Map<String, Object>map);
	
	//예약 확정 상세 보기
	public ReservationDto userAcceptDetail(Map<String, Object>map);
}
