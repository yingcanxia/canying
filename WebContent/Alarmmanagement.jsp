<%@page import="com.cust.easyutil.PageList"%>
<%@page import="java.util.List"%>
<%@page import="com.cust.util.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="assets/css/hwLayer.css">
	
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	<script src="http://webapi.amap.com/maps?v=1.3&key=b167560963be24772232369cb00b972f"></script>
	<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
    <style>
    #papermid{
    	background: none repeat scroll 0 0 #FFFFFF;
    	margin: 0px;
    	padding: 0px;
    	width: 100%;
    }
    body{
		padding: 0px;
		margin: 0px;
		border-radius: 0px;
		color:#000000;
		background: white;
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
    }
    #div_search{
    	margin: 10px 0px;
    }
    </style>
</head>
<body>
<%PageList<PageData> accident_log=(PageList<PageData>)session.getAttribute("accident_log");%>

			<div class="container">
			
			
				<div id="title">
					<h1><center><b>事故</b></center></h1>
				</div>
				<div class="row">
					
					<div class="col-sm-5 col-sm-push-8" id="div_search">
					</div>
					<div class="col-sm-2 col-sm-push-5" style="margin: 10px 0px 0px 0px;">
					</div>
					
				</div>




			<div id="papermid">
                <table class="table table-bordered table-hover">
					  <thead>
					    <tr>
					      <td>事故名称</td>
					      <td>受伤人数</td>
					      <td>失踪人数</td>
					      <td>GPS定位</td>
					      <td>时间</td>
					      <td>操作</td>
					    </tr>
					  </thead>
					  <tbody>
					  <%if(accident_log!=null){ %>
						<%for(int i=0;i<accident_log.getPlist().size();i++){
							PageData al=accident_log.getPlist().get(i);
						  %>
					    <tr>
					      <td><%=al.get("name") %></td>
					      <td><%=al.get("injured_person_num") %></td>
					      <td><%=al.get("losted_person_num") %></td>
					      <td class="show-layer" data-show-layer="hw-layer"><%=al.get("record_GPS_lat") %>, <%=al.get("record_GPS_lon") %></td>
					      <td> <%=al.get("time") %></td>
					      <td>
					      <%if(al.get("statue").toString().equals("0")){%>
					      <a href="accidentlog.jsp?where=<%=i%>"><button class="btn btn-default btn-xs btn-primary">查看／处理</button></a>
					      <%}else{%>
					      
					      <a href="accidentlog.jsp?where=<%=i%>"><button class="btn btn-default btn-xs btn-primary">查看</button></a>
					      
					      <%}%>
					      <%--删除
					       <a href=" FED/deleteAlarm?accidentid=<%=al.get("id")%>"><button class="btn btn-default btn-xs btn-danger" onclick='deleteinfo()'>删除</button></a> --%>
					      </td>
					    </tr>
					   <%} }%>
					  </tbody>
					  
                </table>
            </div>
            <div id="page" style="text-align:center;">
            	<ul class="pagination">
    				<%for(int i=0;i<accident_log.getSumPage();i++){ %>
    				 	<%if(i+1==accident_log.getNowpage()){ %>
    				 		<li class="active"><a href="AL/untreatedAlarmt?nowpage=<%=i+1%>"><%=i+1 %></a></li>
    				 	<%}else{ %>
    				 		<li><a href="AL/untreatedAlarmt?nowpage=<%=i+1%>"><%=i+1 %></a></li>
    				 	<%} %>
    				 <%} %>
					<li><a href="map.jsp">返回</a></li>
				</ul>
            
            </div>
         </div>
         
         <div class="hw-overlay" id="hw-layer">
				<div class="hw-layer-wrap" style="text-align:center;padding: 10px;">
					
					
							<div id="mapContainer" style="width:500;height:400px;position:relative;">
								
							</div>
					
					
				</div>
		</div>
    <script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
	  var marker,map = new AMap.Map("mapContainer", {
	       resizeEnable: true,
	       zoom: 13
	   });
	$(function(){
		//展示层
		function showLayer(id){
			var layer = $('#'+id),
				layerwrap = layer.find('.hw-layer-wrap');
			layer.fadeIn();
			//屏幕居中
			layerwrap.css({
				'margin-top': -layerwrap.outerHeight()/2
			});
		}
	
		//隐藏层
		function hideLayer(){
			$('.hw-overlay').fadeOut();
		}
	
		$('.hwLayer-ok,.hwLayer-cancel,.hwLayer-close').on('click', function() {
			hideLayer();
		});
	
		//触发弹出层
		$('.show-layer').on('click',  function() {		
			var layerid = $(this).data('show-layer');
			showLayer(layerid);
			var gps = '['+this.innerHTML+']';
			var a = this.innerHTML.split(",")[0];
			var b = this.innerHTML.split(",")[1];
			marker = new AMap.Marker({
	            map: map,
	            position: [b,a]

	        });
	        map.setCenter(marker.getPosition());
		});
	
		//点击或者触控弹出层外的半透明遮罩层，关闭弹出层
		$('.hw-overlay').on('click',  function(event) {
			if (event.target == this){
				map.remove(marker);
				hideLayer();
				
			}
		});
	
		//按ESC键关闭弹出层
		$(document).keyup(function(event) {
			if (event.keyCode == 27) {
				map.remove(marker);
				hideLayer();
			}
		});
	});
	function deleteinfo(){
		var newnum = parseInt($("#untreated",window.parent.document).text())-1;
		$("#untreated",window.parent.document).text(newnum);
		
		
		
		
	}
	   
	</script>
</body>
</html>