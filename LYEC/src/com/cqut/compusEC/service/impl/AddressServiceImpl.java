package com.cqut.compusEC.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbAddress;
import com.cqut.compusEC.service.AddressService;
import com.cqut.compusEC.util.EntityIDFactory;

@Service("addressService")
public class AddressServiceImpl implements AddressService{
	
	@Resource(name = "entityDao")
	EntityDao entityDao;

	@Override
	public Map<String, Object> insertAddressInfo(String userId,
			String addressContent) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(userId==null || userId.equals("") || addressContent==null || addressContent.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增收获地址,传入参数为空");
		}else{
			TbAddress address = new TbAddress();
			address.setADDRESS_ID(EntityIDFactory.createId());
			address.setUSER_ID(userId);
			address.setADDRESS_CONTENT(addressContent);
			
			int resultInt = entityDao.save(address);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "新增收获地址成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增收获地址失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getAddressListByUserId(String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取用户收获地址,传入参数为空");
		}else{
			String[] properties = new String[]{
					"ADDRESS_ID",
					"ADDRESS_CONTENT",
					"USER_ID"
			};
			String table = "tb_address";
			String condition = " 1=1 AND USER_ID='" + userId + "' ";
			
			List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
			if(resultList==null || resultList.size()<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取用户收获地址失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取用户收获地址成功");
				resultMap.put("rows", resultList);
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updateAddressInfo(String addressId,
			String addressContent) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(addressId==null || addressId.equals("") || addressContent==null || addressContent.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改收获地址,传入参数为空");
		}else{
			TbAddress address = new TbAddress();
			address.setADDRESS_CONTENT(addressContent);
			
			int resultInt = entityDao.updatePropByID(address, addressId);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改收获地址成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改收获地址失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteAddressInfo(String addressIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(addressIds==null || addressIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改收获地址,传入参数为空");
		}else{
			String deleteCondition = " 1=1 AND ADDRESS_ID IN ( " + addressIds + " ) ";

			int resultInt = entityDao.deleteByCondition(deleteCondition, TbAddress.class);
			
			if(resultInt>=0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改收获地址成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改收获地址失败");
			}
		}
		
		return resultMap;
	}

}
