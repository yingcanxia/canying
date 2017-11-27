<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Nongzi_caigou"%>
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
	</head>

	<body>
	<%PageList<Nongzi_caigou> nzcx=(PageList)session.getAttribute("nzcg");
	int a=(Integer)session.getAttribute("whe");%>
				<%if(nzcx!=null&&a!=-1){
				
				Nongzi_caigou nz=nzcx.getPlist().get(a-1);
				%>
		<form id="frm1" class="suqiu-form"style="position:absolute;top:0px;left:10%;width: 80%;" action="nzcg_update.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
						<!--	<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>农资采购</th>
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
								<td class="td-width" >农资名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_mingcheng' type='text' class="form-control" value="<%=nz.getNongzi_mingcheng()%>">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >农资描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='fabu_nongmin_suqiu_timu' type='text' id='miaoshu' class="form-control">-->
										<textarea id="txt" name="nongzi_miaoshu" class="form-control"><%=nz.getNongzi_miaoshu()%></textarea>
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >其他内容</td>
								<td class='forumRow'>
									<div class="form-group">
											<textarea name="nongzi_qitaneirong" class="ckeditor form-control"><%=nz.getNongzi_qitaneirong()%></textarea>
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >数量</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_shuliang' type='text' id='cid' class="form-control" value="<%=nz.getNongzi_shuliang()%>">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >标注</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_biaozhu' type='text'  class="form-control" value="<%=nz.getNongzi_biaozhu()%>">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >规格</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_guige' type='text'  class="form-control" value="<%=nz.getNongzi_guige()%>">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >价格</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_jiage' type='text'  class="form-control" value="<%=nz.getNongzi_jiage()%>">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >厂商电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_changjiadianhua' type='text'  class="form-control" value="<%=nz.getNongzi_changjiadianhua()%>">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >库存量</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_kucunliang' type='text'  class="form-control" value="<%=nz.getNongzi_kucunliang()%>">
									</div>
								</td>
							</tr>

							<tr>
								<td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<div class="form-group" style="margin-right:20px">
													<input name="fj.upload" type="file" id="file" value="<%=nz.getNongzi_tupian_lianjie()%>">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >厂家地址</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_changjia_dizhi' type='text' id='cid' class="form-control" value="<%=nz.getNongzi_changjia_dizhi()%>">
									</div>
								</td>
							</tr>

							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='nzcg-list.jsp';">
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
		<form id="frm1" class="suqiu-form"style="position:absolute;top:0px;left:10%;width: 80%;" action="nzcg_save.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
						<!--	<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>农资采购</th>
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
								<td class="td-width" >农资名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_mingcheng' type='text' class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >农资描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='fabu_nongmin_suqiu_timu' type='text' id='miaoshu' class="form-control">-->
										<textarea id="txt" name="nongzi_miaoshu" class="form-control"></textarea>
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >其他内容</td>
								<td class='forumRow'>
									<div class="form-group">
											<textarea name="nongzi_qitaneirong" class="ckeditor form-control"></textarea>
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >数量</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_shuliang' type='text' id='cid' class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >标注</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_biaozhu' type='text'  class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >规格</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_guige' type='text'  class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >价格</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_jiage' type='text'  class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >厂商电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_changjiadianhua' type='text'  class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >库存量</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_kucunliang' type='text'  class="form-control">
									</div>
								</td>
							</tr>

							<tr>
								<td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<div class="form-group" style="margin-right:20px">
													<input name="fj.upload" type="file" id="file">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >厂家地址</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='nongzi_changjia_dizhi' type='text' id='cid' class="form-control">
									</div>
								</td>
							</tr>

							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='nzcg-list.jsp';">
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