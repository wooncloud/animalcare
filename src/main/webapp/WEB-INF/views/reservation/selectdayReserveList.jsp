<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

${todayReserve}
<div class="card my-1">
	<div class="card-body">
		<div class="row">
			<div class="col-2">
				<h5 class="card-title">NO</h5>
			</div>
			<div class="col-3">
				<h5 class="card-title">예약 시간</h5>
			</div>
			<div class="col-3">
				<h5 class="card-title">예약종류</h5>
			</div>
			<div class="col-4">
				<h5 class="card-title">예약 가능 여부</h5>
			</div>
		</div>
	</div>
</div>
<c:if test="${todayReserve != null}">
	<c:forEach var="today" items="${todayReserve}" varStatus="vs">
		<div class="card my-1">
			<div class="card-body">
				<div class="row">
					<div class="col-2">
						<p class="card-text">${vs.count}</p>
					</div>
					<div class="col-3">
						<p class="card-text">${today.reservetime}</p>
					</div>
					<div class="col-3">
						<p class="card-text">${today.reservetype}</p>
					</div>
					<div class="col-3">
						<p class="card-text">예약 안됨</p>
					</div>
					<div class="col-1">
						<p class="card-text"></p>
					</div>

				</div>
			</div>
		</div>
	</c:forEach>
</c:if>
<!-- 리스트가 4개 있으면 0개 3개있으면 1개 2개있으면 2개 1개있으면 3개  -->
<c:set property="${todayReserve}" var="list"/>
<c:if test="${fn:length(list) != 0}" >
   <c:forEach items="${list}" begin="${fn:length(list)}" end="4" step="1">
	  <div class="card my-1">
		 <div class="card-body">
			<div class="row">
			   <div class="col-2">
				  <p class="card-text"></p>
			   </div>
			   <div class="col-3">
				  <p class="card-text"></p>
			   </div>
			   <div class="col-3">
				  <p class="card-text"></p>
			   </div>
			   <div class="col-3">
				  <p class="card-text">예약 가능</p>
			   </div>
			   <div class="col-1">
				  <p class="card-text"></p>
			   </div>
			</div>
		 </div>
	  </div>
   </c:forEach>
</c:if>
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#testModal">예약</button>
<div class="modal fade" id="testModal" tabindex="-1" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
		 <div class="modal-header">
			<h5 class="modal-title">예약신청</h5>
			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		 </div>
		 <div class="modal-body">
			<div>
<!-- form -->      <form action="./insertReservation.do" method="post">
				  <div class="form-group">
					 <label>예약 종류:</label>
					 <select name="reservetype">
						<option value="default" selected="selected">선택</option>
						<option value="REV001">일반진료</option>
						<option value="REV002">건강검진</option>
						<option value="REV003">예방접종</option>
					 </select>
				  </div>
				  <div class="form-group">
					 <label>반려 동물:</label>
					 <select name=pet_name>
						<option value="default">선택</option>
						<c:forEach items="${userPet}" var="pet">
						   <option value="${pet}">${pet}</option>
						</c:forEach>
					 </select>
				  </div>
				  <div class="form-group">
					 <label for="reservedate">예약 일자:</label>
					 <input type="text" class="form-control" id="reservedate" name="reservedate" value="${reservedate}" readonly>
				  </div>
				  <div class="form-group">
					 <label for="reservetime">예약 시간:</label>
					 <input type="text" class="form-control" id="reservetime" name="reservetime" value="${reservedate}" readonly>
				  </div>
				  <div class="form-group">
					 <label for="name">이름:</label>
					 <input type="text" class="form-control" id="name" name="name">
				  </div>
				  <div class="form-group">
					 <label for="email">이메일:</label>
					 <input type="text" class="form-control" id="user_email" name="user_email">
				  </div>
				  <div class="form-group">
					 <label for="phone">전화번호:</label>
					 <input type="text" class="form-control" id="phone" name="phone">
				  </div>
<!-- form -->      </form>
			</div>
		 </div>
		 <div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			<input type="button" class="btn btn-primary" value="예약신청" onclick="makeReserve()">
		 </div>
	  </div>
   </div>
</div>

		
<script type="text/javascript">
	function makeReserve(){
	
	var frm = document.forms[0];
</script>
<script>
	function makeReserve() {

		var frm = document.forms[0];

		var reservetype = document.getElementsByName("reservetype")[0].value;
		var petname = document.getElementsByName("pet_name")[0].value;

		if (reservetype == "default") {
			alert("예약 종류를 선택해주세요")
		} else if (petname == "default") {
			alert("반려 동물을 선택해주세요")
		} else if (reservetype == "default" && petname == "default") {
			alert("예약 종류와 반료동물을 선택해주세요")
		} else {
			var email = 'user01@gmail.com'; //ex) ${userInfo.email};
			var name = 'GDJ'; //ex) ${userInfo.name};
			var phone = '010-1234-1111'; //ex) ${userInfo.phone};
			var address = '서울특별시 금천구'; //ex) ${userInfo.address};
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
				buyer_tel : phone, // 구매자 핸드폰
				buyer_addr : address
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
							alert(data);
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
</script>
<script type="text/javascript">
	function requestPay() {

	}
</script>
<%@ include file="/footer.jsp"%>