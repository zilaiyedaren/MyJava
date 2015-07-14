<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>用户列表</title>
</head>
<body>
<script type="text/javascript">
    $(function() {
        $('#shiro-user-dg').datagrid({
            url:'${ctx}/slient/views/shiro/user/userList',
            queryParams: {
                _: getRandomTime
            },
            method:'get',
            fit:true,
            pagination:true,
            striped:true,
            pageSize:20,
            toolbar:[
            <@shiro.hasPermission name="shiro/user:create">
                {text: '新增', iconCls: 'icon-add', handler: newUser},
            </@shiro.hasPermission>
            <@shiro.hasPermission name="shiro/user:update">
                {text: '编辑', iconCls: 'icon-edit', handler: editUser},
            </@shiro.hasPermission>
            <@shiro.hasPermission name="shiro/user:delete">
                {text: '删除', iconCls: 'icon-remove', handler: deleteUser},
            </@shiro.hasPermission>
            <@shiro.hasPermission name="shiro/user:correlationRoles">
                {text: '分配角色', iconCls: 'icon-edit', handler: editRoles},
            </@shiro.hasPermission>
                '-',
                {text: '帮助', iconCls: 'icon-help'}
            ],
            //数据填充
            columns:[[
                {field:'ck',checkbox:true},
                {field:'id',title:'用户Id',width:120,align:'center'},
                {field:'username',title:'用户名',width:150,align:'center'},
                {field:'locked',title:'是否锁定',width:150,align:'center',formatter:lockedFormat}
            ]]
        });

        function lockedFormat(val){
            return val == false?"<font color='green'>可用</font>":"<font color='red'>锁定</font>";
        }

        // 分配用户角色
        function editRoles(){
            var rows = $('#shiro-user-dg').datagrid('getSelections');
            if (rows.length == 1) {
                $('#shiro-user-role-dlg').dialog({
                    title: '分配角色',
                    href: '${ctx}/slient/views/shiro/user/editRole',
                    onLoad:function() {
                        $('#shiro-user-editRole-form').form('load', rows[0]);
                        $('#shiro-user-editRole-save-button').click(correlationRoles);
                    }
                });
                $('#shiro-user-role-dlg').dialog('open');
            } else if (rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }

        function correlationRoles(){
            var role = $('#shiro-user-role-dg').datagrid('getSelections');
            var user = $('#shiro-user-dg').datagrid('getSelections');
            var roleIds = new Array();
            for (var i = 0; i < role.length; i++) {
                roleIds[i] = role[i].id;
            }
            $.post('${ctx}/slient/views/shiro/user/correlationRoles', {userId:user[0].id,roleIds:roleIds.join(',')}, function(result){
                result = eval('(' + result + ')');
                if (!result.success) {
                    $.messager.show({title: '错误提示',msg: result.message});
                } else {
                    $('#shiro-user-role-dlg').dialog('close'); // close the dialog
                }
            });
        }

        function newUser() {
            $('#shiro-user-dlg').dialog({
                title: '创建用户',
                href: '${ctx}/slient/views/shiro/user/create',
                onLoad: function() {
                    $('#shiro-user-new-ok-button').click(saveUser);
                    $('#shiro-user-new-cancel-button').click(closeDialog);
                }
            });
            $('#shiro-user-dlg').dialog('open');
        }


        function editUser() {
            var rows = $('#shiro-user-dg').datagrid('getSelections');
            if (rows.length == 1) {
                $('#shiro-user-dlg').dialog({
                    title: '编辑用户',
                    href: '${ctx}/slient/views/shiro/user/edit',
                    onLoad:function() {
                        $('#shiro-user-edit-form').form('load', rows[0]);
                        $('#shiro-user-edit-ok-button').click(updateUser);
                        $('#shiro-user-edit-cancel-button').click(closeDialog);
                    }
                });
                $('#shiro-user-dlg').dialog('open');
            } else if (rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }

        function saveUser() {
            $('#shiro-user-new-form').form('submit', {
                url: '${ctx}/slient/views/shiro/user/createUser',
                success: function(result) {
                    result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#shiro-user-dlg').dialog('close'); // close the dialog
                        $('#shiro-user-dg').datagrid('reload'); // reload the User data
                    }
                }
            });
        }

        function updateUser(){
            $('#shiro-user-edit-form').form('submit', {
                url: '${ctx}/slient/views/shiro/user/editUser',
                success: function(result){
                    result = eval('('+result+')');
                    if (!result.success){
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#shiro-user-dlg').dialog('close');      // close the dialog
                        $('#shiro-user-dg').datagrid('reload');    // reload the User data
                    }
                }
            });
        }

        function deleteUser() {
            var rows = $('#shiro-user-dg').datagrid('getSelections');
            if (rows.length > 0) {
                $.messager.confirm('删除用户', '您要删除所选用户吗?', function(r){
                    if (r){
                        var userIds = new Array();
                        for (var i = 0; i < rows.length; i++) {
                            userIds[i] = rows[i].id;
                        }
                        $.ajax({
                            type: "post",
                            url: "${ctx}/slient/views/shiro/user/delete",
                            data: {userIds:userIds.join(',')},
                            success:function (data, textStatus) {
                                $('#shiro-user-dg').datagrid('reload');
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
            alert("asdsa")
            $('#shiro-user-dlg').dialog('close');
            $('#shiro-user-role-dlg').dialog('close');
        }

        $('#shiro-user-dg-query-ok-button').click(function() {
            $('#shiro-user-dg').datagrid('load', $('#shiro-user-query-form').serializeObject());
        });


        $('#shiro-user-dg-query-cancel-button').click(function() {
            $('#shiro-user-query-form').form('reset');
            $('#shiro-user-dg').datagrid('load', {});
        });
    });

</script>
<div class="easyui-layout" data-options="fit:true">
    <div region="north" border="false" class="easyui-panel" title="" style="height: 0px; padding: 5px;">
        <br>
    </div>
    <div region="center" border="false">
        <table id="shiro-user-dg"></table>
    </div>
</div>
<div id="shiro-user-dlg" class="easyui-dialog" style="width:320px;height:250px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
<div id="shiro-user-role-dlg" class="easyui-dialog" style="width:630px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
</body>
</html>
