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
 <script type="text/javascript" src="../js/sha256.js"></script>

<link rel="stylesheet" href="../css/style.css">
  
  
<script type="text/javascript">
  	
  	function postuserdata(oldPassword,newPassword){
  		var postData = {'oldPassword':oldPassword,'newPassword':newPassword};
  	  	jQuery.post('/RAF/user/changepwd.action',postData, function(resp) {
  	  	    if(resp[0].flag=='success'){
  	  	    	alert("rerest success");
	  			location.href = "/RAF/user/index.action";
	  		}else{
	  			alert("Registrat fail,check input");
	  		}
  	  	},'json');
  	}
  	
  	$(function(){
  		$("#confirm").click(function(){
  			var oldPassword = $("#oldPassword").val();   
  		    var newPassword = $("#newPassword").val();  
  		    
  		        oldPassword = sha256_digest(oldPassword);
  		        newPassword = sha256_digest(newPassword);
  		        
  		    postuserdata(oldPassword,newPassword);
  		});
  		
  		
  		 var url = "/RAF/img/validateImg.action";
  		 $(".loginValidate").keyup(function(){
  		         $.post(url,{ 
  		             ajaxs:"Code",
  		             inputCode:$(this).val(),
  		             time:(new Date()).getTime()
  		         },function(data,status){
  		             if(data=="false"){
  		                 $('#confirm').attr('disabled',"true");
  		                 $("#results").html("Please enter the correct verification code");    
  		             }else{
  		                 $("#results").html("verification code is correct");
  		                 $('#confirm').removeAttr("disabled");
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
				
				oldPassword: {
					required: true
					
				},
				
				newPassword: {
					required: true,
					minlength: 8
				},
				
				confirm_password: {
					required: true,
					minlength: 8,
					equalTo: "#newPassword"
				}
				
			},
			messages: {
				
				oldPassword: {
					required: "Please enter your full name",
					minlength: "Your username must consist of at least 3 characters"
				},
				newPassword: {
					required: "Please provide a password",
					minlength: "Your password must be at least 8 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 8 characters long",
					equalTo: "Please enter the same password as above"
				}
			}
		});
	});
	
	$().ready(function() {
        $.validator.addMethod("password",function(value,element){
            return this.optional(element) || /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/i.test(value);
        },"newPassword must contain uppercase letters, lowercase letters and at least one number.");
    });
	
	
	
	 function changeImg(){
		      document.getElementById("validateCodeImg").src=document.getElementById("validateCodeImg").src+"?time="+(new Date());
     }
	 
  
  </script>
</head>

</body>


<body>
            <div class="container">
                <section id="content">
                    <form action="" id="signupForm" method="post" onsubmit="return false">
                        <h1>Change password</h1>
                        
                           <p>
                            <label for="oldPassword"><b>Old password</b></label>
                            <input id="oldPassword" name="oldPassword" type="password">
                            </p>
                            
                           <p>
                        <label for="newPassword"><b>New password</b></label>
                        <input id="newPassword" name="newPassword" type="password">
                            </p>
                            
                            <p>
                        <label for="confirm_password"><b>Confirm password</b></label>
                        <input id="confirm_password" name="confirm_password" type="password">
                            </p>
                            
                            <p>
								<label for="Verification Code">
								<b>Verification Code</b></label>
								  <ul>
					                         <li><input type="text" id="validateCode"  name="validateCode" class="loginValidate" autocomplete="off" />
					                         <img alt="change for a clear one"  class="pic" src="/RAF/img/createImg.action" id="validateCodeImg" onclick="changeImg()">
					                         <span id="results"></span></li>
					              </ul>
							</p>
							
                            <div>
                                <input type="submit" value="Confirm" id= "confirm"  disabled />
                            </div>   
                    </form><!-- form -->
                    
                </section><!-- content -->
            </div><!-- container -->
        </body>
</html>

 
 