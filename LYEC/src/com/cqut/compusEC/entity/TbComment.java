package com.cqut.compusEC.entity;

public class TbComment extends Entity{
	
	private String COMMENT_ID;//评论ID
	private String COMMENT_REPLY_ID;//评论回复ID
	private String COMMENT_CONTENT;//评论内容
	private String COMMENT_TIME;//评论时间
	private String USER_ID;//用户ID
	private String ORDER_ID;//订单ID
	public String getCOMMENT_ID() {
		return COMMENT_ID;
	}
	public void setCOMMENT_ID(String cOMMENT_ID) {
		COMMENT_ID = cOMMENT_ID;
	}
	public String getCOMMENT_REPLY_ID() {
		return COMMENT_REPLY_ID;
	}
	public void setCOMMENT_REPLY_ID(String cOMMENT_REPLY_ID) {
		COMMENT_REPLY_ID = cOMMENT_REPLY_ID;
	}
	public String getCOMMENT_CONTENT() {
		return COMMENT_CONTENT;
	}
	public void setCOMMENT_CONTENT(String cOMMENT_CONTENT) {
		COMMENT_CONTENT = cOMMENT_CONTENT;
	}
	public String getCOMMENT_TIME() {
		return COMMENT_TIME;
	}
	public void setCOMMENT_TIME(String cOMMENT_TIME) {
		COMMENT_TIME = cOMMENT_TIME;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_comment";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "COMMENT_ID";
	}
	
}
