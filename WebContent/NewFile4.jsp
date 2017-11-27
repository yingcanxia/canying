<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>联系人</title>

	<link rel="stylesheet" href="http://static.runoob.com/assets/js/jquery-treeview/jquery.treeview.css" />
	<link rel="stylesheet" href="http://static.runoob.com/assets/js/jquery-treeview/screen.css" />

	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://static.runoob.com/assets/js/jquery-treeview/jquery.cookie.js"></script>
	<script src="http://static.runoob.com/assets/js/jquery-treeview/jquery.treeview.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(document).ready(function(){
		$("#browser").treeview({
			toggle: function() {
				console.log("%s was toggled.", $(this).find(">span").text());
			}
		});

		$("#add").click(function() {
			var branches = $("<li><span class='folder'>New Sublist</span><ul>" +
				"<li><span class='file'>Item1</span></li>" +
				"<li><span class='file'>Item2</span></li></ul></li>").appendTo("#browser");
			$("#browser").treeview({
				add: branches
			});
		});
	});
	</script>
	</head>
	<body>

	<h1 id="banner">联系人</h1>
	<div id="main">

	

	<ul id="browser" class="filetree treeview-famfamfam">
		<li><span class="folder">长春理工大学</span>
			<ul>
				<li><span class="folder">南区</span>
					<ul>
						<li><span class="file">Item 1.1.1</span></li>
					</ul>
				</li>
				<li><span class="folder">东区</span>
					<ul>
						<li><span class="folder">Subfolder 2.1</span>
							<ul id="folder21">
								<li><span class="file">File 2.1.1</span></li>
								<li><span class="file">File 2.1.2</span></li>
							</ul>
						</li>
						<li><span class="folder">Subfolder 2.2</span>
							<ul>
								<li><span class="file">File 2.2.1</span></li>
								<li><span class="file">File 2.2.2</span></li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="closed"><span class="folder">西区(closed at start)</span>
					<ul>
						<li><span class="file">File 3.1</span></li>
					</ul>
				</li>
				<li><span class="file">File 4</span></li>
			</ul>
		</li>
	</ul>

	<button id="add">Add!</button>

	

</div>

</body>
</html>
