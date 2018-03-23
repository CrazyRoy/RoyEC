<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="link/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="module/css/login/login_index.css">

	<script src="link/jquery-2.1.4.min.js"></script>
	<script src="link/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript">
		function entersearch() {
			var event = window.event || arguments.callee.caller.arguments[0];
			if (event.keyCode == 13) {
				enter();
			}
		}
		function enter() {
			var parame = {};
			parame.userAccount = $('#OPERATOR').val();
			parame.userPwd = $('#PASSWORD').val();
			parame.type = "2";
			
			$.ajax({
			  url:'userController/login.do',
			  data:parame,
			  success:function(resultJson){
				  var resultCode = resultJson.resultCode;
				  var resultMessage = resultJson.resultMessage;
				  if(resultCode=="0000"){
					  window.location.href = "module/index_home.jsp";
				  }else{
					  alert(resultMessage);
				  }
			  }
			});
		}
	</script>
	
  </head>
  
  <body>
	  <div id="container">
	  	<div id="content">
	  		<img id="backgroundImg" src="module/img/login/bg2.jpg">
	  		<div id="formContent">
	  			<div id="permitLeft">
	  				<img src="module/img/login/timg.png" style="width: 100%;height: 100%;">
	  			</div>
	  			<div id="permitLine">
	  				<img src="module/img/login/line.png" style="width: 100%;height: 100%;">
	  			</div>
	  			<div id="fm">
	  				<div id="logo">
	  					<div id="leaf"><img src="module/img/login/leaf.png" style="width: 100%;height: 120%;"></div>
	  					<div id="font"><img src="module/img/login/logo3.png" style="width: 100%;height: 160%;"></div>
	  				</div>
	  				<div id="login">
	  					<input id="OPERATOR" type="text" class="form-control" placeholder="请输入登录名" aria-describedby="basic-addon1">
	  					<input id="PASSWORD" onkeydown="entersearch()" type="password" class="form-control" placeholder="请输入密码" aria-describedby="basic-addon1">
	  				</div>
	  				<div id="button">
	  					<button type="button" onclick="enter()" class="btn btn-primary btn-lg btn-block">登录</button>
	  				</div>
	  			</div>
	  		</div>
	  	</div>
	  </div>
  </body>
</html>
