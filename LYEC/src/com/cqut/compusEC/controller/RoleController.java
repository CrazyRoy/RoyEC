package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.RoleService;

@Controller
@RequestMapping("/roleController")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping("/getRoleList")  
    @ResponseBody
	public JSONObject getRoleList(int limit, int offset, String order, String sort){
		Map<String, Object> result = roleService.getRoleList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/addRole")  
    @ResponseBody
	public JSONObject addRoleInfo(String roleName, String remarks){
		Map<String, Object> result = roleService.addRoleInfo(roleName, remarks);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editRole")  
    @ResponseBody
	public JSONObject editRoleInfo(String roleId, String roleName, String remarks){
		Map<String, Object> result = roleService.editRoleInfo(roleId, roleName, remarks);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/delRole")  
    @ResponseBody
	public JSONObject delRole(String roleIds){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = roleService.delRole(roleIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除角色异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getRoleModuleListInfo")  
    @ResponseBody
	public JSONObject getRoleModuleListInfo(String roleId){
		Map<String, Object> result = roleService.getRoleModuleListInfo(roleId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/saveRoleModuleInfo")  
    @ResponseBody
	public JSONObject saveRoleModuleInfo(String roleId, String moduleIds){
		Map<String, Object> result = roleService.saveRoleModuleInfo(roleId, moduleIds);
		return JSONObject.fromObject(result);
	}
}
