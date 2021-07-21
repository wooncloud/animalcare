<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<style type="text/css">
	.signup-container{
		max-width: 600px;
		margin: auto;
	}
	
	.small-font{
		font-size: small;
	}
</style>
<script type="text/javascript" src="${path}/js/login.js"></script>

<div class="signup-container">
	<form action="./signupUser.do" method="post">
		<div>
			<div class="signup-row my-3">
				<label for="email" class="form-label">이메일 주소</label>
				<div class="input-group">
					<input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
					<input type="button" class="btn btn-outline-secondary" id="btnEmailDupl" value="중복검사">
					<input type="button" class="btn btn-outline-secondary" id="btnEmailSendConfirm" value="인증번호 발송">
				</div>
			</div>
			<div class="signup-row my-3">
				<label for="emailChk" class="form-label">이메일 인증번호</label>
				<div class="input-group">
					<input type="text" class="form-control" id="emailChk" placeholder="인증번호 입력" readonly>
					<input type="button" class="btn btn-outline-secondary" id="btnEmailConfirm" value="인증">
				</div>
			</div>
			<div class="signup-row my-3">
				<div class="row">
					<div class="col-md-6">
						<label for="password" class="form-label">비밀번호</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요.">
					</div>
					<div class="col-md-6">
						<label for="pwc" class="form-label">비밀번호 확인</label>
						<input type="password" class="form-control" id="pwc" placeholder="비밀번호 확인">
					</div>
				</div>
				<label class="text-muted small-font">비밀번호는 영문 최소 8자, 대문자, 소문자, 숫자 및 특수 문자 하나 이상 포함되어야 합니다.</label>
			</div>
			<div class="signup-row my-3">
				<label for="name" class="form-label">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요.">
			</div>
			<div class="signup-row my-3">
				<div class="row">
					<div class="col-md-6">
						<label for="phone" class="form-label">전화번호</label>
						<div class="input-group">
							<input type="tel" class="form-control" id="phone" name="phone" placeholder="010-0000-0000">
							<input type="button" class="btn btn-outline-secondary" id="btnPhoneSendConfirm" value="인증번호 발송">
						</div>
					</div>
					<div class="col-md-6">
						<label for="phoneConfirm" class="form-label">전화번호 인증</label>
						<div class="input-group">
							<input type="text" class="form-control" id="phoneConfirm" placeholder="인증번호" readonly>
							<input type="button" class="btn btn-outline-secondary" id="btnPhoneConfirm" value="인증">
						</div>
					</div>
				</div>
			</div>
			<div class="signup-row my-3">
				<label for="address1" class="form-label">주소</label>
				<div class="input-group">
					<input type="text" class="form-control" id="address1" name="address1" placeholder="주소를 입력하세요." readonly>
					<input type="button" class="btn btn-outline-secondary" id="btnAddrSearch" value="주소찾기">
				</div>
			</div>
			<div class="signup-row my-3">
				<label for="address2" class="form-label">상세주소</label>
				<input type="text" class="form-control" id="address2" name="address2" placeholder="상세주소를 입력하세요.">
			</div>
		</div>
		<div class="my-5 text-center">
			<input type="button" class="btn btn-secondary mx-1" id="btnCancel" value="취소">
			<input type="button" class="btn btn-success mx-1" id="btnSignup" value="가입하기">
		</div>
	</form>
</div>

<%@include file="/footer.jsp" %>