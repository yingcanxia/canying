<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Cuncuntong_zhen"%>
<%@page import="com.snb.hbm.orm.Cuncuntong_cun"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			/* 	height: 500px; */
			  /*  margin: 100px 230px;*/
				/*border: 1px solid black;*/
				overflow: hidden;
			}
			/* .table{
				position: absolute;
				top:55px;
			} */
			.table{
				/* position: absolute; */
				position:relative;
				top:15px;
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
					content: 'cct-table.html'
				});
			}
		</script>
	</head>

	<body>
		<div class="table-custome">
		<div>
		<div>
			<!-- <div class="search-big">
				<div class="search"> -->
				<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=cun">添加村</a>
					<%} %>
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>属于</th>
					<th>村名称</th>
					<th>操作</th>
				</tr>
				<%PageList<Cuncuntong_cun> cctc=(PageList)session.getAttribute("cctc");
				PageList<Cuncuntong_zhen> cctz=(PageList)session.getAttribute("cctz");%>
				<%if(cctc.getNumber()!=0){
				for(int i=0;i<cctc.getPlist().size();i++){ 
					Cuncuntong_cun cun=cctc.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<%for(int j=0;j<cctz.getPlist().size();j++){
						Cuncuntong_zhen zhen=cctz.getPlist().get(j);
						if(cun.getFk_zhen_id()==zhen.getZhen_id()){%>
					<td><%=zhen.getZhen_mingcheng() %></td>
					<%}} %>
					<td><%=cun.getCun_mingcheng() %></td>
						<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
					<!-- change by Li Zhiying -->
						<a href="count.action?i=<%=i+1%>&type=cun">修改</a>&nbsp;
					<!-- change by Li Zhiying -->
						<%-- <a href="cctc_delete.action?i=<%=i+1%>">刪除</a> --%>
						<a href="cctc_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<%} %>
				</tr>
				<%}} %>
			</table>
		</div>

	</body>
</html>