<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Rexian"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				text-align: center;
			}
		</style>
		<script>
			function c() {
				var updatediv = document.getElementById("update");
				updatediv.style.display = "none";
				parent.document.documentElement.scrollTop = parent.document.body.scrollTop = 0;
				document.getElementById("studentbody").style.opacity = 1;
				document.getElementById("studentbody").style.filter = "alpha(opacity = 100)";
				
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
		<%PageList<Rexian> rxdh=(PageList)session.getAttribute("rx"); 
		int a=(Integer)session.getAttribute("whe");%>
				<%if(rxdh!=null&&a!=-1){
				
				Rexian rx=rxdh.getPlist().get(a-1);
				%>
		<form id="frm1"  style="position:absolute;top:0px;left:10%;width: 80%;" action="rxdh_update.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align="center">
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>热线</th>
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
								<td class="td-width" >姓名</td>
								<td class='forumRow'>
									<div class="form-group">
										<%if(rx.getRexian_leixing()== 1){//专家 %>
										<input name='r_name' type='text' class="form-control" value="<%=rx.getZhuanjia_name()%>">
										<%}else{ %>
										<input name='r_name' type='text' class="form-control" value="<%=rx.getBanshi_name()%>">
										<%} %>
									</div>
								</td>

							</tr>
						
							<tr>
							  <td class='td-width' >上传图片</td>
								<td width="85%" class='forumRowHighLight'>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
											<%if(rx.getRexian_leixing()== 1){//专家 %>
												<img id="preview" alt="该图片无法显示"src="<%=rx.getZhuanjia_tupain()%>" width="83px" height="83px">
											<%}else{ %>
												<img id="preview" alt="该图片无法显示"src="<%=rx.getBanshi_tupian()%>" width="83px" height="83px">
											<%} %>
											</td>
											<td>
												<div class="form-group" style="margin-right:20px">
													<%if(rx.getRexian_leixing()== 1){ %>
													<input name="fj.upload" type="file"  id="f" value="<%=rx.getZhuanjia_tupain()%>" onchange='change()'>
													<%}else{ %>
													<input name="fj.upload" type="file" id="f" value="<%=rx.getBanshi_tupian()%>" onchange='change()'>
													<%} %>
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
								<td class="td-width" >工作单位</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='banshi_danwei' type='text'  class="form-control" value="<%=rx.getBanshi_danwei()%>">
									</div>
								</td>
                            </tr>
							
							<tr>
								<td class="td-width" >工作地址</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='banshi_dizhi' type='text' value="<%=rx.getBanshi_dizhi()%>" class="form-control">
									</div>
								</td>
                            </tr>
                            	
                            <tr>
								<td class="td-width" >相关人员电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhuanjia_dianhua' type='text' value="<%=rx.getZhuanjia_dianhua()%>" class="form-control">
									</div>
								</td>
                            </tr>
							
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='rexian-list.jsp';">
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
		<form id="frm1" style="position:absolute;top:0px;left:10%;width: 80%;" action="rxdh_save.action" method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align="center">
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>热线</th>
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
								<td class="td-width">热线种类</td>
								<td>
									<div style="width: 190px;">
											<input  type="radio" checked = "true" name="identity" value="0" style="margin: 10px;padding-top: ;"/>办事人员
	                                        <input type="radio" name="identity" value="1" style="margin: 10px;" />技术专家	
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width" >姓名</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='r_name' type='text' class="form-control">
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
													<input name="fj.upload" type="file"  id="f" onchange='change()'>
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
								<td class="td-width" >工作单位</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='banshi_danwei' type='text'  class="form-control">
									</div>
								</td>
                            </tr>
							
							<tr>
								<td class="td-width" >工作地址</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='banshi_dizhi' type='text'  class="form-control">
									</div>
								</td>
                            </tr>
                            	
                            <tr>
								<td class="td-width" >相关人员电话</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zhuanjia_dianhua' type='text'  class="form-control">
									</div>
								</td>
                            </tr>
							
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='rexian-list.jsp';">
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