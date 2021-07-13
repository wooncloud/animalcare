<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>Insert title here</title>
</head>
<body>
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
</html>