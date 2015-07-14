<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 15-7-5
  Time: 下午2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <title>注册</title>
    <style type="text/css">
        html,body{padding: 0;margin: 0;font-family: "Microsoft Sans Serif";background: #eef8fe;}
        input{-webkit-tap-highlight-color:rgba(0,0,0,0); -moz-tap-highlight-color:rgba(0,0,0,0);
            -ms-tap-highlight-color:rgba(0,0,0,0);tap-highlight-color:rgba(0,0,0,0);}
        input:hover{border:1px solid #3f89ec; }
        .header{width: 100%;height:60px;background: #3083eb;}
        .nav{width: 900px;margin: 0 auto;text-align: right;height: 60px;line-height: 60px;}
        .nav a{text-decoration: none;color: #fff;margin: 0 5px;}
        .nav span{color: #ddd;}
        .container{width: 100%;margin: 0 auto}
        .register{width: 700px;background: #fff;height:500px;
            border: 1px solid #ddd;
            border-radius: 3px;
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
            margin: 20px auto 0;
            padding-top: 36px;}
          .register h1{color: #2684c2;margin: 0 50px;
              padding: 0 0 36px 182px;font: 26px "Microsoft Yahei","微软雅黑",SimSun,sans-serif;
          background: rgba(0,0,0,0) url('./img/register.gif') no-repeat scroll 140px 5px;border-bottom: 1px solid #ddd;}
          .main{width: 100%;margin: 0 auto;}
          .main p{width: 460px;margin: 20px auto;position: relative}
          .main input{
              width: 320px;height: 36px;border: 1px solid #ccc;;
          }
          .input-tip{display: block;font-size: 13px;color:#959393;width: 240px;margin-left: 64px;}
          .input-label{display: inline-block;width: 50px;text-align: right;margin-right: 10px;}
          #captchas{width: 160px;}
        .captcha-img {width: 100px;height: 36px;position: absolute;right: 130px;top: 0;}
        .captcha-link{position: absolute;right: 80px;top: 6px;font-size: 13px;color:#1b66c7 }
        #btn-submit{background:#3f89ec scroll 0 0;display: block;width: 330px;margin: 0 auto;
            height: 50px;border: 1px solid #3f89ec;color: #fff;font-size: 16px;
       font-weight: bold;cursor: pointer}
        .footer{width: 100%;margin:80px auto 0;text-align: center}
        .copyright{color: #aaa;font-size: 13px;}
    </style>
    <script type="text/javascript">
        function reloadCaptcha() {
            $('.captcha-img').attr('src', 'captcha?_=' + new Date().getTime());
        }
    </script>
</head>
<body>
<div class="header">
    <div class="nav">
        <a href="index.html">首页</a>
        <span>|</span>
        <a href="index.html">登录</a>
        <span>|</span>
        <a href="help">帮助中心</a>
    </div>
   </div>
<div class="container">
    <div class="register">
          <h1>欢迎注册</h1>
        <div class="main">
           <form action="createUser" method="post">
               <p>
                   <label class="input-label" for="user_email">用户名</label>
                   <input tabindex="1" id="user_name" type="text" name="user_name" required="true">
                   <span class="input-tip" data-default-tip="请输入您的用户名">请输入您的用户名</span>
               </p>
            <p>
                <label class="input-label" for="user_email">邮箱</label>
                <input tabindex="1" id="user_email" type="email" name="user_email" required="true">
                <span class="input-tip" data-default-tip="请输入您的电子邮箱号">请输入您的电子邮箱号</span>
            </p>
            <p>
                <label class="input-label" for="user_psd">密码</label>
                <span class="quc-input-bg">
                    <input tabindex="2" id="user_psd" type="password" name="user_psd" required="true">
                </span>
                <span class="input-tip" data-default-tip="请输入你的密码">请输入你的密码</span>
            </p>
            <p>
                <label class="input-label" for="captchas">验证码</label>
                    <input tabindex="3" type="text" id="captchas" name="captchaText" required="true" />
                <span class="input-tip" data-default-tip="请输入验证码">请输入验证码(不区分大小写)</span>
                <img class="captcha-img" src="captcha" alt="captcha">
                <a tabindex="4" class="captcha-link" tabindex="99"  href="javascript:reloadCaptcha();" title="点击可刷新">换一张</a>
            </p>

               <input type="submit" value="注册"  id="btn-submit" tabindex="5">
           </form>
        </div>
    </div>
</div>
<div class="footer">
    <p class="copyright">
        Copyright©2015-2015 Zilaiye All Rights Reserved.
    </p>
</div>
</body>
</html>