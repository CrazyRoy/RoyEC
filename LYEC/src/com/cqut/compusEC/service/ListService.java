package com.cqut.compusEC.service;

import java.util.Map;


public interface ListService {

	public Map<String, Object> addListInfo(String parentId, String isLast, String listName, String listDes, String typeId, String listLevel);
	
	public Map<String, Object> deleteListInfo(String listIds);
	
	public Map<String, Object> getListInfo();
	
	public Map<String, Object> getLastListInfo();
	
	public Map<String, Object> getFirstListInfo();
	
	public Map<String, Object> getChildrenListInfoByListId(String listId);
	
	public Map<String, Object> editListInfo(String listId, String isLast, String listName, String listDes, String typeId);
}
