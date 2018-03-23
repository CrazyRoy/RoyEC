package com.cqut.compusEC.entity;

public class TbPermission extends Entity{

	private String PERMISSIONID;//角色关联模块ID
	private String MODULEID;//模块ID
	private String ROLEID;//角色ID
	
	public String getPERMISSIONID() {
		return PERMISSIONID;
	}

	public void setPERMISSIONID(String pERMISSIONID) {
		PERMISSIONID = pERMISSIONID;
	}

	public String getMODULEID() {
		return MODULEID;
	}

	public void setMODULEID(String mODULEID) {
		MODULEID = mODULEID;
	}

	public String getROLEID() {
		return ROLEID;
	}

	public void setROLEID(String rOLEID) {
		ROLEID = rOLEID;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_permission";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "PERMISSIONID";
	}

}
