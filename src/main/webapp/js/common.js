/**
 * 
 */
// swal2 toast setting ---------------------------------------------->
const swalMini = Swal.mixin({
	toast: true,
	position: 'top-end',
	showConfirmButton: false,
	timer: 3000,
	timerProgressBar: true,
	didOpen: (toast) => {
		toast.addEventListener('mouseenter', Swal.stopTimer)
		toast.addEventListener('mouseleave', Swal.resumeTimer)
	}
});

const swalNotice = Swal.mixin({
	toast: true,
	position: 'top-end',
	showConfirmButton: false,
	showCloseButton: true
});
// <---------------------------------------------- swal2 toast setting

// swal2 공통모듈
const swal = {
	checkIcon: (icon) => {
		switch (icon) {
			case "success":
				return "success";
			case "error":
				return "error";
			case "warning":
				return "warning";
			case "info":
				return "info";
			case "question":
				return "question";
			default:
				return "";
		}
	},
	// 일반 alert
	alert: (msg) => {
		Swal.fire(msg);
	},
	// 아이콘 있는 alert
	alert_icon: (msg, icon) => {
		icon = checkIcon(icon);
		if (icon == "") {
			Swal.fire(msg);
		} else {
			Swal.fire({
				icon: icon,
				title: msg,
			});
		}
	},
	// confirm
	// msg : 질의 메시지
	// action : '네'를 선택시 반응할 메서드
	confirm: (msg, action) => {
		Swal.fire({
			title: msg,
			showCancelButton: true,
			confirmButtonText: `네`,
			concelButtonText: `아니오`,
		}).then((result) => {
			if (result.isConfirmed) {
				action();
			}
		});
	},
	// prompt
	// msg : 질의 메시지
	// action : 값을 받아 실행되는 메서드
	prompt: (msg, action) => {
		Swal.fire({
			title: msg,
			input: 'text',
			inputAttributes: { autocapitalize: 'off' },
			showCancelButton: true,
			confirmButtonText: '제출'
		}).then((result) => {
			if (result.isConfirmed) {
				action(result.value);
			}
		});
	},
	toast_s: (msg) => {
		swalMini.fire({ icon: 'success', title: msg })
	},
	toast_e: (msg) => {
		swalMini.fire({ icon: 'error', title: msg })
	},
	toast_w: (msg) => {
		swalMini.fire({ icon: 'warning', title: msg })
	},
	notice: (msg) => {
		swalNotice.fire({ icon: 'info', title: msg })
	},
}

// JCF Set과 같은 역할
function uniqueArray(arr) {
	return Array.from(new Set(arr))
}

// str이 Date인지 확인
function isValidDateStr(str) {
	var date = new Date(str);
	return !_.isNaN(date.getTime());
}

/**
 * 핸드폰 번호 입력할 때 자동대시
 * 11자리 : 000-0000-0000
 * 10자리 : 000-000-0000
 */
function autoDashPhoneNumber(str) {
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if (str.length < 4) {
		return str;
	} else if (str.length < 7) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	} else if (str.length < 11) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	} else {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
}

/**
 * 생일 입력할 때 자동대시
 */
function autoDashDate(str) {
	str = str.replace(/[^0-9]/g, '');

	var tmp = '';
	if (str.length < 5) {
		return str;
	} else if (str.length < 7) {
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4, 5);
		return tmp;
	} else {
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4, 2);
		tmp += '-';
		tmp += str.substr(6, 2);
		return tmp;
	}
}

/**
 * 첨부파일 사이즈 가져오기
 * */
function formatBytes(bytes, decimals) {
	if (bytes == 0) {
		return '0 Byte';
	}

	var k = 1000;
	var dm = decimals + 1 || 3;
	var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
	var i = Math.floor(Math.log(bytes) / Math.log(k));
	return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
}

// 빈 값인지 체크
function isEmpty(value) {
	if (value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
		return true
	} else {
		return false
	}
};

/**
 * Html에서 태그를 제거
 */
 function RemoveHTML(text) {
    var objReg = new RegExp();
    objReg = /[<][^>]*[>]/gi;
    text= text.replace(objReg, "");
    return text;
}

/**
 * 1자리 숫자일 경우 0붙여주기
 * @param n
 * @returns {string}
 */
function addZero(n) {
    return n < 10 ? "0" + n : n;
}


/**
 * 태그 제거하기
 * @param input
 * @param allowed
 * @returns {XML|string}
 */
function strip_tags (input, allowed) {
    allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
    var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
        commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
    return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
        return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
    });
}


/**
 * 쿠키관련 함수 (get/set/delete)
 * @param name
 * @returns {T}
 */
 function getCookie(cName) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + cName + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function setCookie(cName, cValue, cDay ){
    var expire = new Date();
    expire.setDate(expire.getDate() + cDay);
    cookies = cName + '=' + escape(cValue) + '; domain='+location.hostname+'; path=/;';
    if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
    document.cookie = cookies;
}

function DeleteCookie(name) {
    setCookie(name,"",-1);
}