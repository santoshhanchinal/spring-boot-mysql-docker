<%@ page language="java" contentType="text/html; charset=utf-8"
      pageEncoding="utf-8"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<title>Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<link rel="stylesheet" href="../css/style.css">
  
  
<script type="text/javascript">
  	
  	function postuserdata(usernameValue,fullnameValue,emailValue,mmValue,validateCode){
  		var postData = {'username':usernameValue,'fullname':fullnameValue,'password':mmValue,'email':emailValue,'validateCode':validateCode};
  	  	jQuery.post('/RAF/user/addUserInfo.action',postData, function(resp) {
  	  	    if(resp[0].flag=='success'){
  	  	    	alert("Registrat success");
	  			location.href = "/RAF/user/login.action";
	  		}else{
	  			alert("Registrat fail,check input");
	  		}
  	  	},'json');
  	}
  	
  	$(function(){
  		$("#register").click(function(){
  			var usernameValue = $("#username").val();   
  		    var fullnameValue = $("#fullname").val();  
  		    var emailValue = $("#email").val();  
  		    var mmValue = $("#password").val();
  		    var validateCode = $("#validateCode").val();
  		    postuserdata(usernameValue,fullnameValue,emailValue,mmValue,validateCode);
  		});
  		
  		
  		 var url = "/RAF/img/validateImg.action";
  		 $(".loginValidate").keyup(function(){
  		         $.post(url,{ 
  		             ajaxs:"Code",
  		             inputCode:$(this).val(),
  		             time:(new Date()).getTime()
  		         },function(data,status){
  		             if(data=="false"){
  		                 $('#register').attr('disabled',"true");
  		                 $("#results").html("Please enter the correct verification code");    
  		             }else{
  		                 $("#results").html("verification code is correct");
  		                 $('#register').removeAttr("disabled");
  		             }
  		         });
  		});
  	})
  	
  	/* $.validator.setDefaults({
		submitHandler: function() {
			alert("You have been registered!");
		}
	}); */

	$(function() {
	
		$("#signupForm").validate({
			rules: {
				username: {
					required: true,
					minlength: 5
				},
				fullname: {
					required: true,
					minlength: 5
				},
				password: {
					required: true,
					minlength: 8
				},
				confirm_password: {
					required: true,
					minlength: 8,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				
			},
			messages: {
				username: {
					required: "Please enter a username",
					minlength: "Your username must consist of at least 5 characters"
				},
				fullname: {
					required: "Please enter your full name",
					minlength: "Your username must consist of at least 3 characters"
				},
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 8 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 8 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "Please enter a valid email address",
			}
		});
	});
	
	$().ready(function() {
        $.validator.addMethod("password",function(value,element){
            return this.optional(element) || /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/i.test(value);
        },"Password must contain uppercase letters, lowercase letters and at least one number.");
    });
	
	
	
	 function changeImg(){
		      document.getElementById("validateCodeImg").src=document.getElementById("validateCodeImg").src+"?time="+(new Date());
     }
	 
	 
  
  </script>
</head>
  <body>
	<div class="container">
	<section id="content">
		<form action="" id="signupForm" method="post" onsubmit="return false">
			<h1>Registration</h1>
			<p>
				<label for="fullname"><b>Full Name</b></label>
				<input id="fullname" name="fullname" type="text" autocomplete="off">
			</p>
			<p>
				<label for="username"><b>Username</b></label>
				<input id="username" name="username" type="text" autocomplete="off">
			</p>
			<p>
				<label for="email"><b>Email</b></label>
				<input id="email" name="email" type="text" autocomplete="off">
			</p>
			<p>
				<label for="password"><b>Password</b></label>
				<input id="password" name="password" type="password" autocomplete="off">
			</p>
			<p>
				<label for="confirm_password"><b>Confirm password</b></label>
				<input id="confirm_password" name="confirm_password" type="password" autocomplete="off">
			</p>
			<p>
			<label for="Verification Code"><b>Verification Code</b></label>
			  <ul>
                         <li><input type="text" id="validateCode"  name="validateCode" class="loginValidate" autocomplete="off" />
                         <img alt="change for a clear one"  class="pic" src="/RAF/img/createImg.action" id="validateCodeImg" onclick="changeImg()">
                         <span id="results"></span></li>
              </ul>
			</p>
			<div>
				<input type="submit" id = "register" value="Register!" disabled />
				<a href="/RAF/user/login.action">Login</a>
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->

</body>
</html>

 
 