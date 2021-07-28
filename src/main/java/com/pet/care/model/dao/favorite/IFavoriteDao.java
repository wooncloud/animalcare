package com.pet.care.model.dao.favorite;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.FavoriteDto;

public interface IFavoriteDao {

	public List<FavoriteDto> favoriteList(String email);
	
	public int favoriteInsert(Map<String, Object> map);
	
	public int favoriteDelete(int seq);
}
