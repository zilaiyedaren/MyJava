<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-6
  Time: 下午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div data-theme="a" data-role="header" data-position="fixed">
    <h4>资讯</h4>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" class="ui-btn-left" rel="external">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/"/>"  data-icon="home" data-iconpos="right" class="ui-btn-right" rel="external">主页</a>
</div>
<ul data-role="listview" class="quizzes ui-listview">

    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="caipiao" namespace="/info"/>" rel="external">
                <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                <div class="quizData">
                    <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query1</s:if><s:else>彩票查询</s:else></h4>
                </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    </li>
    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="newstype" namespace="/info"/>" rel="external">
                    <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                    <div class="quizData">
                        <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>新闻</s:else></h4>
                    </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    </li>
    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="stock" namespace="/info"/>" rel="external">
                    <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                    <div class="quizData">
                        <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query3</s:if><s:else>股票查询</s:else></h4>
                    </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="express" namespace="/info"/>" rel="external">
                    <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                    <div class="quizData">
                        <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query4</s:if><s:else>快递查询</s:else></h4>
                    </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    </li>
    <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
        <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
            <div class="ui-btn-text">
                <a class="ui-link-inherit" href="<s:url action="translate?method=" namespace="/info"/>" rel="external">
                    <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                    <div class="quizData">
                        <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">Translate</s:if><s:else>在线翻译</s:else></h4>
                    </div>
                </a>
            </div>
            <span class="arrow-left"></span>
        </div>
    </li>
    </ul>
</body>
</html>