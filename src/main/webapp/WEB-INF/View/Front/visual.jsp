<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/14
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_FrontLayout>
    <jsp:attribute name="scripts">
<script src="/static/js/echart/echarts.min.js"></script>
<script type="text/javascript">
    var myChart1 = echarts.init(document.getElementById("chart1"));
    var myChart2 = echarts.init(document.getElementById("chart2"));
    var myChart3 = echarts.init(document.getElementById("chart3"));

    var option = {
        title:
            {
                text: "关键词: " +"${hotword}",
                show: "true",
                x: 'center',
                y: '9px',
                textStyle: { //设置主标题风格
                    Color: 'green',//设置主标题字体颜色
                    fontStyle: 'normal',//主标题文字风格
                }
            },
        xAxis: {
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 40
            }
        },
        grid: { // 控制图的大小，调整下面这些值就可以，
            x: 40,
            x2: 100,
            y2: 150,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
        },
        yAxis: {
            type: 'value'
        },
        tooltip: {},
        series: [{
            type: 'line',
            smooth: true
        }]
    };



    function query1() {
        $("#empty-query1").hide();
        $("#error-query1").hide();

        var last_date = $("input[name='last-days-1']:checked").val();
        var start_date = $("#start-date-1").val();
        var end_date = $("#end-date-1").val();
        var query = $("#chart-query1").val();

        if(query.replace(/(^s*)|(s*$)/g, "").length ==0){
            $("#empty-query1").show();
            return;
        }

        myChart1.showLoading();

        var result=null;

        var option1 = option;
        $.ajaxSettings.async=false;
        $.ajax({
            type:"POST",
            url:"/Home/Visual/data",
            data:{
                query:query,
                start_date:start_date,
                end_date:end_date,
                last_date:last_date,
                facet:"dateissued_date"
            },
            success:function (data) {
                if(data.success){
                    result= data.data;
                    console.log(JSON.stringify(result));
                    //主要是这段数据怎么办
                }
            },
            error: function (error) {
                console.error("访问SolrAPI出错----------原因：");
                console.error(error);
            }
        });

        if(result==null){
            $("#error-query1").show();
            console.log(result);
            console.error("出错了，显示错误信息");
            return;
        }

        //设置option
        option1["title"]={
            text:"关键词：" + query,
            show: "true",
            x:"center",
            y:"20px",
            textStyle:{
                Color: "green",
                fontStyle: "noraml",
                fontSize:"20px"
            }
        };
        option1["xAxis"]={
            type:"category",
            data:result.index
        };
        option1["series"]={
            data: result.count,
            type:"line"
        };
        myChart1.setOption(option1);

        myChart1.hideLoading();
        $.ajaxSettings.async =true;
    }

    function query2() {
        $("#empty-query2").hide();
        $("#error-query2").hide();

        var last_date = $("input[name='last-days-2']:checked").val();
        var start_date = $("#start-date-2").val();
        var end_date = $("#end-date-2").val();
        var query = $("#chart-query2").val();

        if(query.replace(/(^s*)|(s*$)/g, "").length ==0){
            $("#empty-query2").show();
            return;
        }

        myChart2.showLoading();

        var result=null;

        var option2 = option;
        $.ajaxSettings.async=false;
        $.ajax({
            type:"POST",
            url:"/Home/Visual/data",
            data:{
                query:query,
                start_date:start_date,
                end_date:end_date,
                last_date:last_date,
                facet:"source_filter"
            },
            success:function (data) {
                if(data.success){
                    result= data.data;
                    console.log(JSON.stringify(result));
                    //主要是这段数据怎么办
                }
            },
            error: function (error) {
                console.error("访问SolrAPI出错----------原因：");
                console.error(error);
            }
        });

        if(result==null){
            $("#error-query2").show();
            console.log(result);
            console.error("出错了，显示错误信息")
            return;
        }

        //设置option
        option2["title"]={
            text:"关键词：" + query,
            show: "true",
            x:"center",
            y:"20px",
            textStyle:{
                Color: "green",
                fontStyle: "noraml",
                fontSize: "20px"
            }
        };
        option2["xAxis"]={
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 40
            },
            data:result.index
        };
        option2["series"]=[{
            data: result.count,
            type: 'bar'
        }];
        myChart2.setOption(option2);

        myChart2.hideLoading();
        $.ajaxSettings.async =true;
    }

    function query3() {
        $("#empty-query3").hide();
        $("#error-query3").hide();

        var last_date = "";
        var start_date = $("#start-date-3").val();
        var end_date = $("#end-date-3").val();
        var query = $("#chart-query3").val();

        if(query.replace(/(^s*)|(s*$)/g, "").length ==0){
            $("#empty-query3").show();
            return;
        }

        myChart3.showLoading();

        var result=null;

        var option3 = option;
        $.ajaxSettings.async=false;
        $.ajax({
            type:"POST",
            url:"/Home/Visual/data",
            data:{
                query:query,
                start_date:start_date,
                end_date:end_date,
                last_date:last_date,
                facet:"source_filter",
                journal:true
            },
            success:function (data) {
                if(data.success){
                    result= data.data;
                    console.log(JSON.stringify(result));
                    //主要是这段数据怎么办
                }
            },
            error: function (error) {
                console.error("访问SolrAPI出错----------原因：");
                console.error(error);
            }
        });

        if(result==null){
            $("#error-query3").show();
            console.log(result);
            console.error("出错了，显示错误信息")
            return;
        }

        //设置option
        option3["title"]={
            text:"关键词：" + query,
            show: "true",
            x:"center",
            y:"20px",
            textStyle:{
                Color: "green",
                fontStyle: "noraml",
                fontSize: "20px"
            }
        };
        delete option3["xAxis"];
        delete option3["yAxis"];
        option3["legend"] = {
            orient: 'vertical',
            x: 'left',
            data: result.index
        };
        option3["tooltip"]={
            trigger: 'item',
            formatter: "{b}: <br/>{c} ({d}%)"
        },
        option3["series"]=[{
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '18',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: result.data_li
        }];
        myChart3.setOption(option3);

        myChart3.hideLoading();
        $.ajaxSettings.async =true;
    }


    function select1() {
    if($("input[name='last-days-1']:checked").val() === ""){
    $("#start-date-tag-1").show();
    $("#end-date-tag-1").show();
    }else {
    $("#start-date-tag-1").hide();
    $("#end-date-tag-1").hide();
    }
    }

    function select2() {
    if($("input[name='last-days-2']:checked").val() === ""){
    $("#start-date-tag-2").show();
    $("#end-date-tag-2").show();
    }else {
    $("#start-date-tag-2").hide();
    $("#end-date-tag-2").hide();
    }
    }

    function initVisual() {
        var current_date = new Date();
        var end_date = current_date.getFullYear()+"-"+current_date.getMonth()+"-"+ current_date.getDay()<10?"0":"" +current_date.getDay();
        current_date.setTime(current_date.getTime()-30*24*60*60*1000);
        var start_date = current_date.getFullYear()+"-"+current_date.getMonth()+"-"+ current_date.getDay()<10?"0":"" +current_date.getDay();

        $(".start-date").val(start_date);
        $(".end-date").val(end_date);

        query1();
        query2();
        query3();
    }
    initVisual();

    </script>
