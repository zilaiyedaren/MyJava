<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>管理后台</title>

    <!-- easyUI -->
    <link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">

    <script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/easyui/jquery.easyui.validatebox.rules.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-serializeObject.js"></script>
    <!-- accounting -->
    <script type="text/javascript" src="${ctx}/js/accounting.min.js"></script>

    <!--upload-->

    <script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css">


    <script type="text/javascript">
        $(function() {
            $.fn.calendar.defaults.firstDay = 1; // set the day Monday as the first day in a datebox
            $.fn.datagrid.defaults.loadFilter = function(data) {
                if (typeof(data.total) == 'undefined') {
                    $.messager.alert('错误提示',data.message,'error');
                    return {total:0,rows:[]};
                }
                return data;
            };
            $.fn.datagrid.defaults.onLoadError = function() {
                $.messager.alert('错误提示','请确认您访问的地址是否正确，并且是否拥有访问的权限!','error');
            };

            var hasLoggedIn = ${login}
            if (hasLoggedIn==false) {
                $('#login-dialog').dialog('open');
            }
            else{
                $('#left').panel('refresh','${ctx}/slient/views/left');
            }
            //点击刷新验证码
            $('#captcha-image').click(function() {
                reloadCaptcha();
                $('#captcha').select();
            });

        });
        function getRandomTime(){
            return new Date().getTime();
        }
        function reloadCaptcha() {
            $('#captcha-image').attr('src', '${ctx}/slient/views/captcha?_=' + new Date().getTime());
        }
        function open1(title,url){
//            var jq = top.jQuery;
            if ($('#tt').tabs('exists',title)){
                $('#tt').tabs('select', title);
            } else {
                $('#tt').tabs('add',{
                    title:title,
                    href:url,
                    closable:true
                });
            }
        }
        function submitLoginForm() {
            $('#ff').form('submit',{
                success:function(data) {
                    var result = eval('(' + data + ')');
                    if (result.success) {
                        $('#login-dialog').dialog('close');
                        $('#left').panel('open')
                        $('#left').panel('refresh','${ctx}/slient/views/left');
                    } else {
                        $.messager.alert('登录失败',result.message ,'error',function(){
                            reloadCaptcha();
                        });
                    }
                }
            });
        }

        function clearLoginForm() {
            $('#ff').form('clear');
        }
        function logout(reload) {
            $.get('${ctx}/slient/views/logout',function() {
                if (reload) {
                    document.location.reload();
                } else {
                    $('#left').panel('close');
                    $('#login-dialog').dialog('open');
                    reloadCaptcha();
                }
            });
        }
    </script>

    <!-- The template to display files available for upload -->
</head>
<body class="easyui-layout" style="text-align: left">
<div region="north" border="false" style="text-align: center">
    <div id="header-inner">
        <table cellpadding="0" cellspacing="0" style="width: 100%;">
            <tr>
                <td rowspan="2" style="width: 20px;"></td>
                <td style="height: 52px;">
                    <div style="font-size: 22px; font-weight: bold;">
                        管理后台
                    </div>
                </td>
                <td style="padding-right: 5px; text-align: right; vertical-align: bottom;">
                    <div id="topmenu">
                        <a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-edit'">系统登录</a>
                        <div id="mm1" style="width:150px;">
                            <div>个人信息</div>
                            <div class="menu-sep"></div>
                            <div onclick="logout()">重新登录</div>
                            <div onclick="logout(true)">退出系统</div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<div region="west" border="false" split="true" title="功能菜单" style="width: 250px; padding: 5px;">
    <div id="left" class="easyui-panel"  fit="true" border="false"></div>
</div>
<div region="center" border="false">
    <div id="tt" class="easyui-tabs" fit="true" border="false">
    </div>
</div>
<div id="login-dialog" class="easyui-dialog" title="系统登录" style="width:380px;height:200px;" data-options="closable:false,modal:true,closed:true">
    <div style="padding:10px">

        <form id="ff" method="post" action="${ctx}/slient/views/login" style="padding:0;margin:0">
            <input type="hidden" name="ajax">
            <table align="center">
                <tr>
                    <td width="80px">用户名</td>
                    <td><input class="easyui-validatebox" type="text" name="loginName" data-options="required:true" style="width:160px"/></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input class="easyui-validatebox" type="password" name="password" data-options="required:true" style="width:160px"/></td>
                </tr>
                <tr>
                    <td>验证码</td>
                    <td>
                        <span class="easyui-tooltip" title="点击可刷新"><img id="captcha-image" src="${ctx}/slient/views/captcha" style="float:left;width:80px;height:24px;" /></span>
                        <input id="captcha" class="easyui-validatebox" type="text" name="captcha" data-options="required:true" style="width:80px"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitLoginForm()">登录</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearLoginForm()">重填</a>
    </div>
</div>
</body>
</html>
