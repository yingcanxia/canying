<%@page import="java.math.BigInteger"%>
<%@page import="java.math.BigDecimal"%>

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
				/* height: 500px; */
			  /*  margin: 100px 230px;*/
				/*border: 1px solid black;*/
				overflow: hidden;
			}
			/* .table{
				position: absolute;
				top:55px;
			} */
			.table{
				position:relative;
				top:15px;
			}
			.table th,
			.table td {
				text-align: center;
				height: 38px;
			}
			
			/* tr:hover {
				background-color: aliceblue;
			} */
			
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
					content: 'rexian-table.html'
				});
			}
		</script>
		<script src="js/laydate/laydate.js"></script>
		<style type="text/css">
			.date-div input{
				cursor: default;
			}
		</style>
	</head>

	<body>
		<div class="table-custome">
			
			<div class="date-div">
			<form action="" method="post">
			<input class="laydate-icon" placeholder="起始日期"id="demo" value="" onclick="laydate()" value="${para.qishi }" name="qishi">
			<input class="laydate-icon" placeholder="结束日期"id="demo" value="" onclick="laydate()" value="${para.jieshu }" name="jieshu">
			<input type="submit" value="提交" />
			</form>
			<%-- <p>app下载量<%=session.getAttribute("downloadzt") %></p> --%>
		</div>
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>表名</th>
					<th>浏览次数</th>
				</tr>
				<%List<Object[]> llzt=(List)session.getAttribute("llzt"); %>
				<%if(llzt.size()!=0){
					for(int i=0;i<llzt.size();i++){
						Object[]a=llzt.get(i);
						String duixiang=(String)a[0];
						BigInteger cishu=(BigInteger)a[1];
					%>
				<tr>
					<td><%=i+1 %></td>
					<%if(duixiang.equals("Fabu_nongmin_suqiu")){ %>
					<td>发布诉求</td>
					<%}else if(duixiang.equals("Cuncuntong_cun")){ %>
					<td>村村通-村</td>
					<%}else if(duixiang.equals("Cuncuntong_zhen")){ %>
					<td>村村通-镇</td>
					<%}else if(duixiang.equals("Gongqiu_xinxi")){ %>
					<td>供求信息</td>
					<%}else if(duixiang.equals("Huangye_dianjia")){ %>
					<td>黄页</td>
					<%}else if(duixiang.equals("Jiage_tongbao")){ %>
					<td>价格通报</td>
					<%}else if(duixiang.equals("Huifu_nongmin_suqiu")){ %>
					<td>回复诉求</td>
					<%}else if(duixiang.equals("Nongshi_tixing")){ %>
					<td>农事提醒</td>
					<%}else if(duixiang.equals("Nongyejishu")){ %>
					<td>农业技术</td>
					<%}else if(duixiang.equals("Nongzi_caigou")){ %>
					<td>农资采购</td>
					<%}else if(duixiang.equals("Rexian")){ %>
					<td>热线电话</td>
					<%}else if(duixiang.equals("Xinwen")){ %>
					<td>新闻</td>
					<%}else if(duixiang.equals("Xingzheng_yuyue")){ %>
					<td>行政预约</td>
					<%}else if(duixiang.equals("Zhengce_fagui")){ %>
					<td>政策法规</td>
					<%}else if(duixiang.equals("Zhanxiao_xinxi")){ %>
					<td>展销信息</td>
					<%}else if(duixiang.equals("User")){ %>
					<td>用户</td>
					<%}else if(duixiang.equals("Nongmin_suqiu")){ %>
					<td>农民诉求</td>
					<%}else if(duixiang.equals("Scroll_img")){ %>
					<td>首页图片</td>
					<%}else if(duixiang.equals("Shuoming_wendang")){ %>
					<td>说明文档</td>
					<%} %>
					<td><%=cishu %></td>
				</tr>
				
				<%}} %>
				<tr>
				<td>8</td>
				<td>app下载量</td>
				<td><%=session.getAttribute("downloadzt") %>次</td>
				</tr>
			</table>
		</div>
       
	</body>
</html>