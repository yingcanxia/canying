<%@page import="java.sql.Timestamp"%>
<%@page import="com.snb.bean.PageList"%>
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
			.search input{
				height: 30px;
				font-size: 14px;
			}
		</style>
		<script>
			function openhtml1() {
				layer.open({
					type: 2,
					title: false,
					closeBtn: 1, //不显示关闭按钮
					shade: [0],
					offset:['10px',''],
//					maxmin: true, //开启最大化最小化按钮
					area: ['893px', '600px'],
					content: 'nyjssp-table.html'
				});
			}
			function openhtml2() {
				layer.open({
					type: 2,
					title: false,
					closeBtn: 1, //不显示关闭按钮
					shade: [0],
					offset:['10px',''],
//					maxmin: true, //开启最大化最小化按钮
					area: ['893px', '600px'],
					content: 'nyjswz-table.html'
				});
			}
		</script>
	</head>

	<body>
		<div class="table-custome">
			<div class="search-big">
				<div class="search">
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>类型</th>
					<th>题目</th>
					<th>上传时间</th>
					<th>操作</th>
				</tr>
				
				<%PageList<Object[]> nyjs=(PageList)session.getAttribute("rxnj"); %>
				<%if(nyjs.getNumber()!=0){
				for(int i=0;i<nyjs.getPlist().size();i++){ 
					Object[] js=nyjs.getPlist().get(i);
					String jishu_type=(String)js[4];
					String shipin_timu=(String)js[5];
					String yinpin=(String)js[7];
					String Wenzhang_timu=(String)js[6];
					Timestamp chuangjian_time=(Timestamp)js[13];
					int js_id=(Integer)js[1];
					%>
				<tr>
					<td><%=i+1 %></td>
					<%if(jishu_type.equals("0")){ %>
					<td>视频</td>
					<td><%=shipin_timu %></td>
					<%}else if(jishu_type.equals("1")){%>
					<td>文章</td>
					<td><%=Wenzhang_timu %></td>
					<%}else{%>
					<td>音频</td>
					<td><%=yinpin %></td>
					<%} %>
					<td><%=chuangjian_time %></td>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
					<a href="rxjs_update.action?i=<%=js_id%>">取消推荐</a>&nbsp;/&nbsp;
					</td>
					<%} %>
				</tr>
				<%}} %>
				
			</table>
		</div>
        <table>
        <tr>
          			<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							&nbsp; 共<%=nyjs.getNumber()%>条 &nbsp; 第<%=nyjs.getNowpage() %>/<%=nyjs.getSumPage() %>页 &nbsp;      				
							<a  href="rxnj_queryYixuan.action?nowpage=1" style="cursor:hand">首页</a>&nbsp; 
      				<a  href="rxnj_queryYixuan.action?nowpage=<%=nyjs.getNowpage()-1 %>" style="cursor:hand">上一页</a>&nbsp;
      				<a  href="rxnj_queryYixuan.action?nowpage=<%=nyjs.getNowpage()+1 %>" style="cursor:hand">下一页</a>&nbsp; 
      				<a   style="cursor:hand" href="rxnj_queryYixuan.action?nowpage=<%=nyjs.getSumPage() %>">尾页</a>&nbsp; 
							&nbsp;
					</td>
          		</tr>
        </table>
	</body>
</html>