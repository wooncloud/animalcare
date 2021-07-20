/**
 * 
 */
function a(){
		var email = 'dongdong@pet.care'; //ex) ${userInfo.email};
		var name = 'GDJ'; //ex) ${userInfo.name};
		var phone = '010-1234-1111'; //ex) ${userInfo.phone};
		var address = '서울특별시 금천구'; //ex) ${userInfo.address};
		var hospitalName = 'CDC 동물병원 '; //ex) ${hospitalInfo.name};

		var IMP = window.xIMP; // 생략가능
		
		IMP.init('imp02789701');
		IMP.request_pay({
			pg : 'html5_inicis',
			pay_method : 'card',
			merchant_uid : hospitalName + new Date().getTime(),
			name : '진료예약:결제',
			amount : 100, //예약금 10000원
			buyer_email : email, // 구매자 이메일
			buyer_name : name, // 구매자 이름
			buyer_tel : phone, // 구매자 핸드폰
			buyer_addr : address
		},	function(rsp) {
				console.log(rsp);
				console.log(rsp.paid_amount);
				console.log(rsp.imp_uid);
				var paidamount =rsp.paid_amount;
				var paynum = rsp.imp_uid;
				var applynum =rsp.apply_num;
				if(rsp.success){
//					console.log("성공");
//// 					location.href="./insertPay.do?imp_uid="+paynum+"&paid_amount="+paidamount+"&apply_num="+applynum;
//					$.ajax({
//						type: POST,
//						url : "/payment/insertPay.do",
//						data : "user_email="+email+"&user_phone="+phone+"&paynum="+paynum+"&hospital_name="+hospital_name+"&paidamount="+paidamount+"&applynum="+applynum,
//						success : function(data){
//							console.log("성공성공");
//						},
//						error : function(){
//							console.log("에러");
//						}
//					})
				}else {
	 				var msg = '결제에 실패하였습니다.';
	 				msg += '\n에러내용 : ' + rsp.error_msg;
	 			}
				console.log(msg);
			});
	}
