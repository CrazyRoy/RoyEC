package com.cqut.compusEC.entity;

public class TbMessage extends Entity{
	
	private String MESSAGE_ID;//留言ID
	private String MESSAGE_PARENTID;//留言回复ID
	private String MESSAGE_CONTENT;//留言内容
	private String MESSAGE_TIME;//留言时间
	private String GOODS_ID;//商品ID
	private String BUYERSID;//买家ID
	private String SELLERID;//卖家ID
	
	public String getMESSAGE_ID() {
		return MESSAGE_ID;
	}
	public void setMESSAGE_ID(String mESSAGE_ID) {
		MESSAGE_ID = mESSAGE_ID;
	}
	public String getMESSAGE_PARENTID() {
		return MESSAGE_PARENTID;
	}
	public void setMESSAGE_PARENTID(String mESSAGE_PARENTID) {
		MESSAGE_PARENTID = mESSAGE_PARENTID;
	}
	public String getMESSAGE_CONTENT() {
		return MESSAGE_CONTENT;
	}
	public void setMESSAGE_CONTENT(String mESSAGE_CONTENT) {
		MESSAGE_CONTENT = mESSAGE_CONTENT;
	}
	public String getMESSAGE_TIME() {
		return MESSAGE_TIME;
	}
	public void setMESSAGE_TIME(String mESSAGE_TIME) {
		MESSAGE_TIME = mESSAGE_TIME;
	}
	public String getGOODS_ID() {
		return GOODS_ID;
	}
	public void setGOODS_ID(String gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}
	public String getBUYERSID() {
		return BUYERSID;
	}
	public void setBUYERSID(String bUYERSID) {
		BUYERSID = bUYERSID;
	}
	public String getSELLERID() {
		return SELLERID;
	}
	public void setSELLERID(String sELLERID) {
		SELLERID = sELLERID;
	}
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_message";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "MESSAGE_ID";
	}
}
