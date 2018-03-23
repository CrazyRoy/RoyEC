/**
 * Created by Roy on 17/5/25. 我的订单页面
 */

$(function(){
	// 获取我的订单页面的订单页数
	getTotalmyOrders(0,'0');
	// 请求第一页的数据
	initPageInfo('0');
});

// 请求订单列表
function getTotalmyOrders(page, type) {
	var userId = window.parent.getUserID();
	$.ajax({
		url:'orderController/getOrderListByUserId.do',
		data:{
			userId:userId,
			page:page,
			type:type	
		},
		type:'post',
		dataType:'json',
		success: function(resultJson){
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			var orderList = $('.order-list');
			orderList.empty();
			var htmlStr = '';
			if(resultCode=="0000"){
				var resultData = resultJson.rows;
				for(var i = 0; i < resultData.length; i++) {
					htmlStr += '<li class="item clearfix">'
							+ '<div class="item-pic" onclick="showOrderDetail(' + resultData[i].ORDER_ID + ')">'
							+ resultData[i].GOODS_ID
							+ '</div>'
							+ '<div class="title">'
							+ '<p class="product_title" onclick="showOrderDetail(' + resultData[i].ORDER_ID + ')">'
							+ resultData[i].GOODS_TITLE + '</p>'
							+ '</div>'
							+ '<div class="prices">'
							+ resultData[i].GOODS_RESALE
							+ '</div>'
							+ '<div class="condition">'
							+ resultData[i].ORDER_TIME
							+ '</div>'
							+ '<div class="actions">'
							+ '	<button type="button" class="btn actionButton" onclick="removeOrder(this, ' + resultData[i].ORDER_ID +')">删&nbsp;除</button>'
							+ '	</div>'
							+ '</li>';
				}
				orderList.append(htmlStr);
			}
			window.parent.refreshIframeHeight();
		},
		error: function(resultInfo) {
			alert("咦,服务器跑路了~");
		}
	});
}


/* 删除订单 */
function removeOrder(thisz, orderId) {
	var userId = window.parent.getUserID();
	if(window.confirm('您确定要删除该订单么？')){
		$.ajax({
			url: 'goodsController/deleteGoods.do',
			data: {
				userId: userId,
				orderIds: orderId
			},
			type: 'POST',
			dataType: 'json',
			success: function(resultInfo) {
				$(thisz).parent().parent().remove();
				window.parent.refreshIframeHeight();
			},
			error: function(resultInfo) {
				alert("咦,服务器跑路了~");
			}
		});
        return true;
     }else{
        return false;
    }
}


/* 查看订单详情 */
function showOrderDetail(orderId){
	window.location.href = "platform/orderDetail.jsp?orderId=" + orderId;
}

/* 获得总页数 */
function initPageInfo(type){
	var userId = window.parent.getUserID();
	$.ajax({
		url:'orderController/getOrderListTotalByUserId.do',
		data:{userId:userId,type:'0'},
		type:'post',
		dataType:'json',
		success: function(resultJson){
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			var pageDiv = $('#page_div');
			pageDiv.empty();
			if(resultCode=="0000"){
				var resultData = resultJson.pageNum;
				if(resultData==0){
					pageDiv.remove();
				}else{
					var htmlStr = '<ul class="pagination pagination-lg clearfix">'
						+ '<li onclick="changePageLeft(' + type + ')"><a href="javascript:void(0);">&laquo;</a></li>';
					for(var i = 0; i < resultData; i++) {
						if(i==0){
							htmlStr += '<li onclick="changePage(this,' + (i) + ',' + type + ')" pageIndex=' + i + ' class="active"><a href="javascript:void(0);">' + (i+1) + '</a></li>';
						}else{
							htmlStr += '<li onclick="changePage(this,' + (i) + ',' + type + ')" pageIndex=' + i + '><a href="javascript:void(0);">' + (i+1) + '</a></li>' 
						}
					}
					htmlStr += '<li onclick="changePageRight(' + resultData + ',' + type + ')"><a href="javascript:void(0);">&raquo;</a></li></ul>';
					pageDiv.append(htmlStr);
				}
			}else{
			}
		}
	});
}

function changePage(ele, page, type){
	$('#page_div li').removeClass('active');
	$(ele).attr('class','active');
	getTotalmyOrders(page,type);
}

function changePageLeft(type){
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if(pageIndex==0){
	}else{
		var pageIndexElement = $('#page_div li')[pageIndex];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class','active');
		
		if(type==null || type==undefined){
			type="";
		}
		getTotalIdelGoods((pageIndex-1),type);
	}
}

function changePageRight(total,type){
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if(pageIndex==(total-1)){
	}else{
		var pageIndexElement = $('#page_div li')[parseInt(pageIndex)+2];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class','active');
		
		if(type==null || type==undefined){
			type="";
		}
		getTotalIdelGoods((parseInt(pageIndex)+1),type);
	}
}