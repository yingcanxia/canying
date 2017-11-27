<%@page import="com.cust.easyutil.Compress"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.cust.util.PageData"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript" src="assets/js/Base64.js"></script>
		<style type="text/css">
			.aniu
			{
				width: 50px;
				height: 25px;
				text-align: center;
				border-radius: 5px;
				float: right;
				margin-right: 30px;
				border: 0;
				color: #FFFFFF;
				margin-top: -10px;
				background:deepskyblue ;
			
			
			}
			.hah
			{
				resize: none;
			}
			body{
				width: 40%;
				height:700px;
			}
			p.mine{
				text-align: right;
				color : blue;
			}
			p.opposite{
				text-align: left;
				color: red;
			}
			.time{
				font-size: 8px;
				color: black; 
			
			}
			
		</style>
	</head>

	<body style="margin: 0;padding: 0;">
	<%List<PageData> unread=(List<PageData>)session.getAttribute("unread"); %>
	<%Map receivermap=(Map)session.getAttribute("receivermap");
		String role=(String)receivermap.get("role");
		PageData receiver=(PageData)receivermap.get("receiver");
		PageData user=(PageData)session.getAttribute("pduser");
	%>
		<div style="width: 90%;height:40px;border-top: 1px solid #DDDDFF;border-right: 1px solid #DDDDFF;border-left: 1px solid #DDDDFF;line-height:40px;margin:20px 5%;">
			<span style="margin-left: 10px"><%=receiver.get("name") %></span>
			<span style="color:#d9dce2;font-size:10px;margin-left: 10px ">
				<%if(role.equals("rp")) {%>
					防火负责人
				<%}else{ %>
					区域负责人
				<%} %>
				电话：<span id="phone"><%=receiver.get("phone") %></span>
				
			</span>
		</div>
		<script type="text/javascript">
		function postToServer(){
			var message=document.getElementById("msg").value;
			var message2=doEncode(message);
			var msg={
					"name":"<%=user.get("phone")%>",
					"text":message2
			}
			var obj={
					"deviceid":"<%=user.get("phone")%>",
					"targetid":"<%=receiver.get("phone")%>",
					"message":msg,
					"text":message2,
					"type":0
			}
			var str=JSON.stringify(obj)
			str="T_"+str;
			window.parent.sendmessage(str);
			document.getElementById("msg").value = "";
			$("#history").append("<p class='mine'>我 ："+message+"</p>");
			 $.ajax({
					url: 'AJAX/updateStatue?sender='+<%=receiver.get("phone")%>,
					type: 'POST',
					data: {
					},
					dataType: 'json',
					success: function(list) {
						
						
					},
					error: function(list) {
						
					}
				}); 
			}
		</script>
		<div style="width: 90%;height: 400px;border-top: 1px solid #DDDDFF;border-right: 1px solid #DDDDFF;border-left: 1px solid #DDDDFF;margin: 0px 5%;position: relative;top:-20px;">
	
			<div id="history" style="width: 100%;height: 300px;border-bottom: 1px solid #DDDDFF;text-align: center;overflow-y: auto;">
				<a onclick="history('<%=user.get("phone")%>','<%=receiver.get("phone")%>')"><span style="text-decoration:underline;cursor:pointer;color:blue;">历史记录</span></a>
				<%for(int i = 0;i < unread.size();i++){ 
					if(unread.get(i).get("sender").equals(receiver.get("phone"))&&unread.get(i).get("text_body")!=null){%>
					<%byte []b=Compress.decodeBase64((String)unread.get(i).get("text_body")); %>
					<%String str = new String(b, "UTF-8"); %>
						<p class='opposite'>对方：<%=str %></p>
					
					<%}else if(unread.get(i).get("sender").equals(receiver.get("phone"))&&unread.get(i).get("store_path")!=null){ %>
						<p class='opposite'>对方：</p><br>
						<audio src="<%=unread.get(i).get("store_path") %>" controls="controls"></audio>
					<%}} %>
			</div>
			<div style="width: 100%;height: 100px;">
				

					<textarea id="msg" class="hah" rows="" cols=""style="width: 99%;height: 100px;position: relative;top:-5px;"></textarea>
					

			</div>
			<div  style="position: relative;top: 30px;">
					
					<button class="aniu" onclick="window.location.href=('information.jsp?ogid=<%=receiver.get("fk_organization_id")%>')">关闭</button>
					<button class="aniu" onclick="postToServer()">发送</button>
			</div>
		</div>
	</body>
<script>
	function history(sender,receiver){
		 $.ajax({
				url: 'AJAX/historymessage?sender='+sender+'&&receiver='+receiver,
				type: 'POST',
				data: {
				},
				dataType: 'json',
				success: function(list) {
					for(var i = 0;i < list.length;i++){
						if(sender==list[i].sender){
							var message=doDecode(list[i].text_body);
							var date = new Date(list[i].time);
							var y  = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							var h = date.getHours();
							var min = date.getMinutes();
							var s = date.getSeconds();
							var time = y + "-" + m +  "-" + d + " " + h + ":" + min + ":" + s;
							var msg = "<p class='mine'>我: "+"<span class='time'>"+time+"</span><br />"+message+"</p>";
							
						}
						else{
							var message=doDecode(list[i].text_body);
							var date=new Date(list[i].time);
							var date = new Date(list[i].time);
							var y  = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							var h = date.getHours();
							var min = date.getMinutes();
							var s = date.getSeconds();
							var time = y + "-" + m +  "-" + d + " " + h + ":" + min + ":" + s;
							var msg = "<p class='opposite'>对方: "+"<span class='time'>"+time+"</span><br />"+message+"</p>";
						}
						
						
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