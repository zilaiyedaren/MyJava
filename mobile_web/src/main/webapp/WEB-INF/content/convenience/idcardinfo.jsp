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
    <h3>查询</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
    <div data-role="listview">
        <s:iterator value="idCard" status="status">
            <span>身份证号码：<s:property value="idCard"/></span><br/>
            <span>性别：<s:property value="sex"/></span>  <br/>
            <span>出生日期：<s:property value="birthday"/></span>   <br/>
            <span>所在地：<s:property value="address"/> </span> <hr/>
        </s:iterator>
    </div>
</body>
</html>