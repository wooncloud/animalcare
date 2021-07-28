<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<div>
	<div class="favorite-title my-3">
		<h4>🏣 관심병원 목록</h4>
	</div>
	<div class="favorite-list">
		<c:forEach var="hos" items="${list}" varStatus="vs">
			<div class="card my-2">
				<div class="card-header d-flex justify-content-between">
					<h5 class="mb-0">${hos.name}</h5>
					<div class="form-check form-switch">
						<input class="form-check-input" type="checkbox" checked onclick="modifyFavo(this, '${hos.seq}')">
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-8">${hos.address1} ${hos.address2}</div>
						<div class="col-md-4">${hos.tel}</div>
					</div>
					<div class="row">
						<div class="col-md-5 pet-type">${hos.pettype}</div>
						<div class="col-md-7">${hos.opentime}</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
	const pettype = ${pettype}
	
	window.onload = function(){
		let emts = document.getElementsByClassName('pet-type');
		for (const e of emts) {
			let types = e.innerText.split(', ');
			let result = '';
			for (const i in types) {
				result += getCodeName(types[i], pettype);
				if(i < types.length - 1){
					result += ', ';
				}
			}
			
			e.innerText = result;
		}
	}

	function modifyFavo(e, s) {
		let v = e.checked;
		let data = { "seq": s, "value": v }

		$.ajax({
			url: "./modifyFavorite.do",
			type: "POST",
			data: data,
			success: function (msg) {
				if (msg == 'success') {
					swal.toast_s("변경 되었습니다.");
				}
				else {
					swal.alert_txt("에러", "문제가 발생했습니다.\n관리자에게 문의하세요", "error");
					e.checked = v == true ? false : true;
				}
			},
			error: function(){
				swal.alert_txt("에러", "문제가 발생했습니다.\n관리자에게 문의하세요", "error");
				e.checked = v == true ? false : true;
			}
		});
	}
</script>
<%@include file="/footer.jsp" %>