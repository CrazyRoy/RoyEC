/**
 * Created by Roy on 17/5/23. 商品详情页面
 */

$(function(){
	
	var tabIntro = $('.detail-desc .tabs .intro');
	var tabMes = $('.detail-desc .tabs .mes');
	var intro = $('.detail-desc .tabs-content .mode-block.introduce');
	var mess = $('.detail-desc .tabs-content .mode-block.message');
	
	tabIntro.click(function() {
	 	console.log("intro");
		mess.removeClass("active")
		tabMes.removeClass("active");
		intro.addClass("active");
		$(this).addClass("active");
		window.parent.refreshIframeHeight();
	});
	 
	tabMes.click(function() {
	 	console.log("mes");
		intro.removeClass("active")
		tabIntro.removeClass("active");
		mess.addClass("active");
		$(this).addClass("active");
		window.parent.refreshIframeHeight();
	});
	
	// 根据商品Id获取商品信息
	getGoodsInfoById();	
	
	// 根据商品Id获取用户信息
	getUserInfoByGoodsId();
	
	// 根据商品Id获取留言信息
	getMessageListByGoodsId();
});

/* 根据商品Id获取商品信息 */
function getGoodsInfoById(){
	var goodsId = $('#goodsId').val();
	if(goodsId == null || goodsId == "null" || goodsId == undefined) {
		console.log("null");
		return;
	}else {
		$.ajax({
			url: "goodsController/getGoodsListByGoodsId.do",
			type: 'POST',
	        data: {
	        	goodsId: goodsId
	        },
	        dataType: 'json',
	        success: function(returnInfo){
	        	var resultCode = returnInfo.resultCode;
	        	var resultMessage = returnInfo.resultMessage;
	        	if(resultCode == "0000") {
	        		var goodsInfo = returnInfo.rows[0];
	        		console.log(goodsInfo);
	        		// 基本信息
	        		var goodsId = goodsInfo.GOODS_ID;
	        		var goodsViewCounts = goodsInfo.GOODS_VIEWCONTS;
	        		$('#goodsViewCount').text(goodsViewCounts);
	        		$('#goodsPublishTime').text(goodsInfo.GOODS_CREATEDTIME);
	        		$('#goodsRecentAccessTime').text(goodsInfo.GOODS_RECENTACCESS);
	        		$('#goodsCurrentImg').attr("src", goodsInfo.fileChildren[0].FILE_URL);
	        		$('#goodsTitle').text(goodsInfo.GOODS_TITLE);
	        		$('#goodsCondition').text(goodsInfo.GOODS_CONDITION);
	        		$('#goodsResalePrice').text(goodsInfo.GOODS_RESALE);
	        		$('#goodsOriginalPrice').text(goodsInfo.GOODS_ORIGINAL);
	        		$('#goodsDingNum').val("顶（" + goodsInfo.GOODS_DINGNUM + "）");
	        		$('#goodsCaiNum').val("踩（" + goodsInfo.GOODS_CAINUM + "）");
	        		$('#goodsDes').text(goodsInfo.GOODS_DES);
	        		// 图片数组
	        		var imageDatas = goodsInfo.fileChildren;
	        		var listHtmlStr = '';
	        		for(var i=0; i<imageDatas.length; i++){	
	        			listHtmlStr += '<li class="des-img"><img src="' + imageDatas[i].FILE_URL + '" alt="" /></li>';
	        		}
	        		$('#goodsImageList').empty();
	        		$('#goodsImageList').append(listHtmlStr);
	        		
	        		// 图片切换事件
	        		$('.detail-center .show-images .totalImgs .image-list img').mouseover(function() {
	        			var ind = $(this).index();
	        			console.log(ind);
	        			$('.detail-center .show-images .currentImgBox .currentImg').attr("src", $(this).attr("src"));
	        		});
	        		
	        		// 访问量+1
	        		var newCount = parseInt(goodsViewCounts) + 1;
	        		addGoodsViewCount(goodsId, newCount);
	        	}else {
	        		alert("抱歉, 获取商品信息失败~");
	        	}
	        },
	        error: function(returnInfo) {
	        	alert(returnInfo);
	        }
		});
	}
}

