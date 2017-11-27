<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="icon" href="https://o1aqprei7.qnssl.com/favicon.ico">
<link type="text/css" rel="stylesheet" media="all" href="https://o1aqprei7.qnssl.com/assets/css/hb_style.css?d63f0748b8f3671ac113">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.js"></script>
<script type="text/javascript" src="layer/layer/layer.js"></script>
<link rel="stylesheet" href="css/admin.css" />
<link rel="stylesheet" href="css/amazeui.css" />
<link rel="stylesheet" href="css/amazeui.flat.css" />
<link rel="stylesheet" href="css/amazeui.flat.min.css" />
<link rel="stylesheet" href="css/amazeui.min.css" />
<link rel="stylesheet" href="css/app.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/amazeui.ie8polyfill.js"></script>
<script type="text/javascript" src="js/amazeui.ie8polyfill.min.js"></script>
<script type="text/javascript" src="js/amazeui.js"></script>
<script type="text/javascript" src="js/amazeui.min.js"></script>
<script type="text/javascript" src="js/amazeui.widgets.helper.js"></script>
<script type="text/javascript" src="js/amazeui.widgets.helper.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/handlebars.min.js"></script>
<style type="text/css">
.hb_wrap .hb_workspaces {
	top: -316px;
}

.hb_wrap_bg {
	height: 782px;
}

.hb_wrap_workspaces .hb_wrap .hb_workspaces {
	top: 0px;
	max-height: 782px;
}

.hb_wrap_workspaces .hb_wrap .switch_notice {
	top: 342px;
}

.hb_wrap_workspaces .hb_wrap .hb_header {
	top: 316px;
}

.hb_wrap_workspaces .hb_wrap .hb_main {
	margin-top: 396px;
}

.hb_wrap_workspaces .hb_wrap .hb_app {
	top: 396px;
}

.hb_wrap .hb_app {
	min-height: 702px;
}

.hb_nav_setting .hb_wrap_workspaces .hb_wrap .hb_setting {
	margin-top: 396px;
}

.hb_wrap .hb_setting {
	height: auto !important;
	height: 702px;
	min-height: 702px;
}

.mod_custom .custom_options {
height: 1000px;
}
.scroll {
	/* height: 682px; */
	height: 520px;
}

.mod_custom .custom_specific .scroll {
	height: 594px;
}

.hb_item_side .side_content {
	height: 590px;
}

.hb_item_side.h_reply .side_content {
	height: 442px;
}

.hb_item_side.h_comment .side_content {
	height: 467px;
}

.hb_item_side.h_disable .side_content {
	height: 642px;
}

.hb_doc_scroll, .container_scroll {
	height: 694px;
}

.hb_market .market_main, .hb_market .market_side, .hb_market .market_appitem,
	.hb_market .market_body {
	height: 637px;
}

.popover.in {
	display: block;
	width: 190px;
	height: auto;
	left: 780px;
	top: 24px;
}

.orderBy {
	display: none;
	width: 190px;
	height: auto;
	left: 920px;
	top: 24px;
}

.btn_orderBy {
	cursor: pointer;
}

.mod_bulkoperation {
	display: none;
}

.tbl_height {
	width: 100%;
	height: 582px;
	overflow: scroll;
}

.tbl, .tbl th, .tbl td {
	border: 1px solid #eeecec;
}

.tbl th, .tbl td {
	padding: 0.5%;
	text-align: center;
}

.tbl {
	width: 1600px;
	border-left: none;
	border-right: none;
}

.tbl tr:hover, .tbl tr:nth-child(2n):hover {
	cursor: pointer;
	background-color: #f2f6ff;
}

.tbl tr:nth-child(2n) {
	background-color: #f6f7f8;
}

.tbl tr th:first-child, .tbl tr td:first-child {
	border-left: none;
}

.tbl tr th:first-child, .tbl tr td:last-child {
	border-right: none;
}

.item_view{
	margin-left: 10px;
}

.view_mark_ul {
	display: none;
}

/* .div-iframe-big {
	position: relative;
	width: 1050px;
	/*height: 830px;*/
	/* top: -30px;
	left: 260px; */
	/*background: blue;*/
} */
/*.div-iframe-button{
	position: absolute;
	top: 0px;
	right: 0px;
}*/
/* .div-iframe {
	position: absolute;
	top: 80px;
} */
.footer {
	position: fixed;
	bottom: 0px;
	background: #f0f9fd;
	width: 100%;
	height: 35px;
	border-top: solid 1px #c2d2d7;
	line-height: 30px;
	color: #98a4a9;
}
</style>
<script type="text/javascript">
	function select(divID) {
		var ulid = divID.substring(0, divID.length - 2);//下拉菜单
		var str = '#' + ulid;
		$(str).toggle();

	}

	function turnHtml(url) {
		var strurl = url + '.jsp';
		$("#right-iframe").attr("src", strurl);
		$("#right-iframe").reload();
	}
	function turnAction(url) {
		var strurl = url + '.action';
		$("#right-iframe").attr("src", strurl);
		$("#right-iframe").reload();
	}
