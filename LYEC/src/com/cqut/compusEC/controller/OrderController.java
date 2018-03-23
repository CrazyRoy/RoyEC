package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;




import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

import com.cqut.compusEC.service.OrderService;

@Controller
@RequestMapping("/orderController")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/getOrderList")  
    @ResponseBody
	public JSONObject getOrderList(int limit, int offset, String order, String sort){
		Map<String, Object> result = orderService.getOrderList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getOrderInfoByOrderId")  
    @ResponseBody
	public JSONObject getOrderInfoByOrderId(String orderId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = orderService.getOrderInfoByOrderId(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "通过订单ID获取订单信息异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/updateOrderStatu")  
    @ResponseBody
	public JSONObject updateOrderStatu(String orderId){
		Map<String, Object> result = new HashMap<>();
		try {
			result = orderService.updateOrderStatu(orderId);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改订单状态异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/insertOrderInfoByCard")  
    @ResponseBody
	public JSONObject insertOrderInfoByCard(String userId, String address){
		Map<String, Object> result = new HashMap<>();
		try {
			result = orderService.insertOrderInfoByCard(userId, address);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "根据购物车中的商品信息生成订单异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/insertOrderInfoByGoodsId")  
    @ResponseBody
	public JSONObject insertOrderInfoByGoodsId(String userId, String goodsId, String address){
		Map<String, Object> result = new HashMap<>();
		try {
			result = orderService.insertOrderInfoByGoodsId(userId, goodsId, address);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "根据商品信息生成订单异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteOrderInfoByOrderId")  
    @ResponseBody
	public JSONObject deleteOrderInfoByOrderId(String userId, String orderIds){
		Map<String, Object> result = new HashMap<>();
		try {
			result = orderService.deleteOrderInfoByOrderId(userId, orderIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "根据订单ID删除订单信息异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getOrderListByUserId")  
    @ResponseBody
	public JSONObject getOrderListByUserId(String userId, String page, String type){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = orderService.getOrderListByUserId(userId, page, type);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "获取用户订单列表异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getOrderListTotalByUserId")  
    @ResponseBody
	public JSONObject getOrderListTotalByUserId(String userId, String type){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = orderService.getOrderListTotalByUserId(userId, type);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "获取用户订单列表总数异常");
		}
		
		return JSONObject.fromObject(result);
	}
}
