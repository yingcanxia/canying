<%@page import="com.snb.bean.PageList"%>
<%@page import="com.snb.hbm.orm.Xingzheng_yuyue"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.text.SimpleDateFormat" %>
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
					content: 'xingzhengyuyue.html'
				});
			}
		</script>
	</head>

	<body>
		<div class="table-custome">
			<div >
				<div >
				<% Map map=(Map)request.getAttribute("paraB");
				Xingzheng_yuyue xyy=new Xingzheng_yuyue();
				if(map!=null)xyy=(Xingzheng_yuyue)map.get("query");
				int lstate=-1;
				if(xyy.getXingzheng_yuyue_zhuangtai()>-1){
					lstate=xyy.getXingzheng_yuyue_zhuangtai();
				}
				%>
					<form action="xzyy_query.action" method="post" >
					行政预约题目:<input type="text" name="timu" value="${paraB.query.xingzheng_yuyue_timu }"/>
					联系人:<input type="text" name="name" value="${paraB.query.xingzheng_yuyue_renyuan_xingming }"/>&nbsp;
					联系电话:<input type="text" name="tel" value="${paraB.query.xingzheng_yuyue_renyuan_dianhua }"/>&nbsp;
				<%if(lstate==0){ %>
					状态:全部<input type="radio" name="zhuangtai" value="-1">&nbsp;
						暂不接受<input type="radio" name="zhuangtai" value="0" checked="checked">&nbsp;
						待审核<input type="radio" name="zhuangtai" value="1">&nbsp;
						已接受<input type="radio" name="zhuangtai" value="2">&nbsp;
				<%}else if(lstate==1){ %>
					状态:全部<input type="radio" name="zhuangtai" value="-1">&nbsp;
						暂不接受<input type="radio" name="zhuangtai" value="0">&nbsp;
						待审核<input type="radio" name="zhuangtai" value="1" checked="checked">&nbsp;
						已接受<input type="radio" name="zhuangtai" value="2">&nbsp;
				<%}else if(lstate==2){ %>
					状态:全部<input type="radio" name="zhuangtai" value="-1">&nbsp;
						暂不接受<input type="radio" name="zhuangtai" value="0">&nbsp;
						待审核<input type="radio" name="zhuangtai" value="1">&nbsp;
						已接受<input type="radio" name="zhuangtai" value="2" checked="checked">&nbsp;
				<%}else{ %>
					状态:全部<input type="radio" name="zhuangtai" value="-1" checked="checked">&nbsp;
						暂不接受<input type="radio" name="zhuangtai" value="0">&nbsp;
						待审核<input type="radio" name="zhuangtai" value="1">&nbsp;
						已接受<input type="radio" name="zhuangtai" value="2">&nbsp;
				<%}%>
					<input type="submit" value="查找" />
					</form>
					<!--<input type="button" value="添加" />-->
			    </div>
			</div>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>题目</th>
					<th>日期</th>
					<th>时间点</th>
					<th>联系人</th>
					<th>联系人电话</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%PageList<Xingzheng_yuyue> xzyy=(PageList)session.getAttribute("xzyy"); %>
				<%if(xzyy.getPlist()!=null){
				for(int i=0;i<xzyy.getPlist().size();i++){ 
					Xingzheng_yuyue yy=xzyy.getPlist().get(i);
					Timestamp tm = yy.getXingzheng_yuyue_riqi();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String riqi = df.format(tm);
					%>
					
				<tr>
					<td><%=i+1 %></td>
					<td><%=yy.getXingzheng_yuyue_timu() %></td>
					<td><%=riqi%></td>
					<td><%=yy.getXingzheng_yuyue_shijianduan() %></td>
					<td><%=yy.getXingzheng_yuyue_renyuan_xingming() %></td>
					<td><%=yy.getXingzheng_yuyue_renyuan_dianhua() %></td>
					<%if(yy.getXingzheng_yuyue_zhuangtai()==0){ %>
					<td>暂不接受</td>
					<%}else if(yy.getXingzheng_yuyue_zhuangtai()==1){ %>
					<td>待审核</td>
					<%}else{ %>
					<td>已接受</td>
					<%} %>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
					<!-- change by Li Zhiying -->
						<a href="count.action?i=<%=i+1%>&type=yy">审核</a>&nbsp;/&nbsp;
					<!-- change by Li Zhiying -->
						<a href="xzyy_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
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
		<form method="post" id="ds" action="xzyy_query.action">
  			<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
			<input type="text" name="timu" value="${paraB.query.xingzheng_yuyue_timu }" style="display: none;"/>
			<input type="text" name="name" value="${paraB.query.xingzheng_yuyue_renyuan_xingming }" style="display: none;"/>
			<input type="text" name="tel" value="${paraB.query.xingzheng_yuyue_renyuan_dianhua }" style="display: none;"/>
			<input type="text" value="${paraB.query.xingzheng_yuyue_zhuangtai}" name="zhuangtai" style="display: none;"/>
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=xzyy.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=xzyy.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=xzyy.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=xzyy.getNowpage() %>/<%=xzyy.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=xzyy.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>