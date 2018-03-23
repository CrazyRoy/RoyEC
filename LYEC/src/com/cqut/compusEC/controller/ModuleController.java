package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.ModuleService;

/**
 * 模块Controller
 * @author LV
 *
 */
@Controller
@RequestMapping("/moduleController")
public class ModuleController {

	@Resource
	private ModuleService moduleService;
	
	@RequestMapping("/getModuleByAccountId")  
    @ResponseBody
	public JSONArray getModuleByAccountId(String userId){
		List<Map<String, Object>> result = moduleService.getModuleByAccountId(userId);
		return JSONArray.fromObject(result);
	}
	
	@RequestMapping("/getModuleList")  
    @ResponseBody
	public JSONObject getModuleList(int limit, int offset, String order, String sort){
		Map<String, Object> result = moduleService.getModuleList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/addModuleInfo")  
    @ResponseBody
	public JSONObject addModuleInfo(String moduleName, String url, String remarks){
		Map<String, Object> result = new HashMap<>();
		try {
			result = moduleService.addModuleInfo(moduleName, url, remarks);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增模块异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteModuleInfo")  
    @ResponseBody
	public JSONObject deleteModuleInfo(String moduleIds){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = moduleService.deleteModuleInfo(moduleIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除模块异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editModuleInfo")  
    @ResponseBody
	public JSONObject editModuleInfo(String moduleId, String moduleName, String url, String remarks){
		Map<String, Object> result = new HashMap<>();
		try {
			result = moduleService.editModuleInfo(moduleId, moduleName, url, remarks);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改模块异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editModuleState")  
    @ResponseBody
	public JSONObject editModuleState(String moduleIds, String state){
		Map<String, Object> result = moduleService.editModuleState(moduleIds, state);
		return JSONObject.fromObject(result);
	}
}
