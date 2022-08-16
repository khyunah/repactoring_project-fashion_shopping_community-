<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>

<div class="container">
	<div style="height: 50px;"></div>
	<h2>커뮤니티 상세보기</h2>
	<div style="height: 20px;"></div>

	<div style="display: flex; justify-content: end;">
		<a href="/admin/community/select?keyword=&column="
			class="btn btn-dark">전체목록</a>
		<button type="button" class="btn btn-danger"
			onclick="admin.communityDelete()">삭제</button>
	</div>

	<div style="height: 50px;"></div>

	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>USERNAME</th>
				<th>LIKECOUNT</th>
				<th>CREATEDATE</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${communityBoard.id}</td>
				<td>${communityBoard.user.username}</td>
				<td>${communityBoard.likeCount}</td>
				<td>${communityBoard.createDate}</td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<thead>
			<tr>
				<th>TITLE</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${communityBoard.title}</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th>IMAGE</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div class="admin-detail-img-box">
						<img class="card-img-top" src="/upload/${communityBoard.imageUrl}"
							alt="Card image" style="width: 100%">
					</div>
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th>CONTENT</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${communityBoard.content}</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="admin-id-box">
	<span id="admin-object-id" style="display: none;">${communityBoard.id}</span>
</div>
<script src="/js/admin.js"></script>
</body>
</html>
