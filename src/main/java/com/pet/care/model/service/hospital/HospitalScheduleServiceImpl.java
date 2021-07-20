package com.pet.care.model.service.hospital;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.HospitalScheduleDto;
import com.pet.care.model.dao.hospital.IHospitalScheduleDao;

@Service
public class HospitalScheduleServiceImpl implements IHospitalScheduleService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired IHospitalScheduleDao dao;

	//병원 일정 등록
	@Override
	public boolean insertSchedule(HospitalScheduleDto dto) {
		logger.info("[insertSchedule - {}] : 병원 일정 등록", dto);
		return dao.insertSchedule(dto);
	}

	// 병원 일정 조회(관계자) 월별
	@Override
	public List<HospitalScheduleDto> monthSchedule(Map<String, Object> map) {
		logger.info("[monthSchedule - {}] : 병원 일정 조회(관계자) 월별", map);
		return dao.monthSchedule(map);
	}

	// 병원  오늘 일정 조회(관계자) 
	@Override
	public List<HospitalScheduleDto> todaySchedule(Map<String, Object> map) {
		logger.info("[todaySchedule - {}] : 병원  오늘 일정 조회(관계자) ", map);
		return dao.todaySchedule(map);
	}

	// 병원 일정 상세정보 조회
	@Override
	public HospitalScheduleDto detailSchedule(int seq) {
		logger.info("[detailSchedule - {}] : 병원 일정 상세정보 조회", seq);
		return dao.detailSchedule(seq);
	}

	// 병원 일정 수정
	@Override
	public boolean modifySchedule(HospitalScheduleDto dto) {
		logger.info("[modifySchedule - {}] : 병원 일정 수정", dto);
		return dao.modifySchedule(dto);
	}

	// 병원 일정 삭제
	@Override
	public boolean deleteSchedule(HospitalScheduleDto dto) {
		logger.info("[deleteSchedule - {}] : 병원 일정 삭제", dto);
		return dao.deleteSchedule(dto);
	}
	
}
