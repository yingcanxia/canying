<%@page import="com.cust.util.PageData"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Chat</title>
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	
	
	<link rel="stylesheet" href="assets/css/jquery.treeview.css" />
	<link rel="stylesheet" href="assets/css/screen.css" />
	<script type="text/javascript" src="assets/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="assets/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="assets/js/demo.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function (){
				
				 $.ajax({
					url: 'AJAX/selectog',
					type: 'POST',
					data: {
					},
					dataType: 'json',
					success: function(list) {
						var lg=list;
						var str="";
						for(var i=0;i<lg.length;i++){
							var og=lg[i];
							str+="<li><span>"+og.name+"</span>";
							
							
							str2="<ul>"
					+		"<li><span onclick='selectfitrp(&quot;"+og.id+"&quot;)'>防火负责人</span>"
					+		"	<ul id='"+og.id+"_rp'>"
					+		"</ul></li>"
					+		"<li><span onclick='selectfitfuzeren(&quot;"+og.id+"&quot;)'>单位区域负责人</span>"
					+		"	<ul id='"+og.id+"_is'> "				
					+		"</ul></li></ul></li>"
							str+=str2;
							
							
							
							$("#og").find("li").remove();
							var branches = $(str).appendTo("#og");
							$("#black").treeview({
								add: branches,
							});
							
						}
							
					},
					error: function(list) {
						alert(list);
						alert('提交失败！');
						reg_username_mark.innerHTML = "no";
						return bool;
					}
				}); 
			});
	
	
	
	
	
	
	
	
	
	
	
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
						str+="<li><span class='file'>"+rp.name+"</span></li>";
						
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
		}
		function selectfitfuzeren(ogid){
			
			$.ajax({
				url: 'AJAX//selectis?ogid='+ogid,
				type: 'POST',
				data: {
				},
				dataType: 'json',
				success: function(list2) {
					var lg=list2;
					var str="";
					for(var i=0;i<lg.length;i++){
						var is=lg[i];
						str+="<li><span class='file'>"+is.name+"</span></li>";
						
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
		}
	</script>
</head>
<body style="font-size:12px;hieght：100%">
	<div id="main">
		<h4>关系人列表</h4>
		<div id="treecontrol">
		<a title="Collapse the entire tree below" href="#"><img src="assets/img/minus.gif" /> Collapse All</a>
		<a title="Expand the entire tree below" href="#"><img src="assets/img/plus.gif" /> Expand All</a>
		</div>
		<ul id="black" class="treeview-black">
		<li><span>联系人</span>
		<ul id='og'>
		
		
		</ul>	
		</li>
		<li>lallala</li>
	</ul>

	
	
	
	</div>


</body>
</html>