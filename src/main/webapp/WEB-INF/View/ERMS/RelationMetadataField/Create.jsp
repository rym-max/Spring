<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:10
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
                    <form action="/ERMS/RelationMetadataField/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="Id" type="hidden" value="${rmdf.id}">
                        <input data-val="true" data-val-number="字段 关联对象类型 必须是一个数字。" data-val-required="关联对象类型 字段是必需的。" id="ObjectType" name="ObjectType" type="hidden" value="${rmdf.objectType.code}">
                        <input data-val="true" data-val-number="字段 关联对象编号 必须是一个数字。" data-val-required="关联对象编号 字段是必需的。" id="RelationObjectId" name="RelationObjectId" type="hidden" value="${rmdf.relationObjectId}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="MetadataFieldId">元数据字段</label>
                            <div class="col-sm-4">
                                <select class="form-control" data-val="true" data-val-number="字段 元数据字段 必须是一个数字。" data-val-required="元数据字段 字段是必需的。" id="MetadataFieldId" name="MetadataFieldId">
                                    <c:forEach items="${metafield}" var="item">
                                        <option value="${item.value}" <c:if test="${item.value eq rmdf.metadataFieldId}">selected</c:if>>${item.text}</option>
                                    </c:forEach>
                                </select>
                                <span class="field-validation-valid" data-valmsg-for="MetadataFieldId" data-valmsg-replace="true"></span>
                            </div>
                            <label class="col-sm-2 control-label" for="Name">中文名称</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段中文名称不能超过50个字符" data-val-length-max="50" id="Name" name="Name" type="text" value="${rmdf.name}">
                                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ControlType">控件类型</label>
                            <div class="col-sm-4">
                                <select class="form-control" data-val="true" data-val-number="字段 控件类型 必须是一个数字。" data-val-required="控件类型 字段是必需的。" id="ControlType" name="ControlType">
                                    <option value="0" <c:if test="${rmdf.controlType.code == 0}">selected</c:if>>单行文本框</option>
                                    <option value="1" <c:if test="${rmdf.controlType.code == 1}">selected</c:if>>多行文本框</option>
                                    <option value="2" <c:if test="${rmdf.controlType.code == 2}">selected</c:if>>HTML编辑器</option>
                                    <option value="3" <c:if test="${rmdf.controlType.code == 3}">selected</c:if>>下拉列表</option>
                                    <option value="4" <c:if test="${rmdf.controlType.code == 4}">selected</c:if>>单选按钮</option>
                                    <option value="5" <c:if test="${rmdf.controlType.code == 5}">selected</c:if>>多选按钮</option>
                                    <option value="6" <c:if test="${rmdf.controlType.code == 6}">selected</c:if>>日期选择框(yyyy-MM-dd HH:mm:ss)</option>
                                    <option value="7" <c:if test="${rmdf.controlType.code == 7}">selected</c:if>>日期选择框(yyyy-MM-dd)</option>
                                    <option value="8" <c:if test="${rmdf.controlType.code == 8}">selected</c:if>>上传封面</option>
                                    <option value="9" <c:if test="${rmdf.controlType.code == 9}">selected</c:if>>上传文件控件</option>
                                </select>
                            </div>
                            <label class="col-sm-2 control-label" for="DataType">数据类型</label>
                            <div class="col-sm-4">
                                <select class="form-control" data-val="true" data-val-number="字段 数据类型 必须是一个数字。" data-val-required="数据类型 字段是必需的。" id="DataType" name="DataType">
                                    <option value="0" <c:if test="${rmdf.dataType.code == 0}">selected</c:if>>字符串</option>
                                    <option value="1" <c:if test="${rmdf.dataType.code == 1}">selected</c:if>>数字</option>
                                    <option value="2" <c:if test="${rmdf.dataType.code == 2}">selected</c:if>>日期</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Options">下拉框选项</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="下拉框选项不能超过1024个字符" data-val-length-max="1024" id="Options" name="Options" type="text" value="${rmdf.options}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ValidationRules">验证规则</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" data-val="true" data-val-length="验证规则不能超过255个字符" data-val-length-max="255" id="ValidationRules" name="ValidationRules" rows="2">
                                    ${rmdf.validationRules}
                                </textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="IsRequired">是否必填</label>
                            <div class="col-sm-2">
                                <input <c:if test="${(!empty rmdf.isRequired) && rmdf.isRequired}">checked="checked"</c:if> class="form-control i-checks" data-val="true" data-val-required="是否必填 字段是必需的。" id="IsRequired" name="IsRequired" type="checkbox" value="true" />
                                <input name="IsRequired" type="hidden" value="false" />
                            </div>
                            <label class="col-sm-2 control-label" for="IsSearch">参与检索</label>
                            <div class="col-sm-2">
                                <input <c:if test="${(!empty rmdf.isSearch) && rmdf.isSearch}">checked="checked"</c:if> class="form-control i-checks" data-val="true" data-val-required="参与检索 字段是必需的。" id="IsSearch" name="IsSearch" type="checkbox" value="true" />
                                <input name="IsSearch" type="hidden" value="false" />
                            </div>
                            <label class="col-sm-2 control-label" for="IsFullSearch">参与全文检索</label>
                            <div class="col-sm-2">
                                <input <c:if test="${(!empty rmdf.isFullSearch) && rmdf.isFullSearch}">checked="checked"</c:if> class="form-control i-checks" data-val="true" data-val-required="参与全文检索 字段是必需的。" id="IsFullSearch" name="IsFullSearch" type="checkbox" value="true" />
                                <input name="IsFullSearch" type="hidden" value="false" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="IsCluster">可聚类</label>
                            <div class="col-sm-2">
                                <input <c:if test="${(!empty rmdf.isCluster) && rmdf.isCluster}">checked="checked"</c:if> class="form-control i-checks" data-val="true" data-val-required="可聚类 字段是必需的。" id="IsCluster" name="IsCluster" type="checkbox" value="true" />
                                <input name="IsCluster" type="hidden" value="false" />
                            </div>
                            <label class="col-sm-2 control-label" for="IsSort">可排序</label>
                            <div class="col-sm-2">
                                <input <c:if test="${(!empty rmdf.isSort) && rmdf.isSort}">checked="checked"</c:if> class="form-control i-checks" data-val="true" data-val-required="可排序 字段是必需的。" id="IsSort" name="IsSort" type="checkbox" value="true" />
                                <input name="IsSort" type="hidden" value="false" />
                            </div>
                            <label class="col-sm-2 control-label" for="IsMultiple">可多值</label>
                            <div class="col-sm-2">
                                <input <c:if test="${(!empty rmdf.isMultiple) && rmdf.isMultiple}">checked="checked"</c:if> class="form-control i-checks" data-val="true" data-val-required="可多值 字段是必需的。" id="IsMultiple" name="IsMultiple" type="checkbox" value="true" />
                                <input name="IsMultiple" type="hidden" value="false" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="SearchName">检索名称</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="检索名称不能超过50个字符" data-val-length-max="50" id="SearchName" name="SearchName" type="text" value="${rmdf.searchName}">
                            </div>
                            <label class="col-sm-2 control-label" for="DefaultValue">默认值</label>
                            <div class="col-sm-4">
                                <input class="form-control" id="DefaultValue" name="DefaultValue" type="text" value="${rmdf.defaultValue}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Sort">显示顺序</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-number="字段 显示顺序 必须是一个数字。" data-val-required="显示顺序 字段是必需的。" id="Sort" name="Sort" type="text" value="${rmdf.sort}">
                            </div>
                        </div>


                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#MetadataFieldId").change(function () {
                var href = "./Create?MetadataFieldId=" + $(this).val();
                <c:if test="${!empty paramValues.get('ChannelId')}">href = href + "&ChannelId=${paramValues.get('ChannelId')}";</c:if>
                <c:if test="${!empty paramValues.get('CategoryId')}">href = href + "&CategoryId=${paramValues.get('CategoryId')}";</c:if>
                window.location.href = href;
            });
        })
    </script>

</t:_PopupLayout>