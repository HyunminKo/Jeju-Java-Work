<%@ page import="study3.Util" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  User: hyunminko
  Date: 2019-08-01
  Time: 13:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctxPath = request.getContextPath();
    int talkNo = Util.parseInt(request.getParameter("talkNo"));
    System.out.println(talkNo);
    int roomNo = -1;
    String level = null;
    Cookie[] cookies = request.getCookies();
    if(cookies == null){
        response.sendRedirect(ctxPath+"/talk_login.jsp");
        return;
    }
    for(Cookie v : cookies){
        if("level".equals(v.getName())){
            level = v.getValue();
        }else if("roomNo".equals(v.getName())){
            roomNo = Util.parseInt(v.getValue());
        }
    }
    if(level == null || talkNo == -1 || roomNo == -1 || !"apple".equals(level)){
        response.sendRedirect(ctxPath + "/talk_login.jsp");
        return;
    }
    Exception err = null;
    try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
        String sql = "delete from talk_t where talk_no = ? and room_no = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,talkNo);
        stmt.setInt(2,roomNo);
        int result = stmt.executeUpdate();

        stmt.close();
        conn.close();
    }catch (Exception e){
        err = e;
    }
    if(err!=null){
        session.setAttribute("error",err);
        response.sendRedirect(ctxPath + "/error.jsp");
        return;
    }else {
        response.sendRedirect(ctxPath + "/talk_view_apple.jsp");
    }

%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
