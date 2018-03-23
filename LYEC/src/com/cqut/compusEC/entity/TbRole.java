package com.cqut.compusEC.entity;

public class TbRole extends Entity{

	private String ROLEID;//角色ID
	private String ROLENAME;//角色名称
	private String CREATETIME;//创建时间
	private String REMARKS;//备注
	
	public String getROLEID() {
		return ROLEID;
	}

	public void setROLEID(String rOLEID) {
		ROLEID = rOLEID;
	}

	public String getROLENAME() {
		return ROLENAME;
	}

	public void setROLENAME(String rOLENAME) {
		ROLENAME = rOLENAME;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_role";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "ROLEID";
	}

}
