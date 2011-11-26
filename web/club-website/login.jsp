
<jsp:include page="templateTop.jsp" />

<div class="_form" id="loginForm">
	<p style="font-size:x-large">Bookmarks!</p>
	<form method="POST" action="login.do">
		<table class="centered">
			<tr>
				<td>Email Address:</td>
				<td><input type="text" name="emailAddress" value="${param.emailAddress}" id="email" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
		</table>
		<br />
		<input type="submit" name="button" style="width:7em" value="Login" id="login" />
		<input type="submit" name="button" style="width:7em" value="Register" id="register" />
	</form>
	
<jsp:include page="errorMessage.jsp" />
	
</div>

<jsp:include page="templateBottom.jsp" />
