<html>

<head>
<title>RPG Database Re-Verification</title>
</head>

<body>
	<font color="red">${errorMessage}</font>
	
	<h4>Please Re-Verify Your Authenticity</h4>
	
	<form method = "post">
		<table border="1">
 			 <tr>
    			<td>Password:</td>
    			<td>
      				<input type = "password" name = "password" maxlength="${maxPasswordLength}" size = "${maxPasswordLength}">
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
</body>

</html>