package com.cqut.compusEC.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbPermission;
import com.cqut.compusEC.entity.TbRole;
import com.cqut.compusEC.entity.TbRoleUser;
import com.cqut.compusEC.service.RoleService;
import com.cqut.compusEC.util.DateUtil;
import com.cqut.compusEC.util.EntityIDFactory;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	@Override
	public Map<String, Object> getRoleList(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		int index = limit;  // 每页数据量
		int pageNum = offset/limit + 1;  // 当前页码
		
		String[] roleProperties = new String[]{
				"role.ROLEID",
				"role.ROLENAME",
				"role.CREATETIME",
				"role.REMARKS"
		};
		String roleTable = " tb_role role ";
		List<Map<String, Object>> roleList = entityDao.searchWithpaging(roleProperties, roleTable, null, null, " 1=1 ", null, order, sort, index, pageNum);
		int total = entityDao.getCountByCondition(" 1=1 ", TbRole.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", roleList);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addRoleInfo(String roleName, String remarks) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(roleName==null || roleName.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增角色，传入参数为空");
		}else{
			TbRole role = new TbRole();
			role.setROLEID(EntityIDFactory.createId());
			role.setROLENAME(roleName);
			if(remarks==null || remarks.equals("")){
				role.setREMARKS("");
			}else{
				role.setREMARKS(remarks);
			}
			role.setCREATETIME(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			
			try {
				int resultInt = entityDao.save(role);
				
				if(resultInt>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "新增角色成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "新增角色失败");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增角色异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editRoleInfo(String roleId, String roleName,
			String remarks) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(roleId==null || roleId.equals("") || roleName==null || roleName.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改角色，传入参数为空");
		}else{
			TbRole role = new TbRole();
			role.setROLENAME(roleName);
			role.setREMARKS(remarks);
			
			try {
				int resultInt = entityDao.updatePropByID(role, roleId);
				
				if(resultInt>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "修改角色成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "修改角色失败");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改角色异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> delRole(String roleIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(roleIds==null || roleIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除角色，传入参数为空");
		}else{
			String delCondition = " 1=1 AND ROLEID IN (" + roleIds + ") ";
			int result = entityDao.deleteByCondition(delCondition, TbRole.class); //调用DAO的方法删除多个帐号信息
			
			if(result<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除角色失败");
			}else{
				result = entityDao.deleteByCondition(delCondition, TbPermission.class);
				result = entityDao.deleteByCondition(delCondition, TbRoleUser.class);
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除角色成功");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getRoleModuleListInfo(String roleId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(roleId==null || roleId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "角色关联模块查询，传入参数为空");
		}else{
			String accountRoleCondition = " ROLEID='" + roleId + "' ";
			String accountRoleTable = "tb_permission permission";
			String[] properties = new String[]{"MODULEID"};
			
			try {
				List<Map<String, Object>> resultList = entityDao.searchForeign(properties, accountRoleTable, null, null, accountRoleCondition);
				
				if(resultList==null || resultList.size()<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "角色没有关联模块");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "角色关联模块查询成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "角色关联模块查询异常");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> saveRoleModuleInfo(String roleId,
			String moduleIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(roleId==null || roleId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "角色关联模块保存，传入参数为空");
		}else{
			if(moduleIds==null || moduleIds.equals("")){
				try {
					String delCondition = " ROLEID='" + roleId + "' ";
					int resultInt = entityDao.deleteByCondition(delCondition, TbPermission.class);
					
					if(resultInt>0){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "角色关联模块保存，成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "角色关联模块保存，失败");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "角色关联模块保存，异常");
				}
			}else{
				try {
					String[] moduleArray = moduleIds.split(",");
					String tableName = "tb_permission";
					String properties = " PERMISSIONID, MODULEID, ROLEID ";
					String entityValues = " ";
					for(int i=0; i<moduleArray.length; i++){
						TbPermission permission = new TbPermission();
						permission.setPERMISSIONID(EntityIDFactory.createId());
						permission.setMODULEID(moduleArray[i]);
						permission.setROLEID(roleId);
						
						entityValues += " ('" 
								+ permission.getPERMISSIONID() + "','"
								+ permission.getMODULEID() + "','"
								+ permission.getROLEID() + "'),";
					}
					
					entityValues = entityValues.substring(0, entityValues.length()-1);
					
					String delCondition = " ROLEID='" + roleId + "' ";
					entityDao.deleteByCondition(delCondition, TbPermission.class);
					
					int resultInt = entityDao.saveEntities(tableName, properties, entityValues);
					if(resultInt>0){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "角色关联模块保存，成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "角色关联模块保存，失败");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "角色关联模块保存，异常");
				}
			}
		}
		
		return resultMap;
	}
}
