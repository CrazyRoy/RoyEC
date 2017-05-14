
var time = 0;   // 计时器
$(document).ready(function(){
	console.log("index");
    // 开启定时任务
    setInterval("changePage()", 3000);
    
    // 猜你喜欢/最新闲置/同校交易
    var lastItem = $('#school');
    // tab悬停事件
    $('.news .new-items .item-type li').hover(function(){
    	
    	switchTabStatus(this);	// 更改状态
    });
    
    // TabItem点击事件
    $('.news .new-items .item-type li').click(function(){
    	if('school' == $(this).attr('id')) {
    		alert('是否登录');
    	}else {
    		switchTabStatus(this);	// 更改状态
    	}
    });
    
    // 请求主要类别数据
    GetMainClassData();
    
    // 请求: 猜你喜欢
    
    // 请求: 最新闲置
    GetNewsProducts();
    
    // 请求: 校友闲置
    
    // 数码控
    GetDigitalProducts();
    
    // 潮流生活
    GetFashionLifeProducts();
    
    // 二手图书
    
    // 热门榜
    GetHotsProducts();
});

//获取主要类别数据
function GetMainClassData() {
	// 请求主要类别数据
    $.ajax({
    	url: rootUrl + '/home/queryAllMainClass.do',
    	type: 'post',
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		// var json = JSON.stringify(data);
    		var ulBox = $('.sh-category-list');
		    ulBox.empty();	// 删除子元素
    		// 遍历json数组
    		for(var i=0;i < json.length ;i++){
    			getSubClassByMainID(json[i].main_id); // 根据id请求子类别
    			ulBox.append("<li class='list-item'>"
								+ "<div class='main-area " + json[i].cssName + "'>"
									+ "<b></b>"
									+ "<a href='category.jsp?mainId=" + json[i].main_id +"' target='_self'>" + json[i].className + "</a>"
									+ "<span class='main-link'>"
									+ "</span>" + 
								"</div>" +
							"</li>");
    		 }
    	},
    	error: function(data){
    		console.log(data);
    	}
    });
}

//根据主要类别请求子类别数据
function getSubClassByMainID(Main_id) {
	// 请求子类别数据
    $.ajax({
    	url: rootUrl + '/home/querySubClassByMainId.do',
    	type: 'post',
    	data: {'main_id': Main_id},
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var liBoxs = $('.sh-category-list .list-item')[Main_id-1];
    		var spanBox = $(liBoxs).find('span');
    		spanBox.empty();
    		// 遍历json数组
    		for(var i=0; i<json.length; i++){
    			spanBox.append("<a href='category.jsp?subId=" + json[i].sub_id +"' target='_self'>" + json[i].className + "</a>");	
    		}
    	},
    	error: function(data){
    		console.log('error');
    	}
    });
}


//获取最新闲置数据
function GetNewsProducts() {
	$.ajax({
    	url: rootUrl + '/home/queryNewsProduct.do',
    	type: 'post',
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var ulBox = $('.news .new-items .bd .item-list ul');
			ulBox.empty();
    		// 遍历json数组
    		for(var i=0;i < json.length ;i++){
    			ulBox.append("<li class='item'>" + 
									"<div class='item-pic sh-pic'>" + 
										"<a href='productDetail.jsp' target='_blank'>" + 
											"<img src='" + json[i].imageUrls + "'/>" +
										"</a>" +
									"</div>" +
									"<p class='title'>" +
										"<a href='#'>" + json[i].title + "</a>" +
									"</p>" +
								"</li>");
    		}
    	}, 
    	error: function(data) {
    		consloe.log('error');
    	}
    });
}

