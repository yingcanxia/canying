<%@page import="com.snb.hbm.orm.Huangye_dianjia"%>
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
					content: 'hy-table.html'
				});
			}
			
			</script>
	</head>
<%  Huangye_dianjia _hy=(Huangye_dianjia)request.getAttribute("condition");
			String hy_mingcheng="";
			String con="";
			if(_hy!=null){
				if(_hy.getHuangye_dianjia_mingcheng()!=null&&!"".equals(_hy.getHuangye_dianjia_mingcheng())){
					con=con+"&timu="+_hy.getHuangye_dianjia_mingcheng();
					hy_mingcheng=_hy.getHuangye_dianjia_mingcheng();
				}else{
					_hy.setHuangye_dianjia_mingcheng("");
				}
				if(_hy.getHuangye_dianjia_type()!=-1&&!"".equals(_hy.getHuangye_dianjia_type())){
					con=con+"&type="+_hy.getHuangye_dianjia_type();
				}
				if(_hy.getHuangye_dianjia_suoshuzhen()==null){
					_hy.setHuangye_dianjia_suoshuzhen("");
				}
			}else{
				_hy=new Huangye_dianjia();
				_hy.setHuangye_dianjia_mingcheng("");
				_hy.setHuangye_dianjia_suoshuzhen("");
			}
		%>
	<body>
		<div class="table-custome">
		<form action="hydj_query.action" method="post">
				<select id="type0"  class="form-control" name="type" style="width: 200px;float: left;">
											<option value="-1">请选择</option>
											<option value="1">农特产店</option>
											<option value="2">企业合作社</option>
											<option value="3">农资农机</option>
											<option value="4">政府机关</option>
											<option value="5">医疗</option>
											<option value="6">物流快递</option>
											<option value="7">酒店</option>
											<option value="8">餐饮</option>
											<option value="9">娱乐</option>
											<option value="10">旅游景点</option>
											<option value="11">购物</option>
											<option value="12">出行代驾</option>
											<option value="13">公共热线</option>
											<option value="14">银行保险</option>
											<option value="15">便民</option>
											<option value="16">更多</option>
				</select>
				<select id="suoshuzhen0"  class="form-control" name="huangye_dianjia_suoshuzhen" style="width: 200px;float: left;">
											<option value="">请选择</option>
											<option value="抚松县">抚松县</option>
											<option value="抚松镇">抚松镇</option>
											<option value="松江河镇">松江河镇</option>
											<option value="万良镇">万良镇</option>
											<option value="露水河镇">露水河镇</option>
											<option value="东岗镇">东岗镇</option>
											<option value="泉阳镇">泉阳镇</option>
											<option value="仙人桥镇">仙人桥镇</option>
											<option value="北岗镇">北岗镇</option>
											<option value="漫江镇">漫江镇</option>
											<option value="兴参镇">兴参镇</option>
											<option value="新屯子镇">新屯子镇</option>
											<option value="兴隆乡">兴隆乡</option>
											<option value="抽水乡">抽水乡</option>
											<option value="沿江乡">沿江乡</option>
											<option value="全部">全部</option>
				</select>
			<div class="search-big" style="float: left;">
				<div class="search">

					<input type="text" placeholder="请输入名称" name="timu" value="<%=hy_mingcheng%>"/>
					<input type="submit" value="查找" />
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<a href="count.action?i=-1&type=hy">添加</a>
					<%} %>
			    </div>
			</div>
			<%if(_hy.getHuangye_dianjia_type()>0&&_hy.getHuangye_dianjia_type()<=16){ %>
			<script type="text/javascript">
			var leixing=document.getElementById("type0").getElementsByTagName('option');
				for(var i=0;i<leixing.length;i++){//遍历并比较
					if(<%=_hy.getHuangye_dianjia_type()%>==leixing[i].value){
						leixing[i].selected="selected";
					}
				}
				</script>
				<%} %>
				<%if(_hy.getHuangye_dianjia_suoshuzhen()!=""){ %>
			<script type="text/javascript">
			var quanbuzhen=document.getElementById("suoshuzhen0").getElementsByTagName('option');
				for(var i=0;i<quanbuzhen.length;i++){//遍历并比较
					if('<%=_hy.getHuangye_dianjia_suoshuzhen()%>'==quanbuzhen[i].value){
						quanbuzhen[i].selected="selected";
					}
				}
				</script>
				<%} %>
			    </form>
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>序号</th>
					<th>类型</th>
					<th>所属镇</th>
					<th>名称</th>
					<th>电话</th>
					<th>操作</th>
				</tr>
				<%PageList<Huangye_dianjia> hydj=(PageList)session.getAttribute("hydj");
				if(hydj.getNumber()!=0){
					for(int i=0;i<hydj.getPlist().size();i++){ 
						Huangye_dianjia hy=hydj.getPlist().get(i);
						String suoshuzhen = hy.getHuangye_dianjia_suoshuzhen();
						System.out.println("'"+suoshuzhen+"'");
				%>
				
				<tr>
					<td><%=i+1 %></td>
					
					<%if(hy.getHuangye_dianjia_type()==1){ %>
					<td>农特产店</td>
					<%}else if(hy.getHuangye_dianjia_type()==2){ %>
					<td>企业合作社</td>
					<%}else if(hy.getHuangye_dianjia_type()==3){ %>
					<td>农资农机</td>
					<%}else if(hy.getHuangye_dianjia_type()==4){ %>
					<td>政府机关</td>
					<%}else if(hy.getHuangye_dianjia_type()==5){ %>
					<td>医疗</td>
					<%}else if(hy.getHuangye_dianjia_type()==6){ %>
					<td>物流快递</td>
					<%}else if(hy.getHuangye_dianjia_type()==7){ %>
					<td>酒店</td>
					<%}else if(hy.getHuangye_dianjia_type()==8){ %>
					<td>餐饮</td>
					<%}else if(hy.getHuangye_dianjia_type()==9){ %>
					<td>娱乐</td>
					<%}else if(hy.getHuangye_dianjia_type()==10){ %>
					<td>旅游景点</td>
					<%}else if(hy.getHuangye_dianjia_type()==11){ %>
					<td>购物</td>
					<%}else if(hy.getHuangye_dianjia_type()==12){ %>
					<td>出行代驾</td>
					<%}else if(hy.getHuangye_dianjia_type()==13){ %>
					<td>公共热线</td>
					<%}else if(hy.getHuangye_dianjia_type()==14){ %>
					<td>银行保险</td>
					<%}else if(hy.getHuangye_dianjia_type()==15){ %>
					<td>便民</td>
					<%}else{ %>
					<td>更多</td>
					<%} %>
					
					<%String[] strArray = {"抚松县","抚松镇","松江河镇","万良镇","露水河镇","东岗镇","泉阳镇","仙人桥镇",
													"北岗镇","漫江镇","兴参镇","新屯子镇","兴隆乡","抽水乡","沿江乡"};
											for(int j=0;j<15;j++){  %>
											<%if(strArray[j].equals(suoshuzhen)){ %>
  											<td><%=strArray[j]%></td>
  											<%break;} }%>
					
					<td><%=hy.getHuangye_dianjia_mingcheng() %></td>
					<td><%=hy.getHuangye_dianjia_dianhua() %></td>
					<%if(session.getAttribute("user_leixing").equals("2")||session.getAttribute("user_leixing").equals("3")){ %>
					<td>
						<a href="count.action?i=<%=i+1%>&type=hy">修改</a>&nbsp;/&nbsp;
						<%if(hy.getHuangye_zhidingwei()==0){ %>
						<a href="hydj_zhiding.action?i=<%=i+1%>">置顶</a>&nbsp;/&nbsp;
						<%}else{ %>
						<a href="hydj_cancelzhiding.action?i=<%=i+1%>">取消置顶</a>&nbsp;/&nbsp;
						<%} %>
						<a href="hydj_delete.action?i=<%=i+1%>" onclick= 'return confirm( "确定要删除吗? ") '>刪除</a>
					</td>
					<% }%>
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
		<form method="post" id="ds" action="hydj_query.action">
  			<input id="nowpage2" type="text"  style="display: none;width: 30px;" name="nowpage">
	        <input id="timu" style="display: none" value="<%=_hy.getHuangye_dianjia_mingcheng() %>" name="timu">
	        <input id="type" style="display: none" value="<%=_hy.getHuangye_dianjia_type() %>" name="type">
	        <input id="huangye_dianjia_suoshuzhen" style="display: none" value="<%=_hy.getHuangye_dianjia_suoshuzhen() %>" name="huangye_dianjia_suoshuzhen">
       	 <table>
      	  <tr>
						<td colspan="2" align="right" height="20"  nowrap class="textbar3" >
							<input type="button" onclick="doSubmit(1)" value="首页">
      						<input type="button" onclick="doSubmit(<%=hydj.getNowpage()-1 %>)" value="上一页">
      							<input id="nowpage" type="text"  style="display: inline-block;width: 30px;">
      							<input type="button" onclick="doSubmit(-1)" value="Go" style="display: inline-block;">
      						<input type="button" onclick="doSubmit(<%=hydj.getNowpage()+1 %>)" value="下一页">&nbsp; 
      						<input type="button" onclick="doSubmit(<%=hydj.getSumPage() %>)" value="尾页">&nbsp; &nbsp;
							 第<%=hydj.getNowpage() %>/<%=hydj.getSumPage() %>页 &nbsp;      				
							&nbsp; 共<%=hydj.getNumber()%>条 &nbsp;
						</td>
          		</tr>
        </table>
        </form>
	</body>
</html>