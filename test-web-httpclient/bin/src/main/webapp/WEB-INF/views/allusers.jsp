<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>allusers</title>
</head>
<body>
<input id="bn" type="button" value="getMsg"/>
<p id="msg" ></p>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js" ></script>
<script>
$("#bn").click(function(){
	$.ajax( { 
		 url: "http://localhost:8080/test-web-httpclient/service/user/getallusers",
		 type:'GET',
		 async:true,
		 dataType:'jsonp', 
		 timeout: 10000,
		  success:function(data,textStatus,jqXHR){	
			  $("#msg").html(JSON.stringify(data));
		  },
		 error: function(XMLHttpRequest, textStatus, errorMsg){
			 $("#msg").html(textStatus);
	}});
});
</script>
</body>
</html>