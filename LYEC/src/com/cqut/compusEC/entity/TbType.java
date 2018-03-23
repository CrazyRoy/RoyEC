package com.cqut.compusEC.entity;

public class TbType extends Entity{

	private String TYPE_ID;
	private String TYPE_DES;
	private String TYPE_NAME;
	
	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String tYPE_ID) {
		TYPE_ID = tYPE_ID;
	}

	public String getTYPE_DES() {
		return TYPE_DES;
	}

	public void setTYPE_DES(String tYPE_DES) {
		TYPE_DES = tYPE_DES;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String tYPE_NAME) {
		TYPE_NAME = tYPE_NAME;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_type";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "TYPE_ID";
	}

}
