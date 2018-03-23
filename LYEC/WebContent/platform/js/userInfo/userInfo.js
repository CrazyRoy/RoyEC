/**
 * Created by Roy on 17/5/24. 个人中心页面
 */

$(function() {
	
	// 获取所有学校信息
	getAllSchoolInfo();
});

/* 获取所有学校信息  */
function getAllSchoolInfo() {
	$.ajax({
		url: 'schoolController/getSchoolList.do',
		data: {
			limit: '24',
			offset: '0',
			order: 'ASC',
			sort: 'SCHOOL_ID'
		},
		type: 'post',
		dataType: 'json',
		success: function(resultInfo) {
			var resultCode = resultInfo.resultCode;
			var resultMessage = resultInfo.resultMessage;
			if (resultCode == "0000") {
				var result = resultInfo.rows;
				console.log(result);
				$('#userSchoolInfo').empty();
				var htmlStr = '';
				for(var i=0; i<result.length; i++) {
					htmlStr += '<option value="' + result[i].SCHOOL_ID + '">' + result[i].SCHOOL_NAME + '</option>';
				}
				$('#userSchoolInfo').append(htmlStr);
			}
			// 请求用户基本信息
			getUserInfo();
		},
		error: function(resultInfo) {
			alert('咦, 服务器跑路了');
		}
	});
}

/* 请求用户基本信息 */
function getUserInfo() {
	var userId = $('#userId').val().trim();
	$.ajax({
		url : "userController/getUserInfo.do",
		data : {
			userId : userId
		},
		type : 'POST',
		dataType : 'json',
		success : function(returnInfo) {
			console.log(returnInfo);
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			if (resultCode == "0000") {
				var userInfo = (returnInfo.rows)[0];
				if (userInfo.hasOwnProperty("userPicId")) {
					$('#userPic').attr("src", userInfo.userPicPath);
				} else {
					$('#userPic').attr("src", "module/img/user.jpg");
				}
				$('#userAccount').val(userInfo.userAccount);
				$('#userRealName').val(userInfo.userName);
				$('#userPhoneNo').val(userInfo.userPhone);
				$('#userEmail').val(userInfo.userEmail);
				$('input[name="sex"]').val(userInfo.userSex);
				$('#userSchoolNo').val(userInfo.userSchoolNo);
				$('#userSchoolInfo').val(userInfo.schoolId);
				$('#userAddress').val(userInfo.userAddress);
				$('#userAge').val(userInfo.userAge);
				$('#userAddress').val(userInfo.userAddress);
			} else {
				alert(resultMessage);
			}
		},
		error : function(dataInfo) {
			alert('咦, 服务器跑路了');
		}
	});
}

/* 点击保存用户信息 */
function editUserInfoSave() {
	var userId = $('#userId').val().trim();
	var userAccount = $('#userAccount').val().trim();
	var userRealName = $('#userRealName').val().trim();
	var userPhoneNo = $('#userPhoneNo').val().trim();
	var userSex = $('input[name="sex"]:checked').val();
	var userEmail = $('#userEmail').val().trim();
	var userAge = $('#userAge').val().trim();
	var userSchoolNo = $('#userSchoolNo').val().trim();
	var userAddress = $('#userAddress').val().trim();
	var schoolID = $('#userSchoolInfo').val();
	var userPicName = $('#userPicName').val();
	var userPicPath = $('#userPicPath').val();
	// 参数
	var params = {
		userId : userId,
		userAccount : userAccount,
		userName : userRealName,
		userPhone : userPhoneNo,
		userSex : userSex,
		userEmail : userEmail,
		userAge : userAge,
		userSchoolNo : userSchoolNo,
		userAddress : userAddress,
		schoolId : schoolID,
		userPicName : userPicName,
		userPicPath : userPicPath
	};

	// 发送修改请求
	$.ajax({
		url : "userController/updateUserInfo.do",
		data : params,
		type : 'POST',
		dataType : 'json',
		success : function(returnInfo) {
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			if (resultCode == "0000") {
				alert("信息保存成功!");
			} else {
				alert("信息保存失败~");
			}
		},
		error : function(returnInfo) {
			// 上传失败时显示上传失败信息
			var fileElement = $('#add_file');
			fileElement.after(fileElement.clone().val(""));
			fileElement.remove();
		}
	});
}

/* 点击上传图片事件 */
function initFile() {
	$('#add_file').click();
}

function preView(element) {
	var formData = new FormData();
	formData.append("file", element.files[0]);
	$.ajax({
		url : "fileController/uploadFile.do",
		type : 'POST',
		data : formData,
		async : false,
		cache : false,
		dataType : 'json',
		contentType : false,
		processData : false,
		success : function(returnInfo) {
			var resultCode = returnInfo.resultCode;
			var resultMessage = returnInfo.resultMessage;
			if (resultCode != "0000") {
				var fileElement = $('#add_file');
				fileElement.after(fileElement.clone().val(""));
				fileElement.remove();
				alert(resultMessage);
				return;
			} else {
				var resultFile = returnInfo.resultFile;
				$('#userPic').attr("src", resultFile[0].filePath);
				$('#userPicName').val(resultFile[0].fileName);
				$('#userPicPath').val(resultFile[0].filePath);
			}
		},
		error : function(returnInfo) {
			// 上传失败时显示上传失败信息
			var fileElement = $('#add_file');
			fileElement.after(fileElement.clone().val(""));
			fileElement.remove();
		}
	});
};

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
};
