package com.cqut.compusEC.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.ProductDAO;
import com.cqut.compusEC.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductDAO productDao;
	
	@Override
	public List<Map<String, Object>> queryNewsProduct() throws Exception {
		return productDao.queryNewsProduct();
	}

	@Override
	public List<Map<String, Object>> queryDigitalsProduct() throws Exception {
		return productDao.queryDigitalsProduct();
	}

	@Override
	public List<Map<String, Object>> queryfashionLifesProduct() throws Exception {
		return productDao.queryfashionLifesProduct();
	}

	@Override
	public List<Map<String, Object>> queryHotsProduct() throws Exception {
		return productDao.queryHotsProduct();
	}

	@Override
	public List<Map<String, Object>> queryProductByMainId(Integer mainId)
			throws Exception {
		return productDao.queryProductByMainId(mainId);
	}

	@Override
	public List<Map<String, Object>> queryProductBySubId(Integer subId)
			throws Exception {
		return productDao.queryProductBySubId(subId);
	}
}
