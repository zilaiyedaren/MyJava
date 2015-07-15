<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-11-30
  Time: 下午1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>支付宝支付</title>
</head>
<body>
<div data-theme="b" data-role="header" data-position="fixed">
    <h3>支付宝支付</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
          <s:form action="pay_index" enctype="multipart/form-data" mothod="post">
              <img src="http://imge11.wbiao.cn/201112/02/5734_12766.jpg" width="200" height="200" style="margin-left: 10px;"><br/>
                  <label>价格：￥</label><input id="price" name="price" type="text" value="0.01" style="display:inline"/>
              <input type="submit" value="购买"/>
          </s:form>

</body>
</html>