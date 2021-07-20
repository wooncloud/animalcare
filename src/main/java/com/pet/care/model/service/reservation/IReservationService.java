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
	
	//병원 관계자 예약 신청 완료 처리//
	public boolean acceptReserve(Map<String, Object>map);
	
	//병원 관계자 예약 신청 반려 처리//
	public boolean rejectReserve(Map<String, Object>map);
	
	//예약 취소
	public boolean cancelReserve(Map<String, Object>map);
	
	//병원 관계자 예약 수정
	public boolean modifyReserve(Map<String, Object>map);
	
	//미처리 예약 목록 보기
	public ReservationDto standReserveList(Map<String, Object>map);
	
	//병원 오늘의 예약 보기
	public List<ReservationDto> todayReserveList(Map<String, Object>map);
}