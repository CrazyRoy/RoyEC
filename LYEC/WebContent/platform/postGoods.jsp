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
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="platform/css/basic.css" />
		<link rel="stylesheet" type="text/css" href="platform/css/index.css" />
		<link rel="stylesheet" type="text/css" href="link/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="platform/css/postGoods.css"/>
	</head>

	<body>
		<div class="goodsInfo">
			<h2>发布闲置</h2>
			<div class="row">
				<div class="input-group">
	  				<span class="input-group-addon">商品标题</span>
	  				<input id="goodsTitle" type="text" class="form-control" placeholder="请在这里输入商品标题." aria-describedby="basic-addon1">
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label>商品描述</label>
					<textarea id="goodsDes" class="form-control" rows="5" placeholder="请这里输入商品描述."></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 small-input">
					<div class="input-group">
		  				<span class="input-group-addon">现价</span>
		  				<input id="resalePrice" type="text" class="form-control" placeholder="例如 23.5" aria-describedby="basic-addon1">
					</div>
				</div>
				<div class="col-md-6 small-input last">
					<div class="input-group">
						<span class="input-group-addon">原价</span>
						<input type="text" class="form-control" name="telephone" id="originPrice" placeholder="例如 25.8">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 small-input">
					<div class="input-group">
						<span class="input-group-addon">成色</span>
						<select name="condition" id="condition" class="form-control">
							<option value="一成新" selected="selected">一成新</option>
							<option value="二成新">二成新</option>
							<option value="三成新">三成新</option>
							<option value="四成新">四成新</option>
							<option value="五成新">五成新</option>
							<option value="六成新">六成新</option>
							<option value="七成新">七成新</option>
							<option value="八成新">八成新</option>
							<option value="九成新">九成新</option>
						</select>
					</div>
				</div>
				<div class="col-md-6 small-input last">
					<div class="input-group">
						<span class="input-group-addon">类别</span>
						<select name="category" id="category" class="form-control">
							<option value="类别一" selected="selected">类别一</option>
							<option value="类别二">类别二</option>
							<option value="类别三">类别三</option>
							<option value="类别四">类别四</option>
							<option value="类别五">类别五</option>
							<option value="类别六">类别六</option>
							<option value="类别七">类别七</option>
							<option value="类别八">类别八</option>
							<option value="类别九">类别九</option>
						</select>
					</div>
				</div>
				
			</div>
			<!--地址
			<div class="row">
				<div class="input-group">
	  				<span class="input-group-addon">地址</span>
						<input id="goods-addreess" type="text" class="form-control" placeholder="请在这里输入您的地址." aria-describedby="basic-addon1">
				</div>
			</div>-->
			<div class="row clearfix">
				<div class="form-group">
					<label class="image-label">商品图片集：</label>
					<img onclick="imgBtnClick()" class="btn-upload" />
					<form id="upload_form" method="post" enctype="multipart/form-data" style="display: none;">
						<input id="add_file" type="file" onchange="preView(this)" />
					</form>
					<div id="upload_div" style="float: left; width: 100%; margin: 20px 0;">
						<!-- <div>
							<img alt="" src="">
							<input type="button" value="下载" class="btn btn-primary" />
							<input type="button" value="删除" class="btn btn-primary" />
						</div> -->
					</div>
				</div>
			</div>
			<div class="row">
				<button id="postButton" onClick="postGoods()" type="button" class="postBtn">发&nbsp;布&nbsp;闲&nbsp;置</button>
			</div>
		</div>
		
		<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="platform/js/postGoods/postGoods.js"></script>
	</body>
</html>