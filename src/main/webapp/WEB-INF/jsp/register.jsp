<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database Registration</title>
</head>

<body>
	
	<form method = "post">
		<table border="1">
  			<tr>
    			<td>Username:</td>
   				<td>
      				<input type = "text" name = "name" maxlength="${maxUsernameLength}" size = "${highestMaxLength}">
    			</td>
  			</tr>
 			 <tr>
    			<td>Password:</td>
    			<td>
      				<input type = "password" name = "password" maxlength="${maxPasswordLength}" size = "${highestMaxLength}">
   				</td>
 			 </tr>
 			 <tr>
 				<td>Email:</td>
    			<td>
      				<input type = "email" name = "email" maxlength="${maxEmailLength}" size = "${highestMaxLength}">
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
	
	<a href="login">Already have an account? Click Here!</a>
	<%@ include file = "footer.jsp"%>
</body>

</html>