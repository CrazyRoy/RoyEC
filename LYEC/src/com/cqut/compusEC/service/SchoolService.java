package com.cqut.compusEC.service;

import java.util.Map;

public interface SchoolService {
	
    public Map<String, Object> getSchoolList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> addSchoolInfo(String schoolName,String schoolAddress,String schoolPostcode);
	
	public Map<String, Object> deleteSchoolInfo(String schoolIds);
	
	public Map<String, Object> editSchoolInfo(String schoolId, String schoolName,String schoolAddress,String schoolPostcode);
	
}
