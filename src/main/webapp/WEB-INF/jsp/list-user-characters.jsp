<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database ${curUser.username}'s Characters</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	
	<div><a class="button" href="/account-details">Account Details</a></div>
	<div class="container">
		<c:choose>
			<c:when test="${empty characters}">
				<h3>No Characters</h3>
			</c:when>
			<c:otherwise>
				<table border="1">
					<thead>
						<tr>
							<th>Name</th>
							<th>Bio</th>
							<th>Features</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${characters}" var="character">
							<tr>
								<td>${character.name}</td>
								<td>${character.bio}</td>
								<td>
								<c:forEach items="${character.features}" var="feat">
									${feat.key}<br>
								</c:forEach>
								</td>
								<td><a type="button" class="btn btn-success"
									href="/view-user-character?id=${character.id}">View</a></td>
								<td><a type="button" class="btn btn-warning"
									href="/delete-user-character?id=${character.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		<div>
			<a class="button" href="/add-user-character">Make A New Character</a>
		</div>
	</div>
	
	<%@ include file = "footer.jsp"%>

</body>

</html>