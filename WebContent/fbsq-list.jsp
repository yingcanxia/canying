<%@page import="com.snb.hbm.orm.Fabu_nongmin_suqiu"%>
<%@page import="com.snb.hbm.orm.Nongmin_suqiu"%>
<%@page import="com.snb.bean.PageList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="refresh" content="0;url=queryallsq.action"> -->
<title>Insert title here</title>
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
			
		/* 	tr:hover {
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
			<div class="search-big">
				<div class="search">
				<form action="fbsq_query.action" method="post">
					您所要查找诉求题目是:<br><input value="${para.timu }" type="text" name="timu"/>
					<input type="submit" value="查找" />
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=fb">创建</a>
					<%} %>
					</form>
					<!--<input type="button" value="添加" />-->
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>诉求题目</th>
					<th>诉求人</th>
					<th>电话</th>
					<th>操作</th>
				</tr>
				<%PageList<Fabu_nongmin_suqiu> b=(PageList)session.getAttribute("fbsq"); %>
				<%if(b.getNumber()!=0){
				for(int i=0;i<b.getPlist().size();i++){ 
					Fabu_nongmin_suqiu sq=b.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=sq.getFabu_nongmin_suqiu_timu() %></td>
					<td><%=sq.getFabu_suqiu_renyuan_xingming() %></td>
					<td><%=sq.getFabu_suqiu_renyuan_dianhua() %></td>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
						<a href="count.action?i=<%=i+1%>&type=fb">反馈</a>&nbsp;/&nbsp;
						<a href="fbsq_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<%} %>
				</tr>
				<%} }%>
				
			</table>
		</div>
        <table>
        <tr>
          	<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							&nbsp; 共<%=b.getNumber()%>条 &nbsp; 第<%=b.getNowpage() %>/<%=b.getSumPage() %>页 &nbsp;      				
							<a  href="fbsq_query.action?nowpage=1" style="cursor:hand">首页</a>&nbsp; 
      				<a  href="fbsq_query.action?nowpage=<%=b.getNowpage()-1 %>" style="cursor:hand">上一页</a>&nbsp;
      				<a  href="fbsq_query.action?nowpage=<%=b.getNowpage()+1 %>" style="cursor:hand">下一页</a>&nbsp; 
      				<a   style="cursor:hand" href="fbsq_query.action?nowpage=<%=b.getSumPage() %>">尾页</a>&nbsp; 
							&nbsp;
						</td>
          </tr>
        </table>
        <script type="text/javascript">
			function doSubmit(index){
				document.getElementById('nowpage').value= index;
				document.getElementById('ds').submit();
			}
		</script>
		<form method="post" id="ds" action="cctz_query.action">
	        <input id="timu" style="display: none" value="${para.timu }" name="timu">
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=b.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;" name="nowpage">
      							<input type="submit" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=b.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=b.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=b.getNowpage() %>/<%=b.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=b.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>