<%@page import="com.snb.hbm.orm.Shuoming_wendang"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<!--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
		<script type="text/javascript" src="layer/layer/layer.js"></script>
		<style>
			.table-custome {
				position: relative;
				width: 100%;
				height: 480px;
			  /*  margin: 100px 230px;*/
				/*border: 1px solid black;*/
				overflow: hidden;
			}
			.table{
				position: absolute;
				top:55px;
			}
			.table th,
			.table td {
				text-align: center;
				height: 38px;
			}
			
			tr:hover {
				background-color: aliceblue;
			}
			
			a:hover {
				cursor: pointer;
			}
			.search-big{
				/*background: cornflowerblue;*/
				height: 50px;
				width: 600px;
				position:absolute;
				right: 10px;
			}
			.search{
				position: absolute;
				top: 10px;
				right: 10px;
			}
			.search input:first-child{
				width: 360px;			
				}
			.search input{
				height: 30px;
				font-size: 14px;
			}
		</style>
		<script>
			function openhtml() {
				layer.open({
					type: 2,
					title: false,
					closeBtn: 1, //不显示关闭按钮
					shade: [0],
					offset:['10px',''],
//					maxmin: true, //开启最大化最小化按钮
					area: ['893px', '600px'],
					content: 'gqxx-table.html'
				});
			}
		</script>
	</head>

	<body>
		<div class="table-custome">
			<div class="search-big">
				<div class="search">
					<!--<input type="text" placeholder="请输入关键字" />
					<input type="button" value="查找" />-->
					<!--<input type="button" value="添加" />-->
					<!-- <a href="count.action?i=-1&type=sm">修改</a>&nbsp;/&nbsp; -->
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>类型</th>
					<th>上传时间</th>
					<th>操作</th>
				</tr>
				<%List<Shuoming_wendang>smwd=(List)session.getAttribute("smwd"); %>
				<%if(smwd.size()>0){ %>
				<%for(int i=0;i<smwd.size();i++){ 
					Shuoming_wendang sm=smwd.get(i);
				%>
				<tr>
					<td><%=i+1 %></td>
					<%if(sm.getType()==0) {%>
					<td>农资采购</td>
					<%}else{ %>
					<td>医疗平台</td>
					<%} %>
					<td><%=sm.getShangchuan_shijian() %></td>
					<td>
						<a href="count.action?i=<%=i+1%>&type=sm">修改</a>
					<!-- 	<a>刪除</a> -->
					</td>
				</tr>
			    <%}} %>
			</table>
		</div>

	</body>
</html>