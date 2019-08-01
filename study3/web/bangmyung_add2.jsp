<%@ page import="study3.BangMyungDAO" %>
<%@ page import="study3.BangMyungDAO_OracleImpl" %>
<%@ page import="study3.BangMyungVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exception err = null;
    String sungjun_addr = request.getRemoteAddr();
    String gul = request.getParameter("gul");
    BangMyungDAO dao = new BangMyungDAO_OracleImpl();
    try{
        BangMyungVO vo = new BangMyungVO();
        vo.setGul(gul);
        dao.add(vo);
    }catch (Exception e){
        err = e;
    }
    if(err != null) {
        session.setAttribute("error",err);
        response.sendRedirect("/study3/error.jsp");
    }else {
        response.sendRedirect("/study3/bangmyung_list.jsp");
    }
%>