/* 访问量+1的方法 */
function addGoodsViewCount(goodsId, viewCounts) {
	$.ajax({
		url: 'goodsController/editGoodsInfo.do',
		type: 'POST',
		data: {
			goodsId: goodsId,
			goodsViewConts: viewCounts
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
		},
		error: function(returnInfo) {
			console.log(returnInfo);
		}
	});
}

/* 根据商品Id获取用户信息 */
function getUserInfoByGoodsId() {
	var goodsId = $('#goodsId').val();
	if(goodsId == null || goodsId == "null" || goodsId == undefined) {
		console.log("null");
		return;
	}else {
		$.ajax({
			url: 'userController/getUserInfoBygoodsId.do',
			type: 'POST',
			data: {
				goodsId: goodsId
			},
			dataType: 'json',
			success: function(returnInfo) {
				var resultCode = returnInfo.resultCode;
				var resultMessage = returnInfo.resultMessage;
				if(resultCode == "0000") {
					var userInfo = returnInfo.rows[0];
					console.log(userInfo);
					// 用户基本信息
					var picPath = userInfo.USER_PICPATH;
					if(picPath == null || picPath == "null" || picPath == undefined) {
						$('#goodsUserImg').attr("src", "module/img/user.jpg");
					}else {
						$('#goodsUserImg').attr("src", picPath);
					}
					$('#goodsUserId').val(userInfo.USER_ID);
	        		$('#goodsUserName').text(userInfo.USER_NAME);
	        		$('#goodsUserPhone').text(userInfo.USER_PHONE);
	        		$('#goodsUserEmail').text(userInfo.USER_EMAIL);
	        		$('#goodsUserSchoolName').text(userInfo.SCHOOL_NAME);
	        		$('#goodsUserAddress').text(userInfo.USER_ADDRESS);
				}else {
					alert("抱歉，服务器正忙，获取数据失败啦~");
				}
			},
			error: function(returnInfo) {
				alert("抱歉，服务器正忙，获取数据失败啦~");
			}
		});
	}
}

