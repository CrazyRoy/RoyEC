<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String orderId = request.getParameter("orderId");
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
<link rel="stylesheet" type="text/css" href="platform/css/basic.css" />
<link rel="stylesheet" type="text/css"
	href="platform/css/orderDetail.css" />

<title>订单详情</title>
</head>
<body>
	<div class="alignCenter">
		<!-- 隐藏标签 -->
		<input id="orderId" value="<%=orderId%>" type="text"
			style="display: none;" />
		<input id="orderId" value="<%=orderId%>" type="text"
			style="display: none;" />
		<div class="page-header clearfix">
			<span>订单详情</span>
		</div>

		<div class="orderContent">
			<div class="address-content">
				<span class="titleLabel">收货人信息</span>
				<p id="address-info" class="address-info">我的合法i飞洒发生的解法接哦分的解放咔叽</p>
			</div>
			<div class="goods-content">
				<span class="titleLabel">闲置信息</span>
				<ul class="order-list">
					<li class="item clearfix">
						<div class="item-pic">
							<img id="goods_picture" class="product_pic"
								src="platform/img/girlC.jpg" />
						</div>
						<div class="title">
							<p id="goods_title" class="product_title">
								我是商品我是商品我是商品我是商品我是商品我是商品我是商品我是商品我是商品我是商品</p>
						</div>
						<div class="prices">
							<span class="resale_price"> 现价：<span
								id="goods_resale_price" class="titleMark">￥1092</span>
							</span>
						</div>
						<div class="prices">
							<span id="goods_orginal_price" class="orginal_price">
								原价：￥2324 </span>
						</div>
						<div class="condition">
							<span id="goods_condition" class="product_condition"> 八成新
							</span>
						</div>
					</li>
					<div class="info-footer">
						<p class="order-price">
							交易金额： <span id="goods_order_price" class="order-price titleMark">￥1899.00</span></br>
						</p>
						<p id="order_time" class="order_time">交易时间：2016-06-18 </p>
					</div>
				</ul>
			</div>

			<div class="detail-desc">
				<!--tabs-->
				<div class="tabs">
					<ul>
						<li class="mes active">闲置评论</li>
					</ul>
				</div>
				<!-- 评论 -->
				<div class="tabs-content">
					<div class="mode-block message active">
						<ul id="messageBox" class="item-ul">
							<li class="cl">
								<div class="clearfix">
									<div class="cl-headlogo">
										<img src="module/img/user.jpg" />
									</div>
									<div class="cl-comment">
										<div>
											<div class="cl-header clearfix">
												<span class="comment-user">辉哥</span> <span
													class="comment-time">2014-8-31 15:20 </span>
											</div>
											<div class="cl-content">
												你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？
											</div>
											<div class="clearfix" name="00">
												<div class="cl-foot thumbs-upDiv" onclick="thumbsUpClick()">
													<span class="thumbs-up"> <i id="reply"
														style="background-size: 14px 14px"></i>
													</span> ( <span>0</span> )
												</div>
												<div class="cl-foot replyDiv" onclick="replyClick(this)">
													<span class="reply"> <i id="reply"
														style="background-size: 14px 14px"></i>
													</span>回复 ( <span>0</span> )
												</div>
											</div>
										</div>
										<ul class="item-ul dashed-border-top">
											<li class="cl">
												<div class="clearfix">
													<div class="cl-headlogo">
														<img src="module/img/user.jpg" />
													</div>
													<div class="cl-comment">
														<div>
															<div class="cl-header clearfix">
																<span class="comment-user">辉哥1<i id="replyFor"
																	style="background-size: 14px 14px"></i>辉哥
																</span> <span class="comment-time">2014-8-31 15:20 </span>
															</div>
															<div class="cl-content">
																你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？
															</div>
															<div class="clearfix" name="77">
																<div class="cl-foot thumbs-upDiv"
																	onclick="thumbsUpClick()">
																	<span class="thumbs-up"> <i id="reply"
																		style="background-size: 14px 14px"></i>
																	</span> ( <span>0</span> )
																</div>
																<div class="cl-foot replyDiv" onclick="replyClick(this)">
																	<span class="reply"> <i id="reply"
																		style="background-size: 14px 14px"></i>
																	</span>回复 ( <span>0</span> )
																</div>
															</div>
														</div>
														<ul class="item-ul dashed-border-top">
															<li class="cl">
																<div class="clearfix">
																	<div class="cl-headlogo">
																		<img src="module/img/user.jpg" />
																	</div>
																	<div class="cl-comment">
																		<div>
																			<div class="cl-header clearfix">
																				<span class="comment-user">辉哥2<i
																					id="replyFor" style="background-size: 14px 14px"></i>辉哥1
																				</span> <span class="comment-time">2014-8-31 15:20 </span>
																			</div>
																			<div class="cl-content">
																				你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？
																			</div>
																			<div class="clearfix">
																				<div class="cl-foot thumbs-upDiv"
																					onclick="thumbsUpClick()">
																					<span class="thumbs-up"> <i id="reply"
																						style="background-size: 14px 14px"></i>
																					</span> ( <span>0</span> )
																				</div>
																				<div class="cl-foot replyDiv"
																					onclick="replyClick(this)">
																					<span class="reply"> <i id="reply"
																						style="background-size: 14px 14px"></i>
																					</span>回复 ( <span>0</span> )
																				</div>
																			</div>
																		</div>
																		<!-- 回复文本框 -->
																		<!-- <div class="replyPanel">
																		<textarea name="textarea-reply" class="textarea-reply"
																			placeholder="输入回复内容~"></textarea>
																		<div class="action clearfix">
																			<input type="button" class="replyBtn" onclick="publishReplyInfo()" value="发表回复" />
																		</div>
																	</div> -->
																	</div>
																</div>
															</li>
														</ul>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="module/img/user.jpg" />
								</div>
								<div class="cl-comment">
									<div>
										<div class="cl-header">
											<span class="comment-user">辉哥</span> <span
												class="comment-time">2014-8-31 15:20 </span>
										</div>
										<div class="cl-content">
											你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？
										</div>
										<div class="clearfix" name="01" id="test">
											<div class="cl-foot thumbs-upDiv" onclick="thumbsUpClick()">
												<span class="thumbs-up"> <i id="reply"
													style="background-size: 14px 14px"></i>
												</span> ( <span>0</span> )
											</div>
											<div class="cl-foot replyDiv" onclick="replyClick(this)">
												<span class="reply"> <i id="reply"
													style="background-size: 14px 14px"></i>
												</span>回复 ( <span>0</span> )
											</div>
										</div>
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="cl-headlogo">
									<img src="module/img/user.jpg" />
								</div>
								<div class="cl-comment">
									<div>
										<div class="cl-header">
											<span class="comment-user">辉哥</span> <span
												class="comment-time">2014-8-31 15:20 </span>
										</div>
										<div class="cl-content">
											你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？你是猴子派来的救兵来恶心我的吗？
										</div>
										<div class="clearfix"">
											<div class="cl-foot thumbs-upDiv" onclick="thumbsUpClick()">
												<span class="thumbs-up"> <i id="reply"
													style="background-size: 14px 14px"></i>
												</span> ( <span>0</span> )
											</div>
											<div class="cl-foot replyDiv" onclick="replyClick(this)">
												<span class="reply"> <i id="reply"
													style="background-size: 14px 14px"></i>
												</span>回复 ( <span>0</span> )
											</div>
										</div>
									</div>
								</div>
							</li>
							<li class="cl clearfix">
								<div class="comment clearfix">
									<textarea id="message-textares" name="textarea"
										class="comment-area" onkeyup="words_deal();"
										placeholder="发表您的评论~"></textarea>
									<span>剩余&nbsp;<span class="textCount">100</span>&nbsp;个字
									</span>
									<button class="button" onclick="publishComment()">发表评论</button>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
	<script type="text/javascript"
		src="platform/js/orderDetail/orderDetail.js"></script>
</body>
</html>