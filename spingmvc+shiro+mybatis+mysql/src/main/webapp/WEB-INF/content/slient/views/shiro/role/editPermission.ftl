<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<script type="text/javascript">
var role = $('#shiro-role-dg').datagrid('getSelections');
var roleId = role[0].id;
	$(function() {
		$('#shiro-role-permission-dg').datagrid({
			url:'${ctx}/slient/views/shiro/permission/allPermissions',
			queryParams: {
				roleId:roleId,
				_: getRandomTime
			},
			method:'get',
			fit:true,
			striped:true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'权限Id',width:120,align:'center'},
				{field:'permission',title:'权限标识',width:150,align:'center'},
				{field:'description',title:'权限描述',width:150,align:'center'},
				{field:'available',title:'是否可用',width:150,align:'center',formatter:availableFormat}
			]],onLoadSuccess:function(data){
				if(data){
			            $.each(data.rows, function(index, item){
			               if(item.checked){
			                  $('#shiro-role-permission-dg').datagrid('checkRow', index);
			                }
			            });
			        }	
			}
				
		});

		function availableFormat(val){
			return val == true?"<font color='green'>可用</font>":"<font color='red'>不可用</font>";
		}
	});
</script>
<div class="easyui-layout" data-options="fit:true">
	<div region="north" border="false" class="easyui-panel" style="height: 40px; padding: 5px;">
		<form id="shiro-role-editPermission-form" action="#">
			当前角色：<input type="text" name="role" readonly/>
			<a id="shiro-role-editPermission-save-button" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存权限</a>
		</form>
	</div>
	
	<div region="center" border="false">
	    <table id="shiro-role-permission-dg"></table>
	</div>
</body>
</html>
