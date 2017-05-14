// 项目根路由
var rootUrl = 'http://localhost:8080/LYEC';

/* 预加载 */
$(document).ready(function(){
	// 导航条点击事件
	$('.header .nav .nav-menu li').click(function(){
    	$('.header .nav .nav-menu li').removeClass("active");
    	$(this).addClass("active");
    	$('#iframepage').attr("src", $(this).attr("name"));
    	runloopIframe();
    	/*window.location.href = $(this).attr("name");*/
    });
});

// 登录操作框
// 显示登录框
function showLoginBox() {
    $("#mask").show();
    $("#loginBox").fadeIn(340);
};

// 隐藏登录框
function hiddenLoginBox() {
    $('#mask').hide();
    $('#loginBox').hide();
}

// 返回顶部
function backToTop(){
    $("html,body").animate({
        "scrollTop" : 0
    },300);
};

$(window).scroll(function() {
    if ($(this).scrollTop() > 200) {
        $('.go-top').fadeIn(200);
    } else {
        $('.go-top').fadeOut(200);
    }
});