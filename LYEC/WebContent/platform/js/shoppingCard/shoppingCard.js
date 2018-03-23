/**
 * Created by Roy on 17/5/19. 购物车页面
 */

$(function() {
	// 默认请求全部
	initCardGoodsList(0, '');
	// 默认请求全部的页码
	initPageInfo('');

	$('.idelGoodsStatus .idelStauts-item').click(function() {
		// 更改选项状态
		$(this).addClass("active").siblings().removeClass("active");
		// 请求相应数据
		var index = $(this).index();
		switch (index) {
		case 0: { // 全部
			initCardGoodsList(0, '');
			initPageInfo('');
			break;
		}
			;
		case 1: { // 闲置中
			initCardGoodsList(0, '1');
			initPageInfo('1');
			break;
		}
			;
		case 2: { // 已卖出
			initCardGoodsList(0, '3');
			initPageInfo('3');
			break;
		}
			;
		default:
			break;
		}
	});
});

// 根据商品状态请求购物车商品
function initCardGoodsList(page, type) {
	var userId = window.parent.getUserID();
	$.ajax({
		url : 'shoppingCardController/getGoodsFromCardInfo.do',
		data : {
			userId : userId,
			type : type,
			page : page
		},
		type : 'post',
		dataType : 'json',
		success : function(resultJson) {
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			if (resultCode == "0000") {
				var resultData = resultJson.rows;
				initCardGoodsListHtml(resultData, type);
			} else {
				initCardGoodsListHtml([], type);
			}
		}
	});
}

// 将请求回来的商品放入页面中
function initCardGoodsListHtml(goodsList, type) {
	var htmlElement = $('#card_goods_list');
	var htmlStr = '';
	for (var i = 0; i < goodsList.length; i++) {
		var imageType = '';
		for (var i = 0; i < goodsList.length; i++) {
			if (goodsList[i].GOODS_STATUS == 1) {	// 闲置中
				imageType = 'saleing';
			} else {
				imageType = 'sold';	// 已卖出
			}

			var imageTag = '';
			if (type == '') {
				imageTag = '<img class="InvalidImg" alt="闲置状态描述." src="platform/img/goodsStatus/'
						+ imageType + '.png">';
			}

			htmlStr += '<li class="item clearfix">' + '<div class="item-pic" onclick="showGoodsDetail(' + goodsList[i].GOODS_ID + ', ' + goodsList[i].GOODS_STATUS + ')">'
					+ '<img class="product_pic" src="' + goodsList[i].FILE_URL
					+ '" />' + '</div>' + '<div class="title" onclick="showGoodsDetail(' + goodsList[i].GOODS_ID + ', ' + goodsList[i].GOODS_STATUS + ')">'
					+ '<p class="product_title">' + goodsList[i].GOODS_TITLE
					+ '</p>' + '</div>' + '<div class="prices">'
					+ '<span class="orginal_price"> 原价：￥'
					+ goodsList[i].GOODS_ORIGINAL
					+ ' </span><span class="resale_price"> 现价：￥'
					+ goodsList[i].GOODS_RESALE + ' </span>' + '</div>'
					+ '<div class="condition">'
					+ '<span class="product_condition"> '
					+ goodsList[i].GOODS_CONDITION + ' </span>' + '</div>'
					+ '<div class="actions">';

			if(goodsList[i].GOODS_STATUS == 1) {	// 闲置中
				htmlStr += '<button type="button" class="btn actionButton" onclick="buyGoods('
					+ goodsList[i].GOODS_ID
					+ ')">购&nbsp;买</button>'
					+ '<button type="button" class="btn actionButton" onclick="removeGoods(this, '
					+ goodsList[i].GOODS_ID
					+ ')">删&nbsp;除</button>';
			}else {	 // 已卖出
				htmlStr += '<button type="button" class="btn actionButton" onclick="removeGoods(this, '
					+ goodsList[i].GOODS_ID + ')">删&nbsp;除</button>';
			}
			htmlStr += '</div>' + imageTag + '</li>';
		}
		htmlElement.append(htmlStr);
	}
	htmlElement.empty();
	htmlElement.append(htmlStr);
	window.parent.refreshIframeHeight();
}

/* 查看商品详情 */
function showGoodsDetail(goodsId, type) {
	if(type == '3') {
		alert("该闲置已经卖出！");
	}else {
		location.href = 'platform/goodsDetail.jsp?goodsId=' + goodsId;	
	}
}

/* 购买  */
function buyGoods(thisz, goodsId) {
	location.href = "platform/shippingAddress.jsp?goodsId=" + goodsId;
}

/* 删除 */
function removeGoods(thisz, goodsId) {
	var userId = window.parent.getUserID();
	if(window.confirm('您确定要删除该闲置么？')){
		$.ajax({
			url: 'shoppingCardController/deleteGoodsFromCardInfo.do',
			data: {
				userId: userId,
				goodsIds: goodsId
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

// 请求对应状态的页码数
function initPageInfo(type) {
	var userId = window.parent.getUserID();
	$
			.ajax({
				url : 'shoppingCardController/getGoodsTotalFromCardInfo.do',
				data : {
					userId : userId,
					type : type
				},
				type : 'post',
				dataType : 'json',
				success : function(resultJson) {
					var resultCode = resultJson.resultCode;
					var resultMessage = resultJson.resultMessage;
					var pageDiv = $('#page_div');
					pageDiv.empty();
					if (resultCode == "0000") {
						var resultData = resultJson.pageNum;
						if (resultData == 0) {
							pageDiv.remove();
						} else {
							var htmlStr = '<ul class="pagination pagination-lg clearfix">'
									+ '<li onclick="changePageLeft('
									+ type
									+ ')"><a href="javascript:void(0);">&laquo;</a></li>';
							for (var i = 0; i < resultData; i++) {
								if (i == 0) {
									htmlStr += '<li onclick="changePage(this,'
											+ (i)
											+ ','
											+ type
											+ ')" pageIndex='
											+ i
											+ ' class="active"><a href="javascript:void(0);">'
											+ (i + 1) + '</a></li>';
								} else {
									htmlStr += '<li onclick="changePage(this,'
											+ (i) + ',' + type
											+ ')" pageIndex=' + i
											+ '><a href="javascript:void(0);">'
											+ (i + 1) + '</a></li>'
								}
							}
							htmlStr += '<li onclick="changePageRight('
									+ resultData
									+ ','
									+ type
									+ ')"><a href="javascript:void(0);">&raquo;</a></li></ul>';
							pageDiv.append(htmlStr);
						}
					} 
					window.parent.refreshIframeHeight();
				}
			});
}

// 页码点击事件
function changePage(ele, page, type) {
	$('#page_div li').removeClass('active');
	$(ele).attr('class', 'active');
	initCardGoodsList(page, type);
}

// 点击上一页
function changePageLeft(type) {
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if (pageIndex == 0) {
	} else {
		var pageIndexElement = $('#page_div li')[pageIndex];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class', 'active');

		if (type == null || type == undefined) {
			type = "";
		}
		initCardGoodsList((pageIndex - 1), type);
	}
}

// 点击下一页
function changePageRight(total, type) {
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if (pageIndex == (total - 1)) {
	} else {
		var pageIndexElement = $('#page_div li')[parseInt(pageIndex) + 2];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class', 'active');

		if (type == null || type == undefined) {
			type = "";
		}
		initCardGoodsList((parseInt(pageIndex) + 1), type);
	}
}