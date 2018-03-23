/**
 * Created by Roy on 17/5/23. 商品详情页面
 */

$(function(){
	
	// 根据订单Id获取订单信息
	getOrderInfoByOrderId();
	
	// 根据订单Id获取评论信息
	getCommentListByOrderId();
});

/* 根据订单Id获取订单信息 */
function getOrderInfoByOrderId(){
	var orderId = $('#orderId').val();
	if(orderId == null || orderId == "null" || orderId == undefined) {
		console.log("null");
		return;
	}else {
		$.ajax({
			url: "orderController/getOrderInfoByOrderId.do",
			type: 'POST',
	        data: {
	        	orderId: orderId
	        },
	        dataType: 'json',
	        success: function(returnInfo){
	        	console.log(returnInfo);
	        	var resultCode = returnInfo.resultCode;
	        	var resultMessage = returnInfo.resultMessage;
	        	if(resultCode == "0000") {
	        		var orderInfo = returnInfo.rows[0];
	        		console.log(orderInfo);
		        	// 基本信息
	        		$('#address-info').text(orderInfo.ADDRESS_ID);
	        		$('#goods_picture').attr('src', orderInfo.FILE_PATH);
	        		$('#goods_title').text(orderInfo.GOODS_TITLE);
	        		$('#goodsCurrentImg').attr("src", orderInfo.FILE_URL);
	        		$('#goods_resale_price').text(orderInfo.GOODS_RESALE);
	        		$('#goods_orginal_price').text(orderInfo.GOODS_ORIGINAL);
	        		$('#goods_condition').text(orderInfo.GOODS_CONDITION);
	        		$('#goods_order_price').text(orderInfo.GOODS_RESALE);
	        		$('#order_time').text(orderInfo.ORDER_TIME);
	        	}
	        },
	        error: function(returnInfo) {
	        	alert(returnInfo);
	        }
		});
	}
}


/* 根据订单Id获取评论信息  */
function getCommentListByOrderId() {
	var orderId = $('#orderId').val();
	$.ajax({
		url: 'commentController/getCommentInfoByOrderId.do',
		type: 'POST',
		data: {
			orderId: orderId
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			var comments = returnInfo.rows;
			var messageBox = $('#messageBox');
			messageBox.empty();
			if(resultCode == '0000') {
				var htmlStr = '';
				for(var i = 0; i < comments.length; i++) {
					var imagePath = comments[i].FILE_URL;
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
									+ '<span class="comment-user">' + comments[i].USER_NAME + '</span> <span class="comment-time">' + comments[i].COMMENT_TIME + '</span>'
								+ '</div>'
								+ '<div class="cl-content">'
									+ comments[i].COMMENT_CONTENT 
								+ '</div>'
								+ '<div class="clearfix">'
									+ '<div class="cl-foot replyDiv" onclick="replyClick(this, ' + comments[i].COMMENT_ID + ')">'
										+ '<span class="reply"> <i id="reply" style="background-size: 14px 14px"></i>'
										+ '</span>回复'
									+ '</div>'
								+ '</div>'
							+ '</div>';
					var children = comments[i].children;	// 子回复
					if(children.length>0) {
						htmlStr += '<ul class="item-ul">';
						for(var j=0; j<children.length; j++) {
							var imagePath = children[j].FILE_URL;
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
														+ '<span class="comment-user">' + children[j].USER_NAME + '</span>'
														+ '<span class="comment-time">' + children[j].COMMENT_TIME + '</span>'
													+ '</div>'
													+ '<div class="cl-content">'
														+ children[j].COMMENT_CONTENT
													+ '</div>'
													+ '<div class="clearfix">'
													+ '<div class="cl-foot replyDiv" onclick="replyClick(this, ' + comments[i].COMMENT_ID + ')">'
														+ '<span class="reply"> <i id="reply" style="background-size: 14px 14px"></i>'
														+ '</span>回复'
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
						+ '<button class="button" onclick="publishComment()">发表评论</button>'
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
							+ '<button class="button" onclick="publishComment()">发表评论</button>'
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

/* 点击回复 */
function replyClick(thisz, commentReplyId) {
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
									+ '<input type="button" class="replyBtn" onclick="publishReplyInfo(this, ' + commentReplyId + ')" value="发表回复" />'
								+ '</div>'
						  + '</div>';
	$(thisz).parent().parent().append(replyPanelHtmlStr);
	$('.replyPanel').fadeIn("slow");
}

/* 点击发布回复 */
function publishReplyInfo(thisz, commentReplyId) {
	// 获取内容
	var parentDiv = $(thisz).parent().parent().find("[name='textarea-reply']");
	var commentContent = parentDiv.val();
	
	var orderId = $('#orderId').val(); // 订单Id
	var userId = window.parent.getUserID();	// 用户Id
	
	$.ajax({
		url: 'commentController/addCommentInfo.do',
		type: 'POST',
		data: {
			orderId: orderId,
			userId: userId,
			commentReplyId: commentReplyId,
			commentContent: commentContent
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			if(resultCode == '0000') {	
				alert("评论回复成功!");
				$('.replyPanel').fadeOut("slow");
				$('.replyPanel').remove();
				// 请求所有评论然后刷新
				getCommentListByOrderId();
			}else {
				alert("抱歉, 评论回复失败~");
			}
			window.parent.refreshIframeHeight();
		},
		error: function(returnInfo) {
			alert("抱歉, 留言发表失败~");
			$('.replyPanel').fadeOut("slow");
			$('.replyPanel').remove();
		}
	});
}

/* 点击发表评论 */
function publishComment() {
	if(window.parent.getUserLoginStatus() == false) {
		alert("请您先登录~");
		return;
	}
	
	if(window.parent.getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	
	var orderId = $('#orderId').val(); // 订单Id
	var userId = window.parent.getUserID();	// 用户Id
	
	var orderId = $('#orderId').val(); // 商品Id
	var messageContent = $('#message-textares').val();
	$.ajax({
		url: 'commentController/addCommentInfo.do',
		type: 'POST',
		data: {
			orderId: orderId,
			userId: userId,
			commentContent: messageContent
		},
		dataType: 'json',
		success: function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			if(resultCode == '0000') {	
				alert("评论发表成功!");
				// 更新评论然后刷新
				getCommentListByOrderId();
			}else {
				alert("评论发表失败~");
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