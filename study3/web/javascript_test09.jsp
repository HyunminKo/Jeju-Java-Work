<%--
  User: hyunminko
  Date: 2019-08-06
  Time: 15:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <img id="abcd" src="opera.png">
</body>
<script>
    var ls = ['opera','safari','ie','google','ff'];
    var op = document.getElementById("abcd");
    var index = 1
    op.onclick = function() {
        if(index >= ls.length)
            index=0
        op.src=ls[index++]+".png";

        // alert(ls[index++])
    }
</script>
</html>
