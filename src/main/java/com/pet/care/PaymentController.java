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

import com.pet.care.dto.MemberDto;
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
//			model.addAttribute("paynum", paynum);
			return paynum;
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	@RequestMapping(value="/payList.do", method=RequestMethod.GET)
	public String payList(Model model, HttpSession session) {
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		String user_email = mDto.getEmail();
				
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
	public String cancelPay(@RequestParam Map<String, Object> map, Model model) {
		logger.info("PaymentController : cancelPay 결제 취소 - {}", map);
		
		boolean isc = iService.cancelPay(map);
		
		String seq = (String)map.get("seq");
		model.addAttribute("seq", seq);

		if(isc) {
			// 이게 맞음 20210724 3:54 pm
			return "redirect:/reservation/cancelReserve.do"; 
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	@RequestMapping(value="/userCancelPayRefund.do",method = RequestMethod.GET)
	public String userCancelPayRefund(@RequestParam Map<String, Object> map, Model model ) {
		logger.info("PaymentController : userCancelPayRefund 사용자 결제 취소 (환불 O) - {}", map);

		String imp_uid = iService.sendPayNum(map);

		//환불API
		PaymentRefund re = new PaymentRefund();
		re.setup();
		re.cancelPaymentByImpUid(imp_uid);
		
		boolean isc = iService.userCancelPayRefund(map);
		
		// 이게 맞음 20210724 3:54 pm
		String seq = (String)map.get("seq");
		model.addAttribute("seq", seq);
		if(isc) {
			System.out.println("=========================사용자 환불 성공");
			return "redirect:/reservation/cancelReserve.do";
		}else {
			return "redirect:/error/error.do";
		}
	}
	
	@RequestMapping(value="/operCancelPayRefund.do",method = RequestMethod.GET)
	public String operCancelPayRefund(@RequestParam Map<String, Object> map, Model model) {
		logger.info("PaymentController : operCancelPayRefund 병원관계자 결제 취소 (환불 O) - {}", map);
		
		String imp_uid = iService.sendPayNum(map);
		String seq = (String)map.get("seq");
		String status = (String)map.get("status");

		//환불API
		PaymentRefund re = new PaymentRefund();
		re.setup();
		re.cancelPaymentByImpUid(imp_uid);
		
		boolean isc = iService.operCancelPayRefund(map);
		
		model.addAttribute("seq", seq);
		model.addAttribute("status", status);
		// 이게 맞음 20210730 3:54 am
		if(isc==true) {
			System.out.println("=========================병원관계자 환불 성공");
			if(status.equals("S")) {
				return "redirect:/reservation/rejectStatusReserve.do";
			}else if(status.equals("A")){
				return "redirect:/reservation/cancelReserve.do";
			}else {
				return "redirect:/error/error.do";
			}
			
		}else {
			return "redirect:/error/error.do";
		}
		
	}
	
	
	
}
