<%--
  User: hyunminko
  Date: 2019-08-01
  Time: 16:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="study3.TalkRoomVO"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jc"%>
<%
    List<String> rl = new ArrayList<>();
    rl.add("one");
    rl.add("two");
    rl.add("three");
    rl.add("four");
    request.setAttribute("rl",rl);
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <jc:forEach var="i" begin="0" end="4">
        <jc:choose>
            <jc:when test="${(i%3)==0}">apple</jc:when>
            <jc:when test="${(i%3)==1}">banana</jc:when>
            <jc:otherwise>orange</jc:otherwise>
        </jc:choose>
    </jc:forEach>
    <table border="1" cellpadding="10">
    <jc:forEach var="vo" items="${rl}">
        <tr>
            <td>${vo}</td>
        </tr>
    </jc:forEach>
    </table>
</body>
</html>
