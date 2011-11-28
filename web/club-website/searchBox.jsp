
<div class="_form" id="addVideoForm">

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean" %>
<%  UserBean user = (UserBean) session.getAttribute("user");  %>

	<form method="POST" >
		<br />
		<table class="centered">
			<tr><td colspan="2"><center><strong>Advanced Search</strong></center></td></tr>
			<tr>
				<td>Search Criterion: 
					<input type="radio" name="radio" value="1" id="username" checked="checked"/>User Name
					<input type="radio" name="radio" value="2" id="date" />Date
					<input type="radio" name="radio" value="3" id="tag" />Tag
				</td>
			</tr>
		</table>
		<br />
		<input type="submit" name="button" style="width:11em" value="Search" id="search" />
	</form>

<jsp:include page="errorMessage.jsp" />

</div>
