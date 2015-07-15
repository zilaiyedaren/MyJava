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
            今日天气
        </h3>
        <a data-role="button"  href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">返回</a>
        <a data-role="button"  data-theme="a" href="<s:url action="index"  namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">主页</a>
    </div>

            <div role="main" class="ui-content" id="mapDiv" data-role="content" style="width:95%"></div>
            <div data-role="content" data-theme="c" style="padding-left: 5px;padding-right: 5px; background: #f1f1f1;">
            	<form action="room_list.html" method="get" id="hotel_search_form">
            		<ul data-role="listview" data-divider-theme="c" data-inset="true">
                            <li data-theme="c"  style="padding: 0.1em">
                                <%--<div  class="contact minw320">电话: <span class="telnumber"><a href="tel:<s:property value="bookingPhone"/>" id="contact_us_tel"><s:property value="bookingPhone"/></a></span></div>--%>
                                    <div  class="contact minw300">城市: <s:property value="info.city"/></div>
                                    <%--<div  class="contact minw320">日期: <s:property value="info.date_y"/> <s:property value="info.week"/></div>--%>
                                    <div  class="contact minw320">日期: <s:property value="info.date_y"/></div>
                                    <div  class="contact minw320">温度: <s:property value="info.temp1"/></div>
                                    <div  class="contact minw320">天气: <img src="<s:property value="info.picUrl"/>"/> <s:property value="info.weather1"/></div>
                                    <div  class="contact minw320">风力: <s:property value="info.wind1"/></div>
                                    <div  class="contact minw320">注意: <s:property value="info.index_d"/></div>
                            </li>
            		</ul>
            	</form>
            </div>

    </body>
</html>