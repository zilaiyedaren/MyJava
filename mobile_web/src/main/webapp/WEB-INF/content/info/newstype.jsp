<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-6
  Time: 下午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div data-role="header" class="" data-theme="a" data-position="fixed">
    <h4>新闻</h4>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a> <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
    <s:iterator value="newsTypeList" status="status">
        <div data-role="content" data-theme="c" style="padding: 25px;">
            <div data-role="listview">
                <a href="<s:url action="newslist"><s:param name="newsType_Id" value="newsTypeId" /></s:url>">
                    <h3><s:property value="newsTypeName"/></h3>
                </a>


        </div>
        </div>
        <%--<span class="arrow-left"></span>--%>
    </s:iterator>

</body>
</html>