<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h3>酒店设施jsp文件</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>

<s:form id="facilities" action="maps_info" method="post" namespace="/demo"  validate="true"  onsubmit="return validate_form(this)">
    请填写模块中文名称:<input id="modeNameZh" name="modeNameZh"/>
    请填写模块英文名称:<input id="modeNameEn" name="modeNameEn"/>
    请填写文件写入的路径和名称:<input id="filePath" name="filePath"/>
    请填写模块中类别的个数:<input id="modeNum" name="modeNum"/>
    所有类别英文描述以|作为分分隔:<textarea id="textEn" name="textEn" style="height:200px"></textarea>
    所有类别中文描述以|作为分分隔:<textarea id="textZh" name="textZh" style="height:200px"> </textarea>
    <button type="submit" id="submit">提交</button>
    <button type="reset">取消</button>
</s:form>
</body>
</html>