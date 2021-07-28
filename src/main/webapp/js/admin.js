function grantOper(e, v) {
	let row = e.closest('tr');
	let email = e.closest('tr').querySelector('td').innerText;
	let data = { "email": email, "approvalflag": v }
	let progressState = null;

	$.ajax({
		url: "./grantOper.do",
		type: "POST",
		data: data,
		beforeSend: function () {
			progressState = Swal.fire({
				title: '처리중입니다.',
				html: '잠시만 기다려 주세요.<br><br>' + 
					'<div class="spinner-border text-primary" role="status">' +
					'<span class="visually-hidden">Loading...</span>' +
					'</div>',
				showConfirmButton: false,
				allowOutsideClick: false
			})
		},
		success: function (msg) {
			if (msg == 'success') {
				swal.toast_s("변경 되었습니다.");

				// 승인여부 변경
				let emt = null;
				if (v == 'Y') {
					emt = '<span class="badge bg-success">승인됨</span>'
				} else {
					emt = '<span class="badge bg-danger">거부됨</span>'
				}

				let flag = e.closest('tr').querySelector('.flag');
				flag.innerHTML = emt
			}
			else {
				swal.alert_txt("에러", "문제가 발생했습니다.", "error");
			}
		},
		error: function () {
			swal.alert_txt("오류", "문제가 발생했습니다.", "error");
		},
		complete: function () {
			progressState.close();
		}
	});
}

