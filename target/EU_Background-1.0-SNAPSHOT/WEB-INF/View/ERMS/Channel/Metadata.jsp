<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/29
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_Layout>
<jsp:attribute name="scripts">
    <script type="text/javascript">
        var v = app.vue({
            data: { channels: {}, fields: {} },
            loading: true,
            methods: {
                selectChannel(e, id) {
                    if ($("#channel_id").val() == id) {
                        $(".text-navy").removeClass("text-danger");
                        $("#channel_id").val("");
                    } else {
                        $(".text-navy").removeClass("text-danger");
                        $(e.currentTarget).find(".text-navy").addClass("text-danger");
                        $("#channel_id").val(id);
                    }
                    v.search();
                },
                searchChannel() {
                    _self = this;
                    http.post('/ERMS/Channel/Search').then(function (result) {
                        _self.channels = result;
                    });
                },
                search() {
                    _self = this;
                    http.post('/ERMS/RelationMetadataField/Search', {
                        filter: [{ name: "RelationObjectId", value: $("#channel_id").val() }, { name:"ObjectTypeId",value:0}],
                        sort: [{ name: "Sort", asc: true }, { name: "Id", asc: true }]
                }).then(function (result) {
                        _self.fields = result;
                    });
                }
            }
        })

        v.searchChannel();
        v.search();


        <%--
        Vue.filter('ControlTypeCN', function (value) {
            var enums = @Html.Raw(VipFramework.Common.EnumHelper.GetEnumJson<VipFramework.Entity.Enum.CommonEnum.ControlType>());
            for (var i = 0; i < enums.length; i++) {
                if (enums[i].Value == value) {
                    return enums[i].Text;
                }
            }
            return "未知";
        })

        Vue.filter('DataTypeCN', function (value) {
            var enums = @Html.Raw(VipFramework.Common.EnumHelper.GetEnumJson<VipFramework.Entity.Enum.CommonEnum.DataType>());
            for (var i = 0; i < enums.length; i++) {
                if (enums[i].Value == value) {
                    return enums[i].Text;
                }
            }
            return "未知";
        })
        --%>
    </script>
</jsp:attribute>
    <jsp:body>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>栏目元数据管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/Home/index.html">首页</a>
                    </li>
                    <li>自建库管理</li>
                    <li class="active">
                        <strong>栏目元数据管理</strong>
                    </li>
                </ol>
            </div>
            <%--<div class="col-sm-8">--%>
            <%--<div class="title-action">--%>
                <%--<a href="ERMS/RelationMetadataField/Create" class="btn btn-primary openWindow" title="创建分类" height="600px"><i class="fa fa-plus"></i> 设置元数据</a>--%>
            <%--</div>--%>
        <%--</div>--%>
        </div>

        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-2">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <button class="btn btn-block btn-primary">选择栏目</button>
                            <input type="hidden" id="channel_id" value="" />
                            <ul class="category-list">
                                <li v-for="(row,index) in channels">
                                    <a v-on:click="v.selectChannel($event,row.id)" href="javascript:;" cid="2" class="pull-left"> <i class="fa fa-circle text-navy"></i> {{row.name}}</a>
                                    <a :href="'/ERMS/RelationMetadataField/Create?ChannelId='+row.id" class="btn btn-primary btn-sm pull-right openWindow"><i class="fa fa-plus"></i></a>
                                    <div class="clearfix"></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <div class="row">
                                <div class="col-lg-12">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>字段中文名</th>
                                            <th>要素</th>
                                            <th>修饰</th>
                                            <th>控件类型</th>
                                            <th>数据类型</th>
                                            <th>检索</th>
                                            <th>检索名称</th>
                                            <th>全文检索</th>
                                            <th>多值</th>
                                            <th>必填</th>
                                            <th width="150px;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody v-cloak>
                                        <tr v-for="(row,index) in fields">
                                            <td>{{row.name}}</td>
                                            <td>{{row.ownerMetaFieldRegistry==null?"":row.ownerMetaFieldRegistry.element}}</td>
                                            <td>{{row.ownerMetaFieldRegistry==null?"":row.ownerMetaFieldRegistry.qualifier}}</td>
                                            <td><label class="badge badge-primary">{{row.controlType.nameCN}}</label></td>
                                            <td><label class="badge badge-success">{{row.dataType.nameCN}}</label></td>
                                            <td>{{row.isSearch?"是":"否"}}</td>
                                            <td>{{row.searchName}}</td>
                                            <td>{{row.isFullSearch?"是":"否"}}</td>
                                            <td>{{row.isMultiple?"是":"否"}}</td>
                                            <td>{{row.isRequired?"是":"否"}}</td>
                                            <td class="center">
                                                <a type="button" class="btn btn-primary btn-xs mbs openWindow" height="600px" :href="'/ERMS/RelationMetadataField/Edit/'+row.id">
                                                    <i class="fa fa-edit"></i>&nbsp; 编辑
                                                </a>
                                                <a type="button" class="btn btn-danger btn-xs mbs ajax" :href="'/ERMS/RelationMetadataField/Delete/'+row.id">
                                                    <i class="fa fa-trash-o"></i>&nbsp; 删除
                                                </a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:_Layout>