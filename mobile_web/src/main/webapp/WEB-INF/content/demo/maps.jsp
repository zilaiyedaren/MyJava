<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h3>地图配置针对HTML</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>

<s:form action="maps_info" method="post" namespace="/demo"  validate="true">
    请填写文件写入的路径和名称:<input id="filePath" name="filePath"/>
    输入酒店所在纬度:<input id="latitude" name="latitude"/>
    输入酒店所在经度:<input id="longitude" name="longitude"/>
    输入描述内容:<input id="content" name="content"/>
    输入酒店名称:<input id="hotelName" name="hotelName"/>
    <button type="submit" id="submit">提交</button>
    <button type="reset">取消</button>
</s:form>
</body>
</html>