package com.pet.care.model.service.payment;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.PaymentDto;
import com.pet.care.model.dao.payment.IPaymentDao;

@Service
public class PaymentServiceImpl implements IPaymentService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPaymentDao iDao;
	
	@Override
	public boolean insertPay(PaymentDto pdto) {
		logger.info("PaymentServiceImpl : insertPay 결제 정보 저장 - {}", pdto);
		boolean isc = iDao.insertPay(pdto);
		return isc;
	}

	@Override
	public List<PaymentDto> payList(String user_email) {
		logger.info("PaymentServiceImpl : payList 결제 내역 조회 - {}", user_email);
		return iDao.payList(user_email);
	}
	
	@Override
	public PaymentDto payDetailList(String seq) {
		logger.info("PaymentServiceImpl : payDetailList 결제 내역 상세 조회 - {}", seq);
		return iDao.payDetailList(seq);
	}

	@Override
	public boolean cancelPay(Map<String, Object> map) {
		logger.info("PaymentServiceImpl : cancelPay 결제 취소 (환불 X) - {}", map);
		boolean isc = iDao.cancelPay(map);
		return isc;
	}

	@Override
	public boolean userCancelPayRefund(Map<String, Object> map) {
		logger.info("PaymentServiceImpl : userCancelPayRefund 사용자 결제 취소 (환불 O) - {}", map);
		boolean isc = iDao.userCancelPayRefund(map);
		return isc;
	}

	@Override
	public boolean operCancelPayRefund(Map<String, Object> map) {
		logger.info("PaymentServiceImpl : operCancelPayRefund 병원관계자 결제 취소 (환불 O) - {}", map);
		boolean isc = iDao.operCancelPayRefund(map);
		return isc;
	}

	@Override
	public String sendPayNum(Map<String, Object> map) {
		logger.info("PaymentServiceImpl : sendPayNum 결제 번호 - {}", map);
		return iDao.sendPayNum(map);
	}


}