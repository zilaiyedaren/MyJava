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
    <s:iterator value="trainInfoList" status="status">
        <span>车次：<s:property value="trainCode"/>&nbsp;&nbsp;</span>   <br/>
        <span>始发站：<s:property value="firstStation"/>&nbsp;&nbsp;</span>  <br/>
        <span>终点站：<s:property value="lastStation"/>&nbsp;&nbsp;</span>  <br/>
        <span>发车站：<s:property value="startStation"/></span>   <br/>
        <span>发车时间：<s:property value="startTime"/></span>  <br/>
        <span>到达站：<s:property value="arriveStation"/></span> <br/>
        <span>到达时间：<s:property value="arriveTime"/></span> <br/>
        <span>里程：<s:property value="km"/>km</span><br/>
        <span>历时：<s:property value="useDate"/></span><hr/>
    </s:iterator>
</div>
</body>
</html>