package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {

	@Resource
	private UserService userService;
	
	/**
	 * 分页获取帐号信息
	 * @param limit   每一页的数据量
	 * @param offset  起始数据的下标
	 * @param order   排序的字段
	 * @param sort    排序方式（升降）
	 * @return        多个帐号信息的JSON数组字符串
	 */
	@RequestMapping("/getUserWithPaging")  
    @ResponseBody
	public JSONObject getUserWithPaging(int limit, int offset, String order, String sort){
		Map<String, Object> result = userService.getUserWithPaging(limit,offset,sort,order);
		return JSONObject.fromObject(result); // 将Map转换成JSON对象的字符串
	}
	
	/**
	 * 通过帐号ID删除帐号（可删除多个）
	 * @param accountIDs 多个帐号ID组成的字符串
	 * @return  成功返回删除的对象数，失败返回0
	 */
	@RequestMapping("/delUser")  
    @ResponseBody
	public JSONObject delUser(String userIds){
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.delUser(userIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除帐号异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/updateUserState")  
    @ResponseBody
	public JSONObject updateUserState(String userIds, String state){
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updateUserState(userIds, state);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改帐号状态异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getUserRoleListInfo")  
    @ResponseBody
	public JSONObject getUserRoleListInfo(String userId){
		Map<String, Object> result = userService.getUserRoleListInfo(userId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getUserInfo")  
    @ResponseBody
	public JSONObject getUserInfo(String userId){
		Map<String, Object> result = userService.getUserInfo(userId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getUserInfoBygoodsId")  
    @ResponseBody
	public JSONObject getUserInfoBygoodsId(String goodsId){
		Map<String, Object> result = userService.getUserInfoBygoodsId(goodsId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/saveUserRoleInfo")  
    @ResponseBody
	public JSONObject saveUserRoleInfo(String userId, String roleIds){
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.saveUserRoleInfo(userId, roleIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "帐号关联角色保存，异常");
		}
		return JSONObject.fromObject(result);
	}
	
	// 用户修改信息
	@RequestMapping("/updateUserInfo")  
    @ResponseBody
	public JSONObject updateUserInfo(String userId, String userAccount, String userPwd, String userName, String userPhone, String userEmail, String userSex, String userAge, String userAddress, String userSchoolNo, String schoolId, String userPicName, String userPicPath){
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updateUserInfo(userId, userAccount, userPwd, userName, userPhone, userEmail, userSex, userAge, userAddress, userSchoolNo, schoolId, userPicName, userPicPath);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改信息异常");
		}
		return JSONObject.fromObject(result);
	}
	
	// 用户登录
	@ResponseBody
	@RequestMapping("/login")
	public JSONObject login(String userAccount, String userPwd, String type, HttpServletRequest request) {
		Map<String, Object> result = userService.login(userAccount, userPwd, type, request);
		return JSONObject.fromObject(result);
	}
	
	// 用户注册
	@ResponseBody
	@RequestMapping("/regist")
	public JSONObject regist(String userAccount, String userPwd, String userName, String email){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = userService.registDo(userAccount, userPwd, userName, email);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改用户密码异常");
		}
		return JSONObject.fromObject(result);
	}
	
	// 修改用户密码
	@ResponseBody
	@RequestMapping("/updateUserPwd")
	public JSONObject updateUserPwd(String userId, String userPwd){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = userService.updateUserPwd(userId, userPwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "用户注册异常");
		}
		return JSONObject.fromObject(result);
	}
	
	// 用户退出系统
	@ResponseBody
	@RequestMapping("/exit")
	public JSONObject exit(String type, HttpServletRequest request) {
		Map<String, Object> result = userService.exit(type, request);
		return JSONObject.fromObject(result);
	}
}
