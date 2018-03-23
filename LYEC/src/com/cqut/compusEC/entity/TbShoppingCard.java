package com.cqut.compusEC.entity;

public class TbShoppingCard extends Entity{

	private String CARD_ID;//购物车ID
	private String USER_ID;//用户ID
	private String CARD_DES;//描述
	
	public String getCARD_ID() {
		return CARD_ID;
	}

	public void setCARD_ID(String cARD_ID) {
		CARD_ID = cARD_ID;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getCARD_DES() {
		return CARD_DES;
	}

	public void setCARD_DES(String cARD_DES) {
		CARD_DES = cARD_DES;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_shoppingcard";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "CARD_ID";
	}

}
