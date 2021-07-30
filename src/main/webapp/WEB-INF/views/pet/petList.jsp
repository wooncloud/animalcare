<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>

<div class="container">
<div class="modal-body">
	<h2>내 반려동물 보기</h2>
	<div class="d-grid gap-2 d-md-flex justify-content-md-center">
		<button type="button" class="btn btn-outline-success me-4" onclick="location.href=./petMedicalRecodeList.do">진료내역</button>
		<button type="button" class="btn btn-outline-success ms-4" onclick="location.href='../healthNote/healthNoteList.do'">건강수첩</button>
	</div>
	<div class="row row-3 cols-2">
		<c:forEach var="d" items="${pList}">
			<div id="${d.name}" class="col-6 mt-3 px-4 py-4 border border-light" onclick="location.href='./detailPet.do?name=${d.name}'">
				<div class="row">
					<div class="col-5">
						<c:choose>
							<c:when test="${d.profile}">dd</c:when>
							<c:otherwise>
								<img src="${path}/img/noImg.png" class="img-thumbnail">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-7">
						<div class="row py-2">
							<div class="col-3">이름</div>
							<div class="col-9">${d.name}</div>
						</div>
						<div class="row py-2">
							<div class="col-3">종류</div>
							<div class="col-9">${d.type}</div>
						</div>
						<div class="row py-2">
							<div class="col-3">성별</div>
							<div class="col-9">
								<c:if test="${d.gender eq 'M'}">남</c:if>
								<c:if test="${d.gender eq 'F'}">여</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${pList.size() lt 6}">
		<div class="col-6 mt-3 px-4 py-4">									<!-- 애완동물등록 -->
			<a type="button" data-bs-toggle="modal"
				data-bs-target="#insertModal"><img class="img-thumbnail"
				src='${path}/img/circle_plus.png'></a>
		</div>
		</c:if>
	</div>
</div>
</div>
<!-- insertModal -->
<div class="modal fade" id="insertModal" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="insertModalLabel">애완동물 등록</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
				<form action="./insertPet.do" method="post" id="pdto">
			<div class="modal-body">
					<div class="container-fluid">
						<div class="row my-2">
							<div class="col-md-5">이름*</div>
							<div class="col-md-7">
								<input type="text" id="name" name="name" required="required">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">생년월일(YYYYMMDD)*</div>
							<div class="col-md-7">
								<input type="date" id="birth" name="birth" required="required">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">무게(kg)*</div>
							<div class="col-md-7">
								<input type="text" id="weight" name="weight" required="required">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">성별*</div>
							<div class="col-md-7">
								<select id="gender" name="gender">
									<option value="M">남</option>
									<option value="F">여</option>
								</select>
							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">종류*</div>
							<div class="col-md-7">
								<select id="type" name="type">
									<optgroup label="포유류">
										<option value="PET001">개</option>
										<option value="PET002">고양이</option>
										<option value="PET003">쥐</option>
										<option value="PET004">햄스터</option>
										<option value="PET005">고슴도치</option>
										<option value="PET006">토끼</option>
										<option value="PET007">소형 포유류</option>
										<option value="PET008">대형 포유류</option>
										<option value="PET009">기타 포유류</option>
									</optgroup>
									<optgroup label="조류">
										<option value="PET010">앵무새</option>
										<option value="PET011">닭</option>
										<option value="PET012">오리</option>
										<option value="PET013">거위</option>
										<option value="PET014">애완조</option>
										<option value="PET015">맹금류</option>
										<option value="PET016">기타 조류</option>
									</optgroup>
									<optgroup label="양서류">
										<option value="PET017">거북이</option>
										<option value="PET018">도마뱀</option>
										<option value="PET019">뱀</option>
										<option value="PET020">악어</option>
										<option value="PET021">기타</option>
										<option value="PET022">도룡뇽</option>
										<option value="PET023">개구리</option>
										<option value="PET024">두꺼비</option>
										<option value="PET025">아홀로틀</option>
										<option value="PET026">기타 양서류</option>
									</optgroup>
									<optgroup label="어류">
										<option value="PET027">구피</option>
										<option value="PET028">금붕어</option>
										<option value="PET029">열대어</option>
										<option value="PET030">갑각류</option>
										<option value="PET031">기타 어류</option>
									</optgroup>
									<option value="PET032">연체류</option>
									<option value="PET033">곤충</option>
									<option value="PET034">기타</option>
								</select>

							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">종류상세</div>
							<div class="col-md-7">
								<input type="text" id="typeinfo" name="typeinfo">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">동물등록번호</div>
							<div class="col-md-7">
								<input type="text" id="aniregistnum" name="aniregistnum">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-md-5">특이사항</div>
							<div class="col-md-7">
								<textarea rows="8" cols="22" id="notification" name="notification"></textarea>
							</div>
						</div>

						<div class="row">
							<div class="col-md-5 ms-auto">사진첨부</div>
							<div class="col-md-7 ms-auto">
								<input type="file" id="profile" name="profile">
							</div>
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<input type="submit" value="등록" class="btn btn-primary">
				<input type="button" value="취소" class="btn btn-secondary"
					data-bs-dismiss="modal">
			</div>
				</form>
		</div>
	</div>
</div>
<%@include file="/footer.jsp"%>