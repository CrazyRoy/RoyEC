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
<title>CompusEC</title>
</head>
<body>

	<!--  顶部 header  -->
	<div class="header navbar-fixed-top">
		<div class="content">
			<div class="logo mt24">
				<a href="#" target="_self" class="ec-a" title="校淘">校淘</a>
			</div>
			<div class="nav">
				<div class="nav-menu">
					<ul>
						<li class="m-home" name="index.jsp"><a
							href="javascript:void(0);">首页</a></li>
						<li class="m-app" name="category.jsp?subId=1"><a
							href="javascript:void(0);" target="contentframe">手机二手</a></li>
						<li class="m-bicycle" name="category.jsp?subId=2"><a
							href="javascript:void(0);" target="contentframe">图书馆</a></li>
					</ul>
				</div>
				<div class="nav-manage">
					<span class="nav-manage-sp"></span>
					<ul>
						<li><a href="release.jsp" class="pub-overlay-btn">发布闲置</a></li>
						<li class="my-idle-li"><a href="myProduct.jsp"
							class="my-idle-link">我的闲置 <img class="icon-up"
								src="img/icon_up.png" /> <img class="icon-down"
								src="img/icon_down.png" />
						</a></li>
					</ul>
				</div>
			</div>
			<div class="search">
				<button onclick="">搜索</button>
				<input type="text" name="" class="search_input" placeholder="请输入..." />
			</div>
		</div>
	</div>

	<!--中间内容-->
	<div class="page">
		<!--内容居中-->
		<div class="w content categoryContent">
			<!-- 分类导航 -->
			<div class="ec_category clearfix">
				<div class="ec_hd ec_hd_qrcode">

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
			</div>
			<!-- 内容 -->
			<div class="category_bd">
				<ul class="item-list clearfix">
				</ul>
			</div>
		</div>
	</div>

	<!-- 链接js文件 -->
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/category.js"></script>

</body>
</html>