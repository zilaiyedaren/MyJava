<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限列表</title>
</head>
<body>
<script type="text/javascript">
	$(function() {
		$('#shiro-permission-dg').datagrid({
			url:'${ctx}/slient/views/shiro/permission/permissionList',
			queryParams: {
				_: getRandomTime
			},
			method:'get',
			fit:true,
			pagination:true,
			striped:true,
			pageSize:20,
			toolbar:[
			<@shiro.hasPermission name="shiro/permission:create">
		    	{text: '新增', iconCls: 'icon-add', handler: newPermission},
			</@shiro.hasPermission>
			<@shiro.hasPermission name="shiro/permission:update">
		    	{text: '编辑', iconCls: 'icon-edit', handler: editPermission},
			</@shiro.hasPermission>
			<@shiro.hasPermission name="shiro/permission:delete">
			{text: '删除', iconCls: 'icon-remove', handler: deletePermission},
			</@shiro.hasPermission>
			'-',
			{text: '帮助', iconCls: 'icon-help'}
		    ],
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'权限Id',width:120,align:'center'},
				{field:'permission',title:'权限标识',width:150,align:'center'},
				{field:'description',title:'权限描述',width:150,align:'center'},
				{field:'available',title:'是否可用',width:150,align:'center',formatter:availableFormat}
			]]
		});
		 
		var url;

		function availableFormat(val){
			return val == true?"<font color='green'>可用</font>":"<font color='red'>不可用</font>";
		}
		 
		function newPermission() {
			$('#shiro-permission-dlg').dialog({
				title: '创建权限',
				href: '${ctx}/slient/views/shiro/permission/create',
				onLoad: function() {
					$('#shiro-permission-new-ok-button').click(savePermission);
					$('#shiro-permission-new-cancel-button').click(closeDialog);
				}
			});
		 	$('#shiro-permission-dlg').dialog('open');
		}
		
		
		function editPermission() {
			var rows = $('#shiro-permission-dg').datagrid('getSelections');
			if (rows.length == 1) {
				$('#shiro-permission-dlg').dialog({
					title: '编辑权限',
					href: '${ctx}/slient/views/shiro/permission/edit',
					onLoad:function() {
						$('#shiro-permission-edit-form').form('load', rows[0]);
	            				$('#shiro-permission-edit-ok-button').click(updatePermission);
						$('#shiro-permission-edit-cancel-button').click(closeDialog);
					}
				});
				$('#shiro-permission-dlg').dialog('open');
			} else if (rows.length == 0) {
				$.messager.show({title: '操作提示',msg: '请选择需要操作的数据'});
			} else {
				$.messager.show({title: '操作提示',msg: '只能选择一行数据'});
			}
		}

		function savePermission() {
			$('#shiro-permission-new-form').form('submit', {
				url: '${ctx}/slient/views/shiro/permission/createPermission',
				success: function(result) {
					result = eval('(' + result + ')');
					if (!result.success) {
						$.messager.show({title: '错误提示',msg: result.message});
					} else {
						$('#shiro-permission-dlg').dialog('close'); // close the dialog
						$('#shiro-permission-dg').datagrid('reload'); // reload the Permission data
					}
				}
			});
		}
		
		function updatePermission(){
		    $('#shiro-permission-edit-form').form('submit', {
			url: '${ctx}/slient/views/shiro/permission/editPermission',
			success: function(result){
				result = eval('('+result+')');
				if (!result.success){
				    $.messager.show({title: '错误提示',msg: result.message});
				} else {
				    $('#shiro-permission-dlg').dialog('close');      // close the dialog
				    $('#shiro-permission-dg').datagrid('reload');    // reload the Permission data
				}
			    }
		    });
		}
		
		function deletePermission() {
			var rows = $('#shiro-permission-dg').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('删除权限', '您要删除所选权限吗?', function(r){  
					if (r){
						var permissionIds = new Array();
						for (var i = 0; i < rows.length; i++) {
							permissionIds[i] = rows[i].id;
						}
						$.ajax({
							type: "post",
							url: "${ctx}/slient/views/shiro/permission/delete",
							data: {permissionIds:permissionIds.join(',')},	
							cache:false,
							success:function (data, textStatus) {
								$('#shiro-permission-dg').datagrid('reload');
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
			$('#shiro-permission-dlg').dialog('close');
		}
		
		$('#shiro-permission-dg-query-ok-button').click(function() {
			$('#shiro-permission-dg').datagrid('load', $('#shiro-permission-query-form').serializeObject());
		});
		
		
		$('#shiro-permission-dg-query-cancel-button').click(function() {
			$('#shiro-permission-query-form').form('reset');
			$('#shiro-permission-dg').datagrid('load', {});
		});
	});
	
</script>
<div class="easyui-layout" data-options="fit:true">
<div region="north" border="false" class="easyui-panel" title="" style="height: 0px; padding: 5px;">
<br>
	</div>
	<div region="center" border="false">
	    <table id="shiro-permission-dg"></table>
	</div>
</div>
<div id="shiro-permission-dlg" class="easyui-dialog" style="width:320px;height:250px;padding:5px" data-options="iconCls:'icon-save',modal:true,closed:true,cache:false"></div>
</body>
</html>
