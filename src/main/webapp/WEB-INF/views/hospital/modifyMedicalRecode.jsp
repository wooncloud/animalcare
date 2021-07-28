<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="./modifyRecode.do" method="post" onsubmit="return modifyRecodeChk(this)">

	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">진료기록 수정</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="button" class="btn btn-outline-primary" value="초기화" onClick="window.location.reload()">
			</div>
		</div>
		<div>		
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							반려동물 이름
						</div>
						<div class="col-3 border-end" >
							${dto.petdto[0].name}	
						</div>
						<div class="col-3 text-center ">
							반려인 이름
						</div>
						<div class="col-3 ">
						 	${dto.userdto[0].name }
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row ">
						<div class="col-3 text-center ">
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
						<div class="col-3 text-center">
							진료내용
						</div>
						<div class="col">
	   						<div >
								<div id="treatmentEditor" ></div>
								<input type="hidden" name="modifyTreatmentContent" id="modifyTreatmentContent">
							</div>
  						</div>
					</div>							
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center">
							처방내용
						</div>
						<div class="col">
	   						<div >
								<div id="prescriptionEditor" ></div>
								<input type="hidden" name="modifyPrescriptionContent" id="modifyPrescriptionContent">
							</div>
  						</div>
					</div>							
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center">
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
						<div class="col-3 text-center">
							병원이름
						</div>
						<div class="col">
	   						${dto.hospitalinfodto[0].name}
  						</div>
  						<input type="hidden" name="hiddenSeq" value="${dto.seq}">
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
			let modifyTreatmentContent = `${dto.treatment}`;
			modifyTreatmentContents.init(modifyTreatmentContent);
			
			let modifyPrescriptionContent = `${dto.prescription}`;
			modifyPrescriptionContents.init(modifyPrescriptionContent);
			
		}
</script>


<%@include file="/footer.jsp" %>