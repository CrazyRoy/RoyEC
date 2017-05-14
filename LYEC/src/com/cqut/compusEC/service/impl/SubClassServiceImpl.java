package com.cqut.compusEC.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.SubClassDAO;
import com.cqut.compusEC.service.SubClassService;

@Service
public class SubClassServiceImpl implements SubClassService {

	@Resource
	private SubClassDAO subClassDao;

	@Override
	public List<Map<String, Object>> querySubClassByMainId(Integer mainID) throws Exception {
		return subClassDao.querySubClassByMainId(mainID);
	}

	@Override
	public List<Map<String, Object>> querySubClassNameById(Integer sub_id)
			throws Exception {
		return subClassDao.querySubClassNameById(sub_id);
	}
}
