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
  <script src="${path}/calendarjs/calendars.js"></script>
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
      
		<div class="col d-grid gap-2 d-md-flex justify-content-md-end">	
			<input type="button" class="btn btn-outline-primary" value="일정등록(병원)" onclick="insertSchedulePage()">
		</div>
		
    </div>
    <div id="calendar" style="width: 800px; top:200px; margin: 0 auto;">
    <input type="hidden" name="hospital_seq" id="hospital_seq" value="${hospital_seq}">
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
	  
	//병원 관계자일 때 상세로 보내자 그냥
	//사용자는 클릭시 아무 이벤트 없다 그냥 확인
	cal.on('clickSchedule', function (event) {
		console.log(event);
		console.log(event.schedule);
		var schedule = event.schedule;
		
		if(schedule.calendarId =='병원일정'){
 		location.href="../hospital/detailSchedulePage.do?seq="+schedule.id;
		} else if(schedule.calendarId =='예약완료'){
		location.href="./hospitalReserveDetail.do?seq="+schedule.id;
		}
	});
	
	
	
	cal.on({
	    'beforeCreateSchedule': function(e) {
	        console.log('beforeCreateSchedule', e);
	        console.log(e.start);
	
	        var hospital_seq = document.getElementById("hospital_seq").value;
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
	  		
		 	location.href="./selectdayReserveList.do?reservedate="+selday+"&hospital_seq="+hospital_seq;

	    }
	});
	
	// 병원 일정 등록 페이지 이동
	function insertSchedulePage(){
		location.href = "../hospital/insertSchedulePage.do";
	}

</script>
<script src="${path}/calendarjs/default.js"></script>
 <script>
 window.onload = function(){
	 setCal.init();
 }
 </script>
<%@ include file="/footer.jsp" %>

