<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="./modifyHospital.do" method="post" onsubmit="return modifyHospitalChk(this)">
	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">병원 정보 수정</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="button" class="btn btn-outline-primary" value="초기화" onClick="window.location.reload()">
			</div>
		</div>
		<div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							병원명(필수)
							<input type="hidden" name="seq" value="">
						</div>
						<div id="hospitalName" class="col-3">
							<input class="form-control m-1" type="text" name="name" value="${hjdto.name}">
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							이메일(필수)
						</div>
						<div id="email" class="col-3">
							<input class="form-control m-1" name="email" type="text" value="${sessionScope.member.email}" readonly>
						</div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							전화번호(필수)
						</div>
						<div id="tel" class="col-3">
							<input class="phoneNumber form-control m-1" type="text" name="tel" value="${hjdto.tel}">
						</div>
							** 숫자만 입력하시면 - 은 자동 생성됩니다.
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							주소(필수)
						</div>
						<div id="address" class="col">
							<input class="address-input m-1 " type="text" id="postcode" placeholder="우편번호" readonly>
							<input class="btn btn-outline-success m-1" type="button" onclick="findAdress()" value="우편번호 찾기"><br>
							<input class="address-input2 m-1" type="text" id="address1" name="address1" value="${hjdto.address1}" readonly><br>
							<input class="address-input m-1" type="text" id="address2" name="address2" value="${hjdto.address2}">
							<input class="address-input m-1" type="text" id="extraAddress" placeholder="참고항목">
						</div>
					</div>				
				</div>
			</div>
			<div class="card" id="petType">
				<div class="card-body">
					<div class="row my-2" >
						<div class="col-3 text-center">
							진료 항목(필수)
						</div>
						<div class="col-2" >
							<select class="form-select" id="selectPetType" onchange="selectType()">
								<option value="selectOne" selected>&nbsp;선택&nbsp;</option>
								<c:forEach var="dto" items="${petlist}" varStatus="vs">
									<option  value="${dto.codeid}" >${dto.codename}</option>
								</c:forEach>
							</select>
						</div>
					</div>				
					<span class="choice-div" id="choice" ></span>
					<input type="hidden" value="${hjdto.pettypedto[0].pettype}" name="hiddenPetType" id="hiddenPetType">
				</div>
			</div>
			<div class="card">
				<div class="card-body">
<!-- 					<div class="row"> -->
<!-- 						<div class="col-3 text-center m-2"> -->
<!-- 							내용 -->
<!-- 						</div> -->
<!-- 					</div>				 -->
						<div>
							<div id="editor"></div>
							<input type="hidden" name="modifyContents" id="modifyContents">
						</div>
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							응급실 여부(필수)
						</div>
						<div class="col-1"></div>
						<div id="emergency" class="col form-check">
							<input class="form-check-input" type="radio" name="emergencyRadio" id="have" value="Y"> 
							<label class="form-check-label" for="have">응급실 있음</label>
						</div>
						<div class="col form-check">
							<input class="form-check-input" type="radio" name="emergencyRadio" id="none" value="N" checked> 
							<label class="form-check-label" for="none">응급실 없음</label>
						</div>
						<div class="col-1"></div>
					</div>				
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							운영시간(필수)
						</div>
						<div id="opentime" class="col">
							<textarea rows="5" class="form-control" name="opentime" >${hjdto.opentime}</textarea>
						</div>
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
			let modifyContents = `${hjdto.content}`;
			modifyHospitalContents.init(modifyContents);
			
			let petCode = ${petJson}
			hiddenPetTypes.init(hiddenPetType, petCode);
			
		}
</script>

<%@include file="/footer.jsp" %>