<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.cust.util.PageData"%>
<%@page language="java" contentType="text/html; charset=UTF-8 "
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录成功</title>
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

	<script src="http://webapi.amap.com/maps?v=1.3&key=b167560963be24772232369cb00b972f"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/loader-style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" type="text/css" href="<%=request.getContextPath()%>/assets/js/progress-bar/number-pb.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/Base64.js"></script>


    <style type="text/css">
    canvas#canvas4 {
        position: relative;
        top: 20px;
    }
    </style>

	<script type="text/javascript">
		var h;
		var m;
		var s;
		var y;
		var mon;
		var d;
		function showTime(){
			h = new Date().getHours();
			if(h>12){
				h = h-12;
			}
			m = new Date().getMinutes();
			s = new Date().getSeconds();
			y = new Date().getFullYear();
			mon = new Date().getMonth()+1;
			d = new Date().getDate();
			m=checkTime(m);
			s=checkTime(s);
			$('#hour').html(h);
			$('#min').html(m);
			$('#sec').html(s);
			$('#date').html(y+"-"+mon+"-"+d);
			setTimeout(function() {
	            showTime();
	        }, 1000);
		}
 		function checkTime(i){    
 			if (i<10)     
  				 {i="0" + i}    
   			return i    
 		}    
 		function turnAction(url) {
 			var strurl = url + '.jsp';
 			$("#iframe").attr("src", strurl);
 			$("#iframe").reload();
 		}
 		function turnurl(url) {
 			var strurl = url;
 			$("#iframe").attr("src", strurl);
 			$("#iframe").reload();
 		}
 		function setIframeHeight(iframe) {
 			if (iframe) {
 			var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
 			if (iframeWin.document.body) {
 			iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
 			}
 			}
 			};
 			window.onload = function () {
 			setIframeHeight(document.getElementById('iframe'));
 			};
 		function setIframeWidth(){
 			document.getElementById("iframe").width=document.getElementById("div-iframe").width*0.8;
 		}
 		
	</script>



    <link rel="shortcut icon" href="<%=request.getContextPath()%>/assets/ico/minus.png">
