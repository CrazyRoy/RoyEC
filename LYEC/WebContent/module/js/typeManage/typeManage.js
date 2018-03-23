$(function(){
	$('#type_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'TYPE_ID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'typeController/getTypeList.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			checkbox:true,
			width:'5'//宽度
		},{
			field:'TYPE_ID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'TYPE_NAME',//返回值名称
			title:'类型名称',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'TYPE_DES',//返回值名称
			title:'类型描述',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

function addType(){
	var typeDes = $('#add_TYPEDES').val();
	var typeName = $('#add_TYPENAME').val();
	if(typeName==null || typeName==undefined || typeName==""){
		alert("请输入类型名称");
		return;
	}
	if(typeDes==null || typeDes==undefined || typeDes==""){
		alert("请输入类型描述");
		return;
	}
	
	var ajaxParameter = {
			typeName:typeName,
			typeDes:typeDes
	};
	
	$.ajax({
		url:'typeController/addTypeInfo.do',
		data:ajaxParameter,
		success:function(jsonResult){
			var resultCode = jsonResult.resultCode;
			var resultMessage = jsonResult.resultMessage;
			if(resultCode=="0000"){
				$('#addTypeModal').modal('hide');
				refresh();				
			}else{
				alert(resultMessage);				
			}
		}
	});
}

/* 刷新方法 */
function refresh(){
	$('#type_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delTypeData(){
	var data = $('#type_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].TYPE_ID + "',";
	}
	
	var ajaxParameter = {
			typeIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'typeController/deleteTypeInfo.do',
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
function openTypeModal(){
	var data = $('#type_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#edit_TYPEID').val(data[0].TYPE_ID);
	$('#edit_TYPEDES').val(data[0].TYPE_DES);
	$('#edit_TYPENAME').val(data[0].TYPE_NAME);
	
	$('#eidtTypeModal').modal('show');
}

/* 修改模块方法 */
function editTypeModule(){
	var typeId = $('#edit_TYPEID').val();
	var typeDes = $('#edit_TYPEDES').val();
	var typeName = $('#edit_TYPENAME').val();
	
	var ajaxParameter = {
			typeId:typeId,
			typeDes:typeDes,
			typeName:typeName
	};
	
	$.ajax({
	  url:'typeController/editTypeInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  if(resultCode=="0000"){
			  $('#eidtTypeModal').modal('hide');
			  refresh();			  
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}