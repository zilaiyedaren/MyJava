<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<script type="text/javascript">
var user = $('#shiro-user-dg').datagrid('getSelections');
var userId = user[0].id;
	$(function() {
		$('#shiro-user-role-dg').datagrid({
			url:'${ctx}/slient/views/shiro/role/roleList',
			queryParams: {
				userId:userId,
				_: getRandomTime
			},
			method:'get',
			fit:true,
			striped:true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'角色Id',width:120,align:'center'},
				{field:'role',title:'角色标识',width:150,align:'center'},
				{field:'description',title:'角色描述',width:150,align:'center'},
				{field:'available',title:'是否可用',width:150,align:'center',formatter:availableFormat}
			]],onLoadSuccess:function(data){
				if(data){
			            $.each(data.rows, function(index, item){
			               if(item.checked){
			                  $('#shiro-user-role-dg').datagrid('checkRow', index);
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
		<form id="shiro-user-editRole-form" action="#">
			当前用户：<input type="text" name="username" readonly/>
			<a id="shiro-user-editRole-save-button" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存角色</a>
		</form>
	</div>
	
	<div region="center" border="false">
	    <table id="shiro-user-role-dg"></table>
	</div>
</body>
</html>
