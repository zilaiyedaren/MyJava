<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#entertainment-novel-edit-upload').uploadify({
            'disable':true,
            'fileObjName' : 'file',
            'swf'      : '${ctx}/resources/uploadify/uploadify.swf',
            'fileTypeExts' : '*jpg;*.png;*.gif',
            'auto': true,
            'multi'    : false,
            'buttonText'    : '添加封面图',
            'uploader' : '${ctx}/upload.htm',
            'onUploadSuccess' : function(file, data, response) {
                var	result = eval('(' + data + ')');
                $('#coverUrl').val(result.path);
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <form id="entertainment-novel-edit-form" method="post" action="#" >
            <div class="easyui-tabs" data-options="border:false">
                <div title="小说信息" style="padding:10px">
                    <table width="100%">
                        <input name="id" type="hidden" class="easyui-validatebox" data-options="required:true" readonly/>
                        <tr>
                            <td width="100">小说名称</td>
                            <td><input name="name" class="easyui-validatebox" data-options="required:true" readonly/></td>
                        </tr>
                        <tr>
                            <td width="100">作者</td>
                            <td><input name="author" class="easyui-validatebox" data-options="required:true" readonly/></td>
                        </tr>
                        <tr>
                            <td width="100">小说地址</td>
                            <td><input name="novelUrl" id="novelUrl" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">封面图地址</td>
                            <td><input name="coverUrl" id="coverUrl" class="easyui-validatebox" data-options="required:true" readonly/></td>
                        </tr>
                        <tr>
                            <td width="100">简介</td>
                            <td><textarea name="introduction" class="easyui-validatebox" data-options="required:true"></textarea></td>
                        </tr>
                        <tr>
                            <td>状态</td>
                            <td>
                                <select name="status" class="easyui-combobox" data-options="required:true">
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
        <a id="entertainment-novel-edit-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
        <a id="entertainment-novel-edit-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
    </div>
</body>
</html>
