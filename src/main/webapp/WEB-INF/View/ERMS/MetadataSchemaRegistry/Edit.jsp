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
                    <form action="/ERMS/MetadataSchemaRegistry/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${mdsr.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Name">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Name" name="name" type="text" value="${mdsr.name}">
                                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Code">编码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Code" name="code" type="text" value="${mdsr.code}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</t:_PopupLayout>