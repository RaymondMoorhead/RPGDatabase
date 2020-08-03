<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>RPG Database ${name}'s Characters</title>
</head>

<body>
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

</body>

</html>