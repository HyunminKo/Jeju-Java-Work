<%--
  Created by IntelliJ IDEA.
  User: hyunminko
  Date: 2019-07-31
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        out.println(config.toString());
        out.println(config.getServletContext().getRealPath("/"));

    %>
</body>
</html>
