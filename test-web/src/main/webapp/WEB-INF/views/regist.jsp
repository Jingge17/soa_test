<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>regist</title>
</head>
<body>
年龄:<input id="age" type='text'/>
性别:<input id="sex" type='text'/>
用户昵称:<input id="unn" type='text'/>
用户名:<input id="un" type='text' maxlength='10' autofocus/>
密码:<input id="pd" type='password' maxlength='20' />
用户介绍:<input id="ui" type='text' />
<input id="bn" type="button" value="注 册"/>
<p id="msg" ></p>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js" ></script>
<script>
$("#bn").click(function(){
	$.ajax( { 
		 url: "<%=request.getContextPath()%>/service/user/doregist",
		 type:'POST',
		 async:true,
		 timeout: 10000,
		 data:{
		    age:$("#age").val(),
		    sex:$("#sex").val(),
		    userNickname:$("#unn").val(),
			userName:$("#un").val(),
		    password:$("#pd").val(),
		    userIntroduce:$("#ui").val()
		 },
		  success:function(data,textStatus,jqXHR){	  
			  window.location.href="<%=request.getContextPath()%>/index.jsp";
		  },
		 error: function(XMLHttpRequest, textStatus, errorMsg){
			 $("#msg").html(textStatus);
	}});
});
</script>





</body>
</html>