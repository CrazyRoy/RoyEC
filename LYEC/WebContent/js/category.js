/*预加载*/
$(document).ready(function(){
	
	var mainId = $('#MAINID').val();
	var subId = $('#SUBID').val();
	
	if((mainId != null) && (mainId != "null") && (mainId != undefined))	{		// 主类别
		getMainClassNameById(mainId);		// 获取主类别名
		GetProductsByMainId(mainId);		// 获取主类别的商品
		querySubClassByMainId(mainId);		// 获取主类别下的子类别
	}else if((subId != null) && (subId != "null") && (subId != undefined)) {	// 子类别	
			getSubClassNameById(subId);		// 获取子类别名
			GetProductsBySubId(subId);		// 获取子类别的商品
	}
});

// 根据主类别id查询名称
function getMainClassNameById(mainId) {
	$.ajax({
    	url: rootUrl + '/category/queryMainClassNameById.do',
    	type: 'post',
    	data: {'mainId': mainId},
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var resultList = json.resultList;
    		$('.ec_hd .ec_tit').html(resultList[0].className);
    	},
    	error: function(data){
    		console.log('error');
    	}
    });
}

// 根据子类别id查询名称
function getSubClassNameById(subId) {
	 $.ajax({
	    	url: rootUrl + '/category/querySubClassNameById.do',
	    	type: 'post',
	    	data: {'subId': subId},
	    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    	dataType: 'json',
	    	success: function(json){
	    		var resultList = json.resultList;
	    		$('.ec_hd .ec_tit').html(resultList[0].className);
	    	},
	    	error: function(data){
	    		console.log('error');
	    	}
	    });
}

// 根据主类别id查询子类别
function querySubClassByMainId(mainID) {
	// 请求子类别数据
    $.ajax({
    	url: rootUrl + '/category/querySubClassByMainId.do',
    	type: 'post',
    	data: {'mainId': mainID},
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var divBoxs = $('.ec_category .ec_hd .ec_qrcode_item .ec_tags');
    		divBoxs.empty();
    		// 遍历json数组
    		var resultList = json.resultList;
    		for(var i=0; i<resultList.length; i++){
    			divBoxs.append("<a class='ec_tags_item' target='_self' href='category.jsp?subId=" + resultList[i].sub_id + "'>" + resultList[i].className +"</a>");
    		}
    	},
    	error: function(data){
    		console.log('error');
    	}
    });
}

//根据主类别ID获取商品
function GetProductsByMainId(mainId) {
	$.ajax({
    	url: rootUrl + '/category/queryProductByMainId.do',
    	type: 'post',
    	data: { 'mainId': mainId },
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var ulBox = $('.category_bd .item-list');
			ulBox.empty();
    		// 遍历json数组
			var resultList = json.resultList;
    		for(var i=0; i<resultList.length ;i++){
    			ulBox.append("<li class='product-item'>" + 
    							"<a class='pro_item_pic' href='productDetail.jsp?product_id=" + resultList[i].product_id + "' target='_blank'>" + 
    								"<img src='" + resultList[i].imageUrls + "' alt='" + resultList[i].title + "'>" + 
    								"</img>" + 
    							"</a>" + 
    							"<div class='pro_item_info'>" + 
    								"<p class='pro_item_desc'>" + 
    									"<a href='productDetail.jsp?product_id=" + resultList[i].product_id + "' target='self'>" + resultList[i].title + "</a>" + 
    								"</p>" + 
    								"<p class='pro_item_price'>" + 
    									"<em>¥</em>" + 
    									"<span>" + resultList[i].resalePrice + "</span>" + 
    								"</p>" + 
    							"</div>" + 
    						"</li>");
    		}
    	}, 
    	error: function(data) {
    		consloe.log('error');
    	}
    });
}

//根据子类别ID获取商品
function GetProductsBySubId(subId) {
	$.ajax({
    	url: rootUrl + '/category/queryProductBySubId.do',
    	type: 'post',
    	data: { 'subId': subId },
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var ulBox = $('.category_bd .item-list');
			ulBox.empty();
    		// 遍历json数组
			var resultList = json.resultList;
    		for(var i=0; i<resultList.length ;i++){
    			ulBox.append("<li class='product-item'>" + 
    							"<a class='pro_item_pic' href='productDetail.jsp?product_id=" + resultList[i].product_id + "' target='_blank'>" + 
    								"<img src='" + resultList[i].imageUrls + "' alt='" + resultList[i].title + "'>" + 
    								"</img>" + 
    							"</a>" + 
    							"<div class='pro_item_info'>" + 
    								"<p class='pro_item_desc'>" + 
    									"<a href='productDetail.jsp?product_id=" + resultList[i].product_id + "' target='self'>" + resultList[i].title + "</a>" + 
    								"</p>" + 
    								"<p class='pro_item_price'>" + 
    									"<em>¥</em>" + 
    									"<span>" + resultList[i].resalePrice + "</span>" + 
    								"</p>" + 
    							"</div>" + 
    						"</li>");
    		}
    	}, 
    	error: function(data) {
    		consloe.log('error');
    	}
    });
}
