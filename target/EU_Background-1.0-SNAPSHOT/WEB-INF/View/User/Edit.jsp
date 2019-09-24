<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/27
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:_PopupLayout>
    <jsp:body>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form action="/User/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${user.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="LoginName">登录名</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 登录名 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="LoginName" name="loginName" type="text" value="${user.loginName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="LoginPassword">登录密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 登录密码 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="LoginPassword" name="loginPassword" type="password" value="${user.loginPassword}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Name">姓名</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 姓名 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Name" name="name" type="text" value="${user.name}">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-2 control-label" for="Avatar">头像</label>--%>
                            <%--<div class="col-sm-8">--%>
                                <%--<input class="form-control" data-val="true" data-val-length="字段 头像 必须是最大长度为 512 的字符串。" data-val-length-max="512" id="Avatar" name="Avatar" type="text" value="${user.avator}">--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-2">--%>
                                <%--<button type="button" class="btn btn-primary upload-cover" textbox="Avatar"><i class="fa fa-upload"></i> 选择</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Sex">性别</label>
                            <div class="col-sm-10">
                                <select class="form-control" data-val="true" data-val-length="字段 性别 必须是最大长度为 10 的字符串。" data-val-length-max="10" id="Sex" name="sex">
                                    <option value="男" <c:if test="${user.sex eq '男'}">selected</c:if>>男</option>
                                    <option value="女" <c:if test="${user.sex eq '女'}">selected</c:if>>女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Birthday">生日</label>
                            <div class="col-sm-10">
                                <input class="form-control laydate" data-val="true" data-val-length="字段 生日 必须是最大长度为 10 的字符串。" data-val-length-max="10" format="yyyy-MM-dd" id="Birthday" name="birthday" type="text" value="${user.birthday}" lay-key="1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Email">邮箱</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 邮箱 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Email" name="email" type="text" value="${user.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="MobilePhoneNumber">手机号码</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 手机号码 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="MobilePhoneNumber" name="mobilePhoneNumber" type="text" value="${user.mobilePhoneNumber}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="PhoneNumber">电话</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 电话 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="PhoneNumber" name="phoneNumber" type="text" value="${user.phoneNumber}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Summary">简介</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="Summary" name="summary" rows="2">
                                    ${user.summary}
                                </textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


        <link href="/static/js/plugins/KindEditor/themes/default/default.css" rel="stylesheet" />
        <script src="/static/js/plugins/KindEditor/kindeditor-all-min.js"></script>
        <script src="/static/js/plugins/KindEditor/lang/zh_CN.js"></script>
        <script type="text/javascript">
            $(function () {

                KindEditor.basePath = '/static/js/plugins/KindEditor/';

                var editor = KindEditor.editor({
                    allowFileManager: false,
                    zIndex: 19891230
                });
                //动态小图
                $('.upload-cover').click(function () {
                    var name = $(this).attr("textbox");

                    editor.loadPlugin('image', function () {
                        editor.plugin.imageDialog({
                            imageUrl: $("#" + name).val(),
                            clickFn: function (url, title, width, height, border, align) {
                                $("#" + name).val(url);
                                editor.hideDialog();
                                $("#" + name).val(url);
                            }
                        });
                    });
                });
            })
        </script>    
    </jsp:body>
</t:_PopupLayout>