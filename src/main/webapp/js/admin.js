function grantOper(e, v){
	let row = e.closest('tr');
	let email = e.closest('tr').querySelector('td').innerText;
	let data = { "email": email, "approvalflag": v }

	$.ajax({
		url: "./grantOper.do",
		type: "POST",
		data: data,
		success: function (msg) {
			if (msg == 'success') {
				swal.toast_s("변경 되었습니다.");
				// 버튼 없애기

				// 승인여부 변경
				
			}
			else {
				swal.alert_txt("에러", "문제가 발생했습니다.", "error");
			}
		},
		error: function () {
			swal.alert_txt("오류", "문제가 발생했습니다.", "error");
		}
	});
}