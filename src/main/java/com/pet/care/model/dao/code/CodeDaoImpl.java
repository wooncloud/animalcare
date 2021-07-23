package com.pet.care.model.dao.code;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.CodeDto;

@Repository
public class CodeDaoImpl implements ICodeDao {

	private final String NS = "com.pet.care.model.dao.code.ICodeDao.";
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CodeDto> allCodeSelect() {
		return sqlSession.selectList(NS + "allCodeSelect");
	}

	@Override
	public List<CodeDto> categoryCodeSelect(String type) {
		return sqlSession.selectList(NS + "categoryCodeSelect", type);
	}

	@Override
	public CodeDto oneCodeSelect(String id) {
		return sqlSession.selectOne(NS + "oneCodeSelect", id);
	}

}
