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
    <h3>查询结果</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<div data-role="listview">
    <s:iterator value="isbnInfo" status="status">
        <span>书名:《<s:property value="title"/>》</span><hr/>
        <span>作者：<s:property value="author"/></span> <hr/>
        <span>作者介绍：<s:property value="authorInfo"/></span>  <hr/>
        <span>页数：<s:property value="pages"/></span>   <hr/>
        <s:if test="translator=='null'"></s:if><s:else><span>译者：<s:property value="translator"/></span> <hr/></s:else>
        <span>定价：<s:property value="price"/></span> <hr/>
        <span>装帧：<s:property value="binding"/></span> <hr/>
        <span>出版社：<s:property value="publisher"/></span> <hr/>
        <span>出版日期：<s:property value="pubdate"/></span> <hr/>
    </s:iterator>
</div>
</body>
</html>