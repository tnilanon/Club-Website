<%@ page import="java.util.*" %>
<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean" %>
<%@ page import="edu.cmu.cs15437.clubwebsite.model.UserDAO" %>

<div id="newUserList">

<% 
	List< UserBean > list = (List<UserBean>) request.getAttribute("newUserList"); 
	UserDAO userdao =  new UserDAO();
	UserBean admin = (UserBean) session.getAttribute("user");
	
	if(list!=null){
	for( UserBean user : list ){ %>
		<div class="_boxVideo">
		<form method="POST" action="admit.do">
		<br />
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
				<td>Membership Expiration :</td>
				<td><%=new Date(user.getMembershipExpirationDateValue())%></td>
			</tr>
			<tr>
				<td>Membership Type :</td>
				<td>
					<input type="radio" name="memberType" value="1" /> Basic<br />
					<input type="radio" name="memberType" value="2" /> Advanced<br />
					<input type="radio" name="memberType" value="3" /> CompTeam<br />
					<input type="radio" name="memberType" value="4" /> Officer<br />
					<input type="radio" name="memberType" value="5" /> Admin<br />
					<input type="hidden" name="emailAddress" value="<%=user.getEmailAddress()%>" />
				</td>
			</tr>
			<tr>
				<td colspan=2 align="center">
					<input type="submit" value="Approve" name="approveMember" id="approveMember" />
				</td>
			</tr>
		</table>
		</form>
		</div>
		<br />
	<% }}%>
</div>
