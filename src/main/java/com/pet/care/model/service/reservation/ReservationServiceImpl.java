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
		logger.info(" ReservationServiceImpl insertReserve {}", rDto);
		return rDao.insertReserve(rDto);
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
		logger.info(" ReservationServiceImpl acceptReserve {}", map);
		return rDao.acceptReserve(map);
	}

	@Override
	public boolean rejectReserve(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl rejectReserve {}", map);
		return rDao.rejectReserve(map);
	}

	@Override
	public boolean cancelReserve(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl cancelReserve {}", map);
		return rDao.cancelReserve(map);
	}

	@Override
	public boolean modifyReserve(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl modifyReserve {}", map);
		return rDao.modifyReserve(map);
	}

	@Override
	public List<ReservationDto> hospitalStandReserveList(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl hospitalstandReserveList {}", map);
		return rDao.hospitalStandReserveList(map);
	}

	@Override
	public List<ReservationDto> todayReserveList(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl todayReserveList {}", map);
		return rDao.todayReserveList(map);
	}

	@Override
	public ReservationDto hospitalReserveDetail(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl hospitalReserveDetail {}", map);
		return rDao.hospitalReserveDetail(map);
	}

	@Override
	public List<String> getUserPet(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl getUserPet {}", map);
		return rDao.getUserPet(map);
	}

	@Override
	public List<ReservationDto> selectdayReserveList(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl selectdayReserveList {}", map);
		return rDao.selectdayReserveList(map);
	}

	@Override
	public int userReserveListCount(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl userReserveListCount {}", map);
		return rDao.userReserveListCount(map);
	}

	@Override
	public int hospitalReserveListCount(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl hospitalReserveListCount {}", map);
		return rDao.hospitalReserveListCount(map);
	}

	@Override
	public int hospitalStandReserveListCount(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl hospitalStandReserveListCount {}", map);
		return rDao.hospitalStandReserveListCount(map);
	}

	@Override
	public List<ReservationDto> hospitalCalendarList(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl hospitalCalendarList {}", map);
		return rDao.hospitalCalendarList(map);
	}

	@Override
	public boolean checkReservation(Map<String, Object> map) {
		logger.info(" ReservationServiceImpl checkReservation {}", map);
		return rDao.checkReservation(map);
	}	
	
	
}
