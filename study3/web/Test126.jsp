<%--
  User: hyunminko
  Date: 2019-08-02
  Time: 17:14
--%>
<%@ page contentType="text/plain;charset=UTF-8" language="java" %>
<%
    String pw = request.getParameter("pw");
    if(pw!= null && pw.equals("1234")){
        String[] l = {"사과","귤","배","토마토"};
        String nl = "\r\n";

        for(int i = 0 ; i < l.length; i++){
            %><%=l[i]%><%=nl%><%
        }
    }else {
        %>암호 틀림..<%
    }
%>