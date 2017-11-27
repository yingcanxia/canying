<%@page import="com.cust.util.PageData"%>
<%@page language="java" contentType="text/html; charset=UTF-8 "
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
	<%PageData paged=(PageData)session.getAttribute("pduser"); %>
    
    </script>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	<script src="http://webapi.amap.com/maps?v=1.3&key=b167560963be24772232369cb00b972f&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
	<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=7308a10a069b06b3b6c44d0d3d5e0eba&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
   	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/style1.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/loader-style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" type="text/css" href="<%=request.getContextPath()%>/assets/js/progress-bar/number-pb.css">


    <style type="text/css">
    canvas#canvas4 {
        position: relative;
        top: 20px;
    }
    body{
    	backgroud: white;
    }
    </style>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/assets/ico/minus.png">
</head>
<body>
		<div class="container-fluid paper-wrap bevel tlbr">
			<div class="row">
                <div id="paper-top">
                    

                    
                </div>
            </div>
            <!--/ TITLE -->

            <!-- BREADCRUMB -->
            <ul id="breadcrumb">
                
                <li class="pull-right">
                    <div class="input-group input-widget">

                        <input id="tipinput"  style="border-radius:15px" type="text" placeholder="搜索..." class="form-control">
                    </div>
                </li>
            </ul>
            
            <div id="paper-middle">
				<div id="mapContainer" style="width:100%;position:relative;"></div>
            </div>
            
            
            <!-- FOOTER -->
                <div class="footer-space"></div>
                <div id="footer">
                    <div class="devider-footer-left"></div>
                    <div class="time">
                        <p id="spanDate">
                        <p id="clock">
                    </div>
                   
				

                </div>
                <!-- / END OF FOOTER -->
            
            
            
            
           </div>    
            <script src="<%=request.getContextPath()%>/assets/js/jhere-custom.js"></script>

	
   <script>
   var marker,infoWindow, map = new AMap.Map("mapContainer", {
       resizeEnable: true,
       center: [125.3247893, 43.8868593],
       zoom: 13
   });
  
    var autoOptions = {
			input : "tipinput"
		};

		var auto = new AMap.Autocomplete(autoOptions);

		var placeSearch = new AMap.PlaceSearch({
			map : map
		});
		
		//构造地点查询类
		AMap.event.addListener(auto, "select", select); //注册监听，当选中某条记录时会触发 
		function select(e) {
			placeSearch.setCity(e.poi.adcode);
			placeSearch.search(e.poi.name,function(status,result){
				alert(status);
				alert(result);
			});//关键字查询查询
		}
        
        

	    function addMarker(x,y,accidentname,role) {
	        if (false) {
	            return;
	        }
	        marker = new AMap.Marker({
	        	map: map,
	            icon:"http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
	            position: [x, y]
	        });
	        map.setCenter(marker.getPosition());
	        infoWindow = new AMap.InfoWindow({
	            content: "<p>事故名称："+accidentname+"</p><p>经度："+x+" 维度："+y+"</p><p>角色："+role+"</p>",
	            autoMove: true,
	            offset: {x: 0, y: -30}
	        });
	        marker.on('mouseover', function(e){
	            infoWindow.open(map, marker.getPosition());
	        });
	    }
</script>
           
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



   


</body>
</html>