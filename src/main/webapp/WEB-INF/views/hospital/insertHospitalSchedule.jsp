<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="./insertSchedule.do" method="post" onsubmit="return insertScheduleChk(this)">
	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">병원 일정 등록</div>
		</div>
		<div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							일정 제목
						</div>
						<div id="scheduleName" class="col">
							<input class="scheduleName m-1" type="text" id="scheduleName" name="scheduleName">
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							일정 일자
						</div>
						<div class="col-3 border-end" >
							<input id="scheduleDate" type="date" name="scheduleDate">
						</div>
						<div class="col-2 text-center ">
							예약 가능 여부
						</div>
						<div class="col-2 form-check">
							<input class="form-check-input" type="radio" name="reservationChk" id="have" value="Y"> 
							<label class="form-check-label" for="have">예약 가능</label>
						</div>
						<div class="col-2 form-check">
							<input class="form-check-input" type="radio" name="reservationChk" id="none" value="N" checked> 
							<label class="form-check-label" for="none">예약 불가능</label>
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center m-2">
							일정 내용
						</div>
					</div>				
						<div >
							<div id="editor" ></div>
							<input type="hidden" name="scheduleContent" id="scheduleContent">
						</div>
				</div>
			</div>
			
		</div>
		
		<br>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">				 	
			<input type="submit" class="btn btn-outline-primary btn-lg" value="등록하기" />				 	
			<input type="button" class="btn btn-outline-secondary btn-lg" value="취소" onclick="history.back()"/>		
		</div>

	<br><br>
	</form>
</div>

<script type="text/javascript">
		window.onload = function(){
			addSchedule.init();
			
		}
</script>

<%@include file="/footer.jsp" %>