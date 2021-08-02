<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

	<div class="mw-lg">
		<div class="my-3">
			<h4>고객의 소리</h4>
		</div>
		<div class="my-3">
			<h5 class="my-3"><strong>회원님들과 만들어가는</strong> PET CARE가 되겠습니다.</h5>
			<p class="my-1">
				PET CARE를 이용하면서 느끼신 불편사항이나 바라는 점을 알려주세요.<br>
				회원님의 소중한 의견으로 한 뼘 더 자라는 PET CARE가 되겠습니다.
			</p>
			<p class="my-1">
				문의량이 많은 경우 답변까지 24시간 이상 걸릴 수 있습니다.<br>
				개별 병원에 대한 문의는 병원 페이지에서 확인하실 수 있습니다.
			</p>
		</div>
		<div class="card">
			<form action="" method="post">
				<div class="card-body">
					<div class="row mb-3">
						<div class="col-md-6">
							<label class="form-label text-muted">신고대상</label>
							<div>누구누구</div>
						</div>
						<div class="col-md-6">
							<label class="form-label text-muted">진료기록 일자</label>
							<div>2021-07-28</div>
						</div>
					</div>
					<div class="mb-3">
						<label class="form-label text-muted">신고종류</label>
						<select name="" class="form-select">

						</select>
					</div>
					<div class="mb-3">
						<label class="form-label text-muted">제목</label>
						<input type="text" class="form-control" name="" placeholder="제목을 입력해 주세요">
					</div>
					<div class="mb-3">
						에디터
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		let reportType = ${codes}
	</script>

<%@include file="/footer.jsp" %>