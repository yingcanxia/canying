<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Gongqiu_xinxi"%>
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
					content: 'gqxx-table.html'
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
					<form action="gqxx_query.action" method="post">
					商品名称:<br><input value="${para.shangpin }" type="text" name="shangpin"/>
					<input type="submit" value="查找" />
					</form>
					<!--<input type="button" value="添加" />-->
			    </div>
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>信息的类型</th>
					<th>分类</th>
					<th>供求商品</th>
					<th>有效期</th>
					<th>信息审核状态</th>
					<th>操作</th>
				</tr>
				<%PageList<Gongqiu_xinxi> gqxx=(PageList)session.getAttribute("gqxx"); %>
				<%if(gqxx.getNumber()!=0){
				for(int i=0;i<gqxx.getPlist().size();i++){ 
					Gongqiu_xinxi gq=gqxx.getPlist().get(i);%>
				<tr>
					<td><%=i+1 %></td>
					<%if(gq.getGongqiu_xinxi_leixing()==1){ %>
					<td>供</td>
					<%}else{ %>
					<td>求</td>
					<%} %>
					
					<%if(gq.getGongqiu_xinxi_type()==1){ %>
					<td>农产品买卖</td>
					<%}else if(gq.getGongqiu_xinxi_type()==2){ %>
					<td>出租出售</td>
					<%}else if(gq.getGongqiu_xinxi_type()==3){ %>
					<td>二手买卖</td>
					<%}else if(gq.getGongqiu_xinxi_type()==4){ %>
					<td>招工求职</td>
					<%}else{ %>
					<td>其它</td>
					<%} %>
					
					<td><%=gq.getGongqiu_xinxi_shangpin() %></td>
					<td><%=gq.getGongqiu_xinxi_youxiaoqi() %></td>
					<%if(gq.getGongqiu_xinxi_shenhe_zhuangtai()==0){ %>
					<td>未通过审核</td>
					<%}else if(gq.getGongqiu_xinxi_shenhe_zhuangtai()==1){ %>
					<td>审核</td>
					<%}else{ %>
					<td>通过审核</td>
					<%} %>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
					<!-- change by Li Zhiying -->
						<a href="count.action?i=<%=i+1%>&type=gq">审核</a>&nbsp;/&nbsp;
					<!-- change by Li Zhiying -->
						<a href="gqxx_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
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
		<form method="post" id="ds" action="gqxx_query.action">
  			<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
	        <input id="timu" style="display: none" value="${para.shangpin }" name="shangpin">
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=gqxx.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=gqxx.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=gqxx.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=gqxx.getNowpage() %>/<%=gqxx.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=gqxx.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>