// 记录当前板块：随机推荐0、最新闲置1、同校闲置2
var currentShow = 0;
var currentPage = 0;
var haveMore = 0;// 0: 有， 1:没有

// 换一批
function changeGoods() {
	if (haveMore == 1) {
		alert("没有更多了~");
		return;
	}
	if (currentShow == 0) {
		// 猜你喜欢
		GetGuessDatas();
	} else if (currentShow == 1) {
		// 请求最新
		GetNewsProducts();
	} else {
		// 请求同校
		GetSchoolDatas(window.parent.getUserID(), currentPage);
	}
	console.log('show: ' + currentShow + '===== page: ' + currentPage);
	currentPage++;
}

// 预加载
$(document).ready(
		function() {
			// TabItem点击事件
			$('.news .new-items .item-type li').click(
					function() {
						var indexValue = $(this).attr('name');
						if ('schoolData' == indexValue) {
							if (window.parent.getUserLoginStatus() == false) {
								alert("您还未登录,还不能进行此操作~");
								return;
							} else {
								if (currentShow != 2) {
									currentPage = 0;
									haveMore = 0;
								}
								switchTabStatus(this); // 更改状态
								GetSchoolDatas(window.parent.getUserID(),
										currentPage);
								currentShow = 2;
								currentPage++;
							}
						} else if ('newData' == indexValue) {
							if (currentShow != 1) {
								currentPage = 0;
								haveMore = 0;
							}
							switchTabStatus(this); // 更改状态
							GetNewsProducts();
							currentShow = 1;
							currentPage++;
						} else if ('guessData' == indexValue) {
							if (currentShow != 0) {
								currentPage = 0;
								haveMore = 0;
							}
							switchTabStatus(this); // 更改状态
							GetGuessDatas();
							currentShow = 0;
							currentPage++;
						}
						console.log('show: ' + currentShow + '===== page: '
								+ (currentPage - 1));
					});

			// 请求主要类别数据
			GetMainClassData();

			// 请求: 猜你喜欢
			GetGuessDatas();
			currentPage++;

			// 热门榜
			GetHotsProducts();

			// 获取分类信息
			getTypeListInfo();
		});

// 点击搜索
function searchClick() {
	var content = $('#search_input').val();
	if (content.length == 0 || content == null || content == "") {
		return;
	}
	location.href = "platform/goodsSearch.jsp?content=" + content;
}

// 发布闲置
function postGoods() {
	if (window.parent.getUserLoginStatus() == false) {
		alert("您还未登录,还不能进行此操作~");
		return;
	}

	if (window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}

	location.href = "platform/postGoods.jsp";
}

// 一键转卖
function resellGoods() {
	if (window.parent.getUserLoginStatus() == false) {
		alert("您还未登录,还不能进行此操作~");
		return;
	}

	if (window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}

	location.href = "platform/myOrder.jsp";
}

// 获取主要类别数据
function GetMainClassData() {
	// 请求主要类别数据
	$.ajax({
		url : 'listController/getFirstListInfo.do',
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			var ulBox = $('#category_list');
			ulBox.empty(); // 删除子元素
			// 遍历json数组
			var json = resultJson.rows;
			for (var i = 0; i < json.length; i++) {
				ulBox.append("<li id='list_item'" + i + "' class='list-item'>"
						+ "<div class='main-area'>"
						+ "<a href='platform/category.jsp?name="
						+ json[i].LIST_NAME + "&LIST_ID=" + json[i].LIST_ID
						+ "&PARENT_ID=" + json[i].PARENT_ID + "'>"
						+ json[i].LIST_NAME + "</a>"
						+ "<span id='list_item_span" + i
						+ "' class='main-link'>" + "</span>" + "</div>"
						+ "</li>");
				getSubClassByMainID(json[i].LIST_ID, i); // 根据id请求子类别
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
}

// 根据主要类别请求子类别数据
function getSubClassByMainID(listId, index) {
	// 请求子类别数据
	$.ajax({
		url : 'listController/getChildrenListInfoByListId.do',
		type : 'post',
		data : {
			'listId' : listId
		},
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			var spanBox = $('#list_item_span' + index);
			spanBox.empty();
			// 遍历json数组
			var json = resultJson.rows;
			for (var i = 0; i < json.length; i++) {
				spanBox.append("<a href='platform/category.jsp?name="
						+ json[i].LIST_NAME + "&LIST_ID=" + json[i].LIST_ID
						+ "&PARENT_ID=" + json[i].PARENT_ID + "'>"
						+ json[i].LIST_NAME + "</a>");
			}
		},
		error : function(data) {
			console.log('error');
		}
	});
}

// 获取随机数据
function GetGuessDatas() {
	$.ajax({
		url : 'goodsController/getGoodsListByGuess.do',
		data : {
			index : currentPage
		},
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			var ulBox = $('.news .new-items .bd .item-list ul');
			ulBox.empty();
			// 遍历json数组
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			if (resultCode == null || resultCode == undefined
					|| resultCode == "9999") {
				alert(resultMessage);
				return;
			}
			var json = resultJson.rows;
			if (json.length < 5) {
				haveMore = 1;
			}
			for (var i = 0; i < json.length; i++) {
				ulBox.append("<li class='item'>"
						+ "<div class='item-pic sh-pic'>"
						+ "<a href='platform/goodsDetail.jsp?goodsId="
						+ json[i].GOODS_ID + "' >" + "<img src='"
						+ json[i].FILE_PATH + "'/>" + "</a>" + "</div>"
						+ "<p class='title'>" + "<a href='#'>"
						+ json[i].GOODS_TITLE + "</a>" + "</p>" + "</li>");
			}
		},
		error : function(data) {
			consloe.log('error');
		}
	});
}

// 获取最新闲置数据
function GetNewsProducts() {
	$.ajax({
		url : 'goodsController/getGoodsListByCreatetime.do',
		data : {
			index : currentPage
		},
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			var ulBox = $('.news .new-items .bd .item-list ul');
			ulBox.empty();
			// 遍历json数组
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			if (resultCode == null || resultCode == undefined
					|| resultCode == "9999") {
				alert(resultMessage);
				return;
			}
			var json = resultJson.rows;
			if (json.length < 5) {
				haveMore = 1;
			}
			for (var i = 0; i < json.length; i++) {
				ulBox.append("<li class='item'>"
						+ "<div class='item-pic sh-pic'>"
						+ "<a href='platform/goodsDetail.jsp?goodsId="
						+ json[i].GOODS_ID + "' >" + "<img src='"
						+ json[i].FILE_PATH + "'/>" + "</a>" + "</div>"
						+ "<p class='title'>" + "<a href='#'>"
						+ json[i].GOODS_TITLE + "</a>" + "</p>" + "</li>");
			}
		},
		error : function(data) {
			consloe.log('error');
		}
	});
}

