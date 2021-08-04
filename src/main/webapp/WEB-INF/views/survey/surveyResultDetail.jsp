<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<!-- Step 1) Load D3.js --> 
<script src="https://d3js.org/d3.v5.min.js"></script> 
<!-- Step 2) Load billboard.js with style --> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/3.1.3/billboard.min.js"></script> 
<!-- Load with base style --> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/3.1.3/billboard.min.css">

${surveyResultDetail}
<%-- ${resultAnswer} --%>


<div class="card my-1">
	<div class="card-body">
		설문 제목<br>
		<input type="text" class="form-control" name="title" value="${surveyResultDetail[0].title}" disabled="disabled">

		
		<div id="formResult">
		</div>
		
		
<!-- 		<div id="chart"> -->
<!-- 		</div> -->
		
    </div>
</div>
<div class="card my-1">
	<div class="card-body">
		<c:if test="${surveyResultDetail[0].answer!= null}">
		<p class="card-text">${surveyResultDetail[0].answer}</p>
		<c:forEach var="survey" items="${surveyResultDetail}" varStatus="vs">
			<div class="card my-1">
				<div class="card-body">
					<div class="row text-center">
					    <div class="col-12">
							<p class="card-text">${survey.answer}</p>
					    </div>
					</div>
				</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
</div>

<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>



<script type="text/javascript">
var json = ${resultAnswer}
console.log(json)
console.log(json.josn[0].answer)
var ss = json.josn[0].answer
console.log(ss.indexOf('},{'))
console.log(ss.substr(1,ss.indexOf('},{')))
console.log(ss.substr(ss.indexOf('},{')+2))



window.onload = function(){

// 	var answersLength = ${resultLength}

// 	var answers = ${surveyResultDetail[0].answer};
// 	console.log(answers)
// 	var answersDetail = [];
	
// 	for (var i = 0; i < answersLength; i++) {
// 		answersDetail = answers[i];
// 		console.log(answersDetail);
// 	}
	
	var count1 = 0;
	var count2 = 0;
	var count3 = 0;
	var count4 = 0;
	var count5 = 0;
	
// 	var list = ${surveyResultDetail}
	
	
// 	$(list).each(function(key,value){
// 		console.log(key+"   "+value);
// 	})
	
	
// 	for (var i = 0; i < answersLength; i++) {
		
		
// 		var keys = Object.keys(answersDetail[i]);
// 		var values = Object.values(answersDetail[i]);
		
// 		if(keys[1]=="qtitle"){
// 			html = "";
// 			html += "<input type='text' class='form-control' name='qtitle' value='"+values[1]+"' disabled='disabled'>"
// 			$("#formResult").append(html);
// 		}
// 		if(keys[0]==i+1){
// 			if(keys[2]=="choiceAnswer"){
// 				switch (values[2]) {
// 				case 1: count1++; break;
// 				case 2: count2++; break;
// 				case 3: count3++; break;
// 				case 4: count4++; break;
// 				case 5: count5++; break;
// 				}
				
// 			}
// 		}
	
		var chart = bb.generate({
			data : {
				columns : [ 
					[ "1", count1 ], 
					[ "2", count2 ], 
					[ "3", count3 ],
					[ "4", count4 ],
					[ "5", count5 ]
				],
				type : "gauge",
				order : "asc"
			},
			gauge : {
				title : "Title A"
			},
			bindto : "#formResult"
		});
// 	}
}
</script>
<%@include file="/footer.jsp" %>