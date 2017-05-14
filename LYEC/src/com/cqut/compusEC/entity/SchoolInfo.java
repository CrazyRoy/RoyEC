package com.cqut.compusEC.entity;

import java.io.Serializable;

public class SchoolInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int school_id;				// 学校 id
	private String schoolName;			// 学校名称
	private String schoolAddress;		// 学校地址
	private String school_postCode;		// 学校邮编
	
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getSchool_postCode() {
		return school_postCode;
	}
	public void setSchool_postCode(String school_postCode) {
		this.school_postCode = school_postCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
