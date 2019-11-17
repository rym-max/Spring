<%@ tag description="Background Management System Layout" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="scripts" required="false" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>同济大学中欧智库数据库</title>
    <link href="/static/front/js/layui/css/layui.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="/static/front/css/base.css" />
    <link rel="stylesheet" type="text/css" href="/static/front/css/itemBase.css" />
    <link rel="stylesheet" type="text/css" href="/static/front/css/selectDefault.css" />
    <link rel="stylesheet" type="text/css" href="/static/front/css/style.css" />
    <script type="text/javascript" src="/static/front/js/jquery.js"></script>
    <script src="/static/front/js/layui/layui.js"></script>
    <script src="/static/front/js/avalon/avalon.js"></script>
    <script type="text/javascript" charset="utf-8" src="/static/front/js/common.js"></script><!--下拉框、复选框等-->
    <style>
        ul{}
        ul li{ display:block;position: relative;}
        ul li:hover{}
        ul li ul li{height: 55px;background:#fff; width: 100%}
        /*关键一：将二级菜单设置为display：none;*/
        ul li ul{position: absolute; display: none; padding: auto}
        ul li ul li:hover{}
        /*关键二：在划过二级菜单从属的一级菜单时，设置为display:block;*/
        ul li:hover ul{display: block;}
        ul li ul li a{font-size:15px;font-weight: bold;height: 50px;}
    </style>
</head>
<body>
<div class="bg" id="pop_bg" style="display:none;"></div>
<!--单独覆盖在全局上方的内容1-->
<div class="pop canzhao01" id="pop_body" style="display:none;">
    <!--登录模块-->
    <div class="clr">
        <!--正文 部分开始-->

        <div class="xinxi clr">
            <div class="pop_til01 clr"><b class="fl">登录</b><a class="fr" href= "javascript:;" onclick="login()"><img src="/static/front/images/close_icon01.png" width="25" height="25" /></a></div>
            <div class="xinxi02 clr">
                <form action="" method="post" id="loginform">
                    <div class="form_box form_box02  clr">
                        <p>
                            <input class="name_input" id="loginName" type="text" name="username" placeholder="请输入登录名" />
                        </p>
                        <p>
                            <input class="password_input" id="loginPassword" type="password" name="password" placeholder="请输入登录密码" />
                        </p>
                        <p class="tip" id="login_tip"></p>
                        <p class="mt40"><input type="button" id="btn_login" value="登录" /></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--单独覆盖在全局上方的内容2-->
<div id="pop_hsearch" class="pop canzhao" style="display:none;">
    <!--高级检索模块-->
    <div class="clr" id="pop">
        <!--关闭按钮-->
        <div class="pop_close"><a href="javascript:;" onclick="hsearch()"><img src="/static/front/images/close_icon01.png" width="25" height="25" /></a></div>
        <!--标题 部分开始-->
        <div class="pop_til">
            <ul>
                <li><a href="javascript:;">高级检索</a></li>
            </ul>
        </div>
        <!--正文 部分开始-->
        <div class="xinxi xinxi01 clr">
            <div class="wfull clr pt10">
                <div class="form_box form_box01  clr">
                    <div class="select_div selt_box">
                        <div class="w120slect fl ml135">
                            <div class="rule-single-select">
                                <select name="search_field">
                                    <option value="search_text">全部字段</option>
                                    <option value="title">标题</option>
                                    <option value="subject">关键字</option>
                                    <option value="author">作者</option>
                                    <option value="description">摘要</option>
                                </select>
                            </div>
                        </div>
                        <input type="text" name="searchKey" class="w570 mr10" placeholder="请输入检索关键字" />
                        <div class="tianjia">
                            <a href="javascript:;" class="add_btngry" onclick="add(this);"></a>
                        </div>
                    </div>
                    <div class="select_div selt_box">
                        <div class="tianjia ml10">
                            <a href="javascript:;" class="less_btngry" onclick="less(this);"></a>
                        </div>
                        <div class="w70slect fl">

                            <div class="rule-single-select">
                                <select name="search_operator">
                                    <option value="AND">与</option>
                                    <option value="OR">或</option>
                                    <option value="NOT">非</option>
                                </select>
                            </div>
                        </div>
                        <div class="w120slect fl">
                            <div class="rule-single-select">
                                <select name="search_field">
                                    <option value="search_text">全部字段</option>
                                    <option value="title">标题</option>
                                    <option value="subject">关键字</option>
                                    <option value="author">作者</option>
                                    <option value="description">摘要</option>
                                </select>
                            </div>
                        </div>
                        <input type="text" name="searchKey" class="w570 mr10" placeholder="请输入检索关键字" />
                        <div class="tianjia">
                            <a href="javascript:;" class="add_btngry" onclick="add(this);"></a>
                        </div>
                    </div>
                    <div class="check_boxlist clr">
                        <div class="check_box">
                            <a href="" class="dropdown_til">
                                <b class="fl">资源类型</b>
                            </a>
                            <div class="chock_box01 borno fl">
                                <div class="rule-multi-porp">
                                    @*<input type="checkbox" name="category" id="category_0" value="0" /><label for="category_0">全部</label>*@
                                    <input type="checkbox" name="category" id="category_1" value="1" /><label for="category_1">新闻媒体</label>
                                    <input type="checkbox" name="category" id="category_2" value="2" /><label for="category_2">原始文献</label>
                                    <input type="checkbox" name="category" id="category_3" value="3" /><label for="category_3">智库报告</label>
                                    <input type="checkbox" name="category" id="category_4" value="4" /><label for="category_4">学术论文</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p class="tc bordno">
                        <input type="button" class="radius_btn blue_btn mr5" onclick="on_hsearch();" value="检索" />
                        <input type="reset" class="radius_btn" value="清除" />
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!--单独覆盖在全局上方的内容3-->
<div class="pop canzhao02" id="pop_share" style="display:none;">
    <!--分享模块-->
    <div class="clr">
        <!--正文 部分开始-->
        <div class="xinxi clr">
            <div class="pop_til01 clr"><b class="fl">分享到</b><a class="fr" href="javascript:;" onclick="share()"><img src="/static/front/images/close_icon01.png" width="25" height="25" /></a></div>
            <div class="xinxi03 clr">
                <div class="share-list">
                    <ul class="clr">
                        <li><a target="_blank" href=""><i class="icon-s4"></i></a></li>
                        <li><a target="_blank" href=""><i class="icon-s6"></i></a></li>
                        <li><a target="_blank" href=""><i class="icon-s2"></i></a></li>
                        <li><a target="_blank" href=""><i class="icon-s3"></i></a></li>
                        <li><a target="_blank" href=""><i class="icon-s1"></i></a></li>
                        <li><a target="_blank" href=""><i class="icon-s5"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="all" class="all clr">
    <!--顶部内容开始-->
    <div class="header header01 clr">
        <!--最顶部-->
        <div class="content TopPlate">
            <div class="fl topl">
                <a href="/Home/About">关于我们</a>
                <%--<a href="">网站地图</a>--%>
                <%--<div class="fl cord">--%>
                    <%--<a href="">APP下载</a>--%>
                    <%--<span class="cord_span"><img src="/static/front/images/ercord.jpg" alt="" width="82" height="82" /></span>--%>
                <%--</div>--%>
            </div>
            <div class="fr topr">
                <a href="javascript:;" onclick="share()" class="share_i fr"><img src="/static/front/images/icon_share.png" alt="分享" /></a>
                <a href="javascript:;" onclick="hsearch();" class="gaoji_a fr">高级检索</a>
                <!--检索-->
                <div class="searchTop fr mt10">
                    <div class="w160boxw">
                        <div class="rule-single-select">
                            <select id="search_category">
                                <option value="0">全部</option>
                                <option value="1">新闻媒体</option>
                                <option value="2">原始文献</option>
                                <option value="3">智库报告</option>
                                <option value="4">学术论文</option>
                            </select>
                        </div>
                    </div>
                    <%--<em class="fl line_s">|</em>--%>
                    <%--<div class="w50boxw fl">--%>
                        <%--<div class="rule-single-select ">--%>
                            <%--<select>--%>
                                <%--<option value="">中文</option>--%>
                                <%--<option value="选择一">英文</option>--%>
                                <%--<option value="选择二">德文</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <em class="fl line_s">|</em>
                    <div class="fl ml5">
                        <form id="searchForm" action="/Index/Search" method="get">
                        <input type="hidden" id="search_keywords_q" name="q" />
                        <input type="hidden" id="search_keywords_s" name="s" />
                        </form>
                        <input type="text" id="search_keywords" style="width:180px;" onkeydown="searchKeydown();" placeholder="请输入关键字" />
                        <input id="btnSearch" type="button" />
                    </div>
                </div>
            </div>
        </div>
        <!-- logo部分-->
        <div class="content TopMid clr">
            <div class="logo fl"><img src="/static/front/images/logo.png" alt="" /></div>
            <!--登录注册-->
            <div class="fr loginPlate">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE','ROLE_EDITOR')" var="isAuthorize">
                <a href="" class="login_pic">后台管理</a>
                <a href="" class="pl10">退出登录</a>
                </sec:authorize>
                <c:if test="${!isAuthorize}">
                    <a href="javascript:;" class="login_pic" onclick="login()">登录</a>
                </c:if>
            </div>
        </div>
        <!--导航部分-->
        <div class="content clr">
            <div class="nav fl">
                <div class="pr">
                    <ul class="clr">
                        <li id="navigation_home" class="nav_hover">
                            <a href="/">网站首页</a>
                        </li>
                        <li id="navigation_category_1">
                            <a href="">中国与欧盟</a>
                        </li>
                        <li id="navigation_category_2">
                            <a href="">中国与欧盟次区域</a>
                            <ul>
                                <li><a href="">中国与中东欧</a></li>
                                <li><a href="">中国与北欧</a></li>
                                <li><a href="">中国与南欧</a></li>
                                <li><a href="">“17+1”</a></li>
                            </ul>
                        </li>
                        <li id="navigation_category_3">
                            <a href="">中国与欧盟成员国</a>
                            <ul>
                                <li><a href="">中国与德国</a></li>
                                <li><a href="">中国与英国</a></li>
                                <li><a href="">中国与法国</a></li>
                                <li><a href="">中国与意大利</a></li>
                                <li><a href="">中国与西班牙</a></li>
                            </ul>
                        </li>
                        <li id="navigation_category_4">
                            <a href="">欧洲其他国家</a>
                            <ul>
                                <li><a href="">中国与俄罗斯</a></li>
                                <li><a href="">中国与土耳其</a></li>
                                <li><a href="">其他</a></li>
                            </ul>
                        </li>
                        <li id="navigation_website"><a href="/SiteList/index.html">网站列表</a></li>
                        <li id="navigation_journal"><a href="/JournalList/index.html">期刊列表</a></li>
                    </ul>
                </div>
            </div>
            <%--<a href="" class="help_a fr">帮助</a>--%>
        </div>
    </div>
    <!--顶部内容结束-->
    <!--主体内容开始-->
    <jsp:doBody />
    <!--底部内容部分开始-->
    <div class="footer clr">
        <div class="content pt30 clr">
            <div class="footer_left fl">
                <p class="f20">友情链接</p>
                <p><a href="http://www.tongji.edu.cn" target="_blank">同济大学</a></p>
                <p><a href="http://dgyj.tongji.edu.cn/" target="_blank">同济大学德意志联邦共和国问题研究所欧洲联盟研究所</a></p>
                <p><a href="http://german-studies-online.tongji.edu.cn/main.htm" target="_blank">同济大学德国研究在线</a></p>
                <p><a href="http://sies-cn.org/" target="_blank">上海欧洲学会</a></p>
                <p><a href="http://www.lib.tongji.edu.cn" target="_blank">同济大学图书馆</a></p>
                <p><a href="http://www.lib.tongji.edu.cn/site/tongji/d70263cd-1947-4a6c-9719-a58325f6eb33/info/2013/236d6f50-e76f-4a14-8c38-a88a0431914f.html" target="_blank">电子资源版权公告</a></p>
                <%--<p><a href="">更多+</a></p>--%>
            </div>
            <div class="footer_mid fl">
                <p class="f20">联系方式</p>
                <p><b>电  话：</b>021-65982200</p>
                <p><b>传  真：</b>021-65982200 </p>
                <%--<p><b>邮  箱：</b>tongjidaxue@163.com</p>--%>
                <p><b>地  址：</b>上海市四平路1239号</p>
                <p><b>邮  编：</b>200092</p>
            </div>
            <div class="footer_right fr">
                <p class="f24"><img src="//qr.api.cli.im/qr?data=http%253A%252F%252F202.120.162.24%253A8080&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=0ada3f8c1aa9b2fc0110e2e5ff75b9df" alt="" width="150" height="150" /></p>
                <p>扫一扫 手机浏览</p>
            </div>
        </div>
        <div class="footbootm mt30">同济大学特色专题智库   同济大学图书馆 版权所有  copyright©2017 all rights reserved</div>
    </div>
    <!--底部内容部分结束-->
</div>
<%--<script type="text/javascript" src="/static/front/js/PIE.js"></script>--%>
<%--<script type="text/javascript" src="/static/front/js/commonPIE.js"></script>--%>
<script type="text/javascript">
    function login() {
        $("#pop_bg").toggle();
        $("#pop_body").toggle();
    }
    function share() {
        $("#pop_bg").toggle();
        $("#pop_share").toggle();
    }
    function hsearch() {

        $("#pop_bg").toggle();
        $("#pop_hsearch").toggle();
    }
    function on_hsearch() {
        var fieldQuery = "";
        var index = 0;
        var keywords = "";
        $(".select_div").each(function () {
            var searchKey = $(this).find("input[name=searchKey]").val();
            if (searchKey != "") {
                var searchKey = encodeURIComponent(searchKey.replace(/ /ig, "\\ "));
                var operator = " AND ";
                if (index > 0) {
                    operator = " " + $(this).find("select[name=search_operator]").val() + " ";
                }
                var searchField = $(this).find("select[name=search_field]").val();
                if (fieldQuery == "") {
                    fieldQuery += ("(" + searchField + ":" + searchKey + " OR " + searchField + "_ss:*" + searchKey+"*)");
                } else {
                    fieldQuery += (operator + (" (" + searchField + ":" + searchKey + " OR " + searchField + "_ss:*" + searchKey + "*)"));
                }
                keywords = keywords == "" ? searchKey : (keywords + "[*]" + searchKey);
            }
            index++;
        });
        var categoryQuery = "";
        $(".check_boxlist input[type=checkbox][name=category]:checked").each(function () {
            if (categoryQuery == "") {
                categoryQuery = "category:" + $(this).val();
            } else {
                categoryQuery += (" OR category:" + $(this).val());
            }
        });

        var query = "";
        if (fieldQuery != "" && categoryQuery != "") {
            query = "(" + categoryQuery + ") AND (" + fieldQuery + ")";
        } else if (categoryQuery != "") {
            query = "(" + categoryQuery + ")";
        } else if (fieldQuery != "") {
            query = "(" + fieldQuery + ")";
        }
        $("#search_keywords_q").val(query);
        $("#search_keywords_s").val(encodeURIComponent(keywords));
        $("#searchForm").submit();
        //window.location.href = "@Url.Action("Index", "Search")" + (query == "" ? "" : "?q=" + query) + "&s=" + keywords;
    }
    $(function () {
        $("#loginform").keydown(function(event){
           if(event.keyCode=="13"){//keyCode=13是回车键
               $("#btn_login").click();
           }
        });

        $("#btn_login").click(function () {
            var loginName = $("#loginName").val();
            var loginPassword = $("#loginPassword").val();
            if (loginName == "") {
                $("#login_tip").html("登录名不能为空！");
                return;
            }
            if (loginPassword == "") {
                $("#login_tip").html("登录密码不能为空！");
                return;
            }
            $.ajax({
                type: 'POST',
                url: '/login',
                dataType: "json",
                data: { username: loginName, password: loginPassword },
                success: function (result) {
                    if (result.success) {
                        location.reload();
                    } else {
                        $("#login_tip").html(result.error);
                    }
                }
            });
        })

        $("#btnSearch").click(function () {
            var category = $("#search_category").val();
            var keywords = $("#search_keywords").val();
            var query = "";
            if (category != "0" && keywords != "") {
                query = "category:" + category + " AND search_text:" + encodeURIComponent("\"" + keywords + "\"");
            } else if (category != "0") {
                query = "category:" + category;
            } else if (keywords != "") {
                query = "search_text:" + encodeURIComponent("\"" + keywords + "\"");
            }
            $("#search_keywords_q").val(query);
            $("#search_keywords_s").val(encodeURIComponent(keywords));
            $("#searchForm").submit();
            //window.location.href = "@Url.Action("Index", "Search")" + query;
        });
    })

    function less(obj) {
        $(obj).parent().parent().remove();
    }
    function add(obj) {
        if ($(".select_div").length > 4) {
            return;
        }
        var html = '<div class="select_div selt_box">' +
            '<div class="tianjia ml10">' +
            '<a href="javascript:void(0);" class="less_btngry" onclick="less(this);"></a>' +
            '</div>' +
            '<div class="w70slect fl">' +
            '<div class="rule-single-select">' +
            '<select name="search_operator">' +
            '<option value="AND">与</option>' +
            '<option value="OR">或</option>' +
            '<option value="NOT">非</option>' +
            '</select>' +
            '</div>' +
            '</div>' +
            '<div class="w120slect fl">' +
            '<div class="rule-single-select">' +
            '<select name="search_field">' +
            '<option value="search_text">全部字段</option>' +
            '<option value="title">题名</option>' +
            '<option value="subject">关键字</option>' +
            '<option value="author">作者</option>' +
            '<option value="description">摘要</option>' +
            '</select>' +
            '</div>' +
            '</div>' +
            '<input type="text" name="searchKey" class="w570 mr10" placeholder="请输入检索关键字">' +
            '<div class="tianjia">' +
            '   <a href="javascript:void(0);" class="add_btngry" onclick="add(this);"></a>' +
            '</div>' +
            '</div>';
        $(obj).parent().parent().after(html);
        $(".rule-single-checkbox").ruleSingleCheckbox();
        $(".rule-multi-checkbox").ruleMultiCheckbox();
        $(".rule-multi-radio").ruleMultiRadio();
        $(".rule-single-select").ruleSingleSelect();
        $(".rule-multi-porp").ruleMultiPorp();
    }


</script>
<jsp:invoke fragment="scripts" />
</body>
</html>
