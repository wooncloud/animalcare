<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="./modifySchedule.do?seq=${dto.seq}" method="post" onsubmit="return modifyScheduleChk(this)">
	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">병원 일정 수정</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="button" class="btn btn-outline-primary" value="초기화" onClick="window.location.reload()">
			</div>
		</div>
		<div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							일정 제목
						</div>
						<div id="scheduleName" class=" col">
							<input class="scheduleName form-control m-1" type="text" id="scheduleName" name="scheduleName" value="${dto.title}">
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
							<input id="scheduleDate" type="date" name="scheduleDate" value="<fmt:formatDate value="${dto.schedule}" pattern="yyyy-MM-dd" />">
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
<!-- 					<div class="row"> -->
<!-- 						<div class="col-3 text-center m-2"> -->
<!-- 							일정 내용 -->
<!-- 						</div> -->
<!-- 					</div>				 -->
						<div >
							<div id="editor" ></div>
							<input type="hidden" name="modifyScheduleContent" id="modifyScheduleContent">
						</div>
				</div>
			</div>
			
		</div>
		
		<br>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">				 	
			<input type="submit" class="btn btn-outline-primary btn-lg" value="수정완료" />				 	
			<input type="button" class="btn btn-outline-secondary btn-lg" value="취소" onclick="goBack()"/>		
		</div>

	<br><br>
	</form>
</div>

<script type="text/javascript">
		window.onload = function(){
			let modifyScheduleContent = `${dto.content}`;
			modifyScheduleContents.init(modifyScheduleContent);
			
		}
</script>

<%@include file="/footer.jsp" %>