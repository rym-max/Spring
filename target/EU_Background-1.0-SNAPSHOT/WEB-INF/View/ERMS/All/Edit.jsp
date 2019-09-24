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
                    <form action="/ERMS/All/edit/" method="post" class="form-horizontal">
                        <input data-val="true" data-val-required="Id 字段是必需的。" id="Id" name="id" type="hidden" value="${all.id}" />
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ChannelId">所属频道</label>
                            <div class="col-sm-10">
                                <select class="form-control" data-val="true" data-val-number="字段 所属频道 必须是一个数字。" data-val-required="所属频道不能为空！" id="ChannelId" name="channelId" onchange="v.search()">
                                    <option value="1" <c:if test="${(!empty all.ownerChannel) and (all.ownerChannel.id eq 1)}">selected</c:if>>新闻</option>
                                    <option value="2" <c:if test="${(!empty all.ownerChannel) and (all.ownerChannel.id eq 2)}">selected</c:if>>论文</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="CategoryId">所属栏目</label>
                            <div class="col-sm-10">
                                <select id="CategoryId" name="categoryId" class="form-control">
                                    <option value="0">无分类</option>
                                    <option v-for="(row,index) in data" :value="row.id" :selected="row.id==(${all.categoryId})?'selected':''" :style="row.parentId==0?'':'padding-left:20px;'">{{row.name}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="Status">审核状态</label>
                            <div class="col-sm-10">
                                <select class="form-control" data-val="true" data-val-required="审核状态 字段是必需的。" id="Status" name="status">
                                    <option value="">请选择</option>
                                    <option value="1" <c:if test="${all.status.code eq 1}">selected</c:if>>已通过</option>
                                    <option value="0" <c:if test="${all.status.code eq 0}">selected</c:if>>未通过</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12" id="metadata_field">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>



    <script src="/static/js/plugins/KindEditor/kindeditor-all.js"></script>
    <script src="/static/js/plugins/KindEditor/lang/zh_CN.js"></script>
    <script type="text/javascript">
        var v = app.vue({
            data() {
                return {
                    data: {}
                }
            },
            methods: {
                search() {
                    _self = this;
                    http.post('/ERMS/Category/Search', {
                        filters: {
                            filter: [{ name: "ChannelId", value: $("#ChannelId").val() }],
                            sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                        }
                    }).then(function (result) {
                        _self.data = result;
                        window.setTimeout(function () {
                            v.show_metadata_field_form();
                        }, 100);
                    });
                },
                show_metadata_field_form() {
                    var url = "/ERMS/All/ShowMetadataFieldForm?ChannelId=" + $("#ChannelId").val() + "&CategoryId=" + $("#CategoryId").val() + "&ItemId=${all.id}";
                    http.post(url).then(function (html) {
                        $("#metadata_field").html(html);
                        window.setTimeout(function () {
                            //富文本
                            KindEditor.basePath = '/static/js/plugins/KindEditor/';
                            editorContent = KindEditor.create('.kindeditor', {
                                minWidth: 200,
                                width: '100%',
                                height: '300',
                                themeType: 'simple',
                                cssPath: '/static/js/plugins/KindEditor/plugins/code/prettify.css',
                                allowFileManager: false,
                                zIndex: 19891230,
                                items: [
                                    'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
                                    'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                                    'anchor', 'link', 'unlink'
                                ],
                                afterBlur: function () { this.sync(); }
                            });
                        }, 200);
                    })
                }
            }
        });
        v.search();
    </script>
</t:_PopupLayout>