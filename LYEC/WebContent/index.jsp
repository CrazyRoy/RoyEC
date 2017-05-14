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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- css外联样式 -->
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>CompusEC</title>
</head>

<body>
	<!--mask 遮罩-->
	<!-- 	<div id="mask">
		<div id="loginBox">
			<span id="login_close" onclick="hiddenLoginBox()">X</span>
		</div>
	</div> -->

	<!--  顶部 header  -->
	<div class="header navbar-fixed-top">
		<div class="content clearfix">
			<div class="logo mt24">
				<a href="#" target="_self" class="ec-a" title="校淘">校淘</a>
			</div>
			<div class="nav">
				<div class="nav-menu">
					<ul>
						<li class="m-home active" name="home.jsp"><a
							href="home.jsp" target="centerPage">首页</a></li>
						<li class="m-app" name="category.jsp?subId=1"><a
							href="category.jsp?subId=1" target="centerPage">手机二手</a></li>
						<li class="m-bicycle" name="category.jsp?subId=2"><a
							href="javascript:void(0);" target="centerPage">二手自行车</a></li>
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
	
	<!-- 首页 -->
	<div class="contentPage">
		<iframe id="iframepage" src="home.jsp" style="width:100%;" name="centerPage"
			scrolling="no" frameborder="0"></iframe>
	</div>

	<!-- 返回顶部 -->
	<span class="go-top" onclick="backToTop()"> </span>

</body>

<!-- 链接js文件 -->
<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
	var  timer1;
	window.onload = function(){
		
		timer1 = window.setInterval("reinitIframe()", 500); //定时调用开始
		
		/*document.getElementById("iframepage").src = "frame.html";*/
		/*document.getElementById("iframepage").onload = IframeLoadEND;*/
	};

	function reinitIframe(){  
		console.log(1);
		var iframe = document.getElementById("iframepage");  
		try{  
	  	 	var bHeight = iframe.contentWindow.document.body.scrollHeight;  
	   		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
	   	 	var height = Math.max(bHeight, dHeight);  
	   	 	console.log("bHeight==="+bHeight);
		    console.log("dHeight==="+dHeight);
		    console.log("height==="+height);
	    	iframe.height = height;  
		}catch (ex){}  
	}  
  
 	//完毕后干掉定时器  
 	/*
	function IframeLoadEND(){  
		console.log(2);
		var iframe = document.getElementById("iframepage");  
			try{  
			    window.clearInterval(timer1);  
			    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
			    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			    console.log("bHeight==="+bHeight);
			    console.log("dHeight==="+dHeight);
			    var height = Math.min(bHeight, dHeight);  
			    console.log("height==="+height);
			    iframe.height = height;  
			}catch (ex){}  
		    // 停止定时  
		    window.clearInterval(timer1);
		}   */
</script>
</html>

