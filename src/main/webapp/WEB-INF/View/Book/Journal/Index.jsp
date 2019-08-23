<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_Layout>
    <jsp:attribute name="scripts">
    <script type="text/javascript">
        var v = app.vue({
            data: { data: {} },
            loading: true,
            methods: {
                pagination: function (total) {
                    layui.use(['laypage'], function () {
                        var laypage = layui.laypage;
                        laypage.render({
                            elem: 'pagination'
                            , count: total
                            , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                            , jump: function (obj, first) {
                                if (!first)
                                    v.search(obj.curr, obj.limit, false);
                            }
                        });
                    })
                },
                search(page, rows, init) {
                    _self = this;
                    http.post('/Book/Journal/Search', {
                        page: page-1,
                        rows: rows,
                        filter: [{ name: "Name", value: $("#search_name").val(), exp: "like" }]

                    }).then(function (result) {
                        _self.data = result.data;
                        if (init)
                            v.pagination(_self.data.total);
                    });
                },
                stat() {
                    _self = this;
                    http.post('/Book/Journal/StatisticsFwCount').then(function (result) {
                        if (result.success) {
                            swal({
                                title: result.message,
                                type: "success"
                            }, function () {
                                setTimeout(function () {
                                    v.search(1, 10, true);
                                    //window.location.reload();
                                }, 500);//延时0.1秒，对应360 7.1版本bug
                            });

                        } else {
                            swal({
                                title: result.message,
                                text: result.error,
                                type: "error"
                            });
                        }
                    });
                }
            }
        })
        v.search(1, 10, true);
    </script>
    </jsp:attribute>
    <jsp:body>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>期刊管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/Home/index.html">首页</a>
                    </li>
                    <li class="active">
                        <strong>期刊管理</strong>
                    </li>
                </ol>
            </div>
            <div class="col-sm-8">
                <div class="title-action">
                    <a href="javascript:;" v-on:click="stat()" class="btn btn-primary" title="添加期刊" height="750px"><i class="fa fa-line-chart"></i> 统计发文</a>
                    <a href="./Edit" class="btn btn-primary openWindow" title="添加期刊" height="750px"><i class="fa fa-plus"></i> 添加期刊</a>
                </div>
            </div>
        </div>

        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="row pull-right" style="margin-bottom:20px;">
                                        <div class="col-lg-12">
                                            <form role="form" class="form-inline">
                                                <div class="input-group">
                                                    <label for="search_name" class="sr-only">刊名：</label>
                                                    <input placeholder="刊名" id="search_name" class="form-control" type="text">
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-primary" type="button" v-on:click="search(1);"><i class="fa fa-search"></i> 搜索</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                                <%--<th width="100px">封面</th>--%>
                                            <th>刊名</th>
                                            <th>ISSN</th>
                                            <th>发文量</th>
                                            <th>发文作者</th>
                                            <th>发文机构</th>
                                            <th width="200px;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody v-cloak>
                                        <tr v-for="(row,index) in data.list">
                                                <%--<td><img alt="image" style="height:80px;" src="http://localhost:13142/Content/images/qk01.jpg"></td>--%>
                                            <td>{{row.name}}</td>
                                            <td>{{row.issn}}</td>
                                            <td>{{row.zpCount}}</td>
                                            <td>{{row.author}}</td>
                                            <td>{{row.organ}}</td>
                                            <td class="center">
                                                <a type="button" class="btn btn-primary btn-xs mbs openWindow" height="550px" :href="'./Edit/'+row.id">
                                                    <i class="fa fa-edit"></i>&nbsp; 编辑
                                                </a>&nbsp;
                                                <a class="btn btn-danger btn-xs mbs ajax" :href="'./Delete/'+row.id">
                                                    <i class="fa fa-trash-o"></i>&nbsp; 删除
                                                </a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="pagination" class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:_Layout>
