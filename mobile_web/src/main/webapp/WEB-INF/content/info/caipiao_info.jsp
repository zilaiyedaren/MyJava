<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-9
  Time: 上午11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="b" data-role="header" data-position="fixed">
    <h3><s:property value="caipiaoName"/></h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left" >返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
        <s:iterator value="caipiaoInfoList" status="status">
            <span>开奖期数：<s:property value="caiExpect"/></span>
            <span>开奖时间：<s:property value="caiOpentime"/></span>
            <span>开奖号码：<s:property value="caiOpenCode"/></span>
            <hr/>
        </s:iterator>
</body>
</html>