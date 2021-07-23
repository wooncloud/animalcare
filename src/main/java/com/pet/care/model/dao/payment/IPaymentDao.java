package com.pet.care.model.dao.payment;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.PaymentDto;

public interface IPaymentDao {

	//결제 정보 저장
	public boolean insertPay(PaymentDto pdto);

	//결제 내역 조회
	public List<PaymentDto> payList(String user_email);
	
	//결제 내역 상세 조회
	public PaymentDto payDetailList(String seq);
	
	//결제 취소 (환불 X)
	public boolean cancelPay(Map<String, Object> map);
	
	//사용자 결제 취소 (환불 O)
	public boolean userCancelPayRefund(Map<String, Object> map);
	
	//병원관계자 결제 취소 (환불 O)
	public boolean operCancelPayRefund(Map<String, Object> map);
	
	//결제 번호
	public String sendPayNum(Map<String , Object> map); 
	
}
