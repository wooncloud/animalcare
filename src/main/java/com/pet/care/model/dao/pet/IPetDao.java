package com.pet.care.model.dao.pet;

import java.util.List;

import com.pet.care.dto.PetDto;

public interface IPetDao {
	public List<PetDto> petList(String user_email);
	
	public boolean insertPet(PetDto pdto);
	
	public boolean modifyPet(PetDto pdto);
	
	public boolean deletePet(String id);
}
