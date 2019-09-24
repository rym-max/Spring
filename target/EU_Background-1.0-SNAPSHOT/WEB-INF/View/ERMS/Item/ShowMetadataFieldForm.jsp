<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/7/29
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach var="spiderItem" items="${items}" varStatus="s">
    <c:set var="fieldName" scope="page" value="${spiderItem.fieldName}" />
    <c:set var="values" scope="page" value="${spiderItem.values}" />
    <c:set var="defaultValue" scope="page" value="${spiderItem.defaultValue}" />
    <c:set var="options" scope="page" value="${spiderItem.options}" />
    <div class="form-group">
        <label class="col-sm-2 control-label" for="${fieldName}">${spiderItem.name}</label>
        <c:if test="${spiderItem.controlType.code ==0}">
            <%--//text--%>
            <c:if test="${!spiderItem.isMultiple}">
                <div class="col-sm-10">
                    <input class="form-control" id="${fieldName}" name="${fieldName}" type="text" value="${values[0]}">
                </div>
            </c:if>
            <c:if test="${spiderItem.isMultiple}">
                <c:forEach var="oneMeta" items="${values}" varStatus="metas">

                    <c:if test="${metas.index>0}">
                        <label class="col-sm-2 control-label" for="${fieldName}"></label>
                    </c:if>
                    <div class="col-sm-8">
                        <input class="form-control" id="${fieldName}" name="${fieldName}" type="text" value="${oneMeta}">
                    </div>
                    <c:if test="${metas.index==0}">
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-default" onclick="CreateMultiple(this);"><i class="fa fa-plus"></i></button>
                        </div>
                    </c:if>
                    <c:if test="${metas.index>0}">
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-default" onclick="RemoveMultiple(this);"><i class="fa fa-minus"></i></button>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:if>

        <c:if test="${spiderItem.controlType.code ==1}">
            <%--//multilinetext--%>
            <c:if test="${!spiderItem.isMultiple}">
                <div class="col-sm-10">
                    <textarea class="form-control" cols="20" id="${fieldName}" name="${fieldName}" rows="2">
                        <c:out value="${values[0]}" />
                    </textarea>
                </div>
            </c:if>
            <c:if test="${spiderItem.isMultiple}">
                <c:forEach var="oneMeta" items="${values}" varStatus="metas">
                    <c:if test="${metas.index>0}">
                        <label class="col-sm-2 control-label" for="${fieldName}"></label>
                    </c:if>
                    <div class="col-sm-8">
                    <textarea class="form-control" cols="20" id="${fieldName}" name="${fieldName}" rows="2">
                        <c:out value="${oneMeta}" />
                    </textarea>
                    </div>
                    <c:if test="${metas.index==0}">
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-default" onclick="CreateMultiple(this);"><i class="fa fa-plus"></i></button>
                        </div>
                    </c:if>
                    <c:if test="${metas.index>0}">
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-default" onclick="RemoveMultiple(this);"><i class="fa fa-minus"></i></button>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:if>

        <c:if test="${spiderItem.controlType.code ==2}">
            <%--//html--%>
            <div class="col-sm-10">
                    <textarea class="form-control kindeditor" cols="20" id="${fieldName}" name="${fieldName}" rows="2">
                        <c:out value="${values[0]} " />
                    </textarea>
            </div>
        </c:if>

        <c:if test="${spiderItem.controlType.code ==3}">
            <%--//select--%>
            <div class="col-sm-10">
                <select class="from-control" id="${fieldName}" name="${fieldName}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${options}" var="opt">
                        <option value="${opt}" <c:if test="${opt eq values[0]}">selected</c:if> >${opt}</option>
                    </c:forEach>
                </select>
            </div>
        </c:if>

        <c:if test="${spiderItem.controlType.code ==4}">
            <%--//radio--%>
            <c:forEach items="${options}" var="opt">
                <input type="radio" class="i-checks" value="${opt}" name="${fieldName}" <c:if test="${opt eq values[0]}">checked="checked"</c:if> />
                ${opt}
            </c:forEach>
        </c:if>

        <c:if test="${spiderItem.controlType.code ==5}">
            <%--//checkbox--%>
            <c:forEach items="${options}" var="opt">
                <input type="checkbox" class="i-checks" value="${opt}" name="${fieldName}" <c:if test="${fn:contains(values, opt)}">checked="checked"</c:if> />
                ${opt}
            </c:forEach>
        </c:if>

        <c:if test="${spiderItem.controlType.code == 6}">
            <%--//datetime--%>
            <div class="col-sm-10">
                <input class="form-control laydate" format="yyyy-MM-dd hh:mm:ss" id="${fieldName}" name="${fieldName}" type="text" value="${values[0]}" />
            </div>
        </c:if>

        <c:if test="${spiderItem.controlType.code == 7}">
            <%--//date--%>
            <div class="col-sm-10">
                <input class="form-control laydate" format="yyyy-MM-dd" id="${fieldName}" name="${fieldName}" type="text" value="${values[0]}" />
            </div>
        </c:if>



        <c:if test="${spiderItem.controlType.code <0 || spiderItem.controlType.code > 9}">
            <%--//others--%>
            <div class="col-sm-8">
                <input class="form-control" id="${fieldName}" name="${fieldName}" type="text" value="${values[0]}">
            </div>
        </c:if>
    </div>
</c:forEach>

<script type="text/javascript">
    $(function () {
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            $(".laydate").each(function () {
                laydate.render({ elem: this, festival: true, format: $(this).attr("format") });
            });
        });
    })
    function CreateMultiple(obj) {
        var html = $(obj).parent().parent().html();
        html = html.replace("fa-plus", "fa-minus");
        html = html.replace("CreateMultiple", "RemoveMultiple");
        html = "<div class=\"form-group\">" + html + "</div>";
        var htmlObject = $(html);
        htmlObject.find(".control-label").html("");
        $(obj).parent().parent().after("<div class=\"form-group\">" + htmlObject.html() + "</div>");
    }
    function RemoveMultiple(obj) {
        $(obj).parent().parent().remove();
    }
</script>