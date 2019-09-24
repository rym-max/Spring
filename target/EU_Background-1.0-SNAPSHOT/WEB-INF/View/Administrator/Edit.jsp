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
                    <form action="/Administrator/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。"
                               id="Id" name="id" type="hidden" value="${administrator.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="LoginName">登录名</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="LoginName" name="loginName" type="text" value="${administrator.loginName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="LoginPassword">登录密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="LoginPassword" name="loginPassword" type="password" value="${administrator.loginPassword}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="Name">姓名</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="Name" name="name" type="text" value="${administrator.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="Role">角色</label>
                        <div class="col-sm-10">
                            <select class="form-control" data-val="true" data-val-required="角色 字段是必需的。" id="Role" name="role">
                                <option value="">选择角色</option>
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.code}"
                                    <c:if test="${(!empty administrator) and (role eq administrator.role)}">selected</c:if>
                                    >${role.nameCN}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</t:_PopupLayout>