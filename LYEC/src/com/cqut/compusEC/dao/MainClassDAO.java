package com.cqut.compusEC.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MainClassDAO {
	
	public List<Map<String, Object>> queryAllMainClass() throws Exception;
	
	public List<Map<String, Object>> queryMainClassNameById(@Param("main_id") Integer main_id) throws Exception;
	
}
