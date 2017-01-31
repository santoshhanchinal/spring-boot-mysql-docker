<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<link rel="stylesheet" href="../css/style.css">



<script type="text/javascript">
$(function(){
	$("#loginout").click(function(){
		$.cookie('ltapToken', null); 
		location.href = "/RAF/user/login.action";
	})
	
	$("#resetpwd").click(function(){
		location.href = "/RAF/user/resetpwd.action";
	})
})
</script>
</head>

    <body>
<div class="container" align="center">
	<section id="content">
		<form action="">
			<h1>User Page</h1>
			<div>
				<b>You are now logged in!</b>
			</div>
			<div align="left">
				<a class="button--style-red" id = "resetpwd">reset password</a>
			</div>
			<div align="center">
				<a class="button--style-red" id = "loginout">Log out</a>
			</div>
		</form><!-- form -->
		
	</section><!-- content -->
</div><!-- container -->
</body>

</html>