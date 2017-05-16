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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- css外联样式 -->
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/basic.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>CompusEC</title>
</head>
<body>

		<!--内容居中-->
		<div class="w content indexContent">
			<!--分类+轮播+发布-->
			<div class="clearfix topContent">
				<!-- 分类 -->
				<div class="sh-category-box fl">
					<ul class="sh-category-list">
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
								<img src="img/banner1.png" alt="...">
								<div class="carousel-caption"></div>
							</div>
							<div class="item">
								<img src="img/banner2.png" alt="...">
								<div class="carousel-caption"></div>
							</div>
							<div class="item">
								<img src="img/banner3.png" alt="...">
								<div class="carousel-caption"></div>
							</div>
							<div class="item">
								<img src="img/banner4.png" alt="...">
								<div class="carousel-caption"></div>
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
					<!--<a href="#">-->
					<!--<img src="http://gw.alicdn.com/tps/i3/TB19zkLJXXXXXc3XFXXhXAC0FXX-640-247.jpg" alt="闲鱼"/>-->
					<!--</a>-->
				</div>
				<!-- 发布 -->
				<div class="idle-start">
					<div class="extend pub-overlay-btn">
						<div class="mod left">
							<a> <span class="main-title">发布闲置</span> <span
								class="subtitle">闲置还钱 快速出手</span>
							</a>
						</div>
						<div class="mod right">
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
							<li class="nav selected"><a href="javascript:void(0)">猜你喜欢</a>
							</li>
							<li class="nav"><a href="javascript:void(0)">全新闲置</a></li>
							<li class="nav" id="school"><a href="javascript:void(0)">同校交易</a>
							</li>
						</ul>
					</div>
					<div class="bd clearfix">
						<div class="item-list clearfix">
							<div class="reload">
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
				<div class="floor clearfix">
					<!--数码控-->
					<div class="digitals">
						<!--头部-->
						<div class="box_hd clearfix">
							<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>
							<h3 class="box_tit fl">
								<span>数码控</span>
							</h3>
							<a class="box_subtit" targe="_blank" href="category.jsp?mainId=1">引领高科技
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
					<div class="life small">
						<!--头部-->
						<div class="box_hd clearfix">
							<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>
							<h3 class="box_tit fl">
								<span>潮流生活</span>
							</h3>
							<a class="box_subtit" targe="_blank" href="#">发现优质好物 <span
								class="box_subtit_arrow"></span>
							</a>
						</div>
						<!--内容-->
						<div class="box_bd">
							<ul class="life_list clearfix">
							</ul>
						</div>
					</div>
					<!--二手图书-->
					<div class="book small" style="display:none">
						<!--头部-->
						<div class="box_hd clearfix">
							<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>
							<h3 class="box_tit fl">
								<span>二手图书</span>
							</h3>
							<a class="box_subtit" targe="_blank" href="#">畅游知识海洋 <span
								class="box_subtit_arrow"></span>
							</a>
						</div>
						<!--内容-->
						<div class="box_bd sup_tab EC_sup_tab">
							<div class="sup_page EC_sup_page active">
								<a href="javascrip:;" class="sup_page_lk" target="_blank"
									clstag="Roy01">
									<p class="sup_desc">职场办公可选的五千以下笔记本</p> <img
									class="sup_img EC_sup_img"
									src="https://img13.360buyimg.com/mobilecms/s220x220_jfs/t3556/223/2158676156/110226/59267230/584b5678Nbc9f1e70.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img14.360buyimg.com/mobilecms/s220x220_jfs/t3145/190/3389466467/45053/86b60c3d/57f1bf88N19a974eb.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img10.360buyimg.com/mobilecms/s220x220_jfs/t4000/275/1326147060/182703/f8ccde56/58732b1fNef5f4249.jpg"
									alt="">
								</a>
								<div class="sup_sep"></div>
								<a href="javascrip:;" class="sup_page_lk" target="_blank"
									clstag="Roy02">
									<p class="sup_desc">职场办公可选的五千以下笔记本</p> <img
									class="sup_img EC_sup_img"
									src="https://img13.360buyimg.com/mobilecms/s220x220_jfs/t3556/223/2158676156/110226/59267230/584b5678Nbc9f1e70.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img14.360buyimg.com/mobilecms/s220x220_jfs/t3145/190/3389466467/45053/86b60c3d/57f1bf88N19a974eb.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img10.360buyimg.com/mobilecms/s220x220_jfs/t4000/275/1326147060/182703/f8ccde56/58732b1fNef5f4249.jpg"
									alt="">
								</a>
							</div>
							<div class="sup_page EC_sup_page">
								<a href="javascrip:;" class="sup_page_lk" target="_blank"
									clstag="Roy01">
									<p class="sup_desc">畅玩各种手游，国产大内存千元机推荐</p> <img
									class="sup_img EC_sup_img"
									src="https://img11.360buyimg.com/mobilecms/s220x220_jfs/t4276/257/2416766721/125228/ba72a107/58d1d078N20e18b62.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img12.360buyimg.com/mobilecms/s220x220_jfs/t3046/24/1238189721/263988/a3d4f2f1/57c8d880Nd90eb247.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img13.360buyimg.com/mobilecms/s220x220_jfs/t4639/54/2243107564/102382/ecc71603/58eca38eN714eaaef.jpg"
									alt="">
								</a>
								<div class="sup_sep"></div>
								<a href="javascrip:;" class="sup_page_lk" target="_blank"
									clstag="Roy01">
									<p class="sup_desc">畅玩各种手游，国产大内存千元机推荐</p> <img
									class="sup_img EC_sup_img"
									src="https://img11.360buyimg.com/mobilecms/s220x220_jfs/t4276/257/2416766721/125228/ba72a107/58d1d078N20e18b62.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img12.360buyimg.com/mobilecms/s220x220_jfs/t3046/24/1238189721/263988/a3d4f2f1/57c8d880Nd90eb247.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img13.360buyimg.com/mobilecms/s220x220_jfs/t4639/54/2243107564/102382/ecc71603/58eca38eN714eaaef.jpg"
									alt="">
								</a>
							</div>
							<div class="sup_page EC_sup_page">
								<a href="javascrip:;" class="sup_page_lk" target="_blank"
									clstag="Roy01">
									<p class="sup_desc">用运动蓝牙耳机，畅享运动与音乐</p> <img
									class="sup_img EC_sup_img"
									src="https://img12.360buyimg.com/mobilecms/s220x220_jfs/t3382/252/1774809634/87545/f2100aed/5832cb14N18ad5edc.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img12.360buyimg.com/mobilecms/s220x220_jfs/t3382/252/1774809634/87545/f2100aed/5832cb14N18ad5edc.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img14.360buyimg.com/mobilecms/s220x220_jfs/t2833/270/173916494/95724/a3c2f348/57065592N84864cba.jpg"
									alt="">
								</a>
								<div class="sup_sep"></div>
								<a href="javascrip:;" class="sup_page_lk" target="_blank"
									clstag="Roy01">
									<p class="sup_desc">用运动蓝牙耳机，畅享运动与音乐</p> <img
									class="sup_img EC_sup_img"
									src="https://img12.360buyimg.com/mobilecms/s220x220_jfs/t3382/252/1774809634/87545/f2100aed/5832cb14N18ad5edc.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img12.360buyimg.com/mobilecms/s220x220_jfs/t3382/252/1774809634/87545/f2100aed/5832cb14N18ad5edc.jpg"
									alt=""> <img class="sup_img EC_sup_img"
									src="https://img14.360buyimg.com/mobilecms/s220x220_jfs/t2833/270/173916494/95724/a3c2f348/57065592N84864cba.jpg"
									alt="">
								</a>
							</div>

							<a href="javascript:;" class="sup_btn sup_btn_pre EC_sup_btn_pre">
								<i><</i>
							</a> <a href="javascript:;"
								class="sup_btn sup_btn_next EC_sup_btn_next"> <i>></i>
							</a>

							<ul class="sup_ind">
								<li class="sup_ind_item EC_sup_ind_item active"
									onclick="bookItemClick(this)"></li>
								<li class="sup_ind_item EC_sup_ind_item"
									onclick="bookItemClick(this)"></li>
								<li class="sup_ind_item EC_sup_ind_item"
									onclick="bookItemClick(this)"></li>
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
							<a class="box_subtit" targe="_blank" href="#">更多热门 <span
								class="box_subtit_arrow"></span>
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
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript" src="js/home.js"></script>
</html>