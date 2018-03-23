package com.cqut.compusEC.service;

import java.util.Map;

public interface AddressService {

	public Map<String, Object> insertAddressInfo(String userId, String addressContent);
	
	public Map<String, Object> updateAddressInfo(String addressId, String addressContent);
	
	public Map<String, Object> deleteAddressInfo(String addressIds);
	
	public Map<String, Object> getAddressListByUserId(String userId);
}
