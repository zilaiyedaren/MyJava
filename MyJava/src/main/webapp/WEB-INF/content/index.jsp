<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="./css/banner_swiper.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/swiper/swiper-2.1.min.js"></script>
      <script type="text/javascript" src="./js/swiper/slide.js"></script>
      <style type="text/css">
          html,body{padding: 0;margin: 0;font-family: "Microsoft Sans Serif";background: #fff;}
          input{-webkit-tap-highlight-color:rgba(0,0,0,0); -moz-tap-highlight-color:rgba(0,0,0,0);
              -ms-tap-highlight-color:rgba(0,0,0,0);tap-highlight-color:rgba(0,0,0,0);}
          .header{width: 100%;height:440px;background: #3083eb;}
          .nav{width: 900px;margin: 0 auto;text-align: right;height: 80px;line-height: 70px;}
          .nav a{text-decoration: none;color: #fff;margin: 0 5px;}
          .nav span{color: #ddd;}
           .container{width: 900px;margin: 0 auto;position: relative;height: 300px;}
          .login-box{width: 260px;height:300px;background: #fff none repeat scroll 0 0;
             position: absolute;right: 0;top:0;
             box-shadow: 0 0 2px rgba(0, 0, 0, 0.13), 0 1px 5px rgba(0, 0, 0, 0.36),
             0 0 0 1px rgba(255, 255, 255, 0.69) inset;
              margin: 0 auto;
          }
          .login-box h3{font-size: 16px;color: #0068b7;width: 200px;margin:20px auto 10px;}
           #input-name,#input-password{width: 200px;margin: 0 auto 10px;;display: block;height: 32px;border: 1px solid #c7c7c7;}
           #submit-btn{width: 200px;margin: 0 auto;display: block;height: 40px;background: forestgreen;border: 1px solid forestgreen;color: #fff;font-size: 16px;}
           #_spring_security_remember_me{position: relative;top:2px;}
           .login-box label{font-size: 13px;width: 200px;margin: 0 auto 18px;;display: block}
           .change{text-decoration: none;font-size: 12px;
               background-color: #f2f7fa;
               border-top: 1px solid #e6ecf0;
               bottom: 0;
               height: 40px;
               left: 0;
               line-height: 40px;
               position: absolute;
               width: 100%;text-align: center;
           }
           .change a:hover{text-decoration:underline;}
           .change a{text-decoration: none;font-size: 12px;}
            .change a:nth-child(1){color: #999}
            .change a:nth-child(3){color: #0082cb}
           .banner-box{width: 620px;height: 300px; }
         .footer{width: 900px;margin: 0 auto;}
         .footer a{text-decoration: none;}
          .btn-box a{text-decoration: none;color: #fff;background: #6081eb;display: inline-block;
          width: 220px;height:60px;line-height: 60px;text-align: center}
          .btn-box a:nth-child(2){margin-left: 14px;}
          .wrap{position: relative;margin: 30px auto;}
          .wechat{position: absolute;right: 0;top:0;border-left:1px solid #ddd; height: 100px;width: 260px;}
          .wechat img{width: 100px;height: 100px;position: absolute;left: 30px;}
          .wechat span{display: block;position: absolute;left: 140px;width:90px;line-height: 30px;}
          .footer-bottom{
              margin-top: 80px;
              position: relative;
          }

          .links a{font-size: 12px;border-left: 1px solid #d5d5d5;
              color: #8d8d8d;
              margin: 0 0 0 -10px;
              padding: 0 10px;}
          .copyright{ color: #cdcdcd;
              font-family: arial;font-size: 12px;}
          .sys-notice{color: #f60;font-size: 12px;margin-top: 20px;display: inline-block}
          .other-box a{color: #0068b7;display: inline-block;font-size: 16px;margin-top: 20px;padding:2px 8px;border-left: 1px solid #b2b2b2;}
          .follow-us{position: absolute;right: 0;top:0;}
      </style>
  </head>
  
  <body>
  <div class="header">
      <div class="nav">
          <a href="index.html">首页</a>
          <span>|</span>
          <a href="register.html">注册</a>
          <span>|</span>
          <a href="help">帮助中心</a>
      </div>
      <div class="container">
          <div class="banner-box">
              <div id="wrap">
                  <div class="swiper-container">
                      <div class="swiper-wrapper" id="swiper-wrapper">
                              <div class="swiper-slide">
                                  <%--<a href="#">--%>
                                      <%--<span>1</span>--%>
                                      <img src="./img/new.png" alt="img1" width="100%" >
                                  <%--</a>--%>
                              </div>
                          <div class="swiper-slide">
                              <%--<a href="#">--%>
                                  <%--<span>2</span>--%>
                                  <img src="./img/new2.png" alt="img2" width="100%" >
                              <%--</a>--%>
                          </div>
                      </div>
                      <div class="pagination"></div>
                  </div>
              </div>

          </div>
          <div class="login-box">
               <h3>用户登陆</h3>
              <%--${SPRING_SECURITY_LAST_EXCEPTION.message}--%>
              <form action="j_spring_security_check" method="post">
              <input type="text" name="j_username" id="input-name" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" placeholder="手机号/用户名/邮箱"/>
              <input type="password" name="j_password"  id="input-password" placeholder="请输入您的密码" maxlength="20" />
                  <label>
                      <input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" />下次自动登录
                  </label>

              <input type="submit" id="submit-btn" value="登录"/>
              </form>
              <div class="change">
                 <a href="register.html">注册新账号</a>
                  <span>|</span>
                  <a href="findpwd">忘记密码？</a>
               </div>
          </div>
       </div>
  </div>
  <div class="footer">
        <div class="wrap">
              <div class="download-list">
                  <div class="btn-box">
                      <a href="#">PC客户端</a>
                      <a href="#">手机移动端</a>
                  </div>
                  <div class="other-box">
                     <a href="#">手机正版下载</a>
                      <a href="#">Mac版下载</a>
                  </div>
                  <a href="#" class="sys-notice">严厉打击色情共享及交易账户的公告</a>
              </div>
            <div class="wechat">
                <img src="./img/new.jpg"/>
                <span>手机扫描直接关注官方微信</span>
            </div>
        </div>
      <div class="footer-bottom">
           <p class="links">
               <a target="_blank" href="http://www.360.cn/xukexieyi.html#yunpan">使用协议</a>
               <a target="_blank" href="http://www.360.cn/privacy/v2/360yunpan.html">隐私保护</a>
               <a href="/index/releasenote">更新日志</a>
               <a href="/index/help">帮助中心</a>
               <a target="_blank" href="http://bbs.360safe.com/forum.php?mod=forumdisplay&fid=126">问题反馈</a>
               <a style="color:red" href="mailto:yunduanyueying@163.com">
                   联系我：
                   <strong>yunduanyueying@163.com</strong>
               </a>
           </p>
          <p class="copyright">
              Copyright©2015-2015 Zilaiye All Rights Reserved.
          </p>
          <div class="follow-us">
              <a class="item weixin" href="###">
                  <img class="qrcode" alt="" src="">
              </a>
              <span class="divider"></span>
              <a class="item weibo" target="_blank" href=""></a>
          </div>
      </div>
      </div>
  </div>
    <%--用户登陆 <br>--%>
     <%--${SPRING_SECURITY_LAST_EXCEPTION.message}--%>
      <%--<form action="j_spring_security_check" method="post">--%>
    	<%--USERNAME:<input type="text" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" /><br/>--%>
    	<%--PASSWORD:<input type="password" name="j_password" value="" /><br/>--%>
    	<%--<input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" />两周之内不必登陆(这个功能没有做的)<br/>--%>
		<%--<input type="submit">    	--%>
    <%--</form>--%>
  </body>
</html>
