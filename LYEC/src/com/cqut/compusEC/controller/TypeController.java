package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.TypeService;

@Controller
@RequestMapping("/typeController")
public class TypeController {

	@Resource
	private TypeService typeService;
	
	@RequestMapping("/getTypeList")  
    @ResponseBody
	public JSONObject getTypeList(int limit, int offset, String order, String sort){
		Map<String, Object> result = typeService.getTypeList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/addTypeInfo")  
    @ResponseBody
	public JSONObject addTypeInfo(String typeDes,String typeName){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = typeService.addTypeInfo(typeDes,typeName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增目录类型异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteTypeInfo")  
    @ResponseBody
	public JSONObject deleteTypeInfo(String typeIds){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = typeService.deleteTypeInfo(typeIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除目录类型异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editTypeInfo")  
    @ResponseBody
	public JSONObject editTypeInfo(String typeId, String typeDes,String typeName){
		Map<String, Object> result = new HashMap<>();
		try {
			result = typeService.editTypeInfo(typeId, typeDes, typeName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改模块异常");
		}
		
		return JSONObject.fromObject(result);
	}
}
