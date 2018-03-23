$(function(){
	$('#user_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'USER_ID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'userController/getUserWithPaging.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			checkbox:true,
			width:'5'//宽度
		},{
			field:'USER_ID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'USER_ACCOUNT',//返回值名称
			title:'帐号',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'USER_NAME',//返回值名称
			title:'用户姓名',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'USER_EMAIL',//返回值名称
			title:'用户邮箱',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'SCHOOL_NAME',//返回值名称
			title:'学校名称',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'USER_STATUS',//返回值名称
			title:'状态',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			formatter: stateFormatter
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

function stateFormatter(status){
	switch (status) {
	case "0":
		return '冻结';
		break;
	case "1":
		return '正常';
		break;
	default:
		break;
	}
}

function addUser(){
	var userAccount = $('#add_USERACCOUNT').val().trim();
	var userPwd = $('#add_USERPWD').val().trim();
	var userName = $('#add_USERNAME').val().trim();
	
	if(userAccount==null || userAccount==undefined || userAccount==""){
		alert("请输入登录帐号");
		return;
	}
	if(userPwd==null || userPwd==undefined || userPwd==""){
		alert("请输入登录密码");
		return;
	}
	
	var ajaxParameter = {
			userAccount:userAccount,
			userPwd:userPwd,
			userName:userName,
			email:"123@qq.com",
	};
	
	$.ajax({
		url:'userController/regist.do',
		data:ajaxParameter,
		success:function(jsonResult){
			var resultCode = jsonResult.resultCode;
			var resultMessage = jsonResult.resultMessage;
			if(resultCode=="0000"){
				$('#addUserModal').modal('hide');
				refresh();				
			}else{
				alert(resultMessage);				
			}
		}
	});
}

/* 刷新方法 */
function refresh(){
	$('#user_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delUserData(){
	var data = $('#user_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].USER_ID + "',";
	}
	
	var ajaxParameter = {
			userIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'userController/delUser.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  
		  if(resultCode=="0000"){
			  refresh();
		  }else{
			  alert(resultMessage);
			  refresh();
		  }
	  }
	});
}

/* 修改模块状态 */
function editUserState(state){
	var data = $('#user_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].USER_ID + "',";
	}
	
	var ajaxParameter = {
			userIds:ids.substring(0, (ids.length-1)),
			state:state
	};
	$.ajax({
	  url:'userController/updateUserState.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  
		  if(resultCode=="0000"){
			  refresh();
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}

function openUserRoleModal(){
	var data = $('#user_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#role_USERID').val(data[0].USER_ID);
	$('#userRoleModal').modal('show');
	
	$.ajax({
	  url:'userController/getUserRoleListInfo.do',
	  data:{userId:data[0].USER_ID},
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  
		  if(resultCode=="0000"){
			  initRoleTable(resultJson.rows);
		  }else{
			  initRoleTable([]);
		  }
	  }
	});
}

function initRoleTable(selections){
	var accountRoleDatas = [];
	
	for(var i=0; i<selections.length; i++){
		accountRoleDatas[i] = selections[i].ROLEID;		
	}
	
	$('#role_table').bootstrapTable({
//		data: jsonData,
		height: 200,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 20,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'ROLEID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'roleController/getRoleList.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			field : 'checked',
			checkbox:true,
			width:'5'//宽度
		},{
			field:'ROLEID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'ROLENAME',//返回值名称
			title:'角色名称',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		}],//列配置项,详情请查看 列参数 表格
		/*事件*/
		onPostHeader:function(){
			$('#role_table').bootstrapTable('checkBy', {field:"ROLEID", values:accountRoleDatas});
		}
	});
}

function accountRoleModuleSave(){
	var roleData = $('#role_table').bootstrapTable('getSelections');
	var userId = $('#role_USERID').val();
	
	var ids = "";
	if(roleData.length==0){
		ids += ","; 
	}else{
		for(var i=0; i<roleData.length; i++){
			ids += "" + roleData[i].ROLEID + ",";
		}
	}
	
	var ajaxParameter = {
			userId:userId,
			roleIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'userController/saveUserRoleInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  
		  if(resultCode=="0000"){
			  $('#userRoleModal').modal('hide');
			  refresh();
		  }else{
			  $('#userRoleModal').modal('hide');
			  alert(resultMessage);
		  }
	  }
	});
}