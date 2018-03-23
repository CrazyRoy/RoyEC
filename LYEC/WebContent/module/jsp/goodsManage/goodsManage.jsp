<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>商品管理</title>
    
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
	  	<button type="button" onclick="openLookGoodsModal()" class="btn btn-primary glyphicon glyphicon-zoom-in">&nbsp;查看</button>
  		<button type="button" onclick="openGoodsModal()" class="btn btn-default active glyphicon glyphicon-eye-open">&nbsp;审核</button>
  		<button id="del" onclick="delGoodsData()" type="button" class="btn btn-danger glyphicon glyphicon-trash">&nbsp;删除</button>
  		<button id="refresh" onclick="refresh()" type="button" class="btn btn-success glyphicon glyphicon-refresh">&nbsp;刷新</button>
  	</div>
  	<!-- 表格 -->
  	<div>
	  <table id="goods_table" class="table">
	  </table>
	</div>
	
	<!-- 审核商品弹框 -->
  	<div id="auditGoodsModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">审核</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- 隐藏数据 -->
	      	<div style="display: none;">
	      		<input type="text" id="audit_GOODSID" />
	      	</div>
	      	<div class="row form-inline">
               <div class="col-xs-12 col-md-12 form-group">
                   <h4 class="col-xs-12 col-md-2" style="width: 30%;">审核结果：</h4>
                   <select id="audit_GOODSSTATUS" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1">
                   	<option value="1">审核通过</option>
                   	<option value="2">审核不通过</option>
                   </select>
               </div>
             </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="auditGoodsSave()">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 查看商品信息弹框 -->
  	<div id="lookGoodsModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
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
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品标题：</h4>
		               <input type="text" id="look_GOODS_TITLE" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品原价：</h4>
		               <input type="text" id="look_GOODS_ORIGINAL" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品现价：</h4>
		               <input type="text" id="look_GOODS_RESALE" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品成色：</h4>
		               <input type="text" id="look_GOODS_CONDITION" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品类型：</h4>
		               <input type="text" id="look_LIST_NAME" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品状态：</h4>
		               <input type="text" id="look_GOODS_STATUS" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">访问量：</h4>
		               <input type="text" id="look_GOODS_VIEWCONTS" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">顶数量：</h4>
		               <input type="text" id="look_GOODS_DINGNUM" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">踩数量：</h4>
		               <input type="text" id="look_GOODS_CAINUM" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">最近访问时间：</h4>
		               <input type="text" id="look_GOODS_RECENTACCESS" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">创建时间：</h4>
		               <input type="text" id="look_GOODS_CREATEDTIME" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
	               </div>
	               <div class="col-xs-12 col-md-12 form-group">
		               <h4 class="col-xs-12 col-md-2" style="width: 30%;">商品描述：</h4>
		               <input type="text" id="look_GOODS_DES" class="form-control col-xs-12 col-md-10" style="width: 69%;" aria-describedby="basic-addon1"/>
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
  <script type="text/javascript" src="module/js/goodsManage/goodsManage.js"></script>
</html>
