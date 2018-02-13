<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login</title>
</head>
<body>

用户名:<input id="un" type='text' maxlength='10' autofocus/>
密码:<input id="pd" type='password' maxlength='20' />
<input id="bn" type="button" value="登 录"/>
<p id="msg" ></p>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js" ></script>
<script>
$("#bn").click(function(){
	$.ajax( { 
		 url: "<%=request.getContextPath()%>/service/user/dologin",
		 type:'POST',
		 async:true,
		 timeout: 10000,
		 data:{
			userName:$("#un").val(),
		    password:$("#pd").val()
		 },
		  success:function(data,textStatus,jqXHR){	  
			  $("#msg").html(data);
		  },
		 error: function(XMLHttpRequest, textStatus, errorMsg){
			 $("#msg").html(textStatus);
	}});
});
</script>
</body>
</html>