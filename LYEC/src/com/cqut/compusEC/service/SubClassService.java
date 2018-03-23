package com.cqut.compusEC.service;

import java.util.List;
import java.util.Map;

public interface SubClassService {
	
	public List<Map<String, Object>> querySubClassByMainId(Integer main_id) throws Exception;
	
	public List<Map<String, Object>> querySubClassNameById(Integer sub_id) throws Exception;
}
