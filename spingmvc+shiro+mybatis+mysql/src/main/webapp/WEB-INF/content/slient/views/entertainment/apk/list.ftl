<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>应用列表</title>
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#entertainment-apk-dg').datagrid({
            url:'${ctx}/slient/views/entertainment/apk/apkList',
            queryParams: {
                _: getRandomTime
            },
            method:'get',
            fit:true,
            pagination:true,
            striped:true,
            pageSize:20,
            toolbar:[
                {text: '新增', iconCls: 'icon-add', handler: newApk},
                {text: '编辑', iconCls: 'icon-edit', handler: editApk},
                {text: '删除', iconCls: 'icon-remove', handler: deleteApk},
                '-',
                {text: '帮助', iconCls: 'icon-help'}
            ],
            columns:[[
                {field:'ck',checkbox:true},
                {field:'name',title:'应用名',width:150,align:'center'},
                {field:'pack_name',title:'别名',width:150,align:'center'},
                {field:'introduction',title:'应用介绍',width:150,align:'center'},
                {field:'version_code',title:'版本号',width:150,align:'center'},
                {field:'icon_url',title:'下载链接',width:150,align:'center'},
                {field:'type',title:'类别',width:150,align:'center',formatter:typeFormatter},
                {field:'created',title:'上架时间',width:150,align:'center'},
                {field:'publiced',title:'状态',width:150,align:'center',formatter:statusFormatter}
            ]]
        });

        function typeFormatter(val){
            return val == 1? "IOS":"Android";
        }

        function statusFormatter(val){
            return val == 0?"<font color='red'>锁定</font>":"<font color='green'>打开</font>";
        }


        function newApk() {
            $('#entertainment-apk-dlg').dialog({
                title: '添加应用',
                href: '${ctx}/slient/views/entertainment/apk/create',
                onLoad: function() {
                    $('#entertainment-apk-new-ok-button').click(saveApk);
                    $('#entertainment-apk-new-cancel-button').click(closeDialog);
                }
            });
            $('#entertainment-apk-dlg').dialog('open');
        }

        function saveApk() {
            $('#entertainment-apk-new-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/apk/createApk',
                success: function(result) {
                    result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-apk-dlg').dialog('close'); // close the dialog
                        $('#entertainment-apk-dg').datagrid('reload'); // reload the User data
                    }
                }
            });
        }
        function editApk() {
            var rows = $('#entertainment-apk-dg').datagrid('getSelections');
            if (rows.length == 1) {
                $('#entertainment-apk-dlg').dialog({
                    title: '编辑应用',
                    href: '${ctx}/slient/views/entertainment/apk/edit',
                    onLoad:function() {
                        $('#entertainment-apk-edit-form').form('load', rows[0]);
                        $('#entertainment-apk-edit-ok-button').click(updateApk);
                        $('#entertainment-apk-edit-cancel-button').click(closeDialog);
                    }
                });
                $('#entertainment-apk-dlg').dialog('open');
            } else if (rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }


        function updateApk(){
            $('#entertainment-apk-edit-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/apk/editApk',
                success: function(result){
                    result = eval('('+result+')');
                    if (!result.success){
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-apk-dlg').dialog('close');      // close the dialog
                        $('#entertainment-apk-dg').datagrid('reload');    // reload the User data
                    }
                }
            });
        }

        function deleteApk() {
            var rows = $('#entertainment-apk-dg').datagrid('getSelections');
            if (rows.length > 0) {
                $.messager.confirm('删除应用', '您要删除所选应用吗?', function(r){
                    if (r){
                        var apkIds = new Array();
                        for (var i = 0; i < rows.length; i++) {
                            apkIds[i] = rows[i].id;
                        }
                        $.ajax({
                            type: "post",
                            url: "${ctx}/slient/views/entertainment/apk/delete",
                            data: {apkIds:apkIds.join(',')},
                            success:function (data, textStatus) {
                                $('#entertainment-apk-dg').datagrid('reload');
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
            $('#entertainment-apk-dlg').dialog('close');
            $('#entertainment-apk-role-dlg').dialog('close');
        }

        $('#entertainment-apk-dg-query-ok-button').click(function() {
            $('#entertainment-apk-dg').datagrid('load', $('#entertainment-apk-query-form').serializeObject());
        });


        $('#entertainment-apk-dg-query-cancel-button').click(function() {
            $('#entertainment-apk-query-form').form('reset');
            $('#entertainment-apk-dg').datagrid('load', {});
        });
    });

</script>
<div class="easyui-layout" data-options="fit:true">
    <div region="north" border="false" class="easyui-panel" title="" style="height: 0px; padding: 5px;">
        <br>
    </div>
    <div region="center" border="false">
        <table id="entertainment-apk-dg"></table>
    </div>
</div>
<div id="entertainment-apk-dlg" class="easyui-dialog" style="width:400px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
<div id="entertainment-apk-role-dlg" class="easyui-dialog" style="width:630px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
</body>
</html>
