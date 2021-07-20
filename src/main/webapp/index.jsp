<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
	

	<div>
		<div>
			<h3>버튼 fill</h3>
			<button type="button" class="btn btn-primary">Primary</button>
			<button type="button" class="btn btn-secondary">Secondary</button>
			<button type="button" class="btn btn-success">Success</button>
			<button type="button" class="btn btn-danger">Danger</button>
			<button type="button" class="btn btn-warning">Warning</button>
			<button type="button" class="btn btn-info">Info</button>
			<button type="button" class="btn btn-light">Light</button>
			<button type="button" class="btn btn-dark">Dark</button>
		</div>
		<hr class="my-3">
		<div>
			<h3>버튼 line</h3>
			<button type="button" class="btn btn-outline-primary">Primary</button>
			<button type="button" class="btn btn-outline-secondary">Secondary</button>
			<button type="button" class="btn btn-outline-success">Success</button>
			<button type="button" class="btn btn-outline-danger">Danger</button>
			<button type="button" class="btn btn-outline-warning">Warning</button>
			<button type="button" class="btn btn-outline-info">Info</button>
			<button type="button" class="btn btn-outline-light">Light</button>
			<button type="button" class="btn btn-outline-dark">Dark</button>
		</div>
		<hr class="my-3">
		<div>
			<h3>페이징</h3>
			<nav>
				<ul class="pagination">
					<li class="page-item">
						<a class="page-link" href="#" aria-label="Previous">
							<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
						</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item">
						<a class="page-link" href="#" aria-label="Next">
							<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
		<hr class="my-3">
		<div>
			<h3>아코디언</h3>
			<div class="accordion" id="acco">
				<div class="accordion-item">
					<h2 class="accordion-header" id="acco1">
						<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
							질문 #1
						</button>
					</h2>
					<div id="collapse1" class="accordion-collapse collapse show" aria-labelledby="acco1" data-bs-parent="#acco">
						<div class="accordion-body">
							이것은 질뭇
						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header" id="acco2">
						<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
							질문 #2
						</button>
					</h2>
					<div id="collapse2" class="accordion-collapse collapse" aria-labelledby="acco2" data-bs-parent="#acco">
						<div class="accordion-body">
							요거도 질문
						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header" id="acco3">
						<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse3" aria-expanded="false" aria-controls="collapse3">
							질문 #3
						</button>
					</h2>
					<div id="collapse3" class="accordion-collapse collapse" aria-labelledby="acco3" data-bs-parent="#acco">
						<div class="accordion-body">
							질문3
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr class="my-3">
		<div>
			<h3>카드</h3>
			<div class="card">
				<div class="card-body">
					카드 애용합시다. 카드 좋아요
				</div>
			</div>
			<br>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">제목</h5>
					<h6 class="card-subtitle mb-2 text-muted">서브제목</h6>
					<p class="card-text">내용 내용 내용</p>
				</div>
			</div>
			<br>
			<div class="card">
				<div class="card-header">
					카드 제목
				</div>
				<div class="card-body">
					<h5 class="card-title">오 아주 놀랍군요</h5>
					<p class="card-text">다들 열심히 해야합니다. 그것이 우리가 사는 이유니까요</p>
					<a href="#" class="btn btn-primary">열심히 하러 가기</a>
				</div>
				<div class="card-footer text-muted">
					카드 푸터
				</div>
			</div>
		</div>
		<hr class="my-3">
		<div>
			<h3>컬랩스</h3>
			<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseEx" role="button">
				컬랩스
			</a>
			<br>
			<div class="collapse" id="collapseEx">
				<div class="card card-body">
					예들아 힘내자
				</div>
			</div>
		</div>
		<hr class="my-3">
		<div>
			<h3>모달</h3>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#testModal">
				모달 열기
			</button>

			<div class="modal fade" id="testModal" tabindex="-1" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">이낙연 테마주 망했다</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							스바 삼부토건 내가 팔려고 했을때 팔았어야 했어.. -10% 말이 되냐...
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr class="my-3">
		<div>
			<h3>스피너</h3>
			<br>
			<div class="spinner-border" role="status">
				<span class="visually-hidden">Loading...</span>
			</div>
		</div>
		<hr class="my-3">
		<div>
			<h1>토스트 메시지</h1>
			<button type="button" class="btn btn-primary" id="liveToastBtn">알림용 토스트 사용하기</button>
			
			<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
				<div id="liveToast" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
					<div class="toast-header bg-secondary text-light">
						<i class="primary fas fa-exclamation-circle"></i>&nbsp;
						<strong class="me-auto">예약이 들어왔습니다.</strong>
						<button type="button" class="btn-close text-light" data-bs-dismiss="toast" aria-label="Close"></button>
					</div>
					<div class="toast-body">
						[예방접종] 이거성 - 젤리 (010-1234-5678)
					</div>
				</div>
			</div>
		</div>
		<hr class="my-3">
		<br>
	</div>
	
	<div>
	<form method="post" action="./test/upload.do" enctype="multipart/form-data">
         <label>파일:</label>
         <input type="file" name="file1">
         <input type="submit" value="upload">
  	</form>
	</div>
	<a href="./payment/payList.do">결제내역</a>
	<a href="./payment/payment.do">결제</a>

	<script>
		var toastTrigger = document.getElementById('liveToastBtn')
		var toastLiveExample = document.getElementById('liveToast')
		if (toastTrigger) {
			toastTrigger.addEventListener('click', function () {
				var toast = new bootstrap.Toast(toastLiveExample, {autohide:false})
				toast.show()
			})
		}
	</script>
<%@ include file="/footer.jsp" %>

