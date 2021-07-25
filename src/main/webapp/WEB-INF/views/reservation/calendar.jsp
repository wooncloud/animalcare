<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp" %>
    	<!-- calendaer -->
<!--   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"> -->
<!--   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script> -->
<!--   <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
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
    <!-- Modal -->
    <div class="modal fade" id="calModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="calModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="calModalLabel">일정 정보</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body" id="modalContent">
					내용
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				</div>
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
	  
	cal.on('clickSchedule', function (event) {
		var schedule = event.schedule;
		var myModal = new bootstrap.Modal(document.getElementById('calModal'), {
			keyboard: false
		})
		myModal.show();

		// 스케줄, 이벤트 객체 출력
		console.log(schedule);
		console.log(event);
		console.log(schedule.start);
		console.log(schedule.end);
		console.log(schedule.name);
		
  		var date = new Date(schedule.start); //선택날짜
  		var day = date.getDate();
  		var year = date.getFullYear();
  		var month = (1+date.getMonth());
  		month = (month >= 10) ? month : '0' + month;
  		day = (day >= 10) ? day : '0' + day; 
  		
  		var fullday = year+""+month+""+day;//날짜
//  		console.log(year+""+month+""+day);
  		
  		console.log(fullday);
		
		
		var startdate = new Date(schedule.start); //선택날짜
 	
		var starthours = ('0' + startdate.getHours()).slice(-2); 
		var startminutes = ('0' + startdate.getMinutes()).slice(-2);
		var startseconds = ('0' + startdate.getSeconds()).slice(-2); 
		var starttimeString = starthours + ':' + startminutes  + ':' + startseconds;

		console.log(starttimeString);
		
		var enddate = new Date(schedule.end); //선택날짜
	 	
		var endhours = ('0' + enddate.getHours()).slice(-2); 
		var endminutes = ('0' + enddate.getMinutes()).slice(-2);
		var endseconds = ('0' + enddate.getSeconds()).slice(-2); 
		var endtimeString = endhours + ':' + endminutes  + ':' + endseconds;

		console.log(endtimeString);
		
		
// 		var time = date.substr(10,6);
// 		console.log(time);
		

		
		// 내용 출력
		var modalContent = document.getElementById("modalContent");
		modalContent.innerText = 
			"스케줄 seq : " + schedule.id + 			
			"\n스케줄 제목 : " + schedule.title+
			"\n 시간 : " + starttimeString+"~"+endtimeString
	});
  
	
	cal.on({
	    'clickSchedule': function(e) {
	        console.log('clickSchedule', e);
	    },
	    
	    'beforeCreateSchedule': function(e) {
	        console.log('beforeCreateSchedule', e);

	        
	     	var date = e.start;
//	    	console.log(date._date);
//	  		alert(date._date); // 날짜 값 가져와짐 
	  		
	  		var t = 	date._date.toTimeString();
	  		var d = date._date;
//	  		console.log(t);// 시간 정보만
//	  		console.log(d);// 날짜 정보만
	  		
	  		var date = new Date(d); //선택날짜
	  		var day = date.getDate();
	  		var year = date.getFullYear();
	  		var month = (1+date.getMonth());
	  		month = (month >= 10) ? month : '0' + month;
	  		day = (day >= 10) ? day : '0' + day; 
	  		
	  		var fullday = year+""+month+""+day;//날짜
//	  		console.log(year+""+month+""+day);
	  		
	  		console.log(fullday);
	

//	        alert("일단");
	      
	      //오늘 날짜
	      var today = new Date();
		   var nDay = today.getDate();
	      var nYear = today.getFullYear();
	      var nMonth = (today.getMonth()+1);
	      nMonth = (nMonth >= 10) ? nMonth : '0' + nMonth;
	      nDay = (nDay >= 10) ? nDay : '0' + nDay; 
	      var nDay =nYear+""+nMonth+""+nDay;
	      
	      console.log(nDay);
	      location.href="./getSunday.do?date="+fullday;
	     
// 	      if(fullday < nDay || fullday - nDay > 20 ){
// 	    	  alert("해당일은 예약이 가능한 날짜가 아닙니다.")
// 	      }
	 

	        
	        
	        
	        // open a creation popup

	        // If you dont' want to show any popup, just use `e.guide.clearGuideElement()`

	        // then close guide element(blue box from dragging or clicking days)
	        e.guide.clearGuideElement();
	    }

	});
</script>
<script src="${path}/js/default.js"></script>
<%@ include file="/footer.jsp" %>

