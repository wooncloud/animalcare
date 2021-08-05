package com.pet.care.model.dao.survey;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.SurveyDto;
import com.pet.care.dto.SurveyResultDto;

@Repository
public class SurveyDaoImpl implements ISurveyDao {

	private final String NS="com.pet.care.model.dao.survey.ISurveyDao.";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<SurveyDto> adminSurveyList(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : adminSurveyList 설문 폼 리스트 - {}", map);
		return sqlSession.selectList(NS+"adminSurveyList", map);
	}

	@Override
	public boolean insertSurveyForm(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : insertSurveyForm 설문 폼 작성 - {} ", map);
		int isc = sqlSession.insert(NS+"insertSurveyForm", map);
		return (isc>0)?true:false; 
	}

	@Override
	public boolean updateDateForm(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : updateDateForm 설문 폼 배포기간 설정 - {} ", map);
		int isc = sqlSession.update(NS+"updateDateForm",map);
		return (isc>0)?true:false;
	}
	
	@Override
	public SurveyDto surveyDetail(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : surveyDetail 설문 폼 상세 - {}", map);
		return sqlSession.selectOne(NS+"surveyDetail", map);
	}
	
	@Override
	public SurveyDto userSurveyDetail() {
		logger.info("SurveyDaoImpl : userSurveyDetail 사용자 설문 폼 상세");
		return sqlSession.selectOne(NS+"userSurveyDetail");
	}
	

	@Override
	public int delflagForm(Map<String, String[]> map) {
		logger.info("SurveyDaoImpl : delflagForm 설문 폼 삭제 - {}", map);
		return sqlSession.update(NS+"delflagForm", map);
	}

	@Override
	public boolean compareStartDate(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : compareStartDate 설문 시작일 설정 - {}", map);
		int n = sqlSession.selectOne(NS+"compareStartDate", map);
		return (n>0)?true:false;
	}

	@Override
	public boolean compareEndDate(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : compareEndDate 설문 마감일 설정 - {}", map);
		int n = sqlSession.selectOne(NS+"compareEndDate", map);
		return (n>0)?true:false;
	}

	@Override
	public boolean userSurveySubmit(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : userSurveySubmit (사용자) 설문 폼 제출 - {}", map);
		int n = sqlSession.insert(NS+"userSurveySubmit", map);
		return (n>0)?true:false;
	}

	@Override
	public int checkEmptyResponser(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : checkEmptyResponser 설문 답변 유무 확인 - {}", map);
		return sqlSession.selectOne(NS+"checkEmptyResponser", map);
	}

	@Override
	public boolean insertFirstResponser(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : insertFirstResponser 설문 답변 비어 있을 때 작성자 등록 - {}", map);
		int n = sqlSession.insert(NS+"insertFirstResponser", map);
		return (n>0)?true:false;
	}

	@Override
	public boolean updateResponsers(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : updateResponsers 설문 답변 비어 있지 않을 때 작성자 추가 등록 - {}", map);
		int isc = sqlSession.update(NS+"updateResponsers", map);
		return (isc>0)?true:false;
	}

	@Override
	public int checkSameResponser(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : checkSameResponser 설문 답변 작성자 중복 확인 - {}", map);
		return sqlSession.selectOne(NS+"checkSameResponser", map);
	}
	
	@Override
	public List<SurveyDto> ongoingSurvey(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : ongoingSurvey (사용자) 진행중인 설문 폼 리스트", map);
		return sqlSession.selectList(NS+"ongoingSurvey", map);
	}

	@Override
	public List<SurveyDto> outOfDateSurvey(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : outOfDateSurvey (사용자) 날짜 지난 설문 폼 리스트", map);
		return sqlSession.selectList(NS+"outOfDateSurvey", map);
	}

	@Override
	public List<SurveyDto> ongoingDateCheck() {
		logger.info("SurveyDaoImpl : ongoingDateCheck (사용자) 진행중인 설문 날짜 체크");
		return sqlSession.selectList(NS+"ongoingDateCheck");
	}

	@Override
	public List<SurveyDto> outOfDateCheck() {
		logger.info("SurveyDaoImpl : outOfDateCheck (사용자) 진행중인 설문 날짜 체크");
		return sqlSession.selectList(NS+"outOfDateCheck");
	}

	@Override
	public List<SurveyDto> surveyResultList(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : surveyResultList 설문 결과 리스트 - {}", map);
		return sqlSession.selectList(NS+"surveyResultList", map);
	}

	@Override
	public List<SurveyResultDto> surveyResultDetail(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : surveyResultList 설문 결과 리스트 상세 - {}", map);
		return sqlSession.selectList(NS+"surveyResultDetail", map);
	}

	@Override
	public int adminSurveyListCount() {
		logger.info("SurveyDaoImpl : adminSurveyListCount 설문 폼 리스트 (페이징)");
		return sqlSession.selectOne(NS+"adminSurveyListCount");
	}

	@Override
	public int ongoingSurveyCount(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : ongoingSurveyCount 진행중인 설문 폼 리스트 (페이징) - {}", map);
		return sqlSession.selectOne(NS+"ongoingSurveyCount", map);
	}

	@Override
	public int outOfDateSurveyCount(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : outOfDateSurveyCount 날짜 지난 설문 폼 리스트 (페이징) - {}", map);
		return sqlSession.selectOne(NS+"outOfDateSurveyCount", map);
	}

	@Override
	public int surveyResultListCount() {
		logger.info("SurveyDaoImpl : surveyResultListCount 설문 결과 리스트 (페이징)");
		return sqlSession.selectOne(NS+"surveyResultListCount");
	}

		

}
