<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" scope="page" />

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>登录</title>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet" />
    <link href="/static/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">IN+</h1>

        </div>
        <h3>欢迎登录同济大学特色专题智库后台管理系统</h3>
        <form class="m-t" role="form" method="post">
            <div>
                <span id="showError" style="color: red"></span>
            </div>
            <div class="form-group">
                <input type="text" id="LoginName" name="username" class="form-control" placeholder="登录名" required="">
            </div>
            <div class="form-group">
                <input type="password" id="LoginPassword" name="password" class="form-control" placeholder="登录密码" required="">
            </div>
            <button type="button" id="BtnLogin" class="btn btn-primary block full-width m-b">立即登录</button>
        </form>
        <p class="m-t"> <small>同济大学图书馆 版权所有 copyright©<fmt:formatDate value="${now}" pattern="yyyy" /> all rights reserved</small> </p>
    </div>
</div>

<!-- Mainly scripts -->
<script src="/static/js/jquery-2.1.1.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/plugins/layui/layui.js"></script>
<script src="/static/js/plugins/axios/axios.min.js"></script>
<script src="/static/js/utils/http.js"></script>
<script type="text/javascript">
    $(function () {
        $("form").keydown(function (event) {
            if (event.keyCode == "13") {//keyCode=13是回车键
                $('#BtnLogin').click();
            }
        });
        $("#BtnLogin").click(function () {
            $("#showError").empty();
            var loginname = $("#LoginName").val();
            var loginpassword = $("#LoginPassword").val();
            if(loginname.length ==0 || loginpassword.length ==0){
                $("#showError").append("登录名或密码不可为空");
            }else {
                http.post('./login?' + $("form").serialize()).then(function (result) {
                    console.log(result)
                    if (result.success) {
                        console.log("登陆成功");
                        window.location.href = "Back/index.html";
                    } else {
                        console.log("登陆失败");
                        swal({
                            title: result.message,
                            text: result.error,
                            type: "error"
                        });
                    }
                })
            }
        });
    })
</script>
</body>

</html>
