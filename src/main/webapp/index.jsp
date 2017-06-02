<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get My Parking</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <script src="resources/js/jquery-3.2.1.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#in">Check-IN</a></li>
  <li><a data-toggle="tab" href="#out">Check-OUT</a></li>
</ul>
<div class="tab-content" >
	<div id="in" class="tab-pane fade in active">
		<br>
		<br>
		<form method="post">
		
			<label>Enter Vehicle No.</label><input id="VehNo" name="VehNo" type="text">
			<br>
			<label>Enter Parking Type</label>
			<select id="LotNo" name="LotNo" >
			  <option value="0">0</option>
			  <option value="1">1</option>
			  <option value="2">2</option>
			</select>
			<br>
			<br>
			<button type="button" class="btn btn-primary" onclick="inFunction()">Check In</button>
		</form>		
	</div>
	<div id="out"  class="tab-pane fade">
	<form method="post">
		<br>
		<br>
		<label>Enter Token Id</label><input id="tokenId" name="tokenId" type="text">
		<br>
		<button type="button" class="btn btn-info" onclick="outFunction()">Check Out</button>
	</form>
	</div>
</div>
<script>
function inFunction() {
    alert("process started");
    var data= {};
	data["VehNo"] = document.getElementById("VehNo").value;
	data["LotNo"] = document.getElementById("LotNo").value;
    $.ajax({
    	type:'POST',
    	contentType : "application/json",
    	url: "checkin",
    	dataType: "json",
    	data: JSON.stringify(data),
    	
    	success: function(response){
    		alert(JSON.stringify(response));
    		return false;
    	},
    	error:function(response){
    		alert("error");
    		return false;
    	}
    	});
}
function outFunction() {
    alert("check out started");
    var data= {};
	data["tokenId"] = document.getElementById("tokenId").value;
    $.ajax({
    	type:'POST',
    	contentType : "application/json",
    	url: "checkout",
    	dataType: "json",
    	data: JSON.stringify(data),
    	
    	success: function(response){
    		alert(JSON.stringify(response));
    		return false;
    	},
    	error:function(response){
    		alert("error");
    		return false;
    	}
    	});
}
</script>
</body>
</html>