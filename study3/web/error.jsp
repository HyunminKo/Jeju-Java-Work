<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exception e = (Exception) session.getAttribute("error");
    StackTraceElement[] elements = e.getStackTrace();

    out.println(e.toString()+"<br/>");
    for(StackTraceElement element : elements) {
        out.println(element+"<br/>");
    }
    session.removeAttribute("error");
%>