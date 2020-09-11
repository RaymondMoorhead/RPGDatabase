<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>RPG Database ${curUser.username}'s Characters</title>
</head>

<script src="DiceRoll.js"></script>
<script language="JavaScript">
	function rollDice(index, internal, external) {
		var input = internal + ' + ' + external;
		document.getElementById("rollResult" + index.toString()).innerHTML = roll(input);
	}
</script>

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
				<textarea name = "bio" id="editedBio" style="width: 100%">${character.bio}</textarea><br>
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
							<th width = "10%">Name</th>
							<th width = "50%">Description</th>
							<th width = "10%">Self Roll</th>
							<th width = "7%">External Modifiers</th>
							<th width = "14%">External Targets</th>
							<th width = "3%">Roll</th>
							<th width = "2%">Roll Result</th>
							<th width = "2%"></th>
							<th width = "2%"></th>
						</tr>
					</thead>
					<tbody>
						<%
							// varStatus="loop" ${loop.index} stopped working for some
							// unknown reason, so now I need to manually create my own
							// index tracker
							int index = 0;
							boolean firstOutRollIndex = true;
							boolean firstOutTargetIndex = true;
							request.setAttribute("index", index);
							request.setAttribute("firstOutRollIndex", firstOutRollIndex);
							request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
						%>
						<c:forEach items="${character.features}" var="feat">
								<%
									firstOutRollIndex = true;
									request.setAttribute("firstOutRollIndex", firstOutRollIndex);
								%>
								<c:choose>
									<c:when test="${editFeat == index}">
										<input type = "hidden" name = "id" value="${character.id}">
										<input type = "hidden" name = "index" value="${index}">
										<c:choose>
											<c:when test="${fn:length(feat.outRoll) == 0}">
												<tr>
													<td><input type = "text" name = "name" value="${feat.name}"></td>
													<td><textarea name = "desc" class="fill-cell" id="editedDesc" style="overflow:hidden">${feat.description}</textarea></td>
													<td><textarea name = "selfRoll" class="fill-cell" id="editedSelfRoll" style="overflow:hidden">${feat.selfRoll}</textarea></td>
													<td>No External Modifiers</td>
													<td>No External Targets</td>
													<td><button type="button" onclick="rollDice(${index}, '${feat.selfRoll}', '${feat.externalMods}')">Roll</button></td>
													<td><p id = "rollResult${index}">0</p></td>
													<td><input type = "submit" value = "Save" name = "b1"></td>
													<td><a type="button" class="btn btn-warning" href="/delete-user-character-feat?id=${character.id}&index=${index}">Delete</a></td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${feat.outRoll}" var="outRoll">
												<%
													firstOutTargetIndex = true;
													request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
												%>
												<c:forEach items="${outRoll.second}" var="targetFeat">
													<c:choose>
														<c:when test="${firstOutRollIndex}">
															<tr>
																<td rowspan="${featRows[index]}"><input type = "text" name = "name" value="${feat.name}"></td>
																<td rowspan="${featRows[index]}"><textarea name = "desc" class="fill-cell" id="editedDesc" style="overflow:hidden">${feat.description}</textarea></td>
																<td rowspan="${featRows[index]}"><textarea name = "selfRoll" class="fill-cell" id="editedSelfRoll" style="overflow:hidden">${feat.selfRoll}</textarea></td>
																<td rowspan="${fn:length(outRoll.second)}">${outRoll.first}</td>
																<td>${targetFeat}</td>
																<td rowspan="${featRows[index]}"><button type="button" onclick="rollDice(${index}, '${feat.selfRoll}', '${feat.externalMods}')">Roll</button></td>
																<td rowspan="${featRows[index]}"><p id = "rollResult${index}">0</p></td>
																<td rowspan="${featRows[index]}"><input type = "submit" value = "Save" name = "b1"></td>
																<td rowspan="${featRows[index]}"><a type="button" class="btn btn-warning" href="/delete-user-character-feat?id=${character.id}&index=${index}">Delete</a></td>
																<%
																	firstOutRollIndex = false;
																	firstOutTargetIndex = false;
																	request.setAttribute("firstOutRollIndex", firstOutRollIndex);
																	request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
																%>
															</tr>
														</c:when>
														<c:when test="${firstOutTargetIndex}">
															<tr>
																<td rowspan="${fn:length(outRoll.second)}">${outRoll.first}</td>
																<td>${targetFeat}</td>
																<%
																	firstOutTargetIndex = false;
																	request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
																%>
															</tr>
														</c:when>
														<c:otherwise>
															<tr><td>${targetFeat}</td></tr>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${fn:length(feat.outRoll) == 0}">
												<tr>
													<td>${feat.name}</td>
													<td>${feat.description}</td>
													<td>${feat.selfRoll} + ${feat.externalMods}</td>
													<td>No External Modifiers</td>
													<td>No External Targets</td>
													<td><button type="button" onclick="rollDice(${index}, '${feat.selfRoll}', '${feat.externalMods}')">Roll</button></td>
													<td><p id = "rollResult${index}">0</p></td>
													<td rowspan="${featRows[index]}"><a type="button" class="btn btn-success" href="/edit-user-character-feat?id=${character.id}&index=${index}">Edit</a></td>
													<td><a type="button" class="btn btn-warning" href="/delete-user-character-feat?id=${character.id}&index=${index}">Delete</a></td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${feat.outRoll}" var="outRoll">
													<%
														firstOutTargetIndex = true;
														request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
													%>
													<c:forEach items="${outRoll.second}" var="targetFeat">
														<c:choose>
															<c:when test="${firstOutRollIndex}">
																<tr>
																	<td rowspan="${featRows[index]}">${feat.name}</td>
																	<td rowspan="${featRows[index]}">${feat.description}</td>
																	<td rowspan="${featRows[index]}">${feat.selfRoll} + ${feat.externalMods}</td>
																	<td rowspan="${fn:length(outRoll.second)}">${outRoll.first}</td>
																	<td>${targetFeat}</td>
																	<td rowspan="${featRows[index]}"><button type="button" onclick="rollDice(${index}, '${feat.selfRoll}', '${feat.externalMods}')">Roll</button></td>
																	<td rowspan="${featRows[index]}"><p id = "rollResult${index}">0</p></td>
																	<td rowspan="${featRows[index]}"><a type="button" class="btn btn-success" href="/edit-user-character-feat?id=${character.id}&index=${index}">Edit</a></td>
																	<td rowspan="${featRows[index]}"><a type="button" class="btn btn-warning" href="/delete-user-character-feat?id=${character.id}&index=${index}">Delete</a></td>
																	<%
																		firstOutRollIndex = false;
																		firstOutTargetIndex = false;
																		request.setAttribute("firstOutRollIndex", firstOutRollIndex);
																		request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
																	%>
																</tr>
															</c:when>
															<c:when test="${firstOutTargetIndex}">
																<tr>
																	<td rowspan="${fn:length(outRoll.second)}">${outRoll.first}</td>
																	<td>${targetFeat}</td>
																	<%
																		firstOutTargetIndex = false;
																		request.setAttribute("firstOutTargetIndex", firstOutTargetIndex);
																	%>
																</tr>
															</c:when>
															<c:otherwise>
																<tr><td>${targetFeat}</td></tr>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							<%
								++index;
								request.setAttribute("index", index);
							%>
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
	
	<script src="JsUtility.js"></script>
	<script>
		let toEdit = document.getElementById("editedBio");
		if(toEdit !== null)
			textAreaAdjust(toEdit);
		
		toEdit = document.getElementById("editedDesc");
		if(toEdit !== null)
			textAreaAdjust(toEdit);
		
		toEdit = document.getElementById("editedSelfRoll");
		if(toEdit !== null)
			textAreaAdjust(toEdit);
	</script>
	
</body>

</html>