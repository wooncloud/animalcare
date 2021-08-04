<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>

<script src="${path}/js/pet.js"></script>

<div class="container">
	<form action="./modifyPet.do" method="post" id="pdto">
		<div class="modal-body">
			<h2 class="modal-title" id="detailModalLabel">정보수정</h2>
			<div class="container-fluid">
				<div class="row my-4">
					<div class="col-md-4 border border-light px-auto text-center">
						<c:choose>
							<c:when test="${pdto.profile}">
								<button type="button" class="btn btn-sm">사진변경</button>
							</c:when>
							<c:otherwise>
								<img src="${path}/img/noImg.png" class="img-fluid row mx-auto">
								<input type="hidden" id="profile" name="profile" value="">
								<button type="button" class="btn btn-primary btn-sm row mx-auto mb-4">사진등록</button>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-md-8 border border-light">
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">이름*</div>
							<div class="col-md-10 py-2 border border-light text-center">
								<input class="text-center" type="text" id="name" name="name" value="${pdto.name}" required="required">
							</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">생년월일*</div>
							<div class="col-md-10 py-2 border border-light text-center">
								<input class="text-center" type="date" id="birth" name="birth" value="${pdto.birth}" required="required">
							</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">성별*</div>
							<div class="col-md-4 py-2 border border-light text-center">
								<select id="gender" name="gender">
									<option value="M" ${pdto.gender eq 'M'?"selected":""}>남</option>
									<option value="F" ${pdto.gender eq 'F'?"selected":""}>여</option>
								</select>
							</div>
							<div class="col-md-2 py-2 border border-light text-center">무게(kg)*</div>
							<div class="col-md-4 py-2 border border-light text-center">
								<input class="text-center" type="text" id="weight" name="weight" value="${pdto.weight}" required="required">
							</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">종류*</div>
							<div class="col-md-4 py-2 border border-light text-center">
								<select id="type" name="type">
									<optgroup label="포유류">
										<option value="PET001" ${pdto.type eq '개'?"selected":""}>개</option>
										<option value="PET002" ${pdto.type eq '고양이'?"selected":""}>고양이</option>
										<option value="PET003" ${pdto.type eq '쥐'?"selected":""}>쥐</option>
										<option value="PET004" ${pdto.type eq '햄스터'?"selected":""}>햄스터</option>
										<option value="PET005" ${pdto.type eq '고슴도치'?"selected":""}>고슴도치</option>
										<option value="PET006" ${pdto.type eq '토끼'?"selected":""}>토끼</option>
										<option value="PET007" ${pdto.type eq '소형 포유류'?"selected":""}>소형 포유류</option>
										<option value="PET008" ${pdto.type eq '대형 포유류'?"selected":""}>대형 포유류</option>
										<option value="PET009" ${pdto.type eq '기타 포유류'?"selected":""}>기타 포유류</option>
									</optgroup>
									<optgroup label="조류">
										<option value="PET010" ${pdto.type eq '앵무새'?"selected":""}>앵무새</option>
										<option value="PET011" ${pdto.type eq '닭'?"selected":""}>닭</option>
										<option value="PET012" ${pdto.type eq '오리'?"selected":""}>오리</option>
										<option value="PET013" ${pdto.type eq '거위'?"selected":""}>거위</option>
										<option value="PET014" ${pdto.type eq '애완조'?"selected":""}>애완조</option>
										<option value="PET015" ${pdto.type eq '맹금류'?"selected":""}>맹금류</option>
										<option value="PET016" ${pdto.type eq '기타 조류'?"selected":""}>기타 조류</option>
									</optgroup>
									<optgroup label="양서류">
										<option value="PET017" ${pdto.type eq '거북이'?"selected":""}>거북이</option>
										<option value="PET018" ${pdto.type eq '도마뱀'?"selected":""}>도마뱀</option>
										<option value="PET019" ${pdto.type eq '뱀'?"selected":""}>뱀</option>
										<option value="PET020" ${pdto.type eq '악어'?"selected":""}>악어</option>
										<option value="PET021" ${pdto.type eq '기타'?"selected":""}>기타</option>
										<option value="PET022" ${pdto.type eq '도룡뇽'?"selected":""}>도룡뇽</option>
										<option value="PET023" ${pdto.type eq '개구리'?"selected":""}>개구리</option>
										<option value="PET024" ${pdto.type eq '두꺼비'?"selected":""}>두꺼비</option>
										<option value="PET025" ${pdto.type eq '아홀로틀'?"selected":""}>아홀로틀</option>
										<option value="PET026" ${pdto.type eq '기타 양서류'?"selected":""}>기타 양서류</option>
									</optgroup>
									<optgroup label="어류">
										<option value="PET027" ${pdto.type eq '구피'?"selected":""}>구피</option>
										<option value="PET028" ${pdto.type eq '금붕어'?"selected":""}>금붕어</option>
										<option value="PET029" ${pdto.type eq '열대어'?"selected":""}>열대어</option>
										<option value="PET030" ${pdto.type eq '갑각류'?"selected":""}>갑각류</option>
										<option value="PET031" ${pdto.type eq '기타 어류'?"selected":""}>기타 어류</option>
									</optgroup>
									<option value="PET032" ${pdto.type eq '연체류'?"selected":""}>연체류</option>
									<option value="PET033" ${pdto.type eq '곤충'?"selected":""}>곤충</option>
									<option value="PET034" ${pdto.type eq '기타'?"selected":""}>기타</option>
								</select>
							</div>
							<div class="col-md-2 py-2 border border-light text-center">종류상세</div>
							<div class="col-md-4 py-2 border border-light text-center">
								<input class="text-center" type="text" id="typeinfo" name="typeinfo" value="${pdto.typeinfo}">
							</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">등록번호</div>
							<div class="col-md-10 py-2 border border-light text-center">
								<input class="text-center" type="text" id="aniregistnum" name="aniregistnum" value="${pdto.aniregistnum}">
							</div>
						</div>
						<div class="row  border border-light">
							<div class="col-md-2 py-2 border border-light text-center">특이사항</div>
							<div class="col-md-10 py-2 border border-light text-center">
								<textarea rows="8" cols="40" id="notification" name="notification" value="${pdto.notification}"></textarea>
<%-- 								<input class="text-center" type="text" id="notification" name="notification" value="${pdto.notification}"> --%>
							</div>
						</div>

					</div>
				</div>
			</div>
		<div class="footer d-grid gap-2 d-md-flex justify-content-md-center">
			<input type="submit" value="수정" class="btn btn-lg btn-primary mx-2"> 
			<input type="button" value="취소" class="btn btn-lg btn-secondary mx-2" onclick="history.back(-1)">
		</div>
		</div>
	</form>
</div>

<%@include file="/footer.jsp"%>