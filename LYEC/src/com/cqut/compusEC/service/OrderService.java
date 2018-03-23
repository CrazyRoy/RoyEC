package com.cqut.compusEC.service;

import java.util.Map;

public interface OrderService {
	
	public Map<String, Object> getOrderList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> getOrderInfoByOrderId(String orderId);
	
	public Map<String, Object> updateOrderStatu(String orderId);
	
	public Map<String, Object> insertOrderInfoByCard(String userId, String address);
	
	public Map<String, Object> insertOrderInfoByGoodsId(String userId, String goodsId, String address);
	
	public Map<String, Object> deleteOrderInfoByOrderId(String userId, String orderIds);
	
	public Map<String, Object> getOrderListByUserId(String userId, String page, String type);
	
	public Map<String, Object> getOrderListTotalByUserId(String userId, String type);
	
}
