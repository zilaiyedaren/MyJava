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
    <h3>影院结果</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="cinemaInfoList" status="status">
        <span>影院地址：<s:property value="address"/></span><br/>
        <span>影院名称：<s:property value="name"/></span>  <br/>
        <span>影院电话：<s:property value="telphone"/></span>   <br/>
        <hr/>
        <span>电影：<s:iterator value="movie" status="status">
             <span>影片简介<s:property value=" movieDescription"/></span>
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
            <span>影片概要信息：<s:property value="movieMessage"/></span><hr/>
        </s:iterator>
        </span>
        <hr/>
        <span>网友点评：<s:iterator value="review" status="status">
                <p><s:property value="content"/></p>
                <span><s:property value="date"/></span> <hr/>
            </s:iterator>
             <hr/><hr/>
        </span>
    </s:iterator>

</div>
</body>
</html>