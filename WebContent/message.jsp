<%@page import="java.util.List"%>
<%@page import="com.cust.util.PageData"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
		<title>JSP Page</title>
		<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css" />
		<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7308a10a069b06b3b6c44d0d3d5e0eba&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
		<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="assets/css/jquery.treeview.css" />
		<link rel="stylesheet" href="assets/css/screen.css" />
		<script type="text/javascript" src="assets/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="assets/js/jquery.treeview.js"></script>
		<script type="text/javascript" src="assets/js/demo.js"></script>
		<style type="text/css">
			.zuo {
				width: 40%;
				float: left;
				border: 1px solid #000000;
				height: 100%;
				margin-left: 10%;
				background: #FFFFFF;
				overflow-y: auto;
				-moz-border-radius: 10px;
    			-webkit-border-radius: 10px;
    			border-radius: 10px;
    			padding: 20px 0px 0px 20px;
    			background: rgba(255,255,255,0.5);
    			font-size: 12px;
			}
			
			.you {
				width: 40%;
				float: left;
				border: 1px solid #000000;
				margin-left: 5%;
				height: 100%;
				background: #FFFFFF;
				display: none;
			}
			
			.you-top {
				width: 80%;
				height: 200px;
				text-align: center;
				border: 1px solid #A4CC8D;
				margin: 50px 10%;
			}
			
			.you-bottom {
				width: 80%;
				height: 200px;
				border: 1px solid #A4CC8D;
				text-align: center;
				margin: 50px 10%;
			}
			li{
				cursor: pointer;
			}
			
		</style>
		<%List<PageData> unread=(List<PageData>)session.getAttribute("unread"); %>
		<script type="text/javascript">
		function tiaozhuanA(phone,id){
			$.ajax({
					url: 'AJAX/updateMsgStatue?sender='+phone,
					type: 'POST',
					data: {
					},
					dataType: 'json',
					success: function(result) {
					}
			});
			var is_read = -1;
			<%for ( int i = 0;i < unread.size();i++){%>
				if(phone == <%=unread.get(i).get("sender")%>){
					is_read = 1;
	
				}
			<%}%>
			if(is_read == 1){
				var newnum = parseInt($(parent.document.getElementById("unread")).text()) - 1;
				if(newnum<=0)
					newnum = 0;
				$(parent.document.getElementById("unread")).text(newnum);
				
			}
			
			turnurl("TALK/selectrp?id="+id);
		}
		function tiaozhuanB(phone,id){
			$.ajax({
					url: 'AJAX/updateMsgStatue?sender='+phone,
					type: 'POST',
					data: {
					},
					dataType: 'json',
					success: function(result) {
					}
			});
			var is_read = -1;
			<%for ( int i = 0;i < unread.size();i++){%>
				if(phone == <%=unread.get(i).get("sender")%>){
					is_read = 1;
	
				}
			<%}%>
			if(is_read == 1){
				var newnum = parseInt($(parent.document.getElementById("unread")).text()) - 1;
				if(newnum<=0)
					newnum = 0;
				$(parent.document.getElementById("unread")).text(newnum);			
			}
			turnurl("TALK/selectis?id="+id);
			
		}
		function selectfitrp(ogid){
			
			 $.ajax({
				url: 'AJAX/selectrp?ogid='+ogid,
				type: 'POST',
				data: {
				},
				dataType: 'json',
				success: function(list) {
					var lg=list;
					var str="";
					for(var i=0;i<lg.length;i++){
						var rp=lg[i];
						var str2;
						var count=0;
						<%for(int i=0;i<unread.size();i++){%>
							if(rp.phone=='<%=unread.get(i).get("sender")%>'){
								count++;
							}
							
							<%}%>
							if(count>0){
								str2="<li style='color:red;' onclick='change(this)'><span class='file' onclick='tiaozhuanA("+"\""+rp.phone+"\""+","+"\""+rp.id+"\")'>"+rp.name+"</span><b>&nbsp;&nbsp;&nbsp;有"+count+"条信息"+"</b></li>";
								str=str+str2;
							}else{
								
								str2="<li><span class='file' onclick='tiaozhuanA("+"\""+rp.phone+"\""+","+"\""+rp.id+"\")'>"+rp.name+"</span></li>";
								str=str+str2;
								
								
							}
							
						
					}
						$("#"+rp.fk_organization_id+"_rp").find("li").remove();
						var branches = $(str).appendTo("#"+rp.fk_organization_id+"_rp");
						$("#black").treeview({
							add: branches,
						});
						
				},
				error: function(list) {
					alert(list);
					alert('提交失败！');
					reg_username_mark.innerHTML = "no";
					return bool;
				}
			}); 
			 $(".you").css("display","block");
		}
		function selectfitfuzeren(ogid){
			
			$.ajax({
				url: 'AJAX/selectis?ogid='+ogid,
				type: 'POST',
				data: {
				},
				dataType: 'json',
				success: function(list2) {
					var lg=list2;
					var str="";
					for(var i=0;i<lg.length;i++){
						var is=lg[i];
						var str2;
						var count=0;
						<%for(int i=0;i<unread.size();i++){%>
							if(is.phone=='<%=unread.get(i).get("sender")%>'){
								count++;
							}
							
							<%}%>
						if(count>0){
							str2="<li style='color:red;' onclick='change(this)'><span class='file' onclick='tiaozhuanB("+"\""+is.phone+"\""+","+"\""+is.id+"\")'>"+is.name+"</sapn><b>&nbsp;&nbsp;&nbsp;有"+count+"条信息"+"</b></li>";
							str=str+str2;
						}else{
							str2="<li><span class='file' onclick='tiaozhuanB("+"\""+is.phone+"\""+","+"\""+is.id+"\")'>"+is.name+"</span></li>";
							str=str+str2;
						}
						
					}
						$("#"+is.fk_organization_id+"_is").find("li").remove();
						var branches = $(str).appendTo("#"+is.fk_organization_id+"_is");
						$("#black").treeview({
							add: branches,
						});
						
				},
				error: function(list) {
					alert(list);
					alert('提交失败！');
					reg_username_mark.innerHTML = "no";
					return bool;
				}
			}); 
			$(".you").css("display","block");
		}
		
		function turnurl(url) {
 			var strurl = url;
 			$("#iframeB").attr("src", strurl);
 			$("#iframeB").reload();
 		}
		function sendmessage(str) {
			window.parent.sendmessage(str);
		}
		function change(l){
			
			$(l).css('color','black');
			$(l).find('b').remove();
		}
	</script>
	</head>
