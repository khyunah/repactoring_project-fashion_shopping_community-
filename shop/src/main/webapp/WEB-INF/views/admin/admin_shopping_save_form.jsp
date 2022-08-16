<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>

<div style="height: 50px;"></div>

<div class="container">

	<form action="">
		<input type="hidden" value="admin" id="historyCheck">

		<div class="form-group">
			<label for="gender">gender :</label> <select id="gender">
				<option value="MAN">MAN</option>
				<option value="WOMAN">WOMAN</option>
			</select>
		</div>
		<div class="form-group">
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
				class="form-control" placeholder="Enter name" name="name" id="name">
		</div>
		<div class="form-group">
			<label for="price">price</label> <input type="text"
				class="form-control" placeholder="Enter price" name="price"
				id="price">
		</div>
		<div class="form-group">
			<label for="amount">amount</label> <input type="text"
				class="form-control" placeholder="Enter amount" name="amount"
				id="amount">
		</div>
		<div class="form-group">
			<label for="imageurl">imgUrl</label> <input type="text"
				class="form-control" placeholder="Enter imageurl" name="imageurl"
				id="imageurl">
		</div>
		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>


	</form>
	<button type="button" id="btn-save" class="btn btn-dark text-white" style="background-color: #453675; margin-top: 20px;">상품 등록</button>


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
