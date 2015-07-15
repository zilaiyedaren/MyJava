<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-10
  Time: 下午6:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h3>查询结果</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="trainInfos" status="status">
        <span>车站名称：<s:property value="lastStation"/>&nbsp;&nbsp;</span>
        <span>到站时间：<s:property value="arriveTime"/>&nbsp;&nbsp;</span>
        <span>发车时间：<s:property value="startTime"/>&nbsp;&nbsp;</span>
        <span>里程：<s:property value="km"/></span><hr/>
    </s:iterator>
</div>
</body>
</html>