/* 根据商品Id获取留言信息 */
function getMessageListByGoodsId() {
	var goodsId = $('#goodsId').val();
	$.ajax({
		url: 'messageController/getMessageListByGoodsId.do',
		type: 'POST',
		data: {
			goodsId: goodsId
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			var messages = returnInfo.rows;
			var messageBox = $('#messageBox');
			messageBox.empty();
			if(resultCode == '0000') {
				var htmlStr = '';
				for(var i = 0; i < messages.length; i++) {
					var imagePath = messages[i].BUYERSPIC;
					if(imagePath == null || imagePath == "null") {
						imagePath = "module/img/user.jpg";
					}
					htmlStr += '<li class="cl">'
					+ '<div class="clearfix">'
						+ '<div class="cl-headlogo">'
							+ '<img src="' + imagePath + '" />'
						+ '</div>'
						+ '<div class="cl-comment">'
							+ '<div>'
								+ '<div class="cl-header clearfix">'
									+ '<span class="comment-user">' + messages[i].BUYERSNAME + '</span> <span class="comment-time">' + messages[i].MESSAGE_TIME + '</span>'
								+ '</div>'
								+ '<div class="cl-content">'
									+ messages[i].MESSAGE_CONTENT 
								+ '</div>'
								+ '<div class="clearfix">'
									+ '<div class="cl-foot replyDiv" onclick="replyClick(this, ' + messages[i].MESSAGE_ID + ', ' + messages[i].BUYERSID + ')">'
										+ '<span class="reply"> <i id="reply" style="background-size: 14px 14px"></i>'
										+ '</span>回复 ( <span>0</span> )'
									+ '</div>'
								+ '</div>'
							+ '</div>';
					var children = messages[i].children;	// 子回复
					if(children.length>0) {
						htmlStr += '<ul class="item-ul">';
						for(var j = 0; j < children.length; j++) {
							var imagePath = children[j].BUYERSPIC;
							if(imagePath == null || imagePath == "null") {
								imagePath = "module/img/user.jpg";
							}
							htmlStr += '<li class="cl dashed-border-top">'
									+ '<div class="clearfix">'
										+ '<div class="cl-headlogo">'
										+ '<img src="' + imagePath + '" />'
										+ '</div>'
											+ '<div class="cl-comment">'
												+ '<div>'
													+ '<div class="cl-header clearfix">'
														+ '<span class="comment-user">' + children[j].BUYERSNAME + '<i id= "replyFor" style="background-size: 14px 14px"></i>' + children[j].SELLERNAME + '</span>'
														+ '<span class="comment-time">' + children[j].MESSAGE_TIME + '</span>'
													+ '</div>'
													+ '<div class="cl-content">'
														+ children[j].MESSAGE_CONTENT
													+ '</div>'
													+ '<div class="clearfix">'
													+ '<div class="cl-foot replyDiv" onclick="replyClick(this, ' + children[j].MESSAGE_PARENTID + ', ' + children[j].BUYERSID + ')">'
														+ '<span class="reply"> <i id="reply" style="background-size: 14px 14px"></i>'
														+ '</span>回复 ( <span>0</span> )'
													+ '</div>'
												+ '</div>'
											+ '</div>'
										+ '</div>'
									+ '</div>'
								+ '</li>';
						}
						htmlStr += '</ul>';
					}
					htmlStr += '</div>'
							   + '</div>'
							   + '</li>';
				}
				htmlStr += '<li class="cl clearfix">'
				   + '<div class="comment clearfix">'
				   + '<textarea id="message-textares" name="textarea" class="comment-area" onkeyup="words_deal();" placeholder="留下您的想法~"></textarea>'
				+ '<span>剩余&nbsp;<span class="textCount">100</span>&nbsp;个字'
						+ '</span>'
						+ '<button class="button" onclick="publishComment()">发表留言</button>'
						+ '</div>'
						+ '</li>';
				messageBox.append(htmlStr);
			}else {
				var messages = returnInfo.rows;
				var messageBox = $('#messageBox');
				messageBox.empty();
				var htmlStr = '';
				htmlStr += '<li class="cl clearfix">'
					   + '<div class="comment clearfix">'
					   + '<textarea id="message-textares" name="textarea" class="comment-area" onkeyup="words_deal();" placeholder="留下您的想法~"></textarea>'
					+ '<span>剩余&nbsp;<span class="textCount">100</span>&nbsp;个字'
							+ '</span>'
							+ '<button class="button" onclick="publishComment()">发表留言</button>'
							+ '</div>'
							+ '</li>';
				messageBox.append(htmlStr);
			}
			window.parent.refreshIframeHeight();
		},
		error: function(returnInfo) {
			alert("获取商品留言信息异常~");
		}
	});
}

/* 点击加入购物车 */
function addCard() {
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	var goodsUserId = $('#goodsUserId').val(); // 卖家Id
	var userId = window.parent.getUserID();	// 用户Id
	
	if(goodsUserId == userId) {
		alert('您不能对自己的闲置商品进行此操作！');
		return;
	}
	
	var goodsId = $('#goodsId').val();
	$.ajax({
		url: 'shoppingCardController/addGoodsToCardInfo.do',
		data: {
			goodsId: goodsId,
			userId: userId
		},
		type: 'POST',
		dataType: 'json',
		success: function(resultInfo) {
			console.log(resultInfo);
			var resultCode = resultInfo.resultCode;
			var resultMessage = resultInfo.resultMessage;
			if(resultCode == '0000') {
				alert("添加成功!");
			}else {
				alert('该闲置已经在您的购物车中了, 不能重复添加哦~');
			}
		},
		error: function(resultInfo) {
			alert('购物车添加异常~');
		}
	});
}

/* 点击购买 */
function buyGoods() {
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	var goodsUserId = $('#goodsUserId').val(); // 卖家Id
	var userId = window.parent.getUserID();	// 用户Id
	
	if(goodsUserId == userId) {
		alert('您不能对自己的闲置商品进行此操作！');
		return;
	}
	
	var goodsId = $('#goodsId').val();
	location.href = "platform/shippingAddress.jsp?goodsId=" + goodsId;
}

/* 点击顶 */
function dingClick() {
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	var goodsId = $('#goodsId').val();
	var dingNumVal = $('#goodsDingNum').val();
	var strLength = dingNumVal.length;
	var goodsDingNum = parseInt(dingNumVal.substring(2, strLength-1)) + 1;
	$('#goodsDingNum').val("顶（" + goodsDingNum + "）");
	
	$.ajax({
		url: 'goodsController/editGoodsInfo.do',
		type: 'POST',
		data: {
			goodsId: goodsId,
			goodsDingNum: goodsDingNum
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
		},
		error: function(returnInfo) {
			console.log(returnInfo);
		}
	});
}

