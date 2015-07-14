package org.wuheng.mybatis.web.controller.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.PermissionForm;
import org.wuheng.mybatis.web.service.PermissionService;
import org.wuheng.mybatis.web.slient.pojo.Permission;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

@Controller("permissionController")
@RequestMapping("/slient/views/shiro/permission")
public class PermissionController{

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public ModelAndView  list(){
        SecurityUtils.getSubject().checkPermission("shiro/permission:list");
        return new ModelAndView("/slient/views/shiro/permission/list");
    }
    @RequestMapping("/permissionList")
    public @ResponseBody String permissionList(PermissionForm permissionForm){
        SecurityUtils.getSubject().checkPermission("shiro/permission:list");
        DataGridResult dataGridResult=permissionService.listPermission(permissionForm);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }

    @RequestMapping("/create")
    public ModelAndView  create(){
        SecurityUtils.getSubject().checkPermission("shiro/permission:create");
        return new ModelAndView("/slient/views/shiro/permission/create");
    }

    @RequestMapping("/createPermission")
    public @ResponseBody String createPermission(Permission permission){
        SecurityUtils.getSubject().checkPermission("shiro/permission:create");
        permissionService.createPermission(permission);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(){
        SecurityUtils.getSubject().checkPermission("shiro/permission:update");
        return new ModelAndView("/slient/views/shiro/permission/edit");
    }
    @RequestMapping("/editPermission")
    public @ResponseBody String editPermission(Permission permission){
        SecurityUtils.getSubject().checkPermission("shiro/permission:update");
        permissionService.updatePermission(permission);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody String deletePermission(@RequestParam String permissionIds){
        SecurityUtils.getSubject().checkPermission("shiro/permission:delete");
        String[] strIds = permissionIds.split(",");
		Long[] permissionsId = new Long[strIds.length];
		for (int i = 0; i < strIds.length; i++) {
			permissionsId[i] = Long.valueOf(strIds[i]);
		}
		permissionService.deletePermission(permissionsId);
		String stringView = JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
    @RequestMapping(value = "/allPermissions",method = RequestMethod.GET)
    public @ResponseBody String allPermissions(@RequestParam String roleId){
//        SecurityUtils.getSubject().checkPermission("shiro/permission:allPermissions");
        System.out.println("------------------------------------------"+roleId+"-----------------------------------------------------------");
        Long rId=Long.valueOf(roleId);
        DataGridResult dataGridResult=permissionService.getAllPermissions(rId);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }
}
//public class PermissionController extends MultiActionController {
//	public ModelAndView permissionList(HttpServletRequest request,
//			HttpServletResponse response, PermissionForm formBean)
//			throws Exception {
//		SecurityUtils.getSubject().checkPermission("shiro/permission:list");
//		PermissionService permissionService = ServiceFactory
//				.getBean(PermissionService.class);
//		DataGridResult dg = permissionService.listPermission(formBean);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView createPermission(HttpServletRequest request,
//			HttpServletResponse response, Permission permission) {
//		SecurityUtils.getSubject().checkPermission("shiro/permission:create");
//		PermissionService permissionService = ServiceFactory
//				.getBean(PermissionService.class);
//		permissionService.createPermission(permission);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView updatePermission(HttpServletRequest request,
//			HttpServletResponse response, Permission permission) {
//		SecurityUtils.getSubject().checkPermission("shiro/permission:updateNovel.ftl");
//		PermissionService permissionService = ServiceFactory
//				.getBean(PermissionService.class);
//		permissionService.updatePermission(permission);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView deletePermission(HttpServletRequest request,
//			HttpServletResponse response) {
//		SecurityUtils.getSubject().checkPermission("shiro/permission:delete");
//		String[] strIds = request.getParameter("permissionIds").split(",");
//		Long[] permissionIds = new Long[strIds.length];
//		for (int i = 0; i < strIds.length; i++) {
//			permissionIds[i] = Long.valueOf(strIds[i]);
//		}
//		PermissionService permissionService = ServiceFactory
//				.getBean(PermissionService.class);
//		permissionService.deletePermission(permissionIds);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView allPermissions(HttpServletRequest request,
//			HttpServletResponse response) {
//		SecurityUtils.getSubject().checkPermission("shiro/permission:allPermissions");
//		Long roleId = Long.valueOf(request.getParameter("roleId"));
//		PermissionService permissionService = ServiceFactory
//				.getBean(PermissionService.class);
//		DataGridResult dg = permissionService.getAllPermissions(roleId);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//}
