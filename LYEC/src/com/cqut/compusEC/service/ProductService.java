package com.cqut.compusEC.service;

import java.util.List;
import java.util.Map;

public interface ProductService {
	
	public List<Map<String, Object>> queryNewsProduct() throws Exception;
	
	public List<Map<String, Object>> queryDigitalsProduct() throws Exception;

	public List<Map<String, Object>> queryfashionLifesProduct() throws Exception;
	
	public List<Map<String, Object>> queryHotsProduct() throws Exception;

	public List<Map<String, Object>> queryProductByMainId(Integer mainId) throws Exception;
	
	public List<Map<String, Object>> queryProductBySubId(Integer subId) throws Exception;

}
