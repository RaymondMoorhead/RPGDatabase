<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database ${curUser.username}'s Characters</title>
</head>

<body style="width: 90%">
	<%@ include file = "header.jsp"%>

	<a type="button" class="btn btn-success" href="/list-user-characters">Return To List</a>

	<c:choose>
		<c:when test="${editName != null}">
			<form method = "post" action="/edit-user-character-name">
				<input type = "submit" value = "Save" name = "b1">
				<input type = "hidden" name = "id" value="${character.id}">
				<input type = "text" name = "name" value="${character.name}">
			</form>
		</c:when>
		<c:otherwise>
			<h1>
				<a type="button" style="color: black" href="/edit-user-character-name?id=${character.id}">${character.name}</a>
			</h1>
		</c:otherwise>
	</c:choose>
	<br>

	<c:choose>
		<c:when test="${editBio != null}">
			<form method = "post" action="/edit-user-character-bio">
				<input type = "hidden" name = "id" value="${character.id}">
				<textarea name = "bio" rows="4" cols="50">${character.bio}</textarea><br>
				<input type = "submit" value = "Save" name = "b1">
			</form>
		</c:when>
		<c:when test="${fn:length(character.bio) == 0}">
			<a type="button" class="btn btn-primary" href="/edit-user-character-bio?id=${character.id}">Add a Bio!</a>
		</c:when>
		<c:otherwise>
			<h3>
				<a type="button" style="text-decoration: none;color: black" href="/edit-user-character-bio?id=${character.id}">${character.bio}</a>
			</h3>
		</c:otherwise>
	</c:choose>
	<br>

	<div class="container" style="width: 100%">
			<c:choose>
			<c:when test="${empty character.features}">
				<h4>No Features</h4>
			</c:when>
			<c:otherwise>
				<form method = "post" action="/edit-user-character-feat">
				<table border="1">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${character.features}" var="feat" varStatus="loop">
							<tr>
								<c:choose>
									<c:when test="${editFeat != null && editFeat == loop.index}">
										<input type = "hidden" name = "id" value="${character.id}">
										<input type = "hidden" name = "index" value="${loop.index}">
										<td><input type = "text" name = "name" value="${feat.name}"></td>
										<td><textarea name = "desc" class="fill-cell" rows="4">${feat.description}</textarea></td>
										<td><input type = "submit" value = "Save" name = "b1"></td>
									</c:when>
									<c:otherwise>
										<td>${feat.name}</td>
										<td>${feat.description}</td>
										<td><a type="button" class="btn btn-success"
											href="/edit-user-character-feat?id=${character.id}&index=${loop.index}">Edit</a></td>
									</c:otherwise>
								</c:choose>
								<td><a type="button" class="btn btn-warning"
									href="/delete-user-character-feat?id=${character.id}&index=${loop.index}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<a class="button" href="/add-user-character-feat?id=${character.id}">Add New Feature</a>
	</div>

	<%@ include file = "footer.jsp"%>
</body>

</html>