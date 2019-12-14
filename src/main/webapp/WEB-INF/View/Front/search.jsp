<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/12/13
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date" scope="page" />

<t:_FrontLayout>
<jsp:attribute name="scripts">
<style>
    a .highlight {
        background:#ff0000;
        color:#ffffff;
    }
</style>
<script src="/static/front/js/jquery.pagination.js"></script>
<script type="text/javascript">
    var vm = avalon.define({
        $id: "app",
        items: {},
        source: [],
        years: [],
        categorys: [],
        source_ids: {},
        years_ids: {},
        categorys_ids: {},
        sourceValues: function (name) {
            var checked = false;
            for (var i = 0; i < vm.source_ids.length; i++) {
                if (name == vm.source_ids[i]) {
                    checked = true;
                    break;
                }
            }
            return checked ? { value: name, checked: true } : { value: name };
        },
        yearValues: function (name) {
            var checked = false;
            for (var i = 0; i < vm.years_ids.length; i++) {
                if (name == vm.years_ids[i]) {
                    checked = true;
                    break;
                }
            }
            return checked ? { value: name, checked: true } : { value: name };
        },
        categoryValues: function(name){
            var checked = false;
            for(var i=0; i<vm.categorys_ids.length;i++){
                if(name == vm.categorys_ids[i]){
                    checked =true;
                    break;
                }
            }
            return checked ? { value: name, checked: true } : { value: name };
        }
    });
    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }
    var theRequest = GetRequest();

    avalon.filters.description = function (value) {
        var reTag = /<(?:.|\s)*?>/g;
        value = value.replace(reTag, "");
        value = value.length > 300 ? value.substring(0, 300) : value;

        var searchText = theRequest["s"];//获取你输入的关键字；
        // searchText = unescape(searchText);
        var keywords = searchText.split("[*]");
        for (var i = 0; i < keywords.length; i++) {
            if ($.trim(keywords[i]) == "") {
                continue;
            }
            var regExp = new RegExp(keywords[i].toUpperCase(), 'g');//创建正则表达式，g表示全局的，如果不用g，则查找到第一个就不会继续向下查找了；
            value = value.toUpperCase().replace(regExp, '<span class="highlight" >' + keywords[i].toUpperCase() + '</span>');//将找到的关键字替换，加上highlight属性；
        }
        searchText = $("#search_text").val();
        if (searchText != "") {
            regExp = new RegExp(searchText.toUpperCase(), 'g');
            value = value.toUpperCase().replace(regExp, '<span class="highlight" >' + searchText.toUpperCase() + '</span>');//将找到的关键字替换，加上highlight属性；
        }
        return value;
    };

    avalon.filters.categorysfilter = function(value){
        var category_ids = ["新闻媒体","原始文献","智库文章","学术论文"];

        value = category_ids[parseInt(value,10)-1];
        return value;
    };


    function search(page, init) {
        var query = theRequest["q"]==="*:*"?"":theRequest["q"];
        // query = unescape(query);
        var source_q = "";
        var source_ids = new Array();
        $(".cb_source:checked").each(function () {
            source_q += ((source_q == "" ? "" : " OR ") + "source:\"" + $(this).val() + "\"");
            source_ids.push($(this).val());
        });
        vm.source_ids = source_ids;
        var year_q = "";
        var years_ids = new Array();
        $(".cb_year:checked").each(function () {
            year_q += ((year_q == "" ? "" : " OR ") + "dateissued_year:\"" + $(this).val() + "\"");
            years_ids.push($(this).val());
        });
        vm.years_ids = years_ids;

        var category_q = "";
        var category_ids = new Array();
        $(".cb_category:checked").each(function () {
            category_q += ((category_q == "" ? "" : " OR ") + "category:\"" + $(this).val() + "\"");
            category_ids.push($(this).val());
        });
        vm.categorys_ids = category_ids;


        if (source_q != "") {
            query += (" AND (" + source_q + ")");
        }

        if (year_q != "") {
            query += (" AND (" + year_q + ")");
        }

        if (category_q != "") {
            query += (" AND (" + category_q + ")");
        }

        if ($("#search_text").val() != "") {
            var value = $("#search_text").val().replace(/ /ig, "\\ ");
            query += (" AND (" + $("#search_field").val() + ":" + value + " OR " + $("#search_field").val() + "_ss:*" + value + "*)");
        }

        query += (" AND (dateissued_year:[" + $("#search_start_year").val() + " TO " + $("#search_end_year").val() + "])");

        $.ajax({
            url: "/Home/Search/data",
            type: "POST",
            dataType: "JSON",
            data: "q=" + query + "&page=" + page,
            success: function (data) {
                vm.items = data.data.items;
                vm.source = data.data.cluster["source_filter"];
                vm.years = data.data.cluster["dateissued_filter"];
                vm.categorys = data.data.cluster.category;
                if (init) {
                    $('.pagination').jqPaginator({
                        totalPages: data.data.items.pageCount,
                        totalCounts: data.data.items.total,
                        visiblePages: 10,
                        currentPage: data.data.items.page,
                        onPageChange: function (page, type) {
                            if (type == "change") {
                                search(page, false);
                            }
                        }
                    });
                }
            }
        });
    }

    $(function () {
        search(1, true);
    })
