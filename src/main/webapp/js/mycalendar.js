function makeReserve(user_email,user_name) {

		var frm = document.forms[0];

		var reservetype = document.getElementsByName("reservetype")[0].value;
		var petname = document.getElementsByName("pet_name")[0].value;

		if (reservetype == "default") {
			Swal.fire("알림", "예약 종류를 선택해주세요.", "warning");
		} else if (petname == "default") {
			Swal.fire("알림", "반려 동물을 선택해주세요.", "warning");
		} else {
			var email = user_email; //ex) ${userInfo.email};
			var name = user_name; //ex) ${userInfo.name};
			var phone = '010-1234-1111'; //ex) ${userInfo.phone};
// 			var address = '서울특별시 금천구'; //ex) ${userInfo.address};
			var hospitalName = 'CDC 동물병원 '; //ex) ${hospitalInfo.name};

			console.log('왜 안될까');
			var IMP = window.IMP; // 생략가능

			IMP.init('imp02789701');
			IMP.request_pay({
				pg : 'html5_inicis',
				pay_method : 'card',
				merchant_uid : hospitalName + new Date().getTime(),
				name : '진료예약:결제',
				amount : 100, //예약금 10000원
				buyer_email : email, // 구매자 이메일
				buyer_name : name, // 구매자 이름
				buyer_tel : phone // 구매자 핸드폰
// 				buyer_addr : address
			}, function(rsp) {
				console.log(rsp);
				console.log(rsp.paid_amount);
				console.log(rsp.imp_uid);
				var paidamount = rsp.paid_amount;
				var paynum = rsp.imp_uid;
				var applynum = rsp.apply_num;

				var sendData = {
					"user_email" : rsp.buyer_email,
					"user_phone" : rsp.buyer_tel,
					"paynum" : rsp.imp_uid,
					"hospital_name" : hospitalName,
					"paidamount" : rsp.paid_amount,
					"applynum" : rsp.apply_num
				}

				if (rsp.success) {
					console.log("성공");
					$.ajax({
						type : "POST",
						url : "../payment/insertPay.do",
						// 						data : "user_email="+email+"&user_phone="+phone+"&paynum="+paynum+"&hospital_name="+hospital_name+"&paidamount="+paidamount+"&applynum="+applynum,
						data : sendData,
						datatype : "JSON",
						success : function(data) {
							console.log(data);
							console.log("성공성공");
							Swal.fire("알림", "예약이 완료되었습니다.", "warning");
							var paynumdata = data;
							html = "";
							html += "<input type='hidden' name='paynum' value='"+paynumdata+"'>";
							$("form").append(html);
// 							alert(paynumdata);
// 							console.log(data);
							frm.submit();
							// 							location.href="../reservation/insertReservation.do";
							// 				            window.location.href = data.redirect;

						},
						error : function(msg) {
							console.log(msg);
							console.log("에러");
						}
					})
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '\n에러내용 : ' + rsp.error_msg;
				}
				console.log(msg);
			});
		}
	}
function acceptReservation(seq){
	Swal.fire({
			icon: 'question',
			title:' 확정 하시겠습니까?',
			confirmButtonText: '확인',	
			showCancelButton: true,
			cancelButtonColor: 'gray',
			cancelButtonText: '취소',
		}).then((result) => {
		if (result.isConfirmed) {
			  location.href="./acceptReserve.do?seq="+seq+"&status="+status;
		}
		});

}

function operCancelReservation(seq,status,reservedate){
	
	var date = new Date();
	var day = date.getDate();
	var year = date.getFullYear();
	var month = (1+date.getMonth());
	month = (month >= 10) ? month : '0' + month;
	day = (day >= 10) ? day : '0' + day; 
		
	var today = year+"-"+month+"-"+day;//날짜
	
	if(today > reservedate){
		Swal.fire("알림", "취소가 가능한 기간이 지났습니다.", "warning");
	} else{
		Swal.fire({
			icon: 'question',
			title:' 취소 하시겠습니까?',
			confirmButtonText: '확인',	
			showCancelButton: true,
			cancelButtonColor: 'gray',
			cancelButtonText: '취소',
		}).then((result) => {
		if (result.isConfirmed) {
			location.href="../payment/operCancelPayRefund.do?seq="+seq+"&status="+status;
		}
		});
}	
}

function rejectReservation(){
	
	var commnet = document.getElementById("commnet")
 	var frm = document.forms[0];
	
	if(commnet.value == ''){
		Swal.fire("알림", "반려 사유를 작성해주세요.", "warning");
		return;
	}else{
		var chk = confirm("반려하시겠습니까?");
		
		if(chk){
			// 이게 맞음 20210724 4:37 pm
			frm.submit();
		}
		
	}
	
}


function modifyReserve(){
	
	var reservedate = document.getElementById("reservedate").value;
	var reservetime = document.getElementById("reservetime").value;
	console.log(reservedate);
	console.log(reservetime);
	
	var frm1 = document.forms[1];
	console.log(frm1);
	
	$.ajax({
		type:"get",
		url:"./checkReservation.do",
		data:"reservedate="+reservedate+"&reservetime="+reservetime,
		success:function(msg){
			console.log("왔니"+msg);
			
			if(msg=='false'){
				frm1.submit();
			}else{
				Swal.fire("알림", "선택한 일자로 수정이 불가능합니다.", "warning");
				return false;
			}
		},
		error:function(){
			alert("잘못된 요청");
			
		}
		
	});
	
}

function userCancelReservation(seq,status,reservedate){

	console.log(reservedate);
	
	var date = new Date();
	var day = date.getDate();
	var year = date.getFullYear();
	var month = (1+date.getMonth());
	month = (month >= 10) ? month : '0' + month;
	day = (day >= 10) ? day : '0' + day; 
		
	var today = year+"-"+month+"-"+day;//날짜
	console.log(today);
	console.log(date);

	var cBtn = document.getElementsByName("cancelBtn")[0];
	
	
	if(today > reservedate){
		Swal.fire("알림", "취소가 가능한 기간이 지났습니다.", "warning");
// 		cBtn.style.display="none";
	} else{
		Swal.fire({
			icon: 'question',
			title:' 취소 하시겠습니까?',
			confirmButtonText: '확인',	
			showCancelButton: true,
			cancelButtonColor: 'gray',
			cancelButtonText: '취소',
		}).then((result) => {
		if (result.isConfirmed) {
			location.href="./cancelReservation.do?seq="+seq+"&status="+status;
		}
		});
		console.log(seq,status);
}
}

let setCal={
	init: function(){
		
		var hospital_seq = document.getElementsByName("hospital_seq")[0];
		console.log(hospital_seq.value);
		
		cal.clear();
			$.ajax({
				type:"get",
				url:"./calendar.do?hospital_seq="+hospital_seq.value,
				dataType:"json",
				async:true,
				success:function(msg){
					let data = JSON.parse(msg.result);
					console.log(data);
					cal.createSchedules(data);
					
				},
				error:function(){
					alert("잘못된 요청입니다.")
				}
			});	
	}
}

