<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:_PopupLayout>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form action="/ERMS/Category/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${category.id}" />
                        <div class="form-group">
                            <label for="Name" class="col-sm-2 control-label">分类名称</label>
                            <div class="col-sm-10">
                                <input data-val="true" data-val-length="字段 分类名称 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Name" name="name" type="text" value="${category.name}" class="form-control" />
                                <span data-valmsg-for="Name" data-valmsg-replace="true" class="field-validation-valid"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ChannelId" class="col-sm-2 control-label">所属栏目</label>
                            <div class="col-sm-10">
                                <select data-val="true" data-val-number="字段 所属栏目 必须是一个数字。" data-val-required="所属栏目 字段是必需的。" id="ChannelId" name="channelId" onchange="v.search();" class="form-control">
                                    <option v-for="(row,index) in channels" :value="row.id" :selected="row.id==channelId?'selected':''">{{row.name}}</option>
                                </select>
                                <span data-valmsg-for="ChannelId" data-valmsg-replace="true" class="field-validation-valid"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ParentId" class="col-sm-2 control-label">上级分类</label>
                            <div class="col-sm-10">
                                <select id="ParentId" name="parentId" class="form-control">
                                    <option value="0">顶级分类</option>
                                    <option v-for="(row,index) in categorys" :value="row.id" v-if="row.id!=parentId" :selected="row.id==${category.parentId}?'selected':''">{{row.name}}</option>
                                </select>
                                <span data-valmsg-for="ParentId" data-valmsg-replace="true" class="field-validation-valid"></span>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label for="Status" class="col-sm-2 control-label">显示状态</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<input data-val="true" data-val-number="字段 显示顺序 必须是一个数字。" id="Status" name="status" type="text" value="${category.status}" class="form-control" />--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="Code" class="col-sm-2 control-label">分类编码</label>
                            <div class="col-sm-10">
                                <input data-val="true" data-val-length="字段 分类编码 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Code" name="code" type="text" value="${category.code}" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="QueryExpression" class="col-sm-2 control-label">查询表达式</label>
                            <div class="col-sm-10">
                                <textarea cols="20" data-val="true" data-val-length="字段 查询表达式 必须是最大长度为 1024 的字符串。" data-val-length-max="1024" id="QueryExpression" name="queryExpression" rows="2" class="form-control">
                                    ${category.queryExpression}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="col-sm-2 control-label">分类描述</label>
                            <div class="col-sm-10">
                                <textarea cols="20" id="Description" name="description" rows="2" class="form-control">
                                        ${category.description}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Sort" class="col-sm-2 control-label">显示顺序</label>
                            <div class="col-sm-10">
                                <input data-val="true" data-val-number="字段 显示顺序 必须是一个数字。" data-val-required="显示顺序 字段是必需的。" id="Sort" name="sort" type="text" value="${category.sort}" class="form-control" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">


        var v = app.vue({
            loading: false,
            data: {
                channels: {},
                categorys: {},
                channelId: <c:if test="${!empty category.ownerChannel}">${category.ownerChannel.id}</c:if><c:if test="${empty category.ownerChannel}">1</c:if> ,
                parentId: <c:if test="${!empty category.ownerCategory}">${category.ownerCategory.id}</c:if><c:if test="${empty category.ownerCategory}">0</c:if>
            },
            methods: {
                init(){
                    _self = this;
                    http.post('/ERMS/Category/Search', {
                        filter: [{ name: "ChannelId", value: _self.channelId }, { name: "ParentId", value: 0 }],
                        sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                    }).then(function (result) {
                        _self.categorys = result;
                    })
                },
                search() {
                    _self = this;
                    _self.channelId = $("#ChannelId").val();
                    http.post('/ERMS/Category/Search', {
                            filter: [{ name: "ChannelId", value: _self.channelId }, { name: "ParentId", value: 0 }],
                            sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                    }).then(function (result) {
                        _self.categorys = result;
                    })
                },
                searchChannel() {
                    _self = this;
                    http.post('/ERMS/Channel/Search').then(function (result) {
                        _self.channels = result;
                    });
                }
            }
        })
        v.searchChannel();
        v.init();

    </script>
</t:_PopupLayout>