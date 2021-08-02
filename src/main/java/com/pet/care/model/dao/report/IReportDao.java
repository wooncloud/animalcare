package com.pet.care.model.dao.report;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.ReportDto;

public interface IReportDao {
	public List<ReportDto> allListReport();

	public ReportDto userWriteListReport(String email);

	public ReportDto operWriteListReport(String email);

	public int insertReport(ReportDto dto);

	public int modifyReport(Map<String, Object> map);

	public int adminDelReport(int seq);

	public int acceptanceReport(int seq);

	public int userCancleReport(int seq);
}
