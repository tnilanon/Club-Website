
<div class="_form" id="addVideoForm">
	<form method="POST" action="addVideo.do">
		<br />
		<table class="centered">
			<tr>
				<td>Link:</td>
				<td><input type="text" name="link" value="${param.link}" id="link" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description" value="${param.description}" id="description" /></td>
			</tr>
		</table>
		<br />
		<input type="submit" name="button" style="width:11em" value="Add Video" id="addVideo" />
	</form>

<jsp:include page="errorMessage.jsp" />

</div>
