<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<style>
	.login-div{
		max-width: 500px;
		margin: auto;
		top: calc( 50vh - 300px );
		box-shadow: 0px 5px 10px 0px #ccc;
	}
</style>
<script type="text/javascript" src="${path}/js/login.js"></script>
<div class="card login-div">
	<form action="./login.do" method="post">
		<div class="card-body">
			<div class="my-4">
				<h4 class="text-center fw-bold">Pet Care에 오신것을 환영합니다.</h4>
			</div>
			<div class="form-floating my-3">
				<input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
				<label for="email">이메일</label>
			</div>
			<div class="form-floating my-3">
				<input type="password" class="form-control" id="password" name="password" placeholder="password">
				<label for="password">비밀번호</label>
			</div>
		</div>
		<div class="card-footer">
			<div class="my-3 text-center">
				<a href="javascript:login.login()" class="btn btn-success">
					<i class="fas fa-sign-in-alt"></i> 로그인
				</a>
				<a href="./signupAgree.do" class="btn btn-outline-secondary">
					<i class="fas fa-user-plus"></i> 회원가입
				</a>
			</div>
		</div>
	</form>
</div>

<%@include file="/footer.jsp" %>