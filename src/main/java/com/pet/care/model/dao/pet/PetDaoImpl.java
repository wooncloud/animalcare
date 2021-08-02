package com.pet.care.model.dao.pet;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.PetDto;

@Repository
public class PetDaoImpl implements IPetDao {

	private String NS= "com.pet.care.model.dao.pet.IPetDao.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<PetDto> petList(String user_email) {
		return sqlsession.selectList(NS+"petList", user_email);
	}

	@Override
	public boolean insertPet(PetDto pdto) {
		return sqlsession.insert(NS+"insertPet", pdto)>0?true:false;
	}

	@Override
	public boolean modifyPet(PetDto pdto) {
		return sqlsession.update(NS+"modifyPet", pdto)>0?true:false;
	}

	@Override
	public boolean deletePet(Map<String, Object> map) {
		return sqlsession.update(NS+"deletePet", map)>0?true:false;
	}

	@Override
	public PetDto detailPet(Map<String, Object> map) {
		return sqlsession.selectOne(NS+"detailPet", map);
	}

}
