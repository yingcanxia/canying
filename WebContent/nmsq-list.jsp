<%@page import="java.util.HashMap"%>
<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Nongmin_suqiu"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
				<% Map map=(Map)request.getAttribute("paraB");
				if(map==null)map=new HashMap();
				Nongmin_suqiu nsq=(Nongmin_suqiu)map.get("quer");
				if(nsq==null){
					nsq=new Nongmin_suqiu();
					nsq.setState(-1);
				}
				int lstate=-1;
				if(nsq.getState()>-1){
					lstate=nsq.getState();
				}
				%>
				<form action="nmsq_query.action" method="post">
					诉求题目是:<input type="text" value="${paraB.quer.nongmin_suqiu_timu }" name="timu"/>&nbsp;
					诉求人:<input type="text" value="${paraB.quer.nongmin_suqiu_renyuan_xingming }" name="name"/>&nbsp;
					诉求人电话:<input type="text" value="${paraB.quer.nongmin_suqiu_renyuan_dianhua }" name="tel"/>&nbsp;
				<%if(lstate==1){ %>
					状态:全部<input type="radio" name="state" value="-1"/>&nbsp;
						已通过<input type="radio" name="state" value="1" checked="checked" />&nbsp;
						待审核<input type="radio" name="state" value="0"/>&nbsp;
				<%}else if(lstate==0){ %>
					状态:全部<input type="radio" name="state" value="-1"/>&nbsp;
						已通过<input type="radio" name="state" value="1" />&nbsp;
						待审核<input type="radio" name="state" value="0" checked="checked"/>&nbsp;
				<%}else{ %>
					状态:全部<input type="radio" name="state" value="-1" checked="checked"/>&nbsp;
						已通过<input type="radio" name="state" value="1" />&nbsp;
						待审核<input type="radio" name="state" value="0"/>&nbsp;
				<%} %>
					<input type="submit" value="查找" />
					</form>
					
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>诉求题目</th>
					<th>诉求人</th>
					<th>电话</th>
					<th>目前状态</th>
					<th>操作</th>
				</tr>
				<%PageList<Object[]> b=(PageList)session.getAttribute("list"); %>
				<%if(b.getNumber()!=0){
				for(int i=0;i<b.getPlist().size();i++){ 
					
					Object[] sq=b.getPlist().get(i);//Nongmin_suqiu sq=b.getPlist().get(i);
					
					String timu=(String)sq[1];
					String renyuan_xingming=(String)sq[5];
					String renyuan_dianhua=(String)sq[6];
					int zhiding=(Integer)sq[10];
					int state=(Integer)sq[7];
					%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=timu %></td>
					<td><%=renyuan_xingming %></td>
					<td><%=renyuan_dianhua %></td>
					<%if(state==0){ %>
					<td>待审核</td>
					<%}else{%>
					<td>已通过</td>
					<%} %>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
						<a href="count.action?i=<%=i+1%>&type=sq"  >反馈</a>&nbsp;/&nbsp;
						<%-- <%if(zhiding==0){ %>
						<a href="nmsq_zhiding.action?i=<%=i+1%>">置顶</a>&nbsp;/&nbsp;
						<%}else{ %>
						<a href="nmsq_cancelzhiding.action?i=<%=i+1%>">取消置顶</a>&nbsp;/&nbsp;
						<%} %> --%>
						<a href="nmsq_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<%} %>
				</tr>
				<%} }%>
			
			</table>
		</div>
       <script type="text/javascript">
			function doSubmit(index){
				if(index==-1){
					var va=document.getElementById('nowpage').value;
					if(va==""){
						va=1;
					}
					document.getElementById('nowpage2').value=va;
				}else{
					document.getElementById('nowpage2').value= index;
				}
				document.getElementById('ds').submit();
			}
		</script>
		<form method="post" id="ds" action="nmsq_query.action">
      		<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
			<input type="text" value="${paraB.quer.nongmin_suqiu_timu }" name="timu" style="display: none"/>&nbsp;
			<input type="text" value="${paraB.quer.nongmin_suqiu_renyuan_xingming }" name="name" style="display: none"/>&nbsp;
			<input type="text" value="${paraB.quer.nongmin_suqiu_renyuan_dianhua }" name="tel" style="display: none"/>&nbsp;
			<input type="text" value="${paraB.quer.state }" name="state" style="display: none"/>&nbsp;
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=b.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
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