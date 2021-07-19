package com.pet.care.model.dao.hospital;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;

public interface IHospitalInfoDao {

	//회원 병원 리스트 조회
	public List<HospitalJoinDto> hospitalList(Map<String, Object> map);
	
	//조회된 병원 전체 갯수 (페이징 쿼리)
	public int hospitalCount(Map<String, Object> map);
	
	//병원 검색  
	public List<HospitalJoinDto> searchHospital(Map<String, Object> map);
	
	//검색된 병원 전채 갯수 (페이징 쿼리)
	public int searchCount(Map<String, Object> map);
	
	//병원 정보 입력
	public boolean insertHospital(HospitalInfoDto dto);
	
	//병원 상세정보 조회 
	public HospitalInfoDto detailHospital(int seq);
	
	//병원 정보 수정
	public boolean modifyHospital(HospitalInfoDto dto);
	
	//병원 정보 삭제  
	public boolean deleteHospital(HospitalInfoDto dto);
	
}
