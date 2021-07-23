package com.pet.care;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.PaymentDto;
import com.pet.care.model.service.payment.IPaymentService;
import com.pet.care.payment.PaymentRefund;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPaymentService iService;
	
	@RequestMapping(value="/payment.do", method = RequestMethod.GET)
	public String test() {
		return "payment/payment";
	}
	
	@ResponseBody
	@RequestMapping(value ="/insertPay.do", method=RequestMethod.POST)
	public String insertPay(HttpServletRequest req ,PaymentDto pDto, Model model) {
		logger.info("PaymentController : insertPay 결제 저장 - {}", pDto);
		String paynum = pDto.getPaynum();
		boolean isc = iService.insertPay(pDto);
		if(isc) {
//			return "redirect:/예약컨트롤러";
//			model.addAttribute("paynum", paynum);
			return paynum;
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	@RequestMapping(value="/payList.do", method=RequestMethod.GET)
	public String payList(Model model, HttpSession session) {
//		String user_email = (String)session.getAttribute("user_email");
		String user_email = "user01@gmail.com";
		logger.info("PaymentController : payList 결제 내역 조회 - {}",user_email);
		List<PaymentDto> paylists = iService.payList(user_email);
		System.out.println(paylists);
		model.addAttribute("payList",paylists);
		
		return "payment/payList";
	}
	
	@RequestMapping(value="/payDetailList.do", method=RequestMethod.GET)
	public String payDetailList(Model model, HttpSession session, String seq) {
		logger.info("PaymentController : payDetailList 결제 내역 상세 조회 - {}",seq);
		PaymentDto pDto = iService.payDetailList(seq);
		model.addAttribute("pDto", pDto);
		return "payment/payDetail";
	}
	
	@RequestMapping(value="/cancelPay.do", method=RequestMethod.GET)
	public String cancelPay(@RequestParam Map<String, Object> map) {
		logger.info("PaymentController : cancelPay 결제 취소 - {}", map);
		//예약취소 -> 쿼리돌고 -> 다시 예약내역리스트로 가거나... 일단 내껄로~!
		String seq = "49";
		String status ="A";
		map.put("seq", seq);//예약seq
		map.put("status", status);//상태
		boolean isc = iService.cancelPay(map);
		if(isc) {
			return "redirect:/reservation/????";
			//내꺼 업데이트하고 예약에서 상태 A를 취소(C?)로 업데이트해주는 & paynum null 쿼리로 가야함 
		}else {
			return "payment/index";
			//취소실패페이지 -> 다시 취소할수있는 페이지로
		}
	}
	
	@RequestMapping(value="/userCancelPayRefund.do",method = RequestMethod.GET)
	public String userCancelPayRefund(@RequestParam Map<String, Object> map ) {
		logger.info("PaymentController : userCancelPayRefund 사용자 결제 취소 (환불 O) - {}", map);
		String seq = "48";
		String status ="S";
		map.put("seq", seq); //예약seq
		
		String imp_uid = iService.sendPayNum(map);

		//환불API
		PaymentRefund re = new PaymentRefund();
		re.setup();
		re.cancelPaymentByImpUid(imp_uid);
		
		map.put("status", status); //예약상태

		boolean isc = iService.userCancelPayRefund(map);
		if(isc) {
			System.out.println("=========================사용자 환불 성공");
			return "payment/index"; //예약쪽 페이지가야함 //거기에서 paynum null만드는 쿼리
		}else {
			return "redirect:/error/error.do";
			//일단 에러페이지
			//취소실패페이지 -> 다시 취소할수있는 페이지로
		}
		
		
	}
	
	@RequestMapping(value="/operCancelPayRefund.do",method = RequestMethod.GET)
	public String operCancelPayRefund(@RequestParam Map<String, Object> map ) {
		logger.info("PaymentController : operCancelPayRefund 병원관계자 결제 취소 (환불 O) - {}", map);
		String seq = "47";
		String status ="A";
		map.put("seq", seq); //예약seq
		
		String imp_uid = iService.sendPayNum(map);

		//환불API
		PaymentRefund re = new PaymentRefund();
		re.setup();
		re.cancelPaymentByImpUid(imp_uid);
		
		map.put("status", status); //예약상태

		boolean isc = iService.operCancelPayRefund(map);
		if(isc) {
			System.out.println("=========================병원관계자 환불 성공");
			return "redirect:/payment/payment.do"; //예약쪽 페이지가야함 //거기에서 paynum null만드는 쿼리
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	
	
}