</script>
</jsp:attribute>
<jsp:body>
    <!--主体内容开始-->
    <div class="content clr" :controller="app">
        <!--当前位置-->
        <!--当前位置-->
        <div class="locat">
            您现在所在的位置：<a  href="/Home/index.html">网站首页</a> > <a class="current"  href="javascript:;">搜索结果</a>
        </div>
        <!--专题列表-->
        <div class="content wit_bg clr">
            <%--<div class="list_tip clr"><tt class="fl">检索条件：“任意字段=建筑”</tt><tt class="fr">共 186,904 条 记 录，以下是1-10</tt></div>--%>
            <div class="content_boxM wit_bg fl">
                <!--左侧-->
                <div class="w280 mt10 fl">
                    <!--搜索框部分-->
                    <div class="left_search wit_bg clr">
                        <div class="wfull fl">
                            <div class="fl w100boxw">
                                <div class="rule-single-select">
                                    <select id="search_field">
                                        <option value="search_text">全部</option>
                                        <option value="title">标题</option>
                                        <option value="author">作者</option>
                                        <option value="subject">关键词</option>
                                        <option value="dc.description">摘要</option>
                                    </select>
                                </div>
                            </div>
                            <input class="fl" type="text" id="search_text" placeholder="请输入搜索内容" />
                        </div>
                        <div class="wfull mt15 fl">
                            <label class="fl">时间：</label>
                            <div class="fl w100boxw">
                                <div class="rule-single-select">
                                    <select id="search_start_year">
                                        <option value="0">全部</option>
                                        <c:forEach var="item" varStatus="status" begin="0" end="29">
                                            <option value="${now.year-status.index+1900}">${now.year-status.index+1900}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <label class="fl pl10">至</label>
                            <div class="fr w100boxw">
                                <div class="rule-single-select" style="margin-right:0px;">
                                    <select id="search_end_year">
                                        <option value="9999">全部</option>
                                        <c:forEach var="item" varStatus="status" begin="0" end="29">
                                            <option value="${now.year-status.index+1900}">${now.year-status.index+1900}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="wfull mt15 tc fl">
                            <button class="radius2" onclick="search(1, true);">结果中检索</button>
                        </div>
                    </div>

                    <!--类型 部分开始-->
                    <div class="w280 wit_bg mt10 clr">
                        <!--标题部分开始-->
                        <h2 class="til_h2"><span class="fl ml15">文献类型</span><a href="javascript:;" class="more fr"></a></h2>
                        <div class="cluster-group">
                            <div class="list">
                                <ul>
                                    <li ms-for="($index,item) in categorys">
                                        <tt>{{item.count}}篇</tt>
                                        <input type="checkbox" class="cb_category" onclick="search(1, true)" ms-attr="categoryValues(item.name)">
                                        <span ms-attr="{title:item.name}">{{item.name | categorysfilter}}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--来源 部分开始-->
                    <div class="w280 wit_bg mt10 clr">
                        <!--标题部分开始-->
                        <h2 class="til_h2"><span class="fl ml15">来源</span><a href="javascript:;" class="more fr"></a></h2>
                        <div class="cluster-group">
                            <div class="list">
                                <ul>
                                    <li ms-for="($index,item) in @source">
                                        <tt>{{item.count}}篇</tt>
                                        <input type="checkbox" class="cb_source" onclick="search(1, true)" ms-attr="sourceValues(item.name)">
                                        <span ms-attr="{title:item.name}">{{item.name=="bmwi.de"?"Bundesministerium für Wirtschaft und Energie":item.name}}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--年份 部分开始-->
                    <div class="w280 wit_bg mt15 clr">
                        <!--标题部分开始-->
                        <h2 class="til_h2"><span class="fl ml15">出版年份</span><a href="javascript:;" class="more fr"></a></h2>
                        <div class="cluster-group">
                            <div class="list">
                                <ul>
                                    <li  ms-for="($index,item) in years">
                                        <tt>{{item.count}}篇</tt>
                                        <input type="checkbox" class="cb_year" onclick="search(1, true)" ms-attr="yearValues(item.name)">
                                        <span>{{item.name}}年</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--右侧-->
                <div class="w890 wit_bg fr">
                    <ul class="pagination" id="pagination"></ul>
                    <div class="search-main">
                        <div class="search-result-list search_list search_list_01">
                            <dl ms-for="($index,item) in items.list">
                                <dt><a class="f20" target="_blank" ms-attr="{href: '/Home/Detail/page?id=' + item.id}" ms-html="item.title | description">}</a></dt>
                                <dd class="text_info">
                                    <a target="_blank" ms-attr="{href: '/Home/Detail/page?id=' + item.id}" ms-html="item.simpleDes"></a>
                                </dd>
                                <dd class="pabottm pabottm01">
                                <span class="writer pr10" ms-if="item.creator!=''">
                                    <b class="type-ico-id"></b>
                                    <strong>作者：</strong>
                                    <strong class="pr10"  ms-attr="{title:item.creator}">{{item.creator}}</strong>
                                </span>
                                    <span class="time"><b class="type-ico-id"></b><strong>{{item.date}}</strong></span>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <!--翻页部分开始-->
                    <ul class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>


</jsp:body>
</t:_FrontLayout>