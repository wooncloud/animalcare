package com.pet.care.model.dao.survey;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.SurveyDto;
import com.pet.care.dto.SurveyResultDto;

public interface ISurveyDao {

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
	
	//설문 답변 유무 확인
	public int checkEmptyResponser(Map<String, Object> map);
	//설문 답변 비어 있을 때 작성자 등록
	public boolean insertFirstResponser(Map<String, Object> map);
	//설문 답변 비어 있지 않을 때 작성자 추가
	public boolean updateResponsers(Map<String, Object> map);

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
	public List<SurveyDto> surveyResultList();
	//설문 결과 리스트 상세
	public List<SurveyResultDto> surveyResultDetail(Map<String, Object> map);
	
	
}
