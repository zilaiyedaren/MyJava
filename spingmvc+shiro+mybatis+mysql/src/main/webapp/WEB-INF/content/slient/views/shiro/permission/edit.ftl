<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<form id="shiro-permission-edit-form" method="post" action="#">
			<div class="easyui-tabs" data-options="border:false">
				<div title="基本信息" style="padding:10px">
					<table width="100%">
						<input name="id" type="hidden" class="easyui-validatebox" data-options="required:true"/>
						<tr>
							<td width="100">权限标识</td>
							<td><input name="permission" class="easyui-validatebox" data-options="required:true"/></td>
						</tr>
						<tr>
							<td>权限描述</td>
							<td>
								<input name="description" class="easyui-validatebox" data-options="required:true"/>
							</td>
						</tr>
						<tr>
							<td>是否可用</td>
							<td>
								<select name="available" class="easyui-combobox" data-options="required:true">
									<option value=true>可用</option>
									<option value=false>不可用</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				</div>
			</form>
		</div>
	<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
		<a id="shiro-permission-edit-ok-button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)">确定</a>
		<a id="shiro-permission-edit-cancel-button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
	</div>
</body>
</html>
