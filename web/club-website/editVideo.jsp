<jsp:include page="templateTop.jsp" />

<div id="Content">


<jsp:include page="videoNavigationBar.jsp" />
<br />


<div class="_form" id="editVideoForm">

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean" %>
<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.VideoBean" %>
<%  UserBean user = (UserBean) session.getAttribute("user");  %>

	<form method="POST" action="editVideo.do">
		<br />
		<table class="centered">
			<tr><td colspan="2"><center><strong>Edit Video Information</strong></center></td></tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description" value="${description}" id="description" /></td>
			</tr>
			<tr>
				<td>Who can view:</td>
				<td> 
				<% int accessLevel = (Integer) request.getAttribute("radio");
				int group = user.getUserGroup();
				String[] name = {"Basic Members","Advanced Members","CompTeam Members","Officers","Admins"};
				int i=1;
				for( ; i<group; i++){
				%>	<input type="radio" name="radio" value="<%=i%>" <% if(accessLevel==i) {out.print("checked=\"checked\"");} %> /> <%=name[i-1]%><br />
				<% } %>
					<input type="radio" name="radio" value="<%=i%>" <% if(accessLevel==i) {out.print("checked=\"checked\"");} %> /> <%=name[i-1]%><br />
				<% i++;
				for( ; i<=5; i++){
				%>	<input type="radio" name="radio" value="<%=i%>" disabled="disabled" /> <%=name[i-1]%><br />
				<% } %>
				</td>
			</tr>
		</table>
		<br />
		<input type="hidden" name="videoId" value="${param.videoId}" id="videoId" />
		<input type="submit" name="button" style="width:11em" value="Update Info" id="updateVideo" />
	</form>

<jsp:include page="errorMessage.jsp" />

</div>



</div>


<jsp:include page="templateBottom.jsp" />

