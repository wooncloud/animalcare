<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
  <script src="${path}/js/answerboard.js"></script>
${dto}
<form>
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">문의하기 </h5>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이름</h6>
			</div>
		    <div class="col-3">
		    	${dto.answerboard_name}
			</div>
<%-- 				<c:if test="${sessionScope.member eq null}"> --%>
<!-- 					<div class="col-3"> -->
<!-- 				<h6 class="card-subtitle mb-2 text-muted">비밀번호</h6> -->
<!-- 			</div> -->
<!-- 		    <div class="col-3"> -->
<%-- 		    	${dto.password} --%>
<!-- 			</div> -->
<%-- 			</c:if> --%>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이메일</h6>
			</div>
		    <div class="col-9">
		    ${dto.email}
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">제목</h6>
			</div>
		    <div class="col-9">
 				${dto.title}
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">내용</h6>
			</div>
		    <div class="col-9">
				${dto.content}
			</div>
		</div>
	</div>
</div>
<div id="reply"></div>
</form>
<c:if test="${dto.password != null && sessionScope.member.usertype != 'ROLE_ADMIN'}">
<button class="btn btn-primary" onclick="location.href='./modifyNonUserForm.do?seq=${dto.seq}' ">수정</button>
<button class= "btn btn-secondary" onclick="deleteBoard(${dto.seq})">삭제</button>
<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>
</c:if>
<c:if test="${dto.password eq null && sessionScope.member.usertype != 'ROLE_ADMIN'}">
<button class="btn btn-primary" onclick="location.href='./modifyUserForm.do?seq=${dto.seq}' ">수정</button>
<button class= "btn btn-secondary" onclick="deleteBoard(${dto.seq})">삭제</button>
<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>
</c:if>
<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
<button class="btn btn-outline-primary btn-lg"  id="insertBtn" onclick="insertReply()">답글 작성</button>
</c:if>

<%@include file="/footer.jsp" %>