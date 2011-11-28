
<div class="_form" id="addVideoForm">

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean" %>
<%  UserBean user = (UserBean) session.getAttribute("user");  %>

	<form method="POST" action="addVideo.do">
		<br />
		<table class="centered">
			<tr><td colspan="2"><center><strong>Upload Video</strong></center></td></tr>
			<tr>
				<td>Link:</td>
				<td><input type="text" name="link" value="${param.link}" id="link" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description" value="${param.description}" id="description" /></td>
			</tr>
			<tr>
				<td>Who can view: </td>
				<td> 
				<% int group = user.getUserGroup();
				String[] name = {"Basic Members","Advanced Members","CompTeam Members","Officers","Admins"};
				int i=1;
				for( ; i<group; i++){
				%>	<input type="radio" name="radio" value="<%=i%>" /> <%=name[i-1]%><br />
				<% } %>
					<input type="radio" name="radio" value="<%=i%>" checked="checked" /> <%=name[i-1]%><br />
				<% i++;
				for( ; i<=5; i++){
				%>	<input type="radio" name="radio" value="<%=i%>" disabled="disabled" /> <%=name[i-1]%><br />
				<% } %>
				</td>
			</tr>
		</table>
		<br />
		<input type="submit" name="button" style="width:11em" value="Add Video" id="addVideo" />
	</form>

<jsp:include page="errorMessage.jsp" />

</div>
