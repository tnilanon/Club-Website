<jsp:include page="templateTop.jsp" />

<div id="Content">
<h1>My Profile</h1>

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean"%>
<%  UserBean user = (UserBean) session.getAttribute("user");  %>
<%--	if (user == null){

	}  --%>
<table>
	<tr>
		<td>User Name :</td>
		<td><%=user.getUserName()%></td>
	</tr>
	<tr>
		<td>First Name :</td>
		<td><%=user.getFirstName()%></td>
	</tr>
	<tr>
		<td>Last Name :</td>
		<td><%=user.getLastName()%></td>
	</tr>
	<tr>
		<td>Email Address :</td>
		<td><%=user.getEmailAddress()%></td>
	</tr>
	<tr>
		<td>Sex:</td>
		<td><%=user.getSex()%></td>
	</tr>
	<tr>
		<td>Membership Type :</td>
		<%
		String group;
		switch (user.getUserGroup()){
			case 1: group = "Basic Membership";
			case 2: group = "Advanced Membership";
			case 3: group = "Competition Team Member";
			case 4: group = "Officer";
			case 5: group = "Administrator";
			default: group = "Pending for Approval";
		}
		%>
		<td><%=group%></td>
	</tr>
	<tr>
		<td>Membership Expiration :</td>
		<td><%=user.getMembershipExpirationDateValue() %></td>
	</tr>
</table>

<br />
<br />

<form method="POST" action="profileEdit.do">
	<input type="submit" name="button" style="width:13em" value="Edit Profile" id="editProfile" />
</form>

</div>

<jsp:include page="templateBottom.jsp" />
