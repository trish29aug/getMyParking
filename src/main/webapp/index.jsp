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
<h1>Get My Parking</h1>
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#staticInOut">Static In-Out Parking</a></li>
  <li><a data-toggle="tab" href="#in">Dynamic Check-IN</a></li>
  <li><a data-toggle="tab" href="#out">Dynamic Check-OUT</a></li>
</ul>
<div class="tab-content" >
	<div id="staticInOut" class="tab-pane fade in active">
		<br>
		<br>
		<form method="post">
		<h3>Cost Calculation API</h3>
		<br>
		<br>
			<label>Enter Vehicle Number:</label><input id="VehNo" name="VehNo" type="text">
			<br>
			<label>Enter Parking Type:</label>
			<select id="LotNo" name="LotNo" >
			  <option value="0">0</option>
			  <option value="1">1</option>
			  <option value="2">2</option>
			</select>
			<br>
			<label>Enter In Time:</label><input id="inTime" name="inTime" type="text">
			<br>
			<label>Enter Out Time:</label><input id="outTime" name="outTime" type="text">
			<br>
			<br>
			<button type="button" class="btn btn-primary" onclick="costCalculationAPIFunc()">Check In</button>
		</form>		
	</div>
	<div id="in" class="tab-pane fade">
		<br>
		<br>
		<form method="post">
		
			<label>Enter Vehicle No.</label><input id="Veh1No" name="Veh1No" type="text">
			<br>
			<label>Enter Parking Type:</label>
			<select id="LotNo1" name="LotNo1" >
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
		<label>Enter Token Id:</label><input id="tokenId" name="tokenId" type="text">
		<br>
		<button type="button" class="btn btn-info" onclick="outFunction()">Check Out</button>
	</form>
	</div>
</div>
<script>
function costCalculationAPIFunc() {
    var data= {};
    data["VehNo"] = document.getElementById("VehNo").value;
	data["LotNo"] = document.getElementById("LotNo").value;
	data["inTime"] = document.getElementById("inTime").value;
	data["outTime"] = document.getElementById("outTime").value;
    $.ajax({
    	type:'POST',
    	contentType : "application/json",
    	url: "costCalculationAPI",
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
    	url: "checkoutById",
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