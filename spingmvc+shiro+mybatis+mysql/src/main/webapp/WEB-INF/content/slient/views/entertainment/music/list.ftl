<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>音乐列表</title>
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#entertainment-music-dg').datagrid({
            url:'${ctx}/slient/views/entertainment/music/musicList',
            queryParams: {
                _: getRandomTime
            },
            method:'get',
            fit:true,
            pagination:true,
            striped:true,
            pageSize:20,
            toolbar:[
                {text: '新增', iconCls: 'icon-add', handler: newMusic},
                {text: '编辑', iconCls: 'icon-edit', handler: editMusic},
                {text: '删除', iconCls: 'icon-remove', handler: deleteMusic},
                '-',
                {text: '帮助', iconCls: 'icon-help'}
            ],
            columns:[[
                {field:'ck',checkbox:true},
                {field:'name',title:'音乐名',width:150,align:'center'},
                {field:'music_url',title:'下载链接',width:150,align:'center'},
//                {field:'music_url',title:'下载链接',width:150,align:'center',formatter:typeFormatter},
                {field:'created',title:'发行时间',width:150,align:'center'},
                {field:'singer_id',title:'歌手编号',width:150,align:'center'},
                {field:'publiced',title:'状态',width:150,align:'center',formatter:statusFormatter}
            ]]
        });

        function typeFormatter(val){
            return val == 1? "民族风":"流行乐";
        }

        function statusFormatter(val){
            return val == 0?"<font color='red'>锁定</font>":"<font color='green'>打开</font>";
        }


        function newMusic() {
            $('#entertainment-music-dlg').dialog({
                title: '添加音乐',
                href: '${ctx}/slient/views/entertainment/music/create',
                onLoad: function() {
                    $('#entertainment-music-new-ok-button').click(saveMusic);
                    $('#entertainment-music-new-cancel-button').click(closeDialog);
                }
            });
            $('#entertainment-music-dlg').dialog('open');
        }

        function saveMusic() {
            $('#entertainment-music-new-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/music/createMusic',
                success: function(result) {
                    result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-music-dlg').dialog('close'); // close the dialog
                        $('#entertainment-music-dg').datagrid('reload'); // reload the User data
                    }
                }
            });
        }




        function editMusic() {
            var rows = $('#entertainment-music-dg').datagrid('getSelections');
            if (rows.length == 1) {
                $('#entertainment-music-dlg').dialog({
                    title: '编辑歌曲',
                    href: '${ctx}/slient/views/entertainment/music/edit',
                    onLoad:function() {
                        $('#entertainment-music-edit-form').form('load', rows[0]);
                        $('#entertainment-music-edit-ok-button').click(updateMusic);
                        $('#entertainment-music-edit-cancel-button').click(closeDialog);
                    }
                });
                $('#entertainment-music-dlg').dialog('open');
            } else if (rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }


        function updateMusic(){
            $('#entertainment-music-edit-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/music/editMusic',
                success: function(result){
                    result = eval('('+result+')');
                    if (!result.success){
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-music-dlg').dialog('close');      // close the dialog
                        $('#entertainment-music-dg').datagrid('reload');    // reload the User data
                    }
                }
            });
        }

        function deleteMusic() {
            var rows = $('#entertainment-music-dg').datagrid('getSelections');
            if (rows.length > 0) {
                $.messager.confirm('删除歌曲', '您要删除所选歌曲吗?', function(r){
                    if (r){
                        var musicIds = new Array();
                        for (var i = 0; i < rows.length; i++) {
                            musicIds[i] = rows[i].id;
                        }
                        $.ajax({
                            type: "post",
                            url: "${ctx}/slient/views/entertainment/music/delete",
                            data: {musicIds:musicIds.join(',')},
                            success:function (data, textStatus) {
                                $('#entertainment-music-dg').datagrid('reload');
                            },
                            error: function(){
                                $.messager.show({title: '错误提示',msg: "操作失败！"});
                            }
                        });
                    }
                });
            } else {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            }
        }

        function closeDialog() {
            $('#entertainment-music-dlg').dialog('close');
            $('#entertainment-music-role-dlg').dialog('close');
        }

        $('#entertainment-music-dg-query-ok-button').click(function() {
            $('#entertainment-music-dg').datagrid('load', $('#entertainment-music-query-form').serializeObject());
        });


        $('#entertainment-music-dg-query-cancel-button').click(function() {
            $('#entertainment-music-query-form').form('reset');
            $('#entertainment-music-dg').datagrid('load', {});
        });
    });

</script>
<div class="easyui-layout" data-options="fit:true">
    <div region="north" border="false" class="easyui-panel" title="" style="height: 0px; padding: 5px;">
        <br>
    </div>
    <div region="center" border="false">
        <table id="entertainment-music-dg"></table>
    </div>
</div>
<div id="entertainment-music-dlg" class="easyui-dialog" style="width:400px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
<div id="entertainment-music-role-dlg" class="easyui-dialog" style="width:630px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
</body>
</html>