</head>
<body> 
    <!-- Preloader -->
    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div>
    <!-- TOP NAVBAR -->
    <%PageData paged=(PageData)session.getAttribute("pduser"); %>
    <%List<PageData> unread=(List<PageData>)session.getAttribute("unread"); %>
    <%List<PageData> untreated=(List<PageData>)session.getAttribute("untreated"); %>
    <script type="text/javascript">
    	var ws = new WebSocket("ws://192.168.199.117:8080/FireAlum/ws");
		var msg='R_{"deviceid":"${pduser.phone}"}';
		var senders = new Array();
		<%for (int i =0;i < unread.size();i++){%>
			senders.push(<%=unread.get(i).get("sender")%>);
		<%}%>
		
		ws.onopen = function(){
			ws.send(msg);
		};
		ws.onmessage = function(message){
			var message2=message.data.split("_")[0];
			if(message2=="A"){
			alert("出现警情");
				
				var newnum = parseInt($("#untreated").text())+1;
				$("#untreated").text(newnum);
				var msg2=message.data.split("_")[1];
				var obj = JSON.parse(msg2);
				var msg=obj.message;
				var x=msg.lat;
				var y=msg.lon;
				var accidentname = msg.accidentname;
				var role = msg.role;
				
				var tips ="<li onclick='turnurl('AL/untreatedAlarmt?nowpage=1')'>单位名称：" + accidentname +" 时间："+ $("#date").text() +" / " + $("#hour").text() + " : " + $("#min").text() + " : " + $("#sec").text()+"</li>";
		 		
				$("#nt-title").append(tips);

		 		iframe.window.addMarker(y,x,accidentname,role);

			}
			else if(message2=="T"){
				var msg2=message.data.split("_")[1];
				var obj = JSON.parse(msg2);
				var sender=obj.deviceid;
				if($("#iframe").attr("src").startsWith("OG/SelectAllog2?biaoshi=a")){
					if($(document.getElementById("iframe").contentWindow.document.getElementById("iframeB")).attr("src").startsWith("TALK/selectrp")){
						var sender_now = $(document.getElementById("iframe").contentWindow.document.getElementById("iframeB").contentWindow.document.getElementById("phone")).html();
						var senders_now = senders.slice();
						senders_now.push(sender_now);
						var is_now = -1;
						for(var i = 0;i < senders_now.length; i++){
							
							if(sender == senders_now[i]){
								is_now = 1;
								break;
							}
							
						}
						if(is_now == -1){
							var newnum = parseInt($("#unread").text())+1;
							$("#unread").text(newnum);
							senders.push(sender);
						}
						
					}else{
						var is = -1;
						for(var i = 0;i < senders.length; i++){
							
							if(sender == senders[i]){
								is = 1;
								break;
							}
							
						}
						if(is == -1){
							var newnum = parseInt($("#unread").text())+1;
							$("#unread").text(newnum);
							senders.push(sender);
						}
					}
				}else{
					var is = -1;
					for(var i = 0;i < senders.length; i++){
						
						if(sender == senders[i]){
							is = 1;
							break;
						}
						
					}
					if(is == -1){
						var newnum = parseInt($("#unread").text())+1;
						$("#unread").text(newnum);
						senders.push(sender);
					}
				}
					
				
		
				
				
				if(sender==<%=paged.get("phone")%>){
					sender="我："
				}
				else{
					sender="对方："
				}
				var msg=obj.text;
		 		var a=document.getElementById('iframe');
		 		var b=a.contentWindow.document.getElementById('iframeB');
		 		var d =b.contentWindow.document.getElementById('history');
		 		var p = b.contentWindow.document.getElementById('phone');
		 		msg=doDecode(msg);
		 		if($(p).html()==obj.deviceid){
		 			var string=sender+msg;
		 			if(sender=="对方："){
			 			$(d).append("<p class='opposite'>"+string+"</p>");
		 			}
		 			else{
		 				$(d).append("<p class='mine'>"+string+"</p>");
		 			}
		 		}
		 		
			}
			else if(message2=="V"){
				var msg2=message.data.split("_")[1];
				var obj = JSON.parse(msg2);
				var sender=obj.deviceid;
				if($("#iframe").attr("src").startsWith("OG/SelectAllog2?biaoshi=a")){
					if($(document.getElementById("iframe").contentWindow.document.getElementById("iframeB")).attr("src").startsWith("TALK/selectrp")){
						var sender_now = $(document.getElementById("iframe").contentWindow.document.getElementById("iframeB").contentWindow.document.getElementById("phone")).html();
						var senders_now = senders.slice();
						senders_now.push(sender_now);
						var is_now = -1;
						for(var i = 0;i < senders_now.length; i++){
							
							if(sender == senders_now[i]){
								is_now = 1;
								break;
							}
							
						}
						if(is_now == -1){
							var newnum = parseInt($("#unread").text())+1;
							$("#unread").text(newnum);
							senders.push(sender);
						}
						
					}else{
						var is = -1;
						for(var i = 0;i < senders.length; i++){
							
							if(sender == senders[i]){
								is = 1;
								break;
							}
							
						}
						if(is == -1){
							var newnum = parseInt($("#unread").text())+1;
							$("#unread").text(newnum);
							senders.push(sender);
						}
					}
				}else{
					var is = -1;
					for(var i = 0;i < senders.length; i++){
						
						if(sender == senders[i]){
							is = 1;
							break;
						}
						
					}
					if(is == -1){
						var newnum = parseInt($("#unread").text())+1;
						$("#unread").text(newnum);
						senders.push(sender);
					}
				}
					
				
		
				
				
				sender="对方："
				var path=obj.path;
		 		var a=document.getElementById('iframe');
		 		var b=a.contentWindow.document.getElementById('iframeB');
		 		var d =b.contentWindow.document.getElementById('history');
		 		var p = b.contentWindow.document.getElementById('phone');
		 		
		 		if($(p).html()==obj.deviceid){
			 		$(d).append("<p class='opposite'>"+sender+"</p><audio src='"+path+"' controls='controls'></audio>");
		 			
		 		}
			}
		}

		function sendmessage(message){
			ws.send(message);
		}
		function closeConnect(){
		ws.close();
		}
		function dealmessage(){
			senders.splice(0,senders.length);
			turnurl("OG/SelectAllog2?biaoshi=a&&usphone=<%=paged.get("phone")%>");
			
		}
    </script>
    
    <nav role="navigation" class="navbar navbar-static-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" class="navbar-toggle" type="button">
                    <span class="entypo-menu"></span>
                </button>
                <button class="navbar-toggle toggle-menu-mobile toggle-left" type="button">
                    <span class="entypo-list-add"></span>
                </button>




                <div id="logo-mobile" class="visible-xs">
                   <h1>WEB管理<span>v1.2</span></h1>
                </div>

            </div>


            <!-- Collect the nav links, forms, and other content for toggling -->
            <div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">

                    <li class="dropdown" style="cursor: pointer;">
					<!-- 消息数量 -->
                        <a  onclick="dealmessage()"><i style="font-size:20px;" class="icon-conversation"></i><div class="noft-green"><span id="unread"></span></div></a>
						<!-- 此处有修改 -->
					<!-- 消息-->
                        
                    </li>
                    <li style="cursor: pointer;">
					<!-- 报警数量 -->
                        <a onclick="turnurl('AL/untreatedAlarmt?nowpage=1')"><i style="font-size:19px;" class="icon-warning tooltitle"></i><div class="noft-red"><span id="untreated"><%=untreated.size() %></span></div></a>
                    <!-- 报警 --> 
                        
                    </li>
                    <!-- 帮助-->
                    <li><a href="#"><i data-toggle="tooltip" data-placement="bottom" title="Help" style="font-size:20px;" class="icon-help tooltitle"></i></a>
                    </li>

                </ul>
                <div id="nt-title-container" class="navbar-left running-text visible-lg">
                    <ul class="date-top">
                        <li class="entypo-calendar" style="margin-right:5px"></li>
                        <li><span id="date"></span></li>


                    </ul>

                    <ul id="digital-clock" class="digital">
                        <li class="entypo-clock" style="margin-right:5px"></li>
                        <li><span id="hour"></span></li>
                        <li>:</li>
                        <li><span id="min"></span></li>
                        <li>:</li>
                        <li><span id="sec"></span></li>
                        <li class="meridiem"></li>
                    </ul>
                    <!-- 滚动天气信息 -->
                    <ul id="nt-title" style="cursor:pointer;">
                    	<%for(int i=0;i<untreated.size();i++){
                    	PageData acc=untreated.get(i);
                    	String accname=(String)acc.get("name");
                    	String didian=accname.split("_")[0];
                    	String time=accname.split("_")[2];
                    	%>
                       	
                        	<li onclick="turnurl('AL/untreatedAlarmt?nowpage=1')">单位名称：<%=didian %> 时间<%=time %></li>
                        <% }%>
                    </ul>
                </div>

                <ul style="margin-right:0;" class="nav navbar-nav navbar-right">
                    <li>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <!-- 图片，下拉菜单 -->
                            <img alt="" class="admin-pic img-circle" src="assets/img/timg.png"><%= paged.get("name") %><b class="caret"></b>
                        
                        </a>
                        <ul style="margin-top:14px;" role="menu" class="dropdown-setting dropdown-menu">
                            <li>
                                <a  href="zhuce.jsp">
                                    <span class="entypo-user"></span>&#160;&#160;,注册</a>
                            </li>
                            
                            <li class="divider"></li>
                            <li>
                                <a  onclick="turnAction('xiugaimima')">
                                    <span class="entypo-user"></span>&#160;&#160;修改密码</a>
                            </li>
                            
                            <li class="divider"></li>
                            <li>
                                <a href="FED/logout">
                                    <span class="entypo-basket"></span>&#160;&#160; 登出</a>
                            </li>
                        </ul>
                    </li>
 
                    <!-- 左拉菜单 -->
                    <li class="hidden-xs">
                        <a class="toggle-left" href="#">
                            <span style="font-size:20px;"></span>
                        </a>
                    </li>
                </ul>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- /END OF TOP NAVBAR -->

    <!-- SIDE MENU -->
    <div id="skin-select">
    <!-- logo  -->
        <div id="logo">
         <h1><p style="margin:10px 0px 0px 0px;">火灾预警平台</p></h1>
        </div>

        <a id="toggle">
            <span class="entypo-menu"></span>
        </a>
      



        <div class="skin-part">
            <div id="tree-wrap">
                <div class="side-bar">
                <!-- 左侧菜单栏 -->
                    <ul class="topnav menu-left-nest">
                         <li>
                            <a class="tooltip-tip ajax-load" href="#" title="Blog App" onclick="turnAction('map')">
                                <i class="icon-view-thumb"></i>
                                <span>首页</span>

                            </a>
                            
                        </li>

                        <li>
                            <a class="tooltip-tip ajax-load" href="#" title="Blog App" onclick="turnurl('OG/SelectAllog?nowpage=1')">
                                <i class="icon-document-edit"></i>
                                <span>单位管理</span>

                            </a>
                            
                        </li>
                        <li>
                            <a class="tooltip-tip ajax-load" href="#" title="Social" onclick="dealmessage()">
                                <i class="icon-mail"></i>
                                <span>消息管理</span>

                            </a>
                        </li>
                        <li>
                            <a class="tooltip-tip ajax-load" href="#" title="Media"><!-- 此处作了修改 -->
                                <i class="icon-location"></i>
                                <span>警情管理</span></a>
                            <ul>
                                <li>
                                    <a class="tooltip-tip2 ajax-load" href="#" onclick="turnurl('AL/untreatedAlarmt?nowpage=1')" title="Blog List"><i class="entypo-doc-text"></i><span>未处理</span></a>
                                </li>
                                <li>
                                    <a class="tooltip-tip2 ajax-load" href="#" title="Blog Detail" onclick="turnurl('AL/treatedAlarmt?nowpage=1')"><i class="entypo-newspaper"></i><span>已处理</span></a>
                                </li>
                            </ul>
                                
                        </li>
                        <li>
                            <a class="tooltip-tip ajax-load" href="#" title="Media" onclick="turnurl('MSG/messagepush')">
                                <i class="icon-document-new"></i>
                                <span>消息推送</span>

                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- END OF SIDE MENU -->
    <!--  PAPER WRAP -->
    <div class="wrap-fluid">
    <div class="container-fluid" style="position: relative;overflow: hidden;padding: 20px;min-height: 1000px;">
    <iframe  id="iframe" name="iframe" src="map.jsp" height=860px width=100% scrolling=no frameBorder=0 marginheight=0px style="position: absolute;clear:both;float:left" onload="setIframeHeight(this)">
            <div class="content-wrap">
               
                <div id="placeholder" style="width:100%;height:200px;"></div>
                


            </div>
        </iframe>
        </div>
    </div>
    <!--  END OF PAPER WRAP -->

    <!-- RIGHT SLIDER CONTENT -->
    
    
    <!-- <div class="sb-slidebar sb-right sb-style-push" style="backgroud: #FFFFFF">
    
  	<iframe src="chat.jsp" width=100% height=600 scrolling=auto frameBorder=0 marginheight=0px style="position: absolute;clear:both;float:left">
  	</iframe>
    
    
    
    </div> -->
    

  <!-- END OF RIGHT SLIDER CONTENT-->
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script src="assets/js/progress-bar/src/jquery.velocity.min.js"></script>
    <script src="assets/js/progress-bar/number-pb.js"></script>
    <script src="assets/js/progress-bar/progress-app.js"></script>



    <!-- MAIN EFFECT -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/preloader.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/load.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/main.js"></script>




    <!-- GAGE -->


    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/chart/jquery.flot.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/chart/jquery.flot.resize.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/chart/realTime.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/speed/canvasgauge-coustom.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/countdown/jquery.countdown.js"></script>



    <script src="<%=request.getContextPath()%>/assets/js/jhere-custom.js"></script>
    <script type="text/javascript">
    /* Javascript
     *
     * See http://jhere.net/docs.html for full documentation
     */
     $(function(){
 		showTime();
 		$("#unread").text(senders.length);
 	});
    
    </script>
	

</body>

</html>
