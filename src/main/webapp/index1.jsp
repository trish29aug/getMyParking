<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="org.json.simple.JSONObject,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<h2>Get My Parking</h2>
<form method="post">
<label>Enter Vehicle No.</label><input id="VehNo" name="VehNo" type="text">
<button onclick="myFunction()">Check-in</button>
<div id="demo"> </div>
</form>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
function myFunction() {

    document.getElementById("demo").innerHTML = "Hello World";
    alert("process started");
    var data= {};
	data["VehNo"] = document.getElementById("VehNo").value;
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
</script>
</body>
</html>
