<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>订单管理</title>
    
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
  		<button type="button" onclick="openLookOrderModal()" class="btn btn-primary glyphicon glyphicon-zoom-in">&nbsp;查看</button>
  	    <button id="refresh" onclick="refresh()" type="button" class="btn btn-success glyphicon glyphicon-refresh">&nbsp;刷新</button>
  	</div>
  	<!-- 表格 -->
  	<div>
	  <table id="order_table" class="table">
	  </table>
	</div>
	<!-- 查看订单信息弹框 -->
  	<div id="lookOrderModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">查看</h4>
	      </div>
	      <div class="modal-body">
	      	<div class="row form-inline">
               <div class="col-xs-12 col-md-12 form-group">
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">订单时间：</h4>
		               <input type="text" id="look_ORDER_TIME" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">订单状态：</h4>
		               <input type="text" id="look_ORDER_STATU" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">用户ID：</h4>
		               <input type="text" id="look_USER_ID" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">用户名：</h4>
		               <input type="text" id="look_USER_NAME" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">用户联系电话：</h4>
		               <input type="text" id="look_USER_PHONE" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">用户邮箱：</h4>
		               <input type="text" id="look_USER_EMAIL" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品ID：</h4>
		               <input type="text" id="look_GOODS_ID" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品标题：</h4>
		               <input type="text" id="look_GOODS_TITLE" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品描述：</h4>
		               <input type="text" id="look_GOODS_DES" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品原价：</h4>
		               <input type="text" id="look_GOODS_ORIGINAL" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品现价：</h4>
		               <input type="text" id="look_GOODS_RESAL" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">收货地址ID：</h4>
		               <input type="text" id="look_ADDRESS_ID" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>           
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">收货地址：</h4>
		               <input type="text" id="look_ADDRESS_CONTENT" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div> 
               </div>
             </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
  </body>
  
  <script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap-table.js"></script>
  <script type="text/javascript" src="link/bootstrap/js/bootstrap-table-zh-CN.js"></script>
  <script type="text/javascript" src="module/js/orderManage/orderManage.js"></script>
</html>
