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
            data: { channels: {}, categorys: {} },
            loading: true,
            methods: {
                selectChannel(e, id) {
                    if ($("#channel_id").val() == id) {
                        $(".text-navy").removeClass("text-danger");
                        $("#channel_id").val("");
                    } else {
                        $(".text-navy").removeClass("text-danger");
                        $(e.currentTarget).find(".text-navy").addClass("text-danger");
                        $("#channel_id").val(id);
                    }
                    v.search();
                },
                searchChannel() {
                    _self = this;
                    http.post('/ERMS/Channel/Search').then(function (result) {
                        _self.channels = result;
                    });
                },
                search() {
                    _self = this;
                    http.post('/ERMS/Category/Search', {
                        filter: [{ name: "ChannelId", value: $("#channel_id").val() }],
                        sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                    }).then(function (result) {
                        _self.categorys = result;
                    });
                }
            }
        })

        v.searchChannel();
        v.search();
    </script>
    </jsp:attribute>
    <jsp:body>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>分类管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/Home/index.html">首页</a>
                    </li>
                    <li>自建库管理</li>
                    <li class="active">
                        <strong>分类管理</strong>
                    </li>
                </ol>
            </div>
            <div class="col-sm-8">
                <div class="title-action">
                    <a href="./Edit" class="btn btn-primary openWindow" title="创建分类" height="600px"><i class="fa fa-plus"></i> 创建分类</a>
                </div>
            </div>
        </div>

        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-2">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <button class="btn btn-block btn-primary">选择栏目</button>
                            <input type="hidden" id="channel_id" value="" />
                            <ul class="category-list">
                                <li v-for="(row,index) in channels"><a v-on:click="v.selectChannel($event,row.id)" href="javascript:;" cid="2"> <i class="fa fa-circle text-navy"></i> {{row.name}}</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="row">
                                <div class="col-lg-12">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>名称</th>
                                            <th>所属栏目</th>
                                            <th>上级分类</th>
                                            <th>编码</th>
                                            <th width="200px;">显示顺序</th>
                                            <th width="300px;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody v-cloak>
                                        <tr  v-for="(row,index) in categorys">
                                            <td><div :style="row.ParentId==0?'':'padding-left:20px;'">{{row.name}}</div></td>
                                            <td>{{row.ownerChannel==null?"无":row.ownerChannel.name}}</td>
                                            <td>{{row.ownerCategory==null?"无":row.ownerCategory.name}}</td>
                                            <td>{{row.code}}</td>
                                            <td>{{row.sort}}</td>
                                            <td class="center">
                                                    <%--<a type="button" class="btn btn-info btn-xs mbs openWindow" height="600px" :href="'./Edit?ParentId='+row.id" v-if="row.ParentId==0">
                                                    <i class="fa fa-edit"></i>&nbsp; 创建子分类
                                                    </a>--%>
                                                <a type="button" class="btn btn-primary btn-xs mbs openWindow" height="600px" :href="'./Edit/'+row.id">
                                                    <i class="fa fa-edit"></i>&nbsp; 编辑
                                                </a>
                                                <a type="button" class="btn btn-danger btn-xs mbs ajax" :href="'./Delete/'+row.id">
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
