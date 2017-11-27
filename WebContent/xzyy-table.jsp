<!-- all change by Li Zhiying -->

<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Xingzheng_yuyue"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.text.SimpleDateFormat" %>
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
<%PageList<Xingzheng_yuyue> xzyy=(PageList)session.getAttribute("xzyy");
int a=(Integer)session.getAttribute("whe");%>
				<%if(xzyy.getNumber()!=0&&a!=-1){
				
				Xingzheng_yuyue yy=xzyy.getPlist().get(a-1);
				Timestamp tm = yy.getXingzheng_yuyue_riqi();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = df.format(tm);
				
				%>
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="xzyy_update.action" method="post"> 
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>行政预约</th>
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
								<td class="td-width" >预约题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_timu' type='text' id='a_title' class="form-control" value="<%=yy.getXingzheng_yuyue_timu()%>" readonly="readonly">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >预约日期</td>
								<td class='fabu_nongmin_suqiu_neirong'>
									<div class="form-group">
										<input name='xingzheng_yuyue_riqi' type='text' id='cid' class="form-control" value="<%=riqi%>" readonly="readonly">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >预约时间点</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_shijianduan' type='text' id='miaoshu' class="form-control" value="<%=yy.getXingzheng_yuyue_shijianduan()%>" readonly="readonly">
									</div>
								</td>

							</tr>
							
								<tr>
								<td class="td-width" >预约人姓名</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_renyuan_xingming' type='text' id='miaoshu' class="form-control" value="<%=yy.getXingzheng_yuyue_renyuan_xingming()%>" readonly="readonly">
									</div>
								</td>

							</tr>
							
								<tr>
								<td class="td-width" >预约人电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_renyuan_dianhua' type='text' id='miaoshu' class="form-control" value="<%=yy.getXingzheng_yuyue_renyuan_dianhua()%>" readonly="readonly">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >预约科室</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='fk_xingzheng_yuyue_mubiao_keshi_id' type='text' id='miaoshu' class="form-control" value="<%=yy.getFk_xingzheng_yuyue_mubiao_keshi_id()%>" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class='td-width' >回复内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="xingzheng_yuyue_fankui_neirong" style="width: 100%;height: 100px;" class="ckeditor" id="a_content"><%=yy.getXingzheng_yuyue_fankui_neirong() %></textarea>
									</div>
								</td>
							</tr>

							<tr>
							<tr>
								<td class="td-width" >审核状态</td>
								<td class='forumRow'>
									<div class="form-group">
										<select class="form-control" name="shenhe">
											<option value="2">接受</option>
											<option value="0">暂不接受</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='xzyy-list.jsp';">
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
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="xzyy_save.action" method="post"> 
<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>行政预约</th>
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
								<td class="td-width" >预约题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_timu' type='text' id='a_title' class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >预约日期</td>
								<td class='fabu_nongmin_suqiu_neirong'>
									<div class="form-group">
										<input name='xingzheng_yuyue_riqi' type='text' id='cid' class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >预约时间点</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_shijianduan' type='text' id='miaoshu' class="form-control">
									</div>
								</td>

							</tr>
							
								<tr>
								<td class="td-width" >预约人姓名</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_renyuan_xingming' type='text' id='miaoshu' class="form-control">
									</div>
								</td>

							</tr>
							
								<tr>
								<td class="td-width" >预约人电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='xingzheng_yuyue_renyuan_dianhua' type='text' id='miaoshu' class="form-control">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >预约科室</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='fk_xingzheng_yuyue_mubiao_keshi_id' type='text' id='miaoshu' class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class='td-width' >回复内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="xingzheng_yuyue_fankui_neirong" style="width: 100%;height: 100px;" class="ckeditor" id="a_content"></textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >审核状态</td>
								<td class='forumRow'>
									<div class="form-group">
										<select class="form-control">
											<option value="2">接受</option>
											<option value="0">暂不接受</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='xzyy-list.jsp';">
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
</body>
</html>