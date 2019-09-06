<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/2
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:_PopupLayout>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form action="/Spider/Config/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${config.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Name">爬虫配置名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Name" name="name" type="text" value="${config.name}">
                                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Project">project</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Project" name="project" type="text" value="${config.project}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Spider">spider</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="Spider" name="spider" type="text" value="${config.spider}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Configs">配置信息</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="Configs" name="config" rows="5">
                                        ${config.configs}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Rules">规则信息</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="Rules" name="rules" rows="5">
                                        ${config.rules}
                                </textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</t:_PopupLayout>
