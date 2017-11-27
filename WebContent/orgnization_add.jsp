<%@page import="com.cust.easyutil.PageList"%>
<%@page import="com.cust.util.PageData"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>防火单位注册</title>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function search() {
		document.getElementById("container").style.display = "block";
	}
</script>
<!-- Le styles -->
		<style type="text/css">
			body,
			html,
			#container {
				height: 100%;
				margin: 0px
			}
			
			.panel {
				background-color: #ddf;
				color: #333;
				border: 1px solid silver;
				box-shadow: 3px 4px 3px 0px silver;
				position: absolute;
				top: 10px;
				right: 10px;
				border-radius: 5px;
				overflow: hidden;
				line-height: 20px;
			}
			
		
		</style>
		<style type="text/css">
			body,
			html,
			#container {
				height: 100%;
				margin: 0px
			}
			
			.panel {
				background-color: #ddf;
				color: #333;
				border: 1px solid silver;
				box-shadow: 3px 4px 3px 0px silver;
				position: absolute;
				top: 10px;
				right: 10px;
				border-radius: 5px;
				overflow: hidden;
				line-height: 20px;
			}
			h1{
			
				text-align: center;
			
			}
		
		</style>
<style type="text/css">
body, html, #container {
	height: 100%;
	margin: 0px
}

.panel {
	background-color: #ddf;
	color: #333;
	border: 1px solid silver;
	box-shadow: 3px 4px 3px 0px silver;
	position: absolute;
	top: 10px;
	right: 10px;
	border-radius: 5px;
	overflow: hidden;
	line-height: 20px;
}
</style>

