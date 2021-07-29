package com.pet.care.model.service.survey;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.care.dto.SurveyDto;
import com.pet.care.model.dao.survey.ISurveyDao;

@Service
public class SurveyServiceImpl implements ISurveyService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISurveyDao iDao;
	
	@Override
	public List<SurveyDto> adminSurveyList() {
		logger.info("SurveyServiceImpl : adminSurveyList 설문 폼 리스트");
		return iDao.adminSurveyList();
	}

	@Override
	public boolean insertSurveyForm(Map<String, Object> map) {
		logger.info("SurveyServiceImpl : insertSurveyForm 설문 폼 작성 - {}",map);
		return iDao.insertSurveyForm(map);
	}

	@Override
	public boolean updateDateForm(Map<String, Object> map) {
		logger.info("SurveyServiceImpl : updateDateForm 설문 폼 배포기간 설정 - {} ", map);
		return iDao.updateDateForm(map);
	}

	@Override
	public SurveyDto surveyDetail(Map<String, Object> map) {
		logger.info("SurveyServiceImpl : surveyDetail 설문 폼 상세 - {}",map);
		return iDao.surveyDetail(map);
	}

	@Override
	public int delflagForm(Map<String, String[]> map) {
		logger.info("SurveyServiceImpl : delflagForm 설문 폼 삭제 - {}",map);
		return iDao.delflagForm(map);
	}

	@Override
	public boolean compareStartDate(Map<String, Object> map) {
		logger.info("SurveyServiceImpl : compareStartDate 설문 시작일 설정 - {}",map);
		return iDao.compareStartDate(map);
	}

	@Override
	public boolean compareEndDate(Map<String, Object> map) {
		logger.info("SurveyServiceImpl : compareEndDate 설문 마감일 설정 - {}",map);
		return iDao.compareEndDate(map);
	}

	@Transactional
	@Override
	public boolean userSurveySubmit(Map<String, Object> map) {
		logger.info("SurveyServiceImpl : userSurveySubmit (사용자) 설문 폼 제출 과정 시작 - {}", map);
		
		boolean isc1 = false, isc2 = false, isc3 = false;

		logger.info("SurveyServiceImpl : userSurveySubmit : checkEmptyResponser 설문 답변 유무 확인 - {}", map);
		int n = iDao.checkEmptyResponser(map);
		
		if(n==0) {
			logger.info("SurveyServiceImpl : userSurveySubmit : insertFirstResponser 최초 작성자 등록 - {}", map);
			isc1 = iDao.insertFirstResponser(map);
		} else {
			logger.info("SurveyServiceImpl : userSurveySubmit : checkSameResponser 작성자 중복 확인 - {}", map);
			int chk = iDao.checkSameResponser(map);
			if(chk==1) {
				return isc2 ;
			}else {
				logger.info("SurveyServiceImpl : userSurveySubmit : updateResponsers 작성자 추가 등록 - {}", map);
				isc3 = iDao.updateResponsers(map);
			}
		}
		
		logger.info("SurveyServiceImpl : userSurveySubmit (사용자) 설문 폼 제출 완료 - {}", map);
		boolean isc4=iDao.userSurveySubmit(map);

		return (isc1||isc4)|(isc2)|(isc3||isc4)?true:false;
	}

	
}
