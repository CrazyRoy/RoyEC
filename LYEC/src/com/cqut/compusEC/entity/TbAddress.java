package com.cqut.compusEC.entity;

public class TbAddress extends Entity{
	private String ADDRESS_ID;
	private String ADDRESS_CONTENT;
	private String USER_ID;
	
	public String getADDRESS_ID() {
		return ADDRESS_ID;
	}

	public void setADDRESS_ID(String aDDRESS_ID) {
		ADDRESS_ID = aDDRESS_ID;
	}

	public String getADDRESS_CONTENT() {
		return ADDRESS_CONTENT;
	}

	public void setADDRESS_CONTENT(String aDDRESS_CONTENT) {
		ADDRESS_CONTENT = aDDRESS_CONTENT;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_address";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "ADDRESS_ID";
	}

}
