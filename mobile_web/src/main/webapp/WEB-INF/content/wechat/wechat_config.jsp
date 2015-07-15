<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-11-30
  Time: 下午5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>提交表单</title>
    <script type="text/javascript" src="<s:url value="/js/jquery.min.js"/>"></script>
</head>
<body>
<div data-theme="b" data-role="header" data-position="fixed">
    <h3>微信更新</h3>
    <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left">返回</a>
    <a data-role="button" href="<s:url action="index" namespace="/" />" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
</div>
       <div data-role="content" data-thene="b" style="padding:5px;">
            <s:form id="info" enctype="multipart/form-data" method="post">
                 <ul style="border:1px solid #ccc;border-radius: 8px;">
                     <h3>酒店信息</h3>
                     <input type="checkbox" value="酒店介绍" name="hotelInfo_1" id="hotelInfo_1" checked/>酒店介绍
                     <input type="checkbox" value="房间介绍" name="hotelInfo_2" id="hotelInfo_2" checked/>房间介绍
                     <input type="checkbox" value="酒店设施" name="hotelInfo_3" id="hotelInfo_3" checked/>酒店设施
                     <input type="checkbox" value="优惠促销" name="hotelInfo_4" id="hotelInfo_4" checked/>优惠促销
                     <input type="checkbox" value="酒店新闻" name="hotelInfo_5" id="hotelInfo_5" checked/>酒店新闻
                 </ul>
                <ul style="border:1px solid #ccc;border-radius: 8px;">
                    <h3>预定</h3>

                    <li style="width: 10%;display: inline">
                        <input type="checkbox" value="房间预定" name="hotelBook_1" id="hotelBook_1" checked/>
                        <label>房间预定</label>

                    <input type="checkbox" value="订单查询" name="hotelBook_2" id="hotelBook_2" checked/>订单查询
                    <input type="checkbox" value="会员登录" name="hotelBook_3" id="hotelBook_3" checked/>会员登录
                    <input type="checkbox" value="会员注册" name="hotelBook_4" id="hotelBook_4" checked/>会员注册
                    <input type="checkbox" value="忘记密码" name="hotelBook_5" id="hotelBook_5" checked/>忘记密码
                </ul>
                <ul style="border:1px solid #ccc;border-radius: 8px;">
                    <h3>更多</h3>
                    <input type="checkbox" value="今日天气" name="hotelMore_1" id="hotelMore_1" checked/>今日天气
                    <input type="checkbox" value="联系我们" name="hotelMore_2" id="hotelMore_2" checked/>联系我们
                    <input type="checkbox" value="酒店位置" name="hotelMore_3" id="hotelMore_3" checked/>酒店位置
                    <input type="checkbox" value="酒店相册" name="hotelMore_4" id="hotelMore_4"/>酒店相册
                    <input type="checkbox" value="周边景点" name="hotelMore_5" id="hotelMore_5"/>周边景点
                </ul>
                <input type="submit" value="提交" onclick="submitForm()"/>
            </s:form>
       </div>
</body>
<script type="text/javascript">
    function submitForm(){
        $.ajax({
            url:"http://10.200.105.186:8022/mobile_web/wechatConfig.do",
            cache: true,
            type: "POST",
            data:$('#info').serialize(),// formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                if(data.msg==='success'){
                    alert("提交成功");
                }else{
                    alert("提交失败");
                }
            }
        });
    }
</script>
</html>