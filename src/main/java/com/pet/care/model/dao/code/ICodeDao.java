package com.pet.care.model.dao.code;

import java.util.List;

import com.pet.care.dto.CodeDto;

public interface ICodeDao {

	public List<CodeDto> allCodeSelect();

	public List<CodeDto> categoryCodeSelect(String type);

	public CodeDto oneCodeSelect(String id);

}
