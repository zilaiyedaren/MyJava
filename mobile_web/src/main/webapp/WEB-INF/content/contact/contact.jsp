<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-5
  Time: 下午3:13
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>联系我们</title>
</head>
<body>
      <div data-theme="a" data-role="header" class="" data-position="fixed">
          <h3 class="">
              联系我们
          </h3>
          <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">返回</a>
          <a data-role="button" href="<s:url action="index" namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="right" class="ui-btn-right">主页</a>
      </div>
</body>
</html>