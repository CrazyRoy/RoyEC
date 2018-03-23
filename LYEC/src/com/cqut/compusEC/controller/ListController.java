package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.ListService;

@Controller
@RequestMapping("/listController")
public class ListController {

	@Resource
	private ListService listService;
	
	@RequestMapping("/addListInfo")  
    @ResponseBody
    public JSONObject addListInfo(String parentId, String isLast, String listName, String listDes, String typeId, String listLevel){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = listService.addListInfo(parentId, isLast, listName, listDes, typeId, listLevel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增目录信息异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteListInfo")  
    @ResponseBody
    public JSONObject deleteListInfo(String listIds){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = listService.deleteListInfo(listIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除目录信息异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getListInfo")  
    @ResponseBody
	public JSONObject getListInfo(){
		Map<String, Object> result = listService.getListInfo();
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getLastListInfo")  
    @ResponseBody
	public JSONObject getLastListInfo(){
		Map<String, Object> result = listService.getLastListInfo();
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getFirstListInfo")  
    @ResponseBody
	public JSONObject getFirstListInfo(){
		Map<String, Object> result = listService.getFirstListInfo();
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getChildrenListInfoByListId")  
    @ResponseBody
	public JSONObject getChildrenListInfoByListId(String listId){
		Map<String, Object> result = listService.getChildrenListInfoByListId(listId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editListInfo")  
    @ResponseBody
    public JSONObject editListInfo(String listId, String isLast, String listName, String listDes, String typeId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = listService.editListInfo(listId, isLast, listName, listDes, typeId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改目录信息异常");
		}
		return JSONObject.fromObject(result);
	}
}
