package com.pet.care.model.service.hospital;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.PetTypeDto;

public interface IHospitalInfoService {

	//회원 병원 리스트 조회
	public List<HospitalJoinDto> hospitalList(Map<String, Object> map);
		
	//조회된 병원 전체 갯수 (페이징 쿼리)
	public int hospitalCount(Map<String, Object> map);
	
	//진료항목 전체조회(페이징x 검색 기능 위한 쿼리)
	public List<CodeDto> petTypeList();
	
	//병원 검색  
	public List<HospitalJoinDto> searchHospital(Map<String, Object> map);
	
	//검색된 병원 전채 갯수 (페이징 쿼리)
	public int searchCount(Map<String, Object> map);
	
//	//병원 정보+치료가능항목 입력
//	public boolean insertHpAndPet(HospitalJoinDto dto);
	
	//병원 정보 입력
	public boolean insertHospital(HospitalInfoDto dto);
		
	//병원 정보 입력시 치료가능 항목 같이 추가
	public boolean insertPetType(PetTypeDto dto);
	
	//병원 상세정보 조회 - 사용자
	public HospitalJoinDto detailHospital(int seq);
	
	//병원 상세정보 조회 - 병원 관계자
	public HospitalJoinDto detailHospitalOper(String email);
	
	//병원 정보 수정
	public boolean modifyHospital(HospitalInfoDto dto);
	//병원정보 수정시 기존 진료항목 전체 삭제
	public boolean deletePetType(PetTypeDto dto);
	// 진료항목 전체 삭제 후 선택한 진료항목 새로 입력
	public boolean modifyPetType(PetTypeDto dto);
	
	//병원 정보 삭제  
	public boolean deleteHospital(int seq);
	
	//진료내역 추가전 페이지에 기본 입력되어 있는 데이터
	public HospitalJoinDto insertsBasicData(int seq);
	
}
