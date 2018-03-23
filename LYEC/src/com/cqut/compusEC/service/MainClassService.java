package com.cqut.compusEC.service;

import java.util.List;
import java.util.Map;

public interface MainClassService {
	
	public List<Map<String, Object>> queryAllMainClass() throws Exception;
	
	public List<Map<String, Object>> queryMainClassNameById(Integer main_id) throws Exception;
}
