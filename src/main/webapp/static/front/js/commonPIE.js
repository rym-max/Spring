/**

//jQuery 1.9以下
(function($){
    $.pie = function(name, v){
        // 如果没有加载 PIE 则直接终止
        if (! PIE) return false;
        // 是否 jQuery 对象或者选择器名称
        var obj = typeof name == 'object' ? name : $(name);
        // 指定运行插件的 IE 浏览器版本
        var version = 9;
        // 未指定则默认使用 ie10 以下全兼容模式
        if (typeof v != 'number' && v < 9) {
            version = v;
        }
        // 可对指定的多个 jQuery 对象进行样式兼容
        if ($.browser.msie && obj.size() > 0) {
            if ($.browser.version*1 <= version*1) {
                obj.each(function(){
                    PIE.attach(this);
                });
            }
        }
    }
})(jQuery);
$(function(){
    $.pie('.input');
});
**/
//jQuery 1.9及以上
(function($){
    $.pie = function(name){
        if (! PIE) return false;
        var obj = typeof name == 'object' ? name : $(name);
        if (!$.support.leadingWhitespace && obj.size() > 0) {
                obj.each(function(){
                    PIE.attach(this);
                });
        }
    }
})(jQuery);
$(function(){
	$.pie('.searchTop,.loginPlate,.radius2,.radius4,.radius5,.radius5_dlr,.input,.radius_btn,.blue_btn,.Mark,.search-btn,.login_pop,.boxwrap,.select-tit,.radiusjd,.nav_pic,.zj_pic,.expert_photo,.user_potopic,.red_btn,.ly_jd,.red_btnmin01,.left_box,.right_box,.tile_right,.selr');
});