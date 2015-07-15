<%@ page import="org.myself.mobile.web.utils.Constants" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>微信支付测试</title>
    <script type="text/javascript">
    // 当微信内置浏览器 完成内部初始化后会触发WeixinJSBridgeReady事件。
    document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    //公众号支付
    $('#getBrandWCPayRequest').click(function(e){
    WeixinJSBridge.invoke('getBrandWCPayRequest',{
    "appId" : "${weChatPay.appId}", //公众号名称，由商户传入
    "timeStamp" : "${weChatPay.timeStamp}", //时间戳
    "nonceStr" : "${weChatPay.nonceStr}", //随机串
    "package" : "${weChatPay.packageStr}", ////扩展包
    "signType" : "${weChatPay.signType}", //微信签名方式:1.sha1
    "paySign" : "${weChatPay.paySign}" ////微信签名
    },function(res){
    // if(res.err_msg == get_brand_wcpay_request:ok ) {
    alert( res.err_msg ); // alert("OK");
    // }
    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
    //因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前 展示相应的界面。
    });
    });
    WeixinJSBridge.log('yo~ ready.');
    }, false);
    </script>
    </head>
    
    <body>
    <div class="banner wper80">
        <img alt="" src="<s:url value="/images/baidafeili.jpg"/>" > <br>
        <div class="namestlye">
            价值百万 百达翡丽手表 微信支付只需：0.01￥
        </div>
        <h2 class="sub_btn order" >
            <div class="w100 fr mr5 btn_book">
                <a href="" class="w80 h15" style="padding-top: 15px" id="getBrandWCPayRequest">支付</a>
            </div>
        </h2>
    </div>

    </body>
</html>