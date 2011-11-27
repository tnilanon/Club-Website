<jsp:include page="templateTop.jsp" />

<div class="_form" id="registerForm">
	<p style="font-size:x-large">CMU Ballroom Dance Club</p>
	<form method="POST" action="register.do">
		<table class="centered">
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" value="${param.firstName}" id="firstname" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" value="${param.lastName}" id="lastname" /></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><input type="password" name="confirmPassword" id="confirmpassword" /></td>
			</tr>
		</table>
		<br />
		<input type="hidden" name="emailAddress" value="${param.emailAddress}" id="email" />
		<input type="hidden" name="password" value="${param.password}" id="password" />
		<input type="submit" name="button" style="width:13em" value="Complete Registration" id="completeregistration" />
	</form>
	
<jsp:include page="errorMessage.jsp" />
	
</div>

<jsp:include page="templateBottom.jsp" />
