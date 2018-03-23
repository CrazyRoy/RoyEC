package com.cqut.compusEC.service;

import java.util.List;
import java.util.Map;

public interface RoleService {

	public Map<String, Object> getRoleList(int limit, int offset, String order, String sort);
	
	public Map<String, Object> addRoleInfo(String roleName, String remarks);
	
	public Map<String, Object> editRoleInfo(String roleId, String roleName, String remarks);
	
	public Map<String, Object> delRole(String roleIds);
	
	public Map<String, Object> getRoleModuleListInfo(String roleId);
	
	public Map<String, Object> saveRoleModuleInfo(String roleId, String moduleIds);
}
