<%@ tag description="Background Management System Layout" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="scripts" required="false" fragment="true" %>

<%--<sec:authorize access="isAuthenticated()">--%>
    <%--<sec:authentication property="principal" var="principal"/>--%>
<%--</sec:authorize>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>后台管理系统</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/static/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="/static/css/animate.css" rel="stylesheet" />
    <link href="/static/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <link href="/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet" />
    <link href="/static/css/plugins/iCheck/custom.css" rel="stylesheet" />
    <link href="/static/css/style.css" rel="stylesheet" />


    <!-- Mainly scripts -->
    <script src="/static/js/jquery-2.1.1.js"></script>
    <script src="/static/js/jquery.form.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/bootstrap-paginator.js"></script>
    <script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="/static/js/inspinia.js"></script>
    <script src="/static/js/plugins/pace/pace.min.js"></script>
    <script src="/static/js/plugins/layui/layui.js"></script>
    <script src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="/static/js/plugins/vue/vue.min.js"></script>
    <script src="/static/js/plugins/vue/pagination.js"></script>
    <script src="/static/js/plugins/axios/axios.min.js"></script>
    <script src="/static/js/utils/http.js"></script>
    <script src="/static/js/plugins/iCheck/icheck.min.js"></script>
    <script src="/static/js/jquery.pjax.js"></script>
    <script src="/static/js/app.js"></script>

</head>

<body class="">

