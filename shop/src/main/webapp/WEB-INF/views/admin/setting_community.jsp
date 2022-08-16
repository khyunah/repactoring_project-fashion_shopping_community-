<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>

<div class="admin-container">

<div class="container">

	<div style="height: 50px;"></div>
	<h2>커뮤니티 관리</h2>
	<div style="height: 20px;"></div>

	<hr />

	<div class="setting-header-container">

		<div class="d-flex">

			<div class="form-group mr-2">
				<select class="form-control" id="sel1" onchange="chooseColumn(this)">
					<option>선택</option>
					<option>ID</option>
					<option>USERNAME</option>
					<option>TITLE</option>
				</select>
			</div>

			<div>
				<form class="form-inline" action="/admin/community/select"
					method="get" onsubmit="return checkColumn()">
					<input type="text" class="form-control" name="keyword"
						value="${keyword}" placeholder="검색어를 입력해주세요." id="keyword" /> <input
						type="hidden" id="column" name="column" value="${column}">
					<button type="submit" class="btn btn-dark ml-2">검색</button>
				</form>
			</div>

		</div>

		<div class="setting-btn-box">
		<a href="/admin/community/select?keyword=&column=" class="btn btn-dark">전체조회</a>
			<button type="button" class="btn btn-primary"
				onclick="admin.communityDetail()">상세보기</button>
			<button type="button" class="btn btn-danger"
				onclick="admin.communityDelete()">삭제</button>
		</div>

	</div>

	<div style="height: 70px;"></div>

	<table class="table table-dark table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>USERNAME</th>
				<th>IMAGE</th>
				<th>TITLE</th>
				<th>LIKECOUNT</th>
				<th>CREATEDATE</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="communityBoard" items="${communityBoardPage.content}">
				<tr onclick="clickList(this)">
					<td>${communityBoard.id}</td>
					<td class="admin-commu-td">${communityBoard.user.username}</td>
					<td class="admin-td-img">
						<div class="admin-img-box">
							<img class="card-img-top admin-list-img"
								src="/upload/${communityBoard.imageUrl}" alt="Card image"
								style="width: 100%">
						</div>
					</td>
					<td>${communityBoard.title}</td>
					<td>${communityBoard.likeCount}</td>
					<td>${communityBoard.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div style="height: 100px"></div>
	<div class="admin-pagenation-container">
		<ul class="pagination justify-content-center">
			<c:set var="isDisabled" value="disabled"></c:set>
			<c:set var="isNotDisabled" value=""></c:set>
			<c:set var="isNowPage" value="active"></c:set>

			<li
				class="page-item ${communityBoardPage.first ? isDisabled : isNotDisabled}">
				<a class="page-link"
				href="/admin/user/select?keyword=${keyword}&column=${column}&page=${communityBoardPage.number - 1}">이전</a>
			</li>

			<c:forEach var="num" items="${pageNumbers}">
				<c:choose>
					<c:when test="${communityBoardPage.number + 1 eq num}">
						<li class="page-item active"><a class="page-link"
							href="/admin/user/select?keyword=${keyword}&column=${column}&page=${num - 1}">${num}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/admin/user/select?keyword=${keyword}&column=${column}&page=${num - 1}">${num}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<li
				class="page-item ${communityBoardPage.last ? isDisabled : isNotDisabled}">
				<a class="page-link"
				href="/admin/user/select?keyword=${keyword}&column=${column}&page=${communityBoardPage.number + 1}">다음</a>
			</li>
		</ul>
	</div>
	<div class="admin-id-box">
		<span id="admin-object-id" style="display: none;"></span>
	</div>
</div>
</div>

<script src="/js/admin.js"></script>
</body>
</html>
