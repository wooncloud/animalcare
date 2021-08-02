<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script type="text/javascript" src="${path}/js/answerboard.js" ></script>
<input type="button" class="btn btn-outline-secondary btn-lg" onclick="javascript:history.back(-1);" value="목록"/>
<form action="./insertUserBoard.do" method="post" onsubmit="return insertBoardChk(this)">
${sessionScope.member.email}
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">문의하기 </h5>
		<div class="row my-1">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이름</h6>
			</div>
			<c:if test="${sessionScope.member != null}">
		    <div class="col-3">
				<p class="card-text">${sessionScope.member.name}</p>
				<input type="hidden"  class="form-control" name="name" id="name" value="${sessionScope.member.name}" >
			</div>
			</c:if>
			<c:if test="${sessionScope.member eq null}">
			<div class="col-3">
				<input type="text"  class="form-control" name="name" id="name" >
			</div>
			</c:if>
			<c:if test="${sessionScope.member eq null}">
					<div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">비밀번호</h6>
			</div>
		    <div class="col-3">
		    	<input type="password"  class="form-control" name="password" id="password" >
			</div>
			</c:if>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이메일</h6>
			</div>
			<c:if test="${sessionScope.member != null}">
		    <div class="col-9">
		    		<p class="card-text">${sessionScope.member.email}</p>
				<input type="hidden" name="email" id="email" value="${sessionScope.member.email}">
			</div>
			</c:if>
			<c:if test="${sessionScope.member eq null}">
		    <div class="col-9">
				<input type="text" class="form-control" name="email" id="email">
			</div> 
			</c:if>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">제목</h6>
			</div>
		    <div class="col-9">
				<input type="text" class="form-control" name="title" id="title"  placeholder="제목을 입력해주세요">
			</div>
		</div>
				<div id="editor"></div>
				<input type="hidden" name="content" id="content">
	</div>
</div>
	
<input  type="submit"  class="btn btn-outline-primary btn-lg"  value="작성하기">
<input  type="reset" class="btn btn-outline-secondary btn-lg"  value="초기화">
</form>


<script type="text/javascript">
window.onload = function(){
	insertBoard.init();
}
</script>
<%@include file="/footer.jsp" %>