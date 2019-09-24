<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_PopupLayout>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form action="/ERMS/Channel/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${ch.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Name">频道名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Name" name="name" type="text" value="${ch.name}">
                                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Code">频道编码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Code" name="code" type="text" value="${ch.code}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="QueryExpression">查询表达式</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="QueryExpression" name="queryExpression" rows="2">
                                        ${ch.queryExpression}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Description">频道描述</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="Description" name="description" rows="2">
                                        ${ch.description}
                                </textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Sort">显示顺序</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-number="字段 显示顺序 必须是一个数字。" data-val-required="显示顺序 字段是必需的。" id="Sort" name="sort" type="text" value="${ch.sort}">
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
            data: { categorys: {} },
            methods: {
                search() {
                    _self = this;
                    http.post('/ERMS/Category/Search', {
                        filters: {
                            filter: [{ name: "ChannelId", value: $("#ChannelId").val() }, { name: "ParentId", value: 0 }],
                            sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                        }
                    }).then(function (result) {
                        _self.categorys = result;
                    })
                }
            }
        })
        v.search();
    </script>
</t:_PopupLayout>