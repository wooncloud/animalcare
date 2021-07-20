package com.pet.care.model.dao.hospital;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.HospitalScheduleDto;

@Repository
public class HospitalScheduleDaoImpl implements IHospitalScheduleDao {

	private final String NS = "com.pet.care.model.dao.hospital.IHospitalScheduleDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//병원 일정 등록
	@Override
	public boolean insertSchedule(HospitalScheduleDto dto) {
		int n = sqlSession.insert(NS + "insertSchedule", dto); 
		return (n > 0) ? true : false;
	}

	// 병원 일정 조회(관계자) 월별
	@Override
	public List<HospitalScheduleDto> monthSchedule(Map<String, Object> map) {
		return sqlSession.selectList(NS+"monthSchedule", map);
	}

	// 병원  오늘 일정 조회(관계자) 
	@Override
	public List<HospitalScheduleDto> todaySchedule(Map<String, Object> map) {
		return sqlSession.selectList(NS+"todaySchedule", map);
	}

	// 병원 일정 상세정보 조회
	@Override
	public HospitalScheduleDto detailSchedule(int seq) {
		return sqlSession.selectOne(NS+"detailSchedule", seq);
	}

	// 병원 일정 수정
	@Override
	public boolean modifySchedule(HospitalScheduleDto dto) {
		int n = sqlSession.update(NS + "modifySchedule", dto);
		return (n > 0) ? true : false;
	}

	// 병원 일정 삭제
	@Override
	public boolean deleteSchedule(HospitalScheduleDto dto) {
		int n = sqlSession.update(NS + "deleteSchedule", dto);
		return (n > 0) ? true : false;
	}
	
}
