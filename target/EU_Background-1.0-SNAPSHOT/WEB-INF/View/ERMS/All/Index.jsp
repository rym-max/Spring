<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/6
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:_Layout>
<jsp:attribute name="scripts">
<script type="text/javascript">

    var v = app.vue({
        data() {
            return {
                data: {},
                channels: {},
                categorys: {},
                searchKey: "",
                page: 1,
                rows: 10,
                init: true
            }
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
                var subject = $("#search_subject").val();
                var title = $("#search_title").val();
                var channel = $("#search_channel").val();
                var category = $("#search_category").val();
                var status = $("#search_status").val();
                var parameters = "page=" + _self.page + "&rows=" + _self.rows + "&title=" + title + "&subject=" + subject + "&status=" + status + "&channel=" + channel + "&category=" + category;
                http.post('/ERMS/All/Search?' + parameters).then(function (result) {
                    _self.data = result;
                    if (_self.init)
                        v.pagination(result.total);
                })
            },
            searchChannel() {
                _self = this;
                http.post('/ERMS/Channel/Search').then(function (result) {
                    _self.channels = result;
                });
            },
            selectChannel() {
                _self = this;
                http.post('/ERMS/Category/Search', {
                    filter: [{ name: "ChannelId", value: $("#search_channel").val() }],
                    sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                }).then(function (result) {
                    _self.categorys = result;
                });
            },
            selectAll() {
                $("input[type=checkbox][name=ids]").each(function () {
                    $(this).prop("checked", $("#select_all").prop("checked"));
                });
            },
            operation(action) {
                //swal({
                //    title: 'Ajax request example',
                //    text: 'Submit to run ajax request',
                //    type: 'info',
                //    showCancelButton: true,
                //    closeOnConfirm: false,
                //    showLoaderOnConfirm: true,
                //}, function () {
                //    setTimeout(function () {
                //        swal('Ajax request finished!');
                //    }, 2000);
                //});
                _self = this;
                if ($("input[type=checkbox][name=ids]:checked").length == 0) {
                    swal({
                        title: "提示信息",
                        text: "请选择要操作的的项!",
                        type: "error"
                    });
                    return false;
                }
                var ids = "";
                $("input[type=checkbox][name=ids]:checked").each(function () {
                    if (ids == "") {
                        ids = $(this).val();
                    } else {
                        ids += ("," + $(this).val());
                    }
                })
                swal({
                    title: "你确定要执行该操作?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    showLoaderOnConfirm: true,
                    closeOnConfirm: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        http.get("./Operation", {
                            ids: ids,
                            action: action
                        }).then(function (result) {
                            if (result.success) {
                                swal({
                                    title: result.message,
                                    type: "success"
                                }, function () {
                                    v.search(true);
                                    $("input[type=checkbox]:checked").prop("checked", false);
                                });

                            } else {
                                swal({
                                    title: result.message,
                                    text: result.error,
                                    type: "error"
                                });
                            }
                        }).catch(function (error) {

                        });
                    } else {

                    }
                });

            }
        }
        // ,
        // filters: {
        //     dateFun: function (value) {
        //     return ConvertJSONDateToJSDateObject(value, "yyyy-MM-dd");
        // }
        // }
    })
    v.searchChannel();
    v.search();
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
                        <strong>文献管理</strong>
                    </li>
                </ol>
            </div>
            <div class="col-sm-8">
                <div class="title-action">
                    <a href="./Edit" class="btn btn-primary openWindow" title="创建文献"><i class="fa fa-plus"></i> 创建文献</a>
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
                                    <div class="row" style="margin-bottom:20px;">
                                        <div class="col-lg-3">
                                            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')">
                                                <button class="btn btn-danger" v-on:click="operation(0)" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
                                                <button class="btn btn-primary" v-on:click="operation(1)" title="审核通过"><i class="fa fa-check"></i> 审核通过</button>
                                                <button class="btn btn-warning" v-on:click="operation(2)"  title="审核不通过"><i class="fa fa-close"></i> 审核不通过</button>
                                            </sec:authorize>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="row pull-right">
                                                <div class="col-lg-12">
                                                    <form role="form" class="form-inline">
                                                        <div class="form-group">
                                                            <label for="search_title" class="sr-only">题名：</label>
                                                            <input placeholder="题名" id="search_title" class="form-control" type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="search_subject" class="sr-only">关键字：</label>
                                                            <input placeholder="关键字" id="search_subject" class="form-control" type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <select id="search_status" class="form-control" onchange="v.setPage(1);">
                                                                <option value="">审核状态</option>
                                                                <option value="1">已通过</option>
                                                                <option value="0">未通过</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <select id="search_channel" class="form-control" onchange="v.selectChannel();">
                                                                <option value="">所属栏目</option>
                                                                <option :value="row.id" v-for="(row,index) in channels">{{row.name}}</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <select id="search_category" class="form-control" style="width:200px;">
                                                                <option value="">所属分类</option>
                                                                <option v-for="(row,index) in categorys" :value="row.id" :style="row.parentId==0?'':'padding-left:20px;'">{{row.name}}</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="btn btn-primary" type="button" v-on:click="setPage(1);"><i class="fa fa-search"></i> 搜索</button>
                                                        </div>
                                                            <%--<div class="input-group">
                                                            <div class="input-group-btn">
                                                                <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i> 搜索</button>
                                                            </div>
                                                        </div>--%>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" id="select_all" v-on:click="selectAll()" /></th>
                                            <th>题名</th>
                                            <th>所属栏目</th>
                                            <th>所属分类</th>
                                            <th>是否德国</th>
                                            <th>自动确定区域</th>
                                            <th>关键字</th>
                                            <th>修改时间</th>
                                            <th>审核状态</th>
                                            <th width="200px;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody v-cloak>
                                        <tr v-for="(row,index) in data.list">
                                            <td><input type="checkbox" name="ids" :value="row.id" /></td>
                                            <td>{{get_value(row._field,"dc.title")}}</td>
                                            <td>{{row.ownerChannel==null?"":row.ownerChannel.name}}</td>
                                            <td>{{row.ownerCategory==null?"":row.ownerCategory.name}}</td>
                                            <td>{{row.isGermany==1?"是":"否"}}</td>
                                            <td>{{row.isSolr==1?"是":"否"}}</td>
                                            <td>{{get_value_split(row._field,"dc.subject",",")}}</td>
                                            <td>{{row.modifyTime}}</td>
                                            <td v-html="row.status.code==1?'<label class=\'label label-primary\'>已通过</label>':'<label class=\'label label-danger\'>未通过</label>'"></td>
                                            <td class="center">
                                                <a type="button" class="btn btn-primary btn-xs mbs openWindow" height="80%" :href="'./Edit/'+row.id">
                                                    <i class="fa fa-edit"></i>&nbsp; 编辑
                                                </a>&nbsp;
                                                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')">
                                                    <a class="btn btn-danger btn-xs mbs ajax" :href="'./Delete/'+row.id">
                                                        <i class="fa fa-trash-o"></i>&nbsp; 删除
                                                    </a>
                                                </sec:authorize>
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
