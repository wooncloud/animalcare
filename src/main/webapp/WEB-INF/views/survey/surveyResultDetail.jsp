<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<!-- Step 1) Load D3.js --> 
<script src="https://d3js.org/d3.v5.min.js"></script> 
<!-- Step 2) Load billboard.js with style --> 
<script src=".././js/billboard.js"></script> 
<!-- Load with base style --> 
<link rel="stylesheet" href=".././css/billboard.css">

${surveyResultDetail[0].title}
<div class="card my-1">
   <div class="card-body">
      설문 제목<br>
      <input type="text" class="form-control" name="title" value="${surveyResultDetail[0].title}" disabled="disabled">

      
      <div id="formResult">
      </div>
<!--       <div id="chart"> -->
<!--       </div> -->
      
    </div>
</div>

<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>



<script type="text/javascript">

</script>
<%@include file="/footer.jsp" %>