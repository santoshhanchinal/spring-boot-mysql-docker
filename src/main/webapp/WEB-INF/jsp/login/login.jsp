<%@ page language="java" contentType="text/html; charset=utf-8"
      pageEncoding="utf-8"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Insert title here</title>
  <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="../js/jquery.validate.js"></script>
  <script type="text/javascript" src="../js/jquery.form.js"></script>
  <script type="text/javascript" src="../js/jquery.cookie.js"></script>
  <script type="text/javascript" src="../js/sha256.js"></script>
  <link rel="stylesheet" href="../css/style.css">
  
  <script type="text/javascript">
  
  function changeImg(){
      document.getElementById("validateCodeImg").src=document.getElementById("validateCodeImg").src+"?time="+(new Date());
  }
  
  $(function() {
	    
		$("#signupForm").validate({
			rules: {
				username: {
					required: true,
				},
				password: {
					required: true,					
				},
				
			},
			messages: {
				username: {
					required: "Please enter a username",
						  },
				password: {
					required: "Please provide a password",
				},
			}
		});
		
		
		 var url = "/RAF/img/validateImg.action";
		 $(".loginValidate").keyup(function(){
		         $.post(url,{
		             ajaxs:"Code",
		             inputCode:$(this).val(),
		             time:(new Date()).getTime()
		         },function(data,status){
		             if(data=="false"){
		                 $('#loginin').attr('disabled',"true");
		                 $("#results").html("Please enter the correct verification code");    
		             }else{
		                 $("#results").html("verification code is correct");
		                 $('#loginin').removeAttr("disabled");
		             }
		         });
		});
		 
		$("#loginin").click(function(){
			    
			var usernameValue = $("#username").val(); 
  		    var passwordValue = $("#password").val(); 
  		        passwordValue = sha256_digest(passwordValue);
  		    var validateCode = $("#validateCode").val(); 
  		  
  		    postuserdata(usernameValue,passwordValue,validateCode);
	  			
	  	});
		
		function postuserdata(usernameValue,mmValue,validateCode){
			var postData = {'username':usernameValue,'password':mmValue,'validateCode':validateCode};
	  	  	jQuery.post('/RAF/user/verifyUser.action',postData, function(resp) {
	  	  		if(resp[0].flag=='success'){
	  	  		    $.cookie('ltapToken', resp[0].ltapToken); 
	  	  			location.href = "/RAF/user/index.action";
	  	  		}else{
	  	  			alert("login fail,check you username or password")
	  	  		}
	  	  	},'json');
		}
		
		$("#signupForm").validate({
	        submitHandler:function(form){
	            alert("submitted");   
	            form.submit();
	        }    
	    });
  });
  
  function checksubmit(){
	  return false;
  }
    



</script>
  </head>
      <body>
<div class="container">
	<section id="content">
		<form action="" id="signupForm" method="post" onsubmit="return false">
			<h1>Login</h1>
			<p>
				<label for="username"><b>Username</b></label>
				<input id="username" name="username" type="text" autocomplete="off">
			</p>
			<p>
				<label for="password"><b>Password</b></label>
				<input id="password" name="password" type="password" autocomplete="off">
			</p>
			<p>
			<label for="Verification Code"><b>Verification Code</b></label>
			  <ul>
                         <li><input type="text" id = "validateCode" name="validateCode" class="loginValidate" autocomplete="off" />
                         <img alt="change for a clear one"  class="pic" src="/RAF/img/createImg.action" id="validateCodeImg" onclick="changeImg()">
                         <span id="results"></span></li>
              </ul>
			</p>
			<div>
				<input type="submit" name = "loginin" id = "loginin" value="Log in" disabled />
				<a href="forgot_pass.html">Lost your password?</a>
				<a href="/RAF/user/register.action">Register</a>
			</div>
		</form><!-- form -->
		
	</section><!-- content -->
</div><!-- container -->
</body>
 </html>
 
 