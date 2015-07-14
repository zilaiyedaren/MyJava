<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="CMS,后台管理">
    <title>欢迎页</title>
    <style type="text/css">
         html,body{padding: 0;margin: 0;font-family: '微软雅黑'}
        .header{background:#171f2a url('./images/internet-plus.png') no-repeat center left;
            background-size:90px 30px;width: 100%;height:34px;line-height: 34px;color: #fff; }
        .header a{text-decoration: none;color: #fff;font-size: 14px;}
        .header a:hover{color: #4d91ce;}
        .login-area{position: absolute;right: 20px;}
        .footer{width: 100%;height: 140px;background:#37414b;}
        .footer p{text-align: center;color: #5a5e67;font-size: 13px;}
        .content{
            background: url('./images/bg_login.jpg') no-repeat;
            width: 100%;height:700px;background-attachment: fixed;
        }
        .login-wrapper{width: 900px;height:600px;margin: 0 auto;position: relative;top:40px;background: url('./images/login_text.png') no-repeat top left;}
        .login-box{background: #fff;width: 400px;height:300px;position: absolute;right: 0;border-radius: 5px;
         box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);}
        .login-box h3{margin-left: 30px;}
             /*clear shadow when click*/
         a,button,input,i{-webkit-tap-highlight-color:rgba(0,0,0,0); -moz-tap-highlight-color:rgba(0,0,0,0);
             -ms-tap-highlight-color:rgba(0,0,0,0);tap-highlight-color:rgba(0,0,0,0);}
        .inputstyle{width: 260px;height:34px;margin: 15px auto;border-radius: 4px;border: 1px solid #ccc;background: #fff;display: block; }
        .btn{width: 120px;height: 30px;margin-left: 70px;background: #008fe8;border: 1px solid #008fe8;color: #fff;}
        .login-bottom{position: absolute;bottom: 10px;right: 10px;}
        .login-bottom a{text-decoration: none;font-size: 12px;color: #555;}
        .login-bottom span{color: #ccc;}
        .login-bottom a:hover{text-decoration: underline;}
    </style>
</head>
<body>
<div class="header">
    <div class="login-area">
        <a href="#">登录</a>
        <span>|</span>
        <a href="#">问题咨询</a>
    </div>
</div>
<div class="content">
     <div class="login-wrapper">
           <div class="login-box">
                <h3>账号密码登录</h3>
               <form >
                   <input type="text" class="inputstyle" id="username" tabindex="1" name="username" placeholder="用户名"/>
                   <input type="password" class="inputstyle" id="password" tabindex="2" name="password" placeholder="密码"/>
                   <input type="submit" tabindex="3" class="btn" id="login-btn" value="登录"/>
               </form>
              <div class="login-bottom">
                    <a href="#">忘记密码？</a>
                  <span>|</span>
                    <a href="#">注册新账号</a>
                  <span>|</span>
                    <a href="#">意见反馈</a>
              </div>
           </div>
     </div>
</div>
<div class="footer">
     <p>
         <span>Copyright ©2013-2015</span>
         <span> org.kent. All Rights Reserved. 云端月影 版权所有</span>
     </p>
</div>
</body>
</html>