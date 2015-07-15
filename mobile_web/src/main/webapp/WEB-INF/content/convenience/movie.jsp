<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>服务</title>
</head>

<body>
<div data-theme="a" data-role="header" class="color_style" data-position="fixed">
    <h3 class="m0 lh44">
       影片/影讯查询
    </h3>
    <a data-role="button"  href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">返回</a>
    <a data-role="button"  data-theme="a" href="<s:url action="index"  namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">主页</a>
</div>
<ul data-role="listview" class="quizzes ui-listview">

    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="hostMovie" namespace="/convenience/movie" />" rel="external">
                    <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                    <div class="quizData">
                        <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query1</s:if><s:else>热映影片</s:else></h4>
                    </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    </li>
    <%--<li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">--%>
        <%--<div class="ui-btn-inner ui-li ui-li-static ui-body-c">--%>
            <%--<div class="ui-btn-text">--%>
                <%--<a class="ui-link-inherit" href="<s:url action="transfer" namespace="/convenience/movie" />" rel="external">--%>
                    <%--<img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">--%>
                    <%--<div class="quizData">--%>
                        <%--<h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>影片影讯检索 </s:else></h4>--%>
                    <%--</div>--%>
                <%--</a>--%>
            <%--</div>--%>
            <%--<span class="arrow-left"></span>--%>
        <%--</div>--%>
    <%--</li>--%>

    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="cinemaSearch" namespace="/convenience/movie" />" rel="external">
                    <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                    <div class="quizData">
                        <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query3</s:if><s:else>影院影讯检索</s:else></h4>
                    </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    </li>
    <%--<li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">--%>
        <%--<div class="ui-btn-inner ui-li ui-li-static ui-body-c">--%>
            <%--<div class="ui-btn-text">--%>
                <%--<a class="ui-link-inherit" href="<s:url action="station" namespace="/convenience/bus" />" rel="external">--%>
                    <%--<img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">--%>
                    <%--<div class="quizData">--%>
                        <%--<h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query3</s:if><s:else>周边影院影讯检索</s:else></h4>--%>
                    <%--</div>--%>
                <%--</a>--%>
            <%--</div>--%>
            <%--<span class="arrow-left"></span>--%>
        <%--</div>--%>
    <%--</li>--%>
</ul>
</body>
</html>