/**
 * Created by Roy on 17/5/25. 我的闲置页面
 */

$(function(){
	getTotalIdelGoods(0,'');
	initPageInfo(undefined);
	$('.idelGoodsStatus .idelStauts-item').click(function(){
		// 更改选项状态
		$(this).addClass("active").siblings().removeClass("active");
		// 请求相应数据
		var index = $(this).index();
		switch(index) {
			case 0:{	// 全部
				getTotalIdelGoods(0,'');
				initPageInfo(undefined);
				break;
			};
			case 1:{	// 审核中
				getTotalIdelGoods(0,'0');
				initPageInfo('0');
				break;
			};
			case 2:{	// 审核不通过
				getTotalIdelGoods(0,'2');
				initPageInfo('2');
				break;
			};
			case 3:{	// 闲置中
				getTotalIdelGoods(0,'1');
				initPageInfo('1');
				break;
			};
			case 4:{	// 以卖出
				getTotalIdelGoods(0,'3');
				initPageInfo('3');
				break;
			};
			default: break;
		}
	});
});

// 请求全部闲置商品
function getTotalIdelGoods(page,type) {
	var userId = window.parent.getUserID();
	if(type==undefined){
		type='';
	}
	$.ajax({
		url:'goodsController/getGoodsListByUserId.do',
		data:{userId:userId,page:page,type:type},
		type:'post',
		dataType:'json',
		success: function(resultJson){
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			var orderList = $('.order-list');
			orderList.empty();
			if(resultCode=="0000"){
				var resultData = resultJson.rows;
				var htmlStr = '';
				var imageType = '';
				for(var i = 0; i < resultData.length; i++) {
					if(resultData[i].GOODS_STATUS==0) {	// 审核中
						imageType = 'inAudit';
					}else if(resultData[i].GOODS_STATUS==2){	// 闲置中 
						imageType = 'no-pass';
					}else if(resultData[i].GOODS_STATUS==1) {	// 闲置中
						imageType = 'sale';
					}else {
						imageType = 'invalid';	// 无效/已卖出
					}
					
					var imageTag = '';
					if(type == '') {
						imageTag = '<img class="InvalidImg" alt="闲置状态描述." src="platform/img/goodsStatus/' + imageType + '.png">';
					}
					
					htmlStr += '<li class="item clearfix">' 
								+ '<div onclick="showGoodsDetail(' + resultData[i].GOODS_ID + ', ' + resultData[i].GOODS_STATUS + ')" class="item-pic">'
								+ '<img class="product_pic" src="' + resultData[i].FILE_PATH + '" />'
								+ '</div>'
								+ '<div onclick="showGoodsDetail(' + resultData[i].GOODS_ID + ', ' + resultData[i].GOODS_STATUS + ')" class="title">'
								+ '<p class="product_title">'
								+ resultData[i].GOODS_TITLE + '</p>'
								+ '</div>'
								+ '<div class="prices">'
								+ '<span class="orginal_price"> 原价：￥' + resultData[i].GOODS_ORIGINAL + ' </span> <span'
								+ ' class="resale_price"> 现价：￥' + resultData[i].GOODS_RESALE + ' </span>'
								+ '</div>'
								+ '<div class="condition">'
								+ '<span class="product_condition"> ' + resultData[i].GOODS_CONDITION + ' </span>'
								+ '</div>'
								+ '<div class="actions">';
					if(resultData[i].GOODS_STATUS == 1) {	// 闲置中
						htmlStr += '<button type="button" class="btn actionButton" onclick="removeGoods(this, '
							+ resultData[i].GOODS_ID + ')">删&nbsp;除</button>';
					}else if(resultData[i].GOODS_STATUS){	 // 已卖出
						htmlStr += '<button type="button" class="btn actionButton" onclick="removeGoods(this, '
							+ resultData[i].GOODS_ID + ')">删&nbsp;除</button>';
					}
					
					htmlStr += '</div>'
							+ imageTag
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

/* 删除闲置 */
function removeGoods(thisz, goodsId, type) {
	if(window.confirm('您确定要删除该闲置么？')){
		$.ajax({
			url: 'goodsController/deleteGoods.do',
			data: {
				goodsIds: goodsId
			},
			type: 'POST',
			dataType: 'json',
			success: function(resultInfo) {
				console.log(resultInfo);
				$(thisz).parent().parent().remove();
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


/* 查看闲置 */
function showGoodsDetail(goodsId,type){
	if(type == '3') {
		alert("该闲置已经卖出！");
	}else {
		location.href = 'platform/goodsDetail.jsp?goodsId=' + goodsId;	
	}
}

/* 获得总页数 */
function initPageInfo(type){
	var userId = window.parent.getUserID();
	$.ajax({
		url:'goodsController/getGoodsTotalByUserId.do',
		data:{userId:userId,type:type},
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

// 点击某一页切换样式并且请求对应页的数据
function changePage(ele, page, type){
	$('#page_div li').removeClass('active');
	$(ele).attr('class','active');
	// 请求对应页的数据
	getTotalIdelGoods(page,type);
}

// 点击上一页
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

// 点击下一页
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