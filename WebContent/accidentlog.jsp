<%@page import="com.cust.easyutil.PageList"%>
<%@page import="java.util.List"%>
<%@page import="com.cust.util.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	<script src="http://webapi.amap.com/maps?v=1.3&key=b167560963be24772232369cb00b972f"></script>
	<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/style1.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/loader-style.css">
    <script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
    <style>
    #papermid{
    	margin-left: 10%;
    	padding: 0px;
    	width: 30%;
    }
    body{
		border-radius: 0px;
		height: 800px;
    }
    table{
    	color:#000000;
    	text-align: center;
    	width: 100%;
    }
    h1{
    	margin: 50px 0px 20px 0px;
    	padding: 0px;
    	color: black;
    }
    #div_search{
    	margin: 10px 0px;
    }
    </style>
</head>
<body>
<%PageList<PageData> accident_log=(PageList<PageData>)session.getAttribute("accident_log"); %>
		<%String where=request.getParameter("where"); %>
		<%int wei=Integer.parseInt(where); 
		PageData accident=accident_log.getPlist().get(wei);%>
			
				<div id="title">
					<h1><center><b>事故处理日志</b></center></h1>
				</div>




			<div id="papermid" style="float: left;">
                <table class="table table-bordered table-hover">
					  <tbody>
					  	<tr><td>事故名称</td><td><%=accident.get("name") %></td></tr>
					  	<tr><td>伤亡人数</td><td><%=accident.get("injured_person_num") %></td></tr>
					  	<tr><td>失踪人数</td><td><%=accident.get("losted_person_num") %></td></tr>
					  	<tr><td>时间</td><td><%=accident.get("time") %></td></tr>
					  	<tr><td>单位负责人确认</td><td>
							<%if(accident.get("RP_confirm").equals("1")){ %>
							已确认
							<%}else{ %>
							未确认
							<%} %>
						</td></tr>
					  	<tr><td>消防部门确认</td><td>
					  	<%if(accident.get("FED_confirm").equals("1")){ %>
							已确认
							<%}else{ %>
							未确认
							<%} %>
					  	</td></tr>
					  </tbody>
					  
                </table>
                <%if(accident.get("statue").toString().equals("0")){%>
              	 <div style="text-align:center;">
         	<a href="AL/deleteAlarm?accidentid=<%=accident.get("id")%>"><button class="btn btn-default btn-info btn-sm" onclick="deal()">处理</button></a>
         	<button class="btn btn-default btn-info btn-sm" onclick="javascript :history.back(-1);">返回</button>
         		</div>
         		 <% }else{%>
         		 <div style="text-align:center;">
         	<button class="btn btn-default btn-info btn-sm" disabled="disabled">已处理</button>
         	<button class="btn btn-default btn-info btn-sm" onclick="javascript :history.back(-1);">返回</button>
         		</div>
         		<%} %>
         		 
           	</div>
           
         <div id="mapContainer" style="width:40%;height: 350px;position:relative;float:right;margin-right:10%;">
            	
         </div>
         
       <script src="<%=request.getContextPath()%>/assets/js/jhere-custom.js"></script>
<script>
   var marker, map = new AMap.Map("mapContainer", {
       resizeEnable: true,
       center: [125.3247893, 43.8868593],
       zoom: 13
   });
   $().ready(function(){
	   marker = new AMap.Marker({
       	map: map,
           icon:"http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
           position: [<%=accident.get("record_GPS_lon")%>, <%=accident.get("record_GPS_lat")%>]
       });
       map.setCenter(marker.getPosition());
   });
	function deal(){
		alert("处理完毕！");
		var newnum = parseInt($('#untreated', parent.document).text())-1; 
		$('#untreated', parent.document).text(newnum);
		return true;
	}
</script>
</body>
</html>