var photoNum = 0;// 图片个数
			
$(function(){
	// 获取目录信息
	getCategoryInfo();
});

function imgBtnClick() {
	$('#add_file').click();
}

/* 获取目录信息 */
function getCategoryInfo() {
	$.ajax({
		url: "listController/getLastListInfo.do",
        type: 'POST',
        dataType: 'json',
        success: function (returnInfo) {
        	console.log(returnInfo);
        	var resultCode = returnInfo.resultCode;
        	var resultMessage = returnInfo.resultMessage;
        	if(resultCode == "0000") {
        		var listDatas = returnInfo.rows;
        		var listHtmlStr = '';
        		for(var i=0; i<listDatas.length; i++){
        			if(i==0){
        				listHtmlStr += '<option value="'+ listDatas[i].LIST_ID +'" selected="selected">'+ listDatas[i].LIST_NAME +'</option>'
        			}else{
        				listHtmlStr += '<option value="'+ listDatas[i].LIST_ID +'">'+ listDatas[i].LIST_NAME +'</option>'
        			}
        		}
        		$('#category').empty();
        		$('#category').append(listHtmlStr);
        	}else {
        		console.log(resultMessage);
        	}
        },
        error: function (returnInfo) {
            //上传失败时显示上传失败信息
        	alert(returnInfo);
        }
	});
}

/*发布闲置*/
function postGoods() {
	var goodsTitle = $('#goodsTitle').val().trim();
	var goodsDesc = $('#goodsDes').val().trim();
	var goodsResalePrice = $('#resalePrice').val().trim();
	var goodsOriginPrice = $('#originPrice').val().trim();
	var goodsCondition = $('#condition').val().trim();
	var goodslistID = $('#category').val().trim();
	var userId = window.parent.getUserID();
	
	var fileNames = '';
	var filePaths = '';
	
	if(goodsTitle.length <= 0) {
		alert("商品标题不能为空.");
	}else if(goodsDesc.length <= 10){
		alert("商品描述不能为空，并且不能少于10个汉字.");
	}else if(goodsOriginPrice.length <= 0) {
		alert("商品原价不能为空.");
	}else if(goodsResalePrice.length <= 0) {
		alert("商品现价不能为空.");
	}else if(parseFloat(goodsResalePrice) >= parseFloat(goodsOriginPrice)) {
		alert("现价不能超过原价.");
	}else if(photoNum == 0){
		alert("描述图片不能少于一张.");
	}else {
		for(var i=0; i<photoNum; i++){
			var fileName = $('#fileName'+i).val().trim();
			var filePath = $('#filePath'+i).val().trim();
			fileNames += fileName + ',';
			filePaths += filePath + ',';
		}
		fileNames = fileNames.substring(0,fileNames.length-1);
		filePaths = filePaths.substring(0,filePaths.length-1);
		var params = {
			userId:	userId,
			goodsTitle: goodsTitle,
			goodsDes: goodsDesc,
			goodsOrignal: goodsOriginPrice,
			goodsResale: goodsResalePrice,
			goodsCondition: goodsCondition,
			listId:	goodslistID,
			fileNames: fileNames,
			filePaths: filePaths
		};
		$.ajax({
	    	url: "goodsController/addGoods.do",
	    	data: params,
	        type: 'POST',
	        dataType: 'json',
	        success: function (returnInfo) {
	        	console.log(returnInfo);
	        	var resultCode = returnInfo.resultCode;
	        	var resultMessage = returnInfo.resultMessage;
	        	if(resultCode == "0000") {
	        		alert("闲置发布成功, 耐心等待管理员审核~");
	        		location.href = 'platform/myIdle.jsp';
	        	}else {
	        		alert("抱歉,闲置发布失败~");
	        	}
	        },
	        error: function (returnInfo) {
	            //上传失败时显示上传失败信息
	        	alert(returnInfo);
	        }
	    });
	}
}

/* 选择文件之后的回调方法 */
function preView(element){
	if(photoNum>=5){
		alert("上传图片到达上限");
		return;
	}
	var formData = new FormData();
	formData.append("file", element.files[0]);
	$.ajax({
    	url: "fileController/uploadFile.do",
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        dataType: 'json',
        contentType: false,
        processData: false,
        success: function (returnInfo) {
        	var resultCode = returnInfo.resultCode;
        	var resultMessage = returnInfo.resultMessage;
        	if(resultCode!="0000"){
        		var fileElement = $('#add_file');
        		fileElement.after(fileElement.clone().val(""));
        		fileElement.remove();
//        		alert(resultMessage);
//        		$('#upload_div').empty();
        		return;
        	}else{
        		var resultFile = returnInfo.resultFile;
        		var htmlStr = '';
        		htmlStr += '<img alt="" src="' + resultFile[0].filePath + '" style="height:100px;width:100px; margin-right: 10px;margin-top: 5px;">';
    			htmlStr += '<input id="fileName'+photoNum+'" style="display: none;" type="text" value="' + resultFile[0].fileName + '" />';
    			htmlStr += '<input id="filePath'+photoNum+'" style="display: none;" type="text" value="' + resultFile[0].filePath + '" />';
        		photoNum++;
        		$('#upload_div').append(htmlStr);
        		window.parent.refreshIframeHeight();
        	}
        },
        error: function (returnInfo) {
            //上传失败时显示上传失败信息
        	var fileElement = $('#add_file');
        	fileElement.after(fileElement.clone().val(""));
        	fileElement.remove();
        }
    });
}

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
        url = window.createObjectURL(file)
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(file)
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(file)
    }
    return url
}

function initFile(){
	$('#add_file').click();
}