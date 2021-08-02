package com.pet.care.model.service.report;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.ReportDto;
import com.pet.care.model.dao.report.IReportDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements IReportService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IReportDao dao;

	/**
	 * 관리자 신고 조회
	 */
	@Override
	public List<ReportDto> allListReport() {
		logger.info("allListReport");
		return dao.allListReport();
	}

	/**
	 * 사용자 신고 조회 (일반 사용자)
	 */
	@Override
	public ReportDto userWriteListReport(String email) {
		logger.info("userWriteListReport - {}", email);
		return dao.userWriteListReport(email);
	}

	/**
	 * 사용자 신고 조회 (병원관계자)
	 */
	@Override
	public ReportDto operWriteListReport(String email) {
		logger.info("operWriteListReport - {}", email);
		return dao.operWriteListReport(email);
	}

	/**
	 * 신고 작성
	 */
	@Override
	public boolean insertReport(ReportDto dto) {
		logger.info("insertReport - {}", dto);
		return dao.insertReport(dto) > 0 ? true : false;
	}

	/**
	 * 신고 수정
	 */
	@Override
	public boolean modifyReport(Map<String, Object> map) {
		logger.info("modifyReport - {}", map);
		return dao.modifyReport(map) > 0 ? true : false;
	}

	/**
	 * 신고 삭제
	 */
	@Override
	public boolean adminDelReport(int seq) {
		logger.info("adminDelReport - {}", seq);
		return dao.adminDelReport(seq) > 0 ? true : false;
	}

	/**
	 * 신고 처리
	 */
	@Override
	public boolean acceptanceReport(int seq) {
		logger.info("acceptanceReport - {}", seq);
		return dao.acceptanceReport(seq) > 0 ? true : false;
	}

	/**
	 * 신고 철회
	 */
	@Override
	public boolean userCancleReport(int seq) {
		logger.info("userCancleReport - {}", seq);
		return dao.userCancleReport(seq) > 0 ? true : false;
	}

}
