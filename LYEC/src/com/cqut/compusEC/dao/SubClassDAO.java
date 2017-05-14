package com.cqut.compusEC.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SubClassDAO {
	
	public List<Map<String, Object>> querySubClassByMainId(@Param("main_id") Integer main_id) throws Exception;
	
	public List<Map<String, Object>> querySubClassNameById(@Param("sub_id") Integer sub_id) throws Exception;
}
