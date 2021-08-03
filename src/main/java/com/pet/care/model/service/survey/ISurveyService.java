package com.pet.care.model.service.survey;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.SurveyDto;
import com.pet.care.dto.SurveyResultDto;

public interface ISurveyService {

	//(관리자) 설문 폼 리스트
	public List<SurveyDto> adminSurveyList(Map<String, Object> map);
	
	//(관리자) 설문 폼 작성
	public boolean insertSurveyForm(Map<String, Object> map);
	
	//(관리자) 설문 폼 배포기간 설정
	public boolean updateDateForm(Map<String, Object> map);
	
	//(관리자) 설문 폼 상세 페이지
	public SurveyDto surveyDetail(Map<String, Object> map);
	//(사용자) 설문 폼 상세 페이지
	public SurveyDto userSurveyDetail();
	
	//다중삭제
	public int delflagForm(Map<String, String[]> map);
	
	//설문 시작일 설정
	public boolean compareStartDate(Map<String, Object> map);
	//설문 마감일 설정
	public boolean compareEndDate(Map<String, Object> map);
	
	//(사용자) 설문 폼 제출
	public boolean userSurveySubmit(Map<String, Object> map);
	
	//설문 답변 작성자 중복확인
	public int checkSameResponser(Map<String, Object> map);

	//(사용자) 진행중인 설문 폼 리스트
	public List<SurveyDto> ongoingSurvey(Map<String, Object> map);
	
	//(사용자) 날짜 지난 설문 폼 리스트
	public List<SurveyDto> outOfDateSurvey(Map<String, Object> map);
	
	//(사용자) 진행중인 설문 날짜 체크
	public List<SurveyDto> ongoingDateCheck();
	
	//(사용자) 진행중인 설문 날짜 체크
	public List<SurveyDto> outOfDateCheck();
	
	//설문 결과 리스트
	public List<SurveyDto> surveyResultList(Map<String, Object> map);
	//설문 결과 리스트 상세
	public List<SurveyResultDto> surveyResultDetail(Map<String, Object> map);
	
	
	//페이징 쿼리
	//(관리자) 설문 폼 리스트
	public int adminSurveyListCount();
	//(사용자) 진행중인 설문 폼 리스트
	public int ongoingSurveyCount(Map<String, Object> map);
	//(사용자) 날짜 지난 설문 폼 리스트
	public int outOfDateSurveyCount(Map<String, Object> map);
	//설문 결과 리스트
	public int surveyResultListCount();
	
}
