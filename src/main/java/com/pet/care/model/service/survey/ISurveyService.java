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
}
