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
<html>
<head>
</head>
<body>
    <table border="1" cellpadding="12" cellspacing="0">
    <%
        for(int i = 1 ; i <= 9 ; i++){
    %>
        <tr>
        <%
            for(int j = 1; j<=9; j++){
        %> <td bgcolor="#<%=getColor()%>">
            <%=String.format("%d * %d = %d",i,j,i*j)%>
            </td>
        <%
            }
        %>
        </tr>
    <%
        }
    %>
    </table>
</body>
</html>
