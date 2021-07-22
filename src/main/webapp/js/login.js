window.onload = function () {
	let pathname = window.location.pathname;

	if (pathname.indexOf('signupUserForm') != -1) {
		signupUser.init();
	}
	if (pathname.indexOf('signupSelect') != -1) {
		select.init();
	}
}

const login = {
	login: function () {
		let form = document.forms[0];
		let email = form.email;
		let pw = form.password;

		if (isEmpty(email.value)) {
			swal.alert("이메일을 입력해 주세요.", "error");
			return false;
		}
		if (isEmpty(pw.value)) {
			swal.alert("비밀번호를 입력해 주세요.", "error");
			return false;
		}

		form.submit();
	}
}

const agree = {
	allchk: function () {
		let chks = document.getElementsByClassName('agree-chk');
		if (checkboxUtil.isAllChk(chks)) {
			location.href = './signupSelect.do';
		}
		else {
			swal.alert("약관을 모두 동의하여야 합니다.");
			return false;
		}
	}
}

const select = {
	init: function () {
		let signupUser = document.getElementById('signupUser');
		let signupOper = document.getElementById('signupOper');

		signupUser.addEventListener("click", function () {
			location.href = "./signupUserForm.do";
		});

		signupOper.addEventListener("click", function () {
			location.href = "./signupOperForm.do";
		});
	}
}

const signupUser = {
	status:{
		edc: false
	},
	init: function () {
		document.getElementById('btnEmailDupl').addEventListener('click', signupUser.emailDupl);
		document.getElementById('btnEmailSendConfirm').addEventListener('click', signupUser.emailSendConfirm);
		document.getElementById('btnEmailConfirm').addEventListener('click', signupUser.emailConfirm);
		document.getElementById('btnPhoneSendConfirm').addEventListener('click', signupUser.phoneSendConfirm);
		document.getElementById('btnPhoneConfirm').addEventListener('click', signupUser.phoneConfirm);
		document.getElementById('btnAddrSearch').addEventListener('click', signupUser.addrSearch);
		document.getElementById('btnCancel').addEventListener('click', signupUser.cancel);
		document.getElementById('btnSignup').addEventListener('click', signupUser.signup);

		document.getElementById('email').addEventListener('change', function(){
			edc = false;
		});
	},
	emailDupl: function () {
		let email = document.getElementById("email").value;

		$.ajax({
			url: "./emailDupl.do",
			type: "POST",
			data: "email=" + email,
			success: function (msg) {
				if(msg == false){
					edc = true;
					swal.alert_txt("이메일 중복 검사", "사용 가능한 이메일입니다.", "success");
				}
				else{
					edc = false
					swal.alert_txt("이메일 중복 검사", "이미 있는 계정입니다.", "error");
				}
			},
			error: function () {
				edc = false
				swal.alert_txt("오류", "이메일 검사중 문제가 발생했습니다.\n관리자에게 문의하세요.", "error");
			}
		});
	},
	emailSendConfirm: function () {

	},
	emailConfirm: function () {

	},
	phoneSendConfirm: function () {

	},
	phoneConfirm: function () {

	},
	addrSearch: function () {
		new daum.Postcode({
			oncomplete: function (data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById("address1").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("address2").focus();
			}
		}).open();
	},
	cancel: function () {

	},
	signup: function () {
		let form = document.forms[0];
		let element = {
			"email": form.email,
			"password": form.password,
			"name": form.name,
			"phone": form.phone,
			"address1": form.address1,
			"address2": form.address2
		}
		let data = {
			"email": form.email.value,
			"password": form.password.value,
			"name": form.name.value,
			"phone": form.phone.value,
			"address1": form.address1.value,
			"address2": form.address2.value
		}

		// 유효성 검사(빈값, 정규표현식)
		for (const [key, value] of Object.entries(data)) {
			if (isEmpty(value)) {
				let labelTxt = eval(`element.${key}`).closest('.signup-row').querySelector('.form-label').innerText;
				swal.alert_txt("누락된 내용이 있습니다.", `${labelTxt}(이)가 없습니다.\n다시 확인해 주세요.`, "error");
				return false;
			}
			else {
				if (key == 'email' && regEx.email.test(value) == false) {
					swal.alert_txt(
						"이메일 형식 오류",
						"이메일 형식이 잘못되었습니다.\n다시 확인해 주세요.",
						"error"
					);
					return false;
				}
				if (key == 'password' && regEx.pw.test(value) == false) {
					swal.alert_txt(
						"비밀번호 형식 오류",
						"비밀번호는 영문 최소 8자, 대문자, 소문자, 숫자 및 특수 문자 하나 이상 포함되어야 합니다.",
						"error"
					);
					return false;
				}
				if (key == 'phone' && regEx.phone.test(value) == false) {
					swal.alert_txt(
						"전화번호 형식 오류",
						"전화번호 형식이 잘못 되었습니다.\n다시 확인해 주세요.",
						"error"
					);
					return false;
				}
			}
		}

		// 인증 유무 체크
		if(!edc){
			swal.alert_txt(
				"이메일 검사",
				"이메일 중복검사를 먼저 해야합니다.",
				"error"
			);
			return false;
		}
		// 이메일 인증
		// 전화번호 인증
		
		// 가입
		form.submit();
	}
}