package com.cqut.compusEC.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

public interface UserService {
	public Map<String, Object> login(String userAccount, String password, String type, HttpServletRequest request);
	
	public Map<String, Object> registDo(String userAccount, String userPwd, String userName, String email);
	
	public Map<String, Object> updateUserPwd(String userId, String userPwd);
	
	public Map<String, Object> exit(String type, HttpServletRequest request);
	
	/**
	 * 分页获取帐号信息
	 * @param limit   每一页的数据量
	 * @param offset  起始数据的下标
	 * @param order   排序的字段
	 * @param sort    排序方式（升降）
	 * @return        多个帐号信息和数据总数组成的Map
	 */
	public Map<String, Object> getUserWithPaging(int limit, int offset, String order, String sort);
	
	/**
	 * 通过帐号ID删除帐号（可删除多个）
	 * @param accountIDs 多个帐号ID组成的字符串
	 * @return  成功返回删除的对象数，失败返回0
	 */
	public Map<String, Object> delUser(String userIds);
	
	public Map<String, Object> updateUserState(String userIds, String state);
	
	public Map<String, Object> getUserRoleListInfo(String userId);

	public Map<String, Object> getUserInfo(String userId);
	
	public Map<String, Object> getUserInfoBygoodsId(String goodsId);
	
	public Map<String, Object> saveUserRoleInfo(String userId, String roleIds);
	
	// 修改用户信息
	public Map<String, Object> updateUserInfo(String userId, String userAccount, String userPwd, String userName, String userPhone, String userEmail, String userSex, String userAge, String userAddress, String userSchoolNo, String schoolId, String userPicName, String userPicPath);
}
