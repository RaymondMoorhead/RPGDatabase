<html>

<head>
<title>RPG Database Registration</title>
</head>

<body>
	<font color="red">${errorMessage}</font>
	
	<form method = "post">
		<table border="1">
  			<tr>
    			<td>Username:</td>
   				<td>
      				<input type = "text" name = "name" size = "20">
    			</td>
  			</tr>
 			 <tr>
    			<td>Password:</td>
    			<td>
      				<input type = "password" name = "password" size = "20">
   				</td>
 			 </tr>
 			 <tr>
 				<td>Email:</td>
    			<td>
      				<input type = "email" name = "email" size = "40">
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
	
	<a href="login">Already have an account? Click Here!</a>
</body>

</html>