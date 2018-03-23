// 项目根路由
var rootUrl = 'http://localhost:8080/LYEC';

$(function(){
	// 默认先隐藏返回顶部
	$('.go-top').hide();
});

// 返回顶部
function backToTop(){
    $("html,body").animate({
        "scrollTop" : 0
    },300);
};

//监听屏幕滚动  显示/隐藏返回顶部
$(window).scroll(function() {
    if ($(this).scrollTop() > 200) {
        $('.go-top').fadeIn(200);
    } else {
        $('.go-top').fadeOut(200);
    }
});

// 验证手机号
function checkMobile(str) {
    var 
    re = /^1\d{10}$/
    if (re.test(str)) {
       return true;
    } else {
       return false;
    }
}
