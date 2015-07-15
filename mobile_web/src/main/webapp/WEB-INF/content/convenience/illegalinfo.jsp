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
    <title>详细信息</title>
</head>
<body>
      <div data-role="header" data-theme="a" data-position="fixed">
          <h3>详细信息</h3>
          <a data-role="button" href="javascript:history.go(-1);"data-icon="arrow-l" data-iconpos="left" class="ui-btn-left" rel="external">返回</a>
          <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" class="ui-btn-right" rel="external">主页</a>
      </div>
        <div data-role="listview">
              <s:iterator value="illegalInfoList" status="status">
                    <%--<s:if test="illegalInfo==null">--%>
                        <%--<span>未能找到违章信息</span>--%>
                    <%--</s:if><s:else>--%>
                          <%--<span>总共违章<s:property value="illegalCount"/>次</span><br/>--%>
                            <span>违章行为:<s:property value="illegalAct"/></span><br/>
                            <span>处理机构:<s:property value="illegalAgency"/></span><br/>
                            <span>违章地点:<s:property value="illegalArea"/></span><br/>
                            <span>处理情况:<s:property value="illegalChuli"/></span><br/>
                            <span>违章时间:<s:property value="illegalDate"/></span> <br/>
                            <span>罚款:<s:property value="illegalMoney"/></span> <br/>
                            <span>扣分:<s:property value="illegalOrg_fen"/></span><br/>
                  <hr/>
                    <%--</s:else>--%>
              </s:iterator>
        </div>
</body>
</html>