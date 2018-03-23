<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String goodsId = request.getParameter("goodsId");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<!-- css外联样式 -->
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="platform/css/basic.css" />
<link rel="stylesheet" type="text/css"
	href="platform/css/goodsDetail.css" />
<title>CompusEC</title>
</head>
<body>
	<div class="w detailContent">
		<!-- 隐藏标签 -->
		<input id="goodsId" value="<%=goodsId%>" type="text"
			style="display: none;" />
		<input id="goodsUserId" value="" type="text"
			style="display: none;" />
		<!--顶部板块-->
		<div class="detail-head clearfix">
			<!--发布者信息头像和用户名-->
			<div class="owner-info">
				<img id="goodsUserImg" clss="user_img" src="platform/img/avatar.jpg" />
				<span id="goodsUserName" class="user_name"></span>
			</div>
			<!--浏览量+发布日期-->
			<div class="viewCount-time">
				<div class="viewCount">
					<span>宝贝浏览次数</span> <span id="goodsViewCount" class="count">1</span>
				</div>
				<div class="time">
					<span>最近浏览</span> <span id="goodsRecentAccessTime"
						class="publish_time">2017-05-13 16:30 </span>
				</div>
				<div class="time">
					<span>发布时间</span> <span id="goodsPublishTime" class="publish_time">2017-05-13
						16:30 </span>
				</div>
			</div>
		</div>
		<!--图片+基本信息-->
		<div class="detail-center clearfix">
			<!--图片信息-->
			<div class="show-images clearfix">
				<div class="currentImgBox">
					<img id="goodsCurrentImg" src="platform/img/girlC.jpg" alt=""
						class="currentImg" />
				</div>
				<div class="totalImgs">
					<div class="content">
						<ul id="goodsImageList" class="image-list clearfix">
						</ul>
					</div>
				</div>
			</div>
			<!--基本信息-->
			<div class="base-info">
				<h3 id="goodsTitle" class="title">
					这里是闲置商品标题.
					</h3>
					<ul class="price-info">
						<li class="price-block"><span class="para">转&nbsp;卖&nbsp;价：</span>
							<span class="priceBig"><b>&yen;</b><em
								id="goodsResalePrice">600.00</em></span></li>
						<li class="price-block"><span class="para">原 价：</span> <b>&yen;</b>
							<span id="goodsOriginalPrice">1378.00</span></li>
					</ul>
					<ul class="idle-info">
						<li><span class="para">成 色：</span> <em id="goodsCondition">暂无</em></li>
						<li class="contact"><span class="para">联系电话：</span> <em
							id="goodsUserPhone">暂无</em></li>
						<li class="contact"><span class="para">电子邮箱：</span> <em
							id="goodsUserEmail">暂无</em></li>
						<li class="school"><span class="para">院 校：</span> <em
							id="goodsUserSchoolName">暂未完善</em></li>
						<li class="address"><span class="para">地 址：</span> <em
							id="goodsUserAddress">暂未完善</em></li>
					</ul>
					<div class="actions">
						<input type="button" name="" class="actionBtn buy-btn"
							value="立即购买" onclick="buyGoods()" /> <input type="button"
							name="" class="actionBtn card-btn" value="加入购物车"
							onclick="addCard()" />
						<div class="ding-cai clearfix">
							<input type="button" id="goodsDingNum" name="" class="ding-btn"
								value="顶（0）" onclick="dingClick()" /> <input type="button"
								id="goodsCaiNum" name="" class="cai-btn" value="踩（0）"
								onclick="caiClick()" />
						</div>
					</div>
			</div>
		</div>
		<!--介绍+留言-->
		<div class="detail-desc">
			<!--tabs-->
			<div class="tabs">
				<ul>
					<li class="intro active">宝贝介绍</li>
					<li class="mes">用户留言</li>
				</ul>
			</div>
			<div class="tabs-content">
				<!--宝贝介绍-->
				<div class="mode-block introduce active">
					<p id="goodsDes" class="goodsDes">这里是一些详细介绍哦
						这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦
					</p>
				</div>
				<!--留言-->
				<div class="mode-block message">
					<ul id="messageBox" class="item-ul">
						
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- 链接js文件 -->
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
	<script type="text/javascript"
		src="platform/js/goodsDetail/goodsDetail.js"></script>
</body>
</html>
