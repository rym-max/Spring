<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/2
  Time: 10:15
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
                    <form action="/Spider/Item/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${item.id}" />
                        <div class="form-group">
                            <label for="Name" class="col-sm-2 control-label">爬虫名称</label>
                            <div class="col-sm-10">
                                <input data-val="true" data-val-length="字段 爬虫名称 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Name" name="name" type="text" value="${item.name}" class="form-control" />
                                <span data-valmsg-for="Name" data-valmsg-replace="true" class="field-validation-valid"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ConfigId" class="col-sm-2 control-label">所属配置</label>
                            <div class="col-sm-10">
                                <select data-val="true" data-val-number="字段 所属配置 必须是一个数字。" data-val-required="所属频道 字段是必需的。" id="ConfigId" name="configId" class="form-control">
                                    <option v-for="(row,index) in configs" :value="row.id" :selected="row.id==${item.ownerConfig.id}?'selected':''">{{row.name}}</option>
                                </select>
                                <span data-valmsg-for="ConfigId" data-valmsg-replace="true" class="field-validation-valid"></span>
                            </div>
                        </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="Status" class="col-sm-2 control-label">显示状态</label>--%>
                            <%--<div class="col-sm-10">--%>
                            <%--<input data-val="true" data-val-number="字段 显示顺序 必须是一个数字。" id="Status" name="status" type="text" value="${category.status}" class="form-control" />--%>
                            <%--</div>--%>
                            <%--</div>--%>

                        <div class="form-group">
                            <label for="IsOpen" class="col-sm-2 control-label">启用状态</label>
                            <div class="col-sm-10">
                                <select data-val="true" data-val-number="字段 启用状态 必须是一个数字。" data-val-required="启用状态 字段是必需的。" id="IsOpen" name="isOpen" class="form-control">
                                    <option value="0" <c:if test="${item.isOpen.code == 0}">selected</c:if>>未启用</option>
                                    <option value="1" <c:if test="${item.isOpen.code == 1}">selected</c:if>>已启用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Type" class="col-sm-2 control-label">爬虫类型</label>
                            <div class="col-sm-10">
                                <select data-val="true" data-val-number="字段 爬虫类型 必须是一个数字。" data-val-required="爬虫类型 字段是必需的。" id="Type" name="type" onchange="v.search();" class="form-control">
                                    <option value="0" <c:if test="${item.type.code == 0}">selected</c:if>>永久</option>
                                    <option value="1" <c:if test="${item.type.code == 1}">selected</c:if>>临时</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="inter">
                            <label for="Interval" class="col-sm-2 control-label">爬虫周期</label>
                            <div class="col-sm-10">
                                <input id="Interval" name="interval" type="text" value="${item.interval}" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group" id="ti">
                            <label for="Times" class="col-sm-2 control-label">爬虫剩余次数</label>
                            <div class="col-sm-10">
                                <input id="Times" name="times" type="text" value="${item.times}" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Creator" class="col-sm-2 control-label">创建者</label>
                            <div class="col-sm-10">
                                <input id="Creator" name="creator" type="text" value="${item.creator}" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="LastAction" class="col-sm-2 control-label">上次操作</label>
                            <div class="col-sm-10">
                                <input id="LastAction" name="lastAction" type="text" value="${item.lastAction}" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="LastAcionTime" class="col-sm-2 control-label">上次操作时间</label>
                            <div class="col-sm-10">
                                <input id="LastAcionTime" name="lastActionTime" type="text" value="${item.lastActionTime}" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="LastActionUser" class="col-sm-2 control-label">上次操作用户</label>
                            <div class="col-sm-10">
                                <input id="LastActionUser" name="lastActionUser" type="text" value="${item.lastActionUser}" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="LastResult" class="col-sm-2 control-label">上次操作结果</label>
                            <div class="col-sm-10">
                                <input id="LastResult" name="lastResult" type="text" value="${item.creator}" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="CustomSettings" class="col-sm-2 control-label">自定义配置</label>
                            <div class="col-sm-10">
                                <textarea cols="20" id="CustomSettings" name="customSettings" rows="4" class="form-control">
                                        ${item.customSettings}
                                </textarea>
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
            data: { configs:{} },
            methods: {
                searchConfig() {
                    _self = this;
                    http.post('/Spider/Config/Search').then(function (result) {
                        _self.configs = result;
                    })
                },
                changeType() {
                    if($("#Type option:selected").val() == 0){
                        $("#inter").css("display","block");
                        $("#ti").css("display","none");
                    }else{
                        $("#inter").css("display","none");
                        $("#ti").css("display","block");
                    }
                }
            }
        });

        v.searchConfig()

    </script>
</t:_PopupLayout>