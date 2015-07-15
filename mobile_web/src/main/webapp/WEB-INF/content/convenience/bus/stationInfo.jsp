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
    <h3>公交站点查询结果</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="stationInfoList" status="status">
        <%--name 	string 	站点名称--%>
        <%--xy 	string 	站点坐标 半角逗号,(英文逗号)分隔--%>
        <%--line_names 	string 	途径该站点的线路名称 半角分号;(英文分号)分隔--%>
        <span>途径该站点的线路名称：<s:property value="lineName"/></span><br/>
        <span>站点名称：<s:property value="stationName"/></span>  <br/>
        <span>站点坐标：<s:property value="stationXy"/></span>   <br/>
        <hr/>
    </s:iterator>

</div>
</body>
</html>