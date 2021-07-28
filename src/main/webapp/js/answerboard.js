function modifyBoard(){
	
	var content = document.getElementById("content");
	var frm = document.forms[0];
	var modify = document.getElementById("modify");
	var confirm = document.getElementById("confirm");
	
	
	content.removeAttribute("readonly");
	content.value='';
	
	confirm.removeAttribute("style");
	modify.setAttribute("style","display:none");
	
	if(content != ''){
		var con = confirm("수정하시겠습니까?");
			if(con){
				frm.submit();
			}		
	}
}


function deleteBoard(seq){
	
	 var con = confirm("삭제 하시겠습니까?");
	
	if(con){
		location.href="./deleteUserBoard.do?seq="+seq;
	}
}
