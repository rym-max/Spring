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
                    <form action="/Book/Journal/edit" method="post" class="form-horizontal">
                        <input data-val="true" data-val-number="字段 Id 必须是一个数字。" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${journal.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Name">期刊名称</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 期刊名称 必须是最大长度为 100 的字符串。" data-val-length-max="100" id="Name" name="name" type="text" value="${journal.name}">
                            </div>
                            <label class="col-sm-2 control-label" for="NameEn">期刊英文名称</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 期刊英文名称 必须是最大长度为 100 的字符串。" data-val-length-max="100" id="NameEn" name="nameEn" type="text" value="${journal.nameEn}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Subject">发文主题</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 发文主题 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Subject" name="subject" type="text" value="${journal.subject}">
                            </div>
                            <label class="col-sm-2 control-label" for="Domain">发文领域</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 发文领域 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Domain" name="domain" type="text" value="${journal.domain}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Author">发文作者</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 发文作者 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Author" name="author" type="text" value="${journal.author}">
                            </div>
                            <label class="col-sm-2 control-label" for="Organ">发文机构</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 发文机构 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Organ" name="organ" type="text" value="${journal.organ}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ISSN">国际标准刊号</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 国际标准刊号 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="ISSN" name="iSSN" type="text" value="${journal.ISSN}">
                            </div>
                            <label class="col-sm-2 control-label" for="GCH">馆藏号</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 馆藏号 必须是最大长度为 10 的字符串。" data-val-length-max="10" id="GCH" name="gCH" type="text" value="${journal.GCH}">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="CompetentUnit">主管单位</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 主管单位 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="CompetentUnit" name="competentUnit" type="text" value="${journal.competentUnit}">
                            </div>
                            <label class="col-sm-2 control-label" for="HostUnit">主办单位</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 主办单位 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="HostUnit" name="hostUnit" type="text" value="${journal.hostUnit}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Editor">主编</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 主编 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Editor" name="editor" type="text" value="${journal.editor}">
                            </div>

                            <label class="col-sm-2 control-label" for="PhoneNumber">电话</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 电话 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="PhoneNumber" name="phoneNumber" type="text" value="${journal.phoneNumber}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ZipCode">邮编</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 邮编 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="ZipCode" name="zipCode" type="text" value="${journal.zipCode}">
                            </div>
                            <label class="col-sm-2 control-label" for="Email">电子邮件</label>
                            <div class="col-sm-4">
                                <input class="form-control" data-val="true" data-val-length="字段 电子邮件 必须是最大长度为 50 的字符串。" data-val-length-max="50" id="Email" name="email" type="text" value="${journal.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Address">地址</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 地址 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Address" name="address" type="text" value="${journal.address}">
                        </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Href">链接</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 链接 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Href" name="href" type="text" value="${journal.href}">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ZpCount">作品数</label>
                            <div class="col-sm-2">
                                <input class="form-control" data-val="true" data-val-number="字段 作品数 必须是一个数字。" data-val-required="作品数 字段是必需的。" id="ZpCount" name="zpCount" type="text" value="${journal.zpCount}">
                            </div>
                            <label class="col-sm-2 control-label" for="ByCount">被引量</label>
                            <div class="col-sm-2">
                                <input class="form-control" data-val="true" data-val-number="字段 被引量 必须是一个数字。" data-val-required="被引量 字段是必需的。" id="ByCount" name="byCount" type="text" value="${journal.byCount}">
                            </div>
                            <label class="col-sm-2 control-label" for="HIndex">H指数</label>
                            <div class="col-sm-2">
                                <input class="form-control" data-val="true" data-val-number="字段 H指数 必须是一个数字。" data-val-required="H指数 字段是必需的。" id="HIndex" name="hIndex" type="text" value="${journal.HIndex}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Cover">封面</label>
                            <div class="col-sm-10">
                                <input class="form-control" data-val="true" data-val-length="字段 封面 必须是最大长度为 255 的字符串。" data-val-length-max="255" id="Cover" name="cover" type="text" value="${journal.cover}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Description">期刊描述</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" cols="20" id="Description" name="description" rows="2">${journal.description}</textarea>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</t:_PopupLayout>