<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<button type="button" class="btn btn-outline-success" onclick="choiceQuestion()">선호도형 추가</button>
<button type="button" class="btn btn-outline-success" onclick="essayQuestion()">서술형 추가</button>

<button onclick="onMultipleChoiceAdd()">객관식 추가</button>
<button onclick="onSubjectiveAdd()">주관식 추가</button>

<div class="card my-1">
	<div class="card-body" id="form-container">
	
	</div>
</div>

<!-- <div id="form-container"> -->
<!-- </div> -->


    <script>
        const typeMultiple = "MULTIPLE";
        const typeSubjective = "SUBJECTIVE";
        
        var formData = [];
        var formContainerEle = document.getElementById("form-container");

        function essayQuestion() {
            formData.push({
                "type": typeSubjective,
                "question": "",
                "response" : ""
            });

            render();
        }

        function choiceQuestion() {
            formData.push({
                "type": typeMultiple,
                "question": "",
                "responseOptions" : [
                    { idx: 0, value: "1"},
                    { idx: 1, value: "2"},
                    { idx: 2, value: "3"},
                    { idx: 3, value: "4"},
                    { idx: 4, value: "5"}
                ]
            })

            render();
        }

        function render() {
            formContainerEle.innerHTML = "";
            var multipleFormIdx = 0;
            
            var htmlContent = "";

            formData.forEach(function (item) {
              if (item.type == typeMultiple) {
                multiple = ""; 
                multiple += "<div class='card my-1'>";
                multiple += "	<div class='card-body'>";
//             	multiple += "<h2>" + item.question + "</h2>"
                multiple += "<input type='text' class='form-control'" + item.question + "required='required'>";
                
                item.responseOptions.forEach(function (optItem) {
                  multiple += "<input type='radio' name='radio-form-'" + multipleFormIdx + "' value='" + optItem.value + "'>" + optItem.value;
                });
                multiple += "	</div>";
                multiple += "</div>";
                htmlContent += multiple;
                
                multipleFormIdx++;
              }

              if (item.type == typeSubjective) {
                subjective = "";
//                 subjective += "<h2>" + item.question + "</h2>";
				subjective += "<div class='card my-1'>";
				subjective += "	<div class='card-body'>";
                subjective += "<input type='text' class='form-control'" + item.question + "required='required'>";
                subjective += "<textarea rows='5' class='form-control'></textarea>";
                subjective += "	</div>";
                subjective += "</div>";
                htmlContent += subjective;
              }
            });

            formContainerEle.innerHTML = htmlContent;
        }
    </script>
    
<%@include file="/footer.jsp" %>