package com.cqut.compusEC.service;

import java.util.Map;

public interface MessageService {

	public Map<String, Object> getMessageList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> getMessageListByGoodsId(String goodsId);
	
	public Map<String, Object> addMessageInfo(String goodsId, String messageParentId, String messageContent, String messageTime, String buyersId, String sellerId);
	
}
