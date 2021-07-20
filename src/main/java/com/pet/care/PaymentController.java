package com.pet.care;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.PaymentDto;
import com.pet.care.model.service.payment.IPaymentService;

@Controller
public class PaymentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPaymentService iService;
	
	@RequestMapping(value="/payment/payment.do", method = RequestMethod.GET)
	public String test() {
		return "/payment/payment";
	}
	
	
	@ResponseBody
	@RequestMapping(value ="/payment/insertPay.do", method=RequestMethod.GET)
	public String insertPay(PaymentDto pDto, HttpServletRequest req) {
		logger.info("PaymentController : insertPay 결제 저장 - {}");

		boolean isc = iService.insertPay(pDto);
		if(isc) {
			return "redirect:/예약컨트롤러";
		}else {
			return "결제실패창";
		}
	}
	
	@RequestMapping(value="/payment/payList.do", method=RequestMethod.GET)
	public String payList(Model model, HttpSession session) {
//		String user_email = (String)session.getAttribute("user_email");
		String user_email = "user01@gmail.com";
		logger.info("PaymentController : payList 결제 내역 조회 - {}",user_email);
		List<PaymentDto> paylists = iService.payList(user_email);
		System.out.println(paylists);
		model.addAttribute("payList",paylists);
		
		return "payment/payList";
	}
	
	@RequestMapping(value="/payment/payDetailList.do", method=RequestMethod.GET)
	public String payDetailList(Model model, HttpSession session, String seq) {
		logger.info("PaymentController : payDetailList 결제 내역 상세 조회 - {}",seq);
		PaymentDto pDto = iService.payDetailList(seq);
		model.addAttribute("pDto", pDto);
		return "payment/payDetail";
	}
}
