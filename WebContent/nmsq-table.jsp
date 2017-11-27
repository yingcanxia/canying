<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Nongmin_suqiu"%>
<%@page import="com.snb.hbm.orm.Huifu_nongmin_suqiu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title></title>

		<!--<link rel="stylesheet" href="css/mode2.css" />-->
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
		<%PageList<Object[]> b=(PageList)session.getAttribute("list"); %>
				<%if(b!=null){
				int a=(Integer)session.getAttribute("whe");
				Object[] sq=b.getPlist().get(a-1);
				
				int suqiu_id = (Integer)sq[0];
				String timu=(String)sq[1];
				String neirong=(String)sq[2];
				String tupian_lianjie=(String)sq[3];
				String huifu=(String)sq[12];
				if(huifu==null)huifu="";
				%>
		<form id="frm1" class="suqiu-form"style="position:absolute;top:0px;left:10%;width: 80%;" action="nmsq_update.action" method="post">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
						<!--	<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>反馈农民诉求</th>
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
								<td class="td-width" >农民诉求题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name="fk_neirong_suqiu_yunashi_id" type="hidden" value="<%=suqiu_id%>">
										<input name='huifu_nongmin_suqiu_timu' type='text' id='miaoshu' class="form-control" value="<%=timu%>" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >农民诉求内容</td>
								<td class='fabu_nongmin_suqiu_neirong'>
									<div class="form-group">
										<%-- <input type="hidden" name="nongmin_suqiu_renyuan_xingming" value="<%=sq.getNongmin_suqiu_renyuan_xingming()%>">
										<input type="hidden" name="nongmin_suqiu_renyuan_dianhua" value="<%=sq.getNongmin_suqiu_renyuan_dianhua()%>"> --%>
										<input name='huifu_nongmin_suqiu_neirong' type='text' id='cid' class="form-control" value="<%=neirong%>" readonly="readonly">
									</div>
								</td>

							</tr>

							<!--<tr>
								<td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<div class="form-group" style="margin-right:20px">
													<input name="fabu_nongmin_suqiu_tupian_lianjie" type="file" id="file">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>-->
							
							<tr>
								<td class='td-width' >图片显示</td>
								<td class='forumRow'>
									<div class="form-group">
										<%-- <input type="text" name="fj.upload" class="form-control" value="<%=tupian_lianjie%>" readonly="readonly"/> --%>
										<img alt="该图片无法显示" id="preview" src="<%=tupian_lianjie%>" >
									</div>
								</td>
							</tr>

							<tr>
								<td class='td-width' >回复内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="huifu_suqiu_neirong" style="width: 100%;height: 100px;" class="ckeditor" id="a_content"><%=huifu%></textarea>
									</div>
								</td>
							</tr>

							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<button class="templatemo-blue-button" onclick="tijiao()">提交</button>
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='nmsq-list.jsp';">
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
		<form id="frm1" class="suqiu-form"style="position:absolute;top:0px;left:10%;width: 80%;" action="nmsq_save.action" method="post">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
						<!--	<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>反馈农民诉求</th>
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
								<td class="td-width" >农民诉求题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='fabu_nongmin_suqiu_timu' type='text' id='miaoshu' class="form-control">
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >农民诉求内容</td>
								<td class='fabu_nongmin_suqiu_neirong'>
									<div class="form-group">
										<input name='cid' type='text' id='cid' class="form-control">
									</div>
								</td>

							</tr>

							<!--<tr>
								<td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<div class="form-group" style="margin-right:20px">
													<input name="fabu_nongmin_suqiu_tupian_lianjie" type="file" id="file">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>-->
							
							<tr>
								<td class='td-width' >图片显示</td>
								<td class='forumRow'>
									<div class="form-group">
									<!-- 	<input type="fj.upload" name="fabu_nongmin_suqiu_tupian_lianjie" class="form-control" /> -->
										<img  alt="该图片无法显示" src="">
									</div>
								</td>
							</tr>

							<tr>
								<td class='td-width' >回复内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="fabu_huifu_nongmin_suqiu_neirong" style="width: 100%;height: 100px;" class="ckeditor" id="a_content"></textarea>
									</div>
								</td>
							</tr>

							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
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