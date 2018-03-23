package com.cqut.compusEC.entity;

public class TbClickAmount extends Entity{

	private String AMOUNT_ID;//点击量ID
	private String AMOUNT_NUM;//点击量描述
	
	public String getAMOUNT_ID() {
		return AMOUNT_ID;
	}

	public void setAMOUNT_ID(String aMOUNT_ID) {
		AMOUNT_ID = aMOUNT_ID;
	}

	public String getAMOUNT_NUM() {
		return AMOUNT_NUM;
	}

	public void setAMOUNT_NUM(String aMOUNT_NUM) {
		AMOUNT_NUM = aMOUNT_NUM;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_clickamount";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "AMOUNT_ID";
	}

}
