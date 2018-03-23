package com.cqut.compusEC.service;

import java.util.Map;

public interface TypeService {

	public Map<String, Object> getTypeList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> addTypeInfo(String typeDes, String typeName);
	
	public Map<String, Object> deleteTypeInfo(String typeIds);
	
	public Map<String, Object> editTypeInfo(String typeId, String typeDes, String typeName);
}
