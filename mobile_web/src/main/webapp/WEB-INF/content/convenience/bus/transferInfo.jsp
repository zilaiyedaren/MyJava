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
    <h3>公交换乘查询结构</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="transferInfoList" status="status">
        <%--dist 	int 	 单位：米--%>
        <%--stats 	int 	估计耗费时间 单位：分钟--%>
        <%--foot_dist 	int 	总步行距离 单位：米--%>
        <%--last_foot_dist 	int 	从终点站走到终点的距离 单位：米--%>
        <%--start_stat 	string 	起点站名称--%>
        <%--start_end 	string 	终点站名称--%>
        <%--line_name 	string 	线路名称--%>
        <%--stats 	string 	沿途站点 半角分号;（英文分号）分隔--%>
        <%--line_dist 	int 	行驶距离 单位：米--%>
        <%--foot_dist 	int 	步行距离 单位：米--%>
        <span>总距离：<s:property value="dist"/>米</span><br/>
        <span>估计耗费时间：<s:property value="time"/>分钟</span>  <br/>
        <span>总步行距离：<s:property value="foot_dist"/>米</span>   <br/>
        <span>从终点站走到终点的距离：<s:property value="last_foot_dist"/>米</span> <br/>
        <span>起点站名称：<s:property value="start_stat"/></span> <br/>
        <span>终点站名称：<s:property value="start_end"/></span> <br/>
        <span>线路名称：<s:property value="line_name"/></span> <br/>
        <span>沿途站点：<s:property value="stats"/></span> <br/>
        <span>行驶距离：<s:property value="line_dist"/>米</span> <br/>
        <span>步行距离：<s:property value="dist_end"/>米</span> <br/>
        <hr/>
    </s:iterator>

</div>
</body>
</html>