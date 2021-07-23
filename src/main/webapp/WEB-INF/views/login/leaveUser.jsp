<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>


<style type="text/css">
	.leave-container{
		max-width: 600px;
		margin: auto;
	}
</style>

<script type="text/javascript" src="${path}/js/login.js"></script>
<div class="leave-container">
	<div class="card">
		<div class="card-body">
			<div>
				<h3 class="fw-bold text-center">회원 탈퇴 안내</h3>
				<div class="mb-5 text-center">회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.</div>
				<p>
					▶ 사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능합니다.
					탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가하오니 신중하게 선택하시기 바랍니다.
				</p>
				<p>
					▶ 탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.
					회원정보 및 반려동물 정보, 예약내역, 진료내역 등 개인형 서비스 이용기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.
					삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.
				</p>
				<p>
					▶ 탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.
					설문내용, 병원문의, 시스템 문의 등에 올린 게시글은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다.
					탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 임의로 삭제해드릴 수 없습니다.
				</p>
				<p>
					▶ 탈퇴 후에는 해당 아이디로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.
					게시판형 서비스에 남아 있는 게시글은 탈퇴 후 삭제할 수 없습니다.
				</p>
				<hr>
				<div>
					<input class="form-check-input agree-chk" type="checkbox" id="agree">
					<label class="form-check-label" for="agree">안내 사항을 모두 확인하였으며, 이에 동의합니다.</label>
				</div>
			</div>
		</div>
		<div class="card-footer text-center">
			<input type="button" value="탈퇴하기" class="btn btn-lg btn-secondary mx-1" onclick="leave.leave()">
		</div>
	</div>
</div>

<%@include file="/footer.jsp" %>