// 获取同校交易数据
function GetSchoolDatas(userId, index) {
	$.ajax({
		url : 'goodsController/getGoodsListBySchoole.do',
		data : {
			userId : userId,
			index : index
		},
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			var ulBox = $('.news .new-items .bd .item-list ul');
			ulBox.empty();
			// 遍历json数组
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			if (resultCode == null || resultCode == undefined
					|| resultCode == "9999") {
				alert(resultMessage);
				return;
			}
			var json = resultJson.rows;
			if (json.length < 5) {
				haveMore = 1;
			}
			for (var i = 0; i < json.length; i++) {
				ulBox.append("<li class='item'>"
						+ "<div class='item-pic sh-pic'>"
						+ "<a href='platform/goodsDetail.jsp?goodsId="
						+ json[i].GOODS_ID + "' >" + "<img src='"
						+ json[i].FILE_PATH + "'/>" + "</a>" + "</div>"
						+ "<p class='title'>" + "<a href='#'>"
						+ json[i].GOODS_TITLE + "</a>" + "</p>" + "</li>");
			}
		},
		error : function(data) {
			consloe.log('error');
		}
	});
}

// 获取数码控数据
function GetDigitalProducts(typeId) {
	$
			.ajax({
				url : 'goodsController/getGoodsListByListType.do',
				data : {
					typeId : typeId
				},
				type : 'post',
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				dataType : 'json',
				success : function(resultJson) {
					// 遍历json数组
					var resultCode = resultJson.resultCode;
					var resultMessage = resultJson.resultMessage;
					if (resultCode == "0000") {
						var ulBox = $('.floors .digitals .box_bd .digitals_list');
						ulBox.empty();
						var json = resultJson.rows;
						for (var i = 0; i < json.length; i++) {
							var temp = '';
							if ((i + 1) % 6 == 0) {
								temp = ' li-last-child';
							}
							ulBox
									.append("<li class='digitals_item"
											+ temp
											+ "'>"
											+ "<div class='digitals_item_pic'>"
											+ "<a href='platform/goodsDetail.jsp?goodsId="
											+ json[i].GOODS_ID
											+ "' class='digitals_item_pic_lk'>"
											+ "<img src='"
											+ json[i].FILE_PATH
											+ "' class='digitals_item_img'/> "
											+ "<p class='digitals_item_name'>"
											+ json[i].GOODS_TITLE
											+ "</p>"
											+ "</a>"
											+ "<span class='digitals_item_shadow'></span>"
											+ "</div>"
											+ "<p class='digitals_item_price'>"
											+ "<span class='mod_price digitals_item_price_new'>"
											+ "<i>￥</i>"
											+ "<span>"
											+ json[i].GOODS_RESALE
											+ "</span>"
											+ "</span>"
											+ "<span class='mod_price digitals_item_price_origin'>"
											+ "<i>￥</i>" + "<del>"
											+ json[i].GOODS_ORIGINAL + "</del>"
											+ "</span>" + "</p>" + "</li>");
						}
					} else {
					}
				},
				error : function(data) {
					consloe.log('error');
				}
			});
}

