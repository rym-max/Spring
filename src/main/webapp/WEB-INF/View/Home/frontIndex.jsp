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
            var v = app.vue({
                data: {
                    pagelist1:{},
                    pagelist2:{},
                    pagelist3:{},
                    pagelist4:{},
                    pagelist5:{},
                    // pagelist6:{},
                    topic1:{},
                    topic2:{},
                    topic3:{},
                    topic4:{},
                    topic5:{}
                },
                loading: true,
                methods: {
                    search(){
                        _self = this;
                        http.get('/homelist').then(
                            function(result){
                                if(result.success){
                                    _self.pagelist1 = result.data.pagelist1;
                                    _self.pagelist2 = result.data.pagelist2;
                                    _self.pagelist3 = result.data.pagelist3;
                                    _self.pagelist4 = result.data.pagelist4;
                                    _self.pagelist5 = result.data.pagelist5;
                                    // _self.pagelist6 = result.data.pagelist6;
                                    _self.topic1 = _self.pagelist1[0];
                                    _self.topic2 = _self.pagelist2[0];
                                    _self.topic3 = _self.pagelist3[0];
                                    _self.topic4 = _self.pagelist4[0];
                                    _self.topic5 = _self.pagelist5[0];
                                }
                            }
                        )
                    }
                },
                filters: {
                    dateMonth:function (value) {
                        return value.substring(5,7);
                    },
                    dateYear: function(value){
                        return value.substring(0,4);
                    }
                }
            })

            v.search();
        </script>
    </jsp:attribute>
    <jsp:body>
    <div class="content pt20 mb20 clr">
        <!--新闻部分-->
        <div class="content_box wit_bg clr">
        <!--广告轮播部分开始-->
            <div class="banner mt20 fl">
                <div class="banner_list">
                    <!--轮播图文部分-->
                    <ul class="banner_imgul">
                        <li>
                            <!--图片-->
                            <div class="ad_img"><img src="/static/front/images/ad01.jpg" /></div>
                            <!--类标-->
                            <div class="ad_text"><b>新闻媒体 </b></div>
                            <!--内容-->
                            <div class="banner_cont">
                                <h1>

                                    <a class="til_a" :href="'/Detail/page?id='+topic1.id" target="_blank" :title="topic1.title">{{topic1.title}}</a>

                                </h1>
                                <a :href="'/Detail/page?id='+topic1.id" target="_blank" class="banner_text">{{topic1.title}}</a>
                                <a :href="'/Detail/page?id='+topic1.id" target="_blank" class="more_yellow ml10 mt10">READ MORE<span class="red vb">+</span></a>
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
                        <li v-for="(row,index) in pagelist1.list" v-if="index > 0">
                            <div class="new_date">
                                <b class="new_date_b">{{row.date|dateMonth}}</b>
                                <span class="new_date_span">{{row.date|dateYear}}</span>
                            </div>
                            <a class="toe fb mt20" :href="'/Detail/page?'+row.id" target="_blank" :title="row.title">{{row.title}}</a>
                            <a class="gry_a h44" :href="'/Detail/page?'+row.id" target="_blank">{{row.simpleDes}}</a>
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
                <a href="/Region/0" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl class="">
                        <dt><a target="_blank" :href="'/Detail/page?id='+topic2.id" :title="topic2.title">{{topic2.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{topic2.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{topic2.date|dateYear}}</strong></span>
                        </dd>
                        <dd><a :href="'/Detail/page?id='+topic2.id" target="_blank">{{topic2.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a :href="'/Detail/page?id='+topic2.id" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl v-for="(row,index) in pagelist2" v-if="index>0" :class="(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')">
                        <dt><a class="f14 toe" target="_blank" :href="'/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a :href="'/Detail/page?id='+row.id" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{v.getYear(row.date)}}</strong></span>
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
                <a href="/Region/1" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl class="">
                        <dt><a target="_blank" :href="'/Detail/page?id='+topic3.id" :title="topic3.title">{{topic2.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{topic3.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{topic3.date|dateYear}}</strong></span>
                        </dd>
                        <dd><a :href="'/Detail/page?id='+topic3.id" target="_blank">{{topic3.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a :href="'/Detail/page?id='+topic3.id" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl v-for="(row,index) in pagelist3" v-if="index>0" :class="(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')">
                        <dt><a class="f14 toe" target="_blank" :href="'/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a :href="'/Detail/page?id='+row.id" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date|dateYear}}</strong></span>
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
                <a href="/Region/2" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl class="">
                        <dt><a target="_blank" :href="'/Detail/page?id='+topic4.id" :title="topic4.title">{{topic4.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{topic4.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{topic4.date|dateYear}}</strong></span>
                        </dd>
                        <dd><a :href="'/Detail/page?id='+topic4.id" target="_blank">{{topic4.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a :href="'/Detail/page?id='+topic4.id" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl v-for="(row,index) in pagelist4" v-if="index>0" :class="(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')">
                        <dt><a class="f14 toe" target="_blank" href="'/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a :href="'/Detail/page?id='+row.id" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date|dateYear}}</strong></span>
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
                <a href="/Region/3" class="more fr" target="_blank">MORE<i class="red">+</i></a>
            </h1>
            <div class="wfull clr">
                <div class="search-result-list search_list">

                    <dl class="">
                        <dt><a target="_blank" :href="'/Detail/page?id='+topic5.id" :title="topic4.title">{{topic5.title}}</a></dt>
                        <dd>
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{topic5.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{topic5.date|dateYear}}</strong></span>
                        </dd>
                        <dd><a :href="'/Detail/page?id='+topic5.id" target="_blank">{{topic5.simpleDes}}</a></dd>
                        <dd class="more_dd mt10">
                            <a :href="'/Detail/page?id='+topic5.id" target="_blank" class="more_yellow ">read MORE</a>
                        </dd>
                    </dl>

                    <dl v-for="(row,index) in pagelist5" v-if="index>0" :class="(index%3==0?'fr':'fl')+' wthird h160 '+(index==2?'ml9':'')">
                        <dt><a class="f14 toe" target="_blank" :href="'/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>
                        <dd class="h66"><a :href="'/Detail/page?id='+row.id" target="_blank">{{row.smallDes}}</a></dd>
                        <dd class="pabottm">
                            <span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>日期：{{row.date|dateYear}}</strong></span>
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

                    <%--<dl v-for="(row,index) in pagelist6" v-if="index <6" class="fl wthird01 h200">--%>
                        <%--<dt><a class="f20" target="_blank" :href="'/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>--%>
                        <%--<dd class="h100"><a target="_blank" href="'/Detail/page?id='+row.id">row.simpleDes</a></dd>--%>
                        <%--<dd class="pabottm" style="width:300px; height:24px; overflow:hidden; padding-right:47px;">--%>
                            <%--<span class="from mr5"><b class="type-ico-id"></b><strong>来源：{{row.source}}</strong></span>--%>
                            <%--<span class="time"><b class="type-ico-id"></b><strong>日期：{{row.publishDate}}</strong></span>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>


                    <%--<dl v-for="(row,index) in pagelist6" v-if="index >5" class="fl gry_bg wfull_dl ml10">--%>
                        <%--<dt><a class="f20" target="_blank" :href="'/Detail/page?id='+row.id" :title="row.title">{{row.title}}</a></dt>--%>
                        <%--<dd><a target="_blank" :href="'/Detail/page?id='+row.id">{{row.simpleDes}}</a></dd>--%>
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
