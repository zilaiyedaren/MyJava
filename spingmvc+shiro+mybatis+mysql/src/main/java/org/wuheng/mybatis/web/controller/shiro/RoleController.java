package org.wuheng.mybatis.web.controller.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.RoleForm;
import org.wuheng.mybatis.web.service.RoleService;
import org.wuheng.mybatis.web.slient.pojo.Role;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

@Controller("roleController")
@RequestMapping("/slient/views/shiro/role")
public class RoleController{

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(){
         SecurityUtils.getSubject().checkPermission("shiro/role:list"); //与前端页面的权限一致
         return new ModelAndView("/slient/views/shiro/role/list");
     }
    @RequestMapping("/roleList")
    public @ResponseBody String roleList(RoleForm roleForm){
        SecurityUtils.getSubject().checkPermission("shiro/role:list"); //与前端页面的权限一致
        DataGridResult dataGridResult=roleService.listRole(roleForm);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }


    @RequestMapping("/create")
    public ModelAndView create(){
        SecurityUtils.getSubject().checkPermission("shiro/role:create");
        return new ModelAndView("/slient/views/shiro/role/create");
    }
    @RequestMapping("/createRole")
    public @ResponseBody String createRole(Role role){
        SecurityUtils.getSubject().checkPermission("shiro/role:create");
        roleService.createRole(role);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(){
        SecurityUtils.getSubject().checkPermission("shiro/role:update");
        return new ModelAndView("/slient/views/shiro/role/edit");
    }
    @RequestMapping("/editRole")
    public @ResponseBody String editRole(Role role){
        SecurityUtils.getSubject().checkPermission("shiro/role:update");
        roleService.updateRole(role);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
    //删除报错
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody String deleteRole(@RequestParam String roleIds){
        SecurityUtils.getSubject().checkPermission("shiro/role:delete");
        String[] strIds=roleIds.split(",");
        Long[] rolesId=new Long[strIds.length];
        for(int i=0;i<strIds.length;i++){
            rolesId[i]=Long.valueOf(strIds[i]);
        }
        roleService.deleteRole(rolesId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/allRoles")
    public @ResponseBody String getAllRole(@PathVariable String userIdStr){
        SecurityUtils.getSubject().checkPermission("shiro/role:allRoles");
        Long userId=Long.valueOf(userIdStr);
        DataGridResult dataGridResult=roleService.getAllRoles(userId);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }

    @RequestMapping("editPermission")
    public ModelAndView editPermission(){
        SecurityUtils.getSubject().checkPermission("shiro/role:correlationPermissions");
        return new ModelAndView("/slient/views/shiro/role/editPermission");
    }
    @RequestMapping(value = "/correlationPermissions",method = RequestMethod.POST)
//    public @ResponseBody String correlationPermissions(@PathVariable String permissionIdsStr,CorrelationPermissionForm correlationPermissionForm){
    public @ResponseBody String correlationPermissions(@RequestParam String roleId,@RequestParam String permissionIds){
        SecurityUtils.getSubject().checkPermission("shiro/role:correlationPermissions");
        String[] strIds=permissionIds.split(",");
        Long[] permissionsId=new Long[strIds.length];
        Long rId=Long.valueOf(roleId);
        for(int i=0;i<strIds.length;i++){
            permissionsId[i]=Long.valueOf(strIds[i]);
        }
        roleService.correlationPermissions(rId,permissionsId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
}
//public class RoleController extends MultiActionController {
//	public ModelAndView roleList(HttpServletRequest request,
//			HttpServletResponse response, RoleForm formBean)
//			throws Exception {
//		SecurityUtils.getSubject().checkPermission("shiro/role:list");
//		RoleService roleService = ServiceFactory.getBean(RoleService.class);
//		DataGridResult dg = roleService.listRole(formBean);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView createRole(HttpServletRequest request,
//			HttpServletResponse response, Role role) throws Exception {
//		SecurityUtils.getSubject().checkPermission("shiro/role:create");
//		RoleService roleService = ServiceFactory.getBean(RoleService.class);
//		roleService.createRole(role);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView updateRole(HttpServletRequest request,
//			HttpServletResponse response, Role role) throws Exception {
//		SecurityUtils.getSubject().checkPermission("shiro/role:updateNovel.ftl");
//		RoleService roleService = ServiceFactory.getBean(RoleService.class);
//		roleService.updateRole(role);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView allRoles(HttpServletRequest request,
//			HttpServletResponse response){
//		SecurityUtils.getSubject().checkPermission("shiro/role:allRoles");
//		Long userId = Long.valueOf(request.getParameter("userId"));
//		RoleService roleService = ServiceFactory.getBean(RoleService.class);
//		DataGridResult dg = roleService.getAllRoles(userId);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView correlationPermissions(HttpServletRequest request,
//			HttpServletResponse response, CorrelationPermissionForm formBean) {
//		SecurityUtils.getSubject().checkPermission("shiro/role:correlationPermissions");
//		RoleService roleService = ServiceFactory.getBean(RoleService.class);
//		String[] strIds = formBean.getPermissionIds().split(",");
//		Long[] permissionIds = new Long[strIds.length];
//		for (int i = 0; i < strIds.length; i++) {
//			permissionIds[i] = Long.valueOf(strIds[i]);
//		}
//		roleService.correlationPermissions(formBean.getRoleId(), permissionIds);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView deleteRole(HttpServletRequest request,
//			HttpServletResponse response) {
//		SecurityUtils.getSubject().checkPermission("shiro/role:deleteRole");
//		String[] strIds = request.getParameter("rolesId").split(",");
//		Long[] rolesId = new Long[strIds.length];
//		for (int i = 0; i < strIds.length; i++) {
//			rolesId[i] = Long.valueOf(strIds[i]);
//		}
//		RoleService roleService = ServiceFactory.getBean(RoleService.class);
//		roleService.deleteRole(rolesId);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//}
