package com.cqut.compusEC.dto;

import java.io.Serializable;

public class ProductInfoDto implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String product_id;		// 商品 id
		private String title; 			// 商品标题
		private String introduce;		// 商品介绍
		private String imageUrls;		// 图片地址
		private String resalePrice;		// 现价
		private String originalPrice;	// 原价
		private String condition;		// 商品成色
		private String createdTime;		// 发表时间
		private String view_count;		// 浏览量
		private String ding_num;		// 顶
		private String cai_num;			// 踩
		private String user_id;			// 用户 id
		private String main_class_id;	// 主类别 id
		private String sub_class_id;	// 子类别 id
		
		public String getProduct_id() {
			return product_id;
		}
		public void setProduct_id(String product_id) {
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
		public String getResalePrice() {
			return resalePrice;
		}
		public void setResalePrice(String resalePrice) {
			this.resalePrice = resalePrice;
		}
		public String getOriginalPrice() {
			return originalPrice;
		}
		public void setOriginalPrice(String originalPrice) {
			this.originalPrice = originalPrice;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		public String getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public String getView_count() {
			return view_count;
		}
		public void setView_count(String view_count) {
			this.view_count = view_count;
		}
		public String getDing_num() {
			return ding_num;
		}
		public void setDing_num(String ding_num) {
			this.ding_num = ding_num;
		}
		public String getCai_num() {
			return cai_num;
		}
		public void setCai_num(String cai_num) {
			this.cai_num = cai_num;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getMain_class_id() {
			return main_class_id;
		}
		public void setMain_class_id(String main_class_id) {
			this.main_class_id = main_class_id;
		}
		public String getSub_class_id() {
			return sub_class_id;
		}
		public void setSub_class_id(String sub_class_id) {
			this.sub_class_id = sub_class_id;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}
