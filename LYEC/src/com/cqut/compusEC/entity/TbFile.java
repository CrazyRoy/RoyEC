package com.cqut.compusEC.entity;

public class TbFile extends Entity{

	private String FILE_ID;//文件ID
	private String FILE_URL;//文件路径
	private String FILE_NAME;//文件名
	private String FILE_DES;//文件描述
	private String FILE_TYPE;//文件类型：0-商品文件,1-用户头像
	private String GOODS_ID;//商品ID
	
	public String getFILE_ID() {
		return FILE_ID;
	}

	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}

	public String getFILE_URL() {
		return FILE_URL;
	}

	public void setFILE_URL(String fILE_URL) {
		FILE_URL = fILE_URL;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}

	public String getFILE_DES() {
		return FILE_DES;
	}

	public void setFILE_DES(String fILE_DES) {
		FILE_DES = fILE_DES;
	}

	public String getFILE_TYPE() {
		return FILE_TYPE;
	}

	public void setFILE_TYPE(String fILE_TYPE) {
		FILE_TYPE = fILE_TYPE;
	}

	public String getGOODS_ID() {
		return GOODS_ID;
	}

	public void setGOODS_ID(String gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_file";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "FILE_ID";
	}

}
