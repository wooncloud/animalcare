<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제페이지</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<button onclick="requestPay()">결제하기</button>
<script type="text/javascript">
	
	
	function getFormatDate(date){
	    var year = date.getFullYear();              //yyyy
	    var month = (1 + date.getMonth());          //M
	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	    var day = date.getDate();                   //d
	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	    return  year + '' + month + '' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
	}
	
	
	function requestPay(){
		var email = 'dongdong@pet.care'; //ex) ${userInfo.email};
		var name = 'GDJ'; //ex) ${userInfo.name};
		var phone = '010-1234-1234'; //ex) ${userInfo.phone};
		var address = '서울특별시 금천구'; //ex) ${userInfo.address};
		var hospitalName = 'CDC 동물병원 '; //ex) ${hospitalInfo.name};
		
		
		var date = new Date();
		var paydate = getFormatDate(date);
		console.log(getFormatDate(date));

		
		var IMP = window.IMP; // 생략가능
		
		IMP.init('imp02789701'); 
		IMP.request_pay({
			pg : 'html5_inicis',
			pay_method : 'card',
			merchant_uid : hospitalName + paydate, //ex) 병원DTO.병원고유번호....
			name : '진료예약:결제테스트',
			amount : 100, //예약금 10000원으로 지정해놓는거면 10000으로 수정
			buyer_email : email, // 구매자 이메일
			buyer_name : name, // 구매자 이름
			buyer_tel : phone, // 구매자 핸드폰
			buyer_addr : address
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '\n예약결제번호 : ' + rsp.imp_uid; //아임포트에서 제공하는 거래고유번호 => 예약결제번호로 정의
				msg += '\n병원명 : ' + rsp.merchant_uid; //고유주문번호 => 대신 병원명+예약일자
				msg += '\n결제 금액 : ' + rsp.paid_amount+'원';
				msg += '\n카드 승인번호 : ' + rsp.apply_num; 
				console.log(msg);
				
				location.href="./test.jsp";
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '\n에러내용 : ' + rsp.error_msg;
			}
			alert(msg);
		});
	}
</script>
</body>
</html>