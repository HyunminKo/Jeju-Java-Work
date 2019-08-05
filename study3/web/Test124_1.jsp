<%--
  User: hyunminko
  Date: 2019-08-02
  Time: 14:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String l = (String) request.getAttribute("메세지");
    String l2 = (String) request.getParameter("test");
    System.out.println(l);
%>
<%=l%>
<%=l2%>
