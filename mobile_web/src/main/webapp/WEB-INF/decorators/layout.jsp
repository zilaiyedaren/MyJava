<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-5
  Time: 下午1:07
  To change this template use File | Settings | File Templates.
--%>
<%--<%@page language="java" pageEncoding="UTF-8" %>--%>
<%--<%@taglib prefix="s" uri="/struts-tags" %>--%>
<%--<%@taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title><d:title default="装饰器页面..." /></title>--%>
    <%--<d:head />--%>
<%--</head>--%>
<%--<body>--%>
<%--sitemesh的例子<hr/>--%>
<%--<d:body />--%>
<%--<hr/>chen56@msn.com--%>
<%--</body>--%>
<%--</html>--%>
<%@page import="org.myself.mobile.web.utils.Constants" %>
<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="HandheldFriendly" content="true">
    <s:if test="#session.locale=='en_US'">
        <title><%=Constants.hotelNameEn%></title>
    </s:if><s:else>
    <title><%=Constants.hotelName%></title>
</s:else>
    <link href="<s:url value="/css/mobiscroll-2.1-beta.custom.min.css" />"  rel="stylesheet"/>
    <link href="<s:url value="/css/jquery.mobile-min.css" />" type="text/css"  rel="stylesheet"  />
    <link href="<s:url value="/css/style.css" />" type="text/css"  rel="stylesheet"  />
    <script src="<s:url value="/js/jquery.min.js" />"></script>
    <script src="<s:url value="/js/jquery.mobile-1.1.1.min.js" />"></script>
    <%--<script src="<s:url value="/js/base_util.js" />"></script>--%>
    <%--<script src="<s:url value="/js/web_util.js" />"></script>--%>
    <%--<script src="<s:url value="/js/hotel_search.js" />"></script>--%>
    <script src="<s:url value="/js/mobiscroll-2.1-beta.custom.min.js" />"></script>
    <script type="text/javascript">
        function setLanguage(code){
            document.location.href="<s:url action="index" namespace="/"/>?lan="+code+"&change=true";
        }
    </script>
    <d:head/>
</head>
<body>
<div data-role="page" style="background: #f1f1f1;">
    <%--<div data-theme="a" data-role="header" class="color_style" data-position="fixed">--%>
    <%--<h3 class="m0">--%>
    <%--<img src="<s:url value="/images/logo.png" />" width="144" height="44">--%>
    <%--</h3>--%>
    <%--<a data-role="button"  href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">--%>
    <%--<s:text name="lidu.button.back.value"/>--%>
    <%--</a>--%>

    <%--&lt;%&ndash;<s:if test="#session.test!=null">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<a data-role="button"  data-theme="a" href="<s:url action="indexB"  namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<s:text name="lidu.button.home.value"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</s:if><s:else>&ndash;%&gt;--%>
    <%--<a data-role="button"  data-theme="a" href="<s:url action="index"  namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">--%>
    <%--<s:text name="lidu.button.home.value"/>--%>
    <%--</a>--%>
    <%--&lt;%&ndash;</s:else>&ndash;%&gt;--%>
    <%--</div>--%>
    <d:body/>

    <%--<div id="footerbar" data-position="fixed" datta-role="footer">--%>
    <%--<div id="footer-btn">--%>
    <%--<ul>--%>
    <%--<s:if test="#session.test!=null">--%>
    <%--<li style="width:70%;"><a href="javascript:void(0)"  rel="external"><em class="lag-cn"></em></a></li>--%>
    <%--<s:if test="#session.locale=='en_US'">--%>
    <%--<li style="width:30%;"><a style="font-size:17px;" href="" onclick="setLanguage('zh_CN')" rel="external"><em class="lag-cn"></em>简体中文</a></li>--%>
    <%--</s:if><s:else>--%>
    <%--<li style="width:30%;"><a style="font-size:17px;" href="" onclick="setLanguage('en_US')" rel="external"><em class="lag-en"></em>English</a> </li>--%>
    <%--</s:else>--%>
    <%--</s:if>--%>
    <%--<s:else>--%>
    <%--<s:if test="#session.loginName==null">--%>
    <%--<li style="width:35%;">--%>
    <%--<a href="<s:url action="login" namespace="/"/>" rel="external"><em class="foot-login"></em>--%>
    <%--<s:text name="lidu.button.memberlogin.value"/></a>--%>
    <%--</li>--%>
    <%--<li style="width:35%;">--%>
    <%--<a href="<s:url action="register" namespace="/"/>" rel="external"><em class="foot-register"></em>--%>
    <%--<s:text name="lidu.button.memberregister.value"/></a>--%>
    <%--</li>--%>
    <%--</s:if>--%>
    <%--<s:else>--%>
    <%--<li style="width:70%;">--%>
    <%--<a id="center" href="<s:url action="member_center" namespace="/member" />" rel="external">--%>
    <%--<em class="foot-logined"></em><s:property value="#session.loginName"/>--%>
    <%--, <s:text name="lidu.label.welcome"/>--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--</s:else>--%>
    <%--<s:if test="#session.locale=='en_US'">--%>
    <%--<li style="width:30%;"><a style="font-size:17px;" href="" onclick="setLanguage('zh_CN')" rel="external"><em class="lag-cn"></em>简体中文</a></li>--%>
    <%--</s:if><s:else>--%>
    <%--<li style="width:30%;"><a style="font-size:16px;" href="" onclick="setLanguage('en_US')" rel="external"><em class="lag-en"></em>English</a> </li>--%>
    <%--</s:else>--%>
    <%--</s:else>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--</div>--%>
</div>
</body>
</html>
