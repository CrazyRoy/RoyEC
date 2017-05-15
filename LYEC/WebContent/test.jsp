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


<!-- css外联样式 -->


<title>二手手机</title>
</head>

<body>
 <div style="background-color:red">
 zzzzzzz
</div>
</body>
</html>