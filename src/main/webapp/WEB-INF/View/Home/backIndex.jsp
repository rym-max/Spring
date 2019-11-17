<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_Layout>
<jsp:attribute name="scripts">

</jsp:attribute>
<jsp:body>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-sm-4">
            <h2>控制台</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/Back/index.html">首页</a>
                </li>
                <li class="active">
                    <strong>控制台</strong>
                </li>
            </ol>
        </div>
        <div class="col-sm-8">
            <%--<div class="title-action">
            <a href="" class="btn btn-primary">This is action area</a>
        </div>--%>
        </div>
    </div>

    <div class="wrapper wrapper-content">
        <div class="middle-box text-center animated fadeInRightBig">
            <h3 class="font-bold">欢迎登录同济大学特色专题智库后台管理系统</h3>
            <div class="error-desc">
                <%--You can create here any grid layout you want. And any variation layout you imagine:) Check out
                main dashboard and other site. It use many different layout.--%>
                <%--<br /><a href="Home/index.html" class="btn btn-primary m-t">控制台</a>--%>
            </div>
        </div>
    </div>

</jsp:body>
</t:_Layout>