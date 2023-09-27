<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.getSession().invalidate();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="includes/errors/css/style.css" media="all" rel="stylesheet" type="text/css" />
 <script language="javascript" src="includes/3rdparty/jquery-1.5.min.js"></script>
  </head>
  <script type="text/javascript">
  <%int a=5;%>
  function load(){
	  runCount(3);
	  setTimeout("history.go(-1)",5000) ;
  }
  function runCount(t){
	    if(t>0){
	      $("#tips").html(t);
	      t--;
	      setTimeout(function(){runCount(t);},1000);
	    }
	  }

  </script>
  <body onload="load()">
<div class="wrap">
	<h1>抱歉！！！！！！</h1>
	<div class="banner">
		<img src="includes/errors/images/500.png" alt="" />
	</div>
	<div class="page">
		<h2>Dear,we can't open that page!</h2>
		<h3><span id="tips" style="color:red;"></span>秒后回到上一页</h3>
		<h3><span id="tips" style="color:red;"></span>如果没有返回，请手动返回并刷新！</h3>
	</div>
</div>
</body>
</html>

