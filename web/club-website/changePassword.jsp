<jsp:include page="templateTop.jsp" />

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean"%>
<%  UserBean user = (UserBean) session.getAttribute("user");  %>
<%--	if (user == null){

	}  --%>
	
	
<div class="_form" id="changePasswordForm">
	<p style="font-size:x-large">Change Password</p>
	<form method="POST" action="changePassword.do">
		<table class="centered">
		<tr>
			<td>Password :</td>
			<td><input type="password" name="password" id="password" /></td>
		</tr>
		<tr>
			<td>Confirm Password :</td>
			<td><input type="password" name="confirmPassword" id="confirmpassword" /></td>
		</tr>
	</table>
	<input type="submit" name="button" style="width:13em" value="Change Password" id="submitNewPassword" />

</form>

</div>

<jsp:include page="templateBottom.jsp" />
