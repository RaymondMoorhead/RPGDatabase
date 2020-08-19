<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database ${name}'s Characters</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	
	<a type="button" class="btn btn-success" href="/list-user-characters">Return To List</a>
	<table border="1">
		<tr>
			<td>Name</td>
			<td>${curUser.username}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${curUser.email}</td>
		</tr>
	</table>
	
	<table border="1">
		<tr>
			<td><a type="button" style="color: red" href="/account-delete">Delete Account</a></td>
		</tr>
	</table>
	
	<%@ include file = "footer.jsp"%>
</body>

</html>