package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.SchoolService;

@Controller
@RequestMapping("/schoolController")
public class SchoolController {
	
	@Resource
	private SchoolService schoolService;
	
	@RequestMapping("/getSchoolList")  
    @ResponseBody
	public JSONObject getSchoolList(int limit, int offset, String order, String sort){
		Map<String, Object> result = schoolService.getSchoolList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/addSchoolInfo")  
    @ResponseBody
	public JSONObject addSchoolInfo(String schoolName,String schoolAddress,String schoolPostcode){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = schoolService.addSchoolInfo(schoolName,schoolAddress,schoolPostcode);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增学校信息异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteSchoolInfo")  
    @ResponseBody
	public JSONObject deleteSchoolInfo(String schoolIds){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = schoolService.deleteSchoolInfo(schoolIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除学校信息异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editSchoolInfo")  
    @ResponseBody
	public JSONObject editSchoolInfo(String schoolId, String schoolName,String schoolAddress,String schoolPostcode){
		Map<String, Object> result = new HashMap<>();
		try {
			result = schoolService.editSchoolInfo(schoolId, schoolName, schoolAddress,schoolPostcode);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改学校信息异常");
		}
		
		return JSONObject.fromObject(result);
	}
}
