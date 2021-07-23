/**
 * 
 */

//선택한 체크박스의 값 가져오기
function chkBox(chk) {
	var chkValue = chk.value;
	console.log(chkValue);
}


// 셀렉트 박스에서 진료항목 온체인지로 항목 선택 및 선택 취소
function selectType() {

	// 진료 항목 코드로 불러오기
	var selectPetCode = document.getElementById("selectPetType").value;
	console.log(selectPetCode);
	// 진료 항목 동물명으로 불러오기
	let selectedPetType = $("#selectPetType > option:selected").text();
	console.log(selectedPetType);

	var petType = document.createElement('div');
	petType.className = 'm-2 text-center';
	var petTypeBox = document.createElement('div');
	petTypeBox.className = 'petTypeBox row ms-1 border p-1 text-white rounded-3';
	var petTypeText = document.createElement('div');
	petTypeText.className = 'col text-center';
	var petTypeCancle = document.createElement('div');
	petTypeCancle.className = 'petTypeCancle col-2 bg-white text-dark text-center p-0 rounded-3';
	var text = document.createTextNode(selectedPetType);
	var text2 = document.createTextNode("X");

	// 항목 추가될때 아작스 넣어야 함
	if (selectedPetType != "choice") {
		petType.appendChild(petTypeBox);
		petTypeBox.appendChild(petTypeText);
		petTypeText.appendChild(text);
		petTypeBox.appendChild(petTypeCancle);
		petTypeCancle.appendChild(text2);
		document.getElementById('choice').appendChild(petType);
	}

	// 항목 제거할때 아작스 넣어야 함
	petType.onclick = function () {
		this.parentNode.removeChild(this);
	}

}

// 타입 코드 ID => NAME 변환
let detailHospital = {
	init: function () {

		let pettypes = document.querySelectorAll("#pettypes");
		let types = pettypes[0].innerText.split(',');
		let result = "";
		for (const e of types) {
			result += getCodeName(e, petCode);
			result += " ";
		}

		pettypes[0].innerText = result;
	}
}


//병원 삭제 버튼
function deleteHospital(seq) {
	console.log(seq + "확인1");
	Swal.fire({
		title: '병원 정보를 삭제 하시겠습니까?',
		showCancelButton: true,
		focusConfirm: false,
		confirmButtonText: '삭제',
		confirmButtonColor: '#E74C3C',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
			location.href = "./deleteHospital.do?seq=" + seq;
		}
	});

}

// 병원정보 수정페이지 이동
function modifyHospital(seq) {
	location.href = "./modifyHospital.do?seq=" + seq;
}

//카카오 주소찾기 연결
function findAdress() {
	new daum.Postcode({
		oncomplete: function (data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("extraAddress").value = extraAddr;

			} else {
				document.getElementById("extraAddress").value = '';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('postcode').value = data.zonecode;
			document.getElementById("address1").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("address2").focus();
		}
	}).open();
}

//병원정보 입력 에디터 설정
let insertHospital = {
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

//병원 일정 입력 에디터 설정
let addSchedule = {
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

//병원일정 등록 페이지 이동
function insertSchedule(){
	location.href = "./insertHospitalSchedule.do";
}

function insertScheduleChk(form){
	
	if(form.scheduleName.value==""){
		Swal.fire("알림", "일정 제목을 입력하세요.", "warning");
		form.scheduleName.focus();
		return false;
	}else if(form.scheduleDate.value==""){
		Swal.fire("알림", "일정 일자를 선택하세요.", "warning");
		form.scheduleDate.focus();
		return false;
	}	
	
	let scheduleContent = addSchedule.editor.getHTML();
	document.getElementById("scheduleContent").value = scheduleContent;
}







