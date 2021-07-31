package com.pet.care.model.service.hospital;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.PetTypeDto;
import com.pet.care.model.dao.hospital.IHospitalInfoDao;

@Service
public class HospitalInfoServiceImpl implements IHospitalInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired IHospitalInfoDao dao;
	
	//회원 병원 리스트 조회
	@Override
	public List<HospitalJoinDto> hospitalList(Map<String, Object> map) {
		logger.info("[hospitalList - {}] : 병원 리스트 조회", map);
		return dao.hospitalList(map);
	}
	
	//조회된 병원 전체 갯수 (페이징 쿼리)	
	@Override
	public int hospitalCount(Map<String, Object> map) {
		logger.info("[hospitalCount - {}] : 조회된 병원 전체 갯수", map);
		return dao.hospitalCount(map);
	}
	
	//진료항목 전체조회(페이징x 검색 기능 위한 쿼리)
	@Override
	public List<CodeDto> petTypeList() {
		logger.info("[hospitalList - {}] : 진료항목 전체조회", dao.petTypeList());
		return dao.petTypeList();
	}
	
	//병원 검색  
	@Override
	public List<HospitalJoinDto> searchHospital(Map<String, Object> map) {
		logger.info("[searchHospital - {}] : 병원 검색", map);
		return dao.searchHospital(map);
	}
	
	//검색된 병원 전채 갯수 (페이징 쿼리)
	@Override
	public int searchCount(Map<String, Object> map) {
		logger.info("[searchCount - {}] : 검색된 병원 전채 갯수", map);
		return dao.searchCount(map);
	}
	
	//병원 정보 입력
	@Override
	public boolean insertHospital(HospitalInfoDto dto) {
		logger.info("[insertHospital - {}] : 병원 정보 입력", dto);
		return dao.insertHospital(dto);
	}
		
	//병원 정보 입력시 치료가능 항목 같이 추가
	@Override
	public boolean insertPetType(PetTypeDto dto) {
		logger.info("[insertPetType - {}] : 병원 치료가능 항목", dto);
		return dao.insertPetType(dto);
	}	
	
	//병원 상세정보 조회 - 사용자
	@Override
	public HospitalJoinDto detailHospital(int seq) {
		logger.info("[detailHospital - {}] : 병원 상세정보 조회", seq);
		return dao.detailHospital(seq);
	}
	
	//병원 상세정보 조회 - 병원 관계자
	@Override
	public HospitalJoinDto detailHospitalOper(String email) {
		logger.info("[detailHospitalOper - {}] : 병원 상세정보 조회", email);
		return dao.detailHospitalOper(email);
	}
	
	//병원 정보 수정
	@Override
	public boolean modifyHospital(HospitalInfoDto dto) {
		logger.info("[modifyHospital - {}] : 병원 정보 수정", dto);
		return dao.modifyHospital(dto);
	}
	//병원정보 수정시 기존 진료항목 전체 삭제
	@Override
	public boolean deletePetType(PetTypeDto dto) {
		logger.info("[deletePetType - {}] : 병원 정보 수정", dto);
		return dao.deletePetType(dto);
	}
	// 진료항목 전체 삭제 후 선택한 진료항목 새로 입력
	@Override
	public boolean modifyPetType(PetTypeDto dto) {
		logger.info("[modifyPetType - {}] : 병원 정보 수정", dto);
		return dao.modifyPetType(dto);
	}
	
	//병원 정보 삭제
	@Override
	public boolean deleteHospital(int seq) {
		logger.info("[deleteHospital - {}] : 병원 정보 삭제", seq);
		return dao.deleteHospital(seq) > 0 ? true : false;
	}
	
	//진료내역 추가전 페이지에 기본 입력되어 있는 데이터
	@Override
	public HospitalJoinDto insertsBasicData(int seq) {
		logger.info("[insertsBasicData - {}] : 진료내역 상세정보 조회", seq);
		return dao.insertsBasicData(seq);
	}
	
}
