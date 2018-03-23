/* 预加载 */

var selectVal = ''; // 选中地址

$(function() {
	// 获取用户的所有收货地址
	getAllAddressByUserId();
});

/* 获取用户的收货地址 */
function getAllAddressByUserId() {
	// 获取当前用户Id
	var userId = window.parent.getUserID();

	$.ajax({
		url : 'addressController/getAddressListByUserId.do',
		data : {
			userId : userId
		},
		type : 'POST',
		dataType : 'json',
		success : function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			$('#address-list').empty();
			if (resultCode == '0000') {
				var addressList = returnInfo.rows;
				var htmlStr = '<div>' + '<span class="locationIcon"></span>'
						+ '<label>寄送至</label>' + '</div>';
				var defaultTag = '';
				var active = '';
				var check = '';
				for (var i = 0; i < addressList.length; i++) {
					if (i == 0) {
						active = 'active';
						defaultTag = '<span class="defalut-label">默认地址</span>';
						check = ' checked="checked" ';
						selectVal = addressList[i].ADDRESS_CONTENT;
					} else {
						defaultTag = '';
						active = '';
						check = '';
					}
					htmlStr += '<div class="col-sm-11 address-item">'
							+ '<div class="input-group">'
							+ '<span class="input-group-addon">'
							+ '<input type="radio" ' + check
							+ ' name="address" value="'
							+ addressList[i].ADDRESS_CONTENT + '">' + '</span>'
							+ '<lable class="form-control address-label '
							+ active + '">' + addressList[i].ADDRESS_CONTENT
							+ '</lable>' + defaultTag + '</div> ' + '</div>';
				}
				$('#address-list').append(htmlStr);
				$("input:radio[name=address]").click(
						function() {
							$('#address-list .address-label').removeClass(
									'active');
							$(this).parent().parent().find(".address-label")
									.addClass("active");
							selectVal = $(this).parent().parent().find(
									".address-label").text();
						});

				$('#address-list .address-item').click(
						function() {
							var index = $(this).index();
							$('#address-list .address-item').find(
									'.address-label').removeClass("active");
							$(this).find('.address-label').addClass("active");
							var boxs = $('#address-list .address-item').find(
									'input:radio[name=address]');
							$(boxs).removeAttr('checked');
							for (var i = 0; i < boxs.length; i++) {
								if (i == index - 1) {
									$(boxs[i]).prop('checked', true);
									selectVal = $(this).find(".address-label")
											.text();
									console.log(selectVal);
								} else {
									$(boxs[i]).prop('checked', false);
								}
							}
						});
				window.parent.refreshIframeHeight();
			} else {
				alert('咦, 出错啦~');
			}
		},
		error : function(returnInfo) {
			alert("获取收货地址异常~");
		}
	});
}

/* 点击确认添加 */
function addAddress() {
	var addressVal = $('#input-address-address').val().trim();
	var usernameVal = $('#input-address-userName').val().trim();
	var phoneVal = $('#input-address-phone').val().trim();
	if ((addressVal == '' || addressVal == null)
			|| (usernameVal == '' || usernameVal == null)
			|| (phoneVal == '' || phoneVal == null)) {
		alert("收获地址信息未完善！");
		return;
	} else if (addressVal.length <= 8) {
		alert("收货地址范围太大，请描述详细~");
		return;
	} else if (usernameVal.length <= 1) {
		alert("收货人姓名不完善!");
		return;
	} else if (checkMobile(phoneVal) == false) {
		alert("联系电话格式错误!");
		return;
	}

	// 获取当前用户Id
	var userId = window.parent.getUserID();

	// 拼接收获地址用户电话
	var addressContent = addressVal + ' (' + usernameVal + ' 收) ' + phoneVal
			+ ' ';

	$.ajax({
		url : 'addressController/insertAddressInfo.do',
		data : {
			userId : userId,
			addressContent : addressContent
		},
		type : 'POST',
		dataType : 'json',
		success : function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			if (resultCode == '0000') {
				alert('添加成功！');
				$('#addAddressModal').modal('hide');
				// 重新获取收货地址
				getAllAddressByUserId();
				/*var htmlStr = '<div class="col-sm-11 address-item">'
						+ '<div class="input-group">'
						+ '<span class="input-group-addon">'
						+ '<input type="radio" name="address"></span>'
						+ '<lable class="form-control address-label">'
						+ addressContent + '</lable>' + '</div> ' + '</div>';
				$('#address-list').append(htmlStr);*/
			}
		},
		error : function(returnInfo) {
			alert('新增收货地址失败~');
		}
	});
}

/* 点击下一步  */
function nextStep() {
	if (selectVal == '' || selectVal == null) {
		alert("您还没有选择收货地址，请先添加新地址进行选择!");
		return;
	}
	var goodsId = $('#goodsId').val();
	var url = 'platform/orderConfirm.jsp?addressContent=' + selectVal
			+ '&goodsId=' + goodsId;
	location.href = url;
}

/* 点击重置  */
function resetClick() {
	$('#addAddressModal input').val("");
}