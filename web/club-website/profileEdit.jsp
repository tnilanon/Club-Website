<jsp:include page="templateTop.jsp" />

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean"%>
<%  UserBean user = (UserBean) session.getAttribute("user");  %>
<%--	if (user == null){

	}  --%>
	
	
<div class="_form" id="profileEditForm">
	<p style="font-size:x-large">Edit Profile</p>
	<form method="POST" action="profileEdit.do">
		<table class="centered">
		<tr>
			<td>User Name :</td>
			<td><input type="text" name="userName" value="<%=user.getUserName() %>" id="username" /></td>
		</tr>
		<tr>
			<td>First Name :</td>
			<td><input type="text" name="firstName" value="<%=user.getFirstName() %>" id="firstname" /></td>
		</tr>
		<tr>
			<td>Last Name :</td>
			<td><input type="text" name="lastName" value="<%=user.getLastName() %>" id="lastname" /></td>
		</tr>
		<tr>
			<td>Email Address :</td>
			<td><input type="text" name="emailAddress" value="<%=user.getEmailAddress() %>" id="emailAddress" disabled/></td>
		</tr>
		<tr>
			<td>Sex:</td>
			<td>
			<% if( user.getSex().equals("male") ){ %>
				<input type="radio" name="sex" value="Male" id="sex" checked/>Male
				<input type="radio" name="sex" value="Female" id="sex"/>Female
			<% } else { %>
				<input type="radio" name="sex" value="Male" id="sex" />Male
				<input type="radio" name="sex" value="Female" id="sex" checked/>Female
			<% }  %>
			</td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><input type="submit" name="button" value="Change Password" id="changePassword" /></td>
		</tr>
	</table>
	<input type="submit" name="button" style="width:13em" value="Submit Changes" id="submitProfile" />

</form>

<jsp:include page="errorMessage.jsp" />

</div>

<jsp:include page="templateBottom.jsp" />
