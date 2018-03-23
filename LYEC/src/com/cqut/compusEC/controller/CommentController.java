package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.CommentService;

@Controller
@RequestMapping("/commentController")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@RequestMapping("/getCommentList")  
    @ResponseBody
	public JSONObject getCommentList(int limit, int offset, String order, String sort){
		Map<String, Object> result = commentService.getCommentList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getCommentInfoByOrderId") 
    @ResponseBody
	public JSONObject getCommentInfoByOrderId(String orderId){
		Map<String, Object> result = commentService.getCommentInfoByOrderId(orderId);
		return JSONObject.fromObject(result);
	}

	@RequestMapping("/addCommentInfo")  
    @ResponseBody
	public JSONObject addCommentInfo(String userId, String orderId, String commentReplyId, String commentContent){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = commentService.addCommentInfo(userId, orderId, commentReplyId, commentContent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增订单评论异常");
		}
		
		return JSONObject.fromObject(result);
	}
}
