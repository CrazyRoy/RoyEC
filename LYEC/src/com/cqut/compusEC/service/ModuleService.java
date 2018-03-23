package com.cqut.compusEC.service;

import java.util.List;
import java.util.Map;

public interface ModuleService {

	public List<Map<String, Object>> getModuleByAccountId(String userId);
	
	public Map<String, Object> getModuleList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> addModuleInfo(String moduleName, String url, String remarks);
	
	public Map<String, Object> deleteModuleInfo(String moduleIds);
	
	public Map<String, Object> editModuleInfo(String moduleId, String moduleName, String url, String remarks);
	
	public Map<String, Object> editModuleState(String moduleIds, String state);
}
