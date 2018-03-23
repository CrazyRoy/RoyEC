<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	// 获取页面传递过来的值
	String name = request.getParameter("name"); // 名称
	String LIST_ID = request.getParameter("LIST_ID"); // 目录ID
	String PARENT_ID = request.getParameter("PARENT_ID");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="platform/css/basic.css" />
<link rel="stylesheet" type="text/css" href="platform/css/category.css" />
</head>
<body>
	<!--内容居中-->
	<div class="alignCenter">
		<div class="page-header clearfix">
			<span id="page-name">闲置商品</span>
		</div>
		<!-- 隐藏标签 -->
		<input id="name" value="<%=name%>" style="display: none;">
		<input id="LIST_ID" value="<%=LIST_ID%>" style="display: none;">
		<input id="PARENT_ID" value="<%=PARENT_ID%>" style="display: none;">
		<!-- 分类导航 -->
		<div class="category-item-list">
			<ul id="category-list" class="category-list clearfix">
			</ul>
		</div>
		<!-- 内容 -->
		<div class="category_bd">
			<ul class="item-list clearfix">
			</ul>
			<!--分页-->
			<div id="page_div" class="page-div clearfix">
				<ul class="pagination pagination-lg">
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
	</div>
	<!-- 链接js文件 -->
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
	<script type="text/javascript" src="platform/js/category.js"></script>
</body>
</html>