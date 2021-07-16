<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>캘린더</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

	<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.css" />
	<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css" />
	<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css" />
	<script src="https://uicdn.toast.com/tui.code-snippet/v1.5.2/tui-code-snippet.min.js"></script>
	<script src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
	<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
	<script src="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.js"></script>

<body>
	<div id="calendar"></div>
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
</body>
<script type="text/javascript">
	window.onload = function () {

		//월별	
		var Calendar = tui.Calendar;

		//테마 설정
		var COMMON_CUSTOM_THEME = {
			'common.border': '1px solid #ffbb3b',
			'common.backgroundColor': '#ffbb3b0f',
			'common.holiday.color': '#f54f3d',
			'common.saturday.color': '#3162ea',
			'common.dayname.color': '#333'
		};

		var calendar = new Calendar('#calendar', {
			defaultView: 'month', //day, week
			month: {
				// 		  workweek: true // 주말 숨기기 show only 5 days except for weekend 
			},
			scheduleView: ['time'], //  allday / time
			// 	  theme: COMMON_CUSTOM_THEME, //테마 입히세요
			taskView: true, // task 보기
			useCreationPopup: false,
			useDetailPopup: true,
			template: templates
		});
		//클릭시 팝업에 내용나옴
		var templates = {
			popupIsAllDay: function () {
				return 'All Day';
			},
			popupStateFree: function () {
				return 'Free';
			},
			popupStateBusy: function () {
				return 'Busy';
			},
			titlePlaceholder: function () {
				return 'Subject';
			},
			locationPlaceholder: function () {
				return 'Location';
			},
			startDatePlaceholder: function () {
				return 'Start date';
			},
			endDatePlaceholder: function () {
				return 'End date';
			},
			popupUpdate: function () {
				return 'Update';
			},
			popupDetailDate: function (isAllDay, start, end) {
				var isSameDate = moment(start).isSame(end);
				var endFormat = (isSameDate ? '' : 'YYYY.MM.DD ') + 'hh:mm a';

				if (isAllDay) {
					return moment(start).format('YYYY.MM.DD') + (isSameDate ? '' : ' - ' + moment(end).format('YYYY.MM.DD'));
				}

				return (moment(start).format('YYYY.MM.DD hh:mm a') + ' - ' + moment(end).format(endFormat));
			},
			popupDetailLocation: function (schedule) {
				console.log(schedule);
				return 'Location : ' + schedule.location;
			},
			popupDetailUser: function (schedule) {
				return 'User : ' + (schedule.attendees || []).join(', ');
			},
			popupDetailState: function (schedule) {
				return 'State : ' + schedule.state || 'Busy';
			},
			popupDetailRepeat: function (schedule) {
				console.log("popupDetailRepeat");
				return 'Repeat : ' + schedule.recurrenceRule;
			},
			popupDetailBody: function (schedule) {
				console.log("popupDetailBody");
				return 'Body : ' + schedule.body;
			},
			popupEdit: function () {
				return 'Edit';
			},
			popupDelete: function () {
				return 'Delete';
			}
		};

		calendar.createSchedules([
			{
				id: '3',
				calendarId: 'Travel', // calendarId가 바뀌었죠?
				title: '이렇게 하면 스케줄 추가 된다',
				category: 'task', // 'allday'로 지정하면 allday에 표시 시간 상관없음 task 하면 시간까지 
				start: '2021-07-03T07:00:00',
				end: '2021-07-05T09:00:00',
				color: '#ffffff', // 일정 색상을 직접 지정할 수 있음
				bgColor: '#03bd9e',
				dragBgColor: '#03bd9e',
				borderColor: '#03bd9e'
			},
			{
				id: '4',
				calendarId: 'Major Lecture',
				title: '변경',
				category: 'allday', // 'allday'로 지정하면 allday에 표시 시간 상관없음 task 하면 시간
				start: '2021-07-03T07:00:00',
				end: '2021-07-05T09:00:00',
				color: '#ffffff',
				bgColor: '#03bd9e',
				dragBgColor: '#03bd9e',
				borderColor: '#03bd9e'
			},
		]);

		//입력
		// calendar.on('beforeCreateSchedule', scheduleData => {
		// 	const schedule = {
		// 		calendarId: 'Major Lecture',
		// 		id: String(Math.random() * 100000000000000000),
		// 		title: scheduleData.title,
		// 		isAllDay: scheduleData.isAllDay,
		// 		start: scheduleData.start,
		// 		end: scheduleData.end,
		// 		category: scheduleData.isAllDay ? 'allday' : 'time'
		// 	};

		// 	calendar.createSchedules([schedule]);

		// 	alert('일정 생성 완료');
		// });

		//수정
		calendar.on('beforeUpdateSchedule', scheduleData => {
			const { schedule } = scheduleData;

			calendar.updateSchedule(schedule.id, schedule.calendarId, schedule);
		});

		//삭제		
		calendar.on('beforeDeleteSchedule', scheduleData => {
			const { schedule, start, end } = scheduleData;

			schedule.start = start;
			schedule.end = end;
			calendar.deleteSchedule(schedule.id, schedule.calendarId);
		});

		// 클릭 이벤트 (아래 참고자료)
		// https://github.com/nhn/tui.calendar/blob/master/docs/getting-started.md#clickschedule
		calendar.on('clickSchedule', function (event) {
			var schedule = event.schedule;

			var myModal = new bootstrap.Modal(document.getElementById('calModal'), {
				keyboard: false
			})
			myModal.show();

			// 스케줄, 이벤트 객체 출력
			console.log(schedule);
			console.log(event);

			// 내용 출력
			var modalContent = document.getElementById("modalContent");
			modalContent.innerText = 
				"스케줄 seq : " + schedule.id + 
				"\n스케줄 제목 : " + schedule.title;
		});
	}
</script>

</html>