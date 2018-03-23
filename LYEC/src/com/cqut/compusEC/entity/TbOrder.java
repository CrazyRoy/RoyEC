package com.cqut.compusEC.entity;

public class TbOrder extends Entity{
	
	private String ORDER_ID;//订单ID
	private String ORDER_TIME;//订单时间
	private String ORDER_STATU;//订单状态
	private String USER_ID;//用户ID
	private String GOODS_ID;//商品ID
	private String ADDRESS_ID;//收货地址ID
	
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public String getORDER_TIME() {
		return ORDER_TIME;
	}
	public void setORDER_TIME(String oRDER_TIME) {
		ORDER_TIME = oRDER_TIME;
	}
	public String getORDER_STATU() {
		return ORDER_STATU;
	}
	public void setORDER_STATU(String oRDER_STATU) {
		ORDER_STATU = oRDER_STATU;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getGOODS_ID() {
		return GOODS_ID;
	}
	public void setGOODS_ID(String gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}
	public String getADDRESS_ID() {
		return ADDRESS_ID;
	}
	public void setADDRESS_ID(String aDDRESS_ID) {
		ADDRESS_ID = aDDRESS_ID;
	}
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_order";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "ORDER_ID";
	}
}
