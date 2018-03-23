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
<link rel="stylesheet" type="text/css" href="platform/css/aboutUs.css" />
<title>关于我们</title>
</head>
<body>
	<div class="alignCenter">
		<div class="page-header clearfix">
			<span>关于我们</span>
		</div>
		<div class="aboutusContent">
			<div class="about-head clearfix">
				<div class="head-logo">
					<div class="aboutusImg"></div>
				</div>
				<div class="head-title">
					<span class="aboutusText1">---------牛奶牛--------- </span> <span
						class="aboutusText2">让我眼中的奶牛成为你手中的牛奶 </span>
				</div>
			</div>
			<div class="detail-des">
				<span class="aboutus-detailText">&nbsp;&nbsp;随着Internet爆炸式的发展，电子商务在国内也如雨后春笋般快速发展起来。在校大学生都有体会，我们使用的衣服、电子产品、书籍等物品更新换代的速度很快，这些还有价值的物品丢掉会觉得十分浪费但搁置又很占空间。很多学生就想转手卖掉这些闲置的物品，但联系需要这些物品的买家也是一个很大的难题。面对这一现象，牛奶牛的出现是为了借助交易成本比较低的网络为大家开发一个校园二手交易平台，能够为广大在校大学生提供帮助和服务。</span>
					<span class="aboutus-detailText">&nbsp;&nbsp;现今，网上购物已经日渐普及渗透到人们生活的方方面面，在校大学生二手商品交易市场是一个潜在的大机遇。每年都有大批的毕业生和新生更替，校园二手交易平台系统牛奶牛的实现为在校大学生提供了一个高效便捷的供需平台，在校大学生们正好有着相似的生活环境和学习需要，大家可以在网站上搜索自己需要的商品，也可以把自己不需要的闲置出来的物品信息挂在网站上，寻找需要它的新主人，这一做法让彼此达到双赢。</span>
					<span class="aboutus-detailText">&nbsp;&nbsp;面对广大在校大学生服务的校园电子商务平台牛奶牛是建立在学校校园网的基础之上的。它不仅能满足在校大学生的学习生活需要，还能提供一些兼职的岗位方便有需要的同学。校园转手交易平台的主要用户是学生和老师，而学生和老师的学习生活离不开计算机的使用。这使得校园转手交易平台的开发符合需求，因此，这一平台的实现具有很重要的现实意义。</span>
					<span class="aboutus-detailText">&nbsp;&nbsp;校园转手交易平台很早就被提出来过，但在学校内并没有蓬勃发展起来。比较常见的校园转手交易平台主要有两种模式：一种是只提供了交易物品信息的平台，不参与双方的交易；另一种是专门的交易平台，但该网站的安全保障性不高，会有较大的安全隐患，这会给交易双方带来较大的损失。因此，各大校园二手交易平台一直没有活跃起来。于是，一种只面向校园的转手交易平台便出现了，以此来解决各大高校师生的二手商品交易的需求。</span>
			</div>
			<div class="about-bottom clearfix">
			<div class="bottom-call">
			<span class="call-text">联系我们</span>
			</div>
			<div class="call-des">
			<span class="call-text">邮&nbsp;箱：1043208487@qq.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;话：18875021824</span>
			<span class="call-text">地&nbsp;址:重庆市巴南区红光大道69好重庆理工大学菊轩M618&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系人：李燕女士</span>
			</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
</body>
</html>