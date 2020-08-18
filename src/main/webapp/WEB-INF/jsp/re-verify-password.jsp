<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database Re-Verification</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	
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
	
	<%@ include file = "footer.jsp"%>
</body>

</html>