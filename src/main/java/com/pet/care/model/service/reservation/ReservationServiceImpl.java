package com.pet.care.model.service.reservation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.ReservationDto;
import com.pet.care.model.dao.reservation.IReservationDao;

@Service
public class ReservationServiceImpl implements IReservationService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IReservationDao rDao;

	@Override
	public boolean insertReserve(ReservationDto rDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReservationDto> userReserveList(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl userReserveList {}", map);
		return rDao.userReserveList(map);
	}

	@Override
	public ReservationDto userReserveDetail(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl userReserveDetail {}", map);
		return rDao.userReserveDetail(map);
	}

	@Override
	public ReservationDto userRejectDetail(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl userRejectDetail {}", map);
		return rDao.userRejectDetail(map);
	}

	@Override
	public List<ReservationDto> hospitalReserveList(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl userRejectDetail {}", map);
		return rDao.hospitalReserveList(map);
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
		return rDao.todayReserveList(map);
	}

}
