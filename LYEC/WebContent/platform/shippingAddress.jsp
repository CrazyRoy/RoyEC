<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String goodsId = request.getParameter("goodsId");
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
	href="platform/css/shippingAddress.css" />
<title>选择收货地址</title>
</head>
<body>
	<div class="alignCenter">
		<!-- 隐藏标签 -->
		<input id="goodsId" value="<%=goodsId%>" type="text"
			style="display: none;" />
		<div class="page-header clearfix">
			<span>选择收货地址</span>
		</div>

		<div class="addressContent">
			<div class="address-list">
				<div class="clearfix" id="address-list">
<!-- 					<div>
						<span class="locationIcon"></span>
						<label>寄送至</label>
					</div>
					<div class="col-sm-11 address-item">
						<div class="input-group">
							<span class="input-group-addon"> <input type="radio"
								name="address"></span>
							<lable class="form-control address-label">重庆市 巴南区
							重庆市巴南区红光大道69号重庆理工大学 (dobby收) 18875021824 <span class="defalut-label">默认地址</span></lable>
						</div>
					</div>
					<div class="col-sm-11 address-item">
						<div class="input-group">
							<span class="input-group-addon"> <input type="radio"
								name="address"></span>
							<lable class="form-control address-label">浙江省 湖州市 南浔区
							浙江省湖州市南浔区和孚镇长超杭派超市 (李燕收) 18875021824 </lable>
						</div>
					</div>
					<div class="col-sm-11 address-item">
						<div class="input-group">
							<span class="input-group-addon"> <input type="radio"
								name="address"></span>
							<lable class="form-control address-label">浙江省 杭州市 余杭区
							浙江省杭州市余杭区闲林大道爵士风情花苑 (李燕收) 18875021824</lable>
						</div>
					</div>
					<div class="col-sm-11 address-item">
						<div class="input-group">
							<span class="input-group-addon"> <input type="radio"
								name="address"></span>
							<lable class="form-control address-label">浙江省 杭州市 西湖区
							浙江省杭州市西湖区西溪路浙大科技园 (李燕收) 18875021824</lable>
						</div>
					</div>
					<div class="col-sm-11 address-item">
						<div class="input-group">
							<span class="input-group-addon"> <input type="radio"
								name="address"></span>
							<lable class="form-control address-label">来自遥远的火星哦，地球很危险，我要回火星
							(学霸收) 18166435446</lable>
						</div>
					</div> -->
				</div>
				<div class="address-button clearfix">
					<button type="button" data-toggle="modal" data-target="#addAddressModal"
						class="addAddress-btn btn btn-large glyphicon glyphicon-plus fl">
						添加新地址</button>
					<button type="button" id="next-step"
						class="next-step btn btn-large glyphicon glyphicon-arrow-right fr" onclick="nextStep()">
						下一步</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加新地址 -->
	<div class="modal fade" id="addAddressModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel">
    <div class="modal-dialog myModelWidth" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="loginLabel">新增收货地址</h4>
            </div>
               <div class="modal-body">
                   <div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">收货地址</span>
		  				<input id="input-address-address" type="text" class="form-control" placeholder="Address" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">收货人姓名</span>
		  				<input id="input-address-userName" type="text" class="form-control" placeholder="UserName" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
		  				<span class="input-group-addon" id="basic-addon1">联系电话</span>
		  				<input id="input-address-phone" type="text" class="form-control" placeholder="PhoneNum" aria-describedby="basic-addon1">
					</div>
               </div>
               <div class="modal-footer">
                   <button id="addBtn" onClick="addAddress()" type="button" class="addBtn">确&nbsp;认&nbsp;填&nbsp;加</button>
                   <button id="resetBtn" onClick="resetClick()" type="button" class="resetBtn">重&nbsp;置</button>
               </div>
        </div>
    </div>
</div>

	<script type="text/javascript" src="link/jquery-2.1.4.min.js"></script>
  	<script type="text/javascript" src="link/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="platform/js/basic.js"></script>
	<script type="text/javascript" src="platform/js/shippingAddress/shippingAddress.js"></script>
</body>
</html>