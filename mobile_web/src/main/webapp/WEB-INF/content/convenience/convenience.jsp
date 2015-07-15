<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>今日天气</title>
</head>
    
    <body>
    <div data-theme="a" data-role="header" class="color_style" data-position="fixed">
        <h3 class="m0 lh44">
            服务
        </h3>
        <a data-role="button"  href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">返回</a>
        <a data-role="button"  data-theme="a" href="<s:url action="index"  namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">主页</a>
    </div>
    <ul data-role="listview" class="quizzes ui-listview">

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="weather" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query1</s:if><s:else>天气预报</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="illegal" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>车辆违章查询 </s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="idcard" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query3</s:if><s:else>身份证归属地查询 </s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>

        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="mobiletel" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>手机号码归属地查询 </s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="bus" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>公交查询 </s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="movie" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>电影资讯</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="isbn" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>ISBN信息查询</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="plane" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query2</s:if><s:else>国内飞机航班时刻表</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
        <li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-li-has-thumb ui-li-static ui-body-c ui-btn-up-c" data-theme="c">
            <div class="ui-btn-inner ui-li ui-li-static ui-body-c">
                <div class="ui-btn-text">
                    <a class="ui-link-inherit" href="<s:url action="train" namespace="/convenience" />" rel="external">
                        <img class="ui-li-thumb" src="./images/0.jpg" alt="" width="53" height="53">
                        <div class="quizData">
                            <h4 class="ui-li-heading"><s:if test="#session.locale=='en_US'">query3</s:if><s:else>火车时刻表</s:else></h4>
                        </div>
                    </a>
                </div>
                <span class="arrow-left"></span>
            </div>
        </li>
    </ul>
   </body>
</html>