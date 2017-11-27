<%@page import="com.cust.util.PageData"%>
<%@page import="java.util.List"%>
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
		<style type="text/css">
			.you {
				width: 40%;
				float: left;
				border: 1px solid #000000;
				height: 100%;
				background: #FFFFFF;
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
			body{
				height: 600px;
			}
		</style>
	</head>

	<body style="padding: 0;margin: 0;background: transparent;">
	<%String ogid=request.getParameter("ogid"); %>
	<%List<PageData> oglist=(List<PageData>)session.getAttribute("oglist2"); %>
			<div class="you">
			<%for(int i=0;i<oglist.size();i++){
				if(ogid.equals(oglist.get(i).get("id"))){%>
			<%PageData og=oglist.get(i);%>
				<div class="you-top">
					<%=og.get("name") %><br>
					单位电话：<%=og.get("phone") %><br>
					单位地址：<%=og.get("address") %><br>
					单位坐标：<%=og.get("GPS_lat") %>,<%=og.get("GPS_lon") %><br>
					安全等级：<%=og.get("security_level") %>
				</div>
				<div class="you-bottom" id="you-bottom">显示地图</div>
			</div>
		
	</body>
	<script type="text/javascript">
			//地图加载
			var map = new AMap.Map("you-bottom", {
				resizeEnable: true,
				zoom: 13
			});
			//输入提示
			var autoOptions = {
				input: "tipinput"
			};
			var auto = new AMap.Autocomplete(autoOptions);
			var placeSearch = new AMap.PlaceSearch({
				map: map
			}); //构造地点查询类
			AMap.event.addListener(auto, "select", select); //注册监听，当选中某条记录时会触发
			function select(e) {
				placeSearch.setCity(e.poi.adcode);
				placeSearch.search(e.poi.name); //关键字查询查询
			}
			marker = new AMap.Marker({
	        	map: map,
	            icon:"http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
	            position: [<%=og.get("GPS_lon") %>, <%=og.get("GPS_lat") %>]
	        });
	        map.setCenter(marker.getPosition());
		</script>
			<%} }%>	
</html>