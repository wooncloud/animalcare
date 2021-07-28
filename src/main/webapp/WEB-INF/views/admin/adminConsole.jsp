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
			<div class="card-header">
				설문조사
			</div>
			<div class="card-body">
				설문조사 관련 내용
			</div>
		</div>
	</div>
</div>

<%@include file="/footer.jsp" %>