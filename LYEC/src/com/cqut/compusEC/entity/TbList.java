package com.cqut.compusEC.entity;

public class TbList extends Entity{

	private String LIST_ID;//目录ID
	private String PARENT_ID;//父级ID
	private String IS_LAST;//是否是最后子级
	private String LIST_NAME;//目录名称
	private String LIST_DES;//目录表述
	private String TYPE_ID;//类型ID（对应tb_type表）
	private String LIST_LEVEL;//菜单级别
	private String CLICKCOUNT_ID;//目录点击量ID（对应TB_CLICKAMOUNT）

	public String getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(String lIST_ID) {
		LIST_ID = lIST_ID;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}

	public String getIS_LAST() {
		return IS_LAST;
	}

	public void setIS_LAST(String iS_LAST) {
		IS_LAST = iS_LAST;
	}

	public String getLIST_NAME() {
		return LIST_NAME;
	}

	public void setLIST_NAME(String lIST_NAME) {
		LIST_NAME = lIST_NAME;
	}

	public String getLIST_DES() {
		return LIST_DES;
	}

	public void setLIST_DES(String lIST_DES) {
		LIST_DES = lIST_DES;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String tYPE_ID) {
		TYPE_ID = tYPE_ID;
	}

	public String getCLICKCOUNT_ID() {
		return CLICKCOUNT_ID;
	}

	public void setCLICKCOUNT_ID(String cLICKCOUNT_ID) {
		CLICKCOUNT_ID = cLICKCOUNT_ID;
	}

	public String getLIST_LEVEL() {
		return LIST_LEVEL;
	}

	public void setLIST_LEVEL(String lIST_LEVEL) {
		LIST_LEVEL = lIST_LEVEL;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_list";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "LIST_ID";
	}

}
