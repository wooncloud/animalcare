<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp" %>
    	<!-- calendaer -->
<!--   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"> -->
<!--   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script> -->
<!--   <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  <script src="https://uicdn.toast.com/tui.code-snippet/v1.5.2/tui-code-snippet.min.js"></script>
  <script src="https://uicdn.toast.com/tui.time-picker/v2.0.3/tui-time-picker.min.js"></script>
  <script src="https://uicdn.toast.com/tui.date-picker/v4.0.3/tui-date-picker.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
  <script src="${path}/js/tui-calendar.js"></script>
  <script src="${path}/js/calendars.js"></script>
  <script src="${path}/js/schedules.js"></script>

<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
 <!-- calendar -->
 <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
 <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
 <link rel="stylesheet" type="text/css" href="${path}/css/tui-calendar.css">
 <link rel="stylesheet" type="text/css" href="${path}/css/default.css">
 <link rel="stylesheet" type="text/css" href="${path}/css/icons.css">
  <div class="code-html">
    <div id="menu"  style="height: 700px;">
      <span id="renderRange" class="render-range" style="padding-left:250px;"></span>
          <span id="menu-navi">
        <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Today</button>
        <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
          <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
        </button>
        <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
          <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
        </button>
      </span>
    </div>
    <div id="calendar" style="width: 800px; top:200px; margin: 0 auto;">
    </div>
</div>	
	<!-- 사용자 신청 모달  -->
<c:if test="${member.usertype eq 'ROLE_USER'}">
<div class="modal fade" id="calModal" tabindex="-1" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
		 <div class="modal-header">
			<h5 class="modal-title">예약신청</h5>
			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		 </div>
	 <div class="modal-body">
			<div>
		   <form action="./insertReservation.do" method="post">
				  <div class="form-group">
					 <label>예약 종류:</label>
					 <select class="form-select" aria-label="Default select example" name="reservetype">
						<option value="default" selected="selected">선택</option>
						<option value="REV001">일반</option>
						<option value="REV003">예방접종</option>
						<option value="REV004">건강검진</option>
					 </select>
				  </div>
				  <div class="form-group">
					 <label>반려 동물:</label>
					 <select class="form-select" aria-label="Default select example" name=pet_name>
						<option value="default">선택</option>
						<c:forEach items="${userPet}" var="pet">
						   <option value="${pet}">${pet}</option>
						</c:forEach>
					 </select>
				  </div>
				  <div class="form-group">
					 <label for="reservedate">예약 일자:</label>
					 <input type="text" class="form-control" id="reservedate" name="reservedate" value=""  readonly>
				  </div>
				  <div class="form-group">
					 <label for="reservetime">예약 시간:</label>
						<select class="form-select" aria-label="Default select example" name="reservetime" id="reservetime" >
						<option id="09001100" value="09001100">0900~1100</option>
						<option id="11001300" value="11001300">1100~1300</option>
						<option id="13001500" value="13001500">1300~1500</option>
						<option id="15001700" value="15001700">1500~1700</option>
						</select>
				  </div>
				  <div class="form-group">
					 <label for="name">이름:</label>
					 <input type="text" class="form-control" id="name" name="name" value="${sessionScope.member.name}" readonly="readonly">
				  </div>
				  <div class="form-group">
					 <label for="email">이메일:</label>
					 <input type="text" class="form-control" id="user_email" name="user_email" value="${sessionScope.member.email}" readonly="readonly">
				  </div>
				  <div class="form-group">
					 <label for="phone">전화번호:</label>
					 <input type="text" class="form-control" id="phone" name="phone" value="010-1234-0000" readonly="readonly">
				  </div>
    		</form>
			</div>
		 </div>
		 <div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" >Close</button>
			<input type="button" class="btn btn-primary" value="예약신청" onclick="makeReserve('${sessionScope.member.email}', '${sessionScope.member.name}')">
		 </div>
	  </div>
   </div>
</div>
</c:if>	
<script type="text/javascript" class="code-js">
var cal = new tui.Calendar('#calendar', {
    defaultView: 'month', // monthly view option
    month: {
		daynames: ['일', '월', '화', '수', '목', '금', '토'],
		startDayOfWeek: 0,
		narrowWeekend: false,
		scheduleView: 'time'
	}
  });
	  
	//병원 관계자일 때 상세로 보내자 그냥
	//사용자는 클릭시 아무 이벤트 없다 그냥 확인
	cal.on('clickSchedule', function (event) {
		console.log(event);
		console.log(event.schedule);
		var schedule = event.schedule;
		
		if(schedule.calendarId =='병원일정'){
 			location.href="./detailSchedule.do?seq="+schedule.id; // 일정 상세
		} else if(schedule.calendarId =='예약완료'){
		location.href="./hospitalReserveDetail.do?seq="+schedule.id;
		}
	});
	
	
	
	cal.on({
	  //그리고 예약 내용말고 클릭시 병원 관계자는 그날 예약목록 보기로 보내자
	  //여기서 사용자는 신청
	    'beforeCreateSchedule': function(e) {
	        console.log('beforeCreateSchedule', e);
	        console.log(e.start);

	        //선택날짜
	        var d = e.start;
	  		var date = new Date(d);
	  		var day = date.getDate();
	  		var year = date.getFullYear();
	  		var month = (1+date.getMonth());
	  		month = (month >= 10) ? month : '0' + month;
	  		day = (day >= 10) ? day : '0' + day; 
	  		var selday = year+""+month+""+day;
	  		console.log(selday);

	      //오늘 날짜
	      var today = new Date();
		   var nDay = today.getDate();
	      var nYear = today.getFullYear();
	      var nMonth = (today.getMonth()+1);
	      nMonth = (nMonth >= 10) ? nMonth : '0' + nMonth;
	      nDay = (nDay >= 10) ? nDay : '0' + nDay; 
	      var nDay =nYear+""+nMonth+""+nDay;
	      console.log(nDay);

	     
		  location.href="./selectdayReserveList.do?reservedate="+selday;
		  

	        e.guide.clearGuideElement();
	    }
	});

</script>

<script type="text/javascript">



	function makeReserve(user_email,user_name) {

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
  <script src="${path}/js/default.js"></script>
<%@ include file="/footer.jsp" %>

