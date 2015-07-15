<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员登录</title>
    <%--<link href="<s:url value="/js/validform/validform.css" />" type="text/css" rel="stylesheet"/>--%>
    <%--<script type="text/javascript" src="<s:url value="/js/validform/Validform_v5.3.2_zh.js" />"></script>--%>
    <script type="text/javascript">
//        $(function(){
//            $(".j_validate_form").Validform({
//                tiptype:2,
//                beforeCheck:function(curform){
//                    $("#iMessage").hide();
//                },
//                beforeSubmit:function(curform){
//                }});
//        });
    </script>
</head>
    <body>
    <div data-theme="a" data-role="header" class="color_style" data-position="fixed">
        <h3 class="m0 lh44">
            <%--<s:text name="lidu.button.memberlogin.value"/>--%>
            登录
        </h3>
        <a data-role="button"  href="javascript:history.go(-1);" data-icon="arrow-l" rel="external" data-iconpos="left" class="ui-btn-left">返回</a>
        <a data-role="button"  data-theme="a" href="<s:url action="index"  namespace="/"/>?change=true" rel="external" data-icon="home" data-iconpos="left" class="ui-btn-right">主页</a>
    </div>

    <s:if test="tipMessage!=null">
        <div class="message" id="iMessage">
            <span class="error"><s:property value="tipMessage"/></span>
        </div>
    </s:if>
	<div data-role="content" data-theme="c" style="padding: 5px; background: #f1f1f1;">
    <s:form id="login" cssClass="j_validate_form"  method="post" action="loginSubmit">
        <%--post为异步通讯不会刷新页面--%>
		<ul data-role="listview" data-divider-theme="c" data-inset="true" style="margin: 0.5em 0;  background: #f1f1f1;">
			<li data-theme="c"  style="padding: 0.1em">
            	<div data-role="fieldcontain"  class="h30 minw150 wrap">
                	<div class="main">
                   		<div class="main-inner">
                            <div  class="fixed">
                                <label for="user_name" class="labletitle">用户名:</label>
                            </div>
                            <input type="text" datatype="*1-50"  placeholder="输入用户名" required="true" id="user_name" name="user_name" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
                   		</div>
                    </div>
                    <%--<div  class="fixed">--%>
						<%--<label for="user_name" class="labletitle">用户名:</label>--%>
                    <%--</div>--%>
				</div>
           </li>
			
			<li data-theme="c"  style="padding: 0.1em">
            	<div data-role="fieldcontain"  class="h30 minw150 wrap">
                	<div class="main">
                   		<div class="main-inner">
                            <div  class="fixed">
                                <label for="user_pass" class="labletitle">密码:</label>
                            </div>
                            <input type="password" datatype="*1-20"  placeholder="请输入密码" required="true" id="user_pass" name="user_pass" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
                   		</div>
                    </div>
                    <%--<div  class="fixed">--%>
						<%--<label for="user_pass" class="labletitle">密码:</label>--%>
                    <%--</div>--%>
				</div>
           </li>
            <%--<li data-theme="c"  style="padding: 0.1em">--%>
                <%--<div data-role="fieldcontain"  class="h30 minw150 wrap">--%>
                    <%--<div class="main">--%>
                        <%--<div class="main-inner">--%>
                            <%--<a href="<s:url action="forgetpassword"/>"><span style="color: #333"> <s:text name="lidu.button.forgetPass.value"/></span></a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div  class="fixed">--%>
                        <%--<label for="user_pass" class="labletitle">&nbsp;</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</li>--%>
        </ul>
        <input type="submit" value="会员登录" />
               <%--<a id="login" href="<s:url action="loginSubmit"/>" data-role="button" data-direction="reverse" data-transition="slidefade"  rel="external" data-icon="check" data-iconpos="left"  style="margin: 0.2em 0;">--%>
                   <%--会员登陆--%>
               <%--</a>--%>

               <%--<a id="register" href="<s:url action="register"/>" data-role="button" data-direction="reverse" data-transition="slidefade" rel="external"  style="margin: 0.2em 0;">--%>
                   <%--<s:text name="lidu.button.memberregister.value"/>--%>
               <%--</a>--%>
            <%--<a id="forgetPass" target="_blank" href="http://211.144.87.45/dhsite/entry/find-pwd?hc=03227&lr=en&de=false" data-role="button" data-direction="reverse" data-transition="slidefade" rel="external"  style="margin: 0.2em 0;">--%>
                <%--<s:text name="lidu.button.forgetPass.value"/>--%>
            <%--</a>--%>

        <%--<a id="password" href="<s:url action="forgetpassword"/>" data-role="button" data-direction="reverse" data-transition="slidefade" rel="external"  style="margin: 0.2em 0;">--%>
        <%--<s:text name="lidu.button.forgetPass.value"/>--%>
        <%--</a>--%>

        <%--<a id="register" href="<s:url action="register"/>" data-role="button" data-direction="reverse" data-transition="slidefade" rel="external"  style="margin: 0.2em 0;">--%>
            <%--<s:text name="lidu.button.memberregister.value"/>--%>
        <%--</a>--%>

        <%--<div class="cut-line w"></div>--%>

         <%--<a href="<s:url action="register"/>" style="text-decoration: none;">--%>
             <%--<div class="zchy">--%>
                 <%--<span><s:text name="lidu.button.memberregister.value"/></span>--%>
                 <%--<span class="arrow-left top5"></span>--%>
            <%--</div>--%>
         <%--</a>--%>
        <div class="cut-line w"></div>
    </s:form>
   </div>
    </body>
</html>