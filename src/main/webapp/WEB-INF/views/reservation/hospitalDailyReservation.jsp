<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp" %>
    ${todayReserve}
  <div class="card my-1">
   <div class="card-body">
      <div class="row">
          <div class="col-1">
            <h5 class="card-title"> NO</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 예약 시간</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 예약자</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 이메일</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 연락처</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 예약종류</h5>
          </div>
          <div class="col-1">
             <h5 class="card-title">예약</h5>
          </div>
      </div>
   </div>
</div>

<c:if test="${todayReserve eq null}">

<div class="card my-1">
      <div class="card-body">
         <div class="row">
             <div class="col-1">
               <p class="card-text">${vs.count}</p>
             </div>
             <div class="col-1">
               <p class="card-text">${today.reservetime}</p>
             </div>
             <div class="col-2">
                 <p class="card-text"><a href="./userReserveDetail.do?seq=${today.seq}">${today.name}</a></p>
             </div>
             <div class="col-3">
                <p class="card-text">${today.user_email}</p>
             </div>
             <div class="col-2">
                <p class="card-text">${today.phone}</p>
             </div>
              <div class="col-2">
                <p class="card-text">${today.reservetype}</p>
             </div>
             <div class = "col-1">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#testModal">
					예약
			</button>
				</div>
			<div class="modal fade" id="testModal" tabindex="-1" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">예약신청</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div>
								<form action="./sendReservation.do" method="post">
							    <div class="form-group">
							      <label>예약 종류:</label>
							      <select name="reservetype">
							      	 <option value="default" selected="selected">선택</option>
							      	 <option value="일반진료" >일반진료</option>
							      	 <option value="건강검진">건강검진</option>
							      	 <option value="예방접종">예방접종</option>
							      </select>
							    </div>
							    <div class="form-group">
							      <label>반려 동물:</label>
							      <select name=pet_name>
							      <option value="default">선택</option>
							      <c:forEach items="${userPet}" var="pet">
							      	 <option value="${pet}">${pet}</option>
							      	 </c:forEach>
							      </select>
							    </div>
							    <div class="form-group">
							      <label for="name">이름:</label>
							      <input type="text" class="form-control" id="name" name="name">
							    </div>
							    <div class="form-group">
							      <label for="email">이메일:</label>
							      <input type="text" class="form-control" id="user_email" name="user_email">
							    </div>
							     <div class="form-group">
							      <label for="phone">전화번호:</label>
							      <input type="text" class="form-control" id="phone" name="phone">
							    </div>
							    
							  </form>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<input type="button" class="btn btn-primary" value="예약신청" onclick="makeReserve()">
						</div>
					</div>
				</div>
			</div>
		</div>
         </div>
      </div>
</c:if>
<c:forEach var="today" items="${todayReserve}" varStatus="vs">
   <div class="card my-1">
      <div class="card-body">
         <div class="row">
             <div class="col-1">
               <p class="card-text">${vs.count}</p>
             </div>
             <div class="col-1">
               <p class="card-text">${today.reservetime}</p>
             </div>
             <div class="col-2">
                 <p class="card-text"><a href="./userReserveDetail.do?seq=${today.seq}">${today.name}</a></p>
             </div>
             <div class="col-3">
                <p class="card-text">${today.user_email}</p>
             </div>
             <div class="col-2">
                <p class="card-text">${today.phone}</p>
             </div>
              <div class="col-2">
                <p class="card-text">${today.reservetype}</p>
             </div>
      </div>
    </div>
   </div>
</c:forEach>

<%@ include file="/footer.jsp" %>