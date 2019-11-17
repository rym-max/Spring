<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/16
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_FrontLayout>
    <jsp:attribute name="scripts">

    </jsp:attribute>
    <jsp:body>
        <div class="content clr">
            <!--当前位置-->
            <div class="locat">
                您现在所在的位置：<a  href="/Home/index.html">网站首页</a> > <a class="current"  href="javascript:;">关于我们</a>
            </div>
            <div class="content wit_bg clr">
                <div class="object-intro">
                    <div class="content_box01 gry_lineb">
                        <!--标题部分开始-->
                        <h5 class="til_h5">
                            <b class="tru">关于我们</b>
                        </h5>
                    </div>
                    <!--内容列表部分开始-->
                    <div class="mt20" style="padding: 0 75px 20px 75px;">
                        <div class="p_div">
                            如有任何意见或建议，请联系同济大学图书馆 平台与数据服务部<br />
                            地址：上海市杨浦区四平路1239号<br />
                            联系人：朱玉梅 老师<br />
                            电话：021-65982734<br />
                            E-Mail：ymzhu@lib.tongji.edu.cn
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:_FrontLayout>