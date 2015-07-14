<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<#--<script type="text/javascript">-->
    <#--$(function() {-->
        <#--$('#entertainment-film-country').combogrid({-->
            <#--required:true,-->
            <#--editable:false,-->
            <#--idField:'id',-->
            <#--textField:'name',-->
            <#--pagination:true,-->
            <#--panelWidth: 300,-->
            <#--url:'${ctx}/slient/views/tagList.html?type=3',-->
            <#--method:'get',-->
            <#--columns: [[-->
                <#--{field:'id',title:'ID',width:100},-->
                <#--{field:'name',title:'国家',width:100}-->
            <#--]]-->
        <#--});-->

        <#--$('#entertainment-film-screen').combogrid({-->
            <#--required:true,-->
            <#--editable:false,-->
            <#--idField:'id',-->
            <#--textField:'name',-->
            <#--pagination:true,-->
            <#--panelWidth: 300,-->
            <#--url:'${ctx}/slient/views/tagList.html?type=2',-->
            <#--method:'get',-->
            <#--columns: [[-->
                <#--{field:'id',title:'ID',width:100},-->
                <#--{field:'name',title:'年代',width:100}-->
            <#--]]-->
        <#--});-->

        <#--$('#entertainment-film-upload').uploadify({-->
            <#--'fileObjName' : 'file',-->
            <#--'swf'      : '${ctx}/uploadify/uploadify.swf',-->
            <#--'fileTypeExts' : '*jpg;*.png;*.gif.',-->
            <#--'multi'    : false,-->
            <#--'buttonText'    : '添加封面图',-->
            <#--'uploader' : '${ctx}/upload.html',-->
            <#--'onUploadSuccess' : function(file, data, response) {-->
                <#--var	result = eval('(' + data + ')');-->
                <#--$('#coverUrl').val(result.path);-->
            <#--}-->
        <#--});-->
    <#--});-->
<#--</script>-->
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <form id="entertainment-music-new-form" method="post" action="#" >
            <div class="easyui-tabs" data-options="border:false">
                <div title="基本信息" style="padding:10px">
                    <table width="100%">
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
                            <td><input type="text" name="created" class="easyui-validatebox" data-options="required:true"/></td>
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
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
        <a id="entertainment-music-new-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
        <a id="entertainment-music-new-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
    </div>
</body>
</html>
