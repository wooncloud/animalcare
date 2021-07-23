<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<style>
	.myinfo-div{
		max-width: 600px;
		margin: calc( 50vh - 400px ) auto 0 auto;
	}
</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="${path}/js/login.js"></script>
<div class="card myinfo-div">
	<div class="card-header">
		<h4 class="text-center fw-bold mb-0">내 정보</h4>
	</div>
	<div class="card-body">
		<div class="my-3">
			<label class="text-muted">아이디</label>
			<div><input type="text" class="form-control" value="${user.email}" readonly></div>
		</div>
		<div class="my-3">
			<label class="text-muted">비밀번호</label>
			<div><input type="button" class="btn btn-outline-secondary" value="비밀번호 변경하기" data-bs-toggle="modal" data-bs-target="#pwModal"></div>
		</div>
		<div class="my-3">
			<label class="text-muted">이름</label>
			<div><input type="text" class="form-control" value="${user.name}" readonly></div>
		</div>
		<div class="my-3">
			<label class="text-muted">전화번호</label>
			<div class="input-group">
				<input type="text" id="infoPhone" class="form-control" value="${user.phone}" readonly>
				<input type="button" class="btn btn-outline-secondary" value="전화번호 변경하기" data-bs-toggle="modal" data-bs-target="#phoneModal">
			</div>
		</div>
		<div class="my-3">
			<label class="text-muted">주소</label>
			<div class="input-group">
				<input type="text" id="infoAddress1" class="form-control" value="${user.address1}" readonly>
				<input type="text" id="infoAddress2" class="form-control" value="${user.address2}" readonly>
				<input type="button" class="btn btn-outline-secondary" value="주소 변경하기" data-bs-toggle="modal" data-bs-target="#addrModal">
			</div>
		</div>
	</div>
</div>
<div class="mt-2 text-end myinfo-div">
	<a href="./leaveUserForm.do" class="btn btn-outline-light">탈퇴하기</a>
</div>

<div id="pwModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">비밀번호 변경</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<input type="password" id="pw" class="form-control my-1" placeholder="기존의 비밀번호를 입력하세요.">
				<input type="password" id="npw" class="form-control my-1" placeholder="새 비밀번호를 입력하세요.">
				<input type="password" id="npwc" class="form-control my-1" placeholder="새 비밀번호를 확인.">
				</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-success" value="변경" onclick="myinfo.changePW()">
			</div>
		</div>
	</div>
</div>
<div id="phoneModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">전화번호 변경</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="input-group my-1">
					<input type="tel" id="phone" class="form-control" placeholder="새로운 전화번호를 입력하세요.">
					<input type="button" id="btnSendPhoneCode" class="btn btn-secondary" value="인증번호 전송" onclick="myinfo.sendPhoneCode()">
				</div>
				<div class="input-group my-1">
					<input type="text" id="phoneConfirm" class="form-control" placeholder="인증번호 입력">
					<input type="button" class="btn btn-success" value="인증 및 변경" onclick="myinfo.verifyPhone()">
				</div>
			</div>
		</div>
	</div>
</div>
<div id="addrModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">주소 변경</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<input type="button" class="btn btn-secondary" value="주소 찾기" onclick="myinfo.addrSearch()">
				<input type="text" id="address1" class="form-control my-1" placeholder="위의 주소찾기 버튼을 클릭해 주세요" readonly>
				<input type="text" id="address2" class="form-control my-1" placeholder="상세 주소를 입력하세요..">
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-success" value="변경" onclick="myinfo.changeAddr()">
			</div>
		</div>
	</div>
</div>
<%@include file="/footer.jsp" %>