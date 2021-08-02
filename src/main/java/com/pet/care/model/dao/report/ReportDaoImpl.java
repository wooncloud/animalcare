package com.pet.care.model.dao.report;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.ReportDto;

@Repository
public class ReportDaoImpl implements IReportDao {

	private final String NS = "com.pet.care.model.dao.report.IReportDao.";
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 관리자 신고 조회
	 */
	@Override
	public List<ReportDto> allListReport() {
		return sqlSession.selectList(NS + "allListReport");
	}

	/**
	 * 사용자 신고 조회 (일반 사용자)
	 */
	@Override
	public ReportDto userWriteListReport(String email) {
		return sqlSession.selectOne(NS + "userWriteListReport", email);
	}

	/**
	 * 사용자 신고 조회 (병원관계자)
	 */
	@Override
	public ReportDto operWriteListReport(String email) {
		return sqlSession.selectOne(NS + "operWriteListReport", email);
	}

	/**
	 * 신고 작성
	 */
	@Override
	public int insertReport(ReportDto dto) {
		return sqlSession.insert(NS + "insertReport", dto);
	}

	/**
	 * 신고 수정
	 */
	@Override
	public int modifyReport(Map<String, Object> map) {
		return sqlSession.update(NS + "modifyReport", map);
	}

	/**
	 * 신고 삭제
	 */
	@Override
	public int adminDelReport(int seq) {
		return sqlSession.update(NS + "adminDelReport", seq);
	}

	/**
	 * 신고 처리
	 */
	@Override
	public int acceptanceReport(int seq) {
		return sqlSession.update(NS + "acceptanceReport", seq);
	}

	/**
	 * 신고 철회
	 */
	@Override
	public int userCancleReport(int seq) {
		return sqlSession.update(NS + "userCancleReport", seq);
	}

}
