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

<t:_Layout>
<jsp:attribute name="scripts">
    <script src="/static/js/echart/echarts.min.js"></script>
    <script type="text/javascript">
        let myChart;

        var v = app.vue({
            data: { data: {},xdata:{},ipcount:{} },
            methods: {
                init(){
                    myChart= echarts.init(document.getElementById("resource_month_access_chart"));
                    var option = {
                        title: {
                            text: '${date.Year}年${date.Month}月访问统计',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c}'
                        },
                        legend: {
                            left: 'left',
                            data: ['${date.Year}年${date.Month}月访问统计']
                        },
                        xAxis: {
                            data:["1日","2日","3日","4日","5日","6日","7日","8日","9日","10日","11日","12日","13日","14日"],
                            type: 'category',
                            name: 'x',
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
                                name: 'y',
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
                                data:[12,12,0,1,1,0,1,4,5,7,8,9,10,12],
                                name: '${date.Year}年${date.Month}月访问统计',
                                type: 'line'
                            }
                        ]
                    };
                    myChart.setOption(option);
                },
                search() {
                    _self = this;
                    if($("#month").val()>0 && $("#month").val()<12) {
                        http.post('/Log/AccessLog/Search'+'?month='+$("#month").val()).then(function (result) {
                            if (result.success) {

                                _self.data = result.data.data;
                                _self.xdata = result.data.xdata;
                                _self.ipcount = result.data.ipcount;
                                v.setCharts();
                            } else {
                                swal({
                                    title: result.message,
                                    text: result.error,
                                    type: "error"});
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
                                text: '${date.Year}年'+ $("#month").val() +'月访问统计',
                                left: 'center'
                            },
                            legend: {
                                left: 'left',
                                data: ['${date.Year}年'+ $("#month").val() +'月访问统计']
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
                                    name: '${date.Year}年'+ $("#month").val() +'月访问统计',
                                    data:_self.data,
                                    type: 'line'
                                }
                            ]
                        }
                    );
                    myChart.hideLoading();
                }
            },
            mounted(){
                this.init();
            }
        });

        v.search();




        //$("#month").change(function (e) {
        //    var month = $(this).val();
        //    if (month == 0) {
        //        month = new Date().getMonth() + 1;
        //    }
        //    window.location.href = "/admin/stat/access?month=" + month;
        //});
    </script>
</jsp:attribute>
    <jsp:body>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>访问统计</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/Home/index.html">首页</a>
                    </li>
                    <li class="active">
                        <strong>访问统计</strong>
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
                                                    <select class="selectpicker form-control" data-val="true" data-val-number="字段 Int32 必须是一个数字。" data-val-required="Int32 字段是必需的。" id="month" name="month">
                                                        <option value="0" <c:if test="${date.Month==0}">selected</c:if>>月份</option>
                                                        <option value="1" <c:if test="${date.Month==1}">selected</c:if>>1月</option>
                                                        <option value="2" <c:if test="${date.Month==2}">selected</c:if>>2月</option>
                                                        <option value="3" <c:if test="${date.Month==3}">selected</c:if>>3月</option>
                                                        <option value="4" <c:if test="${date.Month==4}">selected</c:if>>4月</option>
                                                        <option value="5" <c:if test="${date.Month==5}">selected</c:if>>5月</option>
                                                        <option value="6" <c:if test="${date.Month==6}">selected</c:if>>6月</option>
                                                        <option value="7" <c:if test="${date.Month==7}">selected</c:if>>7月</option>
                                                        <option value="8" <c:if test="${date.Month==8}">selected</c:if>>8月</option>
                                                        <option value="9" <c:if test="${date.Month==9}">selected</c:if>>9月</option>
                                                        <option value="10" <c:if test="${date.Month==10}">selected</c:if>>10月</option>
                                                        <option value="11" <c:if test="${date.Month==11}">selected</c:if>>11月</option>
                                                        <option value="12" <c:if test="${date.Month==12}">selected</c:if>>12月</option>
                                                    </select>
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-primary" onclick="v.search()" type="button"><i class="fa fa-search"></i> 搜索</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="col-xs-12">
                                        <div id="resource_month_access_chart" style="height:450px;"></div>
                                    </div>
                                    <div class="col-lg-12">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>IP地址</th>
                                                <th>数量</th>
                                            </tr>
                                            </thead>
                                            <tbody v-cloak>
                                            <tr  v-for="(item,index) in ipcount">
                                                <td>{{item.ipaddress == "127.0.0.1" ? "未知IP" : item.ipaddress}}</td>
                                                <td>{{item.count}}</td>
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
