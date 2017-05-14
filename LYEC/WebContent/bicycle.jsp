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
		<title>二手自行车</title>
	</head>
	<body>
		
		<!-- 链接js文件 -->
		<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/base.js" ></script>

	</body>
</html>