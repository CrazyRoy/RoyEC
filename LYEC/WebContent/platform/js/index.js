/**
 * Created by Roy on 17/5/5.
 */

$(document).ready(function(){
	
	// 用户登录状态判断
	judegUserLoginStatus();
	
	// 商品板块添加点击事件
	$('#myNav .container .menu-list li').click(function(){
    	var index = $(this).index();
    	if(index == 2 || index == 3) {
    		if(getUserLoginStatus() == false) {
    			return;
    		}
    	} 
    	$(this).addClass("active").siblings().removeClass("active");
    });
	
	$("#iframepage")[0].onload = refreshIframeHeight;
});

// 获取页面现在的页面高度然后赋值给iframe的高
function refreshIframeHeight() {
	console.log('refresh');
	$("#iframepage").height(10).height($("#iframepage")[0].contentWindow.document.documentElement.scrollHeight)
}

function autoHeight() {
	console.log('refresh=====');
	var thisz = $("#iframepage");
    $("#iframepage").height(thisz.contentWindow.document.documentElement.offsetHeight);
}

/* 获取用户登录状态 */
function getUserLoginStatus() {
	var user_id = $('#USERID').val();
	if(user_id == null || user_id == "" || user_id == "null" || user_id == undefined) {
		return false;
	}else {
		return true;
	}
}

/* 用户冻结状态 */
function getUserFreeStatus() {
	var isFree = $('#USERSTATUS').val();
	if(isFree == null || isFree == "" || isFree == "0" || isFree == 0) {
		return false;
	}else {
		return true;
	}
}

/* 获取用户ID */
function getUserID() {
	var user_id = $('#USERID').val();
	return user_id;
}

/* 用户登录状态判断 */
function judegUserLoginStatus() {
	var user_id = $('#USERID').val();
	var userInfoBox = $('#user-info');
	var htmlStr = '';
	userInfoBox.empty();
	if(getUserLoginStatus() == false) {
		htmlStr = '<li><a href="#" data-toggle="modal" data-target="#login">Sign in</a></li>'
			+ '<li><a href="#" data-toggle="modal" data-target="#registe">Sign up</a></li>';
		
	}else {
		var user_account = $('#USERACCOUNT').val();
		var user_name = $('#USERNAME').val();
		var htmlStr = '<li class="dropdown">'
						+ '<a href="#" class="dropdown-toggle" data-toggle="dropdown">'
						+ user_account + '<b class="caret"></b>'
						+ '</a>'
			            + '<ul class="dropdown-menu">'
			            	+ '<li><a href="platform/userInfo.jsp" target="centerPage">个人中心</a></li>'
			            	+ '<li class="divider"></li>'
			            	+ '<li><a href="platform/shoppingCard.jsp" target="centerPage">我的购物车</a></li>'
			            	+ '<li class="divider"></li>'
			            	+ '<li><a href="platform/myOrder.jsp" target="centerPage">我的订单</a></li>'
			            + '</ul>'
			            + '</li>'
			        + '<li><a href="javascript:void(0);" onclick="loginOut()">Login out</a></li>';
	}
	userInfoBox.append(htmlStr);
}

/*登录操作*/
function loginIn() {
	var account = $('#input-login-userName').val().trim();
	var pwd = $('#input-login-password').val().trim();
	if(account.length == 0 || pwd.length == 0) {
		alert("账户、密码不能为空！");
		if(account.length == 0) {
			$('#input-login-userName').focus();
		}else {
			$('#input-login-password').focus();
		}
	}else if(pwd.length < 4) {
		alert("密码不能少于4位数！");
		$('#input-login-password').focus();
	}else {
		$.ajax({
	    	url: 'userController/login.do',
	    	type: 'post',
	    	data: {
	    			userAccount: account,
	    		    userPwd: pwd,
	    		    type:"1",
	    		},
	    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    	dataType: 'json',
	    	success: function(json){
	    		if(json.resultCode == '0000') {// 成功
	    			console.log(json.resultMessage);
	    			$('#login').modal("hide");
	    			var userInfo = json.userInfo;
	    			$('#USERID').val(userInfo.USERID);
	    			$('#USERNAME').val(userInfo.USERNAME);
	    			$('#USERACCOUNT').val(userInfo.USERACCOUNT);
	    			$('#USERSTATUS').val(userInfo.USERSTATUS);
	    			// 刷新用户登录状态
	    			judegUserLoginStatus();
	    		}else {// 失败, 提示相应信息
	    			alert("抱歉, 账号或密码错误, 登录失败~");
	    		}
	    	},
	    	error: function(data){
	    		console.log('error');
	    	}
	    });
	}
}

/*注册操作*/
function registe() {
	var account = $('#input-registe-userAccount').val().trim();
	var pwd = $('#input-registe-password').val().trim();
	var conPwd = $('#input-registe-confirm-password').val().trim();
	var name = $('#input-registe-userName').val().trim();
	var email = $('#input-registe-email').val().trim();
	if(account.length == 0 || name.length == 0 || pwd.length == 0 ||  conPwd.length == 0 ||  email.length == 0) {
		alert("所有信息不能为空！");
	}else if(pwd.length < 4) {
		alert("密码不能少于4位数！");
	}else if(pwd != conPwd) {
		alert("两次密码输入不同！");
		$('#input-registe-confirm-password').focus();
	}else if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
	  {
		   alert("邮箱格式不正确,请重新输入!");
		   $("#input-registe-email").focus();
		  }
	else{
		$.ajax({
	    	url: 'userController/regist.do',
	    	type: 'post',
	    	data: {
	    			userAccount: account,
	    		    userPwd: pwd,
	    		    userName: name,
	    		    email: email
	    		},
	    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    	dataType: 'json',
	    	success: function(json){
	    		if(json.resultCode == '0000') {// 成功
	    			console.log(json.resultMessage);
	    			$('#registe').modal("hide");
	    			$('#login').modal("show");
	    		}else {// 失败
	    			alert("抱歉, 注册失败~");
	    		}
	    	},
	    	error: function(data){
	    		console.log('error');
	    	}
	    });
	}
}


//退出登录
function loginOut() {
	$.ajax({
		url: rootUrl + '/userController/exit.do',
		data:{type:"1"},
		type: 'post',
		dataType: '',
		success: function(resultInfo) {
			var resultCode = resultInfo.resultCode;
			var resultMessage = resultInfo.resultMessage;
			if(resultCode == '0000') {
			$('#USERID').val("");
 			$('#USERNAME').val("");
 			$('#USERACCOUNT').val("");
			}else {
				alert(resultMessage);
			}
		},
		error: function(resultInfo) {
			alert(resultInfo);
		}
	});
}


/* 发布闲置 */
function publishGoods() {
	if(getUserLoginStatus() == false){
		alert("您还未登录，还不能进行此操作~");
		return;
	}
	
	if(getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	$("#iframepage").attr("src", "platform/postGoods.jsp");
}

/* 我的闲置 */
function myIdle() {
	if(getUserLoginStatus() == false){
		alert("您还未登录，还不能进行此操作~");
		return;
	}
	
	if(getUserFreeStatus() == false) {
		alert("抱歉，您的账号已经被冻结，请联系管理员：1043208487@qq.com~");
		return;
	}
	$("#iframepage").attr("src", "platform/myIdle.jsp");
}

/* 关于我们  */
function aboutUS() {
	$("#iframepage").attr("src", "platform/aboutUS.jsp");
}

//刷新当前页面
function refresh(){
    window.location.reload();
}