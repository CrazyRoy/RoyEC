package com.cqut.compusEC.entity;

import java.io.Serializable;

public class SubClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int sub_id;				// 子类别 id
	private String className;		// 子类别名称
	private String desc;			// 描述
	private int main_id;			// 主类别 id
	
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getMain_id() {
		return main_id;
	}
	public void setMain_id(int main_id) {
		this.main_id = main_id;
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
