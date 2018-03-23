<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>模块管理</title>
    
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
  		<button type="button" class="btn btn-primary glyphicon glyphicon-plus" data-toggle="modal" data-target="#addModuleModal">&nbsp;新增</button>
  		<button type="button" onclick="openModuleModal()" class="btn btn-warning glyphicon glyphicon-edit">&nbsp;修改</button>
  		<button id="del" onclick="delModuleData()" type="button" class="btn btn-danger glyphicon glyphicon-trash">&nbsp;删除</button>
  		<button id="del" onclick="editModuleState(1)" type="button" class="btn btn-default active glyphicon glyphicon-remove">&nbsp;禁用</button>
  		<button id="del" onclick="editModuleState(0)" type="button" class="btn btn-info glyphicon glyphicon-ok">&nbsp;启用</button>
  		<button id="refresh" onclick="refresh()" type="button" class="btn btn-success glyphicon glyphicon-refresh">&nbsp;刷新</button>
  	</div>
  	<!-- 表格 -->
  	<div>
	  <table id="module_table" class="table">
	  </table>
	</div>
	
	<!-- 新增模块弹框 -->
  	<div id="addModuleModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">新增</h4>
	      </div>
	      <div class="modal-body">
	      	<div class="row form-inline">
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-6 col-md-2" style="width: 15%;">名称：</h4>
                   <input type="text" id="add_MODULENAME" name="MODULENAME" class="form-control col-xs-12 col-md-10" style="width: 84%;" aria-describedby="basic-addon1"/>
               </div>
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-6 col-md-2" style="width: 15%;">URL：</h4>
                   <input type="text" id="add_URL" name="URL" class="form-control col-xs-12 col-md-10" style="width: 84%;" aria-describedby="basic-addon1"/>
               </div>
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-6 col-md-2" style="width: 15%;">备注：</h4>
                   <input type="text" id="add_REMARKS" name="REMARKS" class="form-control col-xs-12 col-md-10" style="width: 84%;" aria-describedby="basic-addon1"/>
               </div>
             </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="addModule()">新增</button>
	      </div>
	    </div>
	  </div>
	</div>	
	
	<!-- 编辑模块弹框 -->
  	<div id="eidtModuleModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">编辑</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- 隐藏数据 -->
	      	<div style="display: none;">
	      		<input type="text" id="edit_MODULEID" />
	      	</div>
	      	<div class="row form-inline">
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-6 col-md-2" style="width: 15%;">名称：</h4>
                   <input type="text" id="edit_MODULENAME" name="MODULENAME" class="form-control col-xs-12 col-md-10" style="width: 84%;" aria-describedby="basic-addon1"/>
               </div>
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-6 col-md-2" style="width: 15%;">URL：</h4>
                   <input type="text" id="edit_URL" name="URL" class="form-control col-xs-12 col-md-10" style="width: 84%;" aria-describedby="basic-addon1"/>
               </div>
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-6 col-md-2" style="width: 15%;">备注：</h4>
                   <input type="text" id="edit_REMARKS" name="REMARKS" class="form-control col-xs-12 col-md-10" style="width: 84%;" aria-describedby="basic-addon1"/>
               </div>
             </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="editModuleModule()">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
  </body>
  
  <script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap-table.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap-table-zh-CN.js"></script>
  <script type="text/javascript" src="module/js/moduleManage/moduleManage.js"></script>
</html>
