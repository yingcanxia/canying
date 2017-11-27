<%@page import="com.snb.hbm.orm.Huangye_dianjia"%>
<%@page import="com.snb.bean.PageList"%>
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
.td-width {
	width: 30%;
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
	<%
		PageList<Huangye_dianjia> hydj = (PageList) session.getAttribute("hydj");
		int a = (Integer) session.getAttribute("whe");
	%>
	<%
		if (hydj != null && a != -1) {

			Huangye_dianjia hy = hydj.getPlist().get(a - 1);
			int type = hy.getHuangye_dianjia_type();
			String suoshuzhen = hy.getHuangye_dianjia_suoshuzhen();
			System.out.println("'" + suoshuzhen + "'");
	%>
	<form id="frm1" class="suqiu-form"
		style="position: absolute; top: 0px; left: 10%; width: 80%;"
		action="hydj_update.action" method="post"
		enctype="multipart/form-data">
		<div class="templatemo-content-container" id="update">
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
						class='tableBorder table  table-striped table-bordered templatemo-user-table'
						align=center>
						<!--	<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

						<th class='tableHeaderText green-bg white-text' colspan=2
							height=25>黄页</th>
						<tr>
							<td height=23 colspan="2" class='forumRow'>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">

									<tr>
										<td height="10">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="td-width">黄页种类</td>
							<td class='forumRow'>
								<div class="form-group">
									<select class="form-control" name="huangye_dianjia_type">
										<%
											String[] typeArray = {"农特产店", "企业合作社", "农资农机", "政府机关", "医疗", "物流快递", "酒店", "餐饮", "娱乐", "旅游景点", "购物",
														"出行代驾", "公共热线", "银行保险", "便民", "更多"};
												for (int i = 0; i < 15; i++) {
										%>
										<%
											if ((i + 1) == type) {
										%>
										<option value="<%=i + 1%>" selected="selected"><%=typeArray[i]%></option>
										<%
											} else {
										%>
										<option value="<%=i + 1%>"><%=typeArray[i]%></option>
										<%
											}
												}
										%>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td class="td-width">所属县/镇</td>
							<td class='forumRow'>
								<div class="form-group">
									<!--<input name='jiage_tongbao_yuefen' type='text' id='a_title' class="form-control">-->
									<select class="form-control" name='huangye_dianjia_suoshuzhen'
										id="zhenSelect">
										<%
											String[] strArray = {"抚松县","抚松镇", "松江河镇", "万良镇", "露水河镇", "东岗镇", "泉阳镇", "仙人桥镇", "北岗镇", "漫江镇", "兴参镇", "新屯子镇",
														"兴隆乡", "抽水乡", "沿江乡"};
												for (int j = 0; j < 15; j++) {
										%>
										<%
											if (strArray[j].equals(suoshuzhen)) {
										%>
										<option value="<%=strArray[j]%>" selected="selected"><%=strArray[j]%></option>
										<%
											} else {
										%>
										<option value="<%=strArray[j]%>"><%=strArray[j]%></option>
										<%
											}
												}
										%>
									</select>

								</div>
							</td>
						</tr>
						<tr>
							<td class="td-width">名称</td>
							<td class='forumRow'>
								<div class="form-group">
									<input name='huangye_dianjia_mingcheng' type='text'
										class="form-control"
										value="<%=hy.getHuangye_dianjia_mingcheng()%>">
								</div>
							</td>

						</tr>
						<tr>
							<td class="td-width">联系方式</td>
							<td class='forumRow'>
								<div class="form-group">
									<input name='huangye_dianjia_dianhua' type='text'
										class="form-control"
										value="<%=hy.getHuangye_dianjia_dianhua()%>">
								</div>
							</td>
						</tr>

						<tr>
							<td class="td-width">地址</td>
							<td class='forumRow'>
								<div class="form-group">
									<input name='huangye_dianjia_dizhi' type='text'
										class="form-control"
										value="<%=hy.getHuangye_dianjia_dizhi()%>">
								</div>
							</td>
						</tr>

						<tr>
							<td class="td-width">详情</td>
							<td class='forumRow'>
								<div class="form-group">
									<textarea id="txt" name="huangye_xiangxi" class="ckeditor"><%=hy.getHuangye_xiangxi()%></textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td class='td-width'>上传图片</td>
							<td width="85%" class='forumRowHighLight'>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img id="preview" alt=""
											该图片无法显示"" src="<%=hy.getHuangye_dianjia_tupian()%>"
											width="83px" height="83px"></td>
										<td>
											<div class="form-group" style="margin-right: 20px">
												<input name="fj.upload" type="file" id="f"
													onchange='change()'>
											
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td height="50" colspan=2 class='forumRow'>
								<div align="center">
									<div class="form-group">
										<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
										<input class="templatemo-blue-button" type="button" value="取消"
											onclick="javascript:window.location.href ='hy-list.jsp';">
									</div>
								</div>
							</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</form>
	<%
		} else {
	%>
	<form class="suqiu-form" id="frm1"
		style="position: absolute; top: 0px; left: 10%; width: 80%;"
		action="hydj_save.action" method="post" enctype="multipart/form-data">
		<div class="templatemo-content-container" id="update">
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
						class='tableBorder table  table-striped table-bordered templatemo-user-table'
						align=center>
						<!--	<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

						<th class='tableHeaderText green-bg white-text' colspan=2
							height=25>黄页</th>
						<tr>
							<td height=23 colspan="2" class='forumRow'>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">

									<tr>
										<td height="10">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td class="td-width">黄页种类</td>
							<td class='forumRow'>
								<div class="form-group">
									<select class="form-control" name="huangye_dianjia_type">
										<option value="1">农特产店</option>
										<option value="2">企业合作社</option>
										<option value="3">农资农机</option>
										<option value="4">政府机关</option>
										<option value="5">医疗</option>
										<option value="6">物流快递</option>
										<option value="7">酒店</option>
										<option value="8">餐饮</option>
										<option value="9">娱乐</option>
										<!-- <option value="10">旅游景点</option> -->
										<option value="11">购物</option>
										<option value="12">出行代驾</option>
										<option value="13">公共热线</option>
										<option value="14">银行保险</option>
										<option value="15">便民</option>
										<option value="16">更多</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td class="td-width">所属县/镇</td>
							<td class='forumRow'>
								<div class="form-group">
									<!--<input name='jiage_tongbao_yuefen' type='text' id='a_title' class="form-control">-->
									<select class="form-control" name="huangye_dianjia_suoshuzhen">
										<option value="抚松县">抚松县</option>
										<option value="抚松镇">抚松镇</option>
										<option value="松江河镇">松江河镇</option>
										<option value="万良镇">万良镇</option>
										<option value="露水河镇">露水河镇</option>
										<option value="东岗镇">东岗镇</option>
										<option value="泉阳镇">泉阳镇</option>
										<option value="仙人桥镇">仙人桥镇</option>
										<option value="北岗镇">北岗镇</option>
										<option value="漫江镇">漫江镇</option>
										<option value="兴参镇">兴参镇</option>
										<option value="新屯子镇">新屯子镇</option>
										<option value="兴隆乡">兴隆乡</option>
										<option value="抽水乡">抽水乡</option>
										<option value="沿江乡">沿江乡</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td class="td-width">名称</td>
							<td class='forumRow'>
								<div class="form-group">
									<input name='huangye_dianjia_mingcheng' type='text'
										class="form-control">
								</div>
							</td>

						</tr>
						<tr>
							<td class="td-width">联系方式</td>
							<td class='forumRow'>
								<div class="form-group">
									<input name='huangye_dianjia_dianhua' type='text'
										class="form-control">
								</div>
							</td>
						</tr>

						<tr>
							<td class="td-width">地址</td>
							<td class='forumRow'>
								<div class="form-group">
									<input name='huangye_dianjia_dizhi' type='text'
										class="form-control">
								</div>
							</td>
						</tr>

						<tr>
							<td class="td-width">详情</td>
							<td class='forumRow'>
								<div class="form-group">
									<textarea id="txt" name="huangye_xiangxi" class="ckeditor"></textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td class='td-width'>上传图片</td>
							<td width="85%" class='forumRowHighLight'>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img id="preview" alt="图片预览" src="" width="83px"
											height="83px"></td>
										<td>
											<div class="form-group" style="margin-right: 20px">
												<input name="fj.upload" type="file" id="f"
													onchange='change()'>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td height="50" colspan=2 class='forumRow'>
								<div align="center">
									<div class="form-group">
										<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
										<input class="templatemo-blue-button" type="button" value="取消"
											onclick="javascript:window.location.href ='hy-list.jsp';">
									</div>
								</div>
							</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</form>
	<%
		}
	%>
</body>
</html>