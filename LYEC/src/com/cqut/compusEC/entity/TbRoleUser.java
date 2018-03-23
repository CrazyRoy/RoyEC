package com.cqut.compusEC.entity;

public class TbRoleUser extends Entity{

	private String ROLEUSERID;//角色成员ID
	private String ROLEID;//角色ID
	private String USERID;//帐号ID
	
	public String getROLEUSERID() {
		return ROLEUSERID;
	}

	public void setROLEUSERID(String rOLEUSERID) {
		ROLEUSERID = rOLEUSERID;
	}

	public String getROLEID() {
		return ROLEID;
	}

	public void setROLEID(String rOLEID) {
		ROLEID = rOLEID;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_roleuser";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "ROLEUSERID";
	}

}
