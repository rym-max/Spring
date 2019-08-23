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
    <form action="/ERMS/MetadataFieldRegistry/edit" method="post" class="form-horizontal">
        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="Id" type="hidden" value="${mdfr.id}" />
        <div class="form-group">
            <label for="MetadataSchemaId" class="col-sm-2 control-label">元数据注册</label>
            <div class="col-sm-4">
                <select data-val="true" data-val-number="字段 元数据注册 必须是一个数字。" data-val-required="元数据注册 字段是必需的。" id="MetadataSchemaId" name="metadataSchemaId" class="form-control">
                    <template v-for="option in metadataSchemaRegistrys">
                    <option  v-if="option.id==${mdfr.ownerMetadataSchemaRegistry.id}" v-bind:value="option.id" selected>{{option.name}}</option>
                        <option  v-else v-bind:value="option.id">{{option.name}}</option>
                    </template>
                </select>
                <span data-valmsg-for="MetadataSchemaId" data-valmsg-replace="true" class="field-validation-valid"></span>
            </div>
            <label for="Name" class="col-sm-2 control-label">字段中文名</label>
            <div class="col-sm-4">
                <input data-val="true" data-val-length="字段中文名不能超过50个字符" data-val-length-max="50" id="Name" name="name" type="text" value="${mdfr.name}" class="form-control" />
                <span data-valmsg-for="Name" data-valmsg-replace="true" class="field-validation-valid"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="Element" class="col-sm-2 control-label">要素</label>
            <div class="col-sm-4">
                <input data-val="true" data-val-length="要素不能超过50个字符" data-val-length-max="50" data-val-required="元数据要素不能为空！" id="Element" name="element" type="text" value="${mdfr.element}" class="form-control" />
            </div>
            <label for="Qualifier" class="col-sm-2 control-label">修饰</label>
            <div class="col-sm-4">
                <input data-val="true" data-val-length="修饰不能超过50个字符" data-val-length-max="50" id="Qualifier" name="qualifier" type="text" value="${mdfr.qualifier}" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="ScopeNote" class="col-sm-2 control-label">范围说明</label>
            <div class="col-sm-10">
                <textarea cols="20" data-val="true" data-val-length="范围说明不能超过50个字符" data-val-length-max="512" id="ScopeNote" name="scopeNote" rows="2" class="form-control">
                        ${mdfr.scopeNote}
                </textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="ControlType" class="col-sm-2 control-label">控件类型</label>
            <div class="col-sm-4">
                <select data-val="true" data-val-number="字段 控件类型 必须是一个数字。" data-val-required="控件类型 字段是必需的。" id="ControlType" name="controlType" class="form-control">
                    <option value="0" <c:if test="${'0' eq mdfr.controlType.code}">selected</c:if> >单行文本框</option>
                    <option value="1" <c:if test="${'1' eq mdfr.controlType.code}">selected</c:if> >多行文本框</option>
                    <option value="2" <c:if test="${'2' eq mdfr.controlType.code}">selected</c:if> >HTML编辑器</option>
                    <option value="3" <c:if test="${'3' eq mdfr.controlType.code}">selected</c:if> >下拉列表</option>
                    <option value="4" <c:if test="${'4' eq mdfr.controlType.code}">selected</c:if> >单选按钮</option>
                    <option value="5" <c:if test="${'5' eq mdfr.controlType.code}">selected</c:if> >多选按钮</option>
                    <option value="6" <c:if test="${'6' eq mdfr.controlType.code}">selected</c:if> >日期选择框(yyyy-MM-dd HH:mm:ss)</option>
                    <option value="7" <c:if test="${'7' eq mdfr.controlType.code}">selected</c:if> >日期选择框(yyyy-MM-dd)</option>
                    <option value="8" <c:if test="${'8' eq mdfr.controlType.code}">selected</c:if> >上传封面</option>
                    <option value="9" <c:if test="${'9' eq mdfr.controlType.code}">selected</c:if> >上传文件控件</option>
                </select>
            </div>
            <label for="DataType" class="col-sm-2 control-label">数据类型</label>
            <div class="col-sm-4">
                <select data-val="true" data-val-number="字段 数据类型 必须是一个数字。" data-val-required="数据类型 字段是必需的。" id="DataType" name="dataType" class="form-control">
                    <option value="0" <c:if test="${'0' eq mdfr.dataType.code}">selected</c:if> >字符串</option>
                    <option value="1" <c:if test="${'1' eq mdfr.dataType.code}">selected</c:if> >数字</option>
                    <option value="2" <c:if test="${'2' eq mdfr.dataType.code}">selected</c:if> >日期</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="Options" class="col-sm-2 control-label">下拉框选项</label>
            <div class="col-sm-10">
                <textarea cols="20" data-val="true" data-val-length="下拉框选项不能超过1024个字符" data-val-length-max="1024" id="Options" name="options" rows="2" class="form-control">
                        ${mdfr.options}
                </textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="ValidationRules" class="col-sm-2 control-label">验证规则</label>
            <div class="col-sm-10">
                <textarea cols="20" data-val="true" data-val-length="验证规则不能超过255个字符" data-val-length-max="255" id="ValidationRules" name="validationRules" rows="2" class="form-control">
                        ${mdfr.validationRules}
                </textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="IsRequired" class="col-sm-2 control-label">是否必填</label>
            <div class="col-sm-2">
                <div class="icheckbox_square-green" style="position: relative;">
                    <input <c:if test="${mdfr.isRequired}">checked="checked"</c:if> data-val="true" data-val-required="是否必填 字段是必需的。" id="IsRequired" name="isRequired" type="checkbox" value="true" class="form-control i-checks" style="position: absolute; opacity: 0;" />
                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                </div>
                <input name="isRequired" type="hidden" value="false" />
            </div>
            <label for="IsSearch" class="col-sm-2 control-label">参与检索</label>
            <div class="col-sm-2">
                <div class="icheckbox_square-green" style="position: relative;">
                    <input <c:if test="${mdfr.isSearch}">checked="checked"</c:if> data-val="true" data-val-required="参与检索 字段是必需的。" id="IsSearch" name="isSearch" type="checkbox" value="true" class="form-control i-checks" style="position: absolute; opacity: 0;" />
                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                </div>
                <input name="isSearch" type="hidden" value="false" />
            </div>
            <label for="IsFullSearch" class="col-sm-2 control-label">参与全文检索</label>
            <div class="col-sm-2">
                <div class="icheckbox_square-green" style="position: relative;">
                    <input <c:if test="${mdfr.isFullSearch}">checked="checked"</c:if> data-val="true" data-val-required="参与全文检索 字段是必需的。" id="IsFullSearch" name="isFullSearch" type="checkbox" value="true" class="form-control i-checks" style="position: absolute; opacity: 0;" />
                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                </div>
                <input name="isFullSearch" type="hidden" value="false" />
            </div>
        </div>
        <div class="form-group">
            <label for="IsCluster" class="col-sm-2 control-label">可聚类</label>
            <div class="col-sm-2">
                <div class="icheckbox_square-green" style="position: relative;">
                    <input <c:if test="${mdfr.isCluster}">checked="checked"</c:if> data-val="true" data-val-required="可聚类 字段是必需的。" id="IsCluster" name="isCluster" type="checkbox" value="true" class="form-control i-checks" style="position: absolute; opacity: 0;" />
                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                </div>
                <input name="isCluster" type="hidden" value="false" />
            </div>
            <label for="IsSort" class="col-sm-2 control-label">可排序</label>
            <div class="col-sm-2">
                <div class="icheckbox_square-green" style="position: relative;">
                    <input <c:if test="${mdfr.isSort}">checked="checked"</c:if> data-val="true" data-val-required="可排序 字段是必需的。" id="IsSort" name="isSort" type="checkbox" value="true" class="form-control i-checks" style="position: absolute; opacity: 0;" />
                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                </div>
                <input name="isSort" type="hidden" value="false" />
            </div>
            <label for="IsMultiple" class="col-sm-2 control-label">可多值</label>
            <div class="col-sm-2">
                <div class="icheckbox_square-green" style="position: relative;">
                    <input <c:if test="${mdfr.isMultiple}">checked="checked"</c:if> data-val="true" data-val-required="可多值 字段是必需的。" id="IsMultiple" name="isMultiple" type="checkbox" value="true" class="form-control i-checks" style="position: absolute; opacity: 0;" />
                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                </div>
                <input name="isMultiple" type="hidden" value="false" />
            </div>
        </div>
        <div class="form-group">
            <label for="SearchName" class="col-sm-2 control-label">检索名称</label>
            <div class="col-sm-4">
                <input data-val="true" data-val-length="检索名称不能超过50个字符" data-val-length-max="50" id="SearchName" name="searchName" type="text" value="${mdfr.searchName}" class="form-control" />
            </div>
            <label for="DefaultValue" class="col-sm-2 control-label">默认值</label>
            <div class="col-sm-4">
                <input id="DefaultValue" name="defaultValue" type="text" value="${mdfr.defaultValue}" class="form-control" />
            </div>
        </div>
    </form>
<script>
    var v = app.vue({
        data: { metadataSchemaRegistrys: {}},
        loading: true,
        methods: {

            searchMetadataSchemaRegistry() {
                _self = this;

                http.post('/ERMS/MetadataSchemaRegistry/Search').then(function (result) {
                    _self.metadataSchemaRegistrys = result;
                });
            }
        }
    })
    v.searchMetadataSchemaRegistry();
</script>
</t:_PopupLayout>