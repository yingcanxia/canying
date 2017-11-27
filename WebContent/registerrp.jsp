<%@page import="com.cust.easyutil.PageList"%>
<%@page import="com.cust.util.PageData"%>
<%@page import="java.util.List"%>
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
			if(validateName()&validatePhone()){
				document.getElementById('formA').action = "RP/register_rp";
				document.getElementById('formA').submit();
			}
			else{
				alert("必填内容不能为空！")
			}
				
		}
		function xiugai() {
			if(validateName()&validatePhone()){
				document.getElementById('formB').action = "RP/update_rp";
				document.getElementById('formB').submit();
			}
			else{
				alert("修改内容不能为空！")
			}
				
		}
		function validatePhone(){
			var r = /^1[3|4|5|7|8][0-9]\d{8}$/;
			var p = document.getElementById("phone").value;
			if(r.test(p)){	
				$("#phoneDiv").attr("class","form-group has-success");	
				$("#phoneSpan").html("<span class='text-success'>输入合法！</span>");
				return true;
			}else{
				$("#phoneDiv").attr("class","form-group has-error");
				$("#phoneSpan").html("<span class='text-danger'>输入不合法！</span>");	
				return false;
				}
		}
		
		
		function validateName(){
			var name = document.getElementById("name").value;
			if($("#name").val()==""){	
				$("#nameDiv").attr("class","form-group has-error");
				$("#nameSpan").html("<span class='text-danger'>输入不能为空！</span>");
				
				return false;
			}else{
				$("#nameDiv").attr("class","form-group has-success");	
				$("#nameSpan").html("<span class='text-success'>输入合法！</span>");	
				return true;
				}
		}
	</script>
	<div class="container">
	<%String biaoshi=request.getParameter("biaoshi"); %>
	<%if(biaoshi.equals("a")){ %><!-- 添加使用 -->
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
				<div class="col-sm-5">
					<input type="text" class="form-control" name="pwd" id="pwd" placeholder="默认密码为123456" readonly="readonly" onblur="">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<button type="button" class="btn btn-info" onclick="zhuce()" id="register">注册</button>
					<button type="button" class="btn btn-warning" onclick="window.location.href='tablRp.jsp'">取消</button>
				</div>
			</div>
		</form>
		<%}else if(biaoshi.equals("b")){ %><!-- 修改使用 -->
		<%PageList<PageData> rplist=(PageList<PageData>)session.getAttribute("rplist"); %>
		<%String where=request.getParameter("where");%>
		<%int wei=Integer.parseInt(where); %>
		<%PageData rp=rplist.getPlist().get(wei); %>
		<form class="form-horizontal" role="form" id="formB" method="post" style="margin:50px 0px 50px 300px">
			<div class="form-group" id="nameDiv">
				<label for="name" class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-5">
					<input type="hidden" name="id" value="<%=rp.get("id")%>">
					<input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名" onblur="validateName()" value="<%=rp.get("name")%>">
					<div id="nameSpan"></div>
				</div>
			</div>
			<div class="form-group" id="phoneDiv">
				<label for="firstname" class="col-sm-2 control-label">手机号码：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="phone" id="phone" placeholder="请输入手机号码" onblur="validatePhone()" value="<%=rp.get("phone")%>">
					<div id="phoneSpan"></div>
				</div>
			</div>
			<div class="form-group" id="pwdDiv">
				<label for="lastname" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="password" id="password" placeholder="123456"  onblur="">
					<input type="hidden" name="ogid" value="<%=rp.get("fk_organization_id")%>">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-4">
					<button type="button" class="btn btn-info" onclick="xiugai()" id="register">修改</button>
					<button type="button" class="btn btn-warning" onclick="window.location.href='tablRp.jsp'">取消</button>
				</div>
			</div>
		</form>
		<% }%>
	</div>


</body>
</html>