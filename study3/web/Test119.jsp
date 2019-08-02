<%@ page import="study3.TalkRoomVO" %><%--
  User: hyunminko
  Date: 2019-08-01
  Time: 16:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("apple",100);
    request.setAttribute("ab",new int[]{1,2,3,4});

    TalkRoomVO vo = new TalkRoomVO();
    vo.setRoomNo(10101);
    vo.setApple("청송사과");
    vo.setBanana("제주바나나");

    request.setAttribute("t",vo);
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
${apple}
${(apple>10)}
${ab[0]}
${ab[1]}
${ab[2]}
${ab[3]}
${t}
</body>
</html>
