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
	// 일반 alert
	alert: (msg) => {
		Swal.fire(msg);
	},
	// 아이콘 있는 alert
	alert_icon: (msg, icon) => {
		Swal.fire({ icon: icon, title: msg, });
	},
	alert_txt: (title, msg, icon) => {
		Swal.fire(title, msg, icon);
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

const regEx = {
	space: /\s/,
	numbers: /^[0-9]*$/,
	eng: /^[a-zA-Z]*$/,
	engAndNum: /^[a-zA-Z0-9]*$/,
	kor: /^[가-힣]*$/,
	email: /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/,
	phone: /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/,
	// 최소 8 자 최대 128 지 및 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상
	pw: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,128}$/
}

const checkboxUtil = {
	// 체크박스 배열을 받아 체크 된 갯수 반환
	chkCnt: function (chks) {
		let cnt = 0;

		for (let c of chks) {
			if (c.checked) {
				cnt++
			}
		}

		return cnt;
	},
	// 모두 체크가 되어 있다면 true, 아니면 false
	isAllChk: function (chks) {
		return chks.length == this.chkCnt(chks);
	}
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