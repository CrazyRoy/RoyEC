package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbClickAmount;
import com.cqut.compusEC.entity.TbList;
import com.cqut.compusEC.service.ListService;
import com.cqut.compusEC.util.EntityIDFactory;

@Service
public class ListServiceImpl implements ListService{

	@Resource(name="entityDao")
	private EntityDao entityDao;

	@Override
	public Map<String, Object> addListInfo(String parentId, String isLast,
			String listName, String listDes, String typeId, String listLevel) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(isLast==null || isLast.equals("")
			|| (listName==null || listName.equals(""))
			|| (listLevel==null || listLevel.equals(""))){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增目录，传入参数为空");
		}else{
			TbClickAmount clickAmount = new TbClickAmount();
			clickAmount.setAMOUNT_ID(EntityIDFactory.createId());
			clickAmount.setAMOUNT_NUM("0");
			int resultInt =  entityDao.save(clickAmount);
			
			if(resultInt>0){
				TbList list = new TbList();
				list.setLIST_ID(EntityIDFactory.createId());
				list.setIS_LAST(isLast);
				list.setLIST_NAME(listName);
				list.setCLICKCOUNT_ID(clickAmount.getAMOUNT_ID());
				list.setLIST_LEVEL(listLevel);
				if(typeId==null || typeId.equals("")){
					list.setTYPE_ID("");
				}else{
					list.setTYPE_ID(typeId);
				}
				if(listDes==null || listDes.equals("")){
					list.setLIST_DES("");
				}else{
					list.setLIST_DES(listDes);
				}
				if(parentId==null || parentId.equals("")){
					list.setPARENT_ID("-1");
				}
				list.setPARENT_ID(parentId);
				resultInt = entityDao.save(list);
				if(resultInt>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "新增目录成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "新增目录失败");
				}
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增目录失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteListInfo(String listIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(listIds==null || listIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除目录，传入参数为空");
		}else{
			String listCondition = " 1=1 AND LIST_ID IN (" + listIds + ") OR PARENT_ID IN ("+ listIds + ") ";
			List<TbList> list = entityDao.getByCondition(listCondition, TbList.class);
			int deleteResult = entityDao.deleteByCondition(listCondition, TbList.class);
			if(deleteResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除模块失败");		
			}else{
				String clickAmountIds = "";
				for(int i=0; i<list.size(); i++){
					clickAmountIds += "'" + list.get(i).getCLICKCOUNT_ID() + "',";
				}
				clickAmountIds = clickAmountIds.substring(0, clickAmountIds.length()-1);
				String clickAmountCondition = " 1=1 AND AMOUNT_ID IN (" + clickAmountIds + ") ";
				deleteResult = entityDao.deleteByCondition(clickAmountCondition, TbClickAmount.class);
				
				if(deleteResult>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "删除模块成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "删除模块失败");
				}
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getListInfo() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> parentMap = new HashMap<>();//用来存放父级目录
		
		String[] properties = new String[]{
				"tb_list.LIST_ID",
				"tb_list.PARENT_ID",
				"tb_list.IS_LAST",
				"tb_list.LIST_NAME",
				"tb_list.LIST_DES",
				"tb_list.TYPE_ID",
				"tb_list.CLICKCOUNT_ID",
				"tb_list.LIST_LEVEL",
				"tb_clickamount.AMOUNT_NUM",
				"tb_type.TYPE_DES",
				"tb_type.TYPE_NAME"
		};
		String table = " tb_list LEFT JOIN tb_clickamount ON tb_list.CLICKCOUNT_ID = tb_clickamount.AMOUNT_ID LEFT JOIN tb_type ON tb_list.TYPE_ID = tb_type.TYPE_ID ";
		String condition = " 1=1 ORDER BY tb_list.LIST_LEVEL ASC ";
		try {
			List<Map<String, Object>> parentObject = entityDao.searchForeign(properties, table, null, null, condition);
			
			for(int i=0; i<parentObject.size(); i++){
				if((parentObject.get(i).get("IS_LAST").toString()).equals("0")){
					Map<String, Object> listMap = new HashMap<>();
					List<Map<String, Object>> listArray = new ArrayList<>();
					Map<String, Object> parentItem = parentObject.get(i);
					if(parentItem.containsKey("LIST_ID")){
						listMap.put("listId",StringUtils.isEmpty(parentItem.get("LIST_ID").toString())?"":parentItem.get("LIST_ID").toString());	
					}else{
						listMap.put("listId","");
					}
					if(parentItem.containsKey("PARENT_ID")){
						listMap.put("parentId",StringUtils.isEmpty(parentItem.get("PARENT_ID").toString())?"":parentItem.get("PARENT_ID").toString());	
					}else{
						listMap.put("parentId","");
					}
					if(parentItem.containsKey("IS_LAST")){
						listMap.put("isLast",StringUtils.isEmpty(parentItem.get("IS_LAST").toString())?"":parentItem.get("IS_LAST").toString());	
					}else{
						listMap.put("isLast","");
					}
					if(parentItem.containsKey("LIST_NAME")){
						listMap.put("listName",StringUtils.isEmpty(parentItem.get("LIST_NAME").toString())?"":parentItem.get("LIST_NAME").toString());	
					}else{
						listMap.put("listName","");
					}
					if(parentItem.containsKey("LIST_DES")){
						listMap.put("listDes",StringUtils.isEmpty(parentItem.get("LIST_DES").toString())?"":parentItem.get("LIST_DES").toString());	
					}else{
						listMap.put("listDes","");
					}
					if(parentItem.containsKey("TYPE_ID")){
						listMap.put("typeId",StringUtils.isEmpty(parentItem.get("TYPE_ID").toString())?"":parentItem.get("TYPE_ID").toString());	
					}else{
						listMap.put("typeId","");
					}
					if(parentItem.containsKey("TYPE_NAME")){
						listMap.put("typeName",StringUtils.isEmpty(parentItem.get("TYPE_NAME").toString())?"":parentItem.get("TYPE_NAME").toString());	
					}else{
						listMap.put("typeName","");
					}
					if(parentItem.containsKey("TYPE_DES")){
						listMap.put("typeDes",StringUtils.isEmpty(parentItem.get("TYPE_DES").toString())?"":parentItem.get("TYPE_DES").toString());	
					}else{
						listMap.put("typeDes","");
					}
					if(parentItem.containsKey("CLICKCOUNT_ID")){
						listMap.put("clickCountId",StringUtils.isEmpty(parentItem.get("CLICKCOUNT_ID").toString())?"":parentItem.get("CLICKCOUNT_ID").toString());	
					}else{
						listMap.put("clickCountId","");
					}
					if(parentItem.containsKey("AMOUNT_NUM")){
						listMap.put("clickCountValue",StringUtils.isEmpty(parentItem.get("AMOUNT_NUM").toString())?"":parentItem.get("AMOUNT_NUM").toString());	
					}else{
						listMap.put("clickCountValue","");
					}
					if(parentItem.containsKey("LIST_LEVEL")){
						listMap.put("listLevel",StringUtils.isEmpty(parentItem.get("LIST_LEVEL").toString())?"":parentItem.get("LIST_LEVEL").toString());	
					}else{
						listMap.put("listLevel","");
					}
					listMap.put("children", listArray);
					
					if(parentMap.containsKey(parentItem.get("PARENT_ID"))){
						Map<String, Object> currentParent = (Map<String, Object>) parentMap.get(parentItem.get("PARENT_ID"));
						List<Map<String, Object>> currentChildren = (List<Map<String, Object>>) currentParent.get("children");
						currentChildren.add(listMap);
					}
					if((listMap.get("listLevel").toString()).equals("0")){
						resultList.add(listMap);
					}
					parentMap.put(listMap.get("listId").toString(), listMap);
				}else{
					Map<String, Object> listMap = new HashMap<>();
					
					Map<String, Object> parentItem = parentObject.get(i);
					if(parentItem.containsKey("LIST_ID")){
						listMap.put("listId",StringUtils.isEmpty(parentItem.get("LIST_ID").toString())?"":parentItem.get("LIST_ID").toString());	
					}else{
						listMap.put("listId","");
					}
					if(parentItem.containsKey("LIST_ID")){
						listMap.put("parentId",StringUtils.isEmpty(parentItem.get("PARENT_ID").toString())?"":parentItem.get("PARENT_ID").toString());	
					}else{
						listMap.put("parentId","");
					}
					if(parentItem.containsKey("IS_LAST")){
						listMap.put("isLast",StringUtils.isEmpty(parentItem.get("IS_LAST").toString())?"":parentItem.get("IS_LAST").toString());	
					}else{
						listMap.put("isLast","");
					}
					if(parentItem.containsKey("LIST_NAME")){
						listMap.put("listName",StringUtils.isEmpty(parentItem.get("LIST_NAME").toString())?"":parentItem.get("LIST_NAME").toString());	
					}else{
						listMap.put("listName","");
					}
					if(parentItem.containsKey("LIST_DES")){
						listMap.put("listDes",StringUtils.isEmpty(parentItem.get("LIST_DES").toString())?"":parentItem.get("LIST_DES").toString());	
					}else{
						listMap.put("listDes","");
					}
					if(parentItem.containsKey("TYPE_ID")){
						listMap.put("typeId",StringUtils.isEmpty(parentItem.get("TYPE_ID").toString())?"":parentItem.get("TYPE_ID").toString());	
					}else{
						listMap.put("typeId","");
					}
					if(parentItem.containsKey("TYPE_NAME")){
						listMap.put("typeName",StringUtils.isEmpty(parentItem.get("TYPE_NAME").toString())?"":parentItem.get("TYPE_NAME").toString());	
					}else{
						listMap.put("typeName","");
					}
					if(parentItem.containsKey("TYPE_DES")){
						listMap.put("typeDes",StringUtils.isEmpty(parentItem.get("TYPE_DES").toString())?"":parentItem.get("TYPE_DES").toString());	
					}else{
						listMap.put("typeDes","");
					}
					if(parentItem.containsKey("CLICKCOUNT_ID")){
						listMap.put("clickCountId",StringUtils.isEmpty(parentItem.get("CLICKCOUNT_ID").toString())?"":parentItem.get("CLICKCOUNT_ID").toString());	
					}else{
						listMap.put("clickCountId","");
					}
					if(parentItem.containsKey("AMOUNT_NUM")){
						listMap.put("clickCountValue",StringUtils.isEmpty(parentItem.get("AMOUNT_NUM").toString())?"":parentItem.get("AMOUNT_NUM").toString());	
					}else{
						listMap.put("clickCountValue","");
					}
					if(parentItem.containsKey("LIST_LEVEL")){
						listMap.put("listLevel",StringUtils.isEmpty(parentItem.get("LIST_LEVEL").toString())?"":parentItem.get("LIST_LEVEL").toString());	
					}else{
						listMap.put("listLevel","");
					}
					
					if(parentMap.containsKey(parentItem.get("PARENT_ID"))){
						Map<String, Object> currentParent = (Map<String, Object>) parentMap.get(parentItem.get("PARENT_ID"));
						List<Map<String, Object>> currentChildren = (List<Map<String, Object>>) currentParent.get("children");
						currentChildren.add(listMap);
					}
					if((listMap.get("listLevel").toString()).equals("0")){
						resultList.add(listMap);
					}
				}
			}
			
			resultMap.put("resultCode", "0000");
			resultMap.put("resultMessage", "获取目录信息成功");
			resultMap.put("total", parentObject.size());
			resultMap.put("rows", resultList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取目录信息失败");
			resultMap.put("total", "0");
			resultMap.put("rows", resultList);
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editListInfo(String listId, String isLast,
			String listName, String listDes, String typeId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		if(isLast==null || isLast.equals("")
			|| (listName==null || listName.equals(""))
			|| (listId==null || listId.equals(""))){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改目录，传入参数为空");
		}else{
			TbList tbList = new TbList();
			tbList.setLIST_ID(listId);
			tbList.setIS_LAST(isLast);
			tbList.setLIST_NAME(listName);
			if(listDes==null || listDes.equals("")){
				tbList.setLIST_DES("");
			}else{
				tbList.setLIST_DES(listDes);
			}
			if(typeId==null || typeId.equals("")){
				tbList.setTYPE_ID("");
			}else{
				tbList.setTYPE_ID(typeId);
			}
			
			int resultInt = entityDao.updatePropByID(tbList, listId);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改目录成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改目录失败");
			}
		}
			
		return resultMap;
	}

	@Override
	public Map<String, Object> getLastListInfo() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		try {
			String[] properties = new String[]{
					"tb_list.LIST_ID",
					"tb_list.PARENT_ID",
					"tb_list.IS_LAST",
					"tb_list.LIST_NAME",
					"tb_list.LIST_DES",
					"tb_list.TYPE_ID",
					"tb_list.CLICKCOUNT_ID",
					"tb_list.LIST_LEVEL",
					"tb_clickamount.AMOUNT_NUM",
					"tb_type.TYPE_DES",
					"tb_type.TYPE_NAME"
			};
			String table = " tb_list LEFT JOIN tb_clickamount ON tb_list.CLICKCOUNT_ID = tb_clickamount.AMOUNT_ID LEFT JOIN tb_type ON tb_list.TYPE_ID = tb_type.TYPE_ID ";
			String condition = " 1=1 AND tb_list.IS_LAST=1  ORDER BY tb_list.LIST_LEVEL ASC ";
			List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
			
			if(resultList==null || resultList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取末端目录失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取末端目录成功");
				resultMap.put("rows", resultList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取末端目录异常");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getFirstListInfo() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		try {
			String[] properties = new String[]{
					"tb_list.LIST_ID",
					"tb_list.PARENT_ID",
					"tb_list.IS_LAST",
					"tb_list.LIST_NAME",
					"tb_list.LIST_DES",
					"tb_list.TYPE_ID",
					"tb_list.CLICKCOUNT_ID",
					"tb_list.LIST_LEVEL",
					"tb_clickamount.AMOUNT_NUM",
					"tb_type.TYPE_DES",
					"tb_type.TYPE_NAME"
			};
			String table = " tb_list LEFT JOIN tb_clickamount ON tb_list.CLICKCOUNT_ID = tb_clickamount.AMOUNT_ID LEFT JOIN tb_type ON tb_list.TYPE_ID = tb_type.TYPE_ID ";
			String condition = " 1=1 AND tb_list.LIST_LEVEL=0  ORDER BY tb_list.LIST_LEVEL ASC ";
			List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
			
			if(resultList==null || resultList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取首级目录失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取首级目录成功");
				resultMap.put("rows", resultList);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取首级目录异常");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getChildrenListInfoByListId(String listId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(listId==null || listId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取指定目录的子级目录，传入参数为空");
		}else{
			try {
				String[] properties = new String[]{
						"tb_list.LIST_ID",
						"tb_list.PARENT_ID",
						"tb_list.IS_LAST",
						"tb_list.LIST_NAME",
						"tb_list.LIST_DES",
						"tb_list.TYPE_ID",
						"tb_list.CLICKCOUNT_ID",
						"tb_list.LIST_LEVEL",
						"tb_clickamount.AMOUNT_NUM",
						"tb_type.TYPE_DES",
						"tb_type.TYPE_NAME"
				};
				String table = " tb_list LEFT JOIN tb_clickamount ON tb_list.CLICKCOUNT_ID = tb_clickamount.AMOUNT_ID LEFT JOIN tb_type ON tb_list.TYPE_ID = tb_type.TYPE_ID ";
				String condition = " 1=1 AND tb_list.PARENT_ID='" + listId + "'  ORDER BY tb_list.LIST_LEVEL ASC ";
				List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
				
				if(resultList==null || resultList.size()<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "获取指定目录的子级目录失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "获取指定目录的子级目录成功");
					resultMap.put("rows", resultList);
				}			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取指定目录的子级目录异常");
			}
		}
		return resultMap;
	}
}
