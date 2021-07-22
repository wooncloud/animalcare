/* eslint-disable */
function init() {
  cal.setCalendars(CalendarList);

  setRenderRangeText();
  setSchedules();
  setEventListener();


}

function getDataAction(target) {
  return target.dataset ? target.dataset.action : target.getAttribute('data-action');
}

function setDropdownCalendarType() {
  var calendarTypeName = document.getElementById('calendarTypeName');
  var calendarTypeIcon = document.getElementById('calendarTypeIcon');
  var options = cal.getOptions();
  var type = cal.getViewName();
  var iconClassName;

  if (type === 'day') {
    type = 'Daily';
    iconClassName = 'calendar-icon ic_view_day';
  } else if (type === 'week') {
    type = 'Weekly';
    iconClassName = 'calendar-icon ic_view_week';
  } else if (options.month.visibleWeeksCount === 2) {
    type = '2 weeks';
    iconClassName = 'calendar-icon ic_view_week';
  } else if (options.month.visibleWeeksCount === 3) {
    type = '3 weeks';
    iconClassName = 'calendar-icon ic_view_week';
  } else {
    type = 'Monthly';
    iconClassName = 'calendar-icon ic_view_month';
  }

  calendarTypeName.innerHTML = type;
  calendarTypeIcon.className = iconClassName;
}

function onClickMenu(e) {
  var target = $(e.target).closest('a[role="menuitem"]')[0];
  var action = getDataAction(target);
  var options = cal.getOptions();
  var viewName = '';

  switch (action) {
    case 'toggle-daily':
      viewName = 'day';
      break;
    case 'toggle-weekly':
      viewName = 'week';
      break;
    case 'toggle-monthly':
      options.month.visibleWeeksCount = 0;
      viewName = 'month';
      break;
    case 'toggle-weeks2':
      options.month.visibleWeeksCount = 2;
      viewName = 'month';
      break;
    case 'toggle-weeks3':
      options.month.visibleWeeksCount = 3;
      viewName = 'month';
      break;
    case 'toggle-narrow-weekend':
      options.month.narrowWeekend = !options.month.narrowWeekend;
      options.week.narrowWeekend = !options.week.narrowWeekend;
      viewName = cal.getViewName();

      target.querySelector('input').checked = options.month.narrowWeekend;
      break;
    case 'toggle-start-day-1':
      options.month.startDayOfWeek = options.month.startDayOfWeek ? 0 : 1;
      options.week.startDayOfWeek = options.week.startDayOfWeek ? 0 : 1;
      viewName = cal.getViewName();

      target.querySelector('input').checked = options.month.startDayOfWeek;
      break;
    case 'toggle-workweek':
      options.month.workweek = !options.month.workweek;
      options.week.workweek = !options.week.workweek;
      viewName = cal.getViewName();

      target.querySelector('input').checked = !options.month.workweek;
      break;
    default:
      break;
  }

  cal.setOptions(options, true);
  cal.changeView(viewName, true);

  setDropdownCalendarType();
  setRenderRangeText();
  setSchedules();
}

function onClickNavi(e) {
  var action = getDataAction(e.target);

  switch (action) {
    case 'move-prev':
      cal.prev();
      break;
    case 'move-next':
      cal.next();
      break;
    case 'move-today':
      cal.today();
      break;
    default:
      return;
  }

  setRenderRangeText();
  setSchedules();
}

