$(function(){
	$('#school_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'SCHOOL_ID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'schoolController/getSchoolList.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			checkbox:true,
			width:'5'//宽度
		},{
			field:'SCHOOL_ID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'SCHOOL_NAME',//返回值名称
			title:'学校名称',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'SCHOOL_ADDRESS',//返回值名称
			title:'学校地址',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'SCHOOL_POSTCODE',//返回值名称
			title:'学校邮编',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

function addSchool(){
	var schoolName = $('#add_SCHOOLNAME').val();
	var schoolAddress = $('#add_SCHOOLADDRESS').val();
	var schoolPostcode = $('#add_SCHOOLPOSTCODE').val();
	if(schoolName==null || schoolName==undefined || schoolName==""){
		alert("请输入学校名称");
		return;
	}
	if(schoolAddress==null || schoolAddress==undefined || schoolAddress==""){
		alert("请输入学校地址");
		return;
	}
	if(schoolPostcode==null || schoolPostcode==undefined || schoolPostcode==""){
		alert("请输入学校邮编");
		return;
	}
	
	
	var ajaxParameter = {
			schoolName:schoolName,
			schoolAddress:schoolAddress,
			schoolPostcode:schoolPostcode
	};
	
	$.ajax({
		url:'schoolController/addSchoolInfo.do',
		data:ajaxParameter,
		success:function(jsonResult){
			var resultCode = jsonResult.resultCode;
			var resultMessage = jsonResult.resultMessage;
			if(resultCode=="0000"){
				$('#addSchoolModal').modal('hide');
				refresh();				
			}else{
				alert(resultMessage);				
			}
		}
	});
}

/* 刷新方法 */
function refresh(){
	$('#school_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delSchoolData(){
	var data = $('#school_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].SCHOOL_ID + "',";
	}
	
	var ajaxParameter = {
			schoolIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'schoolController/deleteSchoolInfo.do',
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
function openSchoolModal(){
	var data = $('#school_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#edit_SCHOOLID').val(data[0].SCHOOL_ID);
	$('#edit_SCHOOLNAME').val(data[0].SCHOOL_NAME);
	$('#edit_SCHOOLADDRESS').val(data[0].SCHOOL_ADDRESS);
	$('#edit_SCHOOLPOSTCODE').val(data[0].SCHOOL_POSTCODE);
	$('#eidtSchoolModal').modal('show');
}

/* 修改模块方法 */
function editSchoolModule(){
	var schoolId = $('#edit_SCHOOLID').val();
	var schoolName = $('#edit_SCHOOLNAME').val();
	var schoolAddress = $('#edit_SCHOOLADDRESS').val();
	var schoolPostcode = $('#edit_SCHOOLPOSTCODE')
	
	var ajaxParameter = {
		    schoolId:schoolId,
		    schoolName:schoolName,
		    schoolAddress:schoolAddress,
		    schoolPostcode:schoolPostcode
	};
	
	$.ajax({
	  url:'schoolController/editSchoolInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  if(resultCode=="0000"){
			  $('#eidtSchoolModal').modal('hide');
			  refresh();			  
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}