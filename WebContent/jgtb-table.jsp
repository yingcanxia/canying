<!-- all change by Li Zhiying -->
 <%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Jiage_tongbao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<%PageList<Jiage_tongbao> jgtb=(PageList)session.getAttribute("jgtb");
int a=(Integer)session.getAttribute("whe");%>
				<%if(jgtb!=null&&a!=-1){
				
				Jiage_tongbao jg=jgtb.getPlist().get(a-1);
				int yuefen = jg.getJiage_tongbao_yuefen();
				%>
<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="jgtb_update.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>价格通报</th>
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
								<td class="td-width" >价格通报月份</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='jiage_tongbao_yuefen' type='text' id='a_title' class="form-control">-->
											<select class="form-control" name="jiage_tongbao_yuefen" >
											<%for(int i=1;i<13;i++){  %>
											<%if(i==yuefen){ %>
  											<option value="<%=i%>" selected="selected"><%=i%></option>
  											<%}else{ %>
  											<option value="<%=i%>"><%=i%></option>
  											<%} }%>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >价格通报题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='jiage_tongbao_timu' type='text' id='miaoshu' class="form-control" value="<%=jg.getJiage_tongbao_timu()%>">
									</div>
								</td>

							</tr>
							
                            <tr>
								<td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<img id="preview" alt="该图片无法显示"src="<%=jg.getJiage_tongbao_neirong_wenjian_lianjie()%>" width="83px" height="83px">
											</td>
											<td>
												<div class="form-group" style="margin-right:20px">
													<input name="fj.upload" type="file" id="f" value="<%=jg.getJiage_tongbao_neirong_wenjian_lianjie()%>" onchange='change()'>
													<script type="text/javascript">
	function change(file) {
    var pic = document.getElementById("preview"),
        file = document.getElementById("f");
 
    if(file.value==""||file.value==null){ 
    	alert("file.value: "+file.value);
    	file.value=pic.src;
    	alert("file.value: "+file.value);
    			
    		}
    	
    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();

     // gif在IE浏览器暂时无法显示
     if(ext!='png'&&ext!='jpg'&&ext!='jpeg'&&ext!=""&&ext!=null){
         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
         return;
     }
     var isIE = navigator.userAgent.match(/MSIE/)!= null,
         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
 
     if(isIE) {
        file.select();
        var reallocalpath = document.selection.createRange().text;
 
        // IE6浏览器设置img的src为本地路径可以直接显示图片
         if (isIE6) {
            pic.src = reallocalpath;
         }else {
            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
             pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
             pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
         }
     }else {
        html5Reader(file);
     }
}
 
 function html5Reader(file){
     var file = file.files[0];
     var reader = new FileReader();
     reader.readAsDataURL(file);
     reader.onload = function(e){
         var pic = document.getElementById("preview");
         pic.src=this.result;
     }
 }
</script>
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
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='jgtb-list.jsp';">
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
			<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="jgtb_save.action" method="post" enctype="multipart/form-data">
				<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>价格通报</th>
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
								<td class="td-width" >价格通报月份</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='jiage_tongbao_yuefen' type='text' id='a_title' class="form-control">-->
											<select class="form-control" name="jiage_tongbao_yuefen" >
  											<option value="1">1</option>
 											<option value="2">2</option>
  											<option value="3">3</option>
  											<option value="4">4</option>
  											<option value="5">5</option>
  											<option value="6">6</option>
  											<option value="7">7</option>
  											<option value="8">8</option>
  											<option value="9">9</option>
  											<option value="10">10</option>
  											<option value="11">11</option>
  											<option value="12">12</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >价格通报题目</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='jiage_tongbao_timu' type='text' id='miaoshu' class="form-control">
									</div>
								</td>

							</tr>
                            <tr>
								<td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<img id="preview" alt="图片预览"src="" width="83px" height="83px">
											</td>
											<td>
												<div class="form-group" style="margin-right:20px">
													<input name="fj.upload" type="file" id="f" onchange='change()'>
														<script type="text/javascript">
	function change(file) {
    var pic = document.getElementById("preview"),
        file = document.getElementById("f");
 
    if(file.value==""||file.value==null){ 
    	alert("file.value: "+file.value);
    	file.value=pic.src;
    	alert("file.value: "+file.value);
    			
    		}
    	
    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();

     // gif在IE浏览器暂时无法显示
     if(ext!='png'&&ext!='jpg'&&ext!='jpeg'&&ext!=""&&ext!=null){
         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
         return;
     }
     var isIE = navigator.userAgent.match(/MSIE/)!= null,
         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
 
     if(isIE) {
        file.select();
        var reallocalpath = document.selection.createRange().text;
 
        // IE6浏览器设置img的src为本地路径可以直接显示图片
         if (isIE6) {
            pic.src = reallocalpath;
         }else {
            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
             pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
             pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
         }
     }else {
        html5Reader(file);
     }
}
 
 function html5Reader(file){
     var file = file.files[0];
     var reader = new FileReader();
     reader.readAsDataURL(file);
     reader.onload = function(e){
         var pic = document.getElementById("preview");
         pic.src=this.result;
     }
 }
</script>
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
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='jgtb-list.jsp';">
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