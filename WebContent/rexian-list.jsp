<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Rexian"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/templatemo-style.css" />
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
	</head>

	<body>
		<div class="table-custome">
		<div>
		<div>
			<!-- <div class="search-big">
				<div class="search"> -->
				<form action="rxdh_query.action" method="post">
					<input type="text" placeholder="请输入姓名" value="${para.timu }" name="timu"/>
					<input type="submit" value="查找" />
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=rx">添加</a>
					<%} %>
				</form>
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>类型</th>
					<th>姓名</th>
					<th>电话</th>
					<th>操作</th>
				</tr>
				<%PageList<Rexian> rx=(PageList)session.getAttribute("rx"); %>
				<%if(rx.getNumber()!=0){
				for(int i=0;i<rx.getPlist().size();i++){ 
					Rexian rexian=rx.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<%if(rexian.getRexian_leixing()==0){ %>
					<td>办事员</td>
					<% }else{%>
					<td>专家</td>
					<%} %>
					<%if(rexian.getBanshi_name()==null){ %>
					<td><%=rexian.getZhuanjia_name() %></td>
					
					<%} else{%>
					<td><%=rexian.getBanshi_name()%></td>
					<%} %>
					<td><%=rexian.getZhuanjia_dianhua() %></td>
				<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
						<a href="count.action?i=<%=i+1%>&type=rx">修改</a>&nbsp;/&nbsp;
						<a href="rxjs_queryweixuan.action?rxid=<%=rexian.getRexian_id()%>">添加推荐技术</a>&nbsp;/&nbsp;
						<%-- <a href="rxjs_queryYixuan.action?rxid=<%=rexian.getRexian_id()%>">已选推荐技术</a>&nbsp;/&nbsp; --%>
						<a href="rxdh_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<%} %>
				</tr>
				<%}} %>
				
			</table>
		</div>
        <script type="text/javascript">
			function doSubmit(index){
				document.getElementById('nowpage').value= index;
				document.getElementById('ds').submit();
			}
		</script>
		<form method="post" id="ds" action="rxdh_query.action">
			<input value="${para.timu }" name="timu" style="display: none;">
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=rx.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;" name="nowpage">
      							<input type="submit" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=rx.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=rx.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=rx.getNowpage() %>/<%=rx.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=rx.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>