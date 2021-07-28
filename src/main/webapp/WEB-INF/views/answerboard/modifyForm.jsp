<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
  <script src="${path}/js/answerboard.js"></script>
${dto}
<form action="./modifyUserBoard" method="POST">
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">문의하기 </h5>
		<div class="row my-1">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이름</h6>
			</div>
		    <div class="col-3">
		    	<input type="text"  class="form-control" name="name" id="name" value="${dto.name}" readonly="readonly">
			</div>
			<div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">전화번호</h6>
			</div>
		    <div class="col-3">
		    	<input type="text"  class="form-control" name="phone" id="phone" value="${dto.phone}" readonly="readonly">
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이메일</h6>
			</div>
		    <div class="col-9">
		    <input type="text"  class="form-control" name="email" id="email" value="${dto.email}" readonly="readonly">
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">제목</h6>
			</div>
		    <div class="col-9">
 				<input type="text" class="form-control" name="title" id="title" value="${dto.title}" readonly="readonly">
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">내용</h6>
			</div>
		    <div class="col-9">
				<input type="text" class="form-control" name="content" id="content" value="${dto.content}" >
			</div>
		</div>
	</div>
</div>
</form>

<button class="btn btn-primary" onclick="modifyBoard()" id="modify">수정</button>
<button class="btn btn-info" onclick="confirmModify()" style="display: none" id="confirm">수정제출</button>
<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>

<%@include file="/footer.jsp" %>