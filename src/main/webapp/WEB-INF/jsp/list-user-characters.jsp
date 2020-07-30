<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>RPG Database ${name}'s Characters</title>
</head>

<body>
	<div class="container">
		<table border="1">
			<thead>
				<tr>
					<th>name</th>
					<th>features</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${characters}" var="character">
					<tr>
						<td>${character.name}</td>
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
		<div>
			<a class="button" href="/add-user-character">Make A New Character</a>
		</div>
	</div>
	<!-- 
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	-->

</body>

</html>