<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/14
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:_FrontLayout>
    <jsp:attribute name="scripts">
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
                channel:1,
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
                },

                on_click:function(href, name,title) {
                    if (href != undefined) {
                        window.open(href[0]);
                    } else if (name != undefined) {
                        var url = "";
                        switch (name[0]) {
                            case "German Politics and Society":
                                url = "http://web.a.ebscohost.com/ehost/results?vid=2&sid=eac21373-437d-4aa7-99d7-9b03462d530f%40sessionmgr4007&bquery=" + title[0] + "&bdata=JmRiPWE5aCZ0eXBlPTAmc2l0ZT1laG9zdC1saXZl";
                                break;
                            case "JCMS: Journal of Common Market Studies":
                                url = "";
                                break;
                            case "Zeitschrift für Au?en- und Sicherheitspolitik":
                                url = "https://link.springer.com/search?query=" + title[0];
                                break;
                            case "West European Politics":
                                url = "http://web.a.ebscohost.com/ehost/results?vid=2&sid=eac21373-437d-4aa7-99d7-9b03462d530f%40sessionmgr4007&bquery=" + title[0] + "&bdata=JmRiPWE5aCZ0eXBlPTAmc2l0ZT1laG9zdC1saXZl";
                                break;
                            case "Journal of Common Market Studies":
                                url = "http://web.a.ebscohost.com/ehost/results?vid=2&sid=eac21373-437d-4aa7-99d7-9b03462d530f%40sessionmgr4007&bquery=" + title[0] + "&bdata=JmRiPWE5aCZ0eXBlPTAmc2l0ZT1laG9zdC1saXZl";
                                break;
                            case "German Politics":
                                url = "http://web.a.ebscohost.com/ehost/results?vid=2&sid=eac21373-437d-4aa7-99d7-9b03462d530f%40sessionmgr4007&bquery=" + title[0] + "&bdata=JmRiPWE5aCZ0eXBlPTAmc2l0ZT1laG9zdC1saXZl";
                                break;
                        }
                        if (url != "") {
                            window.open(url);
                        }
                    }
                }
            });

            avalon.filters.description = function (value) {
                var reTag = /<(?:.|\s)*?>/g;
                value = value.replace(reTag, "");
                value = value.length > 300 ? value.substring(0, 300) : value;
                var searchText = $("#search_text").val();
                if (searchText != "") {
                    var regExp = new RegExp(searchText.toUpperCase(), 'g');
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
                var query = "(status:1)";

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
                    var value = $("#search_text").val();
                    query += " AND (description:\"" + value+"\")";
                }


                var current_path = window.location.pathname
                console.log(current_path)
                var path_params = current_path.split("Region")[1];
                console.log(path_params)
                var dyna_url = "/Home/Region/dataList"+path_params;



                $.ajax({
                    url: dyna_url,
                    type: "POST",
                    dataType: "JSON",
                    data: "q=" + query + "&page=" + page,
                    success: function (data) {
                        // console.log(JSON.stringify(data));
                        vm.items = data.data.items;
                        // console.log(JSON.stringify(data.cluster));
                        vm.categorys = data.data.cluster.category;
                        vm.source = data.data.cluster.source_filter;
                        vm.years = data.data.cluster.dateissued_filter;
                        if (init) {
                            $.jqPaginator('.pagination',{
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
                $("#navigation_home").removeClass("nav_hover");
                $("#navigation_category_1").addClass("nav_hover");
            })
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="content clr">
            <div class="locat">
                您现在所在的位置：<a  href="/Home/index.html">网站首页</a> > <a class="current"  href="javascript:;">${region.nameCN}</a>
            </div>
            <!--专题列表-->
            <div class="content wit_bg clr">
                <div class="content_boxM clr">
                    <div class="zt_top gry_bg clr">
                        <div class="top_left fl">
                            <div class="zt_pic"><img src="/static/front/images${region.iconPath}" alt="" width="225" height="142" /></div>
                        </div>
                        <div class="top_right fr">
                            <div class="zt_tile" style="position:relative; width:730px;">
                                <span style="font-size:16px; font-weight:bold;">${region.nameCN}</span>
                            </div>
                            <div class="zt_intro" style="clear:both;"><strong class="vb">介绍：</strong>${region.description}</div>
                        </div>
                    </div>
                </div>
                <div class="line_fgbg"></div>
                <div class="content_boxM clr" ms-controller="app">
                    <!--左侧-->
                    <div class="w280 fl">
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
                        <!--搜索栏部分开始-->
                        <div class="search mt10 clr">
                            <!--搜索文本框开始-->
                            <div class="search_box clr">
                                <div class="search_text clr">
                                    <input class="input radius4 fl" type="text" name="search_text" id="search_text" placeholder="请输入需要的文字" />
                                    <input class="search-btn radius4 fl" type="button" value="检  索" name="" onclick="search(1, true);" />
                                </div>
                            </div>
                        </div>
                        <!--搜索栏部分结束-->

                        <ul class="pagination" id="pagination"></ul>
                        <div class="search-main">
                            <div class="search-result-list search_list search_list_01">
                                <dl ms-for="($index,item) in items.list">
                                    <dt><a class="f20" target="_blank" ms-attr="{href: '/Home/Detail/page?id=' + item.id}" ms-html="item.title | description"></a></dt>
                                    <dd class="text_info">
                                        <a target="_blank" ms-attr="{href: '/Home/Detail/page?id=' + item.id}" ms-html="item.description | description"></a>
                                    </dd>
                                    <dd class="pabottm">
                                        <span class="from"><b class="type-ico-id"></b><strong>来源：{{item.source}}</strong></span>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                        <!--翻页部分开始-->
                        <ul class="pagination"></ul>
                        <div id="show"></div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>


</t:_FrontLayout>