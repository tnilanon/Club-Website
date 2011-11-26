
<div class="_form" id="addBookmarkForm">
	<form method="POST" action="addBookmark.do">
		<br />
		<table class="centered">
			<tr>
				<td>URL:</td>
				<td><input type="text" name="url" value="${param.url}" id="bookmark" /></td>
			</tr>
			<tr>
				<td>Comment:</td>
				<td><input type="text" name="comment" value="${param.comment}" id="comment" /></td>
			</tr>
		</table>
		<br />
		<input type="submit" name="button" style="width:11em" value="Add Bookmark" id="addbookmark" />
	</form>

<jsp:include page="errorMessage.jsp" />

</div>
