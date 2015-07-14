<ul class="easyui-tree">
    <li iconCls="icon-base">
        <span>用户权限管理</span>
        <ul>
            <@shiro.hasPermission name="shiro/user:list">
                <li iconCls="icon-gears"><a href="#" onclick="open1('用户列表','${ctx}/slient/views/shiro/user/list')">用户列表</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="shiro/role:list">
                <li iconCls="icon-gears"><a href="javascript:void(0);" onclick="open1('角色列表','${ctx}/slient/views/shiro/role/list')">角色列表</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="shiro/permission:list">
                <li iconCls="icon-gears"><a href="javascript:void(0);" onclick="open1('权限列表','${ctx}/slient/views/shiro/permission/list')">权限列表</a></li>
            </@shiro.hasPermission>
        </ul>
    </li>
    <li iconCls="icon-base">
        <span>休闲娱乐</span>
        <ul>
            <li iconCls="icon-gears"><a href="#" onclick="open1('影视','${ctx}/slient/views/entertainment/film/list')">影视列表</a></li>
            <li iconCls="icon-gears"><a href="#" onclick="open1('音乐','${ctx}/slient/views/entertainment/music/list')">音乐列表</a></li>
            <li iconCls="icon-gears"><a href="#" onclick="open1('小说','${ctx}/slient/views/entertainment/novel/list')">小说列表</a></li>
            <li iconCls="icon-gears"><a href="#" onclick="open1('应用','${ctx}/slient/views/entertainment/apk/list')">应用列表</a></li>
        </ul>
    </li>
</ul>