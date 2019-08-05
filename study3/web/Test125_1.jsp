<%--
  User: hyunminko
  Date: 2019-08-02
  Time: 14:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="l"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="12">
    <thead>
    <tr>
        <th width='80'>No</th>
        <th width='320'>Content</th>
        <th width='160'>date</th>
    </tr>
    </thead>
    <l:forEach var="row" varStatus="vs" items="${rl}">
        <tr bgcolor="${(vs.count)%2==0?'khaki':'lightpink'}">
            <td>${row.no}</td>
            <td>${row.gul}</td>
            <td>${row.theTime}</td>
            <td>${vs.count}</td>
        </tr>
    </l:forEach>
</table>
</body>
</html>
