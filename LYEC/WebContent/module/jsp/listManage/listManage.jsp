<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>目录管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="link/easyui/themes/bootstrap/easyui.css" />   
    <link rel="stylesheet" type="text/css" href="link/easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="module/css/listManage/listManage.css" />
  </head>
  
  <body>
  	<div>
		<div>
			<table id="list_tg" toolbar="#list_toolbar"></table>
		</div>
		<div style="height: 5px;">
			<div id="list_toolbar">
				<a onclick="listAdd()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				<a onclick="listEdit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
				<a onclick="listDelete()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			</div>
		</div>
		
		<!-- 新增目录弹框 -->
		<div id="list_add_dialog" class="width350" style="display: none;">
			<!-- 隐藏数据块 -->
			<div style="display: none">
				<input id="add_parentId" name="parentId"/>
				<input id="add_listLevel" name="listLevel"/>
			</div>
			<!-- 显示数据块 -->
			<table class="table_disdan">
				<tr>
					<td class="label">目录名称:</td>
					<td><input type="text" class="easyui-validatebox input_text" id="add_listName" name="listName"/><span class="red">*</span></td>
				</tr>
				<tr>
					<td class="label">目录类型:</td>
					<td><input class="easyui-combobox easyui-validatebox input_text" id="add_typeId" name="typeId"/><span class="red">*</span></td>
				</tr>
				<tr>
					<td class="label">是否末端:</td>
					<td>
						<div id="add_isLast_div">
							<input type="radio" name="add_isLast" value="1" checked>末端目录</input>
							<input type="radio" name="add_isLast" value="0">非末端目录</input>
		           		</div>
					</td>
				</tr>
				<tr>
					<td class="label">目录描述:</td>
					<td><textarea id="add_listDes" name="listDes" class="easyui-validatebox input_text" rows="" cols=""></textarea></td>
				</tr>
			</table>
		</div>
		<!-- 编辑目录弹框 -->
		<div id="list_edit_dialog" class="width350" style="display: none;">
			<!-- 隐藏数据块 -->
			<div style="display: none">
				<input id="edit_listId" name="parentId"/>
			</div>
			<!-- 显示数据块 -->
			<table class="table_disdan">
				<tr>
					<td class="label">目录名称:</td>
					<td><input type="text" class="easyui-validatebox input_text" id="edit_listName" name="listName"/><span class="red">*</span></td>
				</tr>
				<tr>
					<td class="label">目录类型:</td>
					<td><input class="easyui-combobox easyui-validatebox input_text" id="edit_typeId" name="typeId"/><span class="red">*</span></td>
				</tr>
				<tr>
					<td class="label">是否末端:</td>
					<td>
						<div id="edit_isLast_div">
							<input type="radio" name="edit_isLast" value="1" checked>末端目录</input>
							<input type="radio" name="edit_isLast" value="0">非末端目录</input>
		           		</div>
					</td>
				</tr>
				<tr>
					<td class="label">目录描述:</td>
					<td><textarea id="edit_listDes" name="listDes" class="easyui-validatebox input_text" rows="" cols=""></textarea></td>
				</tr>
			</table>
		</div>
  	</div>
  </body>
  <script type="text/javascript" src="link/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="link/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="link/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="module/js/listManage/listManage.js"></script>
</html>
