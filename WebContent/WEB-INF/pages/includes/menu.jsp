
<c:if test="${not empty  links}">
	<div class="w3-bar menu">
		<c:forEach var="link" items="${links}">
			<a class="w3-bar-item w3-button" href="${link.uri}"> ${link.name}</a>
		</c:forEach>
	</div>
</c:if>
