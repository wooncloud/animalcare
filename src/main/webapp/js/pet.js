/**
 * 
 */

function delPet(name){
	console.log(name);
	var chk= confirm("애완동물의 정보를 삭제하시겠습니까?");
	if(chk){
		return location.href='./deletePet.do?name='+name
	}else{
		alert("취소");
	}
}
