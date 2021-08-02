<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<style>
.iamcard{
 padding: 0;
}
</style>

<div>각종 테스트 페이지</div>
<div class="row">
	<div class="col-6">
		그리드테스트
		<hr>
		김젤리

<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">Email address</label>
  <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
</div>
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
</div>		
		
	</div>
	<div class="col">
		이젤리
	</div>
	<div class="col">
		박젤리
		<div class="card" style="width: 18rem;">
  			<img src="..." class="card-img-top" alt="...">
  			<div class="card-body">
    			<h5 class="card-title">Card title</h5>
    			<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    			<a href="#" class="btn btn-primary">Go somewhere</a>
  			</div>
		</div>
	</div>
</div>



<div class="mb-3 row">
    <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="email@example.com">
    </div>
  </div>
  <div class="mb-3 row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword">
    </div>
  </div>
  
  <div class="container">
  그리드 테스트
  <div class="row">
    <div class="iamcard col-2">
      <div class="card">
  <div class="card-body">
   카트1
  </div>
</div>
    </div>
    <div class="iamcard col">
     <div class="card">
  <div class="card-body">
  카드2
  </div>
</div>
    </div>
    <div class="iamcard col-2">
    <div class="card">
  <div class=" card-body">
  카드3
  </div>
</div>
    </div>
  </div>
</div>

<%@include file="/footer.jsp" %>