</script>
<script type="text/javascript">
	$(function(){
		$(".view_mark_ul li").click(function(){
			   $(this).css({background:"#add8e6"})
			   .siblings().css("background","");
			   var $this= $(this);
			   $(".view_mark_ul:visible").find('li').not($this).css("background","");
		});
	});
</script>
<script type="text/javascript">
	$(function(){
		$(".view_mark_ul li").click(function(){
			   $(this).css({background:"#add8e6"})
			   .siblings().css("background","");
			   var $this= $(this);
			   $(".view_mark_ul:visible").find('li').not($this).css("background","");
		});
	});
</script>
</head>
<body class="hb_nav_app" style="overflow-y: hidden;">
	<!-- style="overflow-y: hidden;" -->
	<div id="root">
		<div class="hb_wrap">
			<!--<div>
			<div>-->
			<div class="hb_workspaces">
				<div class="workspaces_h">
					<span>工作区</span>
					<p>和小伙伴一起共享数据的地方</p>
				</div>
				<div class="workspaces_c cl">
					<ul class="mod_workspaces cl">
						<li>
							<div class="mod_cover c_business"
								style="background-color: #79BEAF;">
								<span class="bl"></span><span class="br"></span>
								<h3 class="" style="color: #FFFFFF; background-color: #79BEAF;">三农宝</h3>
								<img
									src="https://oss-cn-hangzhou.aliyuncs.com/hb-v4-public/space_cover/1045757/0"
									class="photo">
							</div>
						</li>
						<li class="workspace_add">
							<div>
								<i></i><span>创建工作区</span>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="switch_workspaces undefined">
				<i title="选择工作区"></i>
			</div>
			<div class="hb_header undefined">
				<div class="set cl">
					<em class="pipe"></em><span class="mod_avatar avt_small"
						title="三农宝"><img
						src="https://oss-cn-hangzhou.aliyuncs.com/hb-v4-public/user_avatar/1311437/32"
						class="avatar" draggable="false"><span><%=session.getAttribute("user_yonghuming_id")%></span></span><a
						href="###" style="display: none;">hidden</a><a href="###"
						style="display: none;">hidden</a><a href="###"
						style="display: none;">hidden</a><a href="###"
						style="display: none;">解绑</a><a href="###" style="display: none;">解绑</a><a
						href="###" style="display: none;">解绑</a>
				</div>
				<div class="nav nav_second nav_back">
					<h1>
						<span><cite>三农宝后台管理系统</cite></span>
					</h1>
					<h2>
						<span><cite>抚松县农业信息服务中心</cite></span>
					</h2>
				</div>
			</div>
			<!--</div>-->
			<!--<div>-->
			<div class="hb_app">
				<div class="hb_frame cl">
					<div class="hb_row cl ">
						<div class="col_2">
							<div class="fixed_custom">
								<div class="mod_custom hb_condition">
									<div class="custom_options" style="margin-top: -10px;margin-left:-20px;background: #f0f9fd;">
										<div class="scroll">
											<!-- <div class="item_view" id="jump">
													<div class="view_mark jump"><i></i><b>设置表格及权限</b><cite><i></i></cite><span class="guidepoint gp_install_app_setting" data-tip="在这里调整表格结构和数据的展现形式"><span></span></span></div>
												</div> -->

											<div class="item_view">
												<div class="view_mark jump" id="first_1"
													onclick="select(this.id)">
													<a href="###" class="add_view"><i data-tip="添加新筛选"></i></a><i></i><b>app管理</b>
												</div>
												<%
												if (session.getAttribute("user_leixing").equals("2") || session.getAttribute("user_leixing").equals("3")) {
											%>
												<ul class="view_mark_ul" id="first">
													<!--<li class="selected"><b>全部</b></li>-->
													<li onclick="turnAction('si_query')"><b>首页图片</b></li>
												</ul>
												<%} %>
											</div>

											<div class="item_view" id="jump">
												<div class="view_mark jump" id="one_1"
													onclick="select(this.id)">
													<i></i><b>农民事务</b><cite><i></i></cite>
												</div>
												<ul id="one" class="view_mark_ul">
													<li onclick="turnAction('nmsq_query')"><b>农民诉求</b></li>
												<!-- 	<li onclick="turnAction('fbsq_query')"><b>示例诉求</b></li> -->
													<li onclick="turnAction('xzyy_query')"><b>行政预约</b><cite><i></i></cite></li>
													<li onclick="turnAction('cctz_query')"><b>村村通</b><cite><i></i></cite></li>
												</ul>
											</div>

											<div class="item_view" id="jump">
												<div class="view_mark jump" id="two_1"
													onclick="select(this.id)">
													<i></i><b>农产品信息</b><cite><i></i></cite>
												</div>
												<ul class="view_mark_ul" id="two">
													<li onclick="turnAction('zxxx_query')"><b>展销平台</b><cite><i></i></cite></li>
													<li onclick="turnAction('gqxx_query')"><b>供求信息</b><cite><i></i></cite></li>
												</ul>
											</div>

											<div class="item_view" id="jump">
												<div class="view_mark jump" id="three_1"
													onclick="select(this.id)">
													<i></i><b>农业信息</b><cite><i></i></cite>
												</div>
												<ul class="view_mark_ul" id="three">
													<li onclick="turnAction('zcfg_query')"><b>政策法规</b><cite><i></i></cite></li>
													<li onclick="turnAction('nyjs_query')"><b>农业技术</b><cite><i></i></cite></li>
													<li onclick="turnAction('jgtb_query')"><b>价格通报</b><cite><i></i></cite></li>
													<!-- <li onclick="turnAction('rxdh_query')"><b>热线电话</b><cite><i></i></cite></li> -->
													<!-- <li onclick="turnAction('nzcg_query')"><b>农资采购</b><cite><i></i></cite></li> -->
												</ul>
											</div>

											<div class="item_view" id="jump">
												<div class="view_mark jump" id="four_1"
													onclick="select(this.id)">
													<i></i><b>公告</b><cite><i></i></cite>
												</div>
												<ul class="view_mark_ul" id="four">
													<li onclick="turnAction('xinwen_query')"><b>新闻</b><cite><i></i></cite></li>
													<li onclick="turnAction('hydj_query')"><b>黄页</b><cite><i></i></cite></li>
													<li onclick="turnAction('nstx_query')"><b>农事提醒</b><cite><i></i></cite></li>
													<%
														if (session.getAttribute("user_leixing").equals("2") || session.getAttribute("user_leixing").equals("3")) {
													%>
													<li onclick="turnAction('smwd_query')"><b>说明文档</b><cite><i></i></cite></li>
													<%} %>
													<!--<li onclick="turnHtml('cct-list')"><b>活动</b><cite><i></i></cite></li>-->
												</ul>
											</div>
											<%
												if (session.getAttribute("user_leixing").equals("2") || session.getAttribute("user_leixing").equals("3")) {
											%>
											<div class="item_view" id="jump">
												<div class="view_mark jump" id="five_1"
													onclick="select(this.id)">
													<i></i><b>用户管理</b><cite><i></i></cite>
												</div>
												<ul class="view_mark_ul" id="five">
													<%
														if (session.getAttribute("user_leixing").equals("3")) {
													%>
													<li onclick="turnAction('user_query')"><b>授权管理</b><cite><i></i></cite></li>
													<li onclick="turnAction('user_querymanager')"><b>管理员工作量统计</b><cite><i></i></cite></li>
													<li onclick="turnAction('user_queryuser0')"><b>app用户管理</b><cite><i></i></cite></li>
													<%
														}
													%>
													<li onclick="turnHtml('user-table')"><b>修改个人密码</b><cite><i></i></cite></li>
												</ul>
											</div>
											<div class="item_view" id="jump">
												<div class="view_mark jump" id="six_1"
													onclick="select(this.id)">
													<i></i><b>我的</b><cite><i></i></cite>
												</div>
												<ul class="view_mark_ul" id="six">
													<li onclick="turnAction('rztj_gztj')"><b>工作量统计</b><cite><i></i></cite></li>
                                                    <li onclick="turnAction('lltj_queryZongti')"><b>浏览量统计</b><cite><i></i></cite></li>
												</ul>
											</div>
											<%
												}
											%>
										</div>
									</div>
									<div class="custom_specific"></div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="div-iframe-big" ><!-- style="overflow-y: auto; overflow-x: hidden; -webkit-overflow-scrolling: touch" -->	
					<div  class="div-iframe">
						<iframe id="right-iframe" src="welcome.html" frameBorder="0" width="83%" height="500px" scrolling="auto" style="overflow-y: auto;position: absolute;right:16px"></iframe>
					</div>
				</div>
				</div>
              	<div class="footer" style="position: fixed;bottom: 0px;width: 100%;height: 35px;background: #f0f9fd;border-top: solid 1px #c2d2d7;"></div>		

				<script src="assets/js/jquery.min.js"></script>
</body>
</html>