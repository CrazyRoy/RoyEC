package com.cqut.compusEC.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int product_id;			// 商品 id
	private String title; 			// 商品标题
	private String introduce;		// 商品介绍
	private String imageUrls;		// 图片地址
	private double resalePrice;		// 现价
	private double originalPrice;	// 原价
	private int condition;			// 商品成色
	private Date createdTime;		// 发表时间
	private int view_count;			// 浏览量
	private int ding_num;			// 顶
	private int cai_num;			// 踩
	private int user_id;			// 用户 id
	private int main_class_id;		// 主类别 id
	private int sub_class_id;		// 子类别 id
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public double getResalePrice() {
		return resalePrice;
	}
	public void setResalePrice(double resalePrice) {
		this.resalePrice = resalePrice;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getDing_num() {
		return ding_num;
	}
	public void setDing_num(int ding_num) {
		this.ding_num = ding_num;
	}
	public int getCai_num() {
		return cai_num;
	}
	public void setCai_num(int cai_num) {
		this.cai_num = cai_num;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMain_class_id() {
		return main_class_id;
	}
	public void setMain_class_id(int main_class_id) {
		this.main_class_id = main_class_id;
	}
	public int getSub_class_id() {
		return sub_class_id;
	}
	public void setSub_class_id(int sub_class_id) {
		this.sub_class_id = sub_class_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
