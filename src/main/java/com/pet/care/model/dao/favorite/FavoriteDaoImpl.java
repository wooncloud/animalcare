package com.pet.care.model.dao.favorite;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.FavoriteDto;

@Repository
public class FavoriteDaoImpl implements IFavoriteDao {

	private final String NS = "com.pet.care.model.dao.favorite.IFavoriteDao.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<FavoriteDto> favoriteList(String email) {
		return sqlSession.selectList(NS + "favoriteList", email);
	}

	@Override
	public int favoriteInsert(Map<String, Object> map) {
		return sqlSession.insert(NS + "favoriteInsert", map);
	}

	@Override
	public int favoriteDelete(int seq) {
		return sqlSession.delete(NS + "favoriteDelete", seq);
	}

}
