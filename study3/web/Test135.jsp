<%--
  User: hyunminko
  Date: 2019-08-05
  Time: 15:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style>
        @import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
        .custom-text, th, td, h1, h3, p { font-family:"Nanum Gothic";}
        .custom-text {
            padding: 12px;
            width:50%;
            position: relative;
            left:50%;
            transform: translate(-50%,0%);
        }
        .table {
            border: solid black 1px !important;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <table class="table table-bordered table-hover table-condensed">
            <thead>
                <th style="width: 80px">번호</th>
                <th style="width: 540px">내용</th>
                <th style="width: 120px">시간</th>
            </thead>
            <tbody>
                <tr class="">
                    <td>1</td>
                    <td>안녕하세요</td>
                    <td>1999-12-12 00:00:00</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>안녕하세요</td>
                    <td>1999-12-12 00:00:00</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>안녕하세요</td>
                    <td>1999-12-12 00:00:00</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
