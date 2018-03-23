<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object USERSTATUSobj = request.getSession().getAttribute("USERSTATUS");
String USERSTATUS = "";
if(USERSTATUSobj==null){
}else{
	USERSTATUS = USERSTATUSobj.toString();
}
System.out.println("USERSTATUS===================="+USERSTATUS);
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
<link rel="stylesheet" type="text/css" href="platform/css/index.css" />
<title>CompusEC</title>
</head>
<body>
<!-- 隐藏标签 -->
<input id="USERID" value="${sessionScope.USERID}" style="display: none;">
<input id="USERACCOUNT" value="${sessionScope.USERACCOUNT}" style="display: none;">
<input id="USERNAME" value="${sessionScope.USERNAME}" style="display: none;">
<input id="USERSTATUS" value="${sessionScope.USERSTATUS}" style="display: none;">

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
            <span class="logo" style="background-size: 50px 50px;"></span>
            <span class="navbar-brand">牛奶牛</span>
            <span class="navbar-brand desc">让我眼中的奶牛成为你手中的牛奶.</span>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav menu-list">
                <li class="active" id="nav-main"><a href="platform/index.jsp">首页</a></li>
                <li id="nav-blank"></li>
                <li id="nav-forum"><a href="javascript:void(0)" onclick="publishGoods()">发布闲置</a></li>
                <li id="nav-goods"><a href="javascript:void(0)" onclick="myIdle()">我的闲置</a></li>
                <li id="nav-blank"><a href="javascript:void(0)"></a></li>
                <li id="nav-about"><a href="javascript:void(0)" onclick="aboutUS()">关于我们</a></li>
            </ul>
            <ul id="user-info" class="nav navbar-nav navbar-right">
            </ul>
        </div>
    </div>
</nav>

<!-- 登录框 -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginLabel">
    <div class="modal-dialog myModelWidth" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginLabel">Log in</h4>
            </div>
               <div class="modal-body">
                   <div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">账号</span>
		  				<input id="input-login-userName" type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">密码</span>
		  				<input id="input-login-password" type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
					</div>
               </div>
               <div class="modal-footer">
                   <button id="loginButton" onClick="loginIn()" type="button" class="loginBtn">登&nbsp;&nbsp;&nbsp;陆</button>
               </div>
        </div>
    </div>
</div>

<!-- 注册框 -->
<div class="modal fade" id="registe" tabindex="-1" role="dialog" aria-labelledby="loginLabel">
    <div class="modal-dialog myModelWidth" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginLabel">Sign up</h4>
            </div>
               <div class="modal-body">
                   <div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">用户名</span>
		  				<input id="input-registe-userAccount" type="text" class="form-control" placeholder="Account" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">用户密码</span>
		  				<input id="input-registe-password" type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">确认密码</span>
		  				<input id="input-registe-confirm-password" type="password" class="form-control" placeholder="Confirm" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">真实姓名</span>
		  				<input id="input-registe-userName" type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">电子邮箱</span>
		  				<input id="input-registe-email" type="text" class="form-control" placeholder="Email" aria-describedby="basic-addon1">
					</div>
               </div>
               <div class="modal-footer">
                   <button id="loginButton" onClick="registe()" type="button" class="loginBtn">注&nbsp;&nbsp;&nbsp;册</button>
               </div>
        </div>
    </div>
</div>

<!-- 首页 -->
<div class="contentPage">
	<iframe id="iframepage" src="platform/home.jsp" style="width:100%;" name="centerPage"
		scrolling="no" frameborder="0" onload="reinitIframe()"></iframe>
</div>

<!-- 返回顶部 -->
<span class="go-top" onclick="backToTop()"> </span>

<!-- 链接js文件 -->
<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="platform/js/basic.js"></script>
<script type="text/javascript" src="platform/js/index.js"></script>
<script type="text/javascript">
	var  timer1;
	$(document).ready(function(){
		/* timer1 = window.setInterval("reinitIframe()", 500); //定时调用开始 */ 
	});
	
	
</script>
</body>
</html>

