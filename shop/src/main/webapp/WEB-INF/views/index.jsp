<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<style>
.d-block {
	object-fit: contain;
	height: 500px;
	width: 200px;
}

#adcontainer {
	position: fixed;
	left: 5%;
	width: 200px;
	margin-top: 20px;
}

#ad {
	margin-top: 20px;
	height: 750px;
	width: 250px;
}
</style>
<div id="adcontainer">
	<div id="carouselExampleInterval" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active" data-bs-interval="10000">
				<a href="https://www.nike.com/kr/ko_kr/"> <img
					src="https://tpc.googlesyndication.com/simgad/8006856824244634350?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&rs=AOga4qlju3mWf_cUUHB_Z5CC5mHxo_HNOQ"
					class="d-block" alt="...">
				</a>
			</div>
			<div class="carousel-item" data-bs-interval="2000">
				<a
					href="https://whoisnerdy.com/?utm_source=google&utm_medium=sa_pc&utm_campaign=nerdy_sa&gclid=EAIaIQobChMIrePIydmT-QIVPsIWBR146gzcEAAYASAAEgJJ8PD_BwE">
					<img
					src="https://tpc.googlesyndication.com/daca_images/simgad/15696822161214466348"
					class="d-block" alt="...">
				</a>
			</div>
			<div class="carousel-item">
				<a
					href="https://whoisnerdy.com/?utm_source=google&utm_medium=sa_pc&utm_campaign=nerdy_sa&gclid=EAIaIQobChMIrePIydmT-QIVPsIWBR146gzcEAAYASAAEgJJ8PD_BwE">
					<img
					src="https://tpc.googlesyndication.com/daca_images/simgad/13356705103795557830"
					class="d-block" alt="...">
				</a>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
</div>
<div class="mainWrap">
	<input type="hidden" value="${principal.user.id}" id="userId">
	<div class="cardWrap cardWrapLeft">
		<input id="pageSize" type="hidden"
			value="${communityBoardList.pageable.pageSize}"> <input
			id="pageNumber" type="hidden" value="${communityBoardList.number}">
		<ul class="card-list leftCards">
			<%@ include file="community/add_community_index.jsp"%>
		</ul>
	</div>

		<div class="cardWrap cardWrapRight">
			<div class="container index-container">

				<c:forEach var="item" items="${itemList}" begin="0" end="3"
					varStatus="status">
					
					<c:forEach var="formatprice" items="${formatPriceList}">
						<c:if test="${item.id == formatprice.id}">
							<c:set var="price" value="${formatprice.price}"></c:set>
						</c:if>
					</c:forEach>

					<div class="card cardRight">
						<div>
							<img class="cardRightImgBox" src="${status.current.imageurl}">
						</div>
						<div class="card-body index-card-body">
							<h4 class="card-title" style="font-size: 15px;">상품명 :
								${status.current.name}</h4>
							<p class="card-text">${price} 원</p>
							<button style="background-color: #453675" class="cardRightBtn"
								onclick="location.href='shop/itemdetail_form/${status.current.id}'">See
								Detail</button>
						</div>
					</div>

				</c:forEach>
			</div>
			<!-- container -->
		</div>

	<input id="pageNumber" type="hidden"
		value="${communityBoardList.number}">
</div>
</body>
<script src="/js/commu.js"></script>
<script src="/js/index_scroll.js"></script>
<script>
	history.scrollRestoration = "manual"
</script>

</html>