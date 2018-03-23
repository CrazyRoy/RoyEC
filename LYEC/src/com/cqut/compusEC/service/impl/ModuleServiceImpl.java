package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbModule;
import com.cqut.compusEC.entity.TbPermission;
import com.cqut.compusEC.service.ModuleService;
import com.cqut.compusEC.util.DateUtil;
import com.cqut.compusEC.util.EntityIDFactory;

/**
 * 模块Service
 * @author LV
 *
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService{

	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	/**
	 * 模块管理-获取导航菜单
	 */
	@Override
	public List<Map<String, Object>> getModuleByAccountId(String userId) {
		// TODO Auto-generated method stub
		/**
		 * 根据accountId查询用户对应的角色
		 * if(如果角色为空)
		 * 	提示用户未分配角色
		 * else
		 * 	组装角色Id，根据角色Id查询出角色拥有的模块
		 * 组装模块数据，并返回组装数据。
		 */
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> roleList = new ArrayList<Map<String,Object>>();
		String[] roleProperties = new String[]{
				"ROLEID"
		};
		String roleTable = " tb_roleuser ";
		String roleCondition = " USERID = '" + userId + "' ";
		roleList = entityDao.searchForeign(roleProperties, roleTable, null, null, roleCondition);
		
		if(roleList==null || roleList.size()<=0){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "该用户未分配角色");
			
			resultList.add(resultMap);
		}else{
			String moduleCondition = "(";
			for(int i=0; i<roleList.size(); i++){
				moduleCondition += "'" + roleList.get(i).get("ROLEID").toString() + "',";
			}
			moduleCondition = moduleCondition.substring(0, moduleCondition.length()-1) + ")";
			String[] moduleProperties = new String[]{
					"module.MODULEID",
					"module.MODULENAME",
					"module.URL",
					"module.STATE",
					"module.CREATETIME",
					"module.REMARKS"
			};
			String moduleTable = "tb_module module,tb_permission permission";
			moduleCondition = " module.MODULEID=permission.MODULEID AND module.STATE=0 AND ROLEID IN " + moduleCondition;
			List<Map<String, Object>> moduleList = entityDao.searchForeign(moduleProperties, moduleTable, null, null, moduleCondition);
			
			if(moduleList==null || moduleList.size()<=0){
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "该用户对应的角色未分配模块权限");
				
				resultList.add(resultMap);
			}else{
				resultList = moduleList;
			}
		}
		
		return resultList;
	}

	/**
	 * 模块管理-分页查询
	 */
	@Override
	public Map<String, Object> getModuleList(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		/**
		 * 
		 */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int index = limit;  // 每页数据量
			int pageNum = offset/limit + 1;  // 当前页码
			
			String[] moduleProperties = new String[]{
					"module.MODULEID",
					"module.MODULENAME",
					"module.URL",
					"module.STATE",
					"module.CREATETIME",
					"module.REMARKS"
			};
			String moduleTable = "tb_module module";
			List<Map<String, Object>> moduleList = entityDao.searchWithpaging(moduleProperties, moduleTable, null, null, " 1=1 ", null, order, sort, index, pageNum);
			int total = entityDao.getCountByCondition(" 1=1 ", TbModule.class);

			if(moduleList==null || moduleList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取模块信息失败");
				resultMap.put("total", 0);
				resultMap.put("rows", new ArrayList<>());
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取模块信息成功");
				resultMap.put("total", total);
				resultMap.put("rows", moduleList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取模块信息异常");
			resultMap.put("total", 0);
			resultMap.put("rows", new ArrayList<>());
		}
		
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addModuleInfo(String moduleName, String url,
			String remarks) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		TbModule module = new TbModule();
		if(moduleName==null || moduleName.equals("") || url==null || url.equals("")){
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增模块，参数为空");
		}else{
			module.setMODULENAME(moduleName);
			module.setURL(url);
			if(remarks==null || remarks.equals("")){
				module.setREMARKS("");
			}else{
				module.setREMARKS(remarks);
			}
			module.setCREATETIME(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			module.setSTATE("0");
			module.setMODULEID(EntityIDFactory.createId());
			
			int insertResult = entityDao.save(module);
			if(insertResult<=0){
				result.put("resultCode", "9999");
				result.put("resultMessage", "新增模块失败");
			}else{
				result.put("resultCode", "0000");
				result.put("resultMessage", "新增模块成功");
			}
		}
		
		
		return result;
	}

	@Override
	public Map<String, Object> deleteModuleInfo(String moduleIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(moduleIds==null || moduleIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除模块，传入参数为空");
		}else{
			String moduleCondition = " 1=1 AND MODULEID IN (" + moduleIds + ") ";
			int deleteResult = entityDao.deleteByCondition(moduleCondition, TbModule.class);
			
			if(deleteResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除模块失败");		
			}else{
				deleteResult = entityDao.deleteByCondition(moduleCondition, TbPermission.class);
				
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除模块成功");		
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editModuleInfo(String moduleId,
			String moduleName, String url, String remarks) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(moduleId==null || moduleId.equals("") || moduleName==null || moduleName.equals("") || url==null || url.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改模块，传入参数为空");
		}else{
			TbModule module = new TbModule();
//			module.setMODULEID(moduleId);
			module.setMODULENAME(moduleName);
			module.setURL(url);
			
			if(remarks==null || remarks.equals("")){
				module.setREMARKS("");
			}else{
				module.setREMARKS(remarks);
			}
			
			int updateResult = entityDao.updatePropByID(module, moduleId);
			
			if(updateResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改模块失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改模块成功");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editModuleState(String moduleIds, String state) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(moduleIds==null || moduleIds.equals("") || state==null || state.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改模块状态，传入参数为空");
		}else{
			TbModule module = new TbModule();
			module.setSTATE(state);
			String moduleCondition = " 1=1 AND MODULEID IN (" + moduleIds + ") ";
			try {
				int updateResult = entityDao.updatePropByCondition(module, moduleCondition);				
				if(updateResult<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "修改模块状态失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "修改模块状态成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改模块状态异常");
			}
		}
		
		return resultMap;
	}

	/*// 组装模块数据
	private List<Map<String, Object>> conversionModuleData(List<Map<String, Object>> moduleData){
		Map<String, Object> parentMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		for(int i=0; i<moduleData.size(); i++){
			Map<String, Object> tempMap = moduleData.get(i);
			if(tempMap.containsKey("LEVEL")){
				String moduleLevel = tempMap.get("LEVEL").toString();
				if(moduleLevel.equals("0")){
					parentMap.put(tempMap.get("MODULEID").toString(), tempMap);
					if(parentMap.containsKey(tempMap.get("PARENTID").toString())){
						Map<String, Object> parent = (Map<String, Object>) parentMap.get(tempMap.get("PARENTID").toString());
						if(parent.containsKey("children")){
							List<Map<String, Object>> children = (List<Map<String, Object>>) parent.get("children");
							children.add(tempMap);
						}else{
							List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
							children.add(tempMap);
							parent.put("children", children);
						}
					}else{
					}
					resultList.add(tempMap);
				}else{
					if(parentMap.containsKey(tempMap.get("PARENTID").toString())){
						Map<String, Object> parent = (Map<String, Object>) parentMap.get(tempMap.get("PARENTID").toString());
						if(parent.containsKey("children")){
							List<Map<String, Object>> children = (List<Map<String, Object>>) parent.get("children");
							children.add(tempMap);
						}else{
							List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
							children.add(tempMap);
							parent.put("children", children);
						}
					}else{
						resultList.add(tempMap);
					}
				}
			}else{
				Map<String, Object> resultMap = new HashMap<String, Object>();
				
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "模块数据异常");
				
				resultList.add(resultMap);
				break;
			}
		}
		
		return resultList;
	}*/
}
