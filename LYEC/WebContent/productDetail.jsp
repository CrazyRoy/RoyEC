<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<!-- css外联样式 -->
<link rel="stylesheet" type="text/css"
	href="link/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/basic.css" />
<link rel="stylesheet" type="text/css" href="css/productDetail.css" />
<title>CompusEC</title>
</head>
<body>
		
		<div class="w detailContent">
			<!--顶部板块-->
			<div class="detail-head clearfix">
				<!--发布者信息头像和用户名-->
				<div class="owner-info">
					<img clss="user_img" src="img/avatar.jpg"/>
					<span class="user_name">华丽的转身</span>
				</div>
				<!--浏览量+发布日期-->
				<div class="viewCount-time">
					<div class="viewCount">
						<span>宝贝浏览次数</span>
						<span class="count">
							1
						</span>
					</div>
					<div class="time">
						<span>发布时间</span>
						<span class="publish_time">
							2017-05-13 16:30
						</span>
					</div>
				</div>
			</div>
			<!--图片+基本信息-->
			<div class="detail-center clearfix">
				<!--图片信息-->
				<div class="show-images clearfix">
					<div class="currentImgBox">
						<img src="img/girlC.jpg" alt="" class="currentImg" />
					</div>
					<div class="totalImgs">
						<ul class="image-list clearfix">
							<li class="des-img">
								<img src="img/girlC.jpg" alt="" />
							</li>
							<li class="des-img">
								<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" alt="" />
							</li>
							<li class="des-img">
								<img src="img/girlC.jpg" alt="" />
							</li>
							<li class="des-img">
								<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" alt="" />
							</li>
							<li class="des-img">
								<img src="img/girlC.jpg" alt="" />
							</li>
						</ul>
					</div>
				</div>
				<!--基本信息-->
				<div class="base-info">
					<h3 class="title">[转卖]【粉丝狂欢节】Asus/华硕 VS207DF 19.</h1>
    				<ul class="price-info">
        				<li class="price-block">
            				<span class="para">转&nbsp;&nbsp;卖&nbsp;&nbsp;价：</span>
            				<span class="priceBig"><b>&yen;</b><em>600.00</em></span>
    					</li>
						<li class="price-block">
               				<span class="para">原　　价：</span>
               				<b>&yen;</b>
                			<span>1378.00</span>
            			</li>
		    		</ul>
    				<ul class="idle-info">
						<li>
    		 				<span class="para">成　　色：</span>
             				<em>九成新</em>
       	 				</li>
       	 				<li class="contact">
                			<span class="para">联系电话：</span>
    			    		<em>18166435447</em>
    			   		</li>
    			   		<li class="school">
                			<span class="para">院　　校：</span>
    			    		<em>重庆理工大学</em>
    			   		</li>
        				<li class="address">
            				<span class="para">地　　址：</span>
            				<em>重庆市巴南区红光大道69号</em>
        				</li>
					</ul>
					<div class="actions">
						<input type="button" name="" id="buy-btn" value="立即购买" />
						<div class="ding-cai">
							<input type="button" name="" class="ding-btn" value="踩（0）" />
							<input type="button" name="" class="cai-btn" value="顶（0）" />
						</div>
					</div>
				</div>
			</div>
			<!--介绍+留言-->
			<div class="detail-desc">
				<!--tabs-->
				<div class="tabs">
					<ul>
						<li class="intro active">宝贝介绍</li>
						<li class="mes">用户留言</li>
					</ul>
				</div>
				<div class="tabs-content">
					<!--宝贝介绍-->
					<div class="mode-block introduce active">
						<p>
							这里是一些详细介绍哦
							这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦这里是一些详细介绍哦
						</p>
					</div>
					<!--留言-->
					<div class="mode-block message">
						<ul class="item-ul">
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="http://www.caobao.com/Public/User/201702/58982a6ded02e.jpg" />
								</div>
								<div class="cl-comment">
									<div class="cl-header">
										辉哥
										<span class="comment-time">2014-8-31 15:20
											<span>
									</div>
									<div class="cl-content">
										你是猴子派来的救兵来恶心我的吗？
									</div>
									<div class="cl-foot">
										<span class="like">
											<i></i>
										</span>
										(
										<span>0</span>)
									</div>
								</div>
							</li>
							<li class="clearfix">
								<ul class="pagination show">
									<li  class="active">
										<a href="#" disabled='true'>&laquo;</a>
									</li>
									<li>
										<a href="#">1</a>
									</li>
									<li>
										<a href="#">2</a>
									</li>
									<li>
										<a href="#">3</a>
									</li>
									<li>
										<a href="#">4</a>
									</li>
									<li>
										<a href="#">5</a>
									</li>
									<li>
										<a href="#">&raquo;</a>
									</li>
								</ul>
							</li>
							<li class="clearfix">
								<div class="comment clearfix">
									<textarea name="textarea" class="comment-area" onkeyup="words_deal();" placeholder="点评一下吧!"></textarea> 剩余
									<span class="textCount">5</span>个字
									<button class="button">发表评论</button>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
	<!-- 链接js文件 -->
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/category.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
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
			});
			 
			tabMes.click(function() {
			 	console.log("mes");
				intro.removeClass("active")
				tabIntro.removeClass("active");
				mess.addClass("active");
				$(this).addClass("active");
			});
			
			// 图片切换事件
			$('.detail-center .show-images .totalImgs .image-list img').mouseover(function() {
				var ind = $(this).index();
				console.log(ind);
				$('.detail-center .show-images .currentImgBox .currentImg').attr("src", $(this).attr("src"));
			});
		});
		
		function words_deal() {
			var curLength = $(".comment-area").val().length;
			if(curLength > 5) {
				var num = $(".comment-area").val().substr(0, 5);
				$(".comment-area").val(num);
				console.log("超过字数限制，多出的字将被截断！");
			} else {
				$(".textCount").text(5 - $(".comment-area").val().length);
			}
		}
	</script>
	</body>
</html>
