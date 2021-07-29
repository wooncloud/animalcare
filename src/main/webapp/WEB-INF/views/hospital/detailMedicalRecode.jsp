<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">

	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">진료기록 상세보기</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">	
				<input type="button" class="btn btn-outline-primary" value="이전페이지" onclick="history.back()">
			</div>
		</div>
		<div>		
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-2 text-center ">
							반려동물 이름
						</div>
						<div class="col-4 border-end" >
							${dto.petdto[0].name}	
						</div>
						<div class="col-2 text-center ">
							반려인 이름
						</div>
						<div class="col-4 ">
						 	${dto.userdto[0].name }
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-2 text-center ">
							증상
						</div>
						<div id="scheduleName" class=" col">
							${dto.symptom}
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-2 text-center">
							진료내용
						</div>
						<div class="col">
	   						${dto.treatment}
  						</div>
					</div>							
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-2 text-center">
							처방내용
						</div>
						<div class="col">
	   						${dto.prescription}
  						</div>
					</div>							
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-2 text-center">
							진료일자
						</div>
						<div class="col">
							<fmt:formatDate value="${dto.treatdate}" pattern="yyyy-MM-dd" />	
  						</div>
					</div>							
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-2 text-center">
							병원이름
						</div>
						<div class="col">
	   						${dto.hospitalinfodto[0].name}
  						</div>
					</div>							
				</div>
			</div>
		</div>
		
		<br>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
				<input type="button" class="btn btn-outline-primary btn-lg" value="수정하기" onclick="location.href='./modifyRecodeHospital.do?seq=${dto.seq}';"/>				 		
		</div>

	<br><br>

</div>


<%@include file="/footer.jsp" %>