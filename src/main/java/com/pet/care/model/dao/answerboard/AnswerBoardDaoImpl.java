package com.pet.care.model.dao.answerboard;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.AnswerBoardDto;

@Repository
public class AnswerBoardDaoImpl implements IAnswerBoardDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String NS = "com.pet.care.model.dao.answerboard.AnswerBoardDaoImpl.";

	@Override
	public boolean insertUserBoard(AnswerBoardDto dto) {
		int cnt = session.insert(NS+"insertUserBoard", dto);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean insertNonUserBoard(AnswerBoardDto dto) {
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
	public boolean deleterBoard(Map<String, Object> map) {
		int cnt = session.delete(NS+"deleterBoard",map);
		return (cnt>0)?true:false;
	}

	@Override
	public List<AnswerBoardDto> selUserBoard(Map<String, Object> map) {
		return session.selectList(NS+"selUserBoard", map);
	}

	@Override
	public List<AnswerBoardDto> selNonUserBoard(Map<String, Object> map) {
		return session.selectList(NS+"selNonUserBoard", map);
	}

	@Override
	public List<AnswerBoardDto> selAllBoard(Map<String, Object>map) {
		return session.selectList(NS+"selAllBoard",map);
	}

	@Override
	public AnswerBoardDto selUserDetail(Map<String, Object> map) {
		return session.selectOne(NS+"selUserDetail", map);
	}

	@Override
	public AnswerBoardDto selNonUserDetail(Map<String, Object> map) {
		return session.selectOne(NS+"selNonUserDetail", map);
	}

//	@Override
//	public boolean insertReply(AnswerBoardDto dto) {
//		int cnt = session.insert(NS+"insertReply", dto);
//		return (cnt>0)?true:false;
//	}
	
	@Override
	public boolean insertReply(Map<String, Object> map) {
		int cnt = session.update(NS+"insertReply", map);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean checkNonUser(Map<String, Object> map) {
		int cnt = session.selectOne(NS+"checkNonUser", map);
		return (cnt>0)?true:false;
	}

	@Override
	public List<AnswerBoardDto> searchName(Map<String, Object> map) {
		return session.selectList(NS+"searchName", map);
	}

	@Override
	public  List<AnswerBoardDto> searchTitle(Map<String, Object> map) {
		return session.selectList(NS+"searchName", map);
	}

	@Override
	public int boardPage() {
		return session.selectOne(NS+"boardPage");
	}

	@Override
	public int searchTitlePage(Map<String, Object> map) {
		return session.selectOne(NS+"searchTitlePage",map);
	}

	@Override
	public int searchNamePage(Map<String, Object> map) {
		return session.selectOne(NS+"searchNamePage",map);
	}
	
	
	
}
