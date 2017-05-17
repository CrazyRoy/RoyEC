/**
 * Created by Roy on 17/5/5.
 */

/*登录操作*/

// 根据主类别id查询名称
function loginIn() {
	var account = $('.');
	var pwd = $('');
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