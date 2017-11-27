<!-- all change by Li Zhiying -->
<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Zhanxiao_xinxi"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<%PageList<Zhanxiao_xinxi> zxxx=(PageList)session.getAttribute("zxxx"); 
int a=(Integer)session.getAttribute("whe");%>
				<%if(zxxx!=null&&a!=-1){
				
				Zhanxiao_xinxi zx=zxxx.getPlist().get(a-1);
				%>
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="zxxx_update.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>农产品展销</th>
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
								<td class="td-width" >展销题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_timu' type='text' id='a_title' class="form-control" value="<%=zx.getZhanxiao_xinxi_timu()%>" >
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >商品名称</td>
								<td class='fabu_nongmin_suqiu_neirong'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_shangpin_mingcheng' type='text' id='cid' class="form-control" value="<%=zx.getZhanxiao_xinxi_shangpin_mingcheng()%>">
											<!--<textarea name="zhengce_neirong" class="ckeditor"></textarea>-->
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >商品描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='zhengce_guanjiazi' type='text' id='miaoshu' class="form-control">-->
											<textarea id="txt" name="zhanxiao_xinxi_shangpin_miaoshu" class="ckeditor"><%=zx.getZhanxiao_xinxi_shangpin_miaoshu()%></textarea>
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
													<input name="fj.upload" type="file" id="file"  value="<%=zx.getZhanxiao_xinxi_tupian_lianjie()%>">
													
													<!--  <img alt="img/banner-shdow.png" src="<%=zx.getZhanxiao_xinxi_tupian_lianjie()%>"> -->
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >商家地址</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_dizhi' type='text' id='miaoshu' class="form-control"  value="<%=zx.getZhanxiao_xinxi_dizhi()%>">
											<!--<textarea name="shipin_miao" class="ckeditor"></textarea>-->
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >联系方式</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_lianxifangshi' type='text' id='miaoshu' class="form-control"  value="<%=zx.getZhanxiao_xinxi_lianxifangshi()%>">
											<!--<textarea name="shipin_miao" class="ckeditor"></textarea>-->
									</div>
								</td>
							</tr>
							

							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='zxxx-list.jsp';">
											
										</div>
									</div>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
			</form>
<%} else{%>
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="zxxx_save.action" method="post" enctype="multipart/form-data">
					<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>农产品展销</th>
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
								<td class="td-width" >展销题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_timu' type='text' id='a_title' class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >商品名称</td>
								<td class='fabu_nongmin_suqiu_neirong'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_shangpin_mingcheng' type='text' id='cid' class="form-control">
											<!--<textarea name="zhengce_neirong" class="ckeditor"></textarea>-->
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >商品描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='zhengce_guanjiazi' type='text' id='miaoshu' class="form-control">-->
											<textarea id="txt" name="zhanxiao_xinxi_shangpin_miaoshu" class="ckeditor"></textarea>
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
								<td class="td-width" >商家地址</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_dizhi' type='text' id='miaoshu' class="form-control">
											<!--<textarea name="shipin_miao" class="ckeditor"></textarea>-->
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >联系方式</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhanxiao_xinxi_lianxifangshi' type='text' id='miaoshu' class="form-control">
											<!--<textarea name="shipin_miao" class="ckeditor"></textarea>-->
									</div>
								</td>
							</tr>
							

							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='zxxx-list.jsp';">
											
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