<%List<PageData> oglist=(List<PageData>)session.getAttribute("oglist2"); %>

	<body style="padding: 0;margin: 0;background: transparent;">
		<div style="width: 100%;height:600px;">
			<div class="zuo">
				
	<div id="main">
		<h4>关系人列表</h4>
		<div id="treecontrol">
		<a title="Collapse the entire tree below" href="#"><img src="assets/img/minus.gif" /> Collapse All</a>
		<a title="Expand the entire tree below" href="#"><img src="assets/img/plus.gif" /> Expand All</a>
		</div>
		<ul id="black" class="treeview-black">
		<li><span>联系人</span><ul>
		
		<%if(oglist!=null&&oglist.size()!=0){
			for(int i=0;i<oglist.size();i++){
			PageData og=oglist.get(i);%>
		<li onclick="show()"><span onclick="turnurl('information.jsp?ogid=<%=og.get("id")%>')"><%=og.get("name") %></span>
			<ul>
				<li><span id="rp" onclick="selectfitrp('<%=og.get("id")%>')">防火负责人</span>
					<ul id="<%=og.get("id")+"_rp"%>">
					</ul>
				</li>
				<li><span onclick="selectfitfuzeren('<%=og.get("id")%>')">单位区域负责人</span>
					<ul id="<%=og.get("id")+"_is"%>">
						
					</ul>
				</li>
			</ul>
		</li>
	<%} }%>
	</ul>
	</li>
	</ul>
	
	
	
	</div>
			</div>
			<div class="you">
			<iframe  id="iframeB" name="iframe" src="" height="860px" width="100%" scrolling=no frameBorder=0 marginheight=0px style="position: absolute;clear:both;float:left">
			
			</iframe>
			</div>
		</div>
		
		
		
		<script type="text/javascript">
		
			$(document).ready(function(){ 
				
				<%if(oglist!=null&&oglist.size()!=0){
					for(int i=0;i<oglist.size();i++){
					PageData og=oglist.get(i);%>
					selectfitrp('<%=og.get("id")%>');
					selectfitfuzeren('<%=og.get("id")%>');
					
					
					<%} }%>
			});
			function show(){
				$(".you").css("display","block");
			}
		</script>
		
		
		
	</body>
</html>