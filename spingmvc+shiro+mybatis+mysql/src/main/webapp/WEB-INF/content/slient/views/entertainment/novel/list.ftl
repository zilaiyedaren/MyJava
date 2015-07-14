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
        $('#entertainment-novel-dg').datagrid({
            url:'${ctx}/slient/views/entertainment/novel/novelList',
            queryParams: {
                _: getRandomTime
            },
            method:'get',
            fit:true,
            pagination:true,
            striped:true,
            pageSize:20,
            toolbar:[
                {text: '新增', iconCls: 'icon-add', handler: newNovel},
                {text: '编辑', iconCls: 'icon-edit', handler: editNovel},
                {text: '删除', iconCls: 'icon-remove', handler: deleteNovel},
                {text: '小说章节', iconCls: 'icon-search', handler: chapterList},
                {text: '帮助', iconCls: 'icon-help'}
            ],
            columns:[[
                {field:'ck',checkbox:true},
                {field:'name',title:'小说名',width:150,align:'center'},
                {field:'author',title:'作者',width:150,align:'center'},
                {field:'introduction',title:'简介',width:150,align:'center'},
                {field:'coverUrl',title:'封面地址',width:150,align:'center'},
                {field:'novelUrl',title:'小说地址',width:150,align:'center'},
                {field:'status',title:'状态',width:150,align:'center',formatter:statusFormatter}
            ]]
        });

        function chapterList(){
            var rows = $('#entertainment-novel-dg').datagrid('getSelections');
            if (rows.length == 1) {
                $('#entertainment-novel-dlg').dialog({
                    width: 650,
                    height:400,
                    title: rows[0].name + '小说章节',
                    href:'${ctx}/slient/views/entertainment/chapter/list'
                });
                $('#entertainment-novel-dlg').dialog('open');
            } else if(rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }

        function typeFormatter(val){
            return val == 1? "电影":"电视剧";
        }

        function statusFormatter(val){
            return val == 0?"<font color='red'>锁定</font>":"<font color='green'>打开</font>";
        }


        function newNovel() {
            $('#entertainment-novel-dlg').dialog({
                title: '添加小说',
                href: '${ctx}/slient/views/entertainment/novel/create',
                onLoad: function() {
                    $('#entertainment-novel-new-ok-button').click(saveNovel);
                    $('#entertainment-novel-new-cancel-button').click(closeDialog);
                }
            });
            $('#entertainment-novel-dlg').dialog('open');
        }

        function saveNovel() {
            $('#entertainment-novel-new-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/novel/createNovel',
                success: function(result) {
                    result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-novel-dlg').dialog('close'); // close the dialog
                        $('#entertainment-novel-dg').datagrid('reload'); // reload the User data
                    }
                }
            });
        }

        <#--// 分配用户角色-->
        <#--function editRoles(){-->
            <#--var rows = $('#entertainment-novel-dg').datagrid('getSelections');-->
            <#--if (rows.length == 1) {-->
                <#--$('#entertainment-novel-role-dlg').dialog({-->
                    <#--title: '分配角色',-->
                    <#--href: '${ctx}/rbac/user/editRole.htm',-->
                    <#--onLoad:function() {-->
                        <#--$('#entertainment-novel-editRole-form').form('load', rows[0]);-->
                        <#--$('#entertainment-novel-editRole-save-button').click(correlationRoles);-->
                    <#--}-->
                <#--});-->
                <#--$('#entertainment-novel-role-dlg').dialog('open');-->
            <#--} else if (rows.length == 0) {-->
                <#--$.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});-->
            <#--} else {-->
                <#--$.messager.show({title: '操作提示',msg: '只能选择一行数据'});-->
            <#--}-->
        <#--}-->

        <#--function correlationRoles(){-->
            <#--var role = $('#entertainment-novel-role-dg').datagrid('getSelections');-->
            <#--var user = $('#entertainment-novel-dg').datagrid('getSelections');-->
            <#--var roleIds = new Array();-->
            <#--for (var i = 0; i < role.length; i++) {-->
                <#--roleIds[i] = role[i].id;-->
            <#--}-->
            <#--$.post('${ctx}/rbac/user/correlationRoles.htm', {userId:user[0].id,roleIds:roleIds.join(',')}, function(result){-->
                <#--result = eval('(' + result + ')');-->
                <#--if (!result.success) {-->
                    <#--$.messager.show({title: '错误提示',msg: result.message});-->
                <#--} else {-->
                    <#--$('#entertainment-novel-role-dlg').dialog('close'); // close the dialog-->
                <#--}-->
            <#--});-->
        <#--}-->


        function editNovel() {
            var rows = $('#entertainment-novel-dg').datagrid('getSelections');
            if (rows.length == 1) {
                $('#entertainment-novel-dlg').dialog({
                    title: '编辑小说',
                    href: '${ctx}/slient/views/entertainment/novel/edit',
                    onLoad:function() {
                        $('#entertainment-novel-edit-form').form('load', rows[0]);//将选中行的数据填入表单
                        $('#entertainment-novel-edit-ok-button').click(updateNovel);
                        $('#entertainment-novel-edit-cancel-button').click(closeDialog);
                    }
                });
                $('#entertainment-novel-dlg').dialog('open');
            } else if (rows.length == 0) {
                $.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
            } else {
                $.messager.show({title: '操作提示',msg: '只能选择一行数据'});
            }
        }


        function updateNovel(){
            $('#entertainment-novel-edit-form').form('submit', {
                url: '${ctx}/slient/views/entertainment/novel/editNovel',
                success: function(result){
                    result = eval('('+result+')');
                    if (!result.success){
                        $.messager.show({title: '错误提示',msg: result.message});
                    } else {
                        $('#entertainment-novel-dlg').dialog('close');      // close the dialog
                        $('#entertainment-novel-dg').datagrid('reload');    // reload the User data
                    }
                }
            });
        }

        function deleteNovel() {
            var rows = $('#entertainment-novel-dg').datagrid('getSelections');
            if (rows.length > 0) {
                $.messager.confirm('删除小说', '您要删除所选小说吗?', function(r){
                    if (r){
                        var novelIds = new Array();
                        for (var i = 0; i < rows.length; i++) {
                            novelIds[i] = rows[i].id;
                        }
                        $.ajax({
                            type: "post",
                            url: "${ctx}/slient/views/entertainment/novel/delete",
                            data: {novelIds:novelIds.join(',')},
                            success:function (data, textStatus) {
                                $('#entertainment-novel-dg').datagrid('reload');
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
            $('#entertainment-novel-dlg').dialog('close');
            $('#entertainment-novel-role-dlg').dialog('close');
        }

        $('#entertainment-novel-dg-query-ok-button').click(function() {
            $('#entertainment-novel-dg').datagrid('load', $('#entertainment-novel-query-form').serializeObject());
        });


        $('#entertainment-novel-dg-query-cancel-button').click(function() {
            $('#entertainment-novel-query-form').form('reset');
            $('#entertainment-novel-dg').datagrid('load', {});
        });
    });

</script>
<div class="easyui-layout" data-options="fit:true">
    <div region="north" border="false" class="easyui-panel" title="" style="height: 0px; padding: 5px;">
        <br>
    </div>
    <div region="center" border="false">
        <table id="entertainment-novel-dg"></table>
    </div>
</div>
<div id="entertainment-novel-dlg" class="easyui-dialog" style="width:400px;height:400px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
<div id="entertainment-novel-role-dlg" class="easyui-dialog" style="width:630px;height:450px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
</body>
</html>
