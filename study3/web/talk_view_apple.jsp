<%@ page import="study3.Util" %>
<%@ page import="study3.TalkVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  User: hyunminko
  Date: 2019-08-01
  Time: 11:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctxPath = request.getContextPath();

    Cookie[] cookies = request.getCookies();
    if(cookies == null){
        response.sendRedirect(ctxPath+"/talk_login.jsp");
        return;
    }
    int roomNo = -1;
    String level = null;
    for(int i = 0 ; i < cookies.length ;i++){
        if("level".equals(cookies[i].getName())){
            level = cookies[i].getValue();
        } else if("roomNo".equals(cookies[i].getName())){
            roomNo = Util.parseInt(cookies[i].getValue());
        }
    }

    if(level == null || !"apple".equals(level)){
        response.sendRedirect(ctxPath+"/talk_login.jsp");
        return;
    }

    List<TalkVO> rl = new ArrayList<>();

    try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
        String sql = "select * from talk_t where room_no = ? order by talk_no";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,roomNo);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            TalkVO vo = new TalkVO();
            vo.setRoomNo(rs.getInt("room_no"));
            vo.setTalkNo(rs.getInt("talk_no"));
            vo.setContent(rs.getString("content"));
            rl.add(vo);
        }
        rs.close();
        stmt.close();
        conn.close();
    }catch (Exception e){
        session.setAttribute("error",e);
        response.sendRedirect(ctxPath+"/error.jsp");
    }
    System.out.println(rl);
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <table border="1" cellpadding="12">
        <thead>
            <th width="100" align="center">Room No</th>
            <th width="100" align="center">Talk No</th>
            <th width="200" align="center">content</th>
            <th width="80" align="center">&nbsp</th>
        </thead>
        <tbody>
        <%
            int i = 0;
            for(TalkVO vo : rl){
                String color = (i++) % 2 == 0 ? "khaki":"lightpink";
        %>
            <tr bgcolor="<%=color%>">
                <td><%=vo.getRoomNo()%></td>
                <td><%=vo.getTalkNo()%></td>
                <td><%=vo.getContent()%></td>
                <td><a href="talk_del2.jsp?talkNo=<%=vo.getTalkNo()%>">삭제</a></td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <br/>
    <form method="POST" action="talk_add2.jsp">
        <textarea rows="5" cols="95" name="content"></textarea>
        <br/>
        <input type="submit"/>
    </form>
</body>
</html>
