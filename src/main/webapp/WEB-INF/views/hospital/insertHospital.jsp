<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
	<form action="" method="post">
	<br><br>

		<div class="row fs-3 my-2">
			<div class="col">병원 정보 등록</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="reset" class="btn btn-outline-primary" value="초기화">
			</div>
		</div>
		<div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center ">
							병원명(필수)
						</div>
						<div id="hospitalName" class="col">
							<input class="m-1" type="text" name="title">
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
						<div id="email" class="col">
							이메일 구역
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
						<div id="phone" class="col">
							<input class="m-1" type="text" value="">
						</div>
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
							<input class="address-input2 m-1" type="text" id="address1" placeholder="주소" readonly><br>
							<input class="address-input m-1" type="text" id="address2" placeholder="상세주소">
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
								<option value="" selected>&nbsp;선택&nbsp;</option>
								<c:forEach var="dto" items="${petlist}" varStatus="vs">
									<option  value="${dto.codeid}" >${dto.codename}</option>
								</c:forEach>
							</select>
						</div>
					</div>				
					<div class="choice-div" id="choice"></div>
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center m-2">
							내용
						</div>
					</div>				
						<div id="content" class="">
							<div id="editor"></div>
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
							<input class="form-check-input" type="radio" name="flexRadioDefault" id="have"> 
							<label class="form-check-label" for="have">응급실 있음</label>
						</div>
						<div class="col form-check">
							<input class="form-check-input" type="radio" name="flexRadioDefault" id="none" checked> 
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
						<div id="opentime" class="col" onchange="timevalue()">
							월요일 <input id="opentime1" class="timepicker m-1" type="text" readonly>-<input id="closetime1" class="timepicker m-1" type="text" readonly><br>
							화요일 <input id="opentime2" class="timepicker m-1" type="text" readonly>-<input id="closetime2" class="timepicker m-1" type="text" readonly><br>
							수요일 <input id="opentime3" class="timepicker m-1" type="text" readonly>-<input id="closetime3" class="timepicker m-1" type="text" readonly><br>
							목요일 <input id="opentime4" class="timepicker m-1" type="text" readonly>-<input id="closetime4" class="timepicker m-1" type="text" readonly><br>
							금요일 <input id="opentime5" class="timepicker m-1" type="text" readonly>-<input id="closetime5" class="timepicker m-1" type="text" readonly><br>
							토요일 <input id="opentime6" class="timepicker m-1" type="text" readonly>-<input id="closetime6" class="timepicker m-1" type="text" readonly><br>
							일요일 <input id="opentime7" class="timepicker m-1" type="text" readonly>-<input id="closetime7" class="timepicker m-1" type="text" readonly>
						</div>
					</div>				
				</div>
			</div>
		</div>
		
		<br>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
			<input type="button" class="btn btn-outline-primary btn-lg" value="미리보기" onclick=""/>				 	
			<input type="button" class="btn btn-outline-primary btn-lg" value="수정완료" onclick=""/>				 	
			<input type="button" class="btn btn-outline-secondary btn-lg" value="취소" onclick=""/>		
		</div>

	<br><br>
	</form>
</div>


<script type="text/javascript">
		window.onload = function(){
			insertHospital.init();
			
			//타임피커 시간 선택 설정
			$('input.timepicker').timepicker({
			    timeFormat: 'HH:mm',
			    interval: 30,
			    startTime: '00:00',
			    dynamic: false,
			    dropdown: true,
			    scrollbar: true
			});
			
		}
</script>

<%@include file="/footer.jsp" %>