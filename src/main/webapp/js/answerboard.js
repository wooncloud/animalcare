function deleteBoard(seq){
	
	Swal.fire({
		title: ' 글을 삭제 하시겠습니까?',
		showCancelButton: true,
		focusConfirm: false,
		confirmButtonText: '삭제',
		confirmButtonColor: '#E74C3C',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
			location.href="./deleteUserBoard.do?seq="+seq;
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


function insertReply(){
	
	var btn = document.getElementById("insertBtn");
	
	btn.style.display='none';
	
	var reply = document.getElementById("reply");
	
	 var div1 = document.createElement("div");
	 var div2 = document.createElement("div");
	 var div3 = document.createElement("div");
	 var div4 = document.createElement("div");
	 var div5 = document.createElement("div");
	div1.setAttribute("class", "card my-1");
	div2.setAttribute("class","card-body");
	div3.setAttribute("class","row")
	reply.append(div);
	
}




