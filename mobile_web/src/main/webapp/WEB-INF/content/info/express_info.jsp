<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-8
  Time: 下午6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h3><s:property value="expressName"/></h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="expressInfoList" status="status">
        <s:if test="expressTime==null">
            <span>未能找到您的order信息</span>
        </s:if><s:else>
        <span><s:property value="expressTime"/></span><br/>
        <span><s:property value="expressContext"/></span><br/>
        <span><s:property value="expressFtime"/></span><br/>
        <hr/>
    </s:else>
    </s:iterator>
</div>

</body>
</html>