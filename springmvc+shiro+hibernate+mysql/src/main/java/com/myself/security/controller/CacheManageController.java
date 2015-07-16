package com.myself.security.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myself.security.service.CacheService;
import com.myself.util.dwz.AjaxObject;

@Controller
@RequestMapping("/management/security/cacheManage")
public class CacheManageController {
	@Autowired
	private CacheService cacheService;
	
	private static final String INDEX = "management/security/cacheManage/index";
	
	@RequiresPermissions("CacheManage:view")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return INDEX;
	}
	
	@RequiresPermissions("CacheManage:edit")
	@RequestMapping(value="/clear", method=RequestMethod.POST)
	public @ResponseBody String clear() {
		cacheService.clearAllCache();
		
		AjaxObject ajaxObject = new AjaxObject("清除缓存成功！");
		ajaxObject.setCallbackType("");
		return ajaxObject.toString();
	}
}
