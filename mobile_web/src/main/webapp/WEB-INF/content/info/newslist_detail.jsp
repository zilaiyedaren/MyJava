<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-8
  Time: 下午3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>新闻详情</title>
</head>
<body>
       <div data-theme="a" data-role="header" data-position="fixed">
           <h4><s:property value="news.newsTag"/></h4>
           <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
           <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" class="ui-btn-right">主页</a>
       </div>
    <div data-role="listview" data-divider-theme="c" data-inset="true">
        <span><s:property value="news.newsTitle"/></span> <br/>
        <span><s:property value="news.newsMessage" escapeHtml="false"/></span> <br/>
        <span><s:property value="news.newsCount"/></span> <br/>
    </div>
</body>
</html>