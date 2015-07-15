<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h3>联系我们配置</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>

<s:form action="contact_info" method="post" namespace="/demo"  validate="true">
    请填写文件写入的路径和名称:<input id="filePath" name="filePath"/>
    <br/>
    输入酒店的地址:<input id="hotelAddressZh" name="hotelAddressZh"/>
    输入酒店英文地址:<input id="hotelAddressEn" name="hotelAddressEn"/>
    输入酒店的邮编:<input id="bookingZip" name="bookingZip"/>
    输入酒店预定电话:<input id="hotelBookPhone" name="hotelBookPhone"/>
    输入酒店传真:<input id="hotelFax" name="hotelFax"/>
    输入酒店电子邮件:<input id="hotelEmail" name="hotelEmail"/>
    <hr/>
    <button type="submit" id="submit">提交</button>
    <button type="reset">取消</button>

</s:form>
</body>
</html>