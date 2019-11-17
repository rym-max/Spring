<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/16
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:_FrontLayout>
    <jsp:attribute name="scripts">
        <script type="text/javascript">
            var v = app.vue({
                data: { detail: {}},
                loading: true,
                methods: {
                    getParams(){
                        var params = window.location.search;
                        return params;
                    },
                    redirectToHome(){
                        console.log("重定向去首页")
                        window.location.href='/Home/index.html';//
                    },
                    search() {
                        _self = this;
                        http.post('/Detail/Item?'+v.getParams()).then(function (result) {
                            if(result.success){
                                _self.detail = result.data;
                            }else {
                                //wait 3 second
                                setTimeout(v.redirectToHome,3000);
                            }
                        });
                    }
                }
            })

            v.searchChannel();
            v.search();
        </script>
    </jsp:attribute>
    <jsp:body>
        <!--主体内容开始-->
        <div class="content clr" id="page-content">
            <!--当前位置-->
            <div class="locat">
                当前文献所属区域：${fromRegion}
            </div>
            <!--专题列表-->
            <div class="content wit_bg clr">
                <div class="object-intro">
                    <div class="content_box01 gry_lineb">
                        <!--标题部分开始-->
                        <h5 class="til_h5">
                            <c:if test="${pageType eq 0}"><b class="tru">访问页面不存在</b></c:if>
                            <c:if test="${pageType > 0}"><b class="tru">{{detail.title}}</b></c:if>
                        </h5>

                        <c:if test="${pageType eq 1}">
                        <div class="type_icobox mt20" style="position:relative;">
                            <span class="writer"><b class="type-ico-id"></b><strong>作者：{{detail.creator}}</strong></span>
                            <span class="time"><b class="type-ico-id"></b><strong>{{detail.date}}</strong></span>
                        </div>
                        </c:if>

                        <c:if test="${pageType eq 2}">
                            <!--操作标题部分-->
                            <div class="opc_til" style="position:relative;">
                                <tt class="fl">
                                        <%--<a href="" class="read">在线阅读<b class="type_ico"></b></a>--%>
                                        <%--<a href="" class="downqw">下载全文<b class="type_ico"></b></a>--%>
                                    <a target="_blank" class="qwlink mr5" :href="{{detail.url}}">全文链接<b class="type_ico"></b></a>
                                    <%--<a v-if="" target="_blank" class="qwfj" :href="">全文附件<b class="type_ico"></b></a>--%>
                                </tt>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${pageType eq 0}">
                        <!--内容列表部分开始-->
                        <div class="mt20" style="padding: 0 75px 20px 75px;">
                            <div class="p_div">您所访问的页面不存在！</div>
                        </div>
                    </c:if>
                    <c:if test="${pageType eq 1}">
                    <!--内容列表部分开始-->
                    <div class="mt20" style="padding: 0 75px 20px 75px;">
                        <div class="p_div">description</div>
                        <div class="p_div">
                            <strong>主题：</strong>
                            <a href="javascript:;">{{detail.subject}}</a>
                        </div>
                        <div class="p_div">
                            <strong>来源：</strong>
                            <a target="_blank" :href="{{detail.url}}">{{detail.source}} （原文链接）</a>
                        </div>
                    </div>
                    </c:if>

                    <c:if test="${pageType eq 2}">
                        <!--内容列表部分开始-->
                        <div class="ml75 mt20 txt_list">
                            <div class="p_div">
                                <p v-if="detail.creator != ''">
                                    <strong>作　　者：</strong>
                                    <span>{{detail.creator}}</span>//dc,creator
                                </p>
                                <p v-if="detail.subject != ''">
                                    <strong>主　　题：</strong>
                                    <span>{{detail.subject}}</span>//dc.subject
                                </p>
                                <p v-if="detail.organ != ''">
                                    <strong>作者单位：</strong>
                                    <span>{{detail.organ}}</span>//dc.organ
                                </p>
                                <p v-if="detail.publishDate != ''">
                                    <strong>发布时间：</strong>
                                    <span>{{detail.publishDate}}</span>//dc.date.issued
                                </p>
                                <p v-if="detail.source != ''">
                                    <strong>出　　处：</strong>
                                    <span>{{detail.source}}</span>//dc.source
                                </p>
                                <p v-if="detail.description != ''">
                                    <strong>摘　　要：</strong>
                                    <span>{{detail.description}}</span>//dc.description
                                </p>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>

        </div>
    </jsp:body>
</t:_FrontLayout>
