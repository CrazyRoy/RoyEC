<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String addressContent = request.getParameter("addressContent");
	String goodsId = request.getParameter("goodsId");
	System.out.println("test:" + goodsId);
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
	href="platform/css/orderConfirm.css" />

<title>订单确认</title>
</head>
<body>
	<div class="alignCenter">
		<!-- 隐藏标签 -->
		<input id="addressContent" value="<%=addressContent%>" type="text"
			style="display: none;" />
		<input id="goodsId" value="<%=goodsId%>" type="text"
			style="display: none;" />
		<div class="page-header clearfix">
			<span>订单确认</span>
		</div>
		<div class="orderContent">
			<div class="address-content">
				<span class="titleLabel">收货人信息</span>
				<p id="address-info" class="address-info">我的合法i飞洒发生的解法接哦分的解放咔叽</p>
			</div>
			<div class="goods-content">
				<span class="titleLabel">闲置信息</span>
				<ul class="order-list">
					<li class="item clearfix">
						<div class="item-pic">
							<img id="goods_picture" class="product_pic" src="platform/img/girlC.jpg" />
						</div>
						<div class="title">
							<p id="goods_title" class="product_title">
								我是商品我是商品我是商品我是商品我是商品我是商品我是商品我是商品我是商品我是商品</p>
						</div>
						<div class="prices">
							<span class="resale_price"> 现价：<span id="goods_resale_price" class="titleMark">￥1092</span> </span>
						</div>
						<div class="prices">
							<span id="goods_orginal_price" class="orginal_price"> 原价：￥2324 </span>
						</div>
						<div class="condition">
							<span id="goods_condition" class="product_condition"> 八成新 </span>
						</div>
					</li>
					<div class="info-footer">
							<p class="order-price">应付总额： <span id="goods_order_price" class="order-price titleMark">￥1899.00</span></p>
							<p id="order-address" class="order-address">寄送至：<span id="address-des"></span>&nbsp;收货人：<span id="address-name"></span>&nbsp;<span id="address-phone"></span></p>
					</div>
				</ul>
			</div>
			<div class="order-footer clearfix">
				<button type="button" id="next-step"
						class="next-step btn btn-large glyphicon glyphicon-ok fr" onclick="submitOrder()">
						确认下单</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
  	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
	<script type="text/javascript" src="platform/js/orderConfirm/orderConfirm.js"></script>
</body>
</html>