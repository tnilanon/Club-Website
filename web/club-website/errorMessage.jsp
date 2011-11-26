
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="errorMessage" id="errors">
	<c:if test="${! empty(errors)}">
		<c:forEach var="error_line" items="${errors}">
			${error_line} <br />
		</c:forEach>
	</c:if>
</p>
