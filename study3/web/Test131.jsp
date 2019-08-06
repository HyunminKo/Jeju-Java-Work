<%--
  User: hyunminko
  Date: 2019-08-05
  Time: 12:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        .apple {
            background-color: #aabbcc;
            width:100px;
            height: 100px;
            position: absolute;
            left: 200px;
            top:200px;
            z-index: 3;
        }
        .banana {
            background-color: #ccddee;
            width: 100px;
            height: 100px;
            position: absolute;
            left: 150px;
            top: 150px;
            z-index: 2;
        }
    </style>
</head>
<body>
    <div class="apple">apple</div>
    <div class="banana">banana</div>
</body>
</html>
