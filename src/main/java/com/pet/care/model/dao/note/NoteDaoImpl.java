package com.pet.care.model.dao.note;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.NoteDto;

@Repository
public class NoteDaoImpl implements INoteDao {

	private String NS="com.pet.care.model.dao.note.INoteDao.";
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<NoteDto> noteList(Map<String, Object> map) {
		return sqlsession.selectList(NS+"noteList", map);
	}
	
	@Override
	public List<NoteDto> selDateList(Map<String, Object> map) {
		return sqlsession.selectList(NS+"selDateList", map);
	}

	@Override
	public NoteDto noteDetail(int seq) {
		return sqlsession.selectOne(NS+"noteDetail", seq);
	}



}
