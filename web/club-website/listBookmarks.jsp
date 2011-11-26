
<jsp:include page="templateTop.jsp" />

<jsp:include page="navigationBar.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${listType == 'myBookmarks'}">
	<jsp:include page="addBookmark.jsp" />
</c:if>

<div id="bookmarkList">
	<c:if test="${! empty(bookmarkList)}">
		<table class="centered" id="bookmarkListTable">
			<tr style="background-color: #E0FFE0">
				<th style="border: 1px solid #BEBEBE">Bookmark</th>
				<th style="border: 1px solid #BEBEBE">Comment</th>
				<c:if test="${listType != 'myBookmarks'}">
					<th style="border: 1px solid #BEBEBE">Owner</th>
				</c:if>
				<th style="border: 1px solid #BEBEBE">Clicks</th>
				<c:if test="${listType == 'myBookmarks'}">
					<th style="border: 1px solid #BEBEBE">Action</th>
				</c:if>
			</tr>
			<c:forEach var="bookmark" items="${bookmarkList}">
				<tr class="bookmarkListRow" id="bookmark${bookmark.id}">
					<td class="bookmarkListCell navigationBarItem">
						<p><a class="bookmark" href="clickLink.do?bookmarkIdStr=${bookmark.id}&listType=${listType}">${bookmark.url}</a></p>
					</td>
					<td class="bookmarkListCell">
						<p class="comment">${bookmark.comment}</p>
					</td>
					<c:if test="${listType != 'myBookmarks'}">
						<td class="bookmarkListCell">
							<p class="owner">${bookmark.userEmailAddress}</p>
						</td>
					</c:if>
					<td class="bookmarkListCell">
						<p class="clicks">${bookmark.click}</p>
					</td>
					<c:if test="${listType == 'myBookmarks'}">
						<td class="navigationBarItem">
							<p><a class="remove" href="deleteBookmark.do?bookmarkIdStr=${bookmark.id}">Remove</a></p>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

<jsp:include page="templateBottom.jsp" />
