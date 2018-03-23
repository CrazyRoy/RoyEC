
/*预加载*/
$(function(){
	var typeName = $('#TYPE_NAME').val();
	var typeId = $('#TYPE_ID').val();
	$('#page-name').text(typeName);
	// 请求该目录下面的商品页数
	initPageInfo(typeId);
	// 默认请求第一页的数据
	getGoodsByTypeID(typeId, '0');
});


// 根据类型ID查询对应页的商品
function getGoodsByTypeID(typeId, page) {
	$.ajax({
		url: 'goodsController/getGoodsListByListType.do',
		data: {
			typeId: typeId,
			page: page,
			number: 12
		},
		type: 'post',
		dataType: 'json',
		success: function(resultInfo) {
			var resultCode = resultInfo.resultCode;
			var resultMessage = resultInfo.resultMessage;
			var ulBox = $('.category_bd .item-list');
			ulBox.empty();
			if(resultCode == '0000') {
				var resultList = resultInfo.rows;
				for(var i=0; i < resultList.length; i++) {
					ulBox.append("<li class='product-item'>" + 
							"<a class='pro_item_pic' href='platform/goodsDetail.jsp?goodsId=" + resultList[i].GOODS_ID + "'>" + 
								"<img src='" + resultList[i].FILE_PATH + "'>" + 
								"</img>" + 
							"</a>" + 
							"<div class='pro_item_info'>" + 
								"<p class='pro_item_desc'>" + 
									"<a href='platform/goodsDetail.jsp?goodsId=" + resultList[i].GOODS_ID + "'>" + resultList[i].GOODS_TITLE + "</a>" + 
								"</p>" + 
								"<p class='pro_item_price'>" + 
									"<em>¥</em>" + 
									"<span>" + resultList[i].GOODS_RESALE + "</span>" + 
								"</p>" + 
							"</div>" + 
						"</li>");
	    		}
	    		// 刷新页面高度
	    		window.parent.refreshIframeHeight();
			}
		},
		error: function(resultInfo) {
			alert('咦, 服务器跑路了~');
		}
	});
}


/* 获得总页数 */
function initPageInfo(typeId){
	var userId = window.parent.getUserID();
	$.ajax({
		url:'goodsController/getGoodsTotalByListType.do',
		data:{
			typeId: typeId,
			number: '12'
		},
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
						+ '<li onclick="changePageLeft(' + typeId + ')"><a href="javascript:void(0);">&laquo;</a></li>';
					for(var i = 0; i < resultData; i++) {
						if(i==0){
							htmlStr += '<li onclick="changePage(this,' + (i) + ',' + typeId + ')" pageIndex=' + i + ' class="active"><a href="javascript:void(0);">' + (i+1) + '</a></li>';
						}else{
							htmlStr += '<li onclick="changePage(this,' + (i) + ',' + typeId + ')" pageIndex=' + i + '><a href="javascript:void(0);">' + (i+1) + '</a></li>' 
						}
					}
					htmlStr += '<li onclick="changePageRight(' + resultData + ',' + typeId + ')"><a href="javascript:void(0);">&raquo;</a></li></ul>';
					pageDiv.append(htmlStr);
				}
			}else{
			}
		}
	});
}

// 点击某一页切换样式并且请求对应页的数据
function changePage(ele, page, typeId){
	$('#page_div li').removeClass('active');
	$(ele).attr('class','active');
	// 请求对应页的数据
	getGoodsByTypeID(typeId, page);
}

// 点击上一页
function changePageLeft(typeId){
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if(pageIndex==0){
	}else{
		var pageIndexElement = $('#page_div li')[pageIndex];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class','active');
		if(typeId==null || typeId==undefined){
			listId="";
		}
		getGoodsByTypeID(typeId, (pageIndex-1));
	}
}

// 点击下一页
function changePageRight(total,typeId){
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if(pageIndex==(total-1)){
	}else{
		var pageIndexElement = $('#page_div li')[parseInt(pageIndex)+2];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class','active');
		
		if(typeId==null || typeId==undefined){
			typeId="";
		}
		getGoodsByTypeID(typeId, (parseInt(pageIndex)+1));
	}
}
