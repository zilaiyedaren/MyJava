<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>
        <s:if test="#session.locale=='en_US'">Hotel Image</s:if><s:else>酒店相册</s:else>
    </title>
    <link href="<s:url value="/css/baguettebox.min.css" />" type="text/css" rel="stylesheet"/>
    <link href="<s:url value="/css/zzsc.css" />" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<s:url value="/js/jquery.min.js" />"></script>
    <script type="text/javascript" src="<s:url value="/js/baguettebox.min.js"/>"></script>



</head>
<body>
<div data-theme="a" data-role="header" class="color_style" data-position="fixed">
    <h3>
        <s:if test="#session.locale=='en_US'">Hotel Images</s:if><s:else>酒店相册</s:else>
    </h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">
        <%--&lt;%&ndash;<s:text name="lidu.button.back.value"/>&ndash;%&gt;--%>返回
    </a>
    <a data-role="button" data-theme="a" href="<s:url action="index" namespace="/" />?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">
        <%--&lt;%&ndash;<s:text name="lidu.button.home.value"/>&ndash;%&gt--%>主页
    </a>
</div>

    <div class="baguetteBoxOne gallery">
        <%--点击图片不进行页面跳转--%>
        <a class="img_left" href="<s:url value="/images/0.jpg"/>" target="_blank" data-caption="贡嘎神汤温泉酒店 酒店全景"><img  src="<s:url value="/images/0.jpg" />"></a>
        <a class="img_right" href="<s:url value="/images/1.jpg"/>" target="_blank"  title="贡嘎神汤温泉酒店 2号别墅房"><img  src="<s:url value="/images/1.jpg" />"></a>
        <a class="img_right" href="<s:url value="/images/2.jpg"/>"target="_blank"  title="贡嘎神汤温泉酒店 标准温泉游泳池 "><img  src="<s:url value="/images/2.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/3.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 标准温泉游泳池3"><img  src="<s:url value="/images/3.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/4.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 别墅会议室"><img  src="<s:url value="/images/4.jpg" />"></a>
        <a class="img_right" href="<s:url value="/images/5.jpg"/>"target="_blank"  title="贡嘎神汤温泉酒店 别墅客房"><img  src="<s:url value="/images/5.jpg" />"></a>
        <a class="img_right" href="<s:url value="/images/6.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 别墅游泳池"><img  src="<s:url value="/images/6.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/7.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 贡嘎神汤温泉"><img  src="<s:url value="/images/7.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/8.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 温泉游泳池"><img  src="<s:url value="/images/8.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/9.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 润之泉"><img  src="<s:url value="/images/9.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/10.jpg"/>"target="_blank"  title="贡嘎神汤温泉酒店 水上电影"><img  src="<s:url value="/images/10.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/11.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 演艺厅"><img  src="<s:url value="/images/11.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/12.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 淑女泡池"><img  src="<s:url value="/images/12.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/13.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 雅克西餐厅"><img  src="<s:url value="/images/13.jpg" />"></a>
        <a class="img_left" href="<s:url value="/images/14.jpg"/>" target="_blank" title="贡嘎神汤温泉酒店 酒店大厅"><img  src="<s:url value="/images/14.jpg" />"></a>
    </div>
    <script>
            baguetteBox.run('.baguetteBoxOne', {
                animation: 'fadeIn'
//                animation: 'slide'
            });
    </script>
    <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    </div>
    <%--<div class="banner" style="margin: 15px auto;">--%>
        <%--<img id="img" src="<s:url value="/images/dinner01.jpg" />"><br/>--%>
    <%--</div>--%>
    <%--<br/>--%>
    <%--<a id="searh_hotel_btn2" data-role="button" data-direction="reverse" data-transition="slidefade"--%>
       <%--href="javascript:nextImg();" rel="external"  data-iconpos="left" style="margin: 0.3em 0;">--%>
        <%--<s:if test="#session.locale=='en_US'">Next</s:if><s:else>下一张</s:else>--%>
    <%--</a>--%>
    <%--<a id="searh_hotel_btn3" data-role="button" data-direction="reverse" data-transition="slidefade"--%>
       <%--href="javascript:lastImg();" rel="external"  data-iconpos="left" style="margin: 0.3em 0;">--%>
        <%--<s:if test="#session.locale=='en_US'">Last</s:if><s:else>上一张</s:else>--%>
    <%--</a>--%>
    <%--<div style="height: 100px">&nbsp;</div>--%>
</body>

</html>