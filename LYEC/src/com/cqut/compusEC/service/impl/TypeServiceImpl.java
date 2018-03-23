package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbType;
import com.cqut.compusEC.service.TypeService;
import com.cqut.compusEC.util.EntityIDFactory;

@Service
public class TypeServiceImpl implements TypeService{
	@Resource(name="entityDao")
	private EntityDao entityDao;

	@Override
	public Map<String, Object> getTypeList(int limit, int offset, String order,
			String sort) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int index = limit;  // 每页数据量
			int pageNum = offset/limit + 1;  // 当前页码
			
			String[] typeProperties = new String[]{
					"tb_type.TYPE_ID",
					"tb_type.TYPE_DES",
					"tb_type.TYPE_NAME"
			};
			String typeTable = "tb_type";
			List<Map<String, Object>> typeList = entityDao.searchWithpaging(typeProperties, typeTable, null, null, " 1=1 ", null, order, sort, index, pageNum);
			int total = entityDao.getCountByCondition(" 1=1 ", TbType.class);
			
			if(typeList.size()>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取目录类型成功");
				resultMap.put("total", total);
				resultMap.put("rows", typeList);
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取目录类型失败");
				resultMap.put("total", 0);
				resultMap.put("rows", new ArrayList<>());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("rows", new ArrayList<>());
			resultMap.put("resultCode", "0000");
			resultMap.put("resultMessage", "获取目录类型异常");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addTypeInfo(String typeDes, String typeName) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		if(typeDes==null || typeDes.equals("") || typeName==null || typeName.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增目录类型，传入参数为空");
		}else{
			TbType tbType = new TbType();
			tbType.setTYPE_ID(EntityIDFactory.createId());
			tbType.setTYPE_DES(typeDes);
			tbType.setTYPE_NAME(typeName);
			
			int resultInt = entityDao.save(tbType);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "新增目录类型成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增目录类型失败");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteTypeInfo(String typeIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(typeIds==null || typeIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除目录类型，传入参数为空");
		}else{
			String typeCondition = " 1=1 AND TYPE_ID IN (" + typeIds + ") ";
			int deleteResult = entityDao.deleteByCondition(typeCondition, TbType.class);
			
			if(deleteResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除目录类型失败");		
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除目录类型成功");		
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editTypeInfo(String typeId, String typeDes, String typeName) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(typeId==null || typeId.equals("") 
			|| typeDes==null || typeDes.equals("")
			|| typeName==null || typeName.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改目录类型，传入参数为空");
		}else{
			TbType type = new TbType();
			type.setTYPE_DES(typeDes);;
			type.setTYPE_NAME(typeName);
			
			int updateResult = entityDao.updatePropByID(type, typeId);
			
			if(updateResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改目录类型失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改目录类型成功");
			}
		}
		
		return resultMap;
	}
}
