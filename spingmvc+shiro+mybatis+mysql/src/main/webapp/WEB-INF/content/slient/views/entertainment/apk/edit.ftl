<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <title>编辑应用</title>
    </head>
<body>
    <script type="text/javascript">
        <#--$(function(){-->
            <#--$("#entertainment-apk-edit-upload").uploadify({-->
                <#--'disable':true ,-->
                <#--'fileObjName':'file',-->
                <#--'swf':'${ctx}/uploadify/uploadify.swf',-->
                <#--'fileTypeExts':'*.jpg;*.png;*.gif',-->
                <#--'auto':true,-->
                <#--'multi':false,-->
                <#--'buttonText':'添加封面',-->
                <#--'uploader':'${ctx}/upload.html' ,-->
                <#--'onUploadSuccess':function(file,data,response){-->
                    <#--var result=eval('('+data+')');-->
                    <#--$("#coverUrl").val(result.path);-->
                <#--}-->
            <#--});-->
        <#--});-->
    </script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <form id="entertainment-apk-edit-form" method="post" action="#">
            <div class="easyui-tabs" data-options="border:false">
                <div title="应用信息">
                    <table width="100%">
                        <input name="id" type="hidden" class="easyui-validatebox" data-options="required:true" readonly/>
                        <tr>
                            <td width="100">应用名称</td>
                            <td><input name="name" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">应用别名</td>
                            <td><input name="pack_name" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">应用介绍</td>
                            <td><input name="introduction" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">版本号</td>
                            <td><input name="version_code" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">下载链接</td>
                            <td><input name="icon_url" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">类别</td>
                            <td><input id="entertainment-apk-country" name="type" class="easyui-validatebox"/></td>
                        </tr>
                        <tr>
                            <td width="100">上架时间</td>
                            <td><input id="entertainment-apk-screen" name="created" class="easyui-validatebox"/></td>
                        </tr>
                        <tr>
                            <td>状态</td>
                            <td>
                                <select name="publiced" class="easyui-combobox" data-options="required:true">
                                    <option value=0>锁定</option>
                                    <option value=1>打开</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<div data-options="region:'south',border:false" style="text-align: right;padding: 5px 0 0">
    <a id="entertainment-apk-edit-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
    <a id="entertainment-apk-edit-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
</div>
</body>
</html>