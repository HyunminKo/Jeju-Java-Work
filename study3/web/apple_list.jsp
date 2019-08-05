<%--
  User: hyunminko
  Date: 2019-08-02
  Time: 15:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="l"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <table border="1" cellspacing="0" cellpadding="12">
        <l:forEach var="vo" items="${rl}">
            <tr>
                <td>${vo.no}</td>
                <td>${vo.gul}</td>
                <td>${vo.theTime}</td>
            </tr>
        </l:forEach>
    </table>
    <form method="POST" action="apple_add2.do">
        <input type="text" name="gul" size="55"/>
        <input type="submit"/>
    </form>
</body>
</html>
