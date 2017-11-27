 <%@page import="java.util.HashMap"%>
<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Nongyejishu"%>
<%@page import="java.util.Map"%>
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
		<div>
		<div>
		<%Map map=(Map)request.getAttribute("para");
		int xx=-1;
		int timu=-1;
		if(map!=null){
		if(map.get("xinxing")!=null){
			xx=(Integer)map.get("xinxing");
		}
		if(map.get("timu")!=null){
			timu=(Integer)map.get("timu");
		}}
		%>
	<form action="nyjs_query.action" method="post">
					
						
		<%if(timu==0){%>
			<label>全部:</label><input type="radio" name="timu"  value="-1">&nbsp;
			<label>视频:</label><input type="radio" name="timu"  value="0" checked="checked">&nbsp;
			<label>文章:</label><input type="radio" name="timu"  value="1">&nbsp;
		<%}else if(timu==1){%>
			<label>全部:</label><input type="radio" name="timu"  value="-1">&nbsp;
			<label>视频:</label><input type="radio" name="timu"  value="0">&nbsp;
			<label>文章:</label><input type="radio" name="timu"  value="1" checked="checked">&nbsp;
		<%}else{%>
			<label>全部:</label><input type="radio" name="timu"  value="-1" checked="checked">&nbsp;
			<label>视频:</label><input type="radio" name="timu"  value="0">&nbsp;
			<label>文章:</label><input type="radio" name="timu"  value="1">&nbsp;
		<%}	
		if(xx==2){%>
			<label>新型职业农民:</label><input type="checkbox" name="xinxing" checked="checked" value="2">&nbsp;
		<%}else{%>
			<label>新型职业农民:</label><input type="checkbox" name="xinxing" value="2">&nbsp;
		<%}
		%>
					
					<!-- <label>音频:</label><input type="radio" name="timu"  value="1">&nbsp; -->
					
					<input type="submit" value="查找" />
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=jsB">添加视频</a>
					<a href="count.action?i=-1&type=jsA">添加文章</a>
					<%} %>
					</form>
					
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>类型</th>
					<th>题目</th>
					<th>上传时间</th>
					<th>是否新型</th>
					<th>操作</th>
				</tr>
				
				<%PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs"); %>
				<%if(nyjs.getNumber()!=0){
				for(int i=0;i<nyjs.getPlist().size();i++){ 
					Nongyejishu js=nyjs.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<%if(js.getJishu_type()==0){ %>
					<td>视频</td>
					<td><%=js.getShipin_timu() %></td>
					<%}else if(js.getJishu_type()==1){%>
					<td>文章</td>
					<td><%=js.getWenzhang_timu() %></td>
					<%}else if(js.getJishu_type()==2){%>
					<td>音频</td>
					<td><%=js.getYinpin_timu() %></td>
					<%} %>
					<td><%=js.getChuangjian_time() %></td>
					
					<%if(js.getJishu_biaozhiwei()==2){ %>
						<td>新型农民</td>
					<%}else{ %>
						<td>其他</td>
					<%} %>
					
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
					<%if(js.getJishu_biaozhiwei()==2){//是新型 %>
					<a href="nyjs_addNew.action?i=<%=i+1%>">取消新型</a>&nbsp;/&nbsp;
					<%}else{ %>
					<a href="nyjs_addNew.action?i=<%=i+1%>">添加新型</a>&nbsp;/&nbsp;
					<%} %>
					
					<%if(js.getJishu_type()==1){ %>
						<a href="count.action?i=<%=i+1%>&type=jsA">修改</a>&nbsp;/&nbsp;
					<%}else{ %>
					<a href="count.action?i=<%=i+1%>&type=jsB">修改</a>&nbsp;/&nbsp;
					<%} %>
						<%if(js.getJishu_zhidingwei()==0){ %>
					    <a href="nyjs_zhiding.action?i=<%=i+1%>">置顶</a>&nbsp;/&nbsp;
					    <%}else{ %>
					    <a href="nyjs_cancelzhiding.action?i=<%=i+1%>">取消置顶</a>&nbsp;/&nbsp;
					    <%} %>
						<a href="nyjs_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<%} %>
				</tr>
				<%}} %>
				
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
		<form method="post" id="ds" action="nyjs_query.action">
  			<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
	        <input id="timu" style="display: none" value="${para.xinxing }" name="xinxing">
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=nyjs.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=nyjs.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=nyjs.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=nyjs.getNowpage() %>/<%=nyjs.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=nyjs.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>