<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css" href="platform/css/home.css" />
<title>CompusEC</title>
</head>
<body>
	<!--内容居中-->
	<div class="w content indexContent">
		<!-- 搜索框 -->
		<div class="search clearfix">
			<button class="search_btn" onclick="searchClick()">搜索</button>
			<input id="search_input" class="search_input" type="text"
				class="search_input" placeholder="输入标题关键字..." />
		</div>
		<!--分类+轮播+发布-->
		<div class="clearfix topContent">
			<!-- 分类 -->
			<div class="sh-category-box fl">
				<ul id="category_list" class="sh-category-list">
				</ul>
			</div>
			<!-- 轮播 -->
			<div class="index-slider col">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="platform/img/banner1.png" alt="...">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="platform/img/banner2.png" alt="...">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="platform/img/banner3.png" alt="...">
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img src="platform/img/banner4.png" alt="...">
							<div class="carousel-caption"></div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
			<!-- 发布 -->
			<div class="idle-start">
				<div class="extend pub-overlay-btn">
					<div class="mod left" onClick="postGoods()">
						<a> <span class="main-title">发布闲置</span> <span
							class="subtitle">闲置还钱 快速出手</span>
						</a>
					</div>
					<div class="mod right" onClick="resellGoods()">
						<a> <span class="main-title">一键转卖</span> <span
							class="subtitle">转卖已买到的宝贝</span>
						</a>
					</div>
				</div>
			</div>
		</div>

		<!--猜你喜欢、全新闲置、同校交易-->
		<div class="news clearfix">
			<div class="new-items col">
				<div class="hd clearfix">
					<ul class="item-type">
						<li class="nav selected" name="guessData"><a
							href="javascript:void(0)">随机推荐</a></li>
						<li class="nav" name="newData"><a href="javascript:void(0)">全新闲置</a></li>
						<li class="nav" name="schoolData"><a
							href="javascript:void(0)">同校交易</a></li>
					</ul>
				</div>
				<div class="bd clearfix">
					<div class="item-list clearfix">
						<div class="reload" onclick="changeGoods()">
							<b></b> "换一批"
						</div>
						<ul class="clearfix">

						</ul>
					</div>
				</div>
			</div>
		</div>

		<!--分割线-->
		<div class="separa clearfix">
			<div class="ftit_inner_left"></div>
			<h3>品质专区</h3>
			<div class="ftit_inner_right"></div>
		</div>

		<!--各种分类板块-->
		<div class="floors">
			<!-- 数码控 -->
			<div id="type_top" class="floor clearfix">
				<!--数码控-->
				<div class="digitals">
					<!--头部-->
					<div class="box_hd clearfix">
						<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>
						<h3 class="box_tit fl">
							<span>数码控</span>
						</h3>
						<a class="box_subtit" href="platform/category.jsp?mainId=1">引领高科技
							<span class="box_subtit_arrow"></span>
						</a>
					</div>
					<!--内容-->
					<div class="box_bd">
						<div class="digitals_list_wrapper clearfix">
							<ul class="digitals_list">
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- 潮流生活+二手图书+热门榜 -->
			<div class="floor smalls clearfix">
				<!--潮流生活-->
				<div id="type_center" class="life small">
					<!--头部-->
					<div class="box_hd clearfix">
						<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>
						<h3 class="box_tit fl">
							<span>潮流生活</span>
						</h3>
						<a class="box_subtit" href="platform/category.jsp?mainId=1">发现优质好物
							<span class="box_subtit_arrow"></span>
						</a>
					</div>
					<!--内容-->
					<div class="box_bd">
						<ul class="life_list clearfix">
						</ul>
					</div>
				</div>
				<!--热门榜-->
				<div class="hot small">
					<!--头部-->
					<div class="box_hd clearfix">
						<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>
						<h3 class="box_tit fl">
							<span>热门榜</span>
						</h3>
						<a class="box_subtit" href="platform/category.jsp?mainId=1">更多热门
							<span class="box_subtit_arrow"></span>
						</a>
					</div>
					<!--内容-->
					<div class="box_bd hot_tab EC_hot_tab">
						<div class="hot_tab_content EC_hot_tab">
							<ul class="hot_list clearfix">
							</ul>
						</div>
					</div>
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
<script type="text/javascript" src="platform/js/basic.js"></script>
<script type="text/javascript" src="platform/js/home.js"></script>
</html>