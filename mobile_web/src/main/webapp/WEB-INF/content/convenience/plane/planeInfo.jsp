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
    <s:iterator value="planeAirLineList" status="status">
        <span>航空公司：<s:property value="companyName"/></span><br/>
        <span>航班号：<s:property value="airlineCode"/></span><br/>
        <span>出发机场：<s:property value="startDrome"/></span><br/>
        <span>到达机场：<s:property value="arriveDrome"/></span><br/>
        <span>出发时间：<s:property value="startTime"/></span><br/>
        <span>到达时间：<s:property value="arriveTime"/></span><br/>
        <span>机型：<s:property value="mode"/></span> <br/>
        <span>经停：<s:property value="airlineStop"/></span><br/>
        <span>飞行周期：<s:property value="week"/></span><hr/>
    </s:iterator>
</div>
</body>
</html>