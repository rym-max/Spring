<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 21:29
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
                    http.post("/User/Search/").then(function (result) {
                        _self.data = result.data;
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
                <h2>用户管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/Home/index.html">首页</a>
                    </li>
                    <li class="active">
                        <strong>用户管理</strong>
                    </li>
                </ol>
            </div>
            <div class="col-sm-8">
                <div class="title-action">
                    <a href="./Edit/" class="btn btn-primary openWindow" title="添加用户" height="750px"><i class="fa fa-plus"></i> 添加用户</a>
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
                                            <th>登录名</th>
                                            <th>姓名</th>
                                            <th>性别</th>
                                            <th>生日</th>
                                            <th>邮箱</th>
                                            <th>手机</th>
                                            <th>电话</th>
                                            <th width="200px;">注册时间</th>
                                            <th width="200px;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody v-cloak>
                                        <tr v-for="(row,index) in data">
                                            <td>{{row.loginName}}</td>
                                            <td>{{row.name}}</td>
                                            <td>{{row.sex}}</td>
                                            <td>{{row.birthday}}</td>
                                            <td>{{row.email}}</td>
                                            <td>{{row.mobilePhoneNumber}}</td>
                                            <td>{{row.phoneNumber}}</td>
                                            <td>{{row.createTime}}</td>
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