</jsp:attribute>
<jsp:body>
    <!--主体内容开始-->
    <div>
    <div class="content clr">
    <!--当前位置-->
    <div class="locat">
    您现在所在的位置：<a  href="/Home/index.html">网站首页</a> > <a class="current"  href="javascript:;">可视图表</a>
    </div>
    <!--折线图 根据关键词-->
    <div class="content pt20 clr" >
    <h1 class="til_h1">
    <b>时间趋势图</b>
    <sup class="">Time Series Plot</sup>
    </h1>
    <!--3张-->
    <div class="search_list">
    <dl style="height: 45px">
    <div style="float: left;">
    关键词：<input id="chart-query1" value="${hotword}" placeholder="请输入关键词" type="text" >
    <button onclick="query1()">搜索</button>
    <br>
    <div id="empty-query1" style="color: red;" hidden>请输入查询关键词！</div>
    <div id="error-query1" style="color: red;" hidden>查询结果失败！</div>
    </div>
    <div style="float: right">
    <input class="chart-radio-1" type="radio" value="7" name="last-days-1" onclick="select1(this)" checked>7天内
    <input class="chart-radio-1" type="radio" value="30" name="last-days-1" onclick="select1(this)">30天内
    <input class="chart-radio-1" type="radio" value="" name="last-days-1" onclick="select1(this)">自定义
    <div id="start-date-tag-1" hidden>
    起始日期：<input id="start-date-1" class="start-date" type="date" value="">
    </div>
    <div id="end-date-tag-1" hidden>
    结束日期：<input id="end-date-1" class="end-date" type="date" value="">
    </div>
    </div>
    </dl>
    </div>
    <br>
    <br>
    <dl>
    <div id="chart1" class="chart1" style="height: 400px;width: 100%;"></div>
    </dl>
    </div>
    <!--柱状图 根据关键词-->
    <div class="content pt20 clr" >
    <h1 class="til_h1">
    <b>来源分布图</b>
    <sup class="">Source Distribution Plot</sup>
    </h1>
    <!--3张-->
    <div class="search_list">
    <dl style="height: 45px">
    <div style="float: left;">
    关键词：<input id="chart-query2" value="${hotword}" placeholder="请输入关键词" type="text">
    <button onclick="query2()">搜索</button>
    <br>
    <div id="empty-query2" style="color: red;" hidden>请输入查询关键词！</div>
    <div id="error-query2" style="color: red;" hidden>查询结果失败！</div>
    </div>
    <div style="float: right">
    <input class="chart-radio-2" type="radio" value="7" name="last-days-2" onclick="select2(this)" checked>7天内
    <input class="chart-radio-2" type="radio" value="30" name="last-days-2" onclick="select2(this)">30天内
    <input class="chart-radio-2" type="radio" value="" name="last-days-2" onclick="select2(this)">自定义
    <div id="start-date-tag-2" hidden>
    起始日期：<input id="start-date-2" class="start-date" type="date" value="">
    </div>
    <div id="end-date-tag-2" hidden>
    结束日期：<input id="end-date-2" class="end-date" type="date" value="">
    </div>
    </div>
    </dl>
    </div>
    <br>
    <br>
    <dl>
    <div id="chart2" class="chart2" style="height: 400px;width: 100%;"></div>
    </dl>
    </div>

    <!--饼图 针对文献 关键词-->
    <div class="content pt20 clr" >
    <h1 class="til_h1">
    <b>期刊分布图</b>
    <sup class="">Journal Distribution Plot</sup>
    </h1>
    <div class="search_list">
    <dl style="height: 45px">
    <div style="float: left;">
    关键词：<input id="chart-query3" value="${hotword}" placeholder="请输入关键词" type="text">
    <button onclick="query3()">搜索</button>
    <br>
    <div id="empty-query3" style="color: red;" hidden>请输入查询关键词！</div>
    <div id="error-query3" style="color: red;" hidden>查询结果失败！</div>
    </div>
    <div style="float: right">
    <div id="start-date-tag-3">
    起始年份：<input id="start-date-3" class="start-date" type="date" value="">
    </div>
    <div id="end-date-tag-3">
    结束年份：<input id="end-date-3" class="start-date" type="date" value="">
    </div>
    </div>
    </dl>
    </div>
    <br>
    <br>
    <dl>
    <div id="chart3" class="chart3" style="height: 400px;width: 100%;"></div>
    </dl>
    </div>
    </div>




    </div>
</jsp:body>

</t:_FrontLayout>
