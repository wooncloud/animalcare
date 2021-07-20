package com.pet.care.model.service.code;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.CodeDto;
import com.pet.care.model.dao.code.ICodeDao;

@Service
public class CodeServiceImpl implements ICodeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICodeDao iDao;

	@Override
	public List<CodeDto> allCodeSelect() {
		logger.info("[allCodeSelect]");
		return iDao.allCodeSelect();
	}

	@Override
	public List<CodeDto> categoryCodeSelect(String type) {
		logger.info("[categoryCodeSelect - {}]", type);
		return iDao.categoryCodeSelect(type);
	}

	@Override
	public CodeDto oneCodeSelect(String id) {
		logger.info("[allCodeSelect - {}]", id);
		return iDao.oneCodeSelect(id);
	}

}
