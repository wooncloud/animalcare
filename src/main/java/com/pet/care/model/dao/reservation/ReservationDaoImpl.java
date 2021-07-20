package com.pet.care.model.dao.reservation;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pet.care.dto.ReservationDto;

@Repository
public class ReservationDaoImpl implements IReservationDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS="com.pet.care.model.dao.reservation.ReservationDaoImpl.";

	@Override
	public boolean insertReserve(ReservationDto rDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReservationDto> userReserveList(Map<String, Object> map) {
		return session.selectList(NS+"userReserveList", map);
	}

	@Override
	public ReservationDto userReserveDetail(Map<String, Object> map) {
		return session.selectOne(NS+"userReserveDetail", map);
	}

	@Override
	public ReservationDto userRejectDetail(Map<String, Object> map) {
		return session.selectOne(NS+"userRejectDetail", map);
	}

	@Override
	public List<ReservationDto> hospitalReserveList(Map<String, Object> map) {
		return session.selectList(NS+"hospitalReserveList", map);
	}

	@Override
	public boolean acceptReserve(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectReserve(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelReserve(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyReserve(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReservationDto standReserveList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationDto> todayReserveList(Map<String, Object> map) {
		return session.selectList(NS+"todayReserveList",map);
	}



}
