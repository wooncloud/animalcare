package com.pet.care.model.service.answerboard;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.AnswerBoardDto;
import com.pet.care.dto.ReservationDto;
import com.pet.care.model.dao.answerboard.IAnswerBoardDao;

@Service
public class AnswerBoardServiceImpl implements IAnswerBoardService {

	@Autowired
	private IAnswerBoardDao aDao;
	
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean insertUserBoard(AnswerBoardDto dto) {
		logger.info("AnswerBoardServiceImpl insertUserBoard {} ", dto);
		return aDao.insertUserBoard(dto);
	}

	@Override
	public boolean insertNonUserBoard(AnswerBoardDto dto) {
		logger.info("AnswerBoardServiceImpl insertNonUserBoard {} ", dto);
		return aDao.insertNonUserBoard(dto);
	}

	@Override
	public boolean modifyUserBoard(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl modifyUserBoard {} ", map);
		return aDao.modifyNonUserBoard(map);
	}

	@Override
	public boolean modifyNonUserBoard(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl modifyNonUserBoard {} ", map);
		return aDao.modifyNonUserBoard(map);
	}

	@Override
	public boolean deleterBoard(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl deleterBoard {} ", map);
		return aDao.deleterBoard(map);
	}

	@Override
	public List<AnswerBoardDto> selUserBoard(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl selUserBoard {} ", map);
		return aDao.selUserBoard(map);
	}

	@Override
	public List<AnswerBoardDto> selNonUserBoard(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl selNonUserBoard {} ", map);
		return aDao.selNonUserBoard(map);
	}

	@Override
	public List<AnswerBoardDto> selAllBoard() {
		logger.info("AnswerBoardServiceImpl selAllBoard");
		return aDao.selAllBoard();
	}

	@Override
	public AnswerBoardDto selUserDetail(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl selUserDetail {} ", map);
		return aDao.selUserDetail(map);
	}

	@Override
	public AnswerBoardDto selNonUserDetail(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl selNonUserDetail {} ", map);
		return aDao.selNonUserDetail(map);
	}
	
//	@Override
//	public boolean insertReply(ReservationDto dto) {
//		logger.info("AnswerBoardServiceImpl insertReply {} ", dto);
//		return aDao.insertReply(dto);
//	}

	@Override
	public boolean insertReply(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl insertReply {} ", map);
		return aDao.insertReply(map);
	}

	@Override
	public boolean checkNonUser(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl checkNonUser {} ", map);
		return aDao.checkNonUser(map);
	}

	@Override
	public List<AnswerBoardDto> searchName(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl searchName {} ", map);
		return aDao.searchName(map);
	}

	@Override
	public List<AnswerBoardDto> searchTitle(Map<String, Object> map) {
		logger.info("AnswerBoardServiceImpl searchTitle {} ", map);
		return aDao.searchTitle(map);
	}
	
	

}
