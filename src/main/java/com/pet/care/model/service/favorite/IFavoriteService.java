package com.pet.care.model.service.favorite;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.FavoriteDto;

public interface IFavoriteService {

	public List<FavoriteDto> favoriteList(String email);

	public boolean favoriteInsert(Map<String, Object> map);

	public boolean favoriteDelete(int seq);
}
