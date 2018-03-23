<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>帐号管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="link/bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="link/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="link/bootstrap/css/bootstrap-table.css">
	
	<style type="text/css">
	.input-group button {
		margin-left: 5px;
		margin-bottom: 5px;
	}
	</style>
  </head>
  
  <body>
  
  	<!-- 功能按钮 -->
  	<div class="input-group" style="float: right;">
  		<button type="button" class="btn btn-primary glyphicon glyphicon-plus" data-toggle="modal" data-target="#addUserModal">&nbsp;新增</button>
  		<button id="del" onclick="delUserData()" type="button" class="btn btn-danger glyphicon glyphicon-trash">&nbsp;删除</button>
  		<button id="del" onclick="editUserState(0)" type="button" class="btn btn-info glyphicon glyphicon-ban-circle">&nbsp;冻结</button>
  		<button id="del" onclick="editUserState(1)" type="button" class="btn btn-warning glyphicon glyphicon-saved">&nbsp;解冻</button>
  		<button id="refresh" onclick="refresh()" type="button" class="btn btn-success glyphicon glyphicon-refresh">&nbsp;刷新</button>
  		<button type="button" onclick="openUserRoleModal()" class="btn  btn-inverse active glyphicon glyphicon-user">&nbsp;帐号关联角色</button>
  	</div>
  	<!-- 表格 -->
  	<div>
	  <table id="user_table" class="table">
	  </table>
	</div>
	
	<!-- 新增帐号弹框 -->
  	<div id="addUserModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">新增</h4>
	      </div>
	      <div class="modal-body">
	      	<div class="row form-inline">
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-12 col-md-2" style="width: 30%;">登录帐号：</h4>
                   <input type="text" id="add_USERACCOUNT" name="USERACCOUNT" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
               </div>
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-12 col-md-2" style="width: 30%;">登录密码：</h4>
                   <input type="password" id="add_USERPWD" name="USERPWD" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
               </div>
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-12 col-md-2" style="width: 30%;">用户姓名：</h4>
                   <input type="text" id="add_USERNAME" name="USERNAME" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
               </div>
             </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="addUser()">新增</button>
	      </div>
	    </div>
	  </div>
	</div>	
	
	<!-- 帐号关联角色弹框 -->
  	<div id="userRoleModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">帐号关联角色</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- 隐藏数据 -->
	      	<div style="display: none;">
	      		<input type="text" id="role_USERID" />
	      	</div>
	      	<div>
	      		<table id="role_table" class="table"></table>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="accountRoleModuleSave()">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
  </body>
  
  <script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap-table.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap-table-zh-CN.js"></script>
  <script type="text/javascript" src="module/js/userManage/userManage.js"></script>
</html>
