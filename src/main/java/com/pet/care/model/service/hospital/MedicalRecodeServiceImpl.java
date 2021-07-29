package com.pet.care.model.service.hospital;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.HospitalJoinDto;
import com.pet.care.dto.MedicalRecodeDto;
import com.pet.care.dto.MedicalRecodeJoinDto;
import com.pet.care.model.dao.hospital.IMedicalRecodeDao;

@Service
public class MedicalRecodeServiceImpl implements IMedicalRecodeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired IMedicalRecodeDao dao;

	// 진료내역 추가
	@Override
	public boolean insertRecode(MedicalRecodeDto dto) {
		logger.info("[insertRecode - {}] : 진료내역 추가", dto);
		return dao.insertRecode(dto);
	}

	// 병원 진료내역 리스트 조회
	@Override
	public List<MedicalRecodeJoinDto> recodeList(Map<String, Object> map) {
		logger.info("[recodeList - {}] : 병원 진료내역 리스트 조회", map);
		return dao.recodeList(map);
	}

	// 병원 진료내역 전채 글 갯수(페이징 쿼리)
	@Override
	public int recodeCount(Map<String, Object> map) {
		logger.info("[recodeCount - {}] : 병원 질료내역 전채 글 갯수(페이징 쿼리)", map);
		return dao.recodeCount(map);
	}

	// 진료내역 상세정보 조회
	@Override
	public MedicalRecodeJoinDto detailRecode(int seq) {
		logger.info("[detailRecode - {}] : 진료내역 상세정보 조회", seq);
		return dao.detailRecode(seq);
	}

	// 진료내역 수정
	@Override
	public boolean modifyRecode(MedicalRecodeDto dto) {
		logger.info("[modifyRecode - {}] : 진료내역 수정", dto);
		return dao.modifyRecode(dto);
	}
	
}
