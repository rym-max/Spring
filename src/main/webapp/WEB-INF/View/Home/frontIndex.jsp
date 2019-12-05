<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/16
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_FrontLayout>
    <jsp:attribute name="scripts">
        <script type="text/javascript">
            var vm = avalon.define({
                $id: "app",
                pagelist1:{},
                pagelist2:{},
                pagelist3:{},
                pagelist4:{},
                pagelist5:{},
                search: function () {
                    $.ajax({
                        url: "/Home/homeList",
                        type: "GET",
                        dataType: "JSON",
                        success: function (result) {
                            if(result.success){
                                vm.pagelist1 = result.data.pagelist1;
                                vm.pagelist2 = result.data.pagelist2;
                                vm.pagelist3 = result.data.pagelist3;
                                vm.pagelist4 = result.data.pagelist4;
                                vm.pagelist5 = result.data.pagelist5;   
                            }
                        }
                    });
                }
            });


            vm.search();
        </script>
    </jsp:attribute>
    <jsp:body>
    <div class="content pt20 mb20 clr" :controller="app">
        <!--新闻部分-->
        <div class="content_box wit_bg clr">
        <!--广告轮播部分开始-->
            <div class="banner mt20 fl">
                <div class="banner_list">
                    <!--轮播图文部分-->
                    <ul class="banner_imgul">
                        <li ms-for="(name,row) in pagelist1" ms-if="name == '1a'">
                            <!--图片-->
                            <div class="ad_img"><img src="/static/front/images/ad01.jpg" /></div>
                            <!--类标-->
                            <div class="ad_text"><b>新闻媒体 </b></div>
                            <!--内容-->
                            <div class="banner_cont">
                                <h1>

                                    <a class="til_a" ms-attr="{href:'/Home/Detail/page?id='+row.id, title:row.title}" target="_blank">{{row.title}}</a>

                                </h1>
                                <a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank" class="banner_text">{{row.title}}</a>
                                <a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank" class="more_yellow ml10 mt10">READ MORE<span class="red vb">+</span></a>
                            </div>

                        </li>
                    </ul>
                </div>
            </div>
        <!--广告轮播部分结束-->
            <!--新闻列表-->
            <div class="news mt10 mb20 fr">
                <div class="list_box list_box01">
                    <ul class="clr">
                        <li ms-for="(name,row) in pagelist1" ms-if="name != '1a'">
                            <div class="new_date">
                                <b class="new_date_b">{{row.dateMonth}}</b>
                                <span class="new_date_span">{{row.dateYear}}</span>
                            </div>
                            <a class="toe fb mt20" ms-attr="{href:'/Home/Detail/page?id='+row.id, title:row.title}" target="_blank">{{row.title}}</a>
                            <a class="gry_a h44" ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.simpleDes}}</a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>

        <!--欧盟-->
        <div class="content pt20 clr">
            <h1 class="til_h1">
                <b>中国与欧盟</b>
                <sup class="">China and European Union</sup>
                <a href="/Home/Region/1" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl ms-for="(name,row) in pagelist2" ms-if="name == '1a'">
                        <dt><a target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                        <dd><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl ms-for="(name,row) in pagelist2" ms-if="name != '1a'" ms-attr="{class:(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')}">
                        <dt><a class="f14 toe" target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>


        <!--次区域-->
        <div class="content pt20 clr">
            <h1 class="til_h1">
                <b>中国与欧洲次区域</b>
                <sup class="">China and European Sub-Regions</sup>
                <a href="/Home/Region/2" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl class="" ms-for="(name,row) in pagelist3" ms-if="name == '1a'">
                        <dt><a target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                        <dd><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl ms-for="(name,row) in pagelist3" ms-if="name != '1a'" ms-attr="{class:(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')}">
                        <dt><a class="f14 toe" target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>


        <!--次区域-->
        <div class="content pt20 clr">
            <h1 class="til_h1">
                <b>中国与欧盟成员国</b>
                <sup class="">China and European Union's Member</sup>
                <a href="/Home/Region/3" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl ms-for="(name,row) in pagelist4" ms-if="name == '1a'">
                        <dt><a target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                        <dd><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl ms-for="(name,row) in pagelist4" ms-if="name != '1a'" ms-attr="{class:(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')}">
                        <dt><a class="f14 toe" target="_blank" href="'/Home/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>

        <!--欧洲其他国家-->
        <div class="content pt20 clr">
            <h1 class="til_h1">
                <b>欧洲其他国家</b>
                <sup class="">European Other Countries</sup>
                <a href="/Home/Region/4" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl ms-for="(name,row) in pagelist5" ms-if="name != '1a'">
                        <dt><a target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                        <dd><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl ms-for="(name,row) in pagelist5" ms-if="name != '1a'" ms-attr="{class:(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')}">
                        <dt><a class="f14 toe" target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a ms-attr="{href:'/Home/Detail/page?id='+row.id}" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date}}</strong></span>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>


        <%--<!--期刊论文-->--%>
        <%--<div class="content pt20 clr">--%>
            <%--<h1 class="til_h1">--%>
                <%--<b>学术论文</b>--%>
                <%--<sup class="">Academic Papers</sup>--%>
                <%--<a href="" class="more fr" target="_blank">MORE<i class="red">+</i></a>--%>
            <%--</h1>--%>
            <%--<div class="wfull mt10 pb10 wit_bg clr">--%>
                <%--<div class="search-result-list search_list">--%>

                    <%--<dl v-for="(row,key,index) in pagelist6" v-if="index <6" class="fl wthird01 h200">--%>
                        <%--<dt><a class="f20" target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>--%>
                        <%--<dd class="h100"><a target="_blank" href="'/Home/Detail/page?id='+row.id">row.simpleDes</a></dd>--%>
                        <%--<dd class="pabottm" style="width:300px; height:24px; overflow:hidden; padding-right:47px;">--%>
                            <%--<span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>--%>
                            <%--<span class="time"><b class="type-ico-id"></b><strong>日期：{{row.publishDate}}</strong></span>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>


                    <%--<dl v-for="(row,key,index) in pagelist6" v-if="index >5" class="fl gry_bg wfull_dl ml10">--%>
                        <%--<dt><a class="f20" target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}" :title="row.title">{{row.title}}</a></dt>--%>
                        <%--<dd><a target="_blank" ms-attr="{href:'/Home/Detail/page?id='+row.id}">{{row.simpleDes}}</a></dd>--%>
                        <%--<dd class="pabottm pabottm01 mt20">--%>
                            <%--<span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>--%>
                            <%--<span class="time"><b class="type-ico-id"></b><strong>日期：{{row.publishDate}}</strong></span>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>

                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>


    </div>
    </jsp:body>
</t:_FrontLayout>
