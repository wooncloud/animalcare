package com.pet.care.model.dao.payment;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.PaymentDto;

@Repository
public class PaymentDaoImpl implements IPaymentDao {

	private final String NS="com.pet.care.model.dao.payment.IPaymentDao.";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertPay(PaymentDto pdto) {
		logger.info("PaymentDaoImpl : insertPay 결제 정보 저장 - {}", pdto);
		int n = sqlSession.insert(NS+"insertPay", pdto);
		return (n>0)? true:false;
	}

	@Override
	public List<PaymentDto> payList(String user_email) {
		logger.info("PaymentDaoImpl : payList 결제 내역 조회 - {}", user_email);
		return sqlSession.selectList(NS+"payList", user_email);
	}
	
	@Override
	public PaymentDto payDetailList(String seq) {
		logger.info("PaymentDaoImpl : payDetailList 결제 내역 상세 조회 - {}", seq);
		return sqlSession.selectOne(NS+"payDetailList", seq);
	}



}
