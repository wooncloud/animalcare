function deleterBoard(seq){
	
	Swal.fire({
		title: ' 글을 삭제 하시겠습니까?',
		showCancelButton: true,
		focusConfirm: false,
		confirmButtonText: '삭제',
		confirmButtonColor: '#E74C3C',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
			location.href="./deleterBoard.do?seq="+seq;
		}
	});

}

function insertBoardChk(form){
	
	let insertContent = insertBoard.editor.getHTML();
		document.getElementById("content").value = insertContent;
		
	
	
	if(form.title.value==""){
		Swal.fire("알림", "제목을 입력하세요.", "warning");
		form.title.focus();
		return false;
	}else if(form.content.value==""){
		Swal.fire("알림", "내용을 입력하세요.", "warning");
		return false;
	}
		

}

function modifyBoardChk(form){
	
	let modifyBoardContents = modifyBoardContent.editor.getHTML();
	document.getElementById("content").value = modifyBoardContents;
	
		 if(form.modifyContent.value==""){
		Swal.fire("알림", "내용을 입력하세요.", "warning");
		return false;
	}
}

function insertReplyChk(form){
	
	let insertContent = replyBoard.editor.getHTML();
		document.getElementById("reply").value = insertContent;
		
	document.getElementById("replyBtn").style.display="none";
	
	if(form.reply.value==""){
		Swal.fire("알림", "답변을 입력하세요.", "warning");
		form.reply.focus();
		return false;
	}
		

}

let insertBoard = {
	editor: null,
	init: function () {

		// editor
		this.editor = new toastui.Editor({
			el: document.querySelector('#editor'),
			previewStyle: 'vertical',
			initialEditType: "wysiwyg",
			height: '500px',
			previewHighlight: true,
			language: 'ko',
			initialValue: ""
		});

	}
}
	


let modifyBoardContent = {
		editor: null,
		init: function (modifyContents) {
			// editor
			this.editor = new toastui.Editor({
				el: document.querySelector('#editor'),
				previewStyle: 'vertical',
				initialEditType: "wysiwyg",
				height: '500px',
				previewHighlight: true,
				language: 'ko',
				initialValue: modifyContents
			});			
		}
}


function clickReply(){
	
	
	var replyBtn = document.getElementById("replyBtn");
	replyBtn.style.display='none';
	
	var form = document.getElementById("replyForm");
	form.style.display='inline';
	
	var insterBtn = document.getElementById("insertBtn");
	insterBtn.style.display='inline';
	
}

function resetInfo(){
	
	document.getElementById("email").value = '';
	document.getElementById("password").value = '';
	
}

let replyBoard = {
	editor: null,
	init: function () {

		// editor
		this.editor = new toastui.Editor({
			el: document.querySelector('#editor'),
			previewStyle: 'vertical',
			initialEditType: "wysiwyg",
			height: '500px',
			previewHighlight: true,
			language: 'ko',
			initialValue: ""
		});

	}
}

function searchList(){
	var opt = document.getElementById("searchOption");
	console.log(opt);
	var frm = document.forms[0];
	
	if(opt == "title"){
		frm.action="./searchTitle.do";
		frm.submit();
	}else{
		frm.action="./searchName.do";
		frm.submit();		
	}
	
}
