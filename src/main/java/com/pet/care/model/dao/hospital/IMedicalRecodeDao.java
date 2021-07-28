package com.pet.care.model.dao.hospital;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.MedicalRecodeDto;
import com.pet.care.dto.MedicalRecodeJoinDto;

public interface IMedicalRecodeDao {

	//진료내역 추가전 페이지에 기본 입력되어 있는 데이터
	public MedicalRecodeJoinDto insertsBasicData(int seq);
	
	// 진료내역 추가
	public boolean insertRecode(MedicalRecodeDto dto);
	
	// 병원 진료내역 리스트 조회
	public List<MedicalRecodeJoinDto> recodeList(Map<String, Object> map);
	
	// 병원 진료내역 전채 글 갯수(페이징 쿼리)
	public int recodeCount(Map<String, Object> map);

	// 진료내역 상세정보 조회
	public MedicalRecodeJoinDto detailRecode(int seq);
	
	// 진료내역 수정
	public boolean modifyRecode(MedicalRecodeDto dto);
	
}
