<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div style= "left: 30%; position: absolute;">
<input type="hidden" id="itemId" name="itemId" value="${item.id}">
	<br/><br/>
	<br/><br/>
	<input type="hidden" id="itemname" value="${item.name}">
	<input type="hidden" id="itemgender" value="${item.gender}">
	<div class="form-group m-2 inline row">
		<div style="width: 420px;">
			<img style="width: 420px; height: 580px;" src="${item.imageurl}" alt=""/>
		</div>
		<div style="width: 420px; margin-left: 120px; margin-bottom: 20px;">
			<h2 style="font-size: 38px; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">${item.name}</h2>
			<br/>
			<h2 style="font-size: 38px; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;"><span style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">판매가</span>&nbsp;&nbsp;${item.price}원</h2>
			<br/>
			<h2 style="font-size: 38px; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;"><span style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold;">Size :</span>&nbsp;&nbsp;${item.size}</h2>
			<br/>
			<input type="hidden" id=userid value="${principal.user.id}"></input>
			<c:choose>
				<c:when test="${checkAmount < 1}">
					<button type="button" id="inputCart" value="${item.id}" class="btn text-white" style="background-color: #453675;" disabled="disabled">장바구니에 넣기 ( 품절 )</button>
				</c:when>
				<c:otherwise>
					<button type="button" id="inputCart" value="${item.id}" class="btn text-white" style="background-color: #453675;">장바구니에 넣기</button>
				</c:otherwise>
			</c:choose>
			<button class="btn text-white" onclick="history.back();" style="background-color: #453675;">뒤로 가기</button>
		</div>	
	</div>

	<div class="form-group m-5">
	<button type="button" class="btn text-white" style="background-color: #453675;">상품 설명</button>
	</div>
	<br/>
	<h4 style="margin-left: 50px;">${item.content}</h4>
	<br/><br/>
	<c:if test="${purchasehistory[0].item.id == item.id}">  
	<div class="form-group m-5"> 
	<button type="button" class="btn text-white" onclick="reviewWrite();" style="background-color: #453675; margin-left: 20px;">상품리뷰 작성하기</button>
	</div>
	</c:if>
	<div style="height: 20px"></div>
	
	 <form action="/review/upload/${item.id}" enctype="multipart/form-data" method="post">
  		<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}">
	<div id="writeReview" style="margin-left: 20px;"></div>
	</form>

	<hr/>
	
<c:forEach var="itemreview" items="${pageable.content}">
	<form action="/review/update/${itemreview.id}" enctype="multipart/form-data" method="post">
  		<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}">
	<div id="updateReview" style="margin-left: 20px;"></div>	
	</form>
	<div style="margin-top: 20px;">
	
	<input type="hidden" id="ItemReveiwId" value="${itemreview.id}">
	<input type="hidden" id="ItemReveiwImageUrl" value="${itemreview.imageUrl}">
	<h2 style="margin-left: 150px;">작성자 : ${itemreview.user.username}</h2>
		<img alt="" src="/upload/${itemreview.imageUrl}" style="height: 350px; width: 350px; border-radius: 15px; margin-left: 150px; object-fit: cover;">
		<h4 id ="ItemReveiwContent"style="margin-left: 150px; margin-top: 15px;]">${itemreview.content}</h4>
		<c:if test="${itemreview.user.id == principal.user.id}">
		<button type="button" class="btn text-white" onclick="updateBtnReview('${itemreview.content},${itemreview.originImageTitle}');" style="background-color: #453675; margin-left: 150px;">수정</button>
		<button type="button" class="btn text-white" onclick="ItemReviewDelete(${itemreview.id}, ${itemreview.item.id}, ${principal.user.id});" style="background-color: #453675; margin-left: 20px;">삭제</button>
		<hr/>
		</c:if>
	</div>

</c:forEach>	

	<br/>

<br/>
<ul class="pagination justify-content-center">
	<!-- 삼항 연산자 (set : 변수 선언) -->
	<c:set var="isDisabled" value="disabled"></c:set>
	<c:set var="isNotDisabled" value=""></c:set>
	<c:set var="isNowPage" value="acive"></c:set>

	<li class="page-item ${pageable.first ? isDisabled : isNotDisabled}">
		<a class="page-link"
		href="/shop/itemdetail_form/${item.id}/?q=${searchTitle}&page=${pageable.number - 1}">이전</a>
	</li>

	<c:forEach var="num" items="${pageNumbers}">
		<c:choose>
			<c:when test="${pageable.number + 1 eq num}">
				<li class="page-item active"><a class="page-link bg-primary"
					href="/shop/itemdetail_form/${item.id}/?q=${searchTitle}&page=${num - 1}">${num}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="/shop/itemdetail_form/${item.id}/?q=${searchTitle}&page=${num - 1}">${num}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<li class="page-item ${pageable.last ? isDisabled : isNotDisabled}">
		<a class="page-link test"
		href="/shop/itemdetail_form/${item.id}/?q=${searchTitle}&page=${pageable.number + 1}">다음</a>
	</li>
</ul>
</div>
<script src="/js/item.js"></script>
<script src="/js/basket.js"></script>