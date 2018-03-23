$(function(){
	$('#role_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
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
		},{
			field:'CREATETIME',//返回值名称
			title:'创建时间',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'REMARKS',//返回值名称
			title:'备注',//列名
			align:'left',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

function addRole(){
	var roleName = $('#add_ROLENAME').val().trim();
	var remarks = $('#add_REMARKS').val().trim();
	
	if(roleName==null || roleName==undefined || roleName==""){
		alert("请输入角色名称");
		return;
	}
	var ajaxParameter = {
			roleName:roleName,
			remarks:remarks
	};
	
	$.ajax({
		url:'roleController/addRole.do',
		data:ajaxParameter,
		success:function(jsonResult){
			var resultCode = jsonResult.resultCode;
			var resultMessage = jsonResult.resultMessage;
			if(resultCode=="0000"){
				$('#addRoleModal').modal('hide');
				refresh();				
			}else{
				alert(resultMessage);				
			}
		}
	});
}

/* 刷新方法 */
function refresh(){
	$('#role_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delAccountData(){
	var data = $('#role_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].ROLEID + "',";
	}
	
	var ajaxParameter = {
			roleIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'roleController/delRole.do',
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

/* 弹出修改弹框方法 */
function openRoleModal(){
	var data = $('#role_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#edit_ROLEID').val(data[0].ROLEID);
	$('#edit_ROLENAME').val(data[0].ROLENAME);
	$('#edit_REMARKS').val(data[0].REMARKS);
	
	$('#eidtRoleModal').modal('show');
}

/* 修改帐号方法 */
function editRoleModule(){
	var roleId = $('#edit_ROLEID').val();
	var roleName = $('#edit_ROLENAME').val();
	var remarks = $('#edit_REMARKS').val();
	
	var ajaxParameter = {
			roleId:roleId,
			roleName:roleName,
			remarks:remarks
	};
	
	$.ajax({
	  url:'roleController/editRole.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  
		  if(resultCode=="0000"){
			  $('#eidtRoleModal').modal('hide');
			  refresh();			  
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}

function openRoleModuleModal(){
	var data = $('#role_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#module_ROLEID').val(data[0].ROLEID);
	$('#roleModuleModal').modal('show');
	
	$.ajax({
	  url:'roleController/getRoleModuleListInfo.do',
	  data:{"roleId":data[0].ROLEID},
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  
		  if(resultCode=="0000"){
			  initModuleTable(resultJson.rows);
		  }else{
			  initModuleTable([]);
		  }
	  }
	});
}

function initModuleTable(selections){
	var roleModuleDatas = [];
	
	for(var i=0; i<selections.length; i++){
		roleModuleDatas[i] = selections[i].MODULEID;		
	}
	
	$('#module_table').bootstrapTable({
//		data: jsonData,
		height: 200,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 20,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'MODULEID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'moduleController/getModuleList.do',//服务器数据的加载地址
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
			field:'MODULEID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'MODULENAME',//返回值名称
			title:'模块名称',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		}],//列配置项,详情请查看 列参数 表格
		/*事件*/
		onPostHeader:function(){
			$('#module_table').bootstrapTable('checkBy', {field:"MODULEID", values:roleModuleDatas});
		}
	});
}

function roleModuleModalSave(){
    var moduleData = $('#module_table').bootstrapTable('getSelections');
    var roleId = $('#module_ROLEID').val();
    
    var ids = "";
    if(moduleData.length==0){
        ids += ","; 
    }else{
        for(var i=0; i<moduleData.length; i++){
            ids += "" + moduleData[i].MODULEID + ",";
        }
    }
    
    var ajaxParameter = {
            roleId:roleId,
            moduleIds:ids.substring(0, (ids.length-1))
    };
    $.ajax({
      url:'roleController/saveRoleModuleInfo.do',
      data:ajaxParameter,
      success:function(resultJson){
          var resultCode = resultJson.resultCode;
          var resultMessage = resultJson.resultMessage;
          
          if(resultCode=="0000"){
              $('#roleModuleModal').modal('hide');
              refresh();
          }else{
              $('#roleModuleModal').modal('hide');
              alert(resultMessage);
          }
      }
    });
}
