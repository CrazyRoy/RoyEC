<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	// 获取页面传递过来的值
	String MAINID = request.getParameter("mainId");
	String SUBID = request.getParameter("subId");
	System.out.println("MAINID===========" + MAINID + "SUBID======"
			+ SUBID);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- css外联样式 -->
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css" href="css/category.css" />
<title>二手手机</title>
</head>

<body>
	<!--中间内容-->
	<div class="page">
		<!--内容居中-->
		<div class="w content categoryContent">
			<!-- 分类导航 -->
			<div class="ec_category clearfix">
				<div class="ec_hd ec_hd_qrcode clearfix">

					<!-- 隐藏标签 -->
					<input id="MAINID" value="<%=MAINID%>" style="display: none;">
					<input id="SUBID" value="<%=SUBID%>" style="display: none;">

					<h3 class="ec_tit">类别标题</h3>
					<div class="ec_qrcode">
						<div class="ec_qrcode_ico"></div>
					</div>

					<div class="ec_qrcode_item">
						<div class="ec_tags"></div>
					</div>
				</div>
				<!-- 内容 -->
			<div class="category_bd">
				<ul class="item-list clearfix">
				</ul>
			</div>
			</div>
		</div>
	</div>
	
	<!--底部 footer-->
	<div class="footer clearfix">
		<p class="copyright">Copyright © 2016-2017 www.cqut.com |
			计算机科学与工程学院 | 软件工程专业 | 版权所有</p>
	</div>
</body>
	<!-- 链接js文件 -->
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/category.js"></script>
</html>