/* 点击踩 */
function caiClick() {
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	var goodsId = $('#goodsId').val();
	var caiNumVal = $('#goodsCaiNum').val();
	var strLength = caiNumVal.length;
	var goodsCaiNum = parseInt(caiNumVal.substring(2, strLength-1)) + 1;
	$('#goodsCaiNum').val("踩（" + goodsCaiNum + "）");
	
	$.ajax({
		url: 'goodsController/editGoodsInfo.do',
		type: 'POST',
		data: {
			goodsId: goodsId,
			goodsCaiNum: goodsCaiNum
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
		},
		error: function(returnInfo) {
			console.log(returnInfo);
		}
	});
}


/* 点击回复 */
function replyClick(thisz, parentMesId, replyId) {
	if($(thisz).parent().parent().find(".replyPanel").length > 0) {
		$('.replyPanel').fadeOut("slow");
		$('.replyPanel').remove();
		return;
	}
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	var replyPanelHtmlStr = '<div class="replyPanel">'
								+ '<textarea name="textarea-reply" class="textarea-reply"'
									+ 'placeholder="输入回复内容~"></textarea>'
								+ '<div class="action clearfix">'
									+ '<input type="button" class="replyBtn" onclick="publishReplyInfo(this, ' + parentMesId + ', ' + replyId + ')" value="发表回复" />'
								+ '</div>'
						  + '</div>';
	$(thisz).parent().parent().append(replyPanelHtmlStr);
	$('.replyPanel').fadeIn("slow");
}

/* 点击发布回复 */
function publishReplyInfo(thisz, parentMesId, replyId) {
	// 获取内容
	var parentDiv = $(thisz).parent().parent().find("[name='textarea-reply']");
	var messageContent = parentDiv.val();
	
	var goodsId = $('#goodsId').val(); // 商品Id
	var userId = window.parent.getUserID();	// 用户Id
	
	$.ajax({
		url: 'messageController/addMessageInfo.do',
		type: 'POST',
		data: {
			goodsId: goodsId,
			messageParentId: parentMesId,
			messageContent: messageContent,
			buyersId: userId, 
			sellerId: replyId
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			if(resultCode == '0000') {	
				alert("留言回复成功!");
				$('.replyPanel').fadeOut("slow");
				$('.replyPanel').remove();
				// 请求所有留言然后刷新
				getMessageListByGoodsId();
				window.parent.refreshIframeHeight();
			}else {
				alert("抱歉, 留言发表失败~");
			}
		},
		error: function(returnInfo) {
			alert("抱歉, 留言发表失败~");
			$('.replyPanel').fadeOut("slow");
			$('.replyPanel').remove();
		}
	});
}

/* 点击发表留言 */
function publishComment() {
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	var goodsUserId = $('#goodsUserId').val(); // 卖家Id
	var userId = window.parent.getUserID();	// 用户Id
	if(goodsUserId == userId) {
		alert('您不能对自己的闲置商品进行此操作！');
		return;
	}
	
	var goodsId = $('#goodsId').val(); // 商品Id
	var messageContent = $('#message-textares').val();
	$.ajax({
		url: 'messageController/addMessageInfo.do',
		type: 'POST',
		data: {
			goodsId: goodsId,
			messageContent: messageContent,
			buyersId: userId, 
			sellerId: goodsUserId
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			if(resultCode == '0000') {	
				alert("留言发表成功!");
				// 请求所有留言然后刷新
				getMessageListByGoodsId();
			}else {
				alert("留言发表失败~");
			}
			window.parent.refreshIframeHeight();
		},
		error: function(returnInfo) {
			alert("抱歉, 留言发表失败~");
		}
	});
}

/* 评论字数处理 */
function words_deal() {
	var curLength = $(".comment-area").val().length;
	if(curLength > 100) {
		var num = $(".comment-area").val().substr(0, 100);
		$(".comment-area").val(num);
		console.log("超过字数限制，多出的字将被截断！");
	} else {
		$(".textCount").text(100 - $(".comment-area").val().length);
	}
}