package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbFile;
import com.cqut.compusEC.entity.TbRoleUser;
import com.cqut.compusEC.entity.TbShoppingCard;
import com.cqut.compusEC.entity.TbUser;
import com.cqut.compusEC.service.UserService;
import com.cqut.compusEC.util.EntityIDFactory;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	/* (non-Javadoc)
	 * @see com.cqut.compusEC.service.UserService#login(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> login(String userAccount, String userPwd, String type, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(userAccount==null || userAccount.equals("") || userPwd==null || userPwd.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "用户登录失败，用户名或密码错误");
		}else{
			try {
				String condition = " 1=1 AND USER_ACCOUNT='" + userAccount + "' AND USER_PWD='" + userPwd + "' ";
				if(type==null || type.equals("") || type.equals("1")){
				}else{
					condition += " AND USER_STATUS=1 ";
				}
				List<TbUser> userList = entityDao.getByCondition(condition, TbUser.class);
				
				if(userList==null || userList.size()<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "用户登录失败，用户名或密码错误");
				}else{
					// Session
					HttpSession session = request.getSession();
					Map<String, Object> userInfo = new HashMap<String, Object>();
					if(type==null || type.equals("") || type.equals("1")){
						session.setAttribute("USERID", userList.get(0).getUSER_ID());
						session.setAttribute("USERACCOUNT", userList.get(0).getUSER_ACCOUNT());
						session.setAttribute("USERNAME", userList.get(0).getUSER_NAME());
						session.setAttribute("USERSTATUS", userList.get(0).getUSER_STATUS());
						// 用户信息
						userInfo.put("USERID", userList.get(0).getUSER_ID());
						userInfo.put("USERACCOUNT", userList.get(0).getUSER_ACCOUNT());
						userInfo.put("USERNAME", userList.get(0).getUSER_NAME());
						userInfo.put("USERSTATUS", userList.get(0).getUSER_STATUS());
					}else{
						session.setAttribute("OPERATORID", userList.get(0).getUSER_ID());
						session.setAttribute("OPERATORACCOUNT", userList.get(0).getUSER_ACCOUNT());
						session.setAttribute("OPERATORNAME", userList.get(0).getUSER_NAME());
						// 用户信息
						userInfo.put("USERID", userList.get(0).getUSER_ID());
						userInfo.put("USERACCOUNT", userList.get(0).getUSER_ACCOUNT());
						userInfo.put("USERNAME", userList.get(0).getUSER_NAME());
						userInfo.put("USERSTATUS", userList.get(0).getUSER_STATUS());
					}
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "用户登录成功");
					resultMap.put("userInfo", userInfo);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "用户登录失败，用户信息异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> registDo(String userAccount, String userPwd,
			String userName, String email) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if((userAccount==null || userAccount.equals(""))
			|| (userPwd==null || userPwd.equals(""))
			|| (userName==null || userName.equals(""))
			|| (email==null || email.equals(""))){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "用户注册，传入参数为空");
		}else{
			TbUser user = new TbUser();
			
			user.setUSER_ID(EntityIDFactory.createId());
			user.setUSER_ACCOUNT(userAccount);
			user.setUSER_PWD(userPwd);
			user.setUSER_NAME(userName);
			user.setUSER_EMAIL(email);
			user.setUSER_STATUS("1");
			
			int resultInt = entityDao.save(user);
			
			if(resultInt>0){
				TbShoppingCard shoppingCard = new TbShoppingCard();
				shoppingCard.setCARD_ID(EntityIDFactory.createId());
				shoppingCard.setUSER_ID(user.getUSER_ID());
				resultInt = entityDao.save(shoppingCard);
				
				if(resultInt>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "用户注册成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "用户注册失败");						
				}
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "用户注册失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> exit(String type, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			HttpSession session = request.getSession(); // 获取Session对象
			/* 删除Session中存储的用户信息 */ 
			String name = "";
			String userName = "";
			if(type==null || type.equals("") || type.equals("1")){
				name = session.getAttribute("USERACCOUNT").toString();
				userName = session.getAttribute("USERNAME").toString();
				session.removeAttribute("USERACCOUNT");
				session.removeAttribute("USERNAME");
				session.removeAttribute("USERID");
				session.removeAttribute("USERSTATUS");
			}else{
				name = session.getAttribute("OPERATORACCOUNT").toString();
				userName = session.getAttribute("OPERATORNAME").toString();
				session.removeAttribute("OPERATORID");
				session.removeAttribute("OPERATORACCOUNT");
				session.removeAttribute("OPERATORNAME");
			}
			if(userName.equals("")){
				System.out.println("用户" + name + "注销");			
			}else{
				System.out.println("用户" + userName + "注销");
			}
			resultMap.put("resultCode", "0000");
			resultMap.put("resultMessage", "退出系统成功");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "用户信息异常，请重新登录");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getUserWithPaging(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		int index = limit;  // 每页数据量
		int pageNum = offset/limit + 1;  // 当前页码
		String tableName = "tb_user user LEFT JOIN tb_school school ON user.SCHOOL_ID=school.SCHOOL_ID";  // 查找的数据库表名
		String[] properties = new String[]{ // 查找的字段组成的字符串数组
				"user.USER_ID",
				"user.USER_ACCOUNT",
				"user.USER_NAME",
				"user.USER_PHONE",
				"user.USER_EMAIL",
				"user.USER_SEX",
				"user.USER_AGE",
				"user.USER_ADDRESS",
				"user.USER_SCHOOLNO",
				"user.SCHOOL_ID",
				"school.SCHOOL_NAME",
				"user.USER_STATUS"
		};
		String condition = " 1=1 ";
		// 通过调用DAO的方法找出需要的数据
		List<Map<String, Object>> result = entityDao.searchWithpaging(properties, tableName, null, null, condition, null, order, sort, index, pageNum);
		// 数据库中总共的数据条数
		int count = entityDao.getCountByCondition(" 1=1 ", TbUser.class);
		
		// 需要返回的Map
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", count); // 数据总数
		map.put("rows", result); // 数据内容
		
		return map;
	}

	@Override
	public Map<String, Object> delUser(String userIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(userIds == null || userIds.isEmpty()){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除帐号，传入参数为空");
		}else{
			String delConditionUser = " 1=1 AND USER_ID IN (" + userIds + ") ";
			String delConditionRoleUser = " 1=1 AND USERID IN (" + userIds + ") ";
			int result = entityDao.deleteByCondition(delConditionRoleUser, TbRoleUser.class); //调用DAO的方法删除多个帐号信息
			result = entityDao.deleteByCondition(delConditionUser, TbUser.class); //调用DAO的方法删除多个帐号信息
			
			if(result<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除帐号失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除帐号成功");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> updateUserState(String userIds, String state) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(userIds==null || userIds.equals("") || state==null || state.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改帐号状态，传入参数为空");
		}else{
			TbUser user = new TbUser();
			user.setUSER_STATUS(state);
			String accountCondition = " 1=1 AND USER_ID IN (" + userIds + ") ";
			int updateResult = entityDao.updatePropByCondition(user, accountCondition);
			if(updateResult<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改帐号状态失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改帐号状态成功");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getUserRoleListInfo(String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "帐号关联角色查询，传入参数为空");
		}else{
			String userRoleCondition = " USERID='" + userId + "' ";
			String userRoleTable = "tb_roleuser";
			String[] properties = new String[]{"ROLEID"};
			
			try {
				List<Map<String, Object>> resultList = entityDao.searchForeign(properties, userRoleTable, null, null, userRoleCondition);
				
				if(resultList==null || resultList.size()<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "帐号没有关联角色");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "帐号关联角色查询成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "帐号关联角色查询异常");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> saveUserRoleInfo(String userId, String roleIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "帐号关联角色保存，传入参数为空");
		}else{
			if(roleIds==null || roleIds.equals("")){
				String delCondition = " USERID='" + userId + "' ";
				int resultInt = entityDao.deleteByCondition(delCondition, TbRoleUser.class);
				
				if(resultInt>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "帐号关联角色保存，成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "帐号关联角色保存，失败");
				}
			}else{
				String[] roleIdArray = roleIds.split(",");
				String tableName = "tb_roleuser";
				String properties = " ROLEUSERID, ROLEID, USERID ";
				String entityValues = " ";
				for(int i=0; i<roleIdArray.length; i++){
					TbRoleUser roleUser = new TbRoleUser();
					roleUser.setROLEUSERID(EntityIDFactory.createId());
					roleUser.setROLEID(roleIdArray[i]);
					roleUser.setUSERID(userId);
					
					entityValues += " ('" 
							+ roleUser.getROLEUSERID() + "','"
							+ roleUser.getROLEID() + "','"
							+ roleUser.getUSERID() + "'),";
				}
				
				entityValues = entityValues.substring(0, entityValues.length()-1);
				
				String delCondition = " USERID='" + userId + "' ";
				entityDao.deleteByCondition(delCondition, TbRoleUser.class);
				
				int resultInt = entityDao.saveEntities(tableName, properties, entityValues);
				if(resultInt>0){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "帐号关联角色保存，成功");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "帐号关联角色保存，失败");
				}
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> updateUserInfo(String userId,
			String userAccount, String userPwd, String userName,
			String userPhone, String userEmail, String userSex, String userAge,
			String userAddress, String userSchoolNo, String schoolId,
			String userPicName, String userPicPath) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改用户信息，传入参数为空");
		}else{
			TbFile picFile = new TbFile();
			int picResultInt = 0;
			if(userPicName==null || userPicName.equals("") || userPicPath==null || userPicPath.equals("")){
				picFile.setFILE_ID(null);
			}else{
				picFile.setFILE_ID(EntityIDFactory.createId());
				picFile.setFILE_NAME(userPicName);
				picFile.setFILE_URL(userPicPath);
				picFile.setFILE_TYPE("1");
				picResultInt = entityDao.save(picFile);
			}
			TbUser user = new TbUser();
			if(userAccount==null || userAccount.equals("")){
				user.setUSER_ACCOUNT(null);
			}else{
				user.setUSER_ACCOUNT(userAccount);
			}
			if(userPwd==null || userPwd.equals("")){
				user.setUSER_PWD(null);
			}else{
				user.setUSER_PWD(userPwd);
			}
			if(userName==null || userName.equals("")){
				user.setUSER_NAME(null);
			}else{
				user.setUSER_NAME(userName);
			}
			if(userPhone==null || userPhone.equals("")){
				user.setUSER_PHONE(null);
			}else{
				user.setUSER_PHONE(userPhone);
			}
			if(userEmail==null || userEmail.equals("")){
				user.setUSER_EMAIL(null);
			}else{
				user.setUSER_EMAIL(userEmail);
			}
			if(userSex==null || userSex.equals("")){
				user.setUSER_SEX(null);
			}else{
				user.setUSER_SEX(userSex);
			}
			if(userAge==null || userAge.equals("")){
				user.setUSER_AGE(null);
			}else{
				user.setUSER_AGE(userAge);
			}
			if(userAddress==null || userAddress.equals("")){
				user.setUSER_ADDRESS(null);
			}else{
				user.setUSER_ADDRESS(userAddress);
			}
			if(userSchoolNo==null || userSchoolNo.equals("")){
				user.setUSER_SCHOOLNO(null);
			}else{
				user.setUSER_SCHOOLNO(userSchoolNo);
			}
			if(schoolId==null || schoolId.equals("")){
				user.setSCHOOL_ID(null);
			}else{
				user.setSCHOOL_ID(schoolId);
			}
			if(picResultInt>0){
				user.setUSER_PICID(picFile.getFILE_ID());
			}else{
				user.setUSER_PICID(null);
			}
			
			int resultUserInt = entityDao.updatePropByID(user, userId);
			if(resultUserInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改用户信息成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改用户信息失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getUserInfo(String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据用户Id获取用户信息，传入参数为空");
		}else{
			try {
				String[] properties = new String[]{
						"a.USER_ID userId",
						"a.USER_ACCOUNT userAccount",
						"a.USER_PWD userPwd",
						"a.USER_PICID userPicId",
						"b.FILE_NAME userPicName",
						"b.FILE_URL userPicPath",
						"a.USER_NAME userName",
						"a.USER_PHONE userPhone",
						"a.USER_EMAIL userEmail",
						"a.USER_SEX userSex",
						"a.USER_AGE userAge",
						"a.USER_ADDRESS userAddress",
						"a.USER_SCHOOLNO userSchoolNo",
						"a.SCHOOL_ID schoolId",
						"c.SCHOOL_NAME schoolName",
						"a.USER_STATUS userStatus"
				};
				String table = "tb_user a LEFT JOIN tb_file b ON a.USER_PICID=b.FILE_ID LEFT JOIN tb_school c ON a.SCHOOL_ID = c.SCHOOL_ID ";
				String condition = " 1=1 AND a.USER_ID='" + userId + "' ";
				
				List<Map<String, Object>> userMap = entityDao.searchForeign(properties, table, null, null, condition);
				if(userMap==null || userMap.size()<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "根据用户Id获取用户信息失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "根据用户Id获取用户信息成功");
					resultMap.put("rows", userMap);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据用户Id获取用户信息异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getUserInfoBygoodsId(String goodsId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(goodsId==null || goodsId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据商品获取卖家信息，传入参数为空");
		}else{
			try {
				String[] properties = new String[]{
						"tb_user.USER_ID",
						"tb_user.USER_ACCOUNT",
						"tb_user.USER_PICID",
						"tb_file.FILE_URL USER_PICPATH",
						"tb_file.FILE_NAME USER_PICNAME",
						"tb_user.USER_NAME",
						"tb_user.USER_PHONE",
						"tb_user.USER_EMAIL",
						"tb_user.USER_ADDRESS",
						"tb_user.USER_SCHOOLNO",
						"tb_user.SCHOOL_ID",
						"tb_school.SCHOOL_NAME",
						"tb_school.SCHOOL_POSTCODE",
						"tb_school.SCHOOL_ADDRESS"
					};
					String table = "tb_user LEFT JOIN tb_file ON tb_user.USER_PICID=tb_file.FILE_ID LEFT JOIN tb_school ON tb_user.SCHOOL_ID=tb_school.SCHOOL_ID LEFT JOIN tb_goods_user ON tb_user.USER_ID=tb_goods_user.USER_ID ";
					String condition = " 1=1 AND tb_user.USER_STATUS=1 AND tb_goods_user.GOODS_ID='" + goodsId + "' ";
					List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
					if(resultList==null || resultList.size()<0){
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "根据商品获取卖家信息失败");
					}else{
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "根据商品获取卖家信息成功");
						resultMap.put("rows", resultList);
					}				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据商品获取卖家信息异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> updateUserPwd(String userId, String userPwd) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("") || userPwd==null || userPwd.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改用户密码，传入参数为空");
		}else{
			TbUser user = new TbUser();
			user.setUSER_PWD(userPwd);
			
			int resultInt = entityDao.updatePropByID(user, userId);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改用户密码成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改用户密码失败");
			}
		}
		return resultMap;
	}
}
