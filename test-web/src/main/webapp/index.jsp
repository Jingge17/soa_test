<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>index</title>
</head>
<body>
<input id="text" type="text"/>
<input id="but" type="button"  />
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var but=document.getElementById('but');
var text=document.getElementById('text');
alert("but");
$("#but").click(function(){
		$("#text").val("adsf");
});
</script>
</body>
</html>