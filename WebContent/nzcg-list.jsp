<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Nongzi_caigou"%>
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
					content: 'nmsq-table.html'
				});
			}
		</script>
	</head>

	<body>
		<div class="table-custome">
		<div>
		<div>
		<!-- 	<div class="search-big">
				<div class="search"> -->
				<form action="" method="post">
					<input type="text" placeholder="请输入关键字" name="timu">
					<input type="submit" value="查找" />
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=nz">添加</a>
					<%} %>
					</form>
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>库存</th>
					<th>价格</th>
					<th>厂家电话</th>
					<th>操作</th>
				</tr>
				<%PageList<Nongzi_caigou> nzcx=(PageList)session.getAttribute("nzcg"); %>
				<%if(nzcx.getNumber()!=0){
				for(int i=0;i<nzcx.getPlist().size();i++){ 
					Nongzi_caigou nz=nzcx.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=nz.getNongzi_mingcheng() %></td>
					<td><%=nz.getNongzi_kucunliang() %></td>
					<td><%=nz.getNongzi_jiage() %></td>
					<td><%=nz.getNongzi_changjiadianhua() %></td>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
						<a href="count.action?i=<%=i+1%>&type=nz">反馈</a>&nbsp;/&nbsp;
						<a href="nzcg_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<%} %>
				</tr>
				<%}} %>
				
			</table>
		</div>
       <table>
       <tr>
          			<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							&nbsp; 共<%=nzcx.getNumber()%>条 &nbsp; 第<%=nzcx.getNowpage() %>/<%=nzcx.getSumPage() %>页 &nbsp;      				
							<a  href="nzcx_query.action?nowpage=1" style="cursor:hand">首页</a>&nbsp; 
      				<a  href="nzcx_query.action?nowpage=<%=nzcx.getNowpage()-1 %>" style="cursor:hand">上一页</a>&nbsp;
      				<a  href="zxxx_query.action?nowpage=<%=nzcx.getNowpage()+1 %>" style="cursor:hand">下一页</a>&nbsp; 
      				<a   style="cursor:hand" href="nzcx_query.action?nowpage=<%=nzcx.getSumPage() %>">尾页</a>&nbsp; 
							&nbsp;
					</td>
          		</tr>
       </table>
	</body>
</html>