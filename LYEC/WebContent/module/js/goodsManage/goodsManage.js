$(function(){
	$('#goods_table').bootstrapTable({
//		data: jsonData,
		height: 400,//定义表格的高度
		striped: true,// 隔行变色效果
		pagination: true,//在表格底部显示分页条
		pageSize: 5,//页面数据条数
		pageNumber:1,//首页页码
		pageList: [5, 10, 20, 50, 200, 500],//设置可供选择的页面数据条数
		clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
		cache: false,//禁用 AJAX 数据缓存
		sortName:'GOODS_ID',//定义排序列
		sortOrder:'asc',//定义排序方式
		url:'goodsController/getGoodsList.do',//服务器数据的加载地址
		sidePagination:'server',//设置在哪里进行分页
		contentType:'application/json',//发送到服务器的数据编码类型
		dataType:'json',//服务器返回的数据类型
//		queryParams:'',//请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数
		selectItemName:'',//radio or checkbox 的字段名
		columns:[{
			checkbox:true,
			width:'5'//宽度
		},{
			field:'GOODS_ID',//返回值名称
			title:'ID',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			visible:false
		},{
			field:'GOODS_TITLE',//返回值名称
			title:'商品标题',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'GOODS_ORIGINAL',//返回值名称
			title:'商品原价',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'GOODS_RESALE',//返回值名称
			title:'商品现价',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'GOODS_CONDITION',//返回值名称
			title:'商品成色',//列名
			align:'center',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10'//宽度
		},{
			field:'GOODS_STATUS',//返回值名称
			title:'商品状态',//列名
			align:'left',//水平居中显示
			valign:'middle',//垂直居中显示
			width:'10',//宽度
			formatter: stateFormatter
		}]//列配置项,详情请查看 列参数 表格
		/*事件*/
	});
});

function stateFormatter(state){
	switch (state) {
	case "0":
		return '待审核';
		break;
	case "1":
		return '未卖出';
		break;
	case "2":
		return "未通过";
		break;
	case "3":
		return "已卖出";
		break;
	default:
		break;
	}
}

/* 刷新方法 */
function refresh(){
	$('#goods_table').bootstrapTable('refresh', null);
}

/* 删除方法 */
function delGoodsData(){
	var data = $('#goods_table').bootstrapTable('getSelections');
	if(data.length==0){
		alert("请至少选中一条数据");
		return;
	}
	
	var ids = "";
	for(var i=0; i<data.length; i++){
		ids += "'" + data[i].GOODS_ID + "',";
	}
	
	var ajaxParameter = {
			goodsIds:ids.substring(0, (ids.length-1))
	};
	$.ajax({
	  url:'goodsController/deleteGoods.do',
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
function openGoodsModal(){
	var data = $('#goods_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#audit_GOODSID').val(data[0].GOODS_ID);
	
	$('#auditGoodsModal').modal('show');
}

/* 审核商品方法 */
function auditGoodsSave(){
	var goodsId = $('#audit_GOODSID').val();
	var goodsStatus = $('#audit_GOODSSTATUS').val();
	
	var ajaxParameter = {
			goodsId:goodsId,
			goodsStatus:goodsStatus
	};
	
	$.ajax({
	  url:'goodsController/editGoodsInfo.do',
	  data:ajaxParameter,
	  success:function(resultJson){
		  var resultCode = resultJson.resultCode;
		  var resultMessage = resultJson.resultMessage;
		  
		  if(resultCode=="0000"){
			  $('#auditGoodsModal').modal('hide');
			  refresh();			  
		  }else{
			  alert(resultMessage);
		  }
	  }
	});
}

function openLookGoodsModal(){
	var data = $('#goods_table').bootstrapTable('getSelections');
	
	if(data.length==0 || data.length>1){
		alert("请选中一条数据");
		return;
	}
	
	$('#look_GOODS_TITLE').val(data[0].GOODS_TITLE);
	$('#look_GOODS_ORIGINAL').val(data[0].GOODS_ORIGINAL);
	$('#look_GOODS_RESALE').val(data[0].GOODS_RESALE);
	$('#look_GOODS_CONDITION').val(data[0].GOODS_CONDITION);
	$('#look_LIST_NAME').val(data[0].LIST_NAME);
	$('#look_GOODS_VIEWCONTS').val(data[0].GOODS_VIEWCONTS);
	$('#look_GOODS_DINGNUM').val(data[0].GOODS_DINGNUM);
	$('#look_GOODS_CAINUM').val(data[0].GOODS_CAINUM);
	$('#look_GOODS_RECENTACCESS').val(data[0].GOODS_RECENTACCESS);
	$('#look_GOODS_CREATEDTIME').val(data[0].GOODS_CREATEDTIME);
	$('#look_GOODS_DES').val(data[0].GOODS_DES);
	
	switch (data[0].GOODS_STATUS) {
	case "0":
		$('#look_GOODS_STATUS').val("待审核");
		break;
	case "1":
		$('#look_GOODS_STATUS').val("未卖出");
		break;
	case "2":
		$('#look_GOODS_STATUS').val("未通过");
		break;
	case "3":
		$('#look_GOODS_STATUS').val("已卖出");
		break;
	default:
		$('#look_GOODS_STATUS').val(data[0].GOODS_STATUS);
		break;
	}
	
	$('#lookGoodsModal').modal('show');
}
