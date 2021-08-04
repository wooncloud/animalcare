<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<style type="text/css">
	.grant-div td{
		text-align: center;
		vertical-align: middle;
	}
</style>
<script type="text/javascript" src="${path}/js/admin.js"></script>
<div class="row">
	<div class="col-12">
		<div id="grantDiv" class="my-3 card grant-div">
			<div class="card-header fw-bold">
				병원관계자 승인처리
			</div>
			<div class="card-body">
				<table class="table table-sm table-striped">
					<thead>
						<tr>
							<td>이메일</td>
							<td>이름</td>
							<td>전화번호</td>
							<td>사업자번호</td>
							<td>면허번호</td>
							<td>승인여부</td>
							<td>승인</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="o" items="${operList}" varStatus="vs">
							<tr>
								<td class="email">${o.email}</td>
								<td class="name">${o.name}</td>
								<td class="phone">${o.phone}</td>
								<td class="crn">${o.corpregnum}</td>
								<td class="ln">${o.licensenum}</td>
								<td class="flag">
									<c:choose>
										<c:when test="${o.approvalflag eq 'Y'}"><span class="badge bg-success">승인</span></c:when>
										<c:when test="${o.approvalflag eq 'N'}"><span class="badge bg-danger">거부</span></c:when>
										<c:otherwise><span class="badge bg-warning">대기</span></c:otherwise>
									</c:choose>
								</td>
								<td>
									<input type="button" class="btn btn-sm btn-success" value="승인" onclick="grantOper(this, 'Y')">
									<input type="button" class="btn btn-sm btn-danger" value="거부" onclick="grantOper(this, 'N')">
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-12">
		<div id="grantDiv" class="my-3 card grant-div">
			<div class="card-header d-flex justify-content-between align-items-center">
				<div>설문조사</div>
				<div>
					<button type="button" class="btn btn-sm btn-primary" onclick="javascript:location.href='./surveyForm.do';">작성</button>
					<input type="submit" class="btn btn-sm btn-danger" value="삭제">
				</div>
			</div>
			<div class="card-body">
				<table class="table table-sm table-striped">
					<thead>
						<tr>
							<td>
								<input class="form-check-input" type="checkbox" onclick="deleteSurvey(this.checked)">
							</td>
							<td>NO</td>
							<td>설문제목</td>
							<td>응답기간</td>
							<td>등록일</td>
							<td>배포상태</td>
							<td>삭제여부</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="survey" items="${adminSurveyList}" varStatus="vs">
							<tr>
								<td><input class="form-check-input" type="checkbox" value="${survey.seq}" name="chkVal"></td>
								<td>${fn:length(adminSurveyList)-vs.count+1}</td>
								<td><a href="./surveyDetail.do?seq=${survey.seq}">${survey.title}</a></td>
								<td>
									<c:choose>
										<c:when test="${survey.startdate eq null}">
											-
										</c:when>
										<c:otherwise>
											${survey.startdate} ~ ${survey.enddate}
										</c:otherwise>
									</c:choose>
								</td>
								<td>${survey.regdate}</td>
								<td>
									<c:choose>
										<c:when test="${survey.surveyflag eq 'Y'}">
											<span class="badge bg-primary" data-flag="${survey.surveyflag}">배포됨</span>
										</c:when>
										<c:when test="${survey.surveyflag eq 'N'}">
											<span class="badge bg-secondary" data-flag="${survey.surveyflag}">배포안됨</span>
										</c:when>
									</c:choose>
								</td>
								<td>
									<c:if test="${survey.delflag eq 'Y'}">
										<span class="badge bg-danger" data-delflag="${survey.delflag}">삭제됨</span>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@include file="/footer.jsp" %>