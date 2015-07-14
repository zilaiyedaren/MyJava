<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>影视列表</title>
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#entertainment-film-dg').datagrid({
            url:'${ctx}/slient/views/entertainment/film/listFilm',
            queryParams: {
                _: getRandomTime
            },
            method:'get',
            fit:true,
            pagination:true,
            striped:true,
            pageSize:20,
            toolbar:[
                {text: '新增', iconCls: 'icon-add', handler: newFilm},
                {text: '编辑', iconCls: 'icon-edit', handler: editFilm},
                {text: '删除', iconCls: 'icon-remove', handler: deleteFilm},
                '-',
                {text: '帮助', iconCls: 'icon-help'}
            ],
            columns:[[
                {field:'ck',checkbox:true},
                {field:'name',title:'影片名',width:150,align:'center'},
                {field:'type',title:'类别',width:150,align:'center',formatter:typeFormatter},
                {field:'country',title:'国家',width:150,align:'center'},
                {field:'screen',title:'年代',width:150,align:'center'},
                {field:'star',title:'星级',width:150,align:'center'},
                {field:'publiced',title:'状态',width:150,align:'center',formatter:statusFormatter}
            ]]
        });

        function typeFormatter(val){
            return val == 1? "电影":"电视剧";
        }

        function statusFormatter(val){
            return val == 0?"<font color='red'>锁定</font>":"<font color='green'>打开</font>";
        }


        function newFilm() {
            $('#entertainment-film-dlg').dialog({
                title: '添加影片',
                href: '${ctx}/slient/views/entertainment/film/create',
                onLoad: function() {
                    $('#entertainment-film-new-ok-button').click(saveFilm);
                    $('#entertainment-film-new-cancel-button').click(closeDialog);
                }
            });
            $('#entertainment-film-dlg').dialog('open');
        }

        function saveFilm() {
            $('#entertainment-film-new-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/film/createFilm',
                success: function(result) {
                    result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-film-dlg').dialog('close'); // close the dialog
                        $('#entertainment-film-dg').datagrid('reload'); // reload the User data
                    }
                }
            });
        }

        function editFilm() {
            //获取表格中选中的行
            var rows = $('#entertainment-film-dg').datagrid('getSelections');

            if (rows.length == 1) {
                $('#entertainment-film-dlg').dialog({
                    title: '编辑影片',
                    href: '${ctx}/slient/views/entertainment/film/edit',
                    onLoad:function() {
                        $('#entertainment-film-edit-form').form('load', rows[0]);
                        $('#entertainment-film-edit-ok-button').click(updateFilm);
                        $('#entertainment-film-edit-cancel-button').click(closeDialog);
                    }
                });
                $('#entertainment-film-dlg').dialog('open');
            } else if (rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }


        function updateFilm(){
            //联级修改影片国家和年代的信息
            var row =  $('#entertainment-film-edit-form').form('load');
            var objs=$(".combo-text");
            row.country= $(objs[1]).val();
            row.screen=$(objs[2]).val();
            $('#entertainment-film-edit-form').form('load',row);

            $('#entertainment-film-edit-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/film/editFilm',
                success: function(result){
                    result = eval('('+result+')');
                    if (!result.success){
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-film-dlg').dialog('close');      // close the dialog
                        $('#entertainment-film-dg').datagrid('reload');    // reload the User data
                    }
                }
            });
        }

        function deleteFilm() {
            var rows = $('#entertainment-film-dg').datagrid('getSelections');
            if (rows.length > 0) {
                $.messager.confirm('删除电影', '您要删除所选电影吗?', function(r){
                    if (r){
                        var filmIds = new Array();
                        for (var i = 0; i < rows.length; i++) {
                            filmIds[i] = rows[i].id;
                        }
                        $.ajax({
                            type: "post",
                            url: "${ctx}/slient/views/entertainment/film/delete",
                            data: {filmIds:filmIds.join(',')},
                            success:function (data, textStatus) {
                                $('#entertainment-film-dg').datagrid('reload');
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
            $('#entertainment-film-dlg').dialog('close');
            $('#entertainment-film-role-dlg').dialog('close');
        }

        $('#entertainment-film-dg-query-ok-button').click(function() {
            $('#entertainment-film-dg').datagrid('load', $('#entertainment-film-query-form').serializeObject());
        });


        $('#entertainment-film-dg-query-cancel-button').click(function() {
            $('#entertainment-film-query-form').form('reset');
            $('#entertainment-film-dg').datagrid('load', {});
        });
    });

</script>
<div class="easyui-layout" data-options="fit:true">
    <div region="north" border="false" class="easyui-panel" title="" style="height: 0px; padding: 5px;">
        <br>
    </div>
    <div region="center" border="false">
        <table id="entertainment-film-dg"></table>
    </div>
</div>
<div id="entertainment-film-dlg" class="easyui-dialog" style="width:400px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
<div id="entertainment-film-role-dlg" class="easyui-dialog" style="width:630px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
</body>
</html>
