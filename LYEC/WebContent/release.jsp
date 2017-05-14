<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		<link rel="stylesheet" type="text/css" href="link/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/base.css"/>
		<title>发布闲置</title>
	</head>
	<body>
		<!--mask 遮罩-->
		<div id="mask">
			<div id="loginBox">
				<span id="login_close" onclick="hiddenLoginBox()">X</span>
			</div>
		</div>
		
		<!--  顶部 header  -->
		<div class="header mt24">
			<div class="content">
    			<div class="logo mt24">
       			 <a href="#" target="_self" class="ec-a" title="校淘">校淘</a>
    			</div>
    			<div class="nav">
    				<div class="nav-menu">
    					<ul>
    						<li class="m-home">
    							<a href="#">首页</a>
    						</li>
    						<li class="m-app">
    							<a href="category.jsp" target="self">手机二手</a>
    						</li>
    						<li class="m-bicycle">
    							<a href="bicycle.jsp">二手自行车</a>
    						</li>
    					</ul>
    				</div>
    				<div class="nav-manage">
    					<span class="nav-manage-sp"></span>
    					<ul>
    						<li class="active">
    							<a href="release.jsp" class="pub-overlay-btn active">发布闲置</a>
    						</li>
    						<li class="my-idle-li">
    							<a href="myProduct.jsp" class="my-idle-link">我的闲置
    								<img class="icon-up" src="img/icon_up.png"/>
    								<img class="icon-down" src="img/icon_down.png"/>
    							</a>
    						</li>
    					</ul>
    				</div>
    			</div>
    			<div class="search">
        			<input type="text" name="" class="search_input" placeholder="请输入..."/>
        			<button onclick="">搜索</button>
   				 </div>
			</div>
		</div>
		
		<!--中间内容-->
		<div class="page">
			<!--内容居中-->
			<div class="w_percent content">
			</div>
		</div>
		
		<!--底部 footer-->
		<div class="footer">
			<p class="copyright"> Copyright © 2016-2017 www.cqut.com | 计算机科学与工程学院 | 软件工程专业 | 版权所有</p>
		</div>
		
		<!-- 返回顶部 -->
		<span class="go-top" onclick="backToTop()">
		</span>
		</body>
		
		<!-- 链接js文件 -->
		<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/base.js" ></script>

	</body>
</html>