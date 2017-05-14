package com.cqut.compusEC.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.MainClassDAO;
import com.cqut.compusEC.service.MainClassService;

@Service
public class MainClassServiceImpl implements MainClassService {
	
	@Resource
	private MainClassDAO mainClassDao;

	@Override
	public List<Map<String, Object>> queryAllMainClass() throws Exception {
		return mainClassDao.queryAllMainClass();
	}

	@Override
	public List<Map<String, Object>> queryMainClassNameById(Integer main_id)
			throws Exception {
		return mainClassDao.queryMainClassNameById(main_id);
	}
}
