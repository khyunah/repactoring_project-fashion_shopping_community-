<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		<thead>
			<tr>
				<th>ID</th>
				<th>USERNAME</th>
				<th>NAME</th>
				<th>EMAIL</th>
				<th>PHONENUMBER</th>
				<th>ADDRESS</th>
				<th>OAUTH</th>
				<th>ROLE</th>
				<th>CREATEDATE</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userPage.content}">
				<tr onclick="clickList(this)">
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.phoneNumber}</td>
					<td>${user.address}</td>
					<td>${user.oauth}</td>
					<td>${user.role}</td>
					<td>${user.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${empty userPage.content}">
		<h3 class="admin-null-info">오늘 가입한 회원이 없습니다.</h3>
	</c:if>

	<div style="height: 100px"></div>
	<div class="admin-pagenation-container">
		<ul class="pagination justify-content-center">
			<c:set var="isDisabled" value="disabled"></c:set>
			<c:set var="isNotDisabled" value=""></c:set>
			<c:set var="isNowPage" value="active"></c:set>
			
			
			<li class="page-item ${userPage.first ? isDisabled : isNotDisabled}">
				<a class="page-link" href="/admin/user/select?keyword=${keyword}&column=${column}&page=${userPage.number - 1}">이전</a>
			</li>

			<c:forEach var="num" items="${pageNumbers}">
				<c:choose>
					<c:when test="${userPage.number + 1 eq num}">
						<li class="page-item active"><a class="page-link"
							href="/admin/user/select?keyword=${keyword}&column=${column}&page=${num - 1}">${num}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/admin/user/select?keyword=${keyword}&column=${column}&page=${num - 1}">${num}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<li class="page-item ${userPage.last ? isDisabled : isNotDisabled}">
				<a class="page-link"
				href="/admin/user/select?keyword=${keyword}&column=${column}&page=${userPage.number + 1}">다음</a>
			</li>
		</ul>
	</div>
	<div class="admin-id-box">
		<span id="admin-object-id" style="display: none;"></span>
	</div>