// 获取数码控数据
function GetDigitalProducts() {
	$.ajax({
    	url: rootUrl + '/home/queryDigitalsProduct.do',
    	type: 'post',
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var ulBox = $('.floors .digitals .box_bd .digitals_list');
			ulBox.empty();
    		// 遍历json数组
    		for(var i=0;i < json.length ;i++){
    			var temp = '';
    			if((i+1)%6==0) {
    				temp = ' li-last-child';
    			}
    			ulBox.append("<li class='digitals_item" + temp + "'>" + 
    							"<div class='digitals_item_pic'>" + 
    								"<a href='#' target='_blank' class='digitals_item_pic_lk'>" + 
    									"<img src='" + json[i].imageUrls +"' class='digitals_item_img'/> " + 
    									"<p class='digitals_item_name'>" + json[i].title +"</p>" +
    								"</a>" + 
    								"<span class='digitals_item_shadow'></span>" + 
    							"</div>" + 
    							"<p class='digitals_item_price'>" +
    								"<span class='mod_price digitals_item_price_new'>" + 
    									"<i>￥</i>" + 
    									"<span>" + json[i].resalePrice +"</span>" +
    								"</span>" + 
    								"<span class='mod_price digitals_item_price_origin'>" +
    									"<i>￥</i>" + 
    									"<del>" + json[i].originalPrice +"</del>" + 
    								"</span>" + 
    							"</p>" + 
    						"</li>");
    		}
    	}, 
    	error: function(data) {
    		consloe.log('error');
    	}
    });
}

//获取潮流生活数据
function GetFashionLifeProducts() {
	$.ajax({
    	url: rootUrl + '/home/queryfashionLifesProduct.do',
    	type: 'post',
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var ulBox = $('.floors .life .box_bd .life_list');
			ulBox.empty();
    		// 遍历json数组
    		for(var i=0;i < json.length ;i++){
    			ulBox.append("<li class='life_item life_item_" + (i+1) +"'>" +
									"<a href='#' target='_blank' class='life_item_lk'>" + 
										"<p class='life_item_name'>" + json[i].title +"</p>" +
										"<img src='" + json[i].imageUrls + "' alt='" + json[i].introduce + "' class='life_item_img'/>" + 
									"</a>" +
								"</li>");
    		}
    	}, 
    	error: function(data) {
    		consloe.log('error');
    	}
    });
}

// 获取热门榜数据
function GetHotsProducts() {
	$.ajax({
    	url: rootUrl + '/home/queryHotsProduct.do',
    	type: 'post',
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: 'json',
    	success: function(json){
    		var ulBox = $('.floors .hot .box_bd .hot_list');
			ulBox.empty();
    		// 遍历json数组
    		for(var i=0;i < json.length ;i++){
    			ulBox.append("<li class='hot_item hot_item_" + (i+1) + "'>" +
										"<a href='productDetail.jsp' class='hot_lk' target='_blank'>" +
											"<div class='hot_rank hot_rank_" + (i+1) + "'>" + (i+1) + "</div>" + 
											"<div class='hot_pic'>" + 
												"<img src='" + json[i].imageUrls + "' alt='' class='hot_img'>" + 
											"</div>" +
											"<p class='hot_name'>" + json[i].title + "</p>" + 
										"</a>" +
									"</li>");
    		}
    	}, 
    	error: function(data) {
    		consloe.log('error');
    	}
    });
}


// 切换Tab样式和事件处理
function switchTabStatus(obj) {
	$(".news .new-items .item-type li").removeClass("selected");
	$(obj).addClass("selected");
}

// 更改显示
function changePage() {
    var index = time % 3;
    var items = $('.sup_ind .sup_ind_item');
    var obj = items[index];
    // 更改按钮状态
    $('.sup_ind li').removeClass("active");
    $(obj).addClass("active");
    // 更改 div 状态
    $('.sup_page').removeClass("active");
    items.each(function (i) {
        if(obj == this) {
            $('.sup_tab').find('.sup_page').eq(i).addClass("active");
        }
    });
    time++;
}

/* 点击小圆点事件 */
function bookItemClick(obj) {
    // 更改按钮状态
    $('.sup_ind li').removeClass("active");
    $(obj).addClass("active");
    // 更改 div 状态
    $('.sup_page').removeClass("active");
    var items = $('.sup_ind .sup_ind_item');
    items.each(function (i) {
        if(obj == this) {
            $('.sup_tab').find('.sup_page').eq(i).addClass("active");
            time = i;
        }
    });
};

