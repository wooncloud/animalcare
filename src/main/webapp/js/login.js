window.onload = function () {
	let pathnameSplits = window.location.pathname.split('/');
	let pathname = pathnameSplits[pathnameSplits.length - 1];

	if (pathname.indexOf('login') != -1) {
		login.init();
	}
	else if (pathname.indexOf('Info') != -1) {
		myinfo.init();
	}
	else if (pathname.indexOf('signupUserForm') != -1) {
		signupUser.init();
	}
	else if (pathname.indexOf('signupOperForm') != -1) {
		signupOper.init();
	}
	else if (pathname.indexOf('signupSelect') != -1) {
		select.init();
	}
}

const login = {
	init: function () {
		if (getParam('type') == "empty") {
			swal.alert_txt("로그인 실패", "로그인에 실패했습니다.\n이메일과 비밀번호를 확인후 다시 시도하세요.", "");
		}
		else if (getParam('type') == "approval") {
			swal.alert_txt("로그인 실패", "승인되지 않은 계정입니다.\n관리자에게 문의하세요.", "");
		}

		document.getElementById('email').addEventListener('keypress', this.enterLogin);
		document.getElementById('password').addEventListener('keypress', this.enterLogin);
	},
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
	},
	enterLogin: function(e){
		if(e.keyCode == 13){
			this.login();
		}
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
	status: {
		edc: false,
		evc: false,
		pvc: false
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

		document.getElementById('email').addEventListener('change', function () {
			signupUser.status.edc = false;
		});

		// mask
		IMask(document.getElementById('phone'), { mask: '000-0000-0000' });

		signupUser.status.edc = false;
		signupUser.status.evc = false;
		signupUser.status.pvc = false;
	},
	emailDupl: function () {
		let email = document.getElementById("email").value;

		$.ajax({
			url: "./emailDupl.do",
			type: "POST",
			data: "email=" + email,
			success: function (msg) {
				if (msg == false) {
					signupUser.status.edc = true;
					swal.alert_txt("이메일 중복 검사", "사용 가능한 이메일입니다.", "success");
				}
				else {
					signupUser.status.edc = false
					swal.alert_txt("이메일 중복 검사", "이미 있는 계정입니다.", "error");
				}
			},
			error: function () {
				signupUser.status.edc = false
				swal.alert_txt("오류", "이메일 검사중 문제가 발생했습니다.\n관리자에게 문의하세요.", "error");
			}
		});
	},
	emailSendConfirm: function () {
		// 중복검사 먼저 진행
		if(!signupUser.status.edc){
			swal.alert_txt("", "먼저 이메일 중복검사를 해야 합니다.", "error");
			return false;
		}

		let email = document.getElementById('email').value;
		if(isEmpty(email)){
			swal.alert_txt("유효값 오류", "이메일을 먼저 입력해야 합니다.", "error");
			return false;
		}

		let data = { "email": email }

		$.ajax({
			url: './sendEmailCode.do',
			type: 'post',
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.toast_s("인증번호가 전송 되었습니다.\n메일을 확인해 주세요.");
					document.getElementById("emailVerificationDiv").style.display = "block";
					document.getElementById("btnEmailSendConfirm").disabled = true;
				} else if (msg == 'error') {
					errorAlert();
				}
			},
			error: function () {
				errorAlert();
			}
		});
	},
	emailConfirm: function () {
		// 인증번호 유효성
		let code = document.getElementById('emailChk').value;
		if(isEmpty(code)){
			swal.alert_txt("유효값 오류", "인증번호가 없습니다.", "error");
			return false;
		}

		let data = { 
			"email": document.getElementById('email').value,
			"code": code 
		}

		$.ajax({
			url: './checkEmailCode.do',
			type: 'post',
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.alert_txt(
						"인증 완료",
						"이메일 인증이 완료 되었습니다.",
						"success"
					);

					document.getElementById('email').readOnly = true;
					document.getElementById('emailChk').value = "";
					document.getElementById('btnEmailDupl').disabled = true;
					document.getElementById('emailVerificationDiv').style.display = "none";
					signupUser.status.evc = true;
				} else if (msg == 'error') {
					errorAlert();
				}
			},
			error: function () {
				errorAlert();
			}
		});
	},
	phoneSendConfirm: function () {
		// 전화번호 유효성, 형식
		let email = document.getElementById('email').value;
		let phone = document.getElementById('phone').value;
		if(isEmpty(email)){
			swal.alert_txt("유효값 오류", "이메일을 먼저 입력해야 합니다.", "error");
			return false;
		}
		if(isEmpty(phone)){
			swal.alert_txt("유효값 오류", "전화번호를 입력하세요.", "error");
			return false;
		}

		let data = { "email": email, "phone": phone }

		// 인증번호 요청 ajax
		$.ajax({
			url: './sendVerifyCode.do',
			type: 'post',
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.toast_s("인증번호가 전송 되었습니다.");
					document.getElementById('phone').readOnly = true;
					document.getElementById('btnPhoneSendConfirm').disabled = true;
					document.getElementById('phoneVerificationDiv').style.display = "block";
				} else if (msg == 'error') {
					swal.alert_txt(
						"에러",
						"문자는 발송되었으나 문제가 발생했습니다.\n관리자에게 문의하세요.",
						"error"
					);
				}
			},
			error: function () {
				errorAlert();
			},
		});
	},
	phoneConfirm: function () {
		// 인증번호 유효성
		let code = document.getElementById('phoneConfirm').value;
		if(isEmpty(code)){
			swal.alert_txt("유효값 오류", "인증번호가 없습니다.", "error");
			return false;
		}

		let data = {
			"email": document.getElementById('email').value,
			"code": code 
		}

		$.ajax({
			url: './checkVerifyCode.do',
			type: 'post',
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.alert_txt(
						"인증 완료",
						"전화번호가 인증 되었습니다.",
						"success"
					);

					document.getElementById('phoneConfirm').value = "";
					document.getElementById('phoneVerificationDiv').style.display = "none";
					signupUser.status.pvc = true;
				} else if (msg == 'error') {
					errorAlert();
				}
			},
			error: function () {
				errorAlert();
			}
		});
	},
	addrSearch: function () {
		let addr1 = document.getElementById("address1");
		let addr2 = document.getElementById("address2");
		searchAddr(addr1, addr2);
	},
	cancel: function () {
		swal.confirm("정말로 나가시겠습니까?", function(){
			location.href = "./loginForm.do";
		})
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

		if(form.password.value != document.getElementById("pwc").value){
			swal.alert_txt("비밀번호 오류", "비밀번호 확인이 다릅니다.", "error");
			return false;
		}
		if (!signupUser.status.edc) {
			swal.alert_txt("이메일 검사 오류", "이메일 중복검사를 먼저 해야합니다.", "error");
			return false;
		}
		if (!signupUser.status.evc) {
			swal.alert_txt("이메일 인증 오류", "이메일 인증을 해야 합니다", "error");
			return false;
		}
		if (!signupUser.status.pvc) {
			swal.alert_txt("전화번호 인증 오류", "전화번호 인증을 해야 합니다", "error");
			return false;
		}

		// 가입
		form.submit();
	}
}

