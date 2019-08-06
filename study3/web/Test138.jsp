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

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <input type="button" class="btn btn-lg" value="Click" data-toggle="modal" data-target="#apple"/>
    <div id="apple" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">this is title</h4>
                </div>
                <div class="modal-body">apple</div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" value="Close" data-dismiss="modal"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
