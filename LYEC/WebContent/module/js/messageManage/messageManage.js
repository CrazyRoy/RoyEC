$(function(){
	$('#message_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'MESSAGE_ID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'messageController/getMessageList.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			checkbox:true,
			width:'5'//宽度
		},{
			field:'MESSAGE_ID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:true
		},{
			field:'MESSAGE_REPLY_ID',//返回值名称
			title:'用户回复ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'MESSAGE_CONTENT',//返回值名称
			title:'回复的内容',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'MESSAGE_TIME',//返回值名称
			title:'留言时间',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'GOODS_ID',//返回值名称
			title:'商品ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'BUYERSID',//返回值名称
			title:'买家ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'SELLERID',//返回值名称
			title:'卖家ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

/* 刷新方法 */
function refresh(){
	$('#message_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delMessageData(){
	var data = $('#message_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].MESSAGE_ID + "',";
	}
	
	var ajaxParameter = {
			messageIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'messageController/delMessage.do',
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