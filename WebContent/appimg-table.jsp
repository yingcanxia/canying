<%@page import="com.snb.hbm.orm.Scroll_img"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title></title>

		<link rel="stylesheet" href="css/templatemo-style.css" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
		<style>
		
			
			/* .container {
				margin-top: 20px;
				float: left;
			} */
			
			.outer {
				/* position: relative;
				top: -80px; */
				display: inline-block;
				margin-right: 20px;
				/*	border: 1px dashed blue;*/
			}
			
			.img {
				width: 300px;
				height: 230px;
				display: inline-block;
				border: 1px dashed blue;
			}
			
			.outer input {
			    position: relative; 
				display: block;
				top: 20px; 
			}
			
			.down {
				display: inline-block;
				width: 600px;
				height: 300px;
				/*background: aliceblue;*/
			}
			
			.form-group input {
				margin-top: 20px;
				display: inline-block;
			}
			.td-width{
				width: 35%;
				height: 50px;
			}
		</style>
		<script>
			function c() {
				var updatediv = document.getElementById("update");
				updatediv.style.display = "none";
				parent.document.documentElement.scrollTop = parent.document.body.scrollTop = 0;
				document.getElementById("studentbody").style.opacity = 1;
				document.getElementById("studentbody").style.filter = "alpha(opacity = 100)";
				document.getElementById("studentbody").style.backgroundColor = "#fff";
			}
			function doSubmit() {

				var txt = CKEDITOR.instances.txt.getData();
				var hrefs = txt.match(/href=\".*\"/gm);
				if (hrefs != null) {
					for ( var i = 0; i < hrefs.length; i++) {
						if(hrefs[i].substring(0,25)!="href=\"javascript:void();\""){
							txt = txt.replace(hrefs[i], "href =\"javascript:void();\" onclick=\"plus.runtime.openURL(\'"+ hrefs[i].substring(6,hrefs[i].length-1)+ "\',\'com.android.browser\');\"");
							
					}
							
					}
				}
				CKEDITOR.instances.txt.setData(txt);
				$('#frm1').submit();
			}
		</script>
		<script type="text/javascript">
													function change() {
														var pic = document
																.getElementById("preview"), file = document
																.getElementById("f");

														if (file.value == ""
																|| file.value == null) {
															alert("file.value: "
																	+ file.value);
															file.value = pic.src;
															alert("file.value: "
																	+ file.value);

														}

														var ext = file.value
																.substring(
																		file.value
																				.lastIndexOf(".") + 1)
																.toLowerCase();

														// gif在IE浏览器暂时无法显示
														if (ext != 'png'
																&& ext != 'jpg'
																&& ext != 'jpeg'
																&& ext != ""
																&& ext != null) {
															alert("图片的格式必须为png或者jpg或者jpeg格式！");
															return;
														}
														var isIE = navigator.userAgent
																.match(/MSIE/) != null, isIE6 = navigator.userAgent
																.match(/MSIE 6.0/) != null;

														if (isIE) {
															file.select();
															var reallocalpath = document.selection
																	.createRange().text;

															// IE6浏览器设置img的src为本地路径可以直接显示图片
															if (isIE6) {
																pic.src = reallocalpath;
															} else {
																// 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
																pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
																		+ reallocalpath
																		+ "\")";
																// 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
																pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															}
														} else {
															html5Reader(file);
														}
													}

													function html5Reader(file) {
														var file = file.files[0];
														var reader = new FileReader();
														reader
																.readAsDataURL(file);
														reader.onload = function(
																e) {
															var pic = document
																	.getElementById("preview");
															pic.src = this.result;
														}
													}
</script>
<script type="text/javascript">
													function change(file) {
														var pic = document
																.getElementById("preview"), file = document
																.getElementById("f");

														if (file.value == ""
																|| file.value == null) {
															alert("file.value: "
																	+ file.value);
															file.value = pic.src;
															alert("file.value: "
																	+ file.value);

														}

														var ext = file.value
																.substring(
																		file.value
																				.lastIndexOf(".") + 1)
																.toLowerCase();

														// gif在IE浏览器暂时无法显示
														if (ext != 'png'
																&& ext != 'jpg'
																&& ext != 'jpeg'
																&& ext != ""
																&& ext != null) {
															alert("图片的格式必须为png或者jpg或者jpeg格式！");
															return;
														}
														var isIE = navigator.userAgent
																.match(/MSIE/) != null, isIE6 = navigator.userAgent
																.match(/MSIE 6.0/) != null;

														if (isIE) {
															file.select();
															var reallocalpath = document.selection
																	.createRange().text;

															// IE6浏览器设置img的src为本地路径可以直接显示图片
															if (isIE6) {
																pic.src = reallocalpath;
															} else {
																// 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
																pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
																		+ reallocalpath
																		+ "\")";
																// 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
																pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															}
														} else {
															html5Reader(file);
														}
													}

													function html5Reader(file) {
														var file = file.files[0];
														var reader = new FileReader();
														reader
																.readAsDataURL(file);
														reader.onload = function(
																e) {
															var pic = document
																	.getElementById("preview");
															pic.src = this.result;
														}
													}
</script>
	</head>

	<body>
	<%List<Scroll_img>scr=(List)session.getAttribute("scr"); 
	int a=(Integer)session.getAttribute("whe");
	if(scr.size()>0&&a!=-1){
		Scroll_img si=scr.get(a-1);
	%>
		<form id="frm1" style="position:absolute;top:-30px;width: 100%;" action="si_update.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>首页图片添加</th>
								<tr>
									<td height=23 colspan="2" class='forumRow'>
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

											<tr>
												<td height="10">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
								<td class="td-width" >题目</td>
								<td class='forumRow'>
									<!--<div class="form-group">-->
										<input name='img_name' type='text' id='a_title' class="form-control" value="<%=si.getTimu()%>">
									<!--</div>-->
								</td>
							</tr>
							
							<tr>
								<td>
									<div class="outer">
										<div class="img">
											<img id="preview" width="300px" height="230px" src="<%=si.getTupian_lianjie()%>">
										</div>
										<input type="file" name="fj.upload" id="f" onchange='change()' />
									</div>
								</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="ccc" style="width: 100%;height: 100px;" class="ckeditor" id="a_content"><%=si.getTupian_neirong()%></textarea>
									</div>
								</td>
							</tr>
							
								<%-- <tr>
									<td colspan=2 >
										<div class="container">
											<div class="outer">
												<div class="img">
													<img id="preview" width="300px" height="230px" src="<%=si.getTupian_lianjie()%>">
												</div>
												<input type="file" name="fj.upload" id="f" onchange='change()' />
											</div>
											<div class="down">

												<textarea class="ckeditor" name="ccc"><%=si.getTupian_neirong() %></textarea>

											</div>

										</div>
									</td>
								</tr> --%>

								<tr>
									<td height="50" colspan=2 class='forumRow'>
										<div align="center">
											<div class="form-group">
												<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
												<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='appimg-list.jsp';">
											</div>
										</div>
									</td>
								</tr>
						</table>

					</div>
				</div>
			</div>

		</form>
		<%}else{ %>
		<form id="frm1" style="position:absolute;top:-30px;width: 100%;" action="si_save.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>首页图片添加</th>
								<tr>
									<td height=23 colspan="2" class='forumRow'>
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

											<tr>
												<td height="10">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
								<td class="td-width" >题目</td>
								<td class='forumRow'>
									<!--<div class="form-group">-->
										<input name='img_name' type='text' id='a_title' class="form-control" >
									<!--</div>-->
								</td>
							</tr>
							<tr>
								<td>
									<div class="outer">
										<div class="img">
											<img id="preview" width="300px" height="230px" src="">
										</div>
										<input type="file" name="fj.upload" id="f" onchange='change()' />
									</div>
								</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="ccc" style="width: 100%;height: 100px;" class="ckeditor" id="a_content"></textarea>
									</div>
								</td>
							</tr>
							
								<!-- <tr>
									<td colspan=2 >
										<div class="container">
											<div class="outer">
												<div class="img">
													<img id="preview" width="300px" height="230px">
												</div>
												<input type="file" name="fj.upload" id="f" onchange='change()' />
											</div>
											<div class="down">

												<textarea class="ckeditor" name="ccc"></textarea>

											</div>

										</div>
									</td>
								</tr> -->

								<tr>
									<td height="50" colspan=2 class='forumRow'>
										<div align="center">
											<div class="form-group">
												<input  onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
												<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='appimg-list.jsp';">
											</div>
										</div>
									</td>
								</tr>
						</table>

					</div>
				</div>
			</div>

		</form>
		
		<%} %>
	</body>
</html>