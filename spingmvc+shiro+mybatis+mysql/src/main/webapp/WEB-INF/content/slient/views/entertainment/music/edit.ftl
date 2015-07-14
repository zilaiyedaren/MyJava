<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <title>编辑歌曲</title>
    </head>
<body>
    <script type="text/javascript">
        <#--$(function(){-->
            <#--$("#entertainment-music-edit-upload").uploadify({-->
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
        <form id="entertainment-music-edit-form" method="post" action="#">
            <div class="easyui-tabs" data-options="border:false">
                <div title="歌曲信息" style="padding:10px;">
                    <table width="100%">
                        <input name="id" type="hidden" class="easyui-validatebox" data-options="required:true" readonly/>
                        <tr>
                            <td width="100">歌曲名称</td>
                            <td><input name="name" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">歌曲下载链接</td>
                            <td><input name="music_url" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">发行时间</td>
                            <td><input name="created" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">歌手编号</td>
                            <td><input name="singer_id" class="easyui-validatebox" data-options="required:true"/></td>
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
    <a id="entertainment-music-edit-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
    <a id="entertainment-music-edit-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
</div>
</body>
</html>