function setRenderRangeText() {
  var renderRange = document.getElementById('renderRange');
  var options = cal.getOptions();
  var viewName = cal.getViewName();
  var html = [];
  if (viewName === 'day') {
    html.push(moment(cal.getDate().getTime()).format('YYYY.MM.DD'));
  } else if (viewName === 'month' &&
    (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
    html.push(moment(cal.getDate().getTime()).format('YYYY.MM'));
  } else {
    html.push(moment(cal.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
    html.push(' ~ ');
    html.push(moment(cal.getDateRangeEnd().getTime()).format('MM.DD'));
  }
  renderRange.innerHTML = html.join('');
}

function setSchedules() {
  cal.clear();
//  generateSchedule(cal.getViewName(), cal.getDateRangeStart(), cal.getDateRangeEnd());
//  cal.createSchedules(ScheduleList);
//  refreshScheduleVisibility();
	

//	var doc = document.getElementsByClassName("tui-full-calendar-weekday-grid-header");
//	var root = document.getElementsByClassName("tui-full-calendar-weekday-grid-header")[0]
//	var first = document.getElementsByClassName("tui-full-calendar-weekday-grid-header")[0].childNodes[1].innerText;
	
	
//	console.log(doc);
//	console.log(root);
//	console.log(doc.length);
//	console.log(first);

//	for(var i = 0; i < doc.length; i++){
//		cal.createSchedules([
//			{
//				id: i,
//				calendarId: 'CDC', // 
//				title: '예약가능',
//				category: 'time', // 
//				start: '2021-07-'+''+first+i+''+'07:00:00',
//				end: '2021-07-'+''+first+i+''+'09:00:00',
//				color: '#black', // 일정 색상을 직접 지정할 수 있음
//				bgColor: '#ccc',
//				dragBgColor: '#03bd9e',
//				borderColor: '#03bd9e'
//			}	
//		]);
//	}

	
	$.ajax({
		type:"get",
		url:"./calendar.do?hospital_seq=3",
		dataType:"text",
		async:true,
		success:function(msg){
			alert("성공");
			console.log(msg);
			
		},
		error:function(){
			alert("잘못된 요청")
		}
	});
	
	
	cal.createSchedules([
			{
				id: '3',
				calendarId: 'CDC', // 
				title: '예약가능',
				name:'백지현',
				category: 'time', // 
				start: '2021-07-14 07:00:00',
				end: '2021-07-14 09:00:00',
				color: '#black', // 일정 색상을 직접 지정할 수 있음
				bgColor: '#ccc',
				dragBgColor: '#ccc',
				borderColor: '#ccc'
			},
				{
				id: '4',
				calendarId: 'CDC', // 
				title: '예약가능',
				category: 'time', // 
				start: '2021-07-14 09:00:00',
				end: '2021-07-14 11:00:00',
				color: '#black', // 일정 색상을 직접 지정할 수 있음
				bgColor: '#ccc',
				dragBgColor: '#ccc',
				borderColor: '#ccc'
			},	{
				id: '5',
				calendarId: 'CDC', // 
				title: '예약가능',
				category: 'time', // 
				start: '2021-07-14 11:00:00',
				end: '2021-07-14 13:00:00',
				color: '#black', // 일정 색상을 직접 지정할 수 있음
				bgColor: '#ccc',
				dragBgColor: '#ccc',
				borderColor: '#ccc'
			},	
			{
				id: '6',
				calendarId: 'CDC', // 
				title: '예약불가',
				category: 'time', // 
				start: '2021-07-14 15:00:00',
				end: '2021-07-14 17:00:00',
				color: '#black', // 일정 색상을 직접 지정할 수 있음
				bgColor: '#ccc',
				dragBgColor: '#03bd9e',
				borderColor: '#03bd9e'
			}
		]);

}


function refreshScheduleVisibility() {
  var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));

  CalendarList.forEach(function(calendar) {
    cal.toggleSchedules(calendar.id, !calendar.checked, false);
  });

  cal.render(true);

  calendarElements.forEach(function(input) {
    var span = input.nextElementSibling;
    span.style.backgroundColor = input.checked ? span.style.borderColor : 'transparent';
  });
}

resizeThrottled = tui.util.throttle(function() {
  cal.render();
}, 50);

function setEventListener() {
  $('.dropdown-menu a[role="menuitem"]').on('click', onClickMenu);
  $('#menu-navi').on('click', onClickNavi);
  window.addEventListener('resize', resizeThrottled);
}

cal.on({
  'clickTimezonesCollapseBtn': function(timezonesCollapsed) {
    if (timezonesCollapsed) {
      cal.setTheme({
        'week.daygridLeft.width': '77px',
        'week.timegridLeft.width': '77px'
      });
    } else {
      cal.setTheme({
        'week.daygridLeft.width': '60px',
        'week.timegridLeft.width': '60px'
      });
    }

    return true;
  }
});

init();
