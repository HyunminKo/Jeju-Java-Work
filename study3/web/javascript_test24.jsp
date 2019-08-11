<%--
  User: hyunminko
  Date: 2019-08-07
  Time: 13:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--
        XMLHttpRequest : ajax의 핵심 개체
        xhr.onreadystatechange = ... 는 이벤트 처리 코드가 된다.

        readyState 1: 요청보낼 준비가 완료되었다. by open 함수
        readyState 2: 요청 발송 완료
        readyState 3: 응답의 제일 처음이 도착
        readyState 4: 응답의 끝이 도착 (도착 완료)
    --%>
    <script>
        function ajaxGet(url,success,error){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(e) {
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        if(success){
                            success(xhr.responseText)
                        }
                    }else {
                        if(error){
                            error(xhr.responseText)
                        }
                    }
                }
            }
            xhr.open("GET",url,true);
            xhr.send(null);
        }
        window.onload = function(){
            if(abcd == undefined){
                console.log(abcd)
            }
            abcd = "test"
            if(abcd != undefined){
                console.log(abcd)
            }
            ajaxGet("for_test24.jsp",function(rt){
                var rl = rt.split(",")
                console.log(rl)
            }, function(code){
                console.log("error: "+code)
            })
        }
    </script>
</head>
<body>

</body>
</html>
