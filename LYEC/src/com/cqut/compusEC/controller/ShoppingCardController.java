package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.ShoppingCardService;

@Controller
@RequestMapping("/shoppingCardController")
public class ShoppingCardController {

	@Resource
	private ShoppingCardService cardService;
	
	@RequestMapping("/addGoodsToCardInfo")  
    @ResponseBody
	public JSONObject addGoodsToCardInfo(String goodsId, String userId, String goodsCardDes){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = cardService.addGoodsToCardInfo(goodsId, userId, goodsCardDes);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "添加商品到购物车异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteGoodsFromCardInfo")  
    @ResponseBody
	public JSONObject deleteGoodsFromCardInfo(String goodsIds, String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = cardService.deleteGoodsFromCardInfo(goodsIds, userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除购物车中指定商品信息异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getGoodsFromCardInfo")  
    @ResponseBody
	public JSONObject getGoodsFromCardInfo(String userId, String type, String page){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = cardService.getGoodsFromCardInfo(userId, type, page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "获取购物车中的商品信息车异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getGoodsTotalFromCardInfo")  
    @ResponseBody
	public JSONObject getGoodsTotalFromCardInfo(String userId, String type){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = cardService.getGoodsTotalFromCardInfo(userId, type);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "获取购物车中的商品信息车异常");
		}
		return JSONObject.fromObject(result);
	}
}
