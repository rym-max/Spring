<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/9/3
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:_Layout>
<jsp:attribute name="scripts">
    <script src="/static/js/echart/echarts.min.js"></script>
    <script type="text/javascript">
        let myChart;

        var v = app.vue({
            data: { usercount: {},pagecount:{},itemcount:{},spiders:{},xdata:{} },
            methods: {
                init(){
                    myChart= echarts.init(document.getElementById("resource_spider_chart"));
                    var option = {
                        title: {
                            text: '近30次爬虫记录',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'spiderItem',
                            formatter: '{a} <br/>{b} : {c}'
                        },
                        legend: {
                            left: 'left',
                            data: ['爬取页面数','相关页面数']
                        },
                        xAxis: {
                            data:["待查询","待查询","待查询"],
                            type: 'category',
                            name: '日期',
                            splitLine: { show: false },
                            axisLabel: {
                                interval: 0,
                                rotate: 40
                            }
                        },
                        grid: {
                            left: '0%',
                            right: '0%',
                            bottom: '15%',
                            containLabel: true
                        },
                        yAxis: [
                            {
                                type: 'value',
                                name: '数量',
                                minInterval: 1
                            }
                        ],
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {
                                    show: true
                                },
                                dataView: {
                                    show: true,
                                    readOnly: true
                                },
                                restore: {
                                    show: true
                                },
                                saveAsImage: {
                                    show: true
                                }
                            }
                        },
                        series: [
                            {
                                data:[0,0,0],
                                name: '爬取页面数',
                                type: 'line'
                            },{
                                data:[0,0,0],
                                name: '相关页面数',
                                type: 'line'
                            }
                        ]
                    };
                    myChart.setOption(option);
                },
                search(){
                    _self = this;
                    if($("#SpiderId").val()>0) {
                        http.post('/Log/SpiderLog/Search'+'?spider='+$("#SpiderId").val()+'&times='+$('#Times').val()).then(function (result) {
                            if (result.success) {

                                _self.pagecount = result.data.pagecount;
                                _self.itemcount = result.data.itemcount;
                                _self.xdata = result.data.day;
                                if(_self.pagecount.length==0){
                                    swal({
                                        title: "查询失败！",
                                        text: "所选爬虫无爬取记录！",
                                        type: "error"
                                    });
                                }else {
                                    v.setCharts();
                                    v.searchUserCount();
                                }
                            } else {
                                swal({
                                    title: result.message,
                                    text: result.error,
                                    type: "error"
                                });
                            }
                        });

                    }
                },
                setCharts(){
                    _self = this;
                    myChart.showLoading();
                    myChart.setOption(
                        {
                            title: {
                                text: $("#SpiderId").text+'  --近'+$("#Times")+'次爬虫记录',
                                left: 'center'
                            },
                            xAxis: {
                                type: 'category',
                                name: 'x',
                                data:_self.xdata,
                                splitLine: { show: false },
                                axisLabel: {
                                    interval: 0,
                                    rotate: 40
                                }
                            },
                            series: [
                                {
                                    name: '爬取页面数',
                                    data:_self.pagecount,
                                    type: 'line'
                                },
                                {
                                    name: '相关页面数',
                                    data:_self.itemcount,
                                    type: 'line'
                                }
                            ]
                        }
                    );
                    myChart.hideLoading();
                },
                searchSpiders(){
                    _self = this;
                    http.post('/Spider/SpiderItem/Search',{
                        filter:[{name:"Status",value:1}]
                    }).then(function(result){
                       _self.spiders=result;
                    });
                },
                searchUserCount(){
                    _self = this;
                    if($("#SpiderId").val()>0) {
                        http.post('/Log/SpiderLog/SearchUserCount?spider=' + $('#SpiderId').val() + '&action=' + $('#Action').val()).then(
                            function (result) {
                                if (result.success) {
                                    _self.usercount = result.data.usercount;
                                } else {
                                    swal({
                                        title: result.message,
                                        text: result.error,
                                        type: "error"
                                    });
                                }
                            }
                        );
                    }
                }
            },
            mounted(){
                this.init();
            }
        });

        v.searchSpiders();




        //$("#month").change(function (e) {
        //    var month = $(this).val();
        //    if (month == 0) {
        //        month = new Date().getMonth() + 1;
        //    }
        //    window.location.href = "admin/stat/access?month=" + month;
        //});
    </script>
</jsp:attribute>
<jsp:body>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-sm-4">
            <h2>爬虫统计</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/Home/index.html">首页</a>
                </li>
                <li class="active">
                    <strong>爬虫统计</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row pull-right" style="margin-bottom:20px;">
                                    <div class="col-lg-12">
                                        <form role="form" class="form-inline">
                                            <div class="input-group">
                                                <select id="Times" name="times" onchange="v.search();" class="form-control">
                                                    <option value="30" selected>近30次</option>
                                                    <option value="60">近60次</option>
                                                    <option value="90">近90次</option>
                                                </select>
                                                <select id="SpiderId" name="spiderId" onchange="v.search();" class="form-control">
                                                    <option value="0">--请选择--</option>
                                                    <option v-for="(row,index) in spiders" :value="row.id">{{row.name}}</option>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-xs-12">
                                    <div id="resource_spider_chart" style="height:450px;"></div>
                                </div>
                                <div class="row pull-right" style="margin-bottom:20px;">
                                    <div class="col-lg-12">
                                        <form role="form" class="form-inline">
                                            <div class="input-group">
                                                <select id="Action" name="action" onchange="v.searchUserCount();" class="form-control">
                                                    <option value="ALL" selected>ALL</option>
                                                    <c:forEach items="${actionEnums}" var="spiderAction">
                                                        <option value="${spiderAction.name}">${spiderAction.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>操作用户</th>
                                            <th>执行操作</th>
                                            <th>爬虫名称</th>
                                            <th>次数</th>
                                        </tr>
                                        </thead>
                                        <tbody v-cloak>
                                        <tr  v-for="(spiderItem,index) in usercount">
                                            <td>{{spiderItem.user}}</td>
                                            <td>{{spiderItem.action}}</td>
                                            <td>{{spiderItem.spider}}</td>
                                            <td>{{spiderItem.count}}</td>
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
    </div>
</jsp:body>
</t:_Layout>