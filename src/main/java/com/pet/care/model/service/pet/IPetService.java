package com.pet.care.model.service.pet;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.PetDto;

public interface IPetService {

	public List<PetDto> petList(String user_email);
	
	public boolean insertPet(PetDto pdto);
	
	public boolean modifyPet(PetDto pdto);
	
	public boolean deletePet(Map<String, Object> map);
	
	public PetDto detailPet(Map<String, Object> map);
}
