package com.pet.care.model.service.hospital;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;
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
	
	//병원 상세정보 조회 
	@Override
	public HospitalInfoDto detailHospital(int seq) {
		logger.info("[detailHospital - {}] : 병원 상세정보 조회", seq);
		return dao.detailHospital(seq);
	}
	
	//병원 정보 수정
	@Override
	public boolean modifyHospital(HospitalInfoDto dto) {
		logger.info("[modifyHospital - {}] : 병원 정보 수정", dto);
		return dao.modifyHospital(dto);
	}
	
	//병원 정보 삭제
	@Override
	public boolean deleteHospital(HospitalInfoDto dto) {
		logger.info("[deleteHospital - {}] : 병원 정보 삭제", dto);
		return dao.deleteHospital(dto);
	}
	
}
