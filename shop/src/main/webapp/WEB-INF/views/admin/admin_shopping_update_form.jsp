<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>

<div style="height: 50px;"></div>

<div class="container">
	<input type="hidden" id="itemId" value="${item.id}">
	<form action="">
		<div class="form-group">
			<label for="gender">gender :</label> <select id="gender">
				<c:choose>
					<c:when test="${item.gender == 'MAN'}">
						<option value="MAN" selected>MAN</option>
						<option value="WOMAN">WOMAN</option>
					</c:when>
					<c:otherwise>
						<option value="MAN">MAN</option>
						<option value="WOMAN" selected>WOMAN</option>
					</c:otherwise>
				</c:choose>

			</select>
		</div>
		<div class="form-group">
			<input id="itemCategory" type="hidden" value="${item.category}">
			<label for="category">category :</label> <select id="category"
				name="category">

			</select>
		</div>
		<div class="form-group">
			<label for="size">size :</label> <select id="size" name="size">
			</select>
		</div>

		<div class="form-group">
			<label for="name">name</label> <input type="text"
				class="form-control" placeholder="Enter name" name="name" id="name"
				value="${item.name}">
		</div>
		<div class="form-group">
			<label for="price">price</label> <input type="text"
				class="form-control" placeholder="Enter price" name="price"
				id="price" value="${item.price}">
		</div>
		<div class="form-group">
			<label for="amount">amount</label> <input type="text"
				class="form-control" placeholder="Enter amount" name="amount"
				id="amount">
		</div>
		<div class="form-group">
			<label for="imageurl">imgUrl</label> <input type="text"
				class="form-control" placeholder="Enter imageurl" name="imageurl"
				id="imageurl" value="${item.imageurl}">
		</div>
		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control summernote" rows="5" id="content">${item.content}</textarea>
		</div>

	</form>
	<button type="button" id="admin-shopping-btn-update"
		class="btn btn-dark text-white"
		style="background-color: #453675; margin-top: 20px;">상품 수정</button>

</div>
<br />
<br />

<script type="text/javascript">
	$('.summernote').summernote({
		placeholder : '내용을 입력해주세요',
		tabsize : 5,
		height : 300
	});
</script>
<script src="/js/item.js"></script>
</body>
</html>
