<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database Create Character</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	
	<form method = "post">
		<table border="1">
 			 <tr>
    			<td>Character Name:</td>
    			<td>
      				<input type = "text" name = "characterName" size = "50">
   				</td>
 			 </tr>
 			 <tr>
    			<td>Character Bio:</td>
    			<td>
      				<textarea name = "characterBio" rows="4" cols="50"></textarea>
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
	
	<%@ include file = "footer.jsp"%>
</body>

</html>