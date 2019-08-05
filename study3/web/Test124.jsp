<%--
  User: hyunminko
  Date: 2019-08-02
  Time: 13:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
int i = 0;
if(i == 0) {
    String test = request.getParameter("test");
    System.out.println(test);
    request.setAttribute("메세지","경고!!!");
    RequestDispatcher rd = request.getRequestDispatcher("/Test124_1.jsp");
    rd.forward(request,response);

}else {

    request.setAttribute("메세지","경고!!!");
    String ctxPath = request.getContextPath();
    response.sendRedirect(ctxPath+"/Test124_1.jsp");
}
%>
