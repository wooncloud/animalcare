package com.pet.care.model.dao.hospital;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.HospitalScheduleDto;

public interface IHospitalScheduleDao {

	//병원 seq구하기용
	public int findSeq(String email);
	
	//병원 일정 등록
	public boolean insertSchedule(HospitalScheduleDto dto);
	
	// 병원 일정 조회(관계자) 월별
	public List<HospitalScheduleDto> monthSchedule(Map<String, Object> map);
	
	// 병원  오늘 일정 조회(관계자) 
	public List<HospitalScheduleDto> todaySchedule(Map<String, Object> map);
	
	// 병원 일정 상세정보 조회
	public HospitalScheduleDto detailSchedule(int seq);
	
	// 병원 일정 수정
	public boolean modifySchedule(HospitalScheduleDto dto);
	
	// 병원 일정 삭제
	public boolean deleteSchedule(int seq);
	
}