<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=7308a10a069b06b3b6c44d0d3d5e0eba&plugin=AMap.Geocoder"></script>
</head>
<body style="min-height: 100%; height: 800px;">
	<h1>
	<%
			String biaoshi = request.getParameter("biaoshi");
			if (biaoshi.equals("a")) {
		%>
		<center>防火单位注册</center>
		<%}else{ %>
		<center>防火单位修改</center>
		<%} %>
	</h1>
	<script type="text/javascript">
		
		function chenkpwd() {
			var reg_password = document.getElementById('passwd').value
					.toLowerCase();
			var t = /^\w{4,10}$/;
			if (!t.test(reg_password)) {
				alert("密码不符合要求");
				return false;
			}
			return true;
		}
		function check() {
			var reg_password = document.getElementById('passwd').value
					.toLowerCase();
			var reg_retry_password = document.getElementById('passwd_retry').value
					.toLowerCase();
			if (reg_password != reg_retry_password) {
				alert("两次密码不一样");
				return false;
			}
			return true;
		}

		function validatePhone() {
			var r = /^1[3|4|5|7|8][0-9]\d{8}$/;
			var p = document.getElementById("phone").value;
			if (r.test(p)) {
				$("#phoneDiv").attr("class", "form-group has-success");
				$("#phoneSpan").html("<span class='text-success'>输入合法！</span>");
				return true;
			} else {
				$("#phoneDiv").attr("class", "form-group has-error");
				$("#phoneSpan").html("<span class='text-danger'>输入不合法！</span>");
				return false;
			}
		}

		function validateAddress() {

			if ($("#tipinput").val() == "") {
				$("#addressDiv").attr("class", "form-group has-error");
				$("#addressSpan").html(
						"<span class='text-danger'>地址不能为空！</span>");
				return false;
			} else {
				$("#addressDiv").attr("class", "form-group has-success");
				$("#addressSpan").html(
						"<span class='text-success'>输入合法！</span>");
				return true;
			}
		}

		function validateGps() {

			if ($("#input").val() == "") {
				$("#gpsDiv").attr("class", "form-group has-error");
				$("#gpsSpan").html("<span class='text-danger'>GPS不能为空！</span>");
				return false;
			} else {
				$("#gpsDiv").attr("class", "form-group has-success");
				$("#gpsSpan").html("<span class='text-success'>合法！</span>");
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
							url : 'OG/chachong',
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

		function promptName() {
			$("#nameDiv").attr("class", "form-group");
			$("#nameSpan").html("<span>请输入所在单位的全称！</span>");
		}
		function promptPhone() {
			$("#phoneDiv").attr("class", "form-group");
			$("#phoneSpan").html("<span>请输入联系方式（固话或手机）！</span>");
		}
		function promptAddress() {
			$("#addressDiv").attr("class", "form-group");
			$("#addressSpan").html("<span>请输入所在单位的详细地址！</span>");
		}
		function promptGps() {
			$("#gpsDiv").attr("class", "form-group");
			$("#gpsSpan").html("<span>请在地图上点选！</span>");
		}
		function promptLevel() {
			$("#levleDiv").attr("class", "form-group");
			$("#levelpan").html("<span>请选择防火等级！</span>");
		}
	</script>

	<div class="container">
		<%if (biaoshi.equals("a")) {%>
		<form class="form-horizontal" role="form" id="form" method="post"
			style="margin: 50px 0px 50px 350px">
			<div class="form-group" id="nameDiv">
				<label for="name" class="col-sm-2 control-label">单位名称：</label>
				<div class="col-sm-5" style="display: line;">
					<input type="text" class="form-control" name="name" id="name"
						placeholder="请输入单位名称" onblur="validateName()"
						onfocus="promptName()">
					<div id="nameSpan"></div>
				</div>
				<span style="font-size: 15px; color: red;"><p style="margin: 5px 0px 0px 0px;">(必填)</p></span>
			</div>
			<div class="form-group" id="phoneDiv">
				<label for="firstname" class="col-sm-2 control-label">联系方式：</label>
				<div class="col-sm-5" style="display: line;">
					<input type="text" class="form-control" name="phone" id="phone"
						placeholder="请输入联系方式" onblur="validatePhone()"
						onfocus="promptPhone()">
					<div id="phoneSpan"></div>
				</div>
				<span style="font-size: 15px; color: red;"><p style="margin: 5px 0px 0px 0px;">(必填)</p></span>
			</div>

			<div class="form-group" id="addressDiv">
				<label for="lastname" class="col-sm-2 control-label">单位地址：</label>
				<div class="col-sm-5" style="display: line;">
					<input type="text" class="form-control" name="address"
						id="tipinput" placeholder="请输入单位地址" onblur="validateAddress()"
						onfocus="promptAddress()" onchange="search();">
					<div id="addressSpan"></div>
				</div>
				<span style="font-size: 15px; color: red;"><p style="margin: 5px 0px 0px 0px;">(必填)</p></span>
			</div>

			<!-- 显示地图 -->
			<div id="container" tabindex="0"
				style="width: 100%; height: 100%; z-index: 50; display: none; position: absolute; top: 0; left: 0;"></div>

			<div class="form-group" id="gpsDiv">
				<label for="lastname" class="col-sm-2 control-label">GPS坐标：</label>
				<div class="col-sm-5">

					<input type="text" class="form-control" name="location" id="input" placeholder="请确认定位" onfocus='this.value=""' readonly="readonly" onmouseout="validateGps()">
					<div id="gpsSpan">请在地图上点选坐标！</div>
				</div>
				<span style="font-size: 15px; color: red;"><p style="margin: 5px 0px 0px 0px;">(必选)</p></span>
			</div>
			<div class="form-group" id="levelDiv">
				<label for="lastname" class="col-sm-2 control-label">防火等级：</label>
				<div class="col-lg-5">
					<select class="form-control" name="level">
						<option value="1">等级一</option>
						<option value="2">等级二</option>
						<option value="3">等级三</option>
						<option value="4">等级四</option>
					</select>

				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<button type="button" class="btn btn-success btn-sm" onclick="tijiaoy()" id="save">保存</button>
					<button type="button" class="btn btn-info btn-sm" onclick="tijiaox()" id="next">下一步</button>
					<button type="button" class="btn btn-danger btn-sm" onclick="window.history.back(-1)">取消</button>
				</div>
			</div>
		</form>
		
		<%}else if(biaoshi.equals("b")){ %>
		<%PageList<PageData> oglist=(PageList<PageData>)session.getAttribute("oglist"); %>
		<%String where=request.getParameter("where"); %>
		<%int wei=Integer.parseInt(where); 
		PageData og=oglist.getPlist().get(wei);%>
		<form class="form-horizontal" role="form" id="form" method="post" style="margin:50px 0px 50px 350px">
			<div class="form-group" id="nameDiv">
				<label for="name" class="col-sm-2 control-label">单位名称：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="name" id="name"
						value="<%=og.get("name")%>" onfocus="promptName()"> <input
						type="hidden" name="id" value="<%=og.get("id")%>">
					<div id="nameSpan"></div>
				</div>
			</div>
			<div class="form-group" id="phoneDiv">
				<label for="firstname" class="col-sm-2 control-label">联系方式：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="phone" id="phone"
						value="<%=og.get("phone")%>" onfocus="promptPhone()">
					<div id="phoneSpan"></div>
				</div>
			</div>

			<div class="form-group" id="addressDiv">
				<label for="lastname" class="col-sm-2 control-label">单位地址：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="address"
						id="tipinput" value="<%=og.get("address")%>" onblur=""
						onfocus="promptAddress()">
					<div id="addressSpan"></div>

				</div>
			</div>
			<div class="form-group" id="gpsDiv">
				<label for="lastname" class="col-sm-2 control-label">GPS坐标：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="location" id="input" value="<%=og.get("GPS_lat")%>,<%=og.get("GPS_lon")%>" onfocus='this.value=""' readonly="readonly">
					<div id="gpsSpan"></div>
				</div>
			</div>
			<div class="form-group" id="levelDiv">
				<label for="lastname" class="col-sm-2 control-label">防火等级：</label>
				<div class="col-lg-5">
					<select class="form-control" name="level">
						<option value="1">等级一</option>
						<option value="2">等级二</option>
						<option value="3">等级三</option>
						<option value="4">等级四</option>
					</select>

				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<button type="button" class="btn btn-success btn-sm"
						onclick="xiugai()" id="save">提交</button>

					<button type="button" class="btn btn-danger btn-sm"
						onclick="window.history.back(-1)">取消</button>
				</div>
			</div>
		</form>
		<%
			}
		%>
	</div>



	<script type="text/javascript">
		function tijiaox() {
			if (validatePhone() & validateName() & validateAddress()
					& validateGps()) {
				document.getElementById('form').action = "OG/insertOrganization?biaoshi=x";//保存并进入下一步
				document.getElementById('form').submit();
			} else {
				alert("必填项不能为空，请重新填写有效信息！");
				window.location.href = "#form";
			}
		}
		function tijiaoy() {
			if (validatePhone() & validateName() & validateAddress()
					& validateGps()) {
				document.getElementById('form').action = "OG/insertOrganization?biaoshi=y";//保存
				document.getElementById('form').submit();
			} else {
				alert("必填项内容不能为空，请重新填写有效信息！");
				window.location.href = "#form";
			}

		}
		function xiugai() {
			if (validatePhone() & validateName() & validateAddress()
					& validateGps()) {
				document.getElementById('form').action = "OG/updateOrganization";//修改
				document.getElementById('form').submit();
			}

		}
	</script>
	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=7308a10a069b06b3b6c44d0d3d5e0eba&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
	<script type="text/javascript">
		var map = new AMap.Map('container', {
			resizeEnable : true,
			zoom : 13,
			center : [ 116.39, 39.9 ]
		});

		//输入提示
		var autoOptions = {
			input : "tipinput"
		};

		var auto = new AMap.Autocomplete(autoOptions);

		var placeSearch = new AMap.PlaceSearch({
			map : map
		});
		function select(e) {
			placeSearch.setCity(e.poi.adcode);
			placeSearch.search(e.poi.name); //关键字查询查询
		}
		//构造地点查询类
		AMap.event.addListener(auto, "select", select); //注册监听，当选中某条记录时会触发
		

		AMap
				.plugin(
						'AMap.Geocoder',
						function() {
							var geocoder = new AMap.Geocoder({
								city : "010" //城市，默认：“全国”
							});
							var marker = new AMap.Marker({
								map : map,
								bubble : true
							})
							var input = document.getElementById('input');
							var message = document.getElementById('message');
							map
									.on(
											'click',
											function(e) {
												marker.setPosition(e.lnglat);

												if (window.confirm("是否确认该标记？"+e.lnglat
														.getLng()
														+ ","
														+ e.lnglat.getLat())) {
													document
															.getElementById("container").style.display = "none";
													input.value = e.lnglat
															.getLng()
															+ ","
															+ e.lnglat.getLat();
													return true;

												} else {

													return false;
												}

											})

							input.onchange = function(e) {
								var address = input.value;
								geocoder
										.getLocation(
												address,
												function(status, result) {
													if (status == 'complete'
															&& result.geocodes.length) {
														marker
																.setPosition(result.geocodes[0].location);
														map.setCenter(marker
																.getPosition())
														message.innerHTML = ''
													} else {
														message.innerHTML = '无法获取位置'
													}
												})
							}

						});
	</script>

	<script type="text/javascript"
		src="http://webapi.amap.com/demos/js/liteToolbar.js"></script>
</body>
</html>