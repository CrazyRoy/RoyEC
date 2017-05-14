package com.cqut.compusEC.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProductDAO {
	
	// 最新闲置
	public List<Map<String, Object>> queryNewsProduct() throws Exception;
	
	// 数码控
	public List<Map<String, Object>> queryDigitalsProduct() throws Exception;
	
	// 潮流生活
	public List<Map<String, Object>> queryfashionLifesProduct() throws Exception;
	
	// 热门榜
	public List<Map<String, Object>> queryHotsProduct() throws Exception;
	
	// 根据主类别查询商品w
	public List<Map<String, Object>> queryProductByMainId(@Param("mainId") Integer mainId) throws Exception;
	
	// 根据子类别查询商品
	public List<Map<String, Object>> queryProductBySubId(@Param("subId") Integer subId) throws Exception;
	
}
