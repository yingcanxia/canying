
<%@page language="java" contentType="text/html; charset=UTF-8 "
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册</title>
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Le styles -->
    <style>
    	
    
    </style>
<body style="height:800px">
<h1><center>单位负责人注册</center></h1>
	<script type="text/javascript">
		function zhuce() {
			if(validateName()&validatePhone()&validateConfirmPwd()&validatePwd()){
				document.getElementById('formA').action = "FED/register";
				document.getElementById('formA').submit();
			}
			else{
				alert("必填内容不能为空！")
			}
				
		}
		
		function validatePhone() {
			var r = /^1[3|4|5|7|8][0-9]\d{8}$/;
			var p = document.getElementById("phone").value;

			if(!r.test(p)){	
				$("#phoneDiv").attr("class","form-group has-error");	
				$("#phoneSpan").html("<span class='text-danger'>输入不合法！</span>");
				return true;
			} else {
				$
						.ajax({
							url : 'AJAX/chachong_fed_phone',
							type : 'POST',
							data : {
								"phone" : phone
							},
							dataType : 'json',
							success : function(result) {
								if (result == 1) {
									$("#nameDiv").attr("class",
											"form-group has-success");
									$("#nameSpan")
											.html(
													"<span class='text-success'>输入合法！</span>");
									return true;
								} else {
									$("#nameDiv").attr("class",
											"form-group has-error");
									$("#nameSpan")
											.html(
													"<span class='text-danger'>用户名已存在！</span>");
									reg_username_mark.innerHTML = "no";
									return false;
								}
							},
							error : function(result) {
								alert(result);
								alert('提交失败！');
								reg_username_mark.innerHTML = "no";
								return false;
							}
						});
				return true;
			}

		}
		
		function validateName() {

			var name = document.getElementById("name").value;
			if ($("#name").val() == "") {
				$("#nameDiv").attr("class", "form-group has-error");
				$("#nameSpan").html("<span class='text-danger'>输入不能为空！</span>");

				return false;
			} else {
				$
						.ajax({
							url : 'AJAX/chachong_fed_name',
							type : 'POST',
							data : {
								"name" : name
							},
							dataType : 'json',
							success : function(result) {
								if (result == 1) {
									$("#nameDiv").attr("class",
											"form-group has-success");
									$("#nameSpan")
											.html(
													"<span class='text-success'>输入合法！</span>");
									return true;
								} else {
									$("#nameDiv").attr("class",
											"form-group has-error");
									$("#nameSpan")
											.html(
													"<span class='text-danger'>用户名已存在！</span>");
									reg_username_mark.innerHTML = "no";
									return false;
								}
							},
							error : function(result) {
								alert(result);
								alert('提交失败！');
								reg_username_mark.innerHTML = "no";
								return false;
							}
						});
				return true;
			}

		}
		
		function validatePwd(){
			var r = /^\w{4,10}$/;
			var p = document.getElementById("pwd").value;
			if(r.test(p)){	
				$("#pwdDiv").attr("class","form-group has-success");	
				$("#pwdSpan").html("<span class='text-success'>输入合法！</span>");
				return true;
			}else{
				$("#pwdDiv").attr("class","form-group has-error");
				$("#pwdSpan").html("<span class='text-danger'>输入不合法！</span>");	
				return false;
				}
		}
		function validateConfirmPwd(){
			var c = document.getElementById("confirmpwd").value;
			var p = document.getElementById("pwd").value;
			if(c==p){	
				$("#confirmpwdDiv").attr("class","form-group has-success");	
				$("#confirmpwdSpan").html("<span class='text-success'>两次输入密码一致！</span>");
				return true;
			}else{
				$("#confirmpwdDiv").attr("class","form-group has-error");
				$("#confirmpwdSpan").html("<span class='text-danger'>两次输入密码不一致！</span>");	
				return false;
				}
		}
		</script>
	<div class="container">
		<form class="form-horizontal" role="form" id="formA" method="post" style="margin:50px 0px 50px 300px">
			<div class="form-group" id="nameDiv">
				<label for="name" class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-5" style="display:line;">
					<input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名" onblur="validateName()">
					<div id="nameSpan"></div>
				</div>
				<span style="font-size:15px;color:red;"><p style="margin:5px 0px 0px 0px;">(必填)</p></span>
			</div>
			<div class="form-group" id="phoneDiv">
				<label for="firstname" class="col-sm-2 control-label">手机号码：</label>
				<div class="col-sm-5" style="display:line;">
					<input type="text" class="form-control" name="phone" id="phone" placeholder="请输入手机号码" onblur="validatePhone()">
					<div id="phoneSpan"></div>
				</div>
				<span style="font-size:15px;color:red;"><p style="margin:5px 0px 0px 0px;">(必填)</p></span>
			</div>
			<div class="form-group" id="pwdDiv">
				<label for="lastname" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-5" style="display:line;">
					<input type="password" class="form-control" name="pwd" id="pwd" placeholder="请输入4-10位字母和数字组成的密码" onblur="validatePwd()">
					<div id="pwdSpan"></div>
				</div>
				<span style="font-size:15px;color:red;"><p style="margin:5px 0px 0px 0px;">(必填)</p></span>
			</div>
			<div class="form-group" id="confirmpwdDiv">
				<label for="lastname" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-5" style="display:line;">
					<input type="password" class="form-control" name="confirmpwd" id="confirmpwd" placeholder="请重新输入密码" onblur="validateConfirmPwd()">
					<div id="confirmpwdSpan"></div>
				</div>
				<span style="font-size:15px;color:red;"><p style="margin:5px 0px 0px 0px;">(必填)</p></span>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<button type="button" class="btn btn-info" onclick="zhuce()" id="register">注册</button>
					<button type="button" class="btn btn-warning" onclick="window.location.href='index.html'">取消</button>
				</div>
			</div>
		</form>
	</div>


</body>
</html>