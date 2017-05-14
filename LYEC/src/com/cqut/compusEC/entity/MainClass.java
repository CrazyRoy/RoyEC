package com.cqut.compusEC.entity;

import java.io.Serializable;

public class MainClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int main_id;			// 主类别 id
	private String className;		// 主类别名称
	private String desc;			// 描述
	private String cssName;			// css样式
	
	public int getMain_id() {
		return main_id;
	}
	public void setMain_id(int main_id) {
		this.main_id = main_id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCssName() {
		return cssName;
	}
	public void setCssName(String cssName) {
		this.cssName = cssName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
