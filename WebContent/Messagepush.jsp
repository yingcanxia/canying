<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.cust.util.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<script type="text/javascript" src="assets/js/Base64.js"></script>
		
		<link rel="StyleSheet" href="assets/css/dtree.css" type="text/css" />
		<script type="text/javascript" src="assets/js/dtree.js"></script>
		
		
		
		
		<style type="text/css">
			.zuo {
				width: 40%;
				float: left;
				border: 1px solid #000000;
				height: 100%;
				margin-left: 10%;
				background: rgba(144,144,144,0.5);
				overflow-y: auto;
				-moz-border-radius: 10px;
    			-webkit-border-radius: 10px;
    			border-radius: 10px;
    			padding: 20px 0px 0px 20px; 
			}
			
			.you {
				width: 40%;
				float: left;
				border: 1px solid #000000;
				margin-left: 5%;
				height: 100%;
				background: #FFFFFF;
				-moz-border-radius: 10px;
    			-webkit-border-radius: 10px;
    			border-radius: 10px;
    			background: rgba(144,144,144,0.5);
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
		<script type="text/javascript">
		
		
		function sendmessage(str) {
			window.parent.sendmessage(str);
		}
	</script>
	</head>
<%List<PageData> oglist=(List<PageData>)session.getAttribute("oglist2"); %>
<%List<PageData> rplist=(List<PageData>)session.getAttribute("rplist"); %>
<%List<PageData> islist=(List<PageData>)session.getAttribute("islist"); %>
<%PageData user=(PageData)session.getAttribute("pduser"); %>
	<body style="padding: 0;margin: 0;background: transparent;">
		<div style="width: 100%;height:600px;">
			<div class="zuo">
				
	
	
	<div class="dtree">

	<p><a href="javascript:  d.closeAll();">open all</a> | <a href="javascript: d.openAll();">close all</a></p>

	<script type="text/javascript">
		d = new dTree('d');
		d.add(0,-1,'<span style="color: black;">联系人列表</span>');
		<%for(int i=0;i<oglist.size();i++){
		PageData og=oglist.get(i);
		%>
			d.add(<%=i+1%>,0,'authority','<%=og.get("id")%>','<span style="color: white;"><%=og.get("name")%></span>');
			
		<%}%>

		<%for(int i=0;i<rplist.size();i++){
			PageData rp=rplist.get(i);
			int id=0;
			for(int j = 0;j<oglist.size();j++){
				PageData og=oglist.get(j);
				
				if(rp.get("fk_organization_id").equals(og.get("id"))){
					
					id = j+1;
					break;
				}
			}
			
			
			
			%>
				
				d.add('<%=rp.get("id")%>',<%=id%>,'checked','<%=rp.get("phone")%>','<span style="color: white;"><%=rp.get("name")%></span> <span class="glyphicon glyphicon-user"  style="color: red;"></span>');
			<%}%>
			
			<%for(int i=0;i<islist.size();i++){
				PageData is=islist.get(i);
				int id = 0;
				for(int j = 0;j<oglist.size();j++){
					PageData og=oglist.get(j);
					
					if(is.get("fk_organization_id").equals(og.get("id"))){
						
						id = j+1;
						break;
					}
				}
				
				%>
					d.add('<%=is.get("id")%>','<%=id%>','checked','<%=is.get("phone")%>','<span style="color: white;"><%=is.get("name")%></span> <span class="glyphicon glyphicon-user"  style="color: green;"></span>');
				<%}%>	
		
		
		
		
		
		
		document.write(d);
		
		d.openAll();
		
	</script>

</div>
	
	
	</div>
			
			<div class="you">
		
		<div style="width: 90%;height:40px;border-top: 1px solid #DDDDFF;border-right: 1px solid #DDDDFF;border-left: 1px solid #DDDDFF;line-height:40px;margin:20px 5%;">
			<span style="margin-left: 10px" id="users">消息推送:</span>
			<span style="color:#d9dce2;font-size:10px;margin-left: 10px ">
			</span>
		</div>
		<script type="text/javascript">
		function postToServer(){
			var message=document.getElementById("msg").value;
			var message2=doEncode(message);
			var obj2 = document.all.checked;
			alert(obj2.length);
			for(i=0;i<obj2.length;i++){
				
				if(obj2[i].checked){
					var msg={
							"name":"<%=user.get("phone")%>",
							"text":message2
					}
					var obj={
							"deviceid":"<%=user.get("phone")%>",
							"targetid":obj2[i].value,
							"message":msg,
							"text":message2,
							"type":1
					}
					var str=JSON.stringify(obj)
					str="T_"+str;
					window.parent.sendmessage(str);
				}
				
			}	
			document.getElementById("msg").value = "";
			$("#history").append("<p style='text-align:left;'>我 ："+message+"</p>");

			}
		</script>
		<div style="width: 90%;height: 400px;border-top: 1px solid #DDDDFF;border-right: 1px solid #DDDDFF;border-left: 1px solid #DDDDFF;margin: 0px 5%;position: relative;top:-20px;">
	
			<div id="history" style="width: 100%;height: 300px;border-bottom: 1px solid #DDDDFF;text-align: center;overflow-y: auto;">
			<a><span style="text-decoration:underline;cursor:pointer;color:blue;" onclick="history()">历史记录</span></a>
			</div>
			<div style="width: 100%;height: 100px;">
				

					<textarea id="msg" class="hah" rows="" cols=""style="width: 99%;height: 100px;position: relative;top:-5px;"></textarea>
					

			</div>
			<div  style="position: relative;top: 30px;">
					
					<button class="aniu" onclick="postToServer()">发送</button>
			</div>
		</div>
			</div>
		</div>
		
		
		
		
		
	</body>
	<script type="text/javascript">

		function test(){
			var count = 0;
			var obj = document.all.checked;	
			var str="";
			for(i=0;i<obj.length;i++){
				if(obj[i].checked){		
					str+=obj[i].value;
					str+=",";
					count++;	
				}
			}	
			alert(count);
			$("#users").append("<span>"+count+"</span>");
		}

</script>
<script>
	function history(){
		 $.ajax({
				url: 'AJAX/historypush',
				type: 'POST',
				data: {
				},
				dataType: 'json',
				success: function(list) {
					for(var i = 0;i < list.length;i++){
						var message2=doDecode(list[i].text_body);
							var msg = "<p class='msg'>我: "+message2+"</p>";
							
					
						
						
						$("#history").append(msg);
						
					}
				},
				error: function(list) {
					alert(list);
					alert('提交失败！');
					reg_username_mark.innerHTML = "no";
					return bool;
				}
			}); 
		
		
		
		
		
	}






</script>
</html>