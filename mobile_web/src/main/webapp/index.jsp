<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-5
  Time: 下午12:33
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    //    var isMobile = navigator.appVersion.indexOf("Mobile");
    var language = navigator.language;
    if(!language){
        language = navigator.browserLanguage;
    }
    language = language.toLowerCase();
    var isCN = language.indexOf("zh");
    var isEN = language.indexOf("en");
//    var a = parseInt(Math.random()*100);
//    var b = a % 2

//    var os = getParameterValue("os")
    var os=null;
    if(isCN > -1){
        window.location.href = "<s:url action="index" namespace="/"/>?lan=zh_CN&os="+os;
    } else {
        window.location.href = "<s:url action="index" namespace="/"/>?lan=en_US&os="+os;
    }
    <%--if(isCN > -1){--%>
        <%--window.location.href = "<s:url action="login"  namespace=""/>?lan=zh_CN&os="+os;--%>
    <%--} else {--%>
        <%--window.location.href = "<s:url action="login" namespace=""/>?lan=en_US&os="+os;--%>
    <%--}--%>
</script>