const signupOper = {
	init: function () {
		document.getElementById('btnEmailDupl').addEventListener('click', signupUser.emailDupl);
		document.getElementById('btnEmailSendConfirm').addEventListener('click', signupUser.emailSendConfirm);
		document.getElementById('btnEmailConfirm').addEventListener('click', signupUser.emailConfirm);
		document.getElementById('btnPhoneSendConfirm').addEventListener('click', signupUser.phoneSendConfirm);
		document.getElementById('btnPhoneConfirm').addEventListener('click', signupUser.phoneConfirm);
		document.getElementById('btnCancel').addEventListener('click', signupUser.cancel);
		document.getElementById('btnSignup').addEventListener('click', signupOper.signup);

		document.getElementById('email').addEventListener('change', function () {
			signupUser.status.edc = false;
		});

		// mask
		IMask(document.getElementById('phone'), { mask: '000-0000-0000' });
		IMask(document.getElementById('corpregNum'), { mask: '000-00-00000' });

		signupUser.status.edc = false;
		signupUser.status.evc = false;
		signupUser.status.pvc = false;
	},
	signup: function () {
		let form = document.forms[0];
		let element = {
			"email": form.email,
			"password": form.password,
			"name": form.name,
			"phone": form.phone,
			"corpregnum": form.corpregNum,
			"licensenum": form.licenseNum
		}
		let data = {
			"email": form.email.value,
			"password": form.password.value,
			"name": form.name.value,
			"phone": form.phone.value,
			"corpregnum": form.corpregNum.value,
			"licensenum": form.licenseNum.value
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

		if(form.password.value != document.getElementById("pwc").value){
			swal.alert_txt("비밀번호 오류", "비밀번호 확인이 다릅니다.", "error");
			return false;
		}
		if (!signupUser.status.edc) {
			swal.alert_txt("이메일 검사 오류", "이메일 중복검사를 먼저 해야합니다.", "error");
			return false;
		}
		if (!signupUser.status.evc) {
			swal.alert_txt("이메일 인증 오류", "이메일 인증을 해야 합니다", "error");
			return false;
		}
		if (!signupUser.status.pvc) {
			swal.alert_txt("전화번호 인증 오류", "전화번호 인증을 해야 합니다", "error");
			return false;
		}

		// 가입
		form.submit();
	}
}

const myinfo = {
	init: function(){
		IMask(document.getElementById('phone'), { mask: '000-0000-0000' });
	},
	changePW: function () {
		let pw = document.getElementById('pw').value;
		let npw = document.getElementById('npw').value;
		let npwc = document.getElementById('npwc').value;

		let myModalEl = document.getElementById('pwModal');
		let modal = bootstrap.Modal.getInstance(myModalEl);

		if (isEmpty(pw) || isEmpty(npw) || isEmpty(npwc)) {
			swal.alert_txt(
				"유효값 오류",
				"내용을 입력하세요.",
				"error"
			);
			return false;
		}

		if (regEx.pw.test(npw) == false) {
			swal.alert_txt(
				"비밀번호 형식 오류",
				"비밀번호는 영문 최소 8자, 대문자, 소문자, 숫자 및 특수 문자 하나 이상 포함되어야 합니다.",
				"error"
			);
			return false;
		}

		if (npw == npwc) {
			let data = { "pw": pw, "npw": npw }

			$.ajax({
				url: './changePW.do',
				type: 'post',
				data: data,
				success: function (data) {
					if (data == 'success') {
						swal.alert_txt(
							"변경완료",
							"비밀번호가 변경 되었습니다.",
							"success"
						);
					} else if (data == 'error') {
						errorAlert();
					} else if (data == 'pw') {
						swal.alert_txt(
							"비밀번호 오류",
							"기존 비밀번호가 다릅니다.",
							"error"
						);
					}
				},
				error: function (err) {
					errorAlert();
				},
				complete: function () {
					document.getElementById('pw').value = "";
					document.getElementById('npw').value = "";
					document.getElementById('npwc').value = "";
					modal.hide();
				}
			});
		} else {
			swal.alert_txt(
				"비밀번호 확인 오류",
				"비밀번호 확인이 다릅니다.",
				"error"
			)
			return false;
		}
	},
	sendPhoneCode: function(){
		// 전화번호 유효성, 형식
		let newPhone = document.getElementById('phone').value;
		if(isEmpty(newPhone)){
			swal.alert_txt("유효값 오류", "전화번호를 입력하세요.", "error");
			return false;
		}

		let data = { "phone": newPhone }

		// 인증번호 요청 ajax
		$.ajax({
			url: './sendVerifyCode.do',
			type: 'post',
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.toast_s("인증번호가 전송 되었습니다.");
					document.getElementById('btnSendPhoneCode').disabled = true;
				} else if (msg == 'error') {
					swal.alert_txt(
						"에러",
						"문자는 발송되었으나 문제가 발생했습니다.\n관리자에게 문의하세요.",
						"error"
					);
				}
			},
			error: function () {
				errorAlert();
			},
		});
	},
	verifyPhone: function () {
		let myModalEl = document.getElementById('phoneModal');
		let modal = bootstrap.Modal.getInstance(myModalEl);

		// 인증번호 유효성
		let code = document.getElementById('phoneConfirm').value;
		if(isEmpty(code)){
			swal.alert_txt("유효값 오류", "인증번호가 없습니다.", "error");
			return false;
		}

		let data = {
			"code": code,
			"phone": document.getElementById('phone').value
		}

		$.ajax({
			url: './checkVerifyCode.do',
			type: 'post',
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.alert_txt(
						"변경완료",
						"전화번호가 변경 되었습니다.",
						"success"
					);
					
					// 전화번호 옮겨적기
					let newPhone = document.getElementById('phone').value;
					document.getElementById('infoPhone').value = newPhone;
				} else if (msg == 'error') {
					errorAlert();
				}
			},
			error: function () {
				errorAlert();
			},
			complete: function () {
				document.getElementById('phone').value = "";
				document.getElementById('phoneConfirm').value = "";
				document.getElementById('btnSendPhoneCode').disabled = false;
				modal.hide()
			}
		});
	},
	addrSearch: function () {
		let addr1 = document.getElementById("address1");
		let addr2 = document.getElementById("address2");
		searchAddr(addr1, addr2);
	},
	changeAddr: function () {
		let myModalEl = document.getElementById('addrModal');
		let modal = bootstrap.Modal.getInstance(myModalEl);

		let addr1 = document.getElementById('address1').value;
		let addr2 = document.getElementById('address2').value;

		if (isEmpty(addr1) || isEmpty(addr2)) {
			swal.alert_txt(
				"유효값 오류",
				"주소 검색을 하여 내용을 입력하세요.",
				"error"
			);
			return false;
		}

		let data = { "address1": addr1, "address2": addr2 }

		$.ajax({
			url: './changeAddr.do',
			type: 'post',
			data: data,
			success: function (data) {
				if (data == 'success') {
					swal.alert_txt(
						"변경완료",
						"주소가 변경 되었습니다.",
						"success"
					);

					document.getElementById('infoAddress1').value = addr1;
					document.getElementById('infoAddress2').value = addr2;
				} else if (data == 'error') {
					errorAlert();
				}
			},
			error: function (err) {
				errorAlert();
			},
			complete: function () {
				document.getElementById('address1').value = "";
				document.getElementById('address2').value = "";
				modal.hide();
			}
		});
	}
}

const leave = {
	leave: function () {
		let agree = document.getElementById('agree').checked;

		if (agree) {
			location.href = "./leaveUser.do";
		} else {
			swal.alert_txt("", "탈퇴 동의를 체크해야 탈퇴가 가능합니다.", "");
			return false;
		}
	}
}

// 주소 찾기 메서드
function searchAddr(addr1, addr2) {
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
			addr1.value = addr;
			// 커서를 상세주소 필드로 이동한다.
			addr2.focus();
		}
	}).open();
}

function errorAlert(){
	swal.alert_txt(
		"에러",
		"문제가 발생했습니다.\n관리자에게 문의하세요.",
		"error"
	)
}