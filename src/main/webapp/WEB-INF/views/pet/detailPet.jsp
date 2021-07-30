<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>

<script src="${path}/js/pet.js"></script>

<div class="container">
	<form action="./detailPet.do" method="post" id="pdto">
		<div class="modal-body">
			<h2 class="modal-title" id="detailModalLabel">상세정보</h2>
			<div class="container-fluid">
				<div class="row my-4">
					<div class="col-md-4 border border-light">
						<c:choose>
							<c:when test="${pdto.profile}">dd</c:when>
							<c:otherwise>
								<img src="${path}/img/noImg.png" class="img-fluid">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-md-8 border border-light">
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">이름</div>
							<div class="col-md-10 py-2 border border-light text-center">${pdto.name}</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">생년월일</div>
							<div class="col-md-10 py-2 border border-light text-center">${pdto.birth}</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">성별</div>
							<div class="col-md-4 py-2 border border-light text-center">${pdto.gender eq 'M'?"남":"여"}</div>
							<div class="col-md-2 py-2 border border-light text-center">무게</div>
							<div class="col-md-4 py-2 border border-light text-center">${pdto.weight}kg</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">종류</div>
							<div class="col-md-4 py-2 border border-light text-center">${pdto.type}</div>
							<div class="col-md-2 py-2 border border-light text-center">종류상세</div>
							<div class="col-md-4 py-2 border border-light text-center">${pdto.typeinfo}</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">등록번호</div>
							<div class="col-md-10 py-2 border border-light text-center">${pdto.aniregistnum}</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">특이사항</div>
							<div class="col-md-10 py-2 border border-light text-center">${pdto.notification}</div>
						</div>

					</div>
				</div>
			</div>
		<div class="footer d-grid gap-2 d-md-flex justify-content-md-center">
			<input type="button" value="수정" class="btn btn-lg btn-primary mx-2" onclick="location.href='./modifyPet.do?name=${pdto.name}'"> 
			<input type="button" value="삭제" class="btn btn-lg btn-secondary mx-2" onclick="delPet('${pdto.name}')">
		</div>
		</div>
	</form>
</div>

<%@include file="/footer.jsp"%>