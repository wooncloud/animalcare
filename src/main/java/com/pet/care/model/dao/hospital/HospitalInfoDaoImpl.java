package com.pet.care.model.dao.hospital;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.CodeDto;
import com.pet.care.dto.HospitalInfoDto;
import com.pet.care.dto.HospitalJoinDto;

@Repository
public class HospitalInfoDaoImpl implements IHospitalInfoDao {

	private final String NS = "com.pet.care.model.dao.hospital.IHospitalInfoDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//회원 병원 리스트 조회
	@Override
	public List<HospitalJoinDto> hospitalList(Map<String, Object> map) { 
		return sqlSession.selectList(NS+"hospitalList", map);
	}

	//조회된 병원 전체 갯수 (페이징 쿼리)	
	@Override
	public int hospitalCount(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"hospitalCount", map);
	}
	
	//진료항목 전체조회(페이징x 검색 기능 위한 쿼리)
	@Override
	public List<CodeDto> petTypeList() {
		return sqlSession.selectList(NS+"petTypeList");
	}

	//병원 검색  
	@Override
	public List<HospitalJoinDto> searchHospital(Map<String, Object> map) {
		return sqlSession.selectList(NS+"searchHospital", map);
	}
	
	//검색된 병원 전채 갯수 (페이징 쿼리)
	@Override
	public int searchCount(Map<String, Object> map) { 
		return sqlSession.selectOne(NS+"searchCount", map);
	}
	
	//병원 정보 입력
	@Override
	public boolean insertHospital(HospitalInfoDto dto) {
		int n = sqlSession.insert(NS + "insertHospital", dto); 
		return (n > 0) ? true : false;
	}
	
	//병원 상세정보 조회 
	@Override
	public HospitalJoinDto detailHospital(int seq) {
		return sqlSession.selectOne(NS+"detailHospital", seq);
	}
	
	//병원 정보 수정
	@Override
	public boolean modifyHospital(HospitalInfoDto dto) {
		int n = sqlSession.update(NS + "modifyHospital", dto);
		return (n > 0) ? true : false;
	}

	//병원 정보 삭제
	@Override
	public int deleteHospital(int seq) {
		return sqlSession.update(NS + "deleteHospital", seq);
	}


}
