<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="study3.Util" %><%--
  User: hyunminko
  Date: 2019-08-01
  Time: 14:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String ctxPath = request.getContextPath();
    String content = request.getParameter("content");
    int roomNo = -1;
    String level = null;
    Cookie[] cookies = request.getCookies();
    if(cookies==null){
        response.sendRedirect(ctxPath+"/talk_login.jsp");
        return;
    }
    for(Cookie c : cookies){
        if("level".equals(c.getName())){
            level = c.getValue();
        }
        if("roomNo".equals(c.getName())){
            roomNo = Util.parseInt(c.getValue());
        }
    }
    if(level == null || roomNo == -1 || "orange".equals(level)){
        response.sendRedirect(ctxPath + "/talk_login.jsp");
        return;
    }

    Exception err = null;
    try {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
        String sql = "insert into talk_t values(seq_talk.nextval,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,roomNo);
        stmt.setString(2,content);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }catch (Exception e){
        throw e;
    }
    if(err!=null) {
        session.setAttribute("error", err);
        response.sendRedirect(ctxPath + "/error.jsp");
    } else if("apple".equals(level)){
        response.sendRedirect(ctxPath+"/talk_view_apple.jsp");
    } else if("banana".equals(level)){
        response.sendRedirect(ctxPath+"/talk_view_banana.jsp");
    }
%>