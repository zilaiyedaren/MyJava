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
<s:form action="planeInfo" method="post" namespace="/convenience/plane">
    输入出发城市:<input id="startCity" name="startCity"/>
    输入抵达城市:<input id="arriveCity" name="arriveCity"/>
    输入出发日期:<input id="theDate" name="theDate"/>
    <button type="submit">提交</button>
    <button type="reset">取消</button>
</s:form>
</body>
</html>
<%--http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx--%>
<%--getDomesticAirlinesTime--%>

<%--获得航班时刻表 DataSet--%>

<%--输入参数：startCity = 出发城市（中文城市名称或缩写、空则默认：上海）；lastCity = 抵达城市（中文城市名称或缩写、空则默认：北京）；theDate = 出发日期（String 格式：yyyy-MM-dd，如：2007-07-02，空则默认当天）；userID = 商业用户ID（免费用户不需要）--%>
<%--返回数据：DataSet，Table(0)结构为 Item(Company)航空公司、Item(AirlineCode)航班号、Item(StartDrome)出发机场、Item(ArriveDrome)到达机场、Item(StartTime)出发时间、Item(ArriveTime)到达时间、Item(Mode)机型、Item(AirlineStop)经停、Item(Week)飞行周期（星期）--%>