<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<div class="row">
	<div class="col-12">
		<div id="grantDiv" class="my-3 card grant-div">
			<div class="card-header">
				병원관계자 승인처리
			</div>
			<div class="card-body">
				<table class="table table-striped">
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
						<tr>${o.email}</tr>
						<tr>${o.name}</tr>
						<tr>${o.phone}</tr>
						<tr>${o.corpregnum}</tr>
						<tr>${o.licensenum}</tr>
						<tr>${o.approvalflag}</tr>
						<tr>
		
						</tr>
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