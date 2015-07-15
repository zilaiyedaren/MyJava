<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-10
  Time: 下午1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>在线翻译</title>
</head>
<body>
      <div data-role="header" data-position="fixed" data-theme="b">
          <h3>在线翻译</h3>
          <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
          <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
      </div>
    <div>
        <span>请选择需要翻译的语言</span>
        <%--<s:form action="translate_info" method="post" value="add">--%>
        <s:form action="translate_info" method="get">
        <select data-theme="b" name="translateType">
            <option value="zh_en">中 -> 英</option>
            <option value="en_zh">英 -> 中</option>
            <option value="zh_jp">中 -> 日</option>
            <option value="jp_zh">日 -> 中</option>
        </select>
            <textarea id="translateText" name="translateText" autofocus required style="width: 100%;height:100px" placeholder="请在次输入要翻译的文本"><s:property value="translateInfo.getTranslateSrc()"></s:property></textarea>
             <button type="submit" data-theme="b">翻译</button>
             <button type="reset"  data-theme="b">重置</button>
        </s:form>
        <textarea style="width: 100%;height:100px" name="dst" id="dst"><s:property value="translateInfo.getTranslateDst()"></s:property></textarea>
    </div>
</body>
</html>