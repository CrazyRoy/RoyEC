package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbSchool;
import com.cqut.compusEC.service.SchoolService;
import com.cqut.compusEC.util.EntityIDFactory;

@Service
public class SchoolServiceImpl implements SchoolService{
	@Resource(name="entityDao")
	private EntityDao entityDao;

	@Override
	public Map<String, Object> getSchoolList(int limit, int offset, String order,String sort) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int index = limit;  // 每页数据量
			int pageNum = offset/limit + 1;  // 当前页码
			
			String[] schoolProperties = new String[]{
					"tb_school.SCHOOL_ID",
					"tb_school.SCHOOL_NAME",
					"tb_school.SCHOOL_ADDRESS",
					"tb_school.SCHOOL_POSTCODE"
			};
			String schoolTable = "tb_school";
			List<Map<String, Object>> schoolList = entityDao.searchWithpaging(schoolProperties, schoolTable, null, null, " 1=1 ", null, order, sort, index, pageNum);
			int total = entityDao.getCountByCondition(" 1=1 ", TbSchool.class);
			
			if(schoolList.size()>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取学校信息成功");
				resultMap.put("total", total);
				resultMap.put("rows", schoolList);
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取学校信息失败");
				resultMap.put("total", 0);
				resultMap.put("rows", new ArrayList<>());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("rows", new ArrayList<>());
			resultMap.put("resultCode", "0000");
			resultMap.put("resultMessage", "获取学校信息异常");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addSchoolInfo(String schoolName,String schoolAddress,String schoolPostcode) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		if(schoolName==null || schoolName.equals("") || schoolAddress==null || schoolAddress.equals("")|| schoolPostcode==null || schoolPostcode.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增学校信息，传入参数为空");
		}else{
			TbSchool tbSchool = new TbSchool();
			tbSchool.setSCHOOL_ID(EntityIDFactory.createId());
			tbSchool.setSCHOOL_NAME(schoolName);
			tbSchool.setSCHOOL_ADDRESS(schoolAddress);
			tbSchool.setSCHOOL_POSTCODE(schoolPostcode);
			
			int resultInt = entityDao.save(tbSchool);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "新增学校信息成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增学校信息失败");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteSchoolInfo(String schoolIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(schoolIds==null || schoolIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除学校信息，传入参数为空");
		}else{
			String schoolCondition = " 1=1 AND SCHOOL_ID IN (" + schoolIds + ") ";
			int deleteResult = entityDao.deleteByCondition(schoolCondition, TbSchool.class);
			
			if(deleteResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除学校信息失败");		
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除学校信息成功");		
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editSchoolInfo(String schoolId, String schoolName,String schoolAddress,String schoolPostcode) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(schoolId==null || schoolId.equals("") 
			|| schoolName==null || schoolName.equals("")
			|| schoolAddress==null || schoolAddress.equals("")
			|| schoolPostcode==null || schoolPostcode.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改学校信息，传入参数为空");
		}else{
			TbSchool school = new TbSchool();
			school.setSCHOOL_NAME(schoolName);
			school.setSCHOOL_ADDRESS(schoolAddress);;
			school.setSCHOOL_POSTCODE(schoolPostcode);
			
			int updateResult = entityDao.updatePropByID(school, schoolId);
			
			if(updateResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改学校信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改学校信息成功");
			}
		}
		
		return resultMap;
	}
}
