package com.pet.care.model.dao.answerboard;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.AnswerBaordDto;

@Repository
public class AnswerBoardDaoImpl implements IAnswerBoardDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String NS = "com.pet.care.model.dao.answerBoard.AnswerBoardDaoImpl.";

	@Override
	public boolean insertUserBoard(AnswerBaordDto dto) {
		int cnt = session.insert(NS+"insertUserBoard", dto);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean insertNonUserBoard(AnswerBaordDto dto) {
		int cnt = session.insert(NS+"insertNonUserBoard", dto);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean modifyUserBoard(Map<String, Object> map) {
		int cnt = session.update(NS+"modifyUserBoard",map);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean modifyNonUserBoard(Map<String, Object> map) {
		int cnt = session.update(NS+"modifyNonUserBoard",map);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean deleteUserBoard(Map<String, Object> map) {
		int cnt = session.delete(NS+"deleteUserBoard",map);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean deleteNonUserBoard(Map<String, Object> map) {
		int cnt = session.delete(NS+"deleteNonUserBoard",map);
		return (cnt>0)?true:false;
	}

	@Override
	public List<AnswerBaordDto> selUserBoard(Map<String, Object> map) {
		return session.selectList(NS+"selUserBoard", map);
	}

	@Override
	public List<AnswerBaordDto> selNonUserBoard(Map<String, Object> map) {
		return session.selectList(NS+"selUserBoard", map);
	}

	@Override
	public List<AnswerBaordDto> selAllBoard() {
		return session.selectList(NS+"selAllBoard");
	}

	@Override
	public AnswerBaordDto selUserDetail(Map<String, Object> map) {
		return session.selectOne(NS+"selUserDetail", map);
	}

	@Override
	public AnswerBaordDto selNonUserDetail(Map<String, Object> map) {
		return session.selectOne(NS+"selNonUserDetail", map);
	}

	@Override
	public boolean insertReply(AnswerBaordDto dto) {
		int cnt = session.insert(NS+"insertReply", dto);
		return (cnt>0)?true:false;
	}
}
