
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="videoList">
	<c:if test="${! empty(videoList)}">
		<c:forEach var="video" items="${videoList}">
			<div class="centered">
				<iframe class="youtube-player" type="text/html" width="640" height="385" src="http://www.youtube.com/embed/${video.link}" frameborder="0">
				</iframe>
			</div>
		</c:forEach>
	</c:if>
</div>