<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                            <span>
                                <img alt="image" class="img-circle" src="/static/images/profile_small.jpg" />
                            </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs">
                                        <strong class="font-bold">
                                            <sec:authentication property="name"/>
                                        </strong>
                                    </span> <span class="text-muted text-xs block">
                                    <sec:authentication property="principal.loginName"/>
                                    <b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <%--<li><a href="profile.html">简介</a></li>
                            <li><a href="contacts.html">联系人</a></li>
                            <li><a href="mailbox.html">电子邮件</a></li>
                            <li class="divider"></li>--%>
                            <li><a href="/logout">退出登录</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">

                    </div>
                </li>
                <li class="active">
                    <a data-pjax href="/Home/index.html"><i class="fa fa-desktop"></i> 控制台</a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')" >
                <li>
                    <a data-pjax href="/Administrator/index.html">
                    <i class="fa fa-users"></i>
                    <span class="nav-label">系统管理员管理</span>
                    </a>
                </li>
                <li>
                    <a data-pjax href="/User/index.html">
                    <i class="fa fa-users"></i>
                    <span class="nav-label">用户管理</span>
                    </a>
                </li>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')">
                <li>
                    <a data-pjax href="/ERMS/Region/index.html">
                        <i class="fa fa-location-arrow"></i> 地区管理 <%--<span class="label label-primary pull-right">100</span>--%>
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/Channel/index.html">
                    <i class="fa fa-table"></i> 栏目管理
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/Channel/metadata.html">
                    <i class="fa fa-cog"></i> 栏目元数据设置
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/Category/index.html">
                    <i class="fa fa-th"></i> 分类管理
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/Category/metadata.html">
                    <i class="fa fa-cog"></i> 分类元数据设置
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/MetadataSchemaRegistry/index.html">
                    <i class="fa fa-th-large"></i> 元数据类型管理
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/MetadataFieldRegistry/index.html">
                    <i class="fa fa-th-list"></i> 元数据字段管理
                    </a>
                </li>
                </sec:authorize>
                <%--<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE','ROLE_EDITOR')">--%>
                <li>
                    <a data-pjax href="/ERMS/Item/index.html">
                    <i class="fa fa-file-pdf-o"></i> 文献管理 <%--<span class="label label-primary pull-right">100</span>--%>
                    </a>
                </li>
                <li>
                    <a data-pjax href="/ERMS/All/index.html">
                        <i class="fa fa-file-pdf-o"></i> 文献管理2 <%--<span class="label label-primary pull-right">100</span>--%>
                    </a>
                </li>
                <%--</sec:authorize>--%>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')">
                <li>
                    <a data-pjax href="/Spider/Config/index.html">
                        <i class="fa fa-bug"></i> 爬虫配置 <%--<span class="label label-primary pull-right">100</span>--%>
                    </a>
                </li>
                <li>
                    <a data-pjax href="/Spider/Item/index.html">
                        <i class="fa fa-bug"></i> 爬虫管理 <%--<span class="label label-primary pull-right">100</span>--%>
                    </a>
                </li>
                <li>
                    <a data-pjax href="/Solr/index.html">
                    <i class="fa fa-th-list"></i> 创建索引
                    </a>
                </li>
                <li>
                    <a data-pjax href="/Book/Journal/index.html">
                    <i class="fa fa-th-list"></i> 期刊管理
                    </a>
                </li>
                <li>
                    <a data-pjax href="/Log/AccessLog/index.html">
                    <i class="fa fa-area-chart"></i> 访问统计
                    </a>
                </li>
                <li>
                    <a data-pjax href="/Log/SpiderLog/index.html">
                        <i class="fa fa-area-chart"></i> 爬虫日志 <%--<span class="label label-primary pull-right">100</span>--%>
                    </a>
                </li>
                </sec:authorize>

                <%--<li>
                <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">自建资源管理</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a data-pjax href="@Url.Action("Index","Channel",new { area = "ERMS" })">栏目管理</a></li>
                    <li><a data-pjax href="@Url.Action("SetMetadata","Channel",new { area = "ERMS" })">栏目元数据设置</a></li>
                    <li><a data-pjax href="@Url.Action("Index","Category",new { area = "ERMS" })">分类管理</a></li>
                    <li><a data-pjax href="@Url.Action("SetMetadata","Category",new { area = "ERMS" })">分类元数据设置</a></li>
                    <li><a data-pjax href="@Url.Action("Index","MetadataSchemaRegistry",new { area = "ERMS" })">元数据注册类型管理</a></li>
                    <li><a data-pjax href="@Url.Action("Index","MetadataFieldRegistry",new { area = "ERMS" })">元数据字段管理</a></li>
                    <li><a data-pjax href="@Url.Action("Index","Item",new { area = "ERMS" })">文献管理 <span class="label label-primary pull-right">100</span></a></li>
                </ul>
                </li>--%>
            </ul>

        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" action="#">
                        <div class="form-group">
                            <input type="text" placeholder="搜索关键字..." class="form-control" name="top-search" id="top-search" />
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">欢迎登录后台管理系统.</span>
                    </li>
                    <%--<li class="dropdown">
                    <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope"></i>  <span class="label label-warning">16</span>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <div class="dropdown-messages-box">
                                <a href="profile.html" class="pull-left">
                                    <img alt="image" class="img-circle" src="/static/images/a7.jpg" />
                                </a>
                                <div class="media-body">
                                    <small class="pull-right">46h ago</small>
                                    <strong>Mike Loreipsum</strong> started following <strong>Monica Smith</strong>. <br />
                                    <small class="text-muted">3 days ago at 7:58 pm - 10.06.2014</small>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="dropdown-messages-box">
                                <a href="profile.html" class="pull-left">
                                    <img alt="image" class="img-circle" src="/static/images/a4.jpg" />
                                </a>
                                <div class="media-body ">
                                    <small class="pull-right text-navy">5h ago</small>
                                    <strong>Chris Johnatan Overtunk</strong> started following <strong>Monica Smith</strong>. <br />
                                    <small class="text-muted">Yesterday 1:21 pm - 11.06.2014</small>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="dropdown-messages-box">
                                <a href="profile.html" class="pull-left">
                                    <img alt="image" class="img-circle" src="/static/images/profile.jpg" />
                                </a>
                                <div class="media-body ">
                                    <small class="pull-right">23h ago</small>
                                    <strong>Monica Smith</strong> love <strong>Kim Smith</strong>. <br />
                                    <small class="text-muted">2 days ago at 2:30 am - 11.06.2014</small>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="text-center link-block">
                                <a href="mailbox.html">
                                    <i class="fa fa-envelope"></i> <strong>阅读所有邮件</strong>
                                </a>
                            </div>
                        </li>
                    </ul>
                </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-bell"></i>  <span class="label label-primary">8</span>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="mailbox.html">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> You have 16 messages
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="profile.html">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="grid_options.html">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a href="notifications.html">
                                        <strong>查看所有提示</strong>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>--%>


                    <li>
                        <a href="/logout">
                        <i class="fa fa-sign-out"></i> 退出登录
                        </a>
                    </li>
                </ul>

            </nav>
        </div>

        <div id="page-content">
            <jsp:doBody />
        </div>
        <div class="footer">
            <div class="pull-right">
                技术支持： <strong>同济大学宽带无线与多媒体实验室</strong>
            </div>

        </div>

    </div>
</div>
<jsp:invoke fragment="scripts"/>
</body>

</html>
