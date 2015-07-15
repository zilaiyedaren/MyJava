<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h3>Constants.java配置</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>

<s:form action="constants_info" method="post" namespace="/demo"  validate="true">
    请填写文件写入的路径和名称:<input id="filePath" name="filePath"/>
    输入酒店的Passport:<input id="hotelPassport" name="hotelPassport"/>
    输入酒店的预定电话:<input id="bookingPhone" name="bookingPhone"/>
    输入酒店中文名称:<input id="hotelName" name="hotelName"/>
    输入酒店英文名称:<input id="hotelNameEn" name="hotelNameEn"/>
    输入酒店中文简称:<input id="hotelTitle" name="hotelTitle"/>
    输入酒店globleIP:<input id="globleIP" name="globleIP"/>
    输入酒店微信测试服务地址:<input id="mserverUrl" name="mserverUrl"/>
    <%--输入酒店微信正式服务地址:<input id="hotelName" name="hotelName"/>   --%>
    <button type="submit" id="submit">提交</button>
    <button type="reset">取消</button>

</s:form>
</body>
</html>