<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/2
  Time: 22:18
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
            search() {
                _self = this;
                http.post('/ERMS/Region/Search').then(function (result) {
                    _self.data = result;
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
            <h2>区域管理</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/Home/index.html">首页</a>
                </li>
                <li>自建库管理</li>
                <li class="active">
                    <strong>区域管理</strong>
                </li>
            </ol>
        </div>
        <div class="col-sm-8">
            <div class="title-action">
                <a href="./Edit" class="btn btn-primary openWindow" title="创建区域" height="550px"><i class="fa fa-plus"></i> 创建区域</a>
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
                                        <th>区域中文名</th>
                                        <th>区域英文名</th>
                                        <th>所属区域</th>
                                        <th>启用状态</th>
                                        <th width="200px;">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody v-cloak>
                                    <tr v-for="(row,index) in data">
                                        <td>{{row.name}}</td>
                                        <td>{{row.nameCN}}</td>
                                        <td>{{row.nameEN}}</td>
                                        <td>{{row.ownerRegion.name}}</td>
                                        <td>{{row.status.nameCN}}</td>
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</jsp:body>
</t:_Layout>