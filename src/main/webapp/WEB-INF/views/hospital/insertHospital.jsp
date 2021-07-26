<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="./insertHospital.do" method="post" onsubmit="return insertHospitalChk(this)">
	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">병원 정보 등록</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="button" class="btn btn-outline-primary" value="초기화" onClick="window.location.reload()">
			</div>
		</div>
		<div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center">
							병원명(필수)
						</div>
						<div  class="col-3">
							<input class="form-control m-1" type="text" name="name" id="name">
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
			<div class="card ">
				<div class="card-body ">
					<div class="row ">
						<div class="col-3 text-center ">
							전화번호(필수)
						</div>
						<div class="col-3">
							<input class="phoneNumber form-control m-1" type="text" name="tel" id="tel" maxlength="13" placeholder="ex) 02-123-4567" >
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
						<div class="col">
							<input class=" address-input m-1 " type="text" id="postcode" placeholder="우편번호" readonly>
							<input class="btn btn-outline-success m-1" type="button" onclick="findAdress()" value="우편번호 찾기"><br>
							<input class="address-input2 m-1" type="text" id="address1" name="address1" placeholder="주소" readonly><br>
							<input class="address-input m-1" type="text" id="address2" name="address2" placeholder="상세주소">
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
							<select class="form-select" id="selectPetType" name="selectPetType" onchange="selectType()">
								<option value="selectOne" selected>&nbsp;선택&nbsp;</option>
								<c:forEach var="dto" items="${petlist}" varStatus="vs">
									<option  value="${dto.codeid}" >${dto.codename}</option>
								</c:forEach>
							</select>
						</div>
					</div>				
					<span class="choice-div" id="choice" ></span>
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
							<input type="hidden" name="insertContent" id="insertContent">
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
						<div class="col form-check">
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
						<div  class="col" >
							<textarea rows="5" class="form-control" name="opentime" id="opentime" placeholder="ex) 평일 10:00-18:00  주말 11:00-17:00"></textarea>
						</div>
					</div>				
				</div>
			</div>
		</div>
		
		<br>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
			<input type="button" class="btn btn-outline-primary btn-lg" value="미리보기" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="modalContent()"/>				 	
			<input type="submit" class="btn btn-outline-primary btn-lg" value="등록하기" />				 	
			<input type="button" class="btn btn-outline-secondary btn-lg" value="취소" onclick="goBack()"/>		
		</div>

		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" aria-labelledby="staticBackdropLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">미리보기</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="container-fluid">

							<div>
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-2 text-center border-end">병원명</div>
											<div id="nameModal" class="col"></div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-2 text-center border-end">진료항목</div>
											<div id="pettypesModal" class="col"></div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-2 text-center border-end">전화번호</div>
											<div class="col-4 border-end" id="telModal"></div>
											<div class="col-2 text-center border-end">응급실 여부</div>
											<div class="col-4" id="emergencyModal"></div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-2 text-center border-end">주소</div>
											<div id="addressModal" class="col-4 border-end"></div>
											<div class="col-2 text-center border-end">운영시간</div>
											<div class="col-4" id="opentimeModal"></div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-2 text-center">내용</div>
											<div class="col" id="contentModal"></div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="close-btn btn btn-outline-success" data-bs-dismiss="modal" value="닫기">
					</div>
				</div>
			</div>
		</div>

		<br><br>
	</form>
</div>


<script type="text/javascript">
		window.onload = function(){
			insertHospital.init();
			
		}
</script>

<%@include file="/footer.jsp" %>