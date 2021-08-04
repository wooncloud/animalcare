<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script type="text/javascript" src="${path}/js/answerboard.js" ></script>
<form action="./modifyUserBoard.do" method="post" onclick="return modifyBoardChk(this)">
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">문의수정 </h5>
		<div class="row my-1">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이름</h6>
			</div>
		    <div class="col-3">
		    	${dto.answerboard_name}
			</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이메일</h6>
			</div>
		<div class="col-9">
		    ${dto.email}
			</div>
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
			<div id="editor"></div>
	<input type="hidden" name="content"  id="content">
	<input type="hidden" name="seq" id="seq" value="${dto.seq}">
	</div>
</div>

<!-- <button class="btn btn-primary" onclick="modifyBoard()" id="modify">수정</button> -->
<input  type="submit"  class="btn btn-outline-primary btn-lg"  value="수정완료">
<input  type="reset" class="btn btn-outline-secondary btn-lg"  value="초기화">
</form>
<script type="text/javascript">
window.onload = function(){
	let modifyContents = `${dto.content}`;
	modifyBoardContent.init(modifyContents);
}
</script>
<%@include file="/footer.jsp" %>