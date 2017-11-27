<%@page import="com.snb.hbm.orm.Keshi"%>
<%@page import="com.snb.hbm.orm.YiYuan"%>
<%@page import="com.snb.hbm.orm.ZhuanJia"%>
<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Cuncuntong_zhen"%>
<%@page import="com.snb.hbm.orm.Cuncuntong_cun"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<%YiYuan yiyuan_use=(YiYuan)session.getAttribute("yiyuan_use"); %>
		<%Keshi keshi_use=(Keshi)session.getAttribute("keshi_use"); %>
				<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=zhuanjia">添加专家</a>
					<%} %>
					<a href="keshi_query.action?yyid=<%=yiyuan_use.getYiyuan_id()%>">返回科室列表</a>
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>所属医院</th>
					<th>所属科室</th>
					<th>专家姓名</th>
					<th>操作</th>
				</tr>
				<%PageList<ZhuanJia> zj=(PageList)session.getAttribute("zj"); %>
				<%if(zj.getNumber()!=0){
				for(int i=0;i<zj.getPlist().size();i++){ 
					ZhuanJia zhuanjia=zj.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=yiyuan_use.getYiyuan_mingcheng() %></td>
					<td><%=keshi_use.getKeshi_mingcheng() %></td>
					<td><%=zhuanjia.getZhuanjia_mingcheng() %></td>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
						<a href="count.action?i=<%=i+1%>&type=zhuanjia">修改</a>&nbsp;/&nbsp;
						<a href="zhuanjia_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
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
		<form method="post" id="ds" action="zhuanjia_query.action">
  			<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
			<input type="text" value="${para.mingcheng}" name="mingcheng" style="display: none;"/>
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=zj.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=zj.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=zj.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=zj.getNowpage() %>/<%=zj.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=zj.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>