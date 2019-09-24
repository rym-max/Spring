<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/2
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:_Layout>
<jsp:attribute name="scripts">
<script type="text/javascript">
    var v = app.vue({
        data: {
            data: {},
            page:1,
            rows:10,
            init:true
        },
        loading: true,
        methods: {
            pagination: function (total, curr) {
                _self = this;
                layui.use(['laypage'], function () {
                    var laypage = layui.laypage;
                    laypage.render({
                        elem: 'pagination'
                        , count: total
                        , curr: _self.page
                        , limit: _self.rows
                        , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                        , jump: function (obj, first) {
                            if (!first) {
                                _self.rows = obj.limit;
                                _self.page = obj.curr;
                                _self.init = false;
                                v.search();
                            }
                        }
                    });
                })
            },
            setPage(page) {
                this.page = page;
                this.init = true;
                v.search();
            },
            search() {
                _self = this;
                var parameters = "page=" + _self.page +"&rows="+_self.rows
                http.post('/Spider/SpiderConfig/SearchPage?'+parameters).then(function (result) {
                    _self.data = result;
                    if(_self.init)
                        v.pagination(result.total)

                })
            }
        }
    })
    v.search();
</script>
</jsp:attribute>
<jsp:body>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-sm-4">
            <h2>爬虫配置管理</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/Home/index.html">首页</a>
                </li>
                <li>自建库管理</li>
                <li class="active">
                    <strong>爬虫配置管理</strong>
                </li>
            </ol>
        </div>
        <div class="col-sm-8">
            <div class="title-action">
                <a href="./Edit" class="btn btn-primary openWindow" title="创建爬虫配置" height="550px"><i class="fa fa-plus"></i> 创建爬虫配置</a>
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
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>名称</th>
                                        <th>创建者</th>
                                        <th>project</th>
                                        <th>spider</th>
                                        <th width="200px;">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody v-cloak>
                                    <tr v-for="(row,index) in data.list">
                                        <td>{{row.name}}</td>
                                        <td>{{row.creator}}</td>
                                        <td>{{row.project}}</td>
                                        <td>{{row.spider}}</td>
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
                                <div id="pagination" class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</jsp:body>
</t:_Layout>
