/**
 * 
 */

// 지역선택 체크박스 
function chkBox(chk) {
	var chkValue = chk.value;
	console.log(chkValue);
	
	let inputBox = document.getElementById(chkValue);
	console.log(inputBox);
	if(chk.checked){
		//체크하면 이름과 값을 활성화
		inputBox.setAttribute("name","hiddenLoc");
		inputBox.setAttribute("value","서울 "+chkValue+" ");		
	}else{
		//체크 해제 하면 이름과 값을 비활성화
		inputBox.removeAttribute("name");	
		inputBox.removeAttribute("value");	
	}
	
}

//병원 찾기 페이지 선택 찾기 
//function forSearch(){
//	location.href = "./searchHospital.do";
	
//	let url = "./searchHospital.do?";
//	let enURI =  encodeURI(url);
//	let enComponentURI = encodeURIComponent(url);
//	
//	location.href = url+"emergency=Y";
//	
//}

// 셀렉트 박스에서 진료항목 온체인지로 항목 선택 및 선택 취소
function selectType() {

	// 진료 항목 코드로 불러오기
	var selectPetCode = document.getElementById("selectPetType").value;
//	console.log(selectPetCode);
	// 진료 항목 동물명으로 불러오기
	let selectedPetType = $("#selectPetType > option:selected").text();
//	console.log(selectedPetType);

	// 셀렉트 선택시 항목 엘리먼트 준비
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

	// 셀렉트에서 선택된 항목의 값
	let selectValue = document.getElementById('selectPetType').value;
	// 선택된 항목들의 텍스트
	let choiceValue = document.getElementById('choice').textContent;
	
	// 선택항목의 코드를 숨김 값 으로
	let inputBox = document.createElement('input');
	inputBox.setAttribute("type","hidden");
	inputBox.setAttribute("value",selectPetCode);
	inputBox.setAttribute("name","hiddenValue");
	
	// 선택 또는 이미선택된 항목을 선택하지 않는다면 선택항목 생성 
	if (selectValue != "selectOne" && choiceValue.indexOf(selectedPetType+'X')==-1) {
		petType.appendChild(petTypeBox);
		petType.appendChild(inputBox);
		petTypeBox.appendChild(petTypeText);
		petTypeText.appendChild(text);
		petTypeBox.appendChild(petTypeCancle);
		petTypeCancle.appendChild(text2);
		document.getElementById('choice').appendChild(petType);
	}

	// x클릭시 항목 삭제
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

// 다른 라디오버튼 선택시 자동 선택 해제(선택안함 값을 null하려고 만듬 )
function radioMaster(){
 let have =	document.getElementById('have');
 let none =	document.getElementById('none');
 let noChoice =	document.getElementById('noChoice');
 if(have.onclick = function(){
	 noChoice.checked = false;
 });
 if(none.onclick = function(){
	 noChoice.checked = false;
 });
 if(noChoice.onclick = function(){
	 have.checked = false;
	 none.checked = false;
 }); 
}

//병원 찾기 페이지 찾기 선택 취소 
function beforeSearch(){
	location.href = "./searchHospitalPage.do";
}

//
function nullCanNot(){
	Swal.fire({
		icon: 'warning',
		title: '로그인 후 이용 가능한 서비스 입니다.',
		confirmButtonColor: '#3399FF',
	})
}

//병원 삭제 버튼
function deleteHospital(seq) {
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

$(document).on("keyup", ".phoneNumber", function() {
		$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
	});


//미리보기 화면 값 확인
function modalContent(){
	//병원명
	document.getElementById('nameModal').innerText = document.getElementById('name').value;
	//진료항목
	var pettypeChk = document.getElementById('choice').innerText;
	var replacePettype = pettypeChk.replace(/X/gi," ");
	document.getElementById('pettypesModal').innerText = replacePettype;
	//전화번호
	document.getElementById('telModal').innerText = document.getElementById('tel').value;
	//응급실 여부
	document.getElementById('emergencyModal').innerText = document.querySelector('input[name="emergencyRadio"]:checked').value; 
	//주소
	document.getElementById('addressModal').innerText = document.getElementById('address1').value+" "+document.getElementById('address2').value;
	//운영시간
	document.getElementById('opentimeModal').innerText = document.getElementById('opentime').value;
	//내용
	let hospitalContent = insertHospital.editor.getHTML();
	document.getElementById('contentModal').innerText = hospitalContent;
}

//클릭시 이전 페이지로 이동
function goBack(){
	Swal.fire({
		icon: 'question',
		title:'작업을 취소 하시겠습니까?',
		text: '입력하신 데이터가 사라집니다.',
		confirmButtonText: '확인',	
		showCancelButton: true,
		cancelButtonColor: 'gray',
		cancelButtonText: '취소',
	}).then((result) => {
		if(result.isConfirmed){
			window.history.back();
		}
	})
	
}

//병원 정보 등록 유효값 확인
function insertHospitalChk(form){
	
	// 진료항목 선택된 항목이 있는지 판단용
	let choiceValue = document.getElementById('choice').textContent;
	
	if(form.name.value==""){
		Swal.fire("알림", "병원명을 입력하세요.", "warning");
		form.name.focus();
		return false;
	}else if(form.tel.value==""){
		Swal.fire("알림", "전화번호를 입력하세요.", "warning");
		form.tel.focus();
		return false;
	}else if(form.address1.value==""){
		Swal.fire("알림", "주소를 입력하세요.", "warning");
		form.address1.focus();
		return false;
	}else if(form.address2.value==""){
		Swal.fire("알림", "상세주소를 입력하세요.", "warning");
		form.address2.focus();
		return false;
	}else if(choiceValue==""){
		Swal.fire("알림", "진료항목을 선택하세요.", "warning");
		form.selectPetType.focus();
		return false;
	}else if(form.opentime.value==""){
		Swal.fire("알림", "운영시간을 입력하세요.", "warning");
		form.opentime.focus();
		return false;
	}					
	
	let hospitalContent = insertHospital.editor.getHTML();
	document.getElementById("insertContent").value = hospitalContent;
	
}

//병원정보 수정페이지 이동
function modifyHospitalPage(seq) {
	location.href = "./modifyHospitalPage.do?seq=" + seq;
}

//병원정보 수정 기존 진료항목 띄워놓기
let hiddenPetTypes = {
		init: function(hiddenPetType, petCode){
			// hiddenPetType의 값이 null이 아니라면 , 로 쪼갠다
			if(hiddenPetType.value!=null){
				let splitPetType = hiddenPetType.value.split(',');

				// 쪼갠것의 길이만큼 for문을 돌려 해당 진료항목 박스를 생성
				for (let i = 0; i < splitPetType.length; i++) {
					//splitPetType[i]
					
					// 진료항목의 코드를 동물 이름으로 변환
					changePetType = getCodeName(splitPetType[i], petCode);
					
					var petType = document.createElement('div');
					petType.className = 'm-2 text-center';
					var petTypeBox = document.createElement('div');
					petTypeBox.className = 'petTypeBox row ms-1 border p-1 text-white rounded-3';
					var petTypeText = document.createElement('div');
					petTypeText.className = 'col text-center';
					var petTypeCancle = document.createElement('div');
					petTypeCancle.className = 'petTypeCancle col-2 bg-white text-dark text-center p-0 rounded-3';
					var text = document.createTextNode(changePetType);
					var text2 = document.createTextNode("X");
					
					// 선택항목의 코드를 숨김 값 으로
					let inputBox = document.createElement('input');
					inputBox.setAttribute("type","hidden");
					inputBox.setAttribute("value",splitPetType[i]);
					inputBox.setAttribute("name","hiddenValue");
					
					// 항목 엘리먼트 생성
					petType.appendChild(petTypeBox);
					petType.appendChild(inputBox);
					petTypeBox.appendChild(petTypeText);
					petTypeText.appendChild(text);
					petTypeBox.appendChild(petTypeCancle);
					petTypeCancle.appendChild(text2);
					document.getElementById('choice').appendChild(petType);
					
					petType.onclick = function () {
						this.parentNode.removeChild(this);
					}	
					
				}
			}
		}
}

//병원정보 수정 에디터 설정
let modifyHospitalContents = {
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

//병원 정보 수정 유효값 확인
function modifyHospitalChk(form){
	
	// 진료항목 선택된 항목이 있는지 판단용
	let choiceValue = document.getElementById('choice').textContent;
	
	if(form.name.value==""){
		Swal.fire("알림", "병원명을 입력하세요.", "warning");
		form.name.focus();
		return false;
	}else if(form.tel.value==""){
		Swal.fire("알림", "전화번호를 입력하세요.", "warning");
		form.tel.focus();
		return false;
	}else if(form.address1.value==""){
		Swal.fire("알림", "주소를 입력하세요.", "warning");
		form.address1.focus();
		return false;
	}else if(form.address2.value==""){
		Swal.fire("알림", "상세주소를 입력하세요.", "warning");
		form.address2.focus();
		return false;
	}else if(choiceValue==""){
		Swal.fire("알림", "진료항목을 선택하세요.", "warning");
		form.selectPetType.focus();
		return false;
	}else if(form.opentime.value==""){
		Swal.fire("알림", "운영시간을 입력하세요.", "warning");
		form.opentime.focus();
		return false;
	}					
	
	//에디터에 작성된 글을 modifyContent 에 옮긴 후 저장
	let hospitalModifyContents = modifyHospitalContents.editor.getHTML();
	document.getElementById("modifyContents").value = hospitalModifyContents;
	
}

//병원일정 등록 페이지 이동
function insertSchedulePage(){
	location.href = "./insertSchedulePage.do";
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

// 병원 일정등록 유효값 확인
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

//병원일정 상세보기 페이지 이동
function detailSchedulePage(){
	location.href = "./detailSchedulePage.do";
}
//병원일정 수정하기 페이지 이동
function modifySchedulePage(seq){
	location.href = "./modifySchedulePage.do?seq="+seq;
}

//병원일정 수정 에디터 설정
let modifyScheduleContents = {
		editor: null,
		init: function (modifyScheduleContent) {
			
			// editor
			this.editor = new toastui.Editor({
				el: document.querySelector('#editor'),
				previewStyle: 'vertical',
				initialEditType: "wysiwyg",
				height: '500px',
				previewHighlight: true,
				language: 'ko',
				initialValue: modifyScheduleContent
			});			
		}
}

//병원 일정수정 유효값 확인
function modifyScheduleChk(form){
	
	if(form.scheduleName.value==""){
		Swal.fire("알림", "일정 제목을 입력하세요.", "warning");
		form.scheduleName.focus();
		return false;
	}else if(form.scheduleDate.value==""){
		Swal.fire("알림", "일정 일자를 선택하세요.", "warning");
		form.scheduleDate.focus();
		return false;
	}	
	
	let modifyScheduleContent = modifyScheduleContents.editor.getHTML();
	document.getElementById("modifyScheduleContent").value = modifyScheduleContent;
}

//병원일정 삭제하기
function deleteSchedule(seq){
	Swal.fire({
		title: '병원 일정을 삭제 하시겠습니까?',
		showCancelButton: true,
		focusConfirm: false,
		confirmButtonText: '삭제',
		confirmButtonColor: '#E74C3C',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
			location.href = "./deleteHospitalSchedule.do?seq="+seq;
		}
	});
	
	
}

//병원 진료기록 리스트 페이지 이동
function medicalListPage(){
	location.href = "./recodeList.do";
}

//병원 진료기록 입력 페이지 이동
function insertRecodePage(seq){
	location.href = "../hospital/insertRecodePage.do?seq="+seq;
	
}

//병원 진료기록 수정 에디터 설정
//진료내용
let modifyTreatmentContents = {
		editor: null,
		init: function (modifyTreatmentContent) {
			
			// editor
			this.editor = new toastui.Editor({
				el: document.querySelector('#treatmentEditor'),
				previewStyle: 'vertical',
				initialEditType: "wysiwyg",
				height: '300px',
				previewHighlight: true,
				language: 'ko',
				initialValue: modifyTreatmentContent
			});			
		}
}
//처방내용
let modifyPrescriptionContents = {
		editor: null,
		init: function (modifyPrescriptionContent) {
			
			// editor
			this.editor = new toastui.Editor({
				el: document.querySelector('#prescriptionEditor'),
				previewStyle: 'vertical',
				initialEditType: "wysiwyg",
				height: '300px',
				previewHighlight: true,
				language: 'ko',
				initialValue: modifyPrescriptionContent
			});			
		}
}

//병원 진료기록 수정 에디터 내용 DB전송하려고 담아줌
function modifyRecodeChk(){
	
	//진료내용
	let modifyTreatmentContent = modifyTreatmentContents.editor.getHTML();
	document.getElementById("modifyTreatmentContent").value = modifyTreatmentContent;
	//처방내용
	let modifyPrescriptionContent = modifyPrescriptionContents.editor.getHTML();
	document.getElementById("modifyPrescriptionContent").value = modifyPrescriptionContent;
}

//병원 진료기록 입력 에디터 설정
//진료내용
let insertTreatmentContents = {
		editor: null,
		init: function () {
			
			// editor
			this.editor = new toastui.Editor({
				el: document.querySelector('#treatmentEditor'),
				previewStyle: 'vertical',
				initialEditType: "wysiwyg",
				height: '300px',
				previewHighlight: true,
				language: 'ko',
				initialValue: ""
			});			
		}
}
//처방내용
let insertPrescriptionContents = {
		editor: null,
		init: function () {
			
			// editor
			this.editor = new toastui.Editor({
				el: document.querySelector('#prescriptionEditor'),
				previewStyle: 'vertical',
				initialEditType: "wysiwyg",
				height: '300px',
				previewHighlight: true,
				language: 'ko',
				initialValue: ""
			});			
		}
}

//병원 진료기록 입력 에디터 내용 DB전송하려고 담아줌
function insertRecodeChk(){
	
	//진료내용
	let insertTreatmentContent = insertTreatmentContents.editor.getHTML();
	document.getElementById("insertTreatmentContent").value = insertTreatmentContent;
	//처방내용
	let insertPrescriptionContent = insertPrescriptionContents.editor.getHTML();
	document.getElementById("insertPrescriptionContent").value = insertPrescriptionContent;
}

// 진료상세내역 PDF 로 출력
function createPDF() {
	let petName = document.getElementById('petName').innerText;
	
	  //pdf_wrap을 canvas객체로 변환
	  html2canvas($('#pdf_wrap')[0]).then(function(canvas) {
		  
		// 캔버스를 이미지로 변환
		    var imgData = canvas.toDataURL('image/png');
			     
		    var imgWidth = 190; // 이미지 가로 길이(mm) / A4 기준 210mm
		    var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
		    var imgHeight = canvas.height * imgWidth / canvas.width;
		    var heightLeft = imgHeight;
		    var margin = 5; // 출력 페이지 여백설정
		    var doc = new jsPDF('p', 'mm');
		    var position = 0;
		       
		    // 첫 페이지 출력
		    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
		    heightLeft -= pageHeight;
		         
		    // 한 페이지 이상일 경우 루프 돌면서 출력
		    while (heightLeft >= 20) {
		        position = heightLeft - imgHeight;
		        doc.addPage();
		        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
		        heightLeft -= pageHeight;
		    }
	    doc.save(petName+'_진료기록.pdf'); // 진료 동물 이름 으로 pdf저장
	  });
};
