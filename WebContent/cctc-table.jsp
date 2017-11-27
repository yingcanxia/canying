<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Cuncuntong_zhen"%>
<%@page import="com.snb.hbm.orm.Cuncuntong_cun"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<%PageList<Cuncuntong_cun> cctc=(PageList)session.getAttribute("cctc"); %>
	<%PageList<Cuncuntong_zhen> cctz=(PageList)session.getAttribute("cctz");
	int a=(Integer)session.getAttribute("whe");%>
	
				<%if(cctz!=null&&a!=-1){
					
					Cuncuntong_cun cun=cctc.getPlist().get(a-1);
				%>
		<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="cctc_update.action" method="post">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>村村通</th>
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
								<td class="td-width" >村名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='cun_mingcheng' type='text' id='a_title' class="form-control" value="<%=cun.getCun_mingcheng()%>">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >村描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='zhen_miaoshu' type='text' id='miaoshu' class="form-control">--><!-- class="form-control" -->
										<textarea id="txt" name="cun_miaoshu" style="width: 100%;height: 100px;" class="ckeditor"  ><%=cun.getCun_miaoshu() %></textarea>
									</div>
								</td>
							</tr>
							
							<%-- <tr>
								<td class="td-width" >选择该镇的所属村</td>
								<td class='forumRow'>
									<div class="form-group">
										<select name="fk_zhen_id" class="form-control">
										<%for(int j=0;j<cctz.getPlist().size();j++){ 
											Cuncuntong_zhen zhen=cctz.getPlist().get(j);
										%>
											<option value="<%=zhen.getZhen_id()%>"><%=zhen.getZhen_mingcheng() %></option>
											<%} %>
										</select>
									</div>
								</td>
							</tr> --%>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='cctc-list.jsp';">
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
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="cctc_save.action" method="post">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>村村通</th>
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
								<td class="td-width" >村名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='cun_mingcheng' type='text' id='a_title' class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >村描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='zhen_miaoshu' type='text' id='miaoshu' class="form-control">--><!-- class="form-control" -->
										<textarea id="txt" name="cun_miaoshu"  style="width: 100%;height: 100px;" class="ckeditor"  ></textarea>
									</div>
								</td>
							</tr>
							
							<%-- <tr>
								<td class="td-width" >选择该镇的所属村</td>
								<td class='forumRow'>
									<div class="form-group">
										<select name="fk_zhen_id" class="form-control">
										<%for(int j=0;j<cctz.getPlist().size();j++){ 
											Cuncuntong_zhen zhen=cctz.getPlist().get(j);
										%>
											<option value="<%=zhen.getZhen_id()%>"><%=zhen.getZhen_mingcheng() %></option>
											<%} %>
										</select>
									</div>
								</td>
							</tr> --%>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='cctc-list.jsp';">
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