package com.pet.care.model.service.report;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.ReportDto;

public interface IReportService {
	public List<ReportDto> allListReport();

	public ReportDto userWriteListReport(String email);

	public ReportDto operWriteListReport(String email);

	public boolean insertReport(ReportDto dto);

	public boolean modifyReport(Map<String, Object> map);

	public boolean adminDelReport(int seq);

	public boolean acceptanceReport(int seq);

	public boolean userCancleReport(int seq);
}
