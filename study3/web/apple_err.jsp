<%--
  User: hyunminko
  Date: 2019-08-02
  Time: 16:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="l"%>
<%
    Exception err = (Exception) session.getAttribute("err");
    StackTraceElement[] stack = err.getStackTrace();
    request.setAttribute("stack",stack);
    session.removeAttribute("err");
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <l:forEach var="row" items="${stack}">
        ${row.toString()}
        <br/>
    </l:forEach>
</body>
</html>
