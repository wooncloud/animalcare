package com.pet.care.model.service.favorite;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.FavoriteDto;
import com.pet.care.model.dao.favorite.IFavoriteDao;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IFavoriteDao dao;

	@Override
	public List<FavoriteDto> favoriteList(String email) {
		logger.info("favoriteList - {}", email);
		return dao.favoriteList(email);
	}

	@Override
	public boolean favoriteInsert(Map<String, Object> map) {
		logger.info("favoriteInsert - {}", map);
		return dao.favoriteInsert(map) > 0 ? true : false;
	}

	@Override
	public boolean favoriteDelete(int seq) {
		logger.info("favoriteDelete - {}", seq);
		return dao.favoriteDelete(seq) > 0 ? true : false;
	}

}
