<%@page import="com.snb.hbm.orm.Users"%>
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
				/* height: 480px; */
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
					content: 'zcfg-table.html'
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
				<form action="user_queryuser0.action" method="post">
					<input type="text" placeholder="请输入电话号码" name="timu" value="${para.timu }"/>
					<input type="submit" value="查找" />
				</form>
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>电话</th>
					<th>地址</th>
					<th>注册时间</th>
					<th>最后登录时间</th>
					<!-- <th>最后登录时间</th> -->
					<!-- <th>用户类型</th> -->
					<th>操作</th>
				</tr>
				<%PageList<Users> users=(PageList)session.getAttribute("user0"); %>
				<%if(users.getNumber()!=0){
				for(int i=0;i<users.getPlist().size();i++){ 
					Users us=users.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=us.getUser_yonghuming_id() %></td>
					<td><%=us.getUser_xingming() %></td>
					<td><%=us.getUser_dianhua() %></td>
					<td><%=us.getUser_dizhi() %></td>
					<td><%=us.getUser_zhuce_shijian() %></td>
					<td><%=us.getUser_lastlogin() %></td>
					<td>
					<a href="user_updateC.action?i=<%=i+1%>" onclick= 'return confirm( "确定恢复吗? ") '>恢复默认密码</a>
					<a href="user_realdelete.action?i=<%=i+1%>" onclick= 'return confirm( "确定删除吗? ") '>删除</a>
					</td>
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
		<form method="post" id="ds" action="user_queryuser0.action">
      		<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
			<input value="${para.timu }" name="timu" style="display: none;"/>
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=users.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=users.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=users.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=users.getNowpage() %>/<%=users.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=users.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>