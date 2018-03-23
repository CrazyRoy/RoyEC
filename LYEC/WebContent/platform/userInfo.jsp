<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String USERID = (String) request.getSession()
			.getAttribute("USERID");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="platform/css/basic.css" />
<link rel="stylesheet" type="text/css" href="platform/css/userInfo.css" />

</head>

<body>
	<div class="w person-info">
		<div class="page-header clearfix">
			<span>个人信息</span>
			<!-- 隐藏标签 -->
			<input id="userId" value="<%=USERID %>" style="display: none;" />
		</div>
		<div class="person-content clearfix">
			<div class="model leftDiv clearfix">
				<div class="imgDiv">
					<img id="userPic" class="headImg" src="platform/img/photo_default.jpeg" alt="" /> <input
						class="change-imgBtn" onclick="initFile()" type="button"
						value="更换头像图片" class="btn btn-primary" />
					<form id="upload_form" method="post" enctype="multipart/form-data"
						style="display: none;">
						<input id="add_file" type="file" onchange="preView(this)" />
					</form>
					<div style="display: none;" >
						<input id="userPicName" type="text" />
						<input id="userPicPath" type="text" />
					</div>
				</div>
				<div class="info clearfix">
					<div class="row clearfix">
						<div>
							<label for="userName">用户名</label> <input id="userAccount" type="text"
								name="userName" value="" placeholder="输入用户名" />
						</div>
					</div>
					<div class="row clearfix">
						<div>
							<label for="userName">真实姓名</label> <input id="userRealName" type="text"
								name="userName" value="" placeholder="输入真实姓名" />
						</div>
					</div>
					<div class="row clearfix">
						<div>
							<label for="userName">用户邮箱</label> <input id="userEmail" type="text"
								name="userName" value="" placeholder="输入用户邮箱" />
						</div>
					</div>
				</div>
			</div>
			<div class="model rightDiv">
				<div class="row clearfix">
					<div>
						<label for="userName">手机号</label> <input id="userPhoneNo" type="text"
							name="userName" value="" placeholder="输入手机号" />
					</div>
				</div>
				<div class="row clearfix">
					<div>
						<label for="userName">性别</label> <input class="selectBtn"
						type="radio" name="sex" value="0" checked="checked"> 男 <input
						class="selectBtn" type="radio" name="sex" value="1" />女
					</div>
				</div>
				<div class="row clearfix">
					<div>
						<label for="userName">年龄</label> <input id="userAge" type="text" name="userName"
							value="" placeholder="输入年龄" />
					</div>
				</div>
				<div class="row clearfix">
					<div>
						<label for="school">学校</label> 
						<select name="school" id="userSchoolInfo" class="schoolSelect">
							<option value="cqut" selected="selected">重庆理工大学</option>
							<option value="cqgs">重庆工商大学</option>
							<option value="cqdx">重庆大学</option>
							<option value="xndx">西南大学</option>
							<option value="cqwl">重庆文理学院</option>
							<option value="cqsf">重庆师范大学</option>
						</select>
					</div>
				</div>
				<div class="row clearfix">
					<div>
						<label for="schoolNo">学号</label> <input id="userSchoolNo" type="text" name="userSchoolNo"
							value="" placeholder="输入您的学号" />
					</div>
				</div>
				<div class="row clearfix">
					<div>
						<label for="schoolNo">居住地址</label> <input id="userAddress" type="text" name="userAddress"
							value="" placeholder="输入您的居住地址" />
					</div>
				</div>
			</div>
			
			<div class="row clearfix">
				<div class="actions clearfix">
					<button type="button" class="btn btn-success" onclick="editUserInfoSave()">保存个人信息</button>
					<!-- <button type="button" class="btn btn-info">点我编辑信息</button> -->
				</div>
			</div>
		</div>
	</div>

	<!-- 链接js文件 -->
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="platform/js/userInfo/userInfo.js"></script>
</body>
</html>