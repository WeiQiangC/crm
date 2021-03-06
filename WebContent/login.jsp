<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		if(window.top!=window){
			window.top.location = window.location;
		}
		
		clearText();
		//自动聚焦的用户名框
		$("#loginAct").focus();
		
		$("#submitBtn").click(function(){
			login();
			}
		);
		
		//为当前页面绑定键盘触发事件
		//event.keyCode表示是哪个键
		$(window).keydown(function(event){
			if(13 == event.keyCode){
				$("#submitBtn").click();
			}
		})
	})
	
	//进行验证登录
	function login(){
		//$.trim()去掉左右空格
		var loginAct = $.trim($("#loginAct").val());
		var loginPwd = $.trim($("#loginPwd").val());
		if(loginAct == "" || loginPwd == ""){
			$("#msg").html("账号或者密码错误");
			return false;
		}
		
		$.ajax({
			url:"settings/user/login.do",
			data:{
				"loginAct":loginAct,
				"loginPwd":loginPwd
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.success == true){
					window.location.href = "workbench/index.jsp";
				}else{
					$("#msg").html(data.msg);
				}
			}
		})
	}
	
	//清空文本框
	function clearText(){
		$("loginAct").val("");
		$("loginPwd").val("");
	}
		
</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color:red"></span>
						
					</div>
					<!-- 按钮写在form中默认就是submit提交表单(不设置按钮的type的时候)
						若设置为button则可以手动设置事件
					 -->
					<button  type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>