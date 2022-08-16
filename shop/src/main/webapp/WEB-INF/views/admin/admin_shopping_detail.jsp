<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>

<div class="container">
	<div style="height: 50px;"></div>
	<h2>상품 상세보기</h2>
	<div style="height: 20px;"></div>

	<div style="display: flex; justify-content: end;">
		<input type="hidden" id="itemId" value="${item.id}"> <a
			href="/admin/shopping/select?keyword=&column="
			class="btn btn-primary">전체목록</a> <a
			href="/admin/shopping-item/update_form/${item.id}"
			class="btn btn-warning">수정</a>
		<button type="button" class="btn btn-danger"
				onclick="admin.shoppingDelete()">삭제</button>
	</div>

	<div style="height: 50px;"></div>

	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>PRICE</th>
				<th>CATEGORY</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td>${item.category}</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th>SIZE</th>
				<th>GENDER</th>
				<th>AMOUNT</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${item.size}</td>
				<td>${item.gender}</td>
				<td>${item.amount}</td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<div style="height: 50px;"></div>
	<table class="table">
		<thead>
			<tr>
				<th>IMAGE</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div class="admin-detail-img-box">
						<img class="card-img-top" src="${item.imageurl}" alt="Card image"
							style="width: 100%">
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
				<td>${item.content}</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="admin-id-box">
	<span id="admin-object-id" style="display: none;">${item.id}</span>
</div>
</body>
</html>
