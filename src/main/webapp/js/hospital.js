/**
 * 
 */

//선택한 체크박스의 값 가져오기
function chkBox(chk){
	var chkValue = chk.value;
	console.log(chkValue);
}


// 셀렉트 박스에서 진료항목 온체인지로 항목 선택 및 선택 취소
function selectPetType(){
	
	// 진료 항목 코드로 불러오기
	var selectPetCode = document.getElementById("selectPetType").value;
	console.log(selectPetCode);
	// 진료 항목 동물명으로 불러오기
	var selectPetType = $("#selectPetType > option:selected").attr("value2");
	console.log(selectPetType);
	
	var petType = document.createElement('div');
	petType.className = 'col text-center';
	var petTypeBox = document.createElement('div');
	petTypeBox.className = 'row ms-1 border w-25 p-1 bg-primary text-white ';
	var petTypeText = document.createElement('div');
	petTypeText.className = 'col text-center';
	var petTypeCancle = document.createElement('div');
	petTypeCancle.className = 'petTypeCancle col-2 bg-white text-dark text-center p-0';
	var text = document.createTextNode(selectPetType);
	var text2 = document.createTextNode("X");
	
	// 항목 추가될때 아작스 넣어야 함
	if(selectPetType!=undefined){		
		petType.appendChild(petTypeBox);
		petTypeBox.appendChild(petTypeText);
		petTypeText.appendChild(text);
		petTypeBox.appendChild(petTypeCancle);
		petTypeCancle.appendChild(text2);
		document.getElementById('choice').appendChild(petType);
	}
	
	// 항목 제거할때 아작스 넣어야 함
	petTypeBox.onclick = function(){
		this.parentNode.removeChild(this);
	}
	
}



