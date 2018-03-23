$(function(){
	$('#module_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
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
		},{
			field:'URL',//返回值名称
			title:'url',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'STATE',//返回值名称
			title:'状态',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			formatter: stateFormatter
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

function stateFormatter(state){
	switch (state) {
	case "0":
		return '启用';
		break;
	case "1":
		return '禁用';
		break;
	default:
		break;
	}
}

function addModule(){
	var moduleName = $('#add_MODULENAME').val().trim();
	var moduleUrl = $('#add_URL').val().trim();
	var moduleRemarks = $('#add_REMARKS').val().trim();
	
	if(moduleName==null || moduleName==undefined || moduleName==""){
		alert("请输入模块名称");
		return;
	}
	if(moduleUrl==null || moduleUrl==undefined || moduleUrl==""){
		alert("请输入模块URL");
		return;
	}
	
	var ajaxParameter = {
			moduleName:moduleName,
			url:moduleUrl,
			remarks:moduleRemarks
	};
	
	$.ajax({
		url:'moduleController/addModuleInfo.do',
		data:ajaxParameter,
		success:function(jsonResult){
			var resultCode = jsonResult.resultCode;
			var resultMessage = jsonResult.resultMessage;
			if(resultCode=="0000"){
				$('#addModuleModal').modal('hide');
				refresh();				
			}else{
				alert(resultMessage);				
			}
		}
	});
}

/* 刷新方法 */
function refresh(){
	$('#module_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delModuleData(){
	var data = $('#module_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].MODULEID + "',";
	}
	
	var ajaxParameter = {
			moduleIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'moduleController/deleteModuleInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
//		  var resultJson = eval('(' + result + ')');
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
function openModuleModal(){
	var data = $('#module_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#edit_MODULEID').val(data[0].MODULEID);
	$('#edit_MODULENAME').val(data[0].MODULENAME);
	$('#edit_URL').val(data[0].URL);
	$('#edit_REMARKS').val(data[0].REMARKS);
	
	$('#eidtModuleModal').modal('show');
}

/* 修改模块方法 */
function editModuleModule(){
	var moduleId = $('#edit_MODULEID').val();
	var moduleName = $('#edit_MODULENAME').val();
	var moduleUrl = $('#edit_URL').val();
	var moduleRemarks = $('#edit_REMARKS').val();
	
	var ajaxParameter = {
			moduleId:moduleId,
			moduleName:moduleName,
			url:moduleUrl,
			remarks:moduleRemarks
	};
	
	$.ajax({
	  url:'moduleController/editModuleInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  
		  if(resultCode=="0000"){
			  $('#eidtModuleModal').modal('hide');
			  refresh();			  
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}

/* 修改模块状态 */
function editModuleState(state){
	var data = $('#module_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].MODULEID + "',";
	}
	
	var ajaxParameter = {
			moduleIds:ids.substring(0, (ids.length-1)),
			state:state
	};
	$.ajax({
	  url:'moduleController/editModuleState.do',
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