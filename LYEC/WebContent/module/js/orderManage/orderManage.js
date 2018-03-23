$(function(){
	$('#order_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'ORDER_ID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'orderController/getOrderList.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			checkbox:true,
			width:'5'//宽度
		},{
			field:'ORDER_ID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'ORDER_TIME',//返回值名称
			title:'订单生成时间',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'ORDER_STATU',//返回值名称
			title:'订单状态',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			formatter: stateFormatter
		},{
			field:'USER_ID',//返回值名称
			title:'用户ID',//列名
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
			field:'ADDRESS_ID',//返回值名称
			title:'收货地址ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

function stateFormatter(statu){
	switch (statu) {
	case "0":
		return '未收货';
		break;
	case "1":
		return '已收货';
		break;
	default:
		break;
	}
}

/* 刷新方法 */
function refresh(){
	$('#order_table').bootstrapTable('refresh', null);
}



function openLookOrderModal(){
	var data = $('#order_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#look_ORDER_TIME').val(data[0].ORDER_TIME);
	$('#look_ORDER_STATU').val(data[0].ORDER_STATU);
	$('#look_USER_ID').val(data[0].USER_ID);
	$('#look_USER_NAME').val(data[0].USER_NAME);
	$('#look_USER_PHONE').val(data[0].USER_PHONE);
	$('#look_USER_EMAIL').val(data[0].USER_EMAIL);
	$('#look_GOODS_ID').val(data[0].GOODS_ID);
	$('#look_GOODS_TITLE').val(data[0].GOODS_TITLE);
	$('#look_GOODS_DES').val(data[0].GOODS_DES);
	$('#look_GOODS_ORIGINAL').val(data[0].GOODS_ORIGINAL);
	$('#look_GOODS_RESAL').val(data[0].GOODS_RESAL);
	$('#look_ADDRESS_ID').val(data[0].ADDRESS_ID);
	$('#look_ADDRESS_CONTENT').val(data[0].ADDRESS_CONTENT);
	
	switch (data[0].ORDER_STATU) {
	case "0":
		$('#look_ORDER_STATU').val("未收货");
		break;
	case "1":
		$('#look_ORDER_STATU').val("已收货");
		break;
	default:
		$('#look_ORDER_STATU').val(data[0].ORDER_STATU);
		break;
	}
	$('#lookOrderModal').modal('show');
}