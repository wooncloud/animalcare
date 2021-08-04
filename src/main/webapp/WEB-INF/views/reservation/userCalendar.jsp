<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp" %>
    <script type="text/javascript" src="${path}/js/mycalendar.js" ></script>
    	<!-- calendaer -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
  <script src="https://uicdn.toast.com/tui.code-snippet/v1.5.2/tui-code-snippet.min.js"></script>
  <script src="https://uicdn.toast.com/tui.time-picker/v2.0.3/tui-time-picker.min.js"></script>
  <script src="https://uicdn.toast.com/tui.date-picker/v4.0.3/tui-date-picker.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
  <script src="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.js"></script>
  <script src="${path}/js/calendarjs/calendars.js"></script>
  <script src="${path}/js/mycalendar.js"></script>
 <!-- calendar -->
 <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
 <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
 <link rel="stylesheet" type="text/css" href="${path}/css/calendarcss/tui-calendar.css">
 <link rel="stylesheet" type="text/css" href="${path}/css/calendarcss/default.css">
  <div class="code-html">
    <div id="menu" style="height: 700px;">
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
        <h3 style="margin-left: 470px;">${searchInfo.hospital_name} 예약 현황</h3>
    </div>
     <div style="height: 200px">
   	 	<div id="calendar" style="width: 800px; top:200px; margin: 0 auto;">
   	 </div>
    </div>
</div>	
<input type="hidden" value="3" name="hospital_seq">


<!-- 신청 모달 -->
<div class="modal fade" id="inesrtModal" tabindex="-1" aria-hidden="true">
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
						 <label for="phone">증상:</label>
						 <input type="text" class="form-control" id="symptom" name="symptom" >
					  </div>	
					  <div class="form-group">
						 <label for="reservedate">예약 일자:</label>
						 <input type="text" class="form-control" id="reservedate" name="reservedate" value="${reservedate}" readonly>
					  </div>
					  <div class="form-group">
						 <label for="reservetime">예약 시간:</label>
							<select class="form-select" aria-label="Default select example" name="reservetime" id="reservetime" onchange="reserveChk()">
							<option id="default" value="default" selected="selected">선택</option>
							<option id="09001100" value="09001100">09:00~11:00</option>
							<option id="11001300" value="11001300">11:00~13:00</option>
							<option id="13001500" value="13001500">13:00~15:00</option>
							<option id="15001700" value="15001700">15:00~17:00</option>						
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
					 <input type="hidden" id="hospital_seq" name="hospital_seq" value="${searchInfo.hospital_seq}">	  
	    </form>
				</div>
			 </div>
			 <div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="resetInfo()" >닫기</button>
				<input type="button" class="btn btn-primary" value="예약신청" onclick="makeReserve('${sessionScope.member.email}', '${sessionScope.member.name}')">
			 </div>
		  </div>
	   </div>
	</div>
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
	cal.on({

	    'beforeCreateSchedule': function(e) {
	        console.log('beforeCreateSchedule', e);
	        console.log(e.start);

	        var insertModal = new bootstrap.Modal(document.getElementById('inesrtModal'), {
				keyboard: false
				})
	        
	        //선택날짜
	        var d = e.start;
	  		var seldate = new Date(d);
	  		var day = seldate.getDate();
	  		var year = seldate.getFullYear();
	  		var month = (1+seldate.getMonth());
	  		month = (month >= 10) ? month : '0' + month;
	  		day = (day >= 10) ? day : '0' + day; 
	  		var selday = year+""+month+""+day;
	  		console.log(seldate);//선택
	  		console.log(selday);

	  		document.getElementById("reservedate").value= selday;
	  		
	      //오늘 날짜
	      var today = new Date();
		   var nDay = today.getDate();
	      var nYear = today.getFullYear();
	      var nMonth = (today.getMonth()+1);
	      nMonth = (nMonth >= 10) ? nMonth : '0' + nMonth;
	      nDay = (nDay >= 10) ? nDay : '0' + nDay; 
	      var nDay =nYear+""+nMonth+""+nDay;
	      console.log(today); // 오늘
	      console.log(nDay);
	      
	      var getDiff = seldate.getTime() - today.getTime();
	      var calDay = getDiff / (1000*60*60*24);
	      console.log(calDay, typeof calDay);
	      
		 if(seldate > today){
			 insertModal.show();
		 } else if(seldate <= today){
			  Swal.fire("알림", "해당일은 예약이 가능한 날짜가 아닙니다.", "warning");
		 } else if(calDay > 20){
			 Swal.fire("알림", "해당일은 예약이 가능한 날짜가 아닙니다.", "warning");
		 }
		 
		 setCal.init();
		 
	    }
	});

</script>
 <script src="${path}/calendarjs/default.js"></script>
 <script>
 window.onload = function(){
	 setCal.init();

 }
 </script>
<%@ include file="/footer.jsp" %>

