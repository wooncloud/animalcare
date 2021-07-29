package com.pet.care.model.service.survey;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.SurveyDto;

public interface ISurveyService {

	//(관리자) 설문 폼 리스트
	public List<SurveyDto> adminSurveyList();
	
	//(관리자) 설문 폼 작성
	public boolean insertSurveyForm(Map<String, Object> map);
	
	//(관리자) 설문 폼 배포기간 설정
	public boolean updateDateForm(Map<String, Object> map);
	
	//설문 폼 상세 페이지
	public SurveyDto surveyDetail(Map<String, Object> map);
	
	//다중삭제
	public int delflagForm(Map<String, String[]> map);
	
	//설문 시작일 설정
	public boolean compareStartDate(Map<String, Object> map);
	//설문 마감일 설정
	public boolean compareEndDate(Map<String, Object> map);
	
	//(사용자) 설문 폼 제출
	public boolean userSurveySubmit(Map<String, Object> map);
}
