package com.pet.care.model.service.pet;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.PetDto;
import com.pet.care.model.dao.pet.IPetDao;

@Service
public class PetServiceImpl implements IPetService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPetDao pDao;
	
	@Override
	public List<PetDto> petList(String user_email) {
		log.info("PetServiceImpl petList");
		return pDao.petList(user_email);
	}

	@Override
	public boolean insertPet(PetDto pdto) {
		log.info("PetServiceImpl insertPet");
		return pDao.insertPet(pdto);
	}

	@Override
	public boolean modifyPet(PetDto pdto) {
		log.info("PetServiceImpl modifyPet");
		return pDao.modifyPet(pdto);
	}

	@Override
	public boolean deletePet(Map<String, Object> map) {
		log.info("PetServiceImpl deletePet");
		return pDao.deletePet(map);
	}

	@Override
	public PetDto detailPet(Map<String, Object> map) {
		log.info("PetServiceImpl detailPet");
		return pDao.detailPet(map);
	}

}
