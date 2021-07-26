<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="./insertSchedule.do" method="post" onsubmit="return insertScheduleChk(this)">
	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">병원 일정 상세보기</div>
		</div>
		<div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							일정 제목
						</div>
						<div id="scheduleName" class=" col">
							${dto.title}
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
							<fmt:formatDate value="${dto.schedule}" pattern="yyyy-MM-dd" />		
						</div>
						<div class="col-3 text-center ">
							예약 가능 여부
						</div>
						<div class="col-3 ">
						 	${dto.check}
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center">
							일정 내용
						</div>
						<div class="col">
	   						${dto.content}
  						</div>
					</div>							
				</div>
			</div>
			
		</div>
		
		<br>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
				<input type="button" class="btn btn-outline-primary btn-lg" value="수정하기" onclick="modifySchedulePage('')"/>				 	
    	 		<input type="button" class="btn btn-outline-secondary btn-lg" value="삭제하기" onclick="deleteSchedule('')"/>		
			</div>

	<br><br>
	</form>
</div>


<%@include file="/footer.jsp" %>