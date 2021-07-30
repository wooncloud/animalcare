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
  <script src="${path}/calendarjs/tui-calendar.js"></script>
  <script src="${path}/calendarjs/calendars.js"></script>
  <script src="${path}/calendarjs/schedules.js"></script>
  <script src="${path}/js/mycalendar.js"></script>

 <!-- calendar -->
 <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
 <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
 <link rel="stylesheet" type="text/css" href="${path}/calendarcss/tui-calendar.css">
 <link rel="stylesheet" type="text/css" href="${path}/calendarcss/default.css">
 <link rel="stylesheet" type="text/css" href="${path}/calendarcss/icons.css">
 
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
<input type="hidden" value="3" name="hospital_seq">
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
	      
		 if(selday > nDay){
			 location.href="./getSunday.do?date="+selday;
		 } else if(selday < nDay){
			  Swal.fire("알림", "해당일은 예약이 가능한 날짜가 아닙니다.", "warning");
		 }
		 
		 
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

