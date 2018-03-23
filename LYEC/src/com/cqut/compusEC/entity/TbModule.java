package com.cqut.compusEC.entity;

public class TbModule extends Entity{

	private String MODULEID;//模块ID
	private String MODULENAME;//模块名称
	private String URL;//模块访问地址
	private String STATE;//模块状态：0-有效，1-无效
	private String CREATETIME;//创建时间
	private String REMARKS;//备注
	
	public String getMODULEID() {
		return MODULEID;
	}

	public void setMODULEID(String mODULEID) {
		MODULEID = mODULEID;
	}

	public String getMODULENAME() {
		return MODULENAME;
	}

	public void setMODULENAME(String mODULENAME) {
		MODULENAME = mODULENAME;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
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
		return "tb_module";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "MODULEID";
	}

}
