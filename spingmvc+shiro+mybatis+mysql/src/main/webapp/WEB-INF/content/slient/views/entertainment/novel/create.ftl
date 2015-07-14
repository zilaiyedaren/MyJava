<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#entertainment-novel-upload').uploadify({
            'fileObjName' : 'file',
            'swf'      : '${ctx}/uploadify/uploadify.swf',
            'fileTypeExts' : '*.jpg;*.png;*.gif',
            'auto': true,
            'multi'    : true,
            'buttonText'    : '添加封面图片',
            'uploader' : '${ctx}/upload/image.html',
            'onUploadSuccess' : function(file, data, response) {
                var	result = eval('(' + data + ')');
                $('#coverUrl').val(result.path);
            }
        });
    });
    $(function() {
        $('#entertainment-noveltxt-upload').uploadify({
            'fileObjName' : 'file',
            'swf'      : '${ctx}/uploadify/uploadify.swf',
            'fileTypeExts' : '*.txt',
            'auto': true,
            'multi'    : true,
            'buttonText'    : '添加小说',
            'uploader' : '${ctx}/upload/text.html',
            'onUploadSuccess' : function(file, data, response) {
                var	result = eval('(' + data + ')');
                $('#novelUrl').val(result.path);
            }
        });
    });

</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <form id="entertainment-novel-new-form" method="post" action="#" >
            <div class="easyui-tabs" data-options="border:false">
                <div title="小说信息" style="padding:10px">
                    <table width="100%">
                        <tr>
                            <td width="100">小说名称</td>
                            <td><input name="name" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">作者</td>
                            <td><input name="author" class="easyui-validatebox" data-options="required:true"/></td>
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
                        <tr>
                            <td>图片文件上传</td>
                            <td>
                                <input type="file" name="entertainment-novel-upload" id="entertainment-novel-upload" multiple="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>小说文件上传</td>
                            <td>
                                <input type="file" name="entertainment-noveltxt-upload" id="entertainment-noveltxt-upload" multiple="true"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
        <a id="entertainment-novel-new-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
        <a id="entertainment-novel-new-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
    </div>
</body>
</html>
