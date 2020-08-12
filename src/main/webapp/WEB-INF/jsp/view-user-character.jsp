<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<html>

<head>
<title>RPG Database ${curUser.username}'s Characters</title>
</head>

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
			<a type="button" class="btn btn-primary" href="/edit-user-character-name?id=${character.id}">${character.name}</a>
		</h1>
	</c:otherwise>
</c:choose>
<br>

<c:choose>
	<c:when test="${editBio != null}">
		<form method = "post" action="/edit-user-character-bio">
			<input type = "submit" value = "Save" name = "b1">
			<input type = "hidden" name = "id" value="${character.id}">
			<input type = "text" name = "bio" value="${character.bio}">
		</form>
	</c:when>
	<c:when test="${fn:length(character.bio) == 0}">
		<a type="button" class="btn btn-primary" href="/edit-user-character-bio?id=${character.id}">Add a Bio!</a>
	</c:when>
	<c:otherwise>
		<h3>
			<a type="button" class="btn btn-primary" href="/edit-user-character-bio?id=${character.id}">${character.bio}</a>
		</h3>
	</c:otherwise>
</c:choose>
<br>

<body>
	<div class="container">
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
						<c:forEach items="${character.features}" var="feat">
							<tr>
								<c:choose>
									<c:when test="${editFeat != null && editFeat == feat.key}">
										<input type = "hidden" name = "id" value="${character.id}">
										<input type = "hidden" name = "oldName" value="${feat.key}">
										<td><input type = "text" name = "name" value="${feat.key}"></td>
										<td><input type = "text" name = "desc" value="${feat.value}"></td>
										<td><input type = "submit" value = "Save" name = "b1"></td>
									</c:when>
									<c:otherwise>
										<td>${feat.key}</td>
										<td>${feat.value}</td>
										<td><a type="button" class="btn btn-success"
											href="/edit-user-character-feat?id=${character.id}&featName=${feat.key}">Edit</a></td>
									</c:otherwise>
								</c:choose>
								<td><a type="button" class="btn btn-warning"
									href="/delete-user-character-feat?id=${character.id}&featName=${feat.key}">Delete</a></td>
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

</body>

</html>