<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<style type="text/css">
a{
 	text-decoration: none;
 	color: black;
}
</style>

${lists}
${slists}
  <script src="${path}/js/answerboard.js"></script>
<h1>문의 게시판</h1>
<form action="./searchName.do" method="post">
<select id="searchOption" name="searchOption" id="searchOption">
	<option value="name">이름</option>
</select>		
<input type="text" id="answerboard_name" name="answerboard_name">
<input type="submit" id="searchMyList" name="searchMyList" value="검색해볼래?">
</form>
<div class="card my-1">
   <div class="card-body">
      <div class="row">
          <div class="col-2">
            <h5 class="card-title"> 제목</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 작성자</h5>
          </div>
          <div class="col-4">
             <h5 class="card-title"> 등록일자</h5>
          </div>
             <div class="col-2">
             <h5 class="card-title"> 처리</h5>
          </div>
      </div>
   </div>
</div>
<c:if test="${slists eq null}">
<c:forEach var="list" items="${lists}" varStatus="vs">
	<div class="card my-1">
		<div class="card-body">
			<div class="row">
				<div class="col-2">
					<p class="card-text">${list.title}</p>
				</div>
				<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
				<div class="col-2">
				<c:if test="${sessionScope.member.name eq list.answerboard_name}"><!-- 어드민과 회원의 상세 조회 -->
						<a href="./selUserDetail.do?seq=${list.seq}">${list.answerboard_name}</a>
				</c:if>
				<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN' || list.password != null}"><!-- 어드민과 비회원 상세 조회 -->
						<p class="card-text">${list.answerboard_name}</p>
				</c:if>
				</div>
				</c:if>
	
				<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
				<div class="col-2">
				<c:if test="${ list.password eq null}"><!-- 어드민과 회원의 상세 조회 -->
						<a href="./selUserDetail.do?seq=${list.seq}">${list.answerboard_name}</a>
				</c:if>
				<c:if test="${list.password != null}"><!-- 어드민과 비회원 상세 조회 -->
						<a href="./selNonUserDetail.do?seq=${list.seq}">${list.answerboard_name}</a>
				</c:if>
				</div>
				</c:if>
				
				<c:if test="${sessionScope.member eq null }">
				<div class="col-2">
				<c:if test="${ list.password eq null}"><!-- 어드민과 회원의 상세 조회 -->
					<p class="card-text">${list.answerboard_name}</p>
				</c:if>
				<c:if test="${list.password != null}"><!-- 어드민과 비회원 상세 조회 -->
						<a href="javascript:checkNonUser(${list.seq});">${list.answerboard_name}</a>
				</c:if>
				</div>
				</c:if>
				<div class="col-4">
					<p class="card-text"><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></p>
				</div>
				<div class="col-2">
				<c:if test="${list.replystatus eq 'Y'}">
					<p class="card-text">답변 완료</p>
				</c:if>
				<c:if test="${list.replystatus eq 'N'}">
					<p class="card-text">미답변</p>
				</c:if>		
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</c:if>
<c:if test="${lists eq null}">
<c:forEach var="list" items="${slists}" varStatus="vs">
	<div class="card my-1">
		<div class="card-body">
			<div class="row">
				<div class="col-2">
					<p class="card-text">${list.title}</p>
				</div>
				<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
				<div class="col-2">
				<c:if test="${sessionScope.member.name eq list.answerboard_name}"><!-- 어드민과 회원의 상세 조회 -->
						<a href="./selUserDetail.do?seq=${list.seq}">${list.answerboard_name}</a>
				</c:if>
				<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN' || list.password != null}"><!-- 어드민과 비회원 상세 조회 -->
						<p class="card-text">${list.answerboard_name}</p>
				</c:if>
				</div>
				</c:if>
	
				<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
				<div class="col-2">
				<c:if test="${ list.password eq null}"><!-- 어드민과 회원의 상세 조회 -->
						<a href="./selUserDetail.do?seq=${list.seq}">${list.answerboard_name}</a>
				</c:if>
				<c:if test="${list.password != null}"><!-- 어드민과 비회원 상세 조회 -->
						<a href="./selNonUserDetail.do?seq=${list.seq}">${list.answerboard_name}</a>
				</c:if>
				</div>
				</c:if>
				
				<c:if test="${sessionScope.member eq null }">
				<div class="col-2">
				<c:if test="${ list.password eq null}"><!-- 어드민과 회원의 상세 조회 -->
					<p class="card-text">${list.answerboard_name}</p>
				</c:if>
				<c:if test="${list.password != null}"><!-- 어드민과 비회원 상세 조회 -->
						<a href="javascript:checkNonUser(${list.seq});">${list.answerboard_name}</a>
				</c:if>
				</div>
				</c:if>
				<div class="col-4">
					<p class="card-text"><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></p>
				</div>
				<div class="col-2">
				<c:if test="${list.replystatus eq 'Y'}">
					<p class="card-text">답변 완료</p>
				</c:if>
				<c:if test="${list.replystatus eq 'N'}">
					<p class="card-text">미답변</p>
				</c:if>		
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</c:if>
<c:if test="${slists == null}">
<ul class="mt-3 pagination justify-content-center">
			<c:if test="${page.startPage > page.countPage}">
				<li class="page-item">
					<a class="page-link" href="./selAllBoard.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>	
				</li>
				</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./selAllBoard.do?page=${num}">${num}</a>	
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./selAllBoard.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>		
</ul>
</c:if>
<c:if test="${slists != null}">
<ul class="mt-3 pagination justify-content-center">
			<c:if test="${page.startPage > page.countPage}">
				<li class="page-item">
					<a class="page-link" href="./searchName.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>	
				</li>
				</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./searchName.do?page=${num}">${num}</a>	
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./searchName.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>		
</ul>
</c:if>
<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
<a class="btn btn-secondary" href="./moveInsert.do">회원 글작성</a>
</c:if>
<c:if test="${sessionScope.member.usertype != 'ROLE_USER' && sessionScope.member.usertype != 'ROLE_ADMIN'}">
<a class="btn btn-secondary" href="./moveInsert.do">비회원 글작성</a>
</c:if>
<div class="modal fade" id="chkModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="chkModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="chkModalLabel">비회원 정보 확인</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body" id="modalContent">
				 <label for="email">이메일:</label>
					<input class="form-control" type="text" id="email" name="email" placeholder=" 작성시 이메일을 입력해주세요">
				<label for="password">비밀번호:</label>	
					<input class="form-control" type="password" id="password" name="password" placeholder="작성시 비밀번호를 입력해주세요">					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" onclick="checkInfo()">확인</button>
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="resetInfo()">닫기</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">

function checkNonUser(seq){
	
	console.log("확인 seq"+seq);
	var myModal = new bootstrap.Modal(document.getElementById('chkModal'), {
		keyboard: false
	})
	myModal.show();
	
  	var content= document.getElementById("modalContent")	
	
	var emt = document.createElement("div");
	emt.innerHTML = "<input type='hidden' name='seq' value='"+seq+"'>";
	content.append(emt);


}

var seq;
function checkInfo(){
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var seq = document.getElementsByName("seq")[0].value;
	
	console.log(email);
	console.log(password);
	console.log(seq);
	
	$.ajax({
		type:"get",
		url:"./checkInfo.do?email="+email+"&password="+password+"&seq="+seq,
// 		dataType:"text",
		success:function(msg){
			console.log(msg);
			if(msg == 'true'){
				location.href="./selNonUserDetail.do?seq="+seq;
			} else{
				alert("입력하신 정보가 일치하지 않습니다.")
			}
		},
		error:function(){
			alert("잘못된 요청입니다.")
		}
	})
}
</script>
<%@include file="/footer.jsp" %>