<%@ page import="java.util.*" %>
<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.VideoBean" %>
<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean" %>
<%@ page import="edu.cmu.cs15437.clubwebsite.model.UserDAO" %>


<div id="videoList">
	<% 
	List< VideoBean > list = (List<VideoBean>) request.getAttribute("videoList"); 
	UserDAO userdao =  new UserDAO();
	UserBean owner;
	
	if(list!=null){
	for( VideoBean video : list ){ %>
		<div class="_boxVideo">
		<table>
			<tr>
				<td rowspan="15">
					<iframe class="youtube-player" type="text/html" width="640" height="385" src="http://www.youtube.com/embed/<%=video.getLink()%>" frameborder="0">
					</iframe></td>
				<td><form method="POST" action="manageVideo.do">
					<input type="submit" style="width:14em" value="Edit Info" id="editVideo" name="button" />
					<input type="submit" style="width:14em" value="Delete Video" id="deleteVideo" name="button" />
					<input type="hidden" value="<%=video.getVideoId()%>" id="videoId" name="videoId" />
				</form></td>
			</tr>
			<tr><td><strong>User Name: </strong></td></tr>
			<tr><td><%=(userdao.lookupWithUserId(video.getOwnerId())).getUserName()%></td></tr>
			<tr><td><strong>Description: </strong></td></tr>
			<tr><td><%=video.getDescription()%></td></tr>
			<tr><td><strong>Date: </strong></td></tr>
			<tr><td><%= new Date(video.getDateValue()) %></td></tr>
			<tr><td><strong>Available to: </strong></td></tr>
			<%
			String groupName;
			switch (video.getAccessLevel()){
				case 1: groupName = "Basic Membership";		break;
				case 2: groupName = "Advanced Membership";	break;
				case 3: groupName = "CompTeam Member";		break;
				case 4: groupName = "Officer";				break;
				case 5: groupName = "Administrator";		break;
				default: groupName = "Public";	
			}
			%>
			<tr><td><%=groupName%> and above</td></tr>
			<tr><td><strong>Tag: </strong></td></tr>
			<tr><td></td></tr>
		</table>
		</div>
		<br />
	<% }}%>
</div>
