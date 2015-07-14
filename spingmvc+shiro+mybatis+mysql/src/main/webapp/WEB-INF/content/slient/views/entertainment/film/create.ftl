<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#entertainment-film-country').combogrid({
            required:true,
            editable:false,
            idField:'id',
            textField:'name',
            pagination:true,
            panelWidth: 300,
            url:'${ctx}/slient/views/tagList.html?type=3',
            method:'get',
            columns: [[
                {field:'id',title:'ID',width:100},
                {field:'name',title:'国家',width:100}
            ]]
        });

        $('#entertainment-film-screen').combogrid({
            required:true,
            editable:false,
            idField:'id',
            textField:'name',
            pagination:true,
            panelWidth: 300,
            url:'${ctx}/slient/views/tagList.html?type=2',
            method:'get',
            columns: [[
                {field:'id',title:'ID',width:100},
                {field:'name',title:'年代',width:100}
            ]]
        });

        $('#entertainment-film-upload').uploadify({
            'fileObjName' : 'file',
            'swf'      : '${ctx}/uploadify/uploadify.swf',
            'fileTypeExts' : '*jpg;*.png;*.gif.',
            'multi'    : false,
            'buttonText'    : '添加封面图',
            'uploader' : '${ctx}/upload.html',
            'onUploadSuccess' : function(file, data, response) {
                var	result = eval('(' + data + ')');
                $('#coverUrl').val(result.path);
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'">
        <form id="entertainment-film-new-form" method="post" action="#" >
            <div class="easyui-tabs" data-options="border:false">
                <div title="基本信息" style="padding:10px">
                    <table width="100%">
                        <tr>
                            <td width="100">影片名称</td>
                            <td><input name="name" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">影片类别</td>
                            <td>
                                <select name="type" class="easyui-combobox" data-options="required:true">
                                    <option value=1>电影</option>
                                    <option value=2>电视剧</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="100">时长</td>
                            <td><input name="duration" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">导演</td>
                            <td><input name="director" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">演员</td>
                            <td><input name="actor" class="easyui-validatebox" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">国家</td>
                            <td><input id="entertainment-film-country" name="countryId" class="easyui-validatebox"/></td>
                        </tr>
                        <tr>
                            <td width="100">上映时间</td>
                            <td><input id="entertainment-film-screen" name="screenId" class="easyui-validatebox"/></td>
                        </tr>
                        <tr>
                            <td width="100">星级</td>
                            <td><input name="star" class="easyui-numberbox" min="0" max="9.9" precision="1" data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td width="100">封面图地址</td>
                            <td><input name="coverUrl" id="coverUrl" class="easyui-validatebox" data-options="required:true" /></td>
                        </tr>
                        <tr>
                            <td width="100">简介</td>
                            <td><textarea name="introduction" class="easyui-validatebox" data-options="required:true"></textarea></td>
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
                        <tr>
                            <td>图片文件上传</td>
                            <td>
                                <input type="file" name="entertainment-film-upload" id="entertainment-film-upload" multiple="true"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
        <a id="entertainment-film-new-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
        <a id="entertainment-film-new-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
    </div>
</body>
</html>
