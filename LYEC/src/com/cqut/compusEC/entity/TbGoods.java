package com.cqut.compusEC.entity;

public class TbGoods extends Entity{

	private String GOODS_ID;//商品ID
	private String GOODS_TITLE;//商品标题
	private String GOODS_DES;//商品描述
	private String GOODS_ORIGINAL;//商品原价
	private String GOODS_RESALE;//商品转卖价
	private String GOODS_CONDITION;// 商品成色
	private String GOODS_CREATEDTIME;//创建时间
	private String GOODS_RECENTACCESS;//最近访问时间
	private Integer GOODS_VIEWCONTS;//访问量
	private String GOODS_DINGNUM;//顶数量
	private String GOODS_CAINUM;//踩数量
	private String GOODS_STATUS;//商品状态(0:未卖出，1：已卖出)
	private String LIST_ID;//目录ID

	public String getGOODS_ID() {
		return GOODS_ID;
	}

	public void setGOODS_ID(String gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}

	public String getGOODS_TITLE() {
		return GOODS_TITLE;
	}

	public void setGOODS_TITLE(String gOODS_TITLE) {
		GOODS_TITLE = gOODS_TITLE;
	}

	public String getGOODS_DES() {
		return GOODS_DES;
	}

	public void setGOODS_DES(String gOODS_DES) {
		GOODS_DES = gOODS_DES;
	}

	public String getGOODS_ORIGINAL() {
		return GOODS_ORIGINAL;
	}

	public void setGOODS_ORIGINAL(String gOODS_ORIGINAL) {
		GOODS_ORIGINAL = gOODS_ORIGINAL;
	}

	public String getGOODS_RESALE() {
		return GOODS_RESALE;
	}

	public void setGOODS_RESALE(String gOODS_RESALE) {
		GOODS_RESALE = gOODS_RESALE;
	}

	public String getGOODS_CREATEDTIME() {
		return GOODS_CREATEDTIME;
	}

	public void setGOODS_CREATEDTIME(String gOODS_CREATEDTIME) {
		GOODS_CREATEDTIME = gOODS_CREATEDTIME;
	}

	public String getGOODS_RECENTACCESS() {
		return GOODS_RECENTACCESS;
	}

	public void setGOODS_RECENTACCESS(String gOODS_RECENTACCESS) {
		GOODS_RECENTACCESS = gOODS_RECENTACCESS;
	}

	public Integer getGOODS_VIEWCONTS() {
		return GOODS_VIEWCONTS;
	}

	public void setGOODS_VIEWCONTS(Integer gOODS_VIEWCONTS) {
		GOODS_VIEWCONTS = gOODS_VIEWCONTS;
	}

	public String getGOODS_DINGNUM() {
		return GOODS_DINGNUM;
	}

	public void setGOODS_DINGNUM(String gOODS_DINGNUM) {
		GOODS_DINGNUM = gOODS_DINGNUM;
	}

	public String getGOODS_CAINUM() {
		return GOODS_CAINUM;
	}

	public void setGOODS_CAINUM(String gOODS_CAINUM) {
		GOODS_CAINUM = gOODS_CAINUM;
	}

	public String getGOODS_STATUS() {
		return GOODS_STATUS;
	}

	public void setGOODS_STATUS(String gOODS_STATUS) {
		GOODS_STATUS = gOODS_STATUS;
	}

	public String getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(String lIST_ID) {
		LIST_ID = lIST_ID;
	}

	public String getGOODS_CONDITION() {
		return GOODS_CONDITION;
	}

	public void setGOODS_CONDITION(String gOODS_CONDITION) {
		GOODS_CONDITION = gOODS_CONDITION;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_goods";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "GOODS_ID";
	}

}
