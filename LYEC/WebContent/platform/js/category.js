
// 记录当前LIST_ID
var listID = '';

/*预加载*/
$(function(){
	// 获取到类别名称
	var name = $('#name').val();
	$('#page-name').text(name);
	var LIST_ID = $('#LIST_ID').val();
	listID = LIST_ID;
	var PARENT_ID = $('#PARENT_ID').val();
	
	// 判断是点击父级目录点击进来的还是从子级目录点击进来的： PARENT_ID == -1 代表是父级目录
	if(PARENT_ID != null && PARENT_ID != 'null' && PARENT_ID == '-1') {	// 父级目录
		// 请求父级下面的所有子目录
		getSubListByID(LIST_ID);
	}else {	// 子目录
		$('.category-list').empty();
		getGoodsByListID(LIST_ID, '');
		initPageInfo(LIST_ID);
	}
});

// 根据父级目录ID查询自己目录
function getSubListByID(parent_id) {
	$.ajax({
		url: 'listController/getChildrenListInfoByListId.do',
    	data: {
    		listId: listID
    	},
		type: 'post',
		dataType: 'json',
		success: function(resultInfo) {
			console.log(resultInfo);
			var resultCode = resultInfo.resultCode;
			var resultMessage = resultInfo.resultMessage;
			var json = resultInfo.rows;
			$('.category-list').empty();
			if(resultCode == '0000') {
				var htmlStr = '';
				for(var i=0; i<json.length; i++) {
					if(i==0) {
						// 默认选中第一个子目录
						htmlStr += '<li class="category-item active" name="' + json[i].LIST_ID + '">' + json[i].LIST_NAME + '</li>';
					}else {
						htmlStr += '<li class="category-item" name="' + json[i].LIST_ID + '">' + json[i].LIST_NAME + '</li>';
					}
				}
				$('.category-list').append(htmlStr);
			}
			// 默认请求第一个子目录的商品
			getGoodsByListID(json[0].LIST_ID, '0');
			initPageInfo(json[0].LIST_ID);
			
			$('.alignCenter .category-item-list .category-list li').click(function() {
				$(this).addClass("active").siblings().removeClass("active");
				var list_id = $(this).attr('name');
				getGoodsByListID(list_id, '0');
				initPageInfo(list_id);
			});
		},
		error: function(resultInfo) {
			alert('咦, 服务器跑路了~');
		}
	});
}

// 根据目录ID查询该目录下的商品
function getGoodsByListID(list_id, page) {
	$.ajax({
		url: 'goodsController/getGoodsListByListId.do',
		data: {
			listId: list_id,
			page: page
		},
		type: 'post',
		dataType: 'json',
		success: function(resultInfo) {
			var resultCode = resultInfo.resultCode;
			var resultMessage = resultInfo.message;
			var ulBox = $('.category_bd .item-list');
			ulBox.empty();
			if(resultCode == '0000') {
	    		// 遍历json数组
	    		var resultList = resultInfo.rows;
	    		for(var i=0; i<resultList.length; i++){
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
function initPageInfo(listId){
	var userId = window.parent.getUserID();
	$.ajax({
		url:'goodsController/getGoodsTotalByListId.do',
		data:{
			listId: listId
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
						+ '<li onclick="changePageLeft(' + listId + ')"><a href="javascript:void(0);">&laquo;</a></li>';
					for(var i = 0; i < resultData; i++) {
						if(i==0){
							htmlStr += '<li onclick="changePage(this,' + (i) + ',' + listId + ')" pageIndex=' + i + ' class="active"><a href="javascript:void(0);">' + (i+1) + '</a></li>';
						}else{
							htmlStr += '<li onclick="changePage(this,' + (i) + ',' + listId + ')" pageIndex=' + i + '><a href="javascript:void(0);">' + (i+1) + '</a></li>' 
						}
					}
					htmlStr += '<li onclick="changePageRight(' + resultData + ',' + listId + ')"><a href="javascript:void(0);">&raquo;</a></li></ul>';
					pageDiv.append(htmlStr);
				}
			}else{
			}
		}
	});
}

// 点击某一页切换样式并且请求对应页的数据
function changePage(ele, page, listId){
	$('#page_div li').removeClass('active');
	$(ele).attr('class','active');
	// 请求对应页的数据
	getGoodsByListID(listId, page);
}

// 点击上一页
function changePageLeft(listId){
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if(pageIndex==0){
	}else{
		var pageIndexElement = $('#page_div li')[pageIndex];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class','active');
		if(listId==null || listId==undefined){
			listId="";
		}
		getGoodsByListID(listId, (pageIndex-1));
	}
}

// 点击下一页
function changePageRight(total,listId){
	var page = $('#page_div').find('.active');
	var pageIndex = $(page).attr('pageIndex');
	if(pageIndex==(total-1)){
	}else{
		var pageIndexElement = $('#page_div li')[parseInt(pageIndex)+2];
		$('#page_div li').removeClass('active');
		$(pageIndexElement).attr('class','active');
		
		if(listId==null || listId==undefined){
			listId="";
		}
		getGoodsByListID(listId, (parseInt(pageIndex)+1));
	}
}
