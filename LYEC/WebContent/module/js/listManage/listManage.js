var typeData = [{
	TYPE_ID:"1",
	TYPE_DES:"类型1"
},{
	TYPE_ID:"2",
	TYPE_DES:"类型2"
},{
	TYPE_ID:"3",
	TYPE_DES:"类型3"
},{
	TYPE_ID:"4",
	TYPE_DES:"类型4"
},{
	TYPE_ID:"5",
	TYPE_DES:"类型5"
}];

$(function() {
	$('#list_tg').treegrid({
		url:'listController/getListInfo.do',
//		queryParams:{"servicName":"MenuInfo","data":{}},
		rownumbers: true,
		idField:"listId",
		treeField:"listName",
		columns:[[
		    {field:'isLast' , title:"isLast", hidden:true,formatter:function(value,row,index){
		    	if(value == 0){
		    		row.state="closed";
		    	}else{
		    		row.state="open";
		    	}
		    }},
		    {field:'listName',title:'目录名称',width:240},
		    {field:'typeName',title:'目录类型',width:200},
		    {field:"clickCountValue",title:'目录点击量',width:100},
		    {field:'listDes',title:'目录描述',width:350}
		]]
	});
	
});

function listAdd(){
	var selectedData = $('#list_tg').treegrid('getSelected');
	
	if(selectedData == null){
		$('#add_parentId').val("-1");
		$('#add_listLevel').val("0");
	}else{
		if(selectedData.isLast == "1"){
			$('#add_parentId').val(selectedData.parentId);
			$('#add_listLevel').val(selectedData.listLevel);
		}else{
			$('#add_parentId').val(selectedData.listId);
			var listLevel = selectedData.listLevel;
			$('#add_listLevel').val(parseInt(listLevel)+1);
		}
	}
	$.ajax({
	  url:'typeController/getTypeList.do',
	  data:{limit:"300",offset:"0",order:"asc",sort:"TYPE_ID"},
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  if(resultCode=="0000"){
			  $('#add_typeId').combobox({
				data:resultJson.rows,
			    valueField:'TYPE_ID',
			    textField:'TYPE_DES',
			    editable:false
			  });
		  }else{
			  $('#add_typeId').combobox({
				data:typeData,
			    valueField:'TYPE_ID',
			    textField:'TYPE_DES',
			    editable:false
			  });
		  }
	  }
	});
	
	$('#list_add_dialog').show();
	$('#list_add_dialog').dialog({
		title : '新增目录',
		height:350,
		modal:true,
		buttons:[
		    {
		    	text:"提交",
		    	iconCls: 'icon-ok',
		    	plain:true,
		    	handler: listAddSave
		    },
		    {
		    	text:"取消",
		    	iconCls: 'icon-cancel',
		    	plain:true,
		    	handler: function () {
		    		$('#list_add_dialog').dialog('close');
	            }
		    }
		]
	});
}

function listAddSave(){
	var parentId = $('#add_parentId').val();
	var listLevel = $('#add_listLevel').val();
	var listName = $('#add_listName').val();
	var listDes = $('#add_listDes').val();
	var typeId = $('#add_typeId').combobox('getValue');
	var isLast = $('#add_isLast_div input[name="add_isLast"]:checked').val();
	
	var ajaxParameter = {
			parentId:parentId,
			listLevel:listLevel,
			listName:listName,
			listDes:listDes,
			typeId:typeId,
			isLast:isLast
	};
	
	$.ajax({
	  url:'listController/addListInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  if(resultCode=="0000"){
			  $('#list_add_dialog').dialog('close');
			  refresh();
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}

/* 刷新方法 */
function refresh(){
	$('#list_tg').treegrid('reload');
}

function listEdit(){
	var selectedData = $('#list_tg').treegrid('getSelected');
	if(selectedData == null){
		$.messager.alert("操作提示","请选中一个目录","info",function(){});
		return;
	}
	
	$('#edit_listId').val(selectedData.listId);
	$('#edit_listName').val(selectedData.listName);
	$("#edit_isLast_div input[name='edit_isLast'][value='" + selectedData.isLast + "']").prop("checked","checked");
	$('#edit_listDes').val(selectedData.listDes);
	
	$.ajax({
	  url:'typeController/getTypeList.do',
	  data:{limit:"300",offset:"0",order:"asc",sort:"TYPE_ID"},
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  if(resultCode=="0000"){
			  $('#edit_typeId').combobox({
				data:resultJson.rows,
			    valueField:'TYPE_ID',
			    textField:'TYPE_DES',
			    editable:false
			  });
			  if(selectedData.hasOwnProperty("typeId")){
				  $('#edit_typeId').combobox('select',selectedData.typeId);
			  }
		  }else{
			  $('#edit_typeId').combobox({
				data:typeData,
			    valueField:'TYPE_ID',
			    textField:'TYPE_DES',
			    editable:false
			  });
		  }
	  }
	});
	
	$('#list_edit_dialog').show();
	$('#list_edit_dialog').dialog({
		title : '修改菜单',
		height:300,
		modal:true,
		buttons:[
		    {
		    	text:"提交",
		    	iconCls: 'icon-ok',
		    	plain:true,
		    	handler: listEditSave
		    },
		    {
		    	text:"取消",
		    	iconCls: 'icon-cancel',
		    	plain:true,
		    	handler: function () {
		    		$('#list_edit_dialog').dialog('close');
	            }
		    }
		]
	});
}

function listEditSave(){
	var listId = $('#edit_listId').val();
	var listName = $('#edit_listName').val();
	var listDes = $('#edit_listDes').val();
	var typeId = $('#edit_typeId').combobox('getValue');
	var isLast = $('#edit_isLast_div input[name="edit_isLast"]:checked').val();
	
	var ajaxParameter = {
			listId:listId,
			listName:listName,
			listDes:listDes,
			typeId:typeId,
			isLast:isLast
	};
	
	$.ajax({
	  url:'listController/editListInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  if(resultCode=="0000"){
			  $('#list_edit_dialog').dialog('close');
			  refresh();
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}

function listDelete(){
	
}