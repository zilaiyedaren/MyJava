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
    <h3>公交线路查询结果</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="lineInfoList" status="status">
        <%--name 	string 	--%>
        <%--info 	string 	（包括线路类型、票价、首末班车时间等）--%>
        <%--stats 	string 	 半角分号;(英文分号)分隔--%>
        <span>线路名称：<s:property value="name"/></span><br/>
        <span>线路信息：<s:property value="info"/></span>  <br/>
        <span>沿途站点：<s:property value="stats"/></span>   <br/>
        <hr/>
    </s:iterator>

</div>
</body>
</html>