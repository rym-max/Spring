<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/8/4
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_Layout>

<jsp:attribute name="scripts">
<script type="text/javascript">
    var timer;
    var v = app.vue({
        data: { data: {} },
        methods: {
            init(){
                _self=this;
                http.post('/Solr/Progress').then(function (result) {
                    _self.data = result;
                    if (result.status == 1) {
                        timer = window.setInterval(function () {
                            v.search();
                        }, 5000);
                    }
                    if (result.percent >= 100) {
                        window.clearInterval(timer);
                    }
                });
            },
            search() {
                _self = this;
                http.post('/Solr/Progress').then(function (result) {
                    _self.data = result;
                    if (result.percent >= 100) {
                        window.clearInterval(timer);
                    }
                })
            },
            createIndex() {
                _self = this;
                http.post('/Solr/CreateIndex').then(function (result) {
                    _self.data = result;
                    if (result.success) {
                        swal({
                            title: "开始创建索引！",
                            type: "success"
                        }, function () {

                            timer = window.setInterval(function () {
                                v.search();
                            }, 5000);
                        })
                    } else {
                        swal({
                            title: "创建索引失败！",
                            text: result.error,
                            type: "error"
                        })
                    }
                })
            }
        }
    })
    v.init();
</script>

</jsp:attribute>
    <jsp:body>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>文献管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/Home/index.html">首页</a>
                    </li>
                    <li>自建库管理</li>
                    <li class="active">
                        <strong>索引管理</strong>
                    </li>
                </ol>
            </div>
            <%--<div class="col-sm-8">--%>
                <%--<div class="title-action">--%>
                    <%--<a href="http://www.baidu.com" class="btn btn-primary openWindow" title="创建文献"><i class="fa fa-plus"></i> 文献管理</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>

        <div class="wrapper wrapper-content">
            <div class="row" style="margin-bottom:20px;">
                <div class="col-lg-12">
                    <button class="btn btn-primary" onclick="v.createIndex()">创建索引</button>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <h5 v-if="data.status==1">正在创建索引,已完成{{data.percent}}%</h5>
                            <h5 v-else>索引进度条</h5>

                            <div class="progress progress-striped">
                                <div :style="'width: '+data.percent+'%'" :aria-valuemax="data.totalCount" aria-valuemin="0" :aria-valuenow="data.completeCount" role="progressbar" class="progress-bar progress-bar-primary">
                                    <span>{{data.percent}}% 已完成</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:_Layout>