$(document).ready(function(){

	// first example
	$("#browser").treeview();

	// second example
	$("#navigation").treeview({
		persist: "location",
		collapsed: true,
		unique: true
	});

	// third example
	$("#red").treeview({
		animated: "fast",
		collapsed:true,
		unique: true,
	control: "#treeclose",	persist: "cookie",

cookieId: "red",		toggle: function() {
			window.console && console.log("%o was toggled", this);
		}
	});

	// fourth example
	$("#black,  #gray").treeview({
		control: "#treecontrol",
		persist: "location",
		
	});

});