<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/19
  Time: 22:53
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
            search: function (page,init) {
                $.ajax({
                    url: "/Home/JournalList/Search",
                    type: "POST",
                    dataType: "JSON",
                    contentType:"application/json",
                    data: JSON.stringify({ page: page-1, sort: [{ name: "Id", asc: true }] }),
                    success: function (data) {
                        vm.items = data.data;
                        if(init){
                            $('.pagination').jqPaginator({
                                totalPages: data.data.pageCount,
                                totalCounts: data.data.total,
                                visiblePages: 10,
                                currentPage: data.data.page+1,
                                onPageChange: function (page, type) {
                                    if (type == "change") {
                                        vm.search(page, false);
                                    }
                                }
                            });
                        }

                    }
                });
            }
        })
        avalon.filters.description = function (value) {
            var reTag = /<(?:.|\s)*?>/g;
            value = value.replace(reTag, "");
            return value.length > 200 ? value.substring(0, 200) : value;
        }
        vm.search(1, true);
        $(function () {
            $("#navigation_home").removeClass("nav_hover");
            $("#navigation_journal").addClass("nav_hover");
        })
    </script>
    </jsp:attribute>
    <jsp:body>
        <!--主体内容开始-->
        <div class="content clr" :controller="app">
            <!--当前位置-->
            <div class="locat">
                您现在所在的位置：<a class="" href="/Home/index.html">网站首页</a> > <a class="current" href="">期刊列表</a>
            </div>
            <!--专题列表-->
            <div class="content_boxM mt10 wit_bg clr">
                <div class="search-result-list search_list search_list_02">
                    <dl  ms-for="($index,item) in items.list">
                        <dt style="position:relative;">
                            <a class="toe" target="_blank" ms-attr="item.href">{{item.name}}</a>
                            <tt class="i_fx s-share">
                                <img src="/static/front/images/icon_fx.png" alt="" width="14" height="14" />
                                <em class="s-share-list">
                                    <a target="_blank" ms-attr=""><i class="icon-s4"></i></a>
                                    <a target="_blank" ms-attr=""><i class="icon-s6"></i></a>
                                    <a target="_blank" ms-attr=""><i class="icon-s2"></i></a>
                                    <a target="_blank" ms-attr=""><i class="icon-s3"></i></a>
                                    <a target="_blank" ms-attr=""><i class="icon-s1"></i></a>
                                    <a target="_blank" ms-attr=""><i class="icon-s5"></i></a>
                                </em>
                            </tt>
                        </dt>
                        <dd class="paper-preview permissinimg">
                            <img ms-if="item.cover==null || item.cover==''" ms-attr="{src:'http://image.cqvip.com/vip1000/qkclearimg/'+item.gch+'/'+item.gch+'.jpg'}" alt="" width="120" height="160" />
                            <img ms-if="item.cover!=null && item.cover!=''" ms-attr="{src:'/static/front/image/'+item.cover}" alt="" width="120" height="160" />
                        </dd>
                        <dd>
                            <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：{{item.zpCount}}</strong></span>
                        </dd>
                        <dd>
                            <a target="_blank" ms-attr="{href:item.href}">{{item.description|description}}</a>
                        </dd>
                        <dd class="more_dd mt10">
                            <a target="_blank" ms-attr="{href:item.href}" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>
                </div>
                <!--翻页部分开始-->
                <ul class="pagination" id="pagination"></ul>
            </div>
        </div>
    </jsp:body>

</t:_FrontLayout>