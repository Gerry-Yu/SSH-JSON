<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSHDemo</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/json2.js"></script>

    <script>
        $(document).ready(function () {
            $("#b1").click(function () {
                $.ajax({
                    type:"post",
                    url:"addUserAction",
                    data:{userId:$("#userId").val(), username:$("#username").val(), password:$("#password").val()},
                    dateType:"json",
                    success:function (data) {
                        if (data.result == "false") {
                            alert("插入失败");
                        } else {
                            alert("插入成功");
                        }
                    }
                })
            })
            $("#b2").click(function(){
                $.ajax({
                    type:"post",
                    url:"queryUserAction",
                    data:{first:$("#first").val(), offset:$("#offset").val()},
                    dataType:"json",
                    success:function (data) {
                        $("#tab").html("");
                        $.each(data.userList, function (index, field) {
                            $("#tab").append("<tr> <td>"+field.userId+"</td><td>"+ field.username+"</td><td>"+field.password+"</td></tr>");
                        })
                    },
                    error:function () {
                        alert("Error");
                    }
                });
            });
        })
    </script>
</head>
<body>

        <input type="text" id="userId">
        <input type="text" id="username">
        <input type="password" id="password">
        <button id = "b1">插入</button>


        <input type="text" id="first">
        <input type="text" id="offset">
        <button id = "b2">查询</button>
        <table id="tab" border="0"></table>
</body>
</html>
