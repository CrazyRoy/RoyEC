package com.cqut.compusEC.entity;

public class TbGoodsUser extends Entity{

	private String GOODS_USER_ID;//ID
	private String GOODS_ID;//商品ID
	private String USER_ID ;//用户ID
	private String GOODS_USER_DES;//描述
	
	public String getGOODS_USER_ID() {
		return GOODS_USER_ID;
	}

	public void setGOODS_USER_ID(String gOODS_USER_ID) {
		GOODS_USER_ID = gOODS_USER_ID;
	}

	public String getGOODS_ID() {
		return GOODS_ID;
	}

	public void setGOODS_ID(String gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getGOODS_USER_DES() {
		return GOODS_USER_DES;
	}

	public void setGOODS_USER_DES(String gOODS_USER_DES) {
		GOODS_USER_DES = gOODS_USER_DES;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_goods_user";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "GOODS_USER_ID";
	}

}
