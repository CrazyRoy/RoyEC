package com.cqut.compusEC.entity;

public class TbUser extends Entity{

	private String USER_ID;//用户ID
	private String USER_ACCOUNT;//账户名
	private String USER_PWD;//密码
	private String USER_PICID;//头像文件ID
	private String USER_NAME;//姓名
	private String USER_PHONE;//联系电话
	private String USER_EMAIL;//邮箱
	private String USER_SEX;//性别
	private String USER_AGE;//年龄
	private String USER_ADDRESS;//住址
	private String USER_SCHOOLNO;//学号
	private String SCHOOL_ID;//学校ID
	private String USER_STATUS;//用户状态(0:被冻结，1:正常)
	
	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getUSER_ACCOUNT() {
		return USER_ACCOUNT;
	}

	public void setUSER_ACCOUNT(String uSER_ACCOUNT) {
		USER_ACCOUNT = uSER_ACCOUNT;
	}

	public String getUSER_PWD() {
		return USER_PWD;
	}

	public void setUSER_PWD(String uSER_PWD) {
		USER_PWD = uSER_PWD;
	}

	public String getUSER_PICID() {
		return USER_PICID;
	}

	public void setUSER_PICID(String uSER_PICID) {
		USER_PICID = uSER_PICID;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getUSER_PHONE() {
		return USER_PHONE;
	}

	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}

	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}

	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}

	public String getUSER_SEX() {
		return USER_SEX;
	}

	public void setUSER_SEX(String uSER_SEX) {
		USER_SEX = uSER_SEX;
	}

	public String getUSER_AGE() {
		return USER_AGE;
	}

	public void setUSER_AGE(String uSER_AGE) {
		USER_AGE = uSER_AGE;
	}

	public String getUSER_ADDRESS() {
		return USER_ADDRESS;
	}

	public void setUSER_ADDRESS(String uSER_ADDRESS) {
		USER_ADDRESS = uSER_ADDRESS;
	}

	public String getUSER_SCHOOLNO() {
		return USER_SCHOOLNO;
	}

	public void setUSER_SCHOOLNO(String uSER_SCHOOLNO) {
		USER_SCHOOLNO = uSER_SCHOOLNO;
	}

	public String getSCHOOL_ID() {
		return SCHOOL_ID;
	}

	public void setSCHOOL_ID(String sCHOOL_ID) {
		SCHOOL_ID = sCHOOL_ID;
	}

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	public void setUSER_STATUS(String uSER_STATUS) {
		USER_STATUS = uSER_STATUS;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_user";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "USER_ID";
	}
}
