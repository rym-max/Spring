layui.use(['layer', 'layedit', 'laydate'], function () {
    var layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;

    $(function () {
        app.init();
        $(document).pjax('a[data-pjax]', '#page-content');
        ///pjax开始（与服务器连接建立后触发）
        //$(document).on('pjax:start', function () {
        //    alert("开始");
        //});
        //$(document).on('pjax:send', function () {
        //    //layer.load();
        //})
        /////pjax成功后触发
        //$(document).on('pjax:success', function () {
        //    //layer.closeAll();
        //    app.init();
        //});
        //$(document).on('pjax:complete', function () {
        //    alert("完成！");
        //})
        //$(document).on('pjax:end', function () {
        //    alert("结束");
        //})

        //pjax出错后触发
        //$(document).on('pjax:error', function (e) {
        //    layer.msg("服务器繁忙，请稍后在试！", { icon: 2 });
        //    e.preventDefault();
        //});

        $("#side-menu li a").click(function () {
            $(this).parent().siblings("li").removeClass("active");
            $(this).parent().addClass("active");
        });

        $(".laydate").each(function () {
            laydate.render({ elem: this, festival: true, format: $(this).attr("format") });
        });
    })
    
})

var app = {
    vue(options) {
        var index;
        var defaults = {
            el: "#page-content",
            loading: false,
            beforeCreate() {
                if (options.loading) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        index = layer.load();
						window.setTimeout(function () {
                            layer.close(index);
                        }, 2000);
                    });
                }
            },
            updated: function () {
                app.init();
                if (options.loading) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.close(index);
                    });
                }
            },
            methods: {
                get_value(fields, key) {
                    if (fields[key] == undefined) {
                        return "";
                    }
                    var value = fields[key];
                    if (value instanceof Array) {
                        return value[0];
                    } else {
                        return value;
                    }
                },
                get_value_split(fields, key, split) {
                    if (fields[key] == undefined) {
                        return "";
                    }
                    var value = fields[key];
                    if (value instanceof Array) {
                        var str = "";
                        if (value.length > 0) {
                            str = value[0];
                        }
                        for (var i = 1; i < value.length; i++) {
                            str += (split + value[i]);
                        }
                        return str;
                    } else {
                        return value;
                    }
                }
            }
        }
        options = $.extend(true, defaults, options);
        return new Vue(options);
    },
    init() {
        $("#page-content .openWindow").unbind("click");
        $("#page-content .openWindow").click(function (event) {
            event.preventDefault();
            var href = $(this).attr("href");
            var title = $(this).attr("title");
            var width = $(this).attr("width");
            var height = $(this).attr("height");
            if (width === undefined) {
                width = "80%;"
            }
            if (height === undefined) {
                height = "80%;"
            }
            layer.open({
                type: 2 //此处以iframe举例
                , title: title
                //, area: ['80%', '80%']
                , area: [width, height]
                , shade: 0.2
                , maxmin: true
                , content: href
                , btn: ['确定', '关闭']
                , yes: function (index, layero) {
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    var form = body.find('form')[0];

                    var loading = iframeWin.contentWindow.layer.load();//显示loading提示
                    var options = {
                        success: function (result) {
                            //关闭loading提示
                            iframeWin.contentWindow.layer.close(loading);
                            if (result.success) {
                                swal({
                                    title: result.message,
                                    type: "success"
                                }, function () {
                                    setTimeout(function () {
                                        //关闭Iframe
                                        layer.close(index);
                                        if (v && typeof (v.refresh) === "function") {
                                            v.refresh();
                                        } else if (v && typeof (v.search) === "function") {
                                            v.search();
                                        }
                                    }, 500);//延时0.1秒，对应360 7.1版本bug
                                });
                                //iframeWin.contentWindow.jmsg(result.message);
                                //刷新当前列表页数据
                                //$("#jqGrid").jqGrid("setGridParam").trigger("reloadGrid");  //重载JQGrid
                                //setTimeout(function () {
                                //    //关闭Iframe
                                //    layer.close(index);
                                //    if (typeof successCallback === 'function') {
                                //        successCallback.call(this);
                                //    }
                                //}, 1000);//延时0.1秒，对应360 7.1版本bug

                            } else {
                                swal({
                                    title: result.message,
                                    text: result.error,
                                    type: "error"
                                });
                            }
                        }
                    }
                    $(form).ajaxSubmit(options);
                }
                , btn2: function () {
                    layer.closeAll();
                }
            });
        });

        $("#page-content .ajax").unbind("click");
        $("#page-content .ajax").click(function (event) {
            event.preventDefault();
            _a = this;
            var href = $(_a).attr("href");
            //var title = $(_a).attr("title");
            var title = $(_a).text();
            swal({
                title: "你确定要执行" + title + "操作?",
                //text: "Your will not be able to recover this imaginary file!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
                //closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    axios.post(href).then(function (response) {
                        var result = response.data;
                        if (result.success) {
                            swal({
                                title: result.message,
                                type: "success"
                            }, function () {
                                if (v && typeof (v.refresh) === "function") {
                                    v.refresh();
                                } else if (v && typeof (v.search) === "function") {
                                    v.search();
                                }
                            });

                        } else {
                            swal({
                                title: result.message,
                                text: result.error,
                                type: "error"
                            });
                        }
                    }).catch(function (error) {

                    });

                } else {

                }
            });
        });
        //初始化复选框
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    }
}

function ConvertJSONDateToJSDateObject(jsondate, fmt) {
    var date = new Date(parseInt(jsondate.replace("/Date(", "").replace(")/", ""), 10));
    if (fmt == undefined) {
        return date;
    }
    var o = {
        "M+": date.getMonth() + 1, //月份 
        "d+": date.getDate(), //日 
        "h+": date.getHours(), //小时 
        "m+": date.getMinutes(), //分 
        "s+": date.getSeconds(), //秒 
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
        "S": date.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}