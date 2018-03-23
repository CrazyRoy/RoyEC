package com.cqut.compusEC.service;

import java.util.Map;

public interface CommentService {

	public Map<String, Object> getCommentList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> addCommentInfo(String userId, String orderId, String commentReplyId, String commentContent);
	
	public Map<String, Object> getCommentInfoByOrderId(String orderId);
	
}
