<%@ page import="study3.Util" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="study3.TalkRoomVO" %>
<%@page %>
<%
    String ctxPath = request.getContextPath();

    int roomNo = Util.parseInt(request.getParameter("roomNo"));
    String pwd = request.getParameter("pwd");

    if( roomNo == -1 || pwd == null || "".equals(pwd) ){
        response.sendRedirect(ctxPath +"/talk_login.jsp");
        return ;
    }

    Exception err = null;
    TalkRoomVO vo = null;

    try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
        String sql = "select * from talk_room_t where room_no = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,roomNo);
        ResultSet rs = stmt.executeQuery();
        vo = new TalkRoomVO();
        if(rs.next()){
            vo.setApple(rs.getString("apple"));
            vo.setBanana(rs.getString("banana"));
            vo.setOrange(rs.getString("orange"));
            vo.setRoomNo(rs.getInt("room_no"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }catch (Exception e){
        err = e;
    }

    if(err!=null){
        session.setAttribute("error",err);
        response.sendRedirect(ctxPath+"/error.jsp");
        return;
    }

    if(vo == null){
        response.sendRedirect(ctxPath +"/talk_login.jsp");
        return;
    }

    if(vo.getApple().equals(pwd)){ //admin
        response.addCookie(new Cookie("level","apple"));
        response.addCookie(new Cookie("roomNo",String.valueOf(roomNo)));
        response.sendRedirect(ctxPath+"/talk_view_apple.jsp");
    }else if(vo.getBanana().equals(pwd)){ //customer
        response.addCookie(new Cookie("level","banana"));
        response.addCookie(new Cookie("roomNo",String.valueOf(roomNo)));
        response.sendRedirect(ctxPath+"/talk_view_banana.jsp");
    }else if(vo.getOrange().equals(pwd)){ //guest
        response.addCookie(new Cookie("level","orange"));
        response.addCookie(new Cookie("roomNo",String.valueOf(roomNo)));
        response.sendRedirect(ctxPath+"/talk_view_orange.jsp");
    }else { //방 번호는 있지만 비밀번호가 틀림.
        response.sendRedirect(ctxPath +"/talk_login.jsp");
    }

%>