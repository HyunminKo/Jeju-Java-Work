<%@ page import="java.util.List" %>
<%@ page import="study3.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    String getColor() {
        char[] chs = "0123456789abcdef".toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < 6; i++){
            int j = (int)(Math.random()*16);
            sb.append(chs[j]);
        }
        return sb.toString();
    }
%>
<%
    //1. 변수 선언
    List<BangMyungVO> rl = null;
    Exception err = null;

    //2. DB 연동
    BangMyungDAO dao = new BangMyungDAO_OracleImpl();
    try {
        rl = dao.findAll();
    }
    catch(Exception e){
        err = e;
    }
    //3. 흐름 만들기
    if(rl == null || err != null){
        response.sendRedirect("/study3/error.jsp");
    }else {
%>
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
        <%
            for(BangMyungVO vo : rl){
                String color = getColor();
        %>
        <tr bgcolor="#<%=color%>">
            <td><%=vo.getNo()%></td>
            <td><%=vo.getGul()%></td>
            <td><%=vo.getTheTime()%></td>
        </tr>
        <%
            }
        %>
    </table>
    <br/><br/>
    <form method="POST" action="bangmyung_add2.jsp">
        <input type="text" size="50" name="gul"/>
        <input type="submit"/>
    </form>
</body>
</html>
<%
    }
%>