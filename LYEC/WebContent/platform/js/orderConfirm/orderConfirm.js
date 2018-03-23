/**
 * Created by Roy on 17/5/28. 订单确认页面
 */

$(function(){
	
	// 收货地址
	var address = $('#addressContent').val();
	$('#address-info').text($('#addressContent').val());
	var addressLengh = address.length;
	var leftMarkIndex = address.indexOf("(");
	var rightMarkIndex = address.indexOf(")");
	
	// 收货地址分割
	var addressDes = address.substring(0,leftMarkIndex);
	var addressUserName = address.substring(leftMarkIndex + 1, rightMarkIndex-2);
	var addressPhone = address.substring(rightMarkIndex+2, addressLengh);
	
	$('#address-des').text(addressDes);
	$('#address-name').text(addressUserName);
	$('#address-phone').text(addressPhone);
	
	// 根据商品Id获取商品信息
	getGoodsInfoById();
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
	        		$('#goods_picture').attr("src", goodsInfo.fileChildren[0].FILE_URL);
	        		$('#goods_title').text(goodsInfo.GOODS_TITLE);
	        		$('#goods_condition').text(goodsInfo.GOODS_CONDITION);
	        		$('#goods_resale_price').text(goodsInfo.GOODS_RESALE);
	        		$('#goods_orginal_price').text(' 原价：￥' + goodsInfo.GOODS_ORIGINAL);
	        		$('#goods_order_price').text('￥' + goodsInfo.GOODS_RESALE);
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

/* 确认下单  */
function submitOrder() {
	var userId = window.parent.getUserID();
	var goodsId = $('#goodsId').val();
	var address = $('#addressContent').val();
	$.ajax({
		url: "orderController/insertOrderInfoByGoodsId.do",
		type: 'POST',
        data: {
        	userId: userId,
        	goodsId: goodsId,
        	address: address
        },
        dataType: 'json',
        success: function(returnInfo){
        	console.log(returnInfo);
        	var resultCode = returnInfo.resultCode;
        	var resultMessage = returnInfo.resultMessage;
        	if(resultCode == "0000") {
        		if(window.confirm('闲置下单成功, 立即前往您的订单页面？')){
        			location.href = "platform/myOrder.jsp";
                    return true;
                 }else{
                    return false;
                }
        	}else {
        		alert("咦, 闲置下单失败啦~");
        	}
        },
        error: function(returnInfo) {
        	alert("咦, 服务器跑路啦~");
        }
	});
}
