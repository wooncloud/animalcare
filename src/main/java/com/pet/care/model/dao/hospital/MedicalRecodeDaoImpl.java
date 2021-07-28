package com.pet.care.model.dao.hospital;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.MedicalRecodeDto;
import com.pet.care.dto.MedicalRecodeJoinDto;

@Repository
public class MedicalRecodeDaoImpl implements IMedicalRecodeDao {

	private final String NS = "com.pet.care.model.dao.hospital.IMedicalRecodeDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//진료내역 추가전 페이지에 기본 입력되어 있는 데이터
	@Override
	public MedicalRecodeJoinDto insertsBasicData(int seq) {	 
		return sqlSession.selectOne(NS+"insertBasicData", seq);
	}
	
	// 진료내역 추가
	@Override
	public boolean insertRecode(MedicalRecodeDto dto) {
		int n = sqlSession.insert(NS + "insertRecode", dto); 
		return (n > 0) ? true : false;
	}

	// 병원 진료내역 리스트 조회
	@Override
	public List<MedicalRecodeJoinDto> recodeList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"recodeList", map);
	}

	// 병원 진료내역 전채 글 갯수(페이징 쿼리)
	@Override
	public int recodeCount(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"recodeCount", map);
	}

	// 진료내역 상세정보 조회
	@Override
	public MedicalRecodeJoinDto detailRecode(int seq) {	 
		return sqlSession.selectOne(NS+"detailRecode", seq);
	}

	// 진료내역 수정
	@Override
	public boolean modifyRecode(MedicalRecodeDto dto) {
		int n = sqlSession.update(NS + "modifyRecode", dto);
		return (n > 0) ? true : false;
	}
	
}
