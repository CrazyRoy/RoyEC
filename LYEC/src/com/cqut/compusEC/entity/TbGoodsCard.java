package com.cqut.compusEC.entity;

public class TbGoodsCard extends Entity{

	private String GOODS_CARD_ID;
	private String GOODS_ID;//商品ID
	private String CARD_ID;//购物车ID
	private String GOODS_CARD_DES;
	
	public String getGOODS_CARD_ID() {
		return GOODS_CARD_ID;
	}

	public void setGOODS_CARD_ID(String gOODS_CARD_ID) {
		GOODS_CARD_ID = gOODS_CARD_ID;
	}

	public String getGOODS_ID() {
		return GOODS_ID;
	}

	public void setGOODS_ID(String gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}

	public String getCARD_ID() {
		return CARD_ID;
	}

	public void setCARD_ID(String cARD_ID) {
		CARD_ID = cARD_ID;
	}

	public String getGOODS_CARD_DES() {
		return GOODS_CARD_DES;
	}

	public void setGOODS_CARD_DES(String gOODS_CARD_DES) {
		GOODS_CARD_DES = gOODS_CARD_DES;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_goods_card";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "GOODS_CARD_ID";
	}

}