// 获取潮流生活数据
function GetFashionLifeProducts(typeId) {
	$.ajax({
		url : 'goodsController/getGoodsListByListType.do',
		data : {
			typeId : typeId
		},
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			// 遍历json数组
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			if (resultCode == "0000") {
				var ulBox = $('.floors .life .box_bd .life_list');
				ulBox.empty();
				var json = resultJson.rows;
				for (var i = 0; i < json.length; i++) {
					ulBox.append("<li class='life_item life_item_" + (i + 1)
							+ "'>"
							+ "<a href='platform/goodsDetail.jsp?goodsId="
							+ json[i].GOODS_ID + "' class='life_item_lk'>"
							+ "<p class='life_item_name'>"
							+ json[i].GOODS_TITLE + "</p>" + "<img src='"
							+ json[i].FILE_PATH + "' alt='" + json[i].introduce
							+ "' class='life_item_img'/>" + "</a>" + "</li>");
				}
			}
			window.parent.refreshIframeHeight();
		},
		error : function(data) {
			consloe.log('error');
		}
	});
}

// 获取热门榜数据
function GetHotsProducts() {
	$.ajax({
		url : 'goodsController/getGoodsListByListViewConts.do',
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType : 'json',
		success : function(resultJson) {
			var ulBox = $('.floors .hot .box_bd .hot_list');
			ulBox.empty();
			// 遍历json数组
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			var json = resultJson.rows;
			for (var i = 0; i < json.length; i++) {
				ulBox.append("<li class='hot_item hot_item_" + (i + 1) + "'>"
						+ "<a href='platform/goodsDetail.jsp?goodsId="
						+ json[i].GOODS_ID + "' class='hot_lk'>"
						+ "<div class='hot_rank hot_rank_" + (i + 1) + "'>"
						+ (i + 1) + "</div>" + "<div class='hot_pic'>"
						+ "<img src='" + json[i].FILE_PATH
						+ "' alt='' class='hot_img'>" + "</div>"
						+ "<p class='hot_name'>" + json[i].GOODS_TITLE + "</p>"
						+ "</a>" + "</li>");
			}
		},
		error : function(data) {
			consloe.log('error');
		}
	});
}

// 切换Tab样式和事件处理
function switchTabStatus(obj) {
	$(".news .new-items .item-type li").removeClass("selected");
	$(obj).addClass("selected");
}

// 获取数码控+潮流生活的类别信息
function getTypeListInfo() {
	$.ajax({
		url : 'typeController/getTypeList.do',
		data : {
			limit : 1000,
			offset : 0,
			order : 'ASC',
			sort : 'TYPE_ID'
		},
		type : 'post',
		dataType : 'json',
		success : function(resultJson) {
			var resultCode = resultJson.resultCode;
			var resultMessage = resultJson.resultMessage;
			if (resultCode == "0000") {
				var resultData = resultJson.rows;
				initTypePage(resultData);
			} else {
				alert(resultMessage);
			}
		}
	});
}

// 将数码控、潮流生活的类别标题信息动态添加到页面中
function initTypePage(typeDatas) {
	for (var i = 0; i < typeDatas.length; i++) {
		if (i == 0) {
			var htmlTopStr = '<div class="digitals">'
					+ '<div class="box_hd clearfix">'
					+ '<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>'
					+ '<h3 class="box_tit fl">'
					+ '<span>'
					+ typeDatas[i].TYPE_NAME
					+ '</span>'
					+ '</h3>'
					+ '<a class="box_subtit" href="platform/goodsType.jsp?TYPE_ID='
					+ typeDatas[i].TYPE_ID + '&TYPE_NAME='
					+ typeDatas[i].TYPE_NAME + '">' + typeDatas[i].TYPE_DES
					+ '<span class="box_subtit_arrow"></span>' + '</a></div>'
					+ '<div class="box_bd">'
					+ '<div class="digitals_list_wrapper clearfix">'
					+ '<ul class="digitals_list"></ul>' + '</div></div>'
					+ '</div>';
			var typeTop = $('#type_top');
			typeTop.empty();
			typeTop.append(htmlTopStr);
			// 数码控
			GetDigitalProducts(typeDatas[i].TYPE_ID);
		} else if (i == 1) {
			var htmlStr = ''
					+ '<div class="box_hd clearfix">'
					+ '<i class="box_hd_arrow"></i> <i class="box_hd_dec"></i>'
					+ '<h3 class="box_tit fl">'
					+ '<span>'
					+ typeDatas[i].TYPE_NAME
					+ '</span></h3>'
					+ '<a class="box_subtit" href="platform/goodsType.jsp?TYPE_ID='
					+ typeDatas[i].TYPE_ID + '&TYPE_NAME='
					+ typeDatas[i].TYPE_NAME + '">' + typeDatas[i].TYPE_DES
					+ '<span class="box_subtit_arrow"></span></a></div>'
					+ '<div class="box_bd">'
					+ '<ul class="life_list clearfix"></ul></div>';
			var typeCenter = $('#type_center');
			typeCenter.empty();
			typeCenter.append(htmlStr);
			// 潮流生活
			GetFashionLifeProducts(typeDatas[i].TYPE_ID);
		} else {
			break;
		}
	}
}