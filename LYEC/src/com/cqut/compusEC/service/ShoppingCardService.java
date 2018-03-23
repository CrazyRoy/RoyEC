package com.cqut.compusEC.service;

import java.util.Map;

public interface ShoppingCardService {

	public Map<String, Object> addGoodsToCardInfo(String goodsId, String userId, String goodsCardDes);
	
	public Map<String, Object> deleteGoodsFromCardInfo(String goodsIds, String userId);
	
	public Map<String, Object> getGoodsFromCardInfo(String userId, String type, String page);
	
	public Map<String, Object> getGoodsTotalFromCardInfo(String userId, String type);
}
