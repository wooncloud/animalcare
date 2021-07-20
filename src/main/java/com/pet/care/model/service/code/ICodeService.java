package com.pet.care.model.service.code;

import java.util.List;

import com.pet.care.dto.CodeDto;

public interface ICodeService {

	public List<CodeDto> allCodeSelect();

	public List<CodeDto> categoryCodeSelect(String type);

	public CodeDto oneCodeSelect(String id);

}
