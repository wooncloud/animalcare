<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<style>
	.myinfo-div{
		max-width: 600px;
		margin: auto;
	}
</style>
<script type="text/javascript" src="${path}/js/login.js"></script>
<div class="card myinfo-div">
	<div class="card-body">
		<div class="my-4">
			<h4 class="text-center fw-bold">내 정보</h4>
		</div>
		<div class="my-3">
			<label class="text-muted">아이디</label>
			<div>
				<label class="form-label">${user.id}</label>
			</div>
		</div>
		<div class="my-3">
			<label class="text-muted">비밀번호</label>
			<div>
				<input type="button" class="btn btn-secondary" value="비밀번호 변경하기">
			</div>
		</div>
		<div class="my-3">
			<label class="text-muted">이름</label>
			<div>
				<label class="form-label">${user.name}</label>
			</div>
		</div>
		<div class="my-3">
			<label class="text-muted">전화번호</label>
			<div class="row">
				<label class="form-label">${user.phone}</label>
				<input type="button" class="btn btn-secondary" value="전화번호 변경하기">
			</div>
		</div>
		<div class="my-3">
			<label class="text-muted">주소</label>
			<div class="row">
				<label class="form-label">${user.address1}</label>
				<label class="form-label">${user.address2}</label>
				<input type="button" class="btn btn-secondary" value="주소 변경하기">
			</div>
		</div>
	</div>
	<div class="mt-2 text-end">
		<a href="./leaveUserForm.do" class="btn btn-outline-light">탈퇴하기</a>
	</div>
</div>
<%@include file="/footer.jsp" %>