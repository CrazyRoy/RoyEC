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
@RequestMapping("/home")
public class HomeController {
	
	@Resource
	private MainClassService mainClassService;
	@Resource
	private SubClassService subClassService;
	@Resource
	private ProductService productService;
	
	// 查询主类别方法
	@ResponseBody
	@RequestMapping("/queryAllMainClass")
	public Object queryAllMainClass() throws Exception {
		List<Map<String, Object>> mainClassList = mainClassService.queryAllMainClass();
		return mainClassList;
	}
	
	// 根据主类别id查询子类别方法
	@ResponseBody
	@RequestMapping("/querySubClassByMainId")
	public Object querySubClassByMainId(Integer main_id) throws Exception {
		List<Map<String, Object>> subClassList = subClassService.querySubClassByMainId(main_id);
		return subClassList;
	}
	
	// 根据子类别id查询商品方法
	@ResponseBody
	@RequestMapping("/queryProductBySubId")
	public Object queryProductBySubId(String subId) throws Exception {
		try {
			List<Map<String, Object>> products = productService.queryProductBySubId(Integer.parseInt(subId));
			System.out.println(products.toString());
			return products;
		}catch(Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msg", "error");
			return map;
		}
	}
	
	// 查询最新闲置商品
	@ResponseBody
	@RequestMapping("/queryNewsProduct")
	public Object queryNewsProduct() throws Exception {
		List<Map<String, Object>> products = productService.queryNewsProduct();
		return products;
	}
	
	// 猜你喜欢
	
	// 同校交易
	
	// 数码控
	@ResponseBody
	@RequestMapping("/queryDigitalsProduct")
	public Object queryDigitalsProduct() throws Exception {
		List<Map<String, Object>> products = productService.queryDigitalsProduct();
		return products;
	}
	
	// 潮流生活
	@ResponseBody
	@RequestMapping("/queryfashionLifesProduct")
	public Object queryfashionLifesProduct() throws Exception {
		List<Map<String, Object>> products = productService.queryfashionLifesProduct();
		return products;
	}
	
	// 二手图书
	
	// 热门榜
	@ResponseBody
	@RequestMapping("queryHotsProduct")
	public Object queryHotsProduct() throws Exception {
		List<Map<String, Object>> products = productService.queryHotsProduct();
		return products;
	}
}
