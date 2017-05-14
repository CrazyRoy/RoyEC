package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.MainClassService;
import com.cqut.compusEC.service.ProductService;
import com.cqut.compusEC.service.SubClassService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Resource
	private ProductService productService;
	@Resource
	private SubClassService subClassService;
	@Resource
	private MainClassService mainClassService;
	
	// 根据主类别id查询子类别方法
	@ResponseBody
	@RequestMapping("/querySubClassByMainId")
	public Object querySubClassByMainId(String mainId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer mainIdInt = Integer.parseInt(mainId);
			List<Map<String, Object>> subClassList = subClassService.querySubClassByMainId(mainIdInt);
			resultMap.put("resultList", subClassList);
			resultMap.put("msg", "success");
		}catch(Exception e) {
			resultMap.put("msg", "error");
		};
		return resultMap;
	}
	
	// 查询主类别id查询商品方法
	@ResponseBody
	@RequestMapping("/queryProductByMainId")
	public Object queryProductByMainId(String mainId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer mainIdInt = Integer.parseInt(mainId);
			List<Map<String, Object>> products = productService.queryProductByMainId(mainIdInt);
			resultMap.put("resultList", products);
			resultMap.put("msg", "success");
		}catch(Exception e) {
			resultMap.put("msg", "error");
		};
		return resultMap;
	}
	
	// 根据子类别id查询商品方法
	@ResponseBody
	@RequestMapping("/queryProductBySubId")
	public Object queryProductBySubId(String subId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer subIdInt = Integer.parseInt(subId);
			List<Map<String, Object>> products = productService.queryProductBySubId(subIdInt);
			resultMap.put("resultList", products);
			resultMap.put("msg", "success");
		}catch(Exception e) {
			resultMap.put("msg", "error");
		};
		return resultMap;
	}
	
	// 查询主类别id查询子类别方法
	@ResponseBody
	@RequestMapping("/queryMainClassNameById")
	public Object queryMainClassNameById(String mainId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer mainIdInt = Integer.parseInt(mainId);
			List<Map<String, Object>> subClasss = mainClassService.queryMainClassNameById(mainIdInt);
			resultMap.put("resultList", subClasss);
			resultMap.put("msg", "success");
		}catch(Exception e) {
			resultMap.put("msg", "error");
		};
		return resultMap;
	}

	// 通过子类别id查询类别名称
	@ResponseBody
	@RequestMapping("/querySubClassNameById")
	public Object querySubClassNameById(String subId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer subIdInt = Integer.parseInt(subId);
			List<Map<String, Object>> subClasss = subClassService.querySubClassNameById(subIdInt);
			resultMap.put("resultList", subClasss);
			resultMap.put("msg", "success");
		}catch(Exception e) {
			resultMap.put("msg", "error");
		};
		return resultMap;
	}
}
