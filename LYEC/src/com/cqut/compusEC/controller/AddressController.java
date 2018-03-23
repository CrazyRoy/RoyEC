package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.AddressService;

@Controller
@RequestMapping("/addressController")
public class AddressController {

	@Resource
	private AddressService addressService;
	
	@RequestMapping("/insertAddressInfo")  
    @ResponseBody
	public JSONObject insertAddressInfo(String userId, String addressContent){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = addressService.insertAddressInfo(userId, addressContent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增收获地址异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/updateAddressInfo")  
    @ResponseBody
	public JSONObject updateAddressInfo(String addressId, String addressContent){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = addressService.updateAddressInfo(addressId, addressContent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改收获地址异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteAddressInfo")  
    @ResponseBody
	public JSONObject deleteAddressInfo(String addressIds){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = addressService.deleteAddressInfo(addressIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除收获地址异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getAddressListByUserId")
    @ResponseBody
	public JSONObject getAddressListByUserId(String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = addressService.getAddressListByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "获取用户收获地址异常");
		}
		return JSONObject.fromObject(result);
	}
}
