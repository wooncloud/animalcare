<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<style>
	.select-div{
		margin: auto;
		max-width: 600px;
		height: 400px;
		top: calc( 50vh - 300px );
		box-shadow: 0px 5px 10px 0px #ccc;
		display: flex;
		align-items: center;
    	justify-content: center;
	}

	.btn-signup-select{
		text-decoration: none;
		font-weight: bold;
		font-size: large;
		text-align: center;
		border: 1px solid darkgray;
		border-radius:4px;
		background-color: var(--theme-color5);
		width: 150px;
		height: 130px;
		display: flex;
		align-items: center;
		justify-content: center;
		box-shadow: 0px 2px 5px 0px #ccc;
		flex-direction: column;
		cursor: pointer;
	}

	.btn-signup-select:hover{
		background-color: #a8c9c7;
		border-color: gray;
		color:#395a58;
	}

	.btn-signup-select:active{
		background-color: #88afad;
	}

	.btn-signup-select > div > i{
		font-size: 38pt;
	}
</style>
<script type="text/javascript" src="${path}/js/login.js"></script>
<div class="select-div card">
	<div class="select-header text-center my-3">
		<h2 class="fw-bold">어떤 사용자 인가요?</h2>
		<p class="text-muted">
			✅ 동물병원 관계자인 경우,<br>
			사업자 번호 및 면허번호를 입력하고<br>
			가입 승인을 받아야 합니다.
		</p>
	</div>
	<div class="d-flex justify-content-center align-items-center my-3">
		<div class="btn-signup-select mx-3" id="signupUser">
			<div><i class="fas fa-user mb-2"></i></div>
			<div>사용자</div>
		</div>
		<div class="btn-signup-select mx-3" id="signupOper">
			<div><i class="fas fa-user-nurse mb-2"></i></div>
			<div>병원관계자</div>
		</div>
	</div>
</div>

<%@include file="/footer.jsp" %>