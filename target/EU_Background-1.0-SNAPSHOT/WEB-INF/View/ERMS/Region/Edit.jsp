<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/2
  Time: 22:18
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
                    <form action="/ERMS/Region/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${region.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Name">区域名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Name" name="name" type="text" value="${region.name}">
                                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="NameCN">中文名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="NameCN" name="nameCN" type="text" value="${region.nameCN}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="NameEN">英文名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="NameEN" name="nameEN" type="text" value="${region.nameEN}">
                            </div>
                        </div>
                        <c:if test="${!empty region.ownerRegion || empty region.id}">
                            <div class="form-group">
                                <label for="ParentId" class="col-sm-2 control-label">所属区域</label>
                                <div class="col-sm-10">
                                    <select data-val="true" data-val-number="字段 所属区域 必须是一个数字。" data-val-required="所属区域 字段是必需的。" id="ParentId" name="parentId" class="form-control">
                                        <option value="">--请选择--</option>
                                        <option v-for="(row,index) in data" :value="row.id" :selected="row.id==
                                        <c:choose>
                                            <c:when test="${!empty region.ownerRegion}">${region.ownerRegion.id}</c:when>
                                            <c:when test="${empty region.ownerRegion}">0</c:when>
                                        </c:choose>

                                        ?'selected':''">{{row.name}}</option>
                                    </select>
                                    <span data-valmsg-for="ChannelId" data-valmsg-replace="true" class="field-validation-valid"></span>
                                </div>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="Status" class="col-sm-2 control-label">启用状态</label>
                            <div class="col-sm-10">
                                <select data-val="true" data-val-number="字段 启用状态 必须是一个数字。" data-val-required="启用状态 字段是必需的。" id="Status" name="status" class="form-control">
                                    <option value="0" <c:if test="${region.status.code == 0}">selected</c:if>>禁用</option>
                                    <option value="1" <c:if test="${region.status.code == 1}">selected</c:if>>启用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="SolrQueryExpression">查询表达式</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="SolrQueryExpression" name="solrQueryExpression" rows="2">
                                        ${region.solrQueryExpression}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Description">区域描述</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="Description" name="description" rows="4">
                                        ${region.description}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Map">前台显示</label>
                            <div class="col-sm-10">
                                <select data-val="true" data-val-number="字段 启用状态 必须是一个数字。" data-val-required="启用状态 字段是必需的。" id="Map" name="map" class="form-control">
                                    <option value="0" <c:if test="${region.map.code == 0}">selected</c:if>>禁用</option>
                                    <option value="1" <c:if test="${region.map.code == 1}">selected</c:if>>启用</option>
                                </select></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var v = app.vue({
            data: { data: {} },
            loading: false,
            methods: {
                search() {
                    _self = this;
                    http.post('/ERMS/Region/SearchParent').then(function (result) {
                        _self.data = result;
                    })
                }
            }
        })
        <c:if test="${!empty region.ownerRegion || empty region.id}" >v.search();</c:if>
    </script>
</t:_PopupLayout>