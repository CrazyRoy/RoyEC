package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.MessageService;

@Controller
@RequestMapping("/messageController")
public class MessageController {
	
	@Resource
	private MessageService messageService;
	
	@RequestMapping("/getMessageList")  
    @ResponseBody
	public JSONObject getMessageList(int limit, int offset, String order, String sort){
		Map<String, Object> result = messageService.getMessageList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getMessageListByGoodsId")  
    @ResponseBody
	public JSONObject getMessageListByGoodsId(String goodsId){
		Map<String, Object> result = messageService.getMessageListByGoodsId(goodsId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/addMessageInfo")  
    @ResponseBody
	public JSONObject addMessageInfo(String goodsId, String messageParentId, String messageContent, String messageTime, String buyersId, String sellerId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = messageService.addMessageInfo(goodsId, messageParentId, messageContent, messageTime, buyersId, sellerId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增商品留言异常");
		}
		return JSONObject.fromObject(result);
	}
}
