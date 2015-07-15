<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-6
  Time: 下午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div data-role="header" class="" data-theme="a" data-position="fixed">
    <h4>新闻列表</h4>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
<s:iterator value="newsInfoList" status="status">
    <%--<div data-role="content" data-theme="c" style="padding: 5px;">--%>
        <div data-role="listview">
            <a href="<s:url action="newslist_detail"><s:param name="news_id" value="newsId" /></s:url>">
                <h3><s:property value="newsTitle"/></h3>
                <h3><s:property value="newsTag"/></h3>
                <h3><s:property value="newsTime"/></h3>
                <h3><s:property value="newsCount"/></h3>
                <%--<h3><s:property value="newsImg"/></h3>--%>
                <s:if test="newsImg=='http://www.yi18.net/'">

                </s:if><s:else>
                    <img src="<s:property value="newsImg" escapeHtml="false"/>">
                </s:else>
            </a>
            <span class="arrow-left"></span>
            <hr/>
        <%--</div>--%>
    </div>

</s:iterator>

</body>
</html>