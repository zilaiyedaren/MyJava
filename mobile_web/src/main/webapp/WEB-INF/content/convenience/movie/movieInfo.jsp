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
        <s:iterator value="movieInfoList" status="status">
            <span>是否首映：<s:if test="movieIsNew==1">首映</s:if><s:else>非首映</s:else></span><br/>
            <span>是否imax类型：<s:if test="movieIsImax==1">IMAX</s:if>非IMAX</span><br/>
            <span>影片id：<s:property value="movieId"/></span><br/>
            <span>影片名称：<s:property value="movieName"/></span><br/>
            <span>影片类型：<s:property value="movieType"/></span><br/>
            <span>影片上映时间：<s:property value="movieReleaseDate"/></span><br/>
            <span>影片所属国家：<s:property value="movieNation"/></span><br/>
            <span>影片演员：<s:property value="movieStarring"/></span><br/>
            <span>影片时长：<s:property value="movieLength"/></span><br/>
            <span>影片图片：<img src="<s:property value="moviePicture"/>"/></span><br/>
            <span>影片评分：<s:property value="movieScore"/></span><br/>
            <span>影片导演：<s:property value="movieDirector"/></span><br/>
            <span>影片所属类型：<s:property value="movieTags"/></span><br/>
            <span>影片概要信息：<s:property value="movieMessage"/></span><br/>
            <span>影片关键字：<s:property value="movieWd"/></span><br/>
            <hr/>
        </s:iterator>
</div>
</body>
</html>