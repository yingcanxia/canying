<%@page import="com.cust.util.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="assets/css/xgmm.css" />
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<title></title>
		<script language=javascript>
			function onlyNum() {
				if(!(event.keyCode == 46) && !(event.keyCode == 8) && !(event.keyCode == 37) && !(event.keyCode == 39))
					if(!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)))
						event.returnValue = false; //执行至该语句时，阻止输入；可类比阻止冒泡原理或者禁止右键功能；
			}
			
			function validatePwd(){
				
				var r = /^\w{4,10}$/;
				var p = document.getElementById("pwd").value;
				if(r.test(p)){		
					$("#pwdSpan").append(" Success！");
					alert("!");
					return true;
				}else{
					$("#pwdSpan").append(" Error！");	
					return false;
					}
			}
			function validateConfirmPwd(){
				var c = document.getElementById("confirmpwd").value;
				var p = document.getElementById("pwd").value;
				if(c==p){	
					$("#confirmpwdSpan").html("<span>两次输入密码一致！</span>");
					return true;
				}else{
					$("#confirmpwdSpan").html("<span>两次输入密码不一致！</span>");	
					return false;
					}
			}
			
			
			
			
			
			
		</script>

	</head>

	<body>
	<%PageData user=(PageData)session.getAttribute("pduser"); %>
		<div class="waicheng">
			<p class="biaoti">修改密码</p>
			<form action="FED/updatepwd" method="post" class="biaodan">
				<label class="label1">电话号码:</label><input type="text" onkeydown="onlyNum();" disabled="disabled" maxlength="11" placeholder="请输入电话号码" value="<%=user.get("phone") %>" /><label class="labe2"></label>
				<label class="label1">原 密 码:</label><input type="password" placeholder="请输入密码"  maxlength="6" name="oldpwd"/><label class="labe2 ">(必填)</label>
				<label class="label1">新 密 码:</label><input type="password" placeholder="请输入新密码"  maxlength="6" name="newpwd" id="pwd" /><label id="pwdSpan" class="labe2 ">(必填)</label>
				<label class="label1">确认密码:</label><input type="password" placeholder="请确认密码"  maxlength="6" id="confirmpwd" /><label class="labe2 ">(必填)</label>
				<input type="submit" value="提交">
			</form>
		</div>
	</body>

</html>