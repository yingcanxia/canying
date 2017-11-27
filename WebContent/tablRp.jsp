<%@page import="com.cust.easyutil.PageList"%>
<%@page import="java.util.List"%>
<%@page import="com.cust.util.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">

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
<%PageList<PageData> rplist=(PageList<PageData>)session.getAttribute("rplist"); %>

			<div class="container">
			
			
				<div id="title">
					<h1><center><b>消防负责人</b></center></h1>
				</div>
				<div class="row">
					
					<div class="col-sm-6 col-sm-push-7" id="div_search">
						<form id="from1" method="post">
						<div class="col-xs-3" id="text_search" style="display:line;">
							<input type="text" class="form-control input-sm" name="name" id="search" placeholder="单位名称" value="${rptiaojian.name }" height="10px">
							<input type="hidden" name="ogid" value="<%=request.getParameter("ogid")%>">
						</div>
						<div class="col-xs-3" id="text_search2" style="display:line;">
							<input type="text" class="form-control input-sm"  name="phone" id="search2" placeholder="联系方式" value="${rptiaojian.phone }" height="10px">
						</div>
						<button class="btn btn-default btn-info btn-sm" onclick="doSubmit(-1)">搜索</button>
						</form>	
					</div>
					<div class="col-sm-2 col-sm-push-5" style="margin: 10px 0px 0px 0px;">
						<button class="btn btn-default btn-info btn-sm" id="add"  onclick="window.location.href='registerrp.jsp?biaoshi=a'">添加</button>
					</div>
					
				</div>
			<div id="papermid">
                <table class="table table-bordered table-hover">
					  <thead>
					    <tr>
					      <td>名称</td>
					      <td>联系方式</td>
					      <td>操作</td>
					    </tr>
					  </thead>
					  <tbody>
					  <%if(rplist!=null&&rplist.getPlist().size()!=0){ %>
						<%for(int i=0;i<rplist.getPlist().size();i++){
							PageData rp=(PageData)rplist.getPlist().get(i);
						  %>
					    <tr>
					      <td><%=rp.get("name") %></td>
					      <td><%=rp.get("phone") %></td>
					      <td>
					      <a href="registerrp.jsp?biaoshi=b&&where=<%=i%>"><button class="btn btn-default btn-xs btn-success">修改</button></a>
					      <a href="RP/delete_rp?rpid=<%=rp.get("id")%>" onclick= 'return confirm( "确定要删除该条记录吗? ")'><button class="btn btn-default btn-xs btn-danger">删除</button></a>
					      </td>
					    </tr>
					   <%} }%>
					  </tbody>
					  
                </table>
            </div>
            <div id="page" style="text-align:center;">
            	<ul class="pagination">
    				
   				 	<!-- <li class="active"><a href="#">1</a></li>
   					<li><a href="#">2</a></li>
    				<li><a href="#">3</a></li>
    				<li><a href="#">4</a></li>
    				<li><a href="#">5</a></li>
    				 -->
    				 <%for(int i=0;i<rplist.getSumPage();i++){ %>
    				 	<%if(i+1==rplist.getNowpage()){ %>
    				 		<li class="active"><a href="javascript:doSubmit(<%=i+1 %>)"><%=i+1 %></a></li>
    				 	<%}else{ %>
    				 		<li><a href="javascript:doSubmit(<%=i+1 %>)"><%=i+1 %></a></li>
    				 	<%} %>
    				 <%} %>
					<li><a href="table.jsp">返回</a></li>
				</ul>
            
            </div>
         </div>
         <script type="text/javascript">
         	function doSubmit(index){
 				if(index==-1){
     				document.getElementById('from1').action = "RP/selectrp.do?nowpage=1";
 				}
 				else{
 					var url="RP/selectrp.do?nowpage="+index;
 					document.getElementById('from1').action = url;
 				}
 				document.getElementById('from1').submit();
 			}
         </script>

</body>
</html>