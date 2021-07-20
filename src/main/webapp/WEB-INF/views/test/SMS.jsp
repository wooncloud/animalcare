<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<div class="row">
    <div class="col-md-6">이거성</div>
    <div class="col-md">유상진</div>
    <div class="col-md">조동찬</div>
</div>

<!-- --------------------------------------------------------------------------------- -->

    <div>
        <input type="tel" name="phoneNumber" id="inputPhoneNumber" placeholder="휴대폰 번호 입력">
        <input type="button" value="인증하기" id="sendPhoneNumber">
    </div>
    <div>
        <input type="text" placeholder="인증번호">
    </div>
</body>
<script type="text/javascript">
    $(document).ready(function () {

        $('#sendPhoneNumber').click(function () {

            let phoneNumber = $('#inputPhoneNumber').val();
            alert('인증번호 발송 완료!');

            $.ajax({
                type: "POST",
                url: "./sendSMS.do",
                data: {
                    "phoneNumber": phoneNumber
                },
                success: function (res) {
                	console.log(res);
//                     $('#checkBtn').click(function () {
//                         if ($.trim(res) == $('#inputCertifiedNumber').val()) {
//                             alert('휴대폰 인증이 정상적으로 완료되었습니다.');

//                             // 휴대폰 번호 업데이트 ajax
//                             // $.ajax({
//                             //     type: "GET",
//                             //     url: "/update/phone",
//                             //     data: {
//                             //         "phoneNumber": $('#inputPhoneNumber').val()
//                             //     }
//                             // });

//                             document.location.href = "/";
//                         } else {
//                             alert('인증번호가 올바르지 않습니다!');
//                         }
//                     })
                },
                error : function(msg){
                    console.log(msg);
                }
            })
        });
    });

</script>

<%@include file="/footer.jsp" %>