<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	href="platform/css/myOrder.css" />
<title>我的订单</title>
</head>
<body>
	<div class="alignCenter">
		<div class="page-header clearfix">
			<span>我的订单</span>
		</div>
		<div class="order-list-title">
			<div class="titles clearfix">
				<span class="title_img">订单号</span> <span class="title_des">商品标题</span>
				<span class="title_price">成交金额</span> <span class="title_condition">成交日期</span>
				<span class="title_actions">操作</span>
			</div>
		</div>
		<!-- 订单列表内容 -->
		<ul class="order-list">
		</ul>
		<!--分页-->
		<div id="page_div" class="page-div">
			<ul class="pagination pagination-lg clearfix">
				<li><a href="javascript:void(0);">&laquo;</a></li>
				<li class="active"><a href="javascript:void(0);">1</a></li>
				<li><a href="javascript:void(0);">2</a></li>
				<li><a href="javascript:void(0);">3</a></li>
				<li><a href="javascript:void(0);">4</a></li>
				<li><a href="javascript:void(0);">5</a></li>
				<li><a href="javascript:void(0);">&raquo;</a></li>
			</ul>
		</div>
	</div>

	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
	<script type="text/javascript"
		src="platform/js/myOrder/myOrder.js"></script>
</body>
</html>