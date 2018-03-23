package com.cqut.compusEC.entity;

public class TbSchool extends Entity{
	
	private String SCHOOL_ID;
	private String SCHOOL_NAME;
	private String SCHOOL_ADDRESS;
	private String SCHOOL_POSTCODE;
	
	public String getSCHOOL_ID() {
		return SCHOOL_ID;
	}
	
	public void setSCHOOL_ID(String sCHOOL_ID) {
		SCHOOL_ID = sCHOOL_ID;
	}
	
	public String getSCHOOL_NAME() {
		return SCHOOL_NAME;
	}
	
	public void setSCHOOL_NAME(String sCHOOL_NAME) {
		SCHOOL_NAME = sCHOOL_NAME;
	}
	
	public String getSCHOOL_ADDRESS() {
		return SCHOOL_ADDRESS;
	}
	
	public void setSCHOOL_ADDRESS(String sCHOOL_ADDRESS) {
		SCHOOL_ADDRESS = sCHOOL_ADDRESS;
	}
	
	public String getSCHOOL_POSTCODE() {
		return SCHOOL_POSTCODE;
	}
	
	public void setSCHOOL_POSTCODE(String sCHOOL_POSTCODE) {
		SCHOOL_POSTCODE = sCHOOL_POSTCODE;
	}
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_school";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "SCHOOL_ID";
	}
	
}
