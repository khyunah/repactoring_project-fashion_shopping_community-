<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<style>
.d-block {
	object-fit: cover;
	height: 500px;
}

.scroll-image {
	display: flex;
	flex-wrap: wrap;
	align-items: flex-start;
	margin: 30px 0;
}

.scroll-image img {
	width: calc(33.333% - 10px);
	margin: 0 15px 15px 0;
}

.scroll-image img:nth-of-type(3n), .scroll-image img:last-child {
	margin-right: 0;
}

@media screen and (max-width:480px) {
	.scroll-image {
		flex-wrap: nowrap;
		justify-content: flex-start;
		position: relative;
		width: 114%;
		left: -7%;
		padding: 0 7%;
		overflow-y: hidden;
		overflow-x: auto;
		-ms-overflow-style: none;
		-webkit-overflow-scrolling: touch;
	}
	.scroll-image img {
		display: block;
		flex: 0 0 auto;
		width: 80%;
		margin: 0 15px 0 0;
	}
	.scroll-image img:last-of-type {
		margin-right: 0;
	}
	.scroll-image:after {
		content: '';
		display: block;
		flex: 0 0 auto;
		align-self: stretch;
		width: 7%;
	}
}
</style>

<div id="carouselExampleDark" class="carousel carousel-dark slide"
	data-bs-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#carouselExampleDark"
			data-bs-slide-to="0" class="active" aria-current="true"
			aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#carouselExampleDark"
			data-bs-slide-to="1" aria-label="Slide 2"></button>
		<button type="button" data-bs-target="#carouselExampleDark"
			data-bs-slide-to="2" aria-label="Slide 3"></button>
	</div>
	<div class="carousel-inner">
		<div class="carousel-item active" data-bs-interval="2000">
			<div class="scroll-image">
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-9.jpg?q=90&w=1400&cbr=1&fit=max" />
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-49.jpg?q=90&w=1400&cbr=1&fit=max" />
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-39.jpg?q=90&w=1400&cbr=1&fit=max" />
			</div>
			<div class="carousel-caption d-none d-md-block"></div>
		</div>
		<div class="carousel-item" data-bs-interval="2000">
			<div class="scroll-image">
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-13.jpg?q=90&w=1400&cbr=1&fit=max" />
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-18.jpg?q=90&w=1400&cbr=1&fit=max" />
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-19.jpg?q=90&w=1400&cbr=1&fit=max" />
			</div>
			<div class="carousel-caption d-none d-md-block"></div>
		</div>
		<div class="carousel-item">
			<div class="scroll-image">
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-25.jpg?q=90&w=1400&cbr=1&fit=max" />
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-32.jpg?q=90&w=1400&cbr=1&fit=max" />
				<img class="d-block" alt=""
					src="https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2022%2F03%2Fmilan-fashion-week-fall-winter-2022-street-style-snaps-33.jpg?q=90&w=1400&cbr=1&fit=max" />
			</div>
			<div class="carousel-caption d-none d-md-block"></div>
		</div>
	</div>
	
</div>
<div style="text-align: center; margin-top: 20px;">
	<span class="">
		<button onclick="location.href='/shop/womans_form'" id="showall"
			class="btn text-white m-2"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">
			ShowAll</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=SHIRTS'"
			id="mans_shirts" class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Shirts</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=PANTS'"
			class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Pants</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=ACCESSORY'"
			class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Accessories</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=SHOES'"
			class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Shoes</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=OUTER'"
			class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Outer</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=SKIRT'"
			class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Skirt</button>
		<button
			onclick="location.href='/shop/womans_form/?gender=WOMAN&category=ONEPIECE'"
			class="btn m-2 text-white"
			style="background-color: #453675; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">One-piece</button>
	</span>
</div>
<div class="h100">
	<br />
	<section>
		<div class="container" id="mCard">
			<div id="list-container-md" class="row">
				<%!int index = 0;%>
				<c:forEach var="item" items="${pageable.content}">
				
				<c:forEach var="formatprice" items="${formatPriceList}">
					<c:if test="${item.id == formatprice.id}">
						<c:set var="price" value="${formatprice.price}"></c:set>
					</c:if>
				</c:forEach>
					<c:choose>
						<c:when test="#">

						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
					<div class="col-3 mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<a class="item" href="/shop/itemdetail_form/${item.id}"> <img
								class="card-img-top" src="${item.imageurl}" alt="..."
								style="width: 200px; height: 253px;">
							</a>
							<!-- Product details-->
							<div class="card-body p-4">
								<div
									style="float: left; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">${item.name}</div>
								<div
									style="float: right; margin-left: 100px; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">${price} 원</div>
								<br />
							</div>
							<div class="">
								<div
									style="margin-left: 23px; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">size
									: ${item.size}</div>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
</div>
<br />



<ul class="pagination justify-content-center">
	<!-- 삼항 연산자 (set : 변수 선언) -->
	<c:set var="isDisabled" value="disabled"></c:set>
	<c:set var="isNotDisabled" value=""></c:set>
	<c:set var="isNowPage" value="acive"></c:set>

	<li class="page-item ${pageable.first ? isDisabled : isNotDisabled}">
		<a class="page-link"
		href="/shop/search/?q=${searchTitle}&page=${pageable.number - 1}">이전</a>
	</li>

	<c:forEach var="num" items="${pageNumbers}">
		<c:choose>
			<c:when test="${pageable.number + 1 eq num}">
				<li class="page-item active"><a class="page-link bg-primary"
					href="/shop/search/?q=${searchTitle}&page=${num - 1}">${num}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="/shop/search/?q=${searchTitle}&page=${num - 1}">${num}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<li class="page-item ${pageable.last ? isDisabled : isNotDisabled}">
		<a class="page-link test"
		href="/shop/search/?q=${searchTitle}&page=${pageable.number + 1}">다음</a>
	</li>
</ul>
<br />

<script src="../js/search.js">
	
</script>
