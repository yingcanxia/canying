<!-- change by Li Zhiying -->

<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Gongqiu_xinxi"%>
<%@page import="java.util.List"%>

<!-- change by Li Zhiying -->
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

				$('#frm1').submit();
			}
		</script>
	</head>

	<body>
		<%PageList<Gongqiu_xinxi> gqxx=(PageList)session.getAttribute("gqxx"); %>
				<%if(gqxx!=null){
				int a=(Integer)session.getAttribute("whe");
				Gongqiu_xinxi gq=gqxx.getPlist().get(a-1);
				%>
		<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="gqxx_update.action" method="post">
		<!-- change by Li Zhiying -->
		
				
		<!-- change by Li Zhiying -->
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>供求信息</th>
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
								<td class="td-width" >供求类型</td>
								<td class='forumRow'>
									<div class="form-group">
										
										<!-- change by Li Zhiying -->
										<%if(gq.getGongqiu_xinxi_leixing()==1){ %>
					<input name='gongqiu_xingxi_leixing' type='text' class="form-control" disabled="disabled" value="供">
					<%}else{ %>
					<input name='gongqiu_xingxi_leixing' type='text' class="form-control" disabled="disabled" value="求">
					<%} %>
  											<!-- <input name='gongqiu_xingxi_leixing' type='text' id='miaoshu' class="form-control" disabled="disabled" > -->
  											<%-- <input name='gongqiu_xingxi_leixing' type='text' id='miaoshu' class="form-control" disabled="disabled" value="<%=gq.getGongqiu_xinxi_leixing()%>"> --%>
  											
  										
  										<!-- change by Li Zhiying -->
									
									</div>
								</td>
								
							</tr>
							<tr>
								<td class="td-width" >分类</td>
								<td class='forumRow'>
									<div class="form-group">
										<select  class="form-control" name="gongqiu_xinxi_type">
											<%String[] typeArray = {"农产品买卖","出租出售","二手买卖","招工求职","其它"};
											for(int i=0;i<5;i++){ 
												%>
											<%if((i+1)==gq.getGongqiu_xinxi_type()){ %>
  											<option value="<%=i+1%>" selected="selected"><%=typeArray[i]%></option>
  											<%}else{ %>
  											<option value="<%=i+1%>"><%=typeArray[i]%></option>
  											<%} }%>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >商品名称</td>
								<td class='forumRow'>
									<div class="form-group">
									<!-- change by Li Zhiying -->
										
  											<!-- <input name='gongqiu_xingxi_shangpin' type='text' id='miaoshu' class="form-control" disabled="disabled"> -->
  											<input name='gongqiu_xingxi_shangpin' type='text' id='miaoshu' class="form-control" disabled="disabled" value="<%=gq.getGongqiu_xinxi_shangpin()%>">
  											
  										
  										<!-- change by Li Zhiying -->
									</div>
								</td>

							</tr>
						
							<tr>
								<td class="td-width" >信息描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='gongqiu_xingxi_miaoshu' type='text' id='cid' class="form-control" disabled="disabled">-->
											<%=gq.getGongqiu_xinxi_miaoshu() %>
									</div>
								</td>

							</tr>

                            <tr>
								<td class="td-width" >有效期</td>
								<td class='forumRow'>
									<div class="form-group">
									<!-- change by Li Zhiying -->
										
  											<!-- <input name='gongqiu_xingxi_youxiaoqi' type='text' id='cid' class="form-control" disabled="disabled"> -->
  											<input name='gongqiu_xingxi_youxiaoqi' type='text' id='cid' class="form-control" disabled="disabled" value="<%=gq.getGongqiu_xinxi_youxiaoqi()%>">
  											
  										
  										<!-- change by Li Zhiying -->
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >图片显示</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='gongqiu_xingxi_tupian_lianjie' type='text' id='cid' class="form-control" disabled="disabled" type="image">-->
										<!-- change by Li Zhiying -->
										
  											<!-- <img  style="width: 300;height: 300;" /> -->
  											<img  style="width: 300;height: 300;" alt="无法显示图片" src="<%=gq.getGongqiu_xinxi_tupian_lianjie()%>"/>
  											
  										
  										<!-- change by Li Zhiying -->
										
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >联系电话</td>
								<td class='forumRow'>
									<div class="form-group">
									<!-- change by Li Zhiying -->
										
  											<!-- <input name='gongqiu_xingxi_renyuan_dianhua' type='text' id='cid' class="form-control" disabled="disabled"> -->
  											<input name='gongqiu_xingxi_renyuan_dianhua' type='text' id='cid' class="form-control" disabled="disabled" value="<%=gq.getGongqiu_xinxi_renyuan_dianhua()%>">
  											
  										
  										<!-- change by Li Zhiying -->
										
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >审核状态</td>
								<td class='forumRow'>
									<div class="form-group">
										<select class="form-control" name="shenhe">
											<option value="2">通过</option>
											<option value="0">不通过</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='gqxx-list.jsp';">
										</div>
									</div>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
			</form>
<!-- change by Li Zhiying -->

<%} else{%>
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="gqxx_save.action" method="post">
<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>供求信息</th>
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
								<td class="td-width" >供求类型</td>
								<td class='forumRow'>
									
										<div class="form-group">
  											<input name='gongqiu_xingxi_leixing' type='text' id='miaoshu' class="form-control" disabled="disabled" >
									</div>
								</td>
							
							</tr>
							<tr>
								<td class="td-width" >商品名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='gongqiu_xingxi_shangpin' type='text' id='miaoshu' class="form-control" disabled="disabled">
									</div>
								</td>

							</tr>
						
							<tr>
								<td class="td-width" >信息描述</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='gongqiu_xingxi_miaoshu' type='text' id='cid' class="form-control" disabled="disabled">-->
											<textarea id="txt" name='gongqiu_xingxi_miaoshu' class="form-control" ></textarea>
									</div>
								</td>

							</tr>

                            <tr>
								<td class="td-width" >有效期</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='gongqiu_xingxi_youxiaoqi' type='text' id='cid' class="form-control" disabled="disabled">
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >图片显示</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='gongqiu_xingxi_tupian_lianjie' type='text' id='cid' class="form-control" disabled="disabled" type="image">-->
										<img  style="width: 300;height: 300;" />
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >联系电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='gongqiu_xingxi_renyuan_dianhua' type='text' id='cid' class="form-control" disabled="disabled">
									</div>
								</td>
							</tr>
							
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											审核通过<input type="radio" name="shenhe" value="2">
											<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
											审核不通过<input type="radio" name="shenhe" value="2">
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='gqxx-list.jsp';">
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