<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="formats.css">
<title>Header</title>
</head>

<body>
	<div>
		<h3 class="header">RPG Database</h3><br><br>
		<a class="button" href = "/logout">Logout</a><br>
		
		<font color="orange">${warningMessage}</font>
		<font color="red">${errorMessage}</font>
		<font color="green">${successMessage}</font>
	</div>
</body>
</html>