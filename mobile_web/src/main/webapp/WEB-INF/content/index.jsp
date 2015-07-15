<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-5
  Time: 下午1:20
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.myself.mobile.web.utils.Constants" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" pageEncoding="utf-8" %>
<%@ page import="org.myself.mobile.web.config.listener.OnlineCounter" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="true" name="HandheldFriendly">

    <s:if test="#session.locale=='en_US'">
        <title><%=Constants.hotelNameEn%></title>
    </s:if><s:else>
    <title><%=Constants.hotelName%></title>
</s:else>

    <link rel="apple-touch-icon" href="images/logo.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="images/icon.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="images/icon.png"/>
    <%--<link rel="apple-touch-startup-image" href="<s:url value="/images/overview.jpg" />"/>--%>
    <link rel="css/style.css" type="text/css" rel="stylesheet" />

    <link href="<s:url value="/css/style.css" />" type="text/css"  rel="stylesheet"  />
    <link href="<s:url value="/css/jquery.mobile-min.css" />" type="text/css"  rel="stylesheet"  />
    <%--<link href="<s:url value="/css/mobiscroll-2.1-beta.custom.min.css" />" type="text/css"  rel="stylesheet"  />--%>
    <script type="text/javascript" src="<s:url value="/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/js/jquery.mobile-1.1.1.min.js"/>"></script>
    <script type="text/javascript">
        function setLanguage(code){
            document.location.href="<s:url action="index" namespace="/"/>?lan="+code+"&change=true";
        }
    </script>
</head>
<body class="index">
<div class="header">
    <h1>
        <img src="<s:url value="/images/logo.png"/>">
    </h1>
</div>
<div class="lan_change">
    <s:if test="#session.locale=='en_US'">
        <a href="javascript:setLanguage('zh_CN');">中文</a>
    </s:if><s:else>
    <a href="javascript:setLanguage('en_US');">English</a>
</s:else>
</div>
<div class="slider_img">
    <img class="" src="<s:url value="/images/bg_body01.jpg" />">
</div>
<div class="tukuicon">
    <a href="<s:url action="img_scan" namespace="images"/>" rel="external">
        <img src="<s:url value="/images/tukuicon.png" />">
    </a>
</div>


<%--<div class="ui-content" id="content" data-role="content">--%>

    <ul data-role="listview" class="quizzes ui-listview">

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="about_us" namespace="about"/>" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">ABOUT US</s:if><s:else>关于我们</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="info" namespace="info"/>" rel="external">
                        <img class="ui-li-thumb" src="./images/1.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">Message Infonation</s:if><s:else>资讯信息</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="convenience" namespace="convenience"/>" rel="external">
                        <img class="ui-li-thumb" src="./images/3.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">SERVICE</s:if><s:else>服务</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="contact" namespace="contact"/>" rel="external">
                        <img class="ui-li-thumb" src="./images/2.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">CONTACT US</s:if><s:else>联系我们</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="index" namespace="demo"/>" rel="external">
                        <img class="ui-li-thumb" src="./images/2.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">Config Demo</s:if><s:else>模板配置</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
    </ul>
<%--</div>--%>
目前在线人数：<%=OnlineCounter.getOnline()%>
<%--退出会话(可以给用户提供一个注销按钮)：--%>
<%--<form action="exit.jsp" method=post>--%>
    <%--<input type=submit value="exit">--%>
<%--</form>--%>
<%--exit.jsp: <%session.invalidate() ;%>--%>
</body>
</html>