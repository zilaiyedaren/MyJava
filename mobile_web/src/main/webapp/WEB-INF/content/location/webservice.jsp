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
    <h3>国内飞机航班时刻表查询</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<s:form action="plane" method="post" namespace="/location">
    输入出发城市:<input id="startCity" name="startCity"/>
    输入抵达城市:<input id="lastCity" name="lastCity"/>
    输入出发日期:<input id="theDate" name="theDate"/>
    <button type="submit">提交</button>
    <button type="reset">取消</button>
</s:form>
</body>
</html>
