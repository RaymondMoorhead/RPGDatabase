<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database ${curUser.username}'s Characters</title>
</head>

<body style="width: 90%">
	<%@ include file = "header.jsp"%>
	
	<div><a class="button" href="/account-details">Account Details</a></div>
	<div class="container" style="width: 100%">
		<c:choose>
			<c:when test="${empty characters}">
				<h3>No Characters</h3>
			</c:when>
			<c:otherwise>
				<table border="1">
					<thead>
						<tr>
							<th style="width:10%">Name</th>
							<th style="width:70%">Bio</th>
							<th style="width:10%">Features</th>
							<th style="width:5%"></th>
							<th style="width:5%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${characters}" var="character">
							<tr>
								<td><h3 style="text-align: center">${character.name}</h3></td>
								<td>${character.bio}</td>
								<td>
								<c:forEach items="${character.features}" var="feat">
									${feat.name}<br>
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