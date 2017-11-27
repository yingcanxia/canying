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
	
	<link rel="stylesheet" type="text/css" href="assets/date/WdatePicker.js">
	
    <link rel="stylesheet" type="text/css" href="assets/css/loader-style1.css">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="assets/js/progress-bar/number-pb.css">
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
<%PageList<PageData> oglist=(PageList<PageData>)session.getAttribute("oglist"); %>
			<div class="container">
			
			
				<div id="title">
					<h1><center><b>防火单位</b></center></h1>
				</div>
				<div class="row">
					
					<div class="col-sm-6 col-sm-push-7" id="div_search">
						<form  method="post" id="from1">
						<div class="col-xs-3" id="text_search" style="display:line;">
							<input type="text" class="form-control input-sm"  name="name" id="search" placeholder="单位名称" value="${tiaojian.name }" height="10px">
						</div>
						<div class="col-xs-3" id="text_search2" style="display:line;">
							<input type="text" class="form-control input-sm"  name="ogphone" id="search2" placeholder="联系方式" value="${tiaojian.ogphone }" height="10px">
						</div>
						<button class="btn btn-default btn-info btn-sm"  onclick="doSubmit(-1)">搜索</button>
						</form>	
					</div>
					<div class="col-sm-2 col-sm-push-5" style="margin: 10px 0px 0px 0px;">
					
					
						<button class="btn btn-default btn-info btn-sm" id="add"  onclick="window.location.href='orgnization_add.jsp?biaoshi=a'">添加</button>
					
					
					</div>
					
				</div>




			<div id="papermid">
                <table class="table table-bordered table-hover">
					  <thead>
					    <tr>
					      <td>单位名称</td>
					      <td>联系方式</td>
					      <td>单位地址</td>
					      <td>GPS定位</td>
					      <td>防火等级</td>
					      <td>操作</td>
					    </tr>
					  </thead>
					  <tbody>
					  <%if(oglist!=null){ %>
						<%for(int i=0;i<oglist.getPlist().size();i++){
							PageData og=oglist.getPlist().get(i);
						  %>
					    <tr>
					      <td><%=og.get("name") %></td>
					      <td><%=og.get("phone") %></td>
					      <td><%=og.get("address") %></td>
					      <td class="show-layer" data-show-layer="hw-layer"><%=og.get("GPS_lat") %>, <%=og.get("GPS_lon") %></td>
					      <td><%=og.get("security_level") %></td>
					      <td>
					      <a href="RP/selectrp.do?nowpage=1&&ogid=<%=og.get("id")%>"><button class="btn btn-default btn-xs btn-primary">查看联系人</button></a>
					      <a href="orgnization_add.jsp?biaoshi=b&&where=<%=i%>"><button class="btn btn-default btn-xs btn-success">修改</button></a>
					      <a href="OG/deleteog?biaoshi=a&&ogid=<%=og.get("id")%>" onclick= 'return confirm( "确定要删除该条记录吗? ")'><button class="btn btn-default btn-xs btn-danger">删除</button></a>
					      </td>
					    </tr>
					   <%} }%>
					  </tbody>
					  
                </table>
            </div>
            <div id="page" style="text-align:center;">
            	<ul class="pagination">
   				 	<%for(int i=0;i<oglist.getSumPage();i++){ %>
    				 	<%if(i+1==oglist.getNowpage()){ %>
    				 		<li class="active"><a href="javascript:doSubmit(<%=i+1 %>)"><%=i+1 %></a></li>
    				 	<%}else{ %>
    				 		<li ><a href="javascript:doSubmit(<%=i+1 %>)"><%=i+1 %></a></li>
    				 	<%} %>
    				 <%} %>
					<li><a href="map.jsp">返回</a></li>
				</ul>
            
            </div>
         </div>
         
         <div class="hw-overlay" id="hw-layer" style="padding:0px;">
				<div class="hw-layer-wrap" style="text-align:center;padding:10px;">
					
					
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
	
	   
	</script>
         <script type="text/javascript">
    	function del(){
    		if(confirm("确认要删除该条记录么！")==true){	
				return true;	
    		}else{
    			return false;
    		}    		   		
    	}   
    	function doSubmit(index){
			if(index==-1){
    			document.getElementById('from1').action = "OG/SelectAllog?nowpage=1";
			}
			else{
				var url="OG/SelectAllog?nowpage="+index;
				document.getElementById('from1').action = url;
			}
			document.getElementById('from1').submit();
		}
    </script>
         
         
</body>
</html>