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
<link rel="stylesheet" type="text/css" href="css/basic.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>CompusEC</title>
</head>
<body>

<!-- <nav class="navbar navbar-inverse navbar-fixed-top" id="myNav">
  	<div class="container-fluid alignCenter clearfix">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">LYEC</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Sign in</a></li>
        <li><a href="#">Sign up</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav> -->

<!--导航条-->
<nav id="myNav" class="navbar navbar-inverse navbar-fixed-top">
    <div class="container container-fluid alignCenter">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand">LYEC LOGO</span>
            <span class="navbar-brand desc">ThERE IS LYEC STORE</span>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active" id="nav-main"><a href="#">首页</a></li>
                <li id="nav-blank"><a href="javascript:void(0)"></a></li>
                <li id="nav-forum"><a href="javascript:void(0)">发布闲置</a></li>
                <li id="nav-goods"><a href="javascript:void(0)">我的闲置</a></li>
                <li id="nav-blank"><a href="javascript:void(0)"></a></li>
                <li id="nav-about"><a href="javascript:void(0)" data-toggle="modal" data-target="#myabout">关于</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            	<%-- <c:if test="${sessionScope.user == null }"> --%>
            		<li><a href="#" data-toggle="modal" data-target="#login">Sign in</a></li>
                	<li><a href="#" data-toggle="modal" data-target="#register">Sign up</a></li>
            	<%-- </c:if>
                <c:if test="${sessionScope.user != null }">
                	<li><a href="javascript:void(0)" onclick="userManager()">欢迎你！: ${sessionScope.user.userName}</a></li>
                	<li><a href="${pageContext.request.contextPath}/userInfo/logout.do">退出</a></li>
                </c:if> --%>
               
            </ul>
        </div>
    </div>
</nav>

<!-- 登录框 -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content myModelWidth">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginLabel">Log in</h4>
            </div>
           <%-- <form action="${pageContext.request.contextPath}/userInfo/login.do" method="post"> --%>
               <div class="modal-body">
                   <div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">账号</span>
		  				<input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">密码</span>
		  				<input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary loginBtn">登陆</button>
                   <!-- <button type="reset" class="btn btn-danger">重置</button> -->
               </div>
         <!--   </form> -->
        </div>
    </div>
</div>

<!-- <div id="login" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"> -->
  <!-- <div class="modal-dialog modal-sm" role="document">
       <div class="modal-content myModelWidth">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h5 class="modal-title" id="myModalLabel">Log in</h5>
        </div>
        <div class="modal-body">
			<div class="input-group">
  				<span class="input-group-addon" id="basic-addon1">账号</span>
  				<input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
  				<span class="input-group-addon" id="basic-addon1">密码</span>
  				<input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
			</div>
        </div>
        
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary">提交更改</button>
        </div>
   	</div>
  </div> -->
<!-- </div> -->

<!--  顶部 header  -->
<!--<div class="header navbar-fixed-top">
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
						<li class="m-app" name="category.jsp?subId=1"><a
						href="category.jsp" target="centerPage">手机二手</a>
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
</div> -->

<!-- 首页 -->
<div class="contentPage">
	<iframe id="iframepage" src="home.jsp" style="width:100%;" name="centerPage"
		scrolling="no" frameborder="0" onload="reinitIframe()"></iframe>
</div>

<!-- 返回顶部 -->
<span class="go-top" onclick="backToTop()"> </span>

<!-- 链接js文件 -->
<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
	var  timer1;
	$(document).ready(function(){
		timer1 = window.setInterval("reinitIframe()", 500); //定时调用开始 
	});

	// 获取iframe高度
	function reinitIframe(){  
		console.log(1);
		var iframe = document.getElementById("iframepage");  
		try{  
	  	 	var bHeight = iframe.contentWindow.document.body.offsetHeight;  
	   		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
	   	 	var height = Math.max(bHeight, dHeight);  
	    	iframe.height = height;  
		}catch (ex){}  
	}  
</script>
</body>
</html>

