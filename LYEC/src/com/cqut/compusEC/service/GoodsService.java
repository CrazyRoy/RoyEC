package com.cqut.compusEC.service;

import java.util.Map;

public interface GoodsService {

	public Map<String, Object> insertGoodsInfo(String userId, String goodsTitle,String goodsDes, String goodsOrignal, String goodsResale, String goodsCondition, String listId, String fileNames, String filePaths);
	
	public Map<String, Object> insertGoodsByGoodsId(String goodsId, String userId);
	
	public Map<String, Object> getGoodsList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> getGoodsListByGoodsId(String goodsId);
	
	public Map<String, Object> getGoodsListBySearch(String seachContent, String page, String number);
	
	public Map<String, Object> editGoodsInfo(String goodsId, String goodsTitle,String goodsDes, String goodsOrignal, String goodsResale, String goodsCondition, String listId, String goodsRecentAccess, String goodsViewConts, String goodsDingNum, String goodsCaiNum, String goodsStatus, String fileNames, String filePaths);
	
	public Map<String, Object> deleteGoods(String goodsIds);
	
	public Map<String, Object> getGoodsListByCreatetime(String index);
	
	public Map<String, Object> getGoodsListBySchoole(String userId, String index);
	
	public Map<String, Object> getGoodsListByGuess();
	
	public Map<String, Object> getGoodsListByListType(String typeId, String page, String number);
	
	public Map<String, Object> getGoodsTotalByListType(String typeId, String number);
	
	public Map<String, Object> getGoodsListByListViewConts();
	
	public Map<String, Object> getGoodsListByListId(String listId, String page);
	
	public Map<String, Object> getGoodsTotalByListId(String listId);
	
	public Map<String, Object> getGoodsListByUserId(String userId, String page, String type);
	
	public Map<String, Object> getGoodsTotalByUserId(String userId, String type);
}
