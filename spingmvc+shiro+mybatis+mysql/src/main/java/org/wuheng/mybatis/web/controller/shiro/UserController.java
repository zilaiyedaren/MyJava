package org.wuheng.mybatis.web.controller.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.UserForm;
import org.wuheng.mybatis.web.service.UserService;
import org.wuheng.mybatis.web.slient.pojo.User;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;
import org.wuheng.mybatis.web.utils.PasswordHelper;

@Controller("userController")
@RequestMapping("/slient/views/shiro/user")
public class UserController{

    @Autowired
    private UserService userService;

    /**
     * 显示用户李彪页面
     * @return
     * @throws Exception
     */
   @RequestMapping("/list")
    public ModelAndView list() throws Exception{
       SecurityUtils.getSubject().checkPermission("shiro/user:list");//检查当前登录用户的权限
       return new ModelAndView("/slient/views/shiro/user/list");
   }

    /**
     * 获取用户列表数据
     * @param userForm
     * @return
     */
   @RequestMapping("userList")
   public @ResponseBody String userList(UserForm userForm){
      SecurityUtils.getSubject().checkPermission("shiro/user:list");
       DataGridResult dataGridResult=userService.listUser(userForm);
       String stringView=JsonUtil.getJsonStr(dataGridResult);
       return stringView;
   }
    @RequestMapping("/create")
    public  ModelAndView create() throws Exception{
        return new ModelAndView("/slient/views/shiro/user/create");
    }

    @RequestMapping("/createUser")
    public @ResponseBody String createUser(User user) throws Exception{
        SecurityUtils.getSubject().checkPermission("shiro/user:create");
        PasswordHelper passwordHelper=new PasswordHelper();
        //在新用户信息持久化之前需要对密码进行加密
        passwordHelper.encryptPassword(user);
        userService.createUser(user);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }


    @RequestMapping("/edit")
    public ModelAndView edit(){
        SecurityUtils.getSubject().checkPermission("shiro/user:update");
        return new ModelAndView("/slient/views/shiro/user/edit");
    }
    @RequestMapping("/editUser")
    public @ResponseBody String editUser(User user){
        SecurityUtils.getSubject().checkPermission("shiro/user:update");
        userService.updateUser(user);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/editRole")
    public ModelAndView editRole(){
        return new ModelAndView("/slient/views/shiro/user/editRole");
    }
    @RequestMapping(value = "/correlationRoles",method = RequestMethod.POST)
//    public @ResponseBody String correlationRoles(CorrelationRoleForm correlationRoleForm){
    public @ResponseBody String correlationRoles(@RequestParam String userId,@RequestParam String roleIds){
        SecurityUtils.getSubject().checkPermission("shiro/user:correlationRoles");
        String[] strIds = roleIds.split(",");
		Long[] rolesId = new Long[strIds.length];
        Long uId=Long.valueOf(userId);
		for (int i = 0; i < strIds.length; i++) {
			rolesId[i] = Long.valueOf(strIds[i]);
		}
		userService.correlationRoles(uId, rolesId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public @ResponseBody String deleteUser(@RequestParam String userIds){
        SecurityUtils.getSubject().checkPermission("shiro/user:delete");
        String[] idsStr=userIds.split(",");
        Long[] usersId=new Long[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            usersId[i]=Long.valueOf(idsStr[i]);
        }

        userService.deleteUser(usersId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
}
//public class UserController extends MultiActionController {
//
//	public ModelAndView userList(HttpServletRequest request,
//			HttpServletResponse response, UserForm formBean)
//			throws Exception {
//		SecurityUtils.getSubject().checkPermission("shiro/user:list");
//		UserService userService = ServiceFactory.getBean(UserService.class);
//		DataGridResult dg = userService.listUser(formBean);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView createUser(HttpServletRequest request,
//			HttpServletResponse response, User user) {
//		SecurityUtils.getSubject().checkPermission("shiro/user:create");
//		UserService userService = ServiceFactory.getBean(UserService.class);
//		userService.createUser(user);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView updateUser(HttpServletRequest request,
//			HttpServletResponse response, User user) {
//		SecurityUtils.getSubject().checkPermission("shiro/user:updateNovel.ftl");
//		UserService userService = ServiceFactory.getBean(UserService.class);
//		userService.updateUser(user);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView deleteUser(HttpServletRequest request,
//			HttpServletResponse response) {
//		SecurityUtils.getSubject().checkPermission("shiro/user:delete");
//		String[] strIds = request.getParameter("userIds").split(",");
//		Long[] userIds = new Long[strIds.length];
//		for (int i = 0; i < strIds.length; i++) {
//			userIds[i] = Long.valueOf(strIds[i]);
//		}
//		UserService userService = ServiceFactory.getBean(UserService.class);
//		userService.deleteUser(userIds);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView correlationRoles(HttpServletRequest request,
//			HttpServletResponse response, CorrelationRoleForm formBean) {
//		SecurityUtils.getSubject().checkPermission("shiro/user:correlationRoles");
//		UserService userService = ServiceFactory.getBean(UserService.class);
//		String[] strIds = formBean.getRoleId().split(",");
//		Long[] roleIds = new Long[strIds.length];
//		for (int i = 0; i < strIds.length; i++) {
//			roleIds[i] = Long.valueOf(strIds[i]);
//		}
//		userService.correlationRoles(formBean.getUserId(